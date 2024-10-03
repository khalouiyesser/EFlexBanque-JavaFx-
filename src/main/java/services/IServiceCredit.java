package services;

import Entities.Credit;

import java.sql.SQLException;
import java.util.List;

public interface IServiceCredit<T> {



    void supprimer(int id) throws SQLException;

    List<Credit> afficher() throws SQLException;
}
