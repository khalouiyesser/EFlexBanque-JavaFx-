package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {

    final String url = "jdbc:mysql://localhost:3306/dev";
    final String username = "root";
    final String pwd = "";
    private Connection conx;

    public static MyDB instance;

    public static MyDB getInstance() {
        if (instance == null)
            instance = new MyDB();
        return instance;

    }

    private MyDB() {

        try {
            conx = DriverManager.getConnection(url, username, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Connection getConx() {
        return conx;
    }

}
