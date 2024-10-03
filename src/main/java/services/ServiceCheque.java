package services;

import controllers.Cheque.AjouterChequeCard;
import utils.MyDatabase;
import Entities.Cheque;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCheque implements  IServiceCheque <Cheque> {
    private Connection connection;
    public Statement statement;

    public ServiceCheque() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public boolean ajouter(Cheque cheque) throws SQLException {
        String req = "INSERT INTO cheque "
                + "(compte_id, user_id, beneficiaire, montant, telephone, email, cin, nom_prenom, date, decision, photo_cin, signature_id, document_id ,signer_id, pdf_sans_signature) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, cheque.getCompte_id());
            ps.setInt(2, cheque.getUser_id());
            ps.setString(3, cheque.getBeneficiaire());
            ps.setDouble(4, cheque.getMontant());
            ps.setInt(5, cheque.getTelephone());
            ps.setString(6, cheque.getEmail());
            ps.setInt(7, cheque.getCin());
            ps.setString(8, cheque.getNom_prenom());
            ps.setObject(9, cheque.getDate());
            ps.setString(10, cheque.getDecision());
            ps.setString(11, cheque.getPhoto_cin());
            ps.setString(12, cheque.getSignature_id());
            ps.setString(13, cheque.getDocument_id());
            ps.setString(14, cheque.getSigner_id());
            ps.setString(15, cheque.getpdf_sans_signature());

            ps.executeUpdate();
            System.out.println("Chèque ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
    public boolean ajouterS(Cheque cheque) throws SQLException {
        //   INSERT INTO cheque (id, compte_id, user_id, beneficiaire, montant, telephone, email, cin, nom_prenom, date, decision, photo_cin, signature_id, document_id, signer_id, pdf_sans_signature) VALUES (NULL, NULL, NULL, 'jbjqjs', '2222', '222222', 'Ddjdnjdn', '222222', 'djdjdd', '2024-04-23', 'dedeeed', 'eeee', NULL, NULL, NULL, NULL);
        String reqe = "INSERT INTO cheque";


        String req = "INSERT INTO cheque "
                + "(beneficiaire, montant, email, cin, nom_prenom, date, decision, photo_cin,compte_id,user_id,telephone,rib) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, cheque.getBeneficiaire());
            ps.setDouble(2, cheque.getMontant());
            // ps.setInt(3, cheque.getTelephone());
            ps.setString(3, cheque.getEmail());
            ps.setInt(4, cheque.getCin());
            ps.setString(5, cheque.getNom_prenom());
            ps.setDate(6, cheque.getDate());
            ps.setString(7, cheque.getDecision());
            ps.setString(8, cheque.getPhoto_cin());
            ps.setInt(9,cheque.getCompte_id());
            ps.setInt(10,cheque.getUser_id());
            ps.setInt(11,cheque.getTelephone());
           // AjouterChequeCard ajouterChequeCard = new AjouterChequeCard();
            ps.setBigDecimal(12, new BigDecimal(cheque.getRib().toString())); // Convertir BigInteger en BigDecimal
            ps.executeUpdate();
            System.out.println("Chèque ajouté avec succès !");
            System.out.println(cheque.getId());
            System.out.println(cheque);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void modifier(Cheque cheque ) throws SQLException {
        try {
            String req = "UPDATE cheque SET  beneficiaire=?, montant=?, telephone=?, email=?, cin=?, nom_prenom=?, date=?, decision=?, photo_cin=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, cheque.getBeneficiaire());
            ps.setDouble(2, cheque.getMontant());
            ps.setInt(3, cheque.getTelephone());
            ps.setString(4, cheque.getEmail());
            ps.setInt(5, cheque.getCin());
            ps.setString(6, cheque.getNom_prenom());
            ps.setObject(7, cheque.getDate());
            ps.setString(8, cheque.getDecision());
            ps.setString(9, cheque.getPhoto_cin());
            ps.setInt(10, cheque.getId());
            ps.executeUpdate();
            System.out.println("Chèque avec ID " + cheque.getId() + " modifié !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM cheque WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Chèque avec ID " + id + " supprimé !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public  List<Cheque> afficher() throws SQLException {
        List<Cheque> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM cheque";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Cheque(rs.getInt("id"), rs.getInt("compte_id"),
                        rs.getInt("user_id"), rs.getString("beneficiaire"),
                        rs.getDouble("montant"), rs.getInt("telephone"),
                        rs.getString("email"), rs.getInt("cin"),
                        rs.getString("nom_prenom"), rs.getObject("date", Date.class),
                        rs.getString("decision"), rs.getString("photo_cin"),
                        rs.getString("signature_id"), rs.getString("signer_id"),
                        rs.getString("document_id"), rs.getString("pdf_sans_signature"),rs.getBigDecimal("rib")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public Cheque getById(int id) throws SQLException {
        Cheque cheque = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Établir une connexion à la base de données
            connection = MyDatabase.getInstance().getConnection();

            // Préparer la requête SQL
            String query = "SELECT * FROM cheque WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            // Exécuter la requête
            resultSet = statement.executeQuery();

            // Parcourir les résultats
            if (resultSet.next()) {
                // Construire un objet Cheque à partir des données de la base de données
                cheque = new Cheque(
                        resultSet.getInt("id"),
                        resultSet.getInt("compte_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("beneficiaire"),
                        resultSet.getDouble("montant"),
                        resultSet.getInt("telephone"),
                        resultSet.getString("email"),
                        resultSet.getInt("cin"),
                        resultSet.getString("nom_prenom"),
                        resultSet.getDate("date"),
                        resultSet.getString("decision"),
                        resultSet.getString("photo_cin"),
                        resultSet.getString("signature_id"),
                        resultSet.getString("document_id"),
                        resultSet.getString("signer_id"),
                        resultSet.getString("pdf_sans_signature"),
                        resultSet.getBigDecimal("rib")
                );
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return cheque;
    }


    public List<Cheque> searchCheque(String searchTerm) throws SQLException {
        List<Cheque> cheques = new ArrayList<>();

        String sql = "SELECT * FROM cheque WHERE ";
        // Construct the WHERE clause to search across all attributes
        sql += "beneficiaire LIKE ? OR ";
        sql += "montant LIKE ? OR ";
        sql += "telephone LIKE ? OR ";
        sql += "email LIKE ? OR ";
        sql += "cin LIKE ? OR ";
        sql += "nom_prenom LIKE ? OR ";
        sql += "date LIKE ?"; // Remove the extra OR

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the search term for each attribute in the WHERE clause
            for (int i = 1; i <= 7; i++) {
                preparedStatement.setString(i, "%" + searchTerm + "%");
            }

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cheque cheque = new Cheque();
                cheque.setId(rs.getInt("id")); // Assuming id is present in the Cheque object
                cheque.setMontant(rs.getInt("montant"));
                cheque.setBeneficiaire(rs.getString("beneficiaire"));
                cheque.setEmail(rs.getString("email")); // Corrected case for "email"
                cheque.setDate(rs.getDate("date"));
                cheque.setTelephone(rs.getInt("telephone"));
                cheque.setNom_prenom(rs.getString("nom_prenom"));
                cheque.setCin(rs.getInt("cin"));

                cheques.add(cheque);
            }
        } catch (SQLException ex) {
            System.out.println("Error while searching for cheques: " + ex.getMessage());
        }

        return cheques;
    }

}
