package com.epam.webapphello.entity;

public class User implements Identifable{

    public final static String TABLE = "user";

    private Long id;
    private String login;
    private String password;
    private String name;


    public User(Long id, String name,String login) {
        this.id = id;
        this.login = login;
        this.name = name;

    }
    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
