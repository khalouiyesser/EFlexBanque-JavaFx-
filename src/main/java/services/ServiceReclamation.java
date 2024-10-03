package services;

import Entities.actualites.Reclamation;
import utils.MyDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceReclamation implements IServiceReclamation<Reclamation> {
    Connection connection;

    public ServiceReclamation(){
        connection= MyDatabase.getInstance().getConnection();
    }
//    @Override
//    public void ajouter(Reclamation reclamation) throws SQLException {
//        String req = "INSERT INTO reclamation (objet_rec, contenu_rec, adr_rec, nom_aut_rec, dep_rec, statut_rec, piece_jrec, date_rec) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
//            preparedStatement.setString(1, reclamation.getObjet_rec());
//            preparedStatement.setString(2, reclamation.getContenu_rec());
//            preparedStatement.setString(3, reclamation.getAdr_rec());
//            preparedStatement.setString(4, reclamation.getNom_aut_rec());
//            preparedStatement.setString(5, reclamation.getDep_rec());
//            preparedStatement.setString(6, reclamation.getStatut_rec());
//            preparedStatement.setString(7, reclamation.getPiece_jrec());
//            preparedStatement.setTimestamp(8, Timestamp.valueOf(reclamation.getDate_rec()));
//
//
//            preparedStatement.executeUpdate();
//            System.out.println("Réclamation ajoutée");
//        } catch (SQLException e) {
//            System.out.println("Erreur lors de l'ajout de la réclamation : " + e.getMessage());
//        }
//    }

    public void updateReclamationStatut(int reclamationId, String newStatut) throws SQLException {
        String req = "UPDATE reclamation SET statut_rec = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, newStatut);
            preparedStatement.setInt(2, reclamationId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Statut de la réclamation mis à jour avec succès.");
            } else {
                System.out.println("Aucune réclamation trouvée avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du statut de la réclamation : " + e.getMessage());
        }
    }


    @Override
    public boolean ajouter(Reclamation reclamation) throws SQLException {

        String req = "INSERT INTO reclamation (objet_rec, contenu_rec, adr_rec, nom_aut_rec, dep_rec, statut_rec, piece_jrec, date_rec,user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        LocalDateTime currentDate = LocalDateTime.now();

        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, reclamation.getObjet_rec());
            preparedStatement.setString(2, reclamation.getContenu_rec());
            preparedStatement.setString(3, reclamation.getAdr_rec());
            preparedStatement.setString(4, reclamation.getNom_aut_rec());
            preparedStatement.setString(5, reclamation.getDep_rec());
            preparedStatement.setString(6, reclamation.getStatut_rec());
            preparedStatement.setString(7, reclamation.getPiece_jrec());
            preparedStatement.setTimestamp(8, Timestamp.valueOf(currentDate));
            preparedStatement.setInt(9,reclamation.getUser_id());


            preparedStatement.executeUpdate();
            System.out.println("Réclamation ajoutée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réclamation : " + e.getMessage());
        }
        return false;
    }







    @Override
    public void supprimer(Reclamation reclamation) throws SQLException {
        try {
            String req = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,reclamation.getId());
            preparedStatement.executeUpdate();
            System.out.println("Réclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }




    @Override
    public List<Reclamation> afficher() throws SQLException {
        List<Reclamation> reclamations= new ArrayList<>();
        String req="select * from reclamation";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(req);
        while (rs.next()){
            Reclamation reclamation= new Reclamation();
            reclamation.setId(rs.getInt("id"));
            reclamation.setObjet_rec(rs.getString("objet_rec"));
            reclamation.setContenu_rec(rs.getString("contenu_rec"));
            reclamation.setAdr_rec(rs.getString("adr_rec"));
            reclamation.setNom_aut_rec(rs.getString("nom_aut_rec"));
            reclamation.setDep_rec(rs.getString("dep_rec"));
            reclamation.setStatut_rec(rs.getString("statut_rec"));
            reclamation.setPiece_jrec(rs.getString("piece_jrec"));
            reclamation.setDate_rec(rs.getTimestamp("date_rec").toLocalDateTime());

            reclamations.add(reclamation);
        }
        return reclamations;
    }

    public List<Reclamation> afficherById(int id) throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        connection = MyDatabase.getInstance().getConnection();
        String req = "SELECT * FROM reclamation WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(req);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Reclamation reclamation = new Reclamation();
            reclamation.setId(rs.getInt("id"));
            reclamation.setObjet_rec(rs.getString("objet_rec"));
            reclamation.setContenu_rec(rs.getString("contenu_rec"));
            reclamation.setAdr_rec(rs.getString("adr_rec"));
            reclamation.setNom_aut_rec(rs.getString("nom_aut_rec"));
            reclamation.setDep_rec(rs.getString("dep_rec"));
            reclamation.setStatut_rec(rs.getString("statut_rec"));
            reclamation.setPiece_jrec(rs.getString("piece_jrec"));
            reclamation.setDate_rec(rs.getTimestamp("date_rec").toLocalDateTime());
            reclamations.add(reclamation);
        }
        return reclamations;

    }
}