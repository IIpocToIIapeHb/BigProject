package com.epam.webapphello.connection;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static volatile ConnectionPool instance;
    private Queue<ProxyConnection> availableConnection;
    private Queue<ProxyConnection> connectionInUse;
    private static final int CONNECTIONS_NUMBER = 10;
    private static final Lock LOCK = new ReentrantLock();
    private Lock getConnectionLocker = new ReentrantLock();
    private Lock returnConnectionLocker = new ReentrantLock();
    private ConnectionFactory connectionFactory = new ConnectionFactory();


    private ConnectionPool(){
        fillConnectionPool();
    }


    public static ConnectionPool getInstance()  {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            LOCK.lock();
            localInstance = instance;
            if (localInstance == null) {
                instance = localInstance = new ConnectionPool();
            }
            LOCK.unlock();
        }
        return localInstance;
    }


    public void returnConnection(ProxyConnection proxyConnection) {
        returnConnectionLocker.lock();
        try {
            if (connectionInUse.contains(proxyConnection)) {
                availableConnection.offer(proxyConnection);
                connectionInUse.remove(proxyConnection);
            }
        } finally {
            returnConnectionLocker.unlock();
        }
    }

    public ProxyConnection getConnection()  {
        ProxyConnection connection = null;
        getConnectionLocker.lock();
        try {
            if (!availableConnection.isEmpty()) {
                connection = availableConnection.poll();
            } else {
                connection = new ProxyConnection(connectionFactory.getConnection(), this);
            }
            connectionInUse.offer(connection);
        } finally {
            getConnectionLocker.unlock();
        }
        return connection;
    }


    private void fillConnectionPool() {
        if (availableConnection == null && connectionInUse == null) {
            availableConnection = new ArrayDeque<>();
            for (int i = 0; i < CONNECTIONS_NUMBER; i++) {
                    availableConnection.offer(new ProxyConnection(connectionFactory.getConnection(), this));
            }
            connectionInUse = new ArrayDeque<>();
        }
    }
}
