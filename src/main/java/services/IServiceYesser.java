package services;

import Entities.OffreDeStage;

import java.sql.SQLException;
import java.util.List;

public interface IServiceYesser<T>{
    void ajouter(T t) throws SQLException;
    void supprimer(int id) throws SQLException;
    void modifier (T t) throws SQLException;
    public List<T> afficher() throws SQLException;
    OffreDeStage afficheUne(int id) throws SQLException;
}