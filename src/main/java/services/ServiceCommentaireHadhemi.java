package services;

import Entities.actualites.Article;
import Entities.actualites.CommentaireHadhemi;
import utils.MyDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommentaireHadhemi  implements IServiceCommentaireHadhemi<CommentaireHadhemi> {

    private Connection connection;

    public ServiceCommentaireHadhemi() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(CommentaireHadhemi commentaire) throws SQLException {
        String req = "INSERT INTO commentaire_hadhemi (contenu, date_creation, nom_aut_com, article_id, image_u, `like`) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, commentaire.getContenu());
            preparedStatement.setObject(2, commentaire.getDate_creation());
            preparedStatement.setString(3, commentaire.getNom_aut_com());
            preparedStatement.setInt(4, commentaire.getArticle_id());
            preparedStatement.setString(5, commentaire.getImage_u());
            preparedStatement.setInt(6, commentaire.getLike());

            preparedStatement.executeUpdate();
            System.out.println("Commentaire ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du commentaire : " + e.getMessage());
        }
    }


    @Override
    public void supprimer(CommentaireHadhemi commentaire) throws SQLException {
        try {
            String req = "DELETE FROM commentaire_hadhemi WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, commentaire.getId());
            preparedStatement.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du commentaire : " + ex.getMessage());
        }
    }

    @Override
    public void modifier(CommentaireHadhemi commentaire) throws SQLException {
        String req = "UPDATE commentaire_hadhemi SET contenu=?, date_creation=?, nom_aut_com=?, image_u=?,like=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, commentaire.getContenu());
            preparedStatement.setObject(2, commentaire.getDate_creation());
            preparedStatement.setString(3, commentaire.getNom_aut_com());
            preparedStatement.setString(4, commentaire.getImage_u());
            preparedStatement.setInt(5, commentaire.getLike());

            preparedStatement.setInt(6, commentaire.getId());
            preparedStatement.executeUpdate();
            System.out.println("Commentaire modifié avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du commentaire : " + ex.getMessage());
        }
    }

