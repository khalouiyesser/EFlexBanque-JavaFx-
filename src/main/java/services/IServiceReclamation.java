package services;

import java.sql.SQLException;
import java.util.List;

public interface IServiceReclamation <T> {
    boolean ajouter(T t) throws SQLException;
    void supprimer(T t) throws SQLException;
    public List<T> afficher() throws SQLException;

}