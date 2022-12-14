package com.ll.exam.db;

import lombok.Getter;
import lombok.Setter;

public class MyDb {
    @Getter
    @Setter
    private boolean isDevMode;
    private ConnectionPool connectionPool;

    public MyDb(String host, int port, String username, String password, String dbName) {
        connectionPool = new ConnectionPool(host, port, username, password, dbName);
    }

    public MyDb(String host, String username, String password, String dbName) {
        this(host, 3306, username, password, dbName);
    }

    public SecSql genSecSql() {
        return new SecSql(connectionPool, isDevMode);
    }

    public void run(String sql, Object... bindingDatum) {
        genSecSql().append(sql, bindingDatum).update();
    }

    public void closeConnection() {
        connectionPool.closeConnection();
    }
}
