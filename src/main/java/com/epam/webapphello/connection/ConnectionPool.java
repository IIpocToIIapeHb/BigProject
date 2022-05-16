package com.epam.webapphello.connection;

import com.epam.webapphello.exception.ConnectionException;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static volatile ConnectionPool instance;
    private Queue<ProxyConnection> availableConnection;
    private Queue<ProxyConnection> connectionInUse;
    private static final int CONNECTIONS_NUMBER = 10;
    private static final Lock LOCK_INSTANCE = new ReentrantLock();
    private ConnectionFactory connectionFactory = new ConnectionFactory();


    private ConnectionPool() throws ConnectionException {
        fillConnectionPool();
    }


    public static ConnectionPool getInstance() throws ConnectionException {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            LOCK_INSTANCE.lock();
            localInstance = instance;
            if (localInstance == null) {
                instance = localInstance = new ConnectionPool();
            }
            LOCK_INSTANCE.unlock();
        }
        return localInstance;
    }


    public void returnConnection(ProxyConnection proxyConnection) {
        LOCK_INSTANCE.lock();
        try {
            if (connectionInUse.contains(proxyConnection)) {
                availableConnection.offer(proxyConnection);
                connectionInUse.remove(proxyConnection);
            }
        } finally {
            LOCK_INSTANCE.unlock();
        }
    }

    public ProxyConnection getConnection() throws ConnectionException {
        ProxyConnection connection = null;
        LOCK_INSTANCE.lock();
        try {
            if (!availableConnection.isEmpty()) {
                connection = availableConnection.poll();
            } else {
                connection = new ProxyConnection(connectionFactory.getConnection(), this);
            }
            connectionInUse.offer(connection);
        } finally {
            LOCK_INSTANCE.unlock();
        }
        return connection;
    }


    private void fillConnectionPool() throws ConnectionException {
        if (availableConnection == null && connectionInUse == null) {
            availableConnection = new ArrayDeque<>();
            for (int i = 0; i < CONNECTIONS_NUMBER; i++) {
                    availableConnection.offer(new ProxyConnection(connectionFactory.getConnection(), this));
            }
            connectionInUse = new ArrayDeque<>();
        }
    }
}
