package com.hydroyura.dictinaryapp;

import com.badlogic.gdx.Gdx;
import com.hydroyura.dictinaryapp.core.model.Word;
import com.hydroyura.dictinaryapp.core.repository.IRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DesktopRepositorySQLite implements IRepository {

    private static final String DB_NAME = "dictionary_db_desktop.db";
    private static final String DB_TEMPLATE = "assets/db_template.db";
    private static final String DB_TABLES_WORD = "words";

    private Connection connection;

    public DesktopRepositorySQLite() {}


    @Override
    public void dispose() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            if(!Gdx.files.internal(DB_NAME).exists()) {

                throw new RuntimeException();
            }

            this.connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addWord(Word word) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO words (original, type, collection_id, translate, train_status)" +
                    "VALUES ('"
                        + word.getOriginal() + "', '"
                        + word.getType() + "', '"
                        + word.getCollectionId() + "', '"
                        + Arrays.stream(word.getTranslate()).collect(Collectors.joining(",")) + "', "
                        + word.getTrainStatus() + ");";
            statement.executeUpdate(sql);
            statement.close();
            //connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


/*
stmt = c.createStatement();
         String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                  "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                  "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                  "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
         stmt.executeUpdate(sql);

         stmt.close();
         c.commit();
 */