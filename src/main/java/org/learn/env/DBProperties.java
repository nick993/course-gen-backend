package org.learn.env;

public class DBProperties {
    private String address;
    private String dbName;
    private String user;
    private String password;

    DBProperties(String address, String dbName, String user, String password) {
        this.address = address;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }


    public String getAddress() {
        return address;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}