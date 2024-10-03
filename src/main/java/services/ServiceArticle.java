package services;

import Entities.actualites.Article;
import utils.MyDatabase;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceArticle  implements IServiceArticle<Article> {
    Connection connection;

    public ServiceArticle() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public boolean ajouter(Article article) throws SQLException {
        String req = "INSERT INTO article (nom_aut_art, adr_aut_art, date_pub_art, duree_art, categorie_art, titre_art, contenu_art, piecejointe_art, image_art,likes ,dislikes,qrCode) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? ,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, article.getNom_aut_art());
            preparedStatement.setString(2, article.getAdr_aut_art());
            preparedStatement.setObject(3, article.getDate_pub_art());
            preparedStatement.setInt(4, article.getDuree_art());
            preparedStatement.setString(5, article.getCategorie_art());
            preparedStatement.setString(6, article.getTitre_art());
            preparedStatement.setString(7, article.getContenu_art());
            preparedStatement.setString(8, article.getPiecejointe_art());
            preparedStatement.setString(9, article.getImage_art());
            preparedStatement.setInt(10, article.getLikes());
            preparedStatement.setInt(11, article.getDislikes());
            preparedStatement.setString(12, article.getQrCode());



            preparedStatement.executeUpdate();
            System.out.println("Article ajouté");
            return true ;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'article : " + e.getMessage());
        }
        return false;
    }
