package services;

import Entities.Project;

import java.sql.SQLException;
import java.util.List;


public interface IProjectService<T> {

    public void ajouter(Project user) throws SQLException;

    public Project getOneUser(String email) throws SQLException;

    public Project getUserById(int email) throws SQLException;
  public List<T> afficher() throws SQLException;
}
