package services;

import Entities.Commentaire;
import Entities.Evenement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import utils.DataSource;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommentaire implements IService<Commentaire> {

    @Override
    public void ajouter(Commentaire commentaire )throws SQLException, IOException {
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement("INSERT INTO commentaire (contenu, date_creation, nomuser, img,evenement_id) VALUES (?, ?, ?, ?, ?)")) {
            String content =commentaire.getContenu();
            //API
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, content);
            Request request = new Request.Builder()
                    .url("https://api.apilayer.com/bad_words?censor_character=censor_character")
                    .addHeader("apikey", "jmn9xg2r5WsDmygEIDjW5lwFUrhSpplW")
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            String censoredContent = jsonObject.get("censored_content").getAsString();
            System.out.println(censoredContent);
            preparedStatement.setString(1, censoredContent);
            preparedStatement.setString(2, commentaire.getDate());
            preparedStatement.setString(3, commentaire.getNomuser());
            preparedStatement.setString(4, "ayoub.jpg");
            preparedStatement.setInt(5, commentaire.getEvenement_id());
            preparedStatement.executeUpdate();
            System.out.println("Commentarie ajouté");
        }
    }
    @Override
    public void ajouter1(Commentaire commentaire, int projectId) throws SQLException {
    }
    @Override
    public List<Evenement> sortEvent(int value, int idCategory) {
        return null;
    }

    @Override
    public void modifier(Commentaire commentaire) throws SQLException {
        String query = "UPDATE commentaire SET contenu=?, date_creation=?, nomuser=?, img=?, likes=?, dislikes=?  WHERE id=?";
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setString(1, commentaire.getContenu());
            preparedStatement.setString(2, commentaire.getDate());
            preparedStatement.setString(3, commentaire.getNomuser());
            preparedStatement.setString(4, commentaire.getImg());
            preparedStatement.setInt(5, commentaire.getLikes()+1);
            preparedStatement.setInt(6, commentaire.getDislikes());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void modifierlike(Commentaire commentaire) throws SQLException {
        String query = "UPDATE commentaire SET likes = likes + 1 WHERE id=?";
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setInt(1, commentaire.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void modifierdislike(Commentaire commentaire) throws SQLException {
        String query = "UPDATE commentaire SET dislikes = dislikes + 1 WHERE id=?";
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setInt(1, commentaire.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Evenement getOneEvenement(int idEvenement) throws SQLException {
        return null;
    }

    @Override
    public void AddEvenenemtOffer(Evenement evenement) {

    }


    @Override
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM commentaire WHERE id=?";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("commentaire supprimée avec succès");
        }
    }

    @Override
    public List<Commentaire> afficher() throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM commentaire";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setId(resultSet.getInt("id"));
                commentaire.setContenu(resultSet.getString("contenu"));
                commentaire.setDate(resultSet.getString("date_creation"));
                commentaire.setNomuser(resultSet.getString("nomuser"));
                commentaire.setImg(resultSet.getString("img"));
                commentaire.setEvenement_id(resultSet.getInt("evenement_id"));
                commentaire.setDislikes(resultSet.getInt("dislikes"));
                commentaire.setLikes(resultSet.getInt("likes"));
                commentaires.add(commentaire);
            }
        }
        return commentaires;
    }


    @Override
    public List<Commentaire> show(int evenement_id) throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM commentaire WHERE evenement_id = ?";
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setInt(1, evenement_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Commentaire commentaire = new Commentaire();
                    commentaire.setId(resultSet.getInt("id"));
                    commentaire.setContenu(resultSet.getString("contenu"));
                    commentaire.setDate(resultSet.getString("date_creation"));
                    commentaire.setNomuser(resultSet.getString("nomuser"));
                    commentaire.setImg(resultSet.getString("img"));
                    commentaire.setEvenement_id(resultSet.getInt("evenement_id"));
                    commentaire.setDislikes(resultSet.getInt("dislikes"));
                    commentaire.setLikes(resultSet.getInt("likes"));
                    commentaires.add(commentaire);
                }
            }
        }
        return commentaires;
    }

    @Override
    public Commentaire afficher1(int id) throws SQLException {
        return null;
    }
}
