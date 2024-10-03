package services;

import Entities.DemandeStage;
import Entities.OffreDeStage;
import utils.MyDatabase;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDemandeStage implements IServiceYesser<DemandeStage> {
    private Connection connection;
    private String path = "C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory\\";
    public ServiceDemandeStage() {
        connection = MyDatabase.getInstance().getConnection();
    }

    public void ajouterParOffre(DemandeStage demandeStage, int id) throws SQLException, IOException, InterruptedException {
//        Connection connection = MyDatabase.getInstance().getConnection();
        AnalyseurCv analyseurCv = new AnalyseurCv();

        System.out.println("id = " + id);
        // Récupérer le chemin complet du CV
        String pathT = path + demandeStage.getCv();
        OffreDeStage  stage = new OffreDeStage();
        stage = afficheUne(id);
        int K = id;
        Symfony symfony = new Symfony();
        int score = symfony.score(id,demandeStage.getCv());
        System.out.println("score f service " + score);
        DemandeStage demandeStag = new DemandeStage(K,demandeStage.getNom(), demandeStage.getPrenom(), demandeStage.getEmail(), demandeStage.getNumeroTelephone(), demandeStage.getLettremotivation(), demandeStage.getCv(), demandeStage.getDomaine(), demandeStage.getEtat(), demandeStage.getDate(), score);
        DemandeStage demandeStageParOffre = new DemandeStage(K,demandeStage.getNom(),demandeStage.getPrenom(),demandeStage.getEmail(), demandeStage.getNumeroTelephone(),demandeStage.getLettremotivation(), demandeStage.getCv(), demandeStage.getDomaine(),demandeStage.getEtat(), demandeStage.getDate(),score);

//        System.out.println(
//                "DemandeStage{" +
////                "id =" +id + '\''+
//                "nom='" + demandeStag.getNom() + '\'' +
//                ", prenom='" + demandeStag.getPrenom() + '\'' +
//                ", email='" + demandeStag.getEmail() + '\'' +
//                ", lettremotivation='" + demandeStag.getLettremotivation() + '\'' +
//                ", cv='" + demandeStag.getCv() + '\'' +
//                ", domaine='" + demandeStag.getDomaine() + '\'' +
//                ", etat='" + demandeStag.getEtat() + '\'' +
//                ", id_offre=" + demandeStag.getId_offre() +
//                ", numeroTelephone=" + demandeStag.getNumeroTelephone() +
//                ", score=" + demandeStag.getScore() +
//                ", date=" + demandeStag.getDate() +
//                '}'
////                demandeStag
//                );
        String req = "INSERT INTO demandestage " +
                "(offre_stage_id,nom, prenom, email, numeroTelephone, lettremotivation, cv, domaine, etat, date, score)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.setString(2, demandeStageParOffre.getNom());
            ps.setString(3, demandeStageParOffre.getPrenom());
            ps.setString(4, demandeStageParOffre.getEmail());
            ps.setInt(5, demandeStageParOffre.getNumeroTelephone());
            ps.setString(6, demandeStageParOffre.getLettremotivation());
            ps.setString(7, demandeStageParOffre.getCv());
            ps.setString(8, demandeStageParOffre.getDomaine());
            ps.setString(9, demandeStageParOffre.getEtat());
            ps.setDate(10, demandeStageParOffre.getDate());
            ps.setInt(11, demandeStageParOffre.getScore()); // Utiliser le score préalablement obtenu
            ps.executeUpdate();
            System.out.println("Ajouté avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
        } finally {
            connection.close();
        }
    }
    public String[] afficheOne(int id) throws SQLException {
        Connection connection = MyDatabase.getInstance().getConnection();
        String reqMot = "SELECT mots_cles FROM offre_stage WHERE id=?";

        ResultSet rs = null;
        List<String> motsClesList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(reqMot);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String motsCles = rs.getString("mots_cles");
                motsClesList.add(motsCles);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close(); // Fermer le ResultSet après utilisation
            }
            connection.close(); // Fermer la connexion après utilisation
        }

        // Convertir la liste de mots clés en tableau de chaînes de caractères
        String[] motsClesArray = motsClesList.toArray(new String[0]);
        return motsClesArray;
    }

//    public ResultSet afficheOne(int id) throws SQLException {
//        Connection connection = MyDatabase.getInstance().getConnection();
//        String reqMot = "SELECT mots_cles FROM offre_stage WHERE id=?";
//
//        ResultSet rs = null;
//        try {
//            PreparedStatement ps = connection.prepareStatement(reqMot);
//            ps.setInt(1, id); // Utiliser l'identifiant passé en argument
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                // Récupérer les valeurs des colonnes de la ligne courante
//                String motsCles = rs.getString("mots_cles");
////                System.out.println("Mots clés: " + motsCles);
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        } finally {
//            connection.close(); // Fermer la connexion après utilisation
//        }
//        return rs;
//    }

    @Override
    public void ajouter(DemandeStage demandeStage) throws SQLException {


        String req = "insert into demandestage " +
                "(nom,prenom,email,numeroTelephone,lettremotivation,cv,domaine,etat,date)" +
                "values(?,?,?,?,?,?,?,?,?)";
        // LocalDateTime dateTime = LocalDateTime.now();

// Définir la date dans l'objet demandeStage
//        demandeStage.setDate(dateTime);
//        demandeStage.setEtat("encours");
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,demandeStage.getNom());
            ps.setString(2,demandeStage.getPrenom());
            ps.setString(3,demandeStage.getEmail());
            ps.setInt(4,demandeStage.getNumeroTelephone());
            ps.setString(5,demandeStage.getLettremotivation());
            ps.setString(6,demandeStage.getCv());
            ps.setString(7,demandeStage.getDomaine());
            ps.setString(8,demandeStage.getEtat());
            ps.setDate(9,demandeStage.getDate());
            ps.executeUpdate();
            System.out.println("ajouté");

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "delete from demandestage where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Demande Stage avec ID "+ id + "est supprimer");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(DemandeStage demandeStage) throws SQLException {
        String req = "update demandestage set nom=?,prenom=?,email=?,numeroTelephone=?,lettremotivation=?,cv=?,domaine=?,etat=? WHERE id=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,demandeStage.getNom());
            ps.setString(2,demandeStage.getPrenom());
            ps.setString(3,demandeStage.getEmail());
            ps.setInt(4,demandeStage.getNumeroTelephone());
            ps.setString(5,demandeStage.getLettremotivation());
            ps.setString(6,demandeStage.getCv());
            ps.setString(7,demandeStage.getDomaine());
            ps.setString(8,demandeStage.getEtat());
            ps.setInt(9,demandeStage.getId());
            ps.executeUpdate();
            System.out.println("modification avec succées");
            System.out.println(demandeStage);

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public List<DemandeStage> afficher() throws SQLException {
        List<DemandeStage> list = new ArrayList<>();
        try {
            String req = "select * from demandestage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                list.add(new DemandeStage(rs.getInt("id"),rs.getInt("offre_stage_id"),rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getInt("numerotelephone"),rs.getString("lettremotivation"),rs.getString("cv"),rs.getString("domaine"),rs.getString("etat"),rs.getDate("date"),rs.getInt("score")));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
//        for (DemandeStage i : list){
//
//            System.out.println("Demande de stage{" +
//                    ", id=" + i.getId() +
//                    "nom='" + i.getNom() + '\'' +
//                    ", prenom='" + i.getPrenom() + '\'' +
//                    ", email='" + i.getEmail() + '\'' +
//                    ", cv='" + i.getCv() + '\'' +
//                    ", domaine='" + i.getDomaine() + '\'' +
//                    ", date='" + i.getScore() + '\'' +
//                    '}');
//        }
        return list;
    }

    public List<DemandeStage> afficherParOffre(int a) throws SQLException {
        List<DemandeStage> list = new ArrayList<>();
        try {
            String req = "SELECT ds.*, os.title FROM demandestage ds INNER JOIN offre_stage os ON ds.offre_stage_id = os.id WHERE ds.offre_stage_id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, a);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new DemandeStage(rs.getInt("id"),rs.getInt("offre_stage_id"),rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getInt("numerotelephone"),rs.getString("lettremotivation"),rs.getString("cv"),rs.getString("domaine"),rs.getString("etat"),rs.getDate("date"),rs.getInt("score")));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public OffreDeStage afficheUne(int id) throws SQLException {
        Connection connection = MyDatabase.getInstance().getConnection();
        String reqMot = "select mots_cles from offre_stage where id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(reqMot);
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Récupérer les valeurs des colonnes de la ligne courante
                String motsCles = rs.getString("mots_cles");
//                System.out.println("Mots clés: " + motsCles);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


}