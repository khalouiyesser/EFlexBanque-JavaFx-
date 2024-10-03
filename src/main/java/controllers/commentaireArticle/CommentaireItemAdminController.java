package controllers.commentaireArticle;

import Entities.actualites.CommentaireHadhemi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceCommentaireHadhemi;

import java.io.IOException;
import java.sql.SQLException;

public class CommentaireItemAdminController{

    @FXML
    private Text contComment;
    @FXML
    private Text nomusercom;
    @FXML
    private Text dateartitemDateText;


    @FXML
    private Text datecom;

    @FXML
    private ImageView deleteComment;

    @FXML
    private Text titreartcom;
    @FXML
    void deleteComment(MouseEvent event) {


    }



    public void initData(CommentaireHadhemi commentaireHadhemi) {
        ServiceCommentaireHadhemi serviceCommentaireHadhemi = new ServiceCommentaireHadhemi();
        contComment.setText(commentaireHadhemi.getContenu());
        datecom.setText(String.valueOf(commentaireHadhemi.getDate_creation()));
        nomusercom.setText(commentaireHadhemi.getNom_aut_com());
        titreartcom.setText(commentaireHadhemi.getArticle().getTitre_art());
        dateartitemDateText.setText(String.valueOf(commentaireHadhemi.getArticle().getDate_pub_art()));
deleteComment.setId(String.valueOf(commentaireHadhemi.getId()));
        deleteComment.setOnMouseClicked(event -> {
            System.out.println("ID du commentaire à supprimer : " + commentaireHadhemi.getId());
            try {
                serviceCommentaireHadhemi.supprimer(commentaireHadhemi);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/commentaire/listCommentAdmin.fxml"));
            try {
                Parent root = loader.load();

                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        }
    }