/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Entities.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

  private static DataSource data;
  private Connection con;
  private String url = "jdbc:mysql://localhost:3306/yessertest";
  private String login = "root";
  private String pwd = "";
  //set password

  private DataSource() {
    try {
      con = DriverManager.getConnection(url, login, pwd);
      System.out.println("Connexion établie");
    } catch (SQLException ex) {
      System.out.println(ex);
    }
  }

  public Connection getCon() {
    return con;
  }

  public static DataSource getInstance() {
    if (data == null) {
      data = new DataSource();
    }
    return data;
  }

  public List<Project> getProjects() throws SQLException {
    List<Project> projects = new ArrayList<>();
    try (Statement statement = con.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM project")) {
      while (resultSet.next()) {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setNomProjet(resultSet.getString("nomProjet"));

        projects.add(project);
      }
    }
    return projects;
  }
}
