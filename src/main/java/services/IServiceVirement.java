package services;

import Entities.Virement;

import java.sql.SQLException;
import java.util.List;

public interface IServiceVirement<T> {
    void ajouter(Virement virement) throws SQLException;

    void modifier(Virement virement) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<Virement> afficher() throws SQLException;
}
