package services;

import utils.MyDatabase;
import Entities.Compte;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCompte  implements  IServiceCompte <Compte> {

    public Connection connection;
    public Statement statement;


    public ServiceCompte() {
        connection = MyDatabase.getInstance().getConnection();
    }


    @Override
    public void ajouter(Compte compte) throws SQLException {
        String req = "INSERT INTO compte "
                + "(email, confirmation_email, cin, date_delivrance_cin, nom, prenom, sexe, date_naissance, proffesion, "
                + "type_compte, montant, statut_marital, nationalite, numero_telephone, preference_communic, "
                + "type_cin, rib, statut) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, compte.getEmail());
            ps.setString(2, compte.getConfirmation_email());
            ps.setInt(3, compte.getCin());
            ps.setObject(4, compte.getDate_delivrance_cin());
            ps.setString(5, compte.getNom());
            ps.setString(6, compte.getPrenom());
            ps.setString(7, compte.getSexe());
            ps.setObject(8, compte.getDate_naissance());
            ps.setString(9, compte.getProffesion());
            ps.setString(10, compte.getType_compte());
            ps.setDouble(11, compte.getMontant());
            ps.setString(12, compte.getStatut_marital());
            ps.setString(13, compte.getNationalite());
            ps.setInt(14, compte.getNumero_telephone());
            ps.setString(15, compte.getPreference_communic());
            ps.setString(16, compte.getType_cin());
            ps.setLong(17, compte.getRib());
            ps.setString(18, compte.getStatut());
            System.out.println("ici c'est Paris" + compte.getStatut());

            ps.executeUpdate();
            System.out.println("Compte ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void modifier(Compte compte) throws SQLException {
        try {
            String req = "UPDATE compte SET email=?, confirmation_email=?, cin=?, date_delivrance_cin=?, "
                    + "nom=?, prenom=?, sexe=?, date_naissance=?, proffesion=?, type_compte=?, "
                    + "montant=?, statut_marital=?, nationalite=?, numero_telephone=?, preference_communic=?, "
                    + "type_cin=?, rib=?, statut=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, compte.getEmail());
            ps.setString(2, compte.getConfirmation_email());
            ps.setInt(3, compte.getCin());
            ps.setObject(4, compte.getDate_delivrance_cin());
            ps.setString(5, compte.getNom());
            ps.setString(6, compte.getPrenom());
            ps.setString(7, compte.getSexe());
            ps.setObject(8, compte.getDate_naissance());
            ps.setString(9, compte.getProffesion());
            ps.setString(10, compte.getType_compte());
            ps.setDouble(11, compte.getMontant());
            ps.setString(12, compte.getStatut_marital());
            ps.setString(13, compte.getNationalite());
            ps.setInt(14, compte.getNumero_telephone());
            ps.setString(15, compte.getPreference_communic());
            ps.setString(16, compte.getType_cin());
            ps.setLong(17, compte.getRib());
            ps.setString(18, compte.getStatut());
            ps.setInt(19, compte.getId());

            ps.executeUpdate();
            System.out.println("Compte avec ID " + compte.getId() + " modifié !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }

    public void modifierOne(Compte compte) throws SQLException {
        try {
            String req = "UPDATE compte SET statut=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, "Approuve");
            ps.setInt(2, compte.getId());

            ps.executeUpdate();
            System.out.println("Compte avec ID " + compte.getId() + " modifié !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM compte WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Compte avec ID " + id + " supprimé !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public List<Compte> afficher() throws SQLException {
        List<Compte> list = new ArrayList<>();


        try {
            String req = "SELECT * FROM compte";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Compte(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("confirmation_email"),
                        rs.getInt("cin"),
                        rs.getDate("date_delivrance_cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("sexe"),
                        rs.getDate("date_naissance"),
                        rs.getString("proffesion"), // Correction de la variable proffesion à profession
                        rs.getString("type_compte"),
                        rs.getDouble("montant"),
                        rs.getString("statut_marital"),
                        rs.getString("nationalite"),
                        rs.getInt("numero_telephone"), // Ajout du numéro de téléphone
                        rs.getString("type_cin"),
                        rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List<Compte> afficherSE() throws SQLException {
        List<Compte> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM compte WHERE statut = 'encours'"; // Ajout des guillemets autour de encours
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Compte(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("confirmation_email"),
                        rs.getInt("cin"),
                        rs.getDate("date_delivrance_cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("sexe"),
                        rs.getDate("date_naissance"),
                        rs.getString("proffesion"), // Correction de la variable proffesion à profession
                        rs.getString("type_compte"),
                        rs.getDouble("montant"),
                        rs.getString("statut_marital"),
                        rs.getString("nationalite"),
                        rs.getInt("numero_telephone"), // Ajout du numéro de téléphone
                        rs.getString("type_cin"),
                        rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

}