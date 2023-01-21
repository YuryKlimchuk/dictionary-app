package com.hydroyura.dictinaryapp.data;

import com.hydroyura.dictinaryapp.data.repository.IRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository implements IRepository {

    private Connection connection;

    public Repository() {
        connect();
    }

    void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:assets/data/JTP.db";
            // create a connection to the database
            connection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    void initSchema() {

    }





}
