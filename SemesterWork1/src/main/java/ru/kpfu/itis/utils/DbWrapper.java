package ru.kpfu.itis.utils;

import ru.kpfu.itis.exceptions.DbException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbWrapper {
    private static Connection conn;

    public static Connection getConnection() throws DbException {
        Properties environment = new Properties();
        try {
            environment.load(DbWrapper.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        if(conn == null){
            try{
                Class.forName(environment.getProperty("db.driver"));
                conn = DriverManager.getConnection(environment.getProperty("db.url"),
                        environment.getProperty("db.user"), environment.getProperty("db.password"));
            }
            catch(ClassNotFoundException ex){
                throw new DbException("Can't find DB driver.");
            } catch (SQLException ex) {
                throw new DbException("Can't connect to DB (" + ex.getErrorCode() + ": " + ex.getMessage() + ").");
            }
        }
        return conn;
    }
}
