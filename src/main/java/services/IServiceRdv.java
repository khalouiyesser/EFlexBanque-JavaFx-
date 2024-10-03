package services;

import Entities.RDV;

import java.sql.SQLException;
import java.util.List;

public interface IServiceRdv<T> {


    void supprimer(int id) throws SQLException;

    List<RDV> afficher() throws SQLException;
}
