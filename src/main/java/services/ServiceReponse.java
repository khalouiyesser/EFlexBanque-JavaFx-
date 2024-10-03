package services;

import Entities.actualites.Reclamation;
import Entities.actualites.Reponse;
import utils.MyDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ServiceReponse implements IServiceReponse <Reponse> {
    Connection connection ;

    public ServiceReponse() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Reponse reponse) throws SQLException {
        String req = "INSERT INTO reponse (reclamation_id, adr_rep, date_rep, contenu_rep, piece_jrep) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, reponse.getReclamation_id());
            preparedStatement.setString(2, reponse.getAdr_rep());
            preparedStatement.setObject(3, reponse.getDate_rep());
            preparedStatement.setString(4, reponse.getContenu_rep());
            preparedStatement.setString(5, reponse.getPiece_jrep());

            preparedStatement.executeUpdate();
            System.out.println("Réponse ajoutée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réponse : " + e.getMessage());
        }

    }

    @Override
    public void supprimer(Reponse reponse) throws SQLException {
        try {
            String req = "DELETE FROM reponse WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, reponse.getId());
            preparedStatement.executeUpdate();
            System.out.println("Réponse supprimée");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de la réponse : " + ex.getMessage());

        }
    }
    @Override
    public void modifier(Reponse reponse) throws SQLException {
        String req = "UPDATE reponse SET contenu_rep=?, adr_rep=?, date_rep=?, piece_jrep=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, reponse.getContenu_rep());
            preparedStatement.setString(2, reponse.getAdr_rep());
            preparedStatement.setObject(3, reponse.getDate_rep());
            preparedStatement.setString(4, reponse.getPiece_jrep());
            preparedStatement.setInt(5, reponse.getId());
            preparedStatement.executeUpdate();
            System.out.println("Réponse modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de la réponse : " + ex.getMessage());
        }
    }

    @Override
//
    public List<Reponse> afficher() throws SQLException {
        List<Reponse> reponses = new ArrayList<>();
        String req = "SELECT r.*, re.* FROM reponse r JOIN reclamation re ON r.reclamation_id = re.id";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(req)) {
            while (rs.next()) {
                Reponse reponse = new Reponse();
                reponse.setReclamation_id(rs.getInt("reclamation_id"));
                reponse.setAdr_rep(rs.getString("adr_rep"));
                reponse.setDate_rep(rs.getTimestamp("date_rep").toLocalDateTime());
                reponse.setContenu_rep(rs.getString("contenu_rep"));
                reponse.setPiece_jrep(rs.getString("piece_jrep"));
                reponse.setId(rs.getInt("id"));

                Reclamation reclamation = new Reclamation();
                reclamation.setId(rs.getInt("id"));
                reponse.setDate_rep(rs.getTimestamp("date_rep").toLocalDateTime());
                LocalDateTime dateRec= rs.getObject("re.date_rec", LocalDateTime.class);
                reclamation.setDate_rec(dateRec);
                reclamation.setAdr_rec(rs.getString("adr_rec"));


                // Récupérer d'autres colonnes de la table reclamation si nécessaire
                reponse.setReclamation(reclamation);
                reponses.add(reponse);
            }
        }
        return reponses;
    }
    public Reponse getOneProject(int id) throws SQLException {
        String req = "SELECT * FROM `reponse` where id = ?";
        PreparedStatement ps = MyDatabase.getInstance().getConnection().prepareStatement(req);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Reponse reponse= new Reponse();
        reponse.setId(-999);

        while (rs.next()) {
            reponse.setId(rs.getInt("id"));
            reponse.setContenu_rep(rs.getString("contenu_rep"));
            reponse.setPiece_jrep(rs.getString("piece_jrep"));
            reponse.setDate_rep(rs.getTimestamp("date_rep").toLocalDateTime());
            reponse.setAdr_rep(rs.getString("adr_rep"));

        }
        ps.close();
        return reponse;
    }

    public Reponse getReponseById(int id) throws SQLException {
        String req = "SELECT * FROM reponse WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return mapResultSetToReponse(rs);
            }
        }
        throw new SQLException("Reponse non trouvée avec l'ID : " + id);
    }

    private Reponse mapResultSetToReponse(ResultSet rs) throws SQLException {
        return new Reponse(
                rs.getInt("id"),
                rs.getInt("reclamation_id"),
                rs.getInt("user_id"),
                rs.getString("adr_rep"),
                rs.getTimestamp("date_rep").toLocalDateTime(),
                rs.getString("contenu_rep"),
                rs.getString("piece_jrep")
        );
    }
    @Override
    public void supprimerParId(int id) throws SQLException {
        String query = "DELETE FROM reponse WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<Reponse> afficherReponsesParIdReclamation(int idReclamation) {
        List<Reponse> reponses = new ArrayList<>();
        String req = "SELECT * FROM reponse WHERE reclamation_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, idReclamation);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reponse reponse = mapResultSetToReponse(rs);
                reponses.add(reponse);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des réponses pour la réclamation avec l'ID : " + idReclamation);
            ex.printStackTrace();
        }
        return reponses;
    }

}