//    @Override
//    public List<CommentaireHadhemi> afficher() throws SQLException {
//        List<CommentaireHadhemi> commentaires = new ArrayList<>();
//        String req = "SELECT * FROM commentaire_hadhemi";
//        try (Statement statement = connection.createStatement();
//             ResultSet rs = statement.executeQuery(req)) {
//            while (rs.next()) {
//                CommentaireHadhemi commentaire = new CommentaireHadhemi();
//                commentaire.setId(rs.getInt("id"));
//                commentaire.setContenu(rs.getString("contenu"));
//                commentaire.setDate_creation(rs.getObject("date_creation", LocalDateTime.class));
//                commentaire.setNom_aut_com(rs.getString("nom_aut_com"));
//                commentaire.setArticle_id(rs.getInt("article_id"));
//                commentaire.setImage_u(rs.getString("image_u"));
//                commentaires.add(commentaire);
//            }
//        }
//        return commentaires;
//    }

    @Override
    public List<CommentaireHadhemi> afficher() throws SQLException {
        List<CommentaireHadhemi> commentaires = new ArrayList<>();
        String req = "SELECT c.*, a.* " +
                "FROM commentaire_hadhemi c " +
                "INNER JOIN article a ON c.article_id = a.id";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(req)) {
            while (rs.next()) {
                CommentaireHadhemi commentaire = new CommentaireHadhemi();
                commentaire.setId(rs.getInt("c.id"));
                commentaire.setContenu(rs.getString("c.contenu"));
                commentaire.setDate_creation(rs.getObject("c.date_creation", LocalDateTime.class));
                commentaire.setNom_aut_com(rs.getString("c.nom_aut_com"));
                commentaire.setArticle_id(rs.getInt("c.article_id"));
                commentaire.setImage_u(rs.getString("c.image_u"));
                commentaire.setLike(rs.getInt("like"));
                Article article = new Article();
                LocalDateTime dateArticle = rs.getObject("a.date_pub_art", LocalDateTime.class);
                article.setDate_pub_art(dateArticle);

                article.setId(rs.getInt("a.id"));
                article.setTitre_art(rs.getString("a.titre_art"));
                commentaire.setArticle(article);

                commentaires.add(commentaire);
            }
        }
        return commentaires;
    }

    public void modifierLike(int like, int article) throws SQLException {
        String req = "UPDATE commentaire_hadhemi SET `like`=? WHERE article_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, like);
            preparedStatement.setInt(2, article);
            preparedStatement.executeUpdate();
            System.out.println("Commentaire modifié avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du commentaire : " + ex.getMessage());
        }
    }
    public int countCommentsByArticleId(int articleId) throws SQLException {
        int commentCount = 0;
        String req = "SELECT COUNT(*) AS comment_count FROM commentaire_hadhemi WHERE article_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, articleId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                commentCount = rs.getInt("comment_count");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors du comptage des commentaires : " + ex.getMessage());
        }
        return commentCount;
    }

    public CommentaireHadhemi getCommentaireById(int id) throws SQLException {
        String req = "SELECT * FROM commentaire_hadhemi WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                CommentaireHadhemi commentaireHadhemi = new CommentaireHadhemi(  rs.getInt("id"),
                        rs.getString("contenu"),
                        rs.getObject("date_creation", LocalDateTime.class),
                        rs.getString("nom_aut_com"),
                        rs.getInt("article_id"),
                        rs.getString("image_u"),
                        rs.getInt("like"));
            }

        }

        throw new SQLException("Commentaire non trouvé avec l'ID : " + id);
    }

    public CommentaireHadhemi getCommentaireArtById(int id) throws SQLException {
        String req = "SELECT * FROM commentaire_hadhemi WHERE article_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new CommentaireHadhemi(
                        rs.getInt("id"),
                        rs.getString("contenu"),
                        rs.getObject("date_creation", LocalDateTime.class),
                        rs.getString("nom_aut_com"),
                        rs.getInt("article_id"),
                        rs.getString("image_u"),
                        rs.getInt("like")
                );
            }
        }
        throw new SQLException("Commentaire non trouvé avec l'ID : " + id);
    }



    public List<CommentaireHadhemi> afficherById(int idAuto) throws SQLException {
        List<CommentaireHadhemi> commentaires = new ArrayList<>();
        String req = "SELECT c.*, a.* " +
                "FROM commentaire_hadhemi c " +
                "INNER JOIN article a ON c.article_id = a.id " +
                "WHERE c.article_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, idAuto);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    CommentaireHadhemi commentaire = new CommentaireHadhemi();
                    commentaire.setId(rs.getInt("c.id"));
                    commentaire.setContenu(rs.getString("c.contenu"));
                    commentaire.setDate_creation(rs.getObject("c.date_creation", LocalDateTime.class));
                    commentaire.setNom_aut_com(rs.getString("c.nom_aut_com"));
                    commentaire.setArticle_id(rs.getInt("c.article_id"));
                    commentaire.setImage_u(rs.getString("c.image_u"));
                    commentaire.setLike(rs.getInt("like"));
                    Article article = new Article();
                    LocalDateTime dateArticle = rs.getObject("a.date_pub_art", LocalDateTime.class);
                    article.setDate_pub_art(dateArticle);
                    article.setId(rs.getInt("a.id"));
                    article.setTitre_art(rs.getString("a.titre_art"));

                    commentaire.setArticle(article);
                    commentaires.add(commentaire);
                }
            }
        }
        return commentaires;
    }


    private CommentaireHadhemi mapResultSetToCommentaire(ResultSet rs) throws SQLException {
        CommentaireHadhemi  CommentaireHadhemi = new CommentaireHadhemi();
        return new CommentaireHadhemi(
                rs.getInt("id"),
                rs.getString("contenu"),
                rs.getObject("date_creation", LocalDateTime.class),
                rs.getString("nom_aut_com"),
                rs.getInt("article_id"),
                rs.getString("image_u"),
                rs.getInt("like")
        );
    }



//    public List<CommentaireHadhemi> getCommentairesByUserId(int userId) throws SQLException {
//        List<CommentaireHadhemi> commentaires = new ArrayList<>();
//
//        // Établir une connexion à la base de données
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_base_de_donnees", "utilisateur", "mot_de_passe");
//
//        // Préparer la requête SQL pour récupérer les commentaires de l'utilisateur donné
//        String query = "SELECT * FROM commentaires WHERE user_id = ?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        statement.setInt(1, userId);
//
//        // Exécuter la requête et récupérer les résultats
//        ResultSet resultSet = statement.executeQuery();
//
//        // Parcourir les résultats et créer des objets Commentaire
//        while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String contenu = resultSet.getString("contenu");
//            // Récupérer d'autres champs si nécessaire
//
//            // Créer un objet Commentaire et l'ajouter à la liste
//            CommentaireHadhemi commentaire = new CommentaireHadhemi();
//            commentaires.add(commentaire);
//        }
//
//        // Fermer les ressources
//        resultSet.close();
//        statement.close();
//        connection.close();
//
//        return commentaires;
//    }


}
