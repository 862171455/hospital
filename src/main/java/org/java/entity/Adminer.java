package org.java.entity;

public class Adminer {
    private Integer adminerId;

    private String password;

    private String username;

    private Integer stafffId;

    public Integer getAdminerId() {
        return adminerId;
    }

    public void setAdminerId(Integer adminerId) {
        this.adminerId = adminerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getStafffId() {
        return stafffId;
    }

    public void setStafffId(Integer stafffId) {
        this.stafffId = stafffId;
    }
}