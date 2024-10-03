package services;

import Entities.OffreDeStage;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceOffreDeStage implements IServiceYesser<OffreDeStage> {
    private Connection connection;
    public ServiceOffreDeStage() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(OffreDeStage offreDeStage) throws SQLException {
//        INSERT INTO `offre_stage` (`id`, `user_id`, `title`, `domaine`, `type_offre`, `poste_propose`, `experience`, `niveau`, `language`, `description`, `exigence_offre`, `date_postu`, `mots_cles`, `pfe_book`) VALUES (NULL, '3', 'nbnb', 'bb', 'vv', '3', NULL, NULL, '[\"shsh\"]', 'bvvb', 'b b', NULL, NULL, NULL);
        // String a = "[\"" + offreDeStage.getExigenceOffre()+"\"]"+"'";

        String req = "insert into offre_stage " +
                "(title,domaine,type_offre,poste_propose,experience,description,exigence_offre)" +
                "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,offreDeStage.getTitle());
            ps.setString(2,offreDeStage.getDomaine());
            ps.setString(3,offreDeStage.getTypeOffre());
            ps.setInt(4,offreDeStage.getPostePropose());
            ps.setString(5,offreDeStage.getExperience());

//            ps.setString(6,offreDeStage.getLanguage());
            ps.setString(6,offreDeStage.getDescription());
            ps.setString(7,offreDeStage.getExigenceOffre());
            ps.executeUpdate();
            System.out.println("ajouté");

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        String y = "yesser";
        String a = "[\"" +y+"\"]";
        System.out.println(a);
    }

    @Override
    public void modifier(OffreDeStage offreDeStage) throws SQLException {
        String req = "UPDATE offre_stage SET title=?, domaine=?, type_offre=?, poste_propose=?, experience=?, description=?, exigence_offre=?, date_postu=? WHERE id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,offreDeStage.getTitle());
            ps.setString(2,offreDeStage.getDomaine());
            ps.setString(3,offreDeStage.getTypeOffre());
            ps.setInt(4,offreDeStage.getPostePropose());
            ps.setString(5,offreDeStage.getExperience());

//            ps.setString(6,offreDeStage.getLanguage());
            ps.setString(6,offreDeStage.getDescription());
            ps.setString(7,offreDeStage.getExigenceOffre());
//            ps.setString(8,offreDeStage.getPfeBook());
            ps.setDate(8,offreDeStage.getDatePostu());
            ps.setInt(9,offreDeStage.getId());
            ps.executeUpdate();
            System.out.println("Update avec succès");

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "delete from offre_stage where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Offfre Stage avec ID "+ id + "est supprimer");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<OffreDeStage> afficher() throws SQLException {
        List<OffreDeStage> list = new ArrayList<>();
        try {
            String req = "select * from offre_stage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                list.add(new OffreDeStage(rs.getInt("id"),rs.getString("title"),rs.getString("domaine"), rs.getString("type_offre"),rs.getString("experience"),rs.getString("description"),rs.getString("exigence_offre"),rs.getDate("date_postu")));
            }
        }catch (SQLException e){
            System.err.println("e.getMessage()");
        }
        return list;
    }

    public List<OffreDeStage> afficherId() throws SQLException {
        List<OffreDeStage> list = new ArrayList<>();
        try {
            String req = "select * from offre_stage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                list.add(new OffreDeStage(rs.getInt("id"),rs.getString("title"),rs.getString("domaine"), rs.getString("type_offre"),rs.getString("experience"),rs.getString("description"),rs.getString("exigence_offre"),rs.getDate("date_postu")));
            }

        }catch (SQLException e){
            System.err.println("e.getMessage()");
        }
        return list;
    }


    @Override
    public OffreDeStage afficheUne(int id) throws SQLException {
        OffreDeStage stage = null; // Initialiser à null
        try {
            String req = "select * from offre_stage where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); // Utiliser executeQuery() pour une requête SELECT
            while (rs.next()) {
                // Créer une nouvelle instance d'OffreDeStage avec les données de la base de données
                stage = new OffreDeStage(rs.getInt("id"), rs.getString("title"), rs.getString("domaine"),
                        rs.getString("type_offre"), rs.getString("experience"), rs.getString("description"),
                        rs.getString("exigence_offre"), rs.getDate("date_postu"));
//                System.out.println(stage.getPostePropose());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); // Afficher l'exception réelle
        }
        return stage;
    }

//    public static void main(String[] args) throws SQLException {

//    }
}
