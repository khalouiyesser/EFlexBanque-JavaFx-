/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Commentaire;
import Entities.Evenement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public interface IService<T> {
  void ajouter(T t) throws SQLException, IOException;
  void ajouter1(T t,int projectId) throws SQLException;
  public List<Evenement> sortEvent(int value, int idCategory); // 0: sort by stock *** 1: sort by category *** 2: sort

  void modifier (T t) throws SQLException;
  void modifierlike (T t) throws SQLException;
  void modifierdislike (T t) throws SQLException;

  public Evenement getOneEvenement(int idEvenement) throws SQLException;
  public void AddEvenenemtOffer(Evenement evenement);
  public void supprimer(int id) throws SQLException;
  public List<T> afficher() throws SQLException;

  List<Commentaire> show(int evenement_id) throws SQLException;

  public T afficher1(int id) throws SQLException;

}