//@Override
//public void ajouter(Article article) throws SQLException {
//    String req = "INSERT INTO article ( date_pub_art, duree_art, categorie_art, titre_art, contenu_art) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? ,?)";
//    try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
//
//        preparedStatement.setObject(3, article.getDate_pub_art());
//        preparedStatement.setInt(4, article.getDuree_art());
//        preparedStatement.setString(5, article.getCategorie_art());
//        preparedStatement.setString(6, article.getTitre_art());
//        preparedStatement.setString(7, article.getContenu_art());
//
//
//        preparedStatement.executeUpdate();
//        System.out.println("Article ajouté");
//    } catch (SQLException e) {
//        System.out.println("Erreur lors de l'ajout de l'article : " + e.getMessage());
//    }
//}

    @Override
    public void supprimer(Article article) throws SQLException {
        try {
            String req = "DELETE FROM article WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, article.getId());
            preparedStatement.executeUpdate();
            System.out.println("Article supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de l'article : " + ex.getMessage());
        }
    }

    @Override
    public void modifier(Article article) throws SQLException {
        String req = "UPDATE article SET nom_aut_art=?, adr_aut_art=?, date_pub_art=?, duree_art=?, categorie_art=?, titre_art=?, contenu_art=?, piecejointe_art=?, image_art=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, article.getNom_aut_art());
            preparedStatement.setString(2, article.getAdr_aut_art());
            preparedStatement.setObject(3, article.getDate_pub_art());
            preparedStatement.setInt(4, article.getDuree_art());
            preparedStatement.setString(5, article.getCategorie_art());
            preparedStatement.setString(6, article.getTitre_art());
            preparedStatement.setString(7, article.getContenu_art());
            preparedStatement.setString(8, article.getPiecejointe_art());
            preparedStatement.setString(9, article.getImage_art());
            preparedStatement.setInt(10, article.getId());

            preparedStatement.executeUpdate();
            System.out.println("Article modifié avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de l'article : " + ex.getMessage());
        }
    }

    public Article getOneProject(int id) throws SQLException {
        String req = "SELECT * FROM `article` where id = ?";
        PreparedStatement ps = MyDatabase.getInstance().getConnection().prepareStatement(req);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Article article = new Article();
        article.setId(-999);

        while (rs.next()) {
            article.setId(rs.getInt("id"));
            article.setNom_aut_art(rs.getString("nom_aut_art"));
            article.setAdr_aut_art(rs.getString("adr_aut_art"));
            article.setDate_pub_art(rs.getTimestamp("date_pub_art").toLocalDateTime());
            article.setDuree_art(rs.getInt("duree_art"));
            article.setCategorie_art(rs.getString("categorie_art"));
            article.setTitre_art(rs.getString("titre_art"));
            article.setContenu_art(rs.getString("contenu_art"));
            article.setPiecejointe_art(rs.getString("piecejointe_art"));
            article.setImage_art(rs.getString("image_art"));

        }
        ps.close();
        return article;
    }


    @Override
    public List<Article> afficher() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String req = "SELECT * FROM article";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(req)) {
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setNom_aut_art(rs.getString("nom_aut_art"));
                article.setAdr_aut_art(rs.getString("adr_aut_art"));
                article.setDate_pub_art(rs.getTimestamp("date_pub_art").toLocalDateTime());
                article.setDuree_art(rs.getInt("duree_art"));
                article.setCategorie_art(rs.getString("categorie_art"));
                article.setTitre_art(rs.getString("titre_art"));
                article.setContenu_art(rs.getString("contenu_art"));
                article.setPiecejointe_art(rs.getString("piecejointe_art"));
                article.setImage_art(rs.getString("image_art"));
                article.setQrCode(rs.getString("qrCode"));

                articles.add(article);
            }
        }
        return articles;
    }
    public Article getArticleById(int id) throws SQLException {
        String req = "SELECT * FROM article WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return mapResultSetToArticle(rs);
            }
        }
        throw new SQLException("Article non trouvé avec l'ID : " + id);
    }

    private Article mapResultSetToArticle(ResultSet rs) throws SQLException {
        return new Article(
                rs.getInt("id"),
                rs.getString("nom_aut_art"),
                rs.getString("adr_aut_art"),
                rs.getTimestamp("date_pub_art").toLocalDateTime(),
                rs.getInt("duree_art"),
                rs.getString("categorie_art"),
                rs.getString("titre_art"),
                rs.getString("contenu_art"),
                rs.getString("piecejointe_art"),
                rs.getString("image_art"),
                rs.getInt("likes"),
                rs.getInt("dislikes")
//                rs.getString("qrCode")

        );
    }
    public List<Article> getAllArticles() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM article"; // Assurez-vous que "article" est le nom de votre table d'articles dans la base de données
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Article article = new Article();
                    article.setId(resultSet.getInt("id"));
                    article.setTitre_art(resultSet.getString("titre_art"));
                    article.setContenu_art(resultSet.getString("contenu_art"));
                    article.setDate_pub_art(resultSet.getTimestamp("date_pub_art").toLocalDateTime());
                    // Assurez-vous que la colonne "date_pub_art" dans votre table de base de données est de type TIMESTAMP
                    // Convertissez-le en LocalDateTime en utilisant toLocalDateTime()
                    // Ajoutez d'autres attributs d'article de la même manière si nécessaire
                }
            }
        }
        return articles;
    }



    public List<Article> search(String searchTerm) throws SQLException {
        // Liste pour stocker les articles correspondants à la recherche
        List<Article> matchingArticles = new ArrayList<>();

        // Récupérer la liste complète des articles
        List<Article> allArticles = getAllArticles(); // Assurez-vous d'avoir une méthode getAllArticles() définie

        // Filtrer les articles dont le titre commence par le terme de recherche
        matchingArticles = allArticles.stream()
                .filter(article -> article.getTitre_art().startsWith(searchTerm))
                .collect(Collectors.toList());

        // Renvoyer la liste des articles correspondants à la recherche
        return matchingArticles;
    }


    public List<Article> searchArticles(String searchTerm) throws SQLException {
        List<Article> articles = new ArrayList<>();

        String sql = "SELECT * FROM article WHERE ";
        // Construct the WHERE clause to search across all attributes
        sql += "nom_aut_art LIKE ? OR ";
        sql += "adr_aut_art LIKE ? OR ";
        sql += "date_pub_art LIKE ? OR ";
        sql += "duree_art LIKE ? OR ";
        sql += "categorie_art LIKE ? OR ";
        sql += "titre_art LIKE ? OR ";
        sql += "contenu_art LIKE ? OR ";
        sql += "piecejointe_art LIKE ? OR ";
        sql += "image_art LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the search term for each attribute in the WHERE clause
            for (int i = 1; i <= 9; i++) {
                preparedStatement.setString(i, "%" + searchTerm + "%");
            }

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setNom_aut_art(rs.getString("nom_aut_art"));
                article.setAdr_aut_art(rs.getString("adr_aut_art"));
                article.setDate_pub_art(rs.getTimestamp("date_pub_art").toLocalDateTime());
                article.setDuree_art(rs.getInt("duree_art"));
                article.setCategorie_art(rs.getString("categorie_art"));
                article.setTitre_art(rs.getString("titre_art"));
                article.setContenu_art(rs.getString("contenu_art"));
                article.setPiecejointe_art(rs.getString("piecejointe_art"));
                article.setUser_id(rs.getInt("user_id"));
                article.setImage_art(rs.getString("image_art"));
                article.setLikes(rs.getInt("likes"));
                article.setDislikes(rs.getInt("dislikes"));
                //article.setTotalReactions(rs.getInt("totalReactions"));
                // article.setNbComments(rs.getInt("nbComments"));
                //article.setNbShares(rs.getInt("nbShares"));

                articles.add(article);
            }
        } catch (SQLException ex) {
            System.out.println("Error while searching for article: " + ex.getMessage());
        }

        return articles;
    }
    public void incrementLikes(Article article) throws SQLException {
        article.setLikes(article.getLikes() + 1);
        modifier(article); // Mettre à jour la base de données
    }

    public void decrementLikes(Article article) throws SQLException {
        article.setLikes(article.getLikes() - 1);
        modifier(article); // Mettre à jour la base de données
    }

    public void incrementDislikes(Article article) throws SQLException {
        article.setDislikes(article.getDislikes() + 1);
        modifier(article); // Mettre à jour la base de données
    }

    public void decrementDislikes(Article article) throws SQLException {
        article.setDislikes(article.getDislikes() - 1);
        modifier(article); // Mettre à jour la base de données
    }
}

