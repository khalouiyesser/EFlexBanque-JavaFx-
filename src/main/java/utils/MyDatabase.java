package utils;

import java.sql.*;


public class MyDatabase {

    final String URL="jdbc:mysql://localhost:3306/yessertest";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private Connection connection;
    private static MyDatabase instance;

    public MyDatabase() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println("ouvre xampp");      }
    }


    public static MyDatabase getInstance() {
        if(instance == null)
            instance = new MyDatabase();
        return instance;
    }


    public Connection getConnection() {

        return connection;
    }


}
