package services;

import Entities.Cheque;

import java.sql.SQLException;
import java.util.List;

public interface IServiceCheque<T> {
    boolean ajouter(Cheque cheque) throws SQLException;

    void modifier(Cheque cheque) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<Cheque> afficher() throws SQLException;
}
