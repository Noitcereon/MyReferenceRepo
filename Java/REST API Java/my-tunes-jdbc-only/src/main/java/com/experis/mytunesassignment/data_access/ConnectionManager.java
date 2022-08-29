package com.experis.mytunesassignment.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Abstract ConnectionManager into an interface.
// TODO: implementation IConnectionManager w. url to local and production db (Profile prod & Profile dev)

public class ConnectionManager {
    private static ConnectionManager instance;
    private Connection connection;
    private static final String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    // jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite (local development)
    // jdbc:sqlite::resource:Chinook_Sqlite.sqlite (production)

    private ConnectionManager(){
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ConnectionManager getInstance(){
        if(instance == null){
            instance = new ConnectionManager();
        }
        return instance;
    }
    public Connection getConnection(){
        try {
            if(getInstance().connection.isClosed()){
                getInstance().connection = DriverManager.getConnection(URL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getInstance().connection;
    }

}
