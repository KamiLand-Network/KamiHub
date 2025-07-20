package net.kamiland.kamihub.data.manager.cp;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPoolManager {

    void init();
    void close();

    public Connection getConnection() throws SQLException;

}
