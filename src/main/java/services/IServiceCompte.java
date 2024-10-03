package services;

import Entities.Compte;

import java.sql.SQLException;
import java.util.List;

public interface IServiceCompte<T> {
    void ajouter(Compte compte) throws SQLException;

    void modifier(Compte compte) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<Compte> afficher() throws SQLException;
}
