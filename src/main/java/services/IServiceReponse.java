package services;

import java.sql.SQLException;
import java.util.List;

public interface IServiceReponse<T> {
    void ajouter(T t) throws SQLException;
    void supprimer(T t) throws SQLException;
    void modifier (T t) throws SQLException;
    public List<T> afficher() throws SQLException;
    void supprimerParId(int id) throws SQLException;


    }
