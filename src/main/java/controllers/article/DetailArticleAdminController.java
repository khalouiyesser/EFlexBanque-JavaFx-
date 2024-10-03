package controllers.article;

import Entities.actualites.Article;
import Entities.actualites.CommentaireHadhemi;
import controllers.commentaireArticle.CommentaireItemFrontController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceArticle;
import services.ServiceCommentaireHadhemi;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DetailArticleAdminController implements Initializable {

    @FXML
    private Text category;

    @FXML
    private ScrollPane CommentListContainerr;
    @FXML
    private GridPane commentsListContainer;

    @FXML
    private VBox content_area;

    @FXML
    private Text description;

    @FXML
    private ImageView favBtn;

    @FXML
    private ImageView img;

    @FXML
    private Text offre;

    @FXML
    private HBox offreRow;

    @FXML
    private Text percentReviews;

    @FXML
    private ImageView reviewsBox_star1;

    @FXML
    private ImageView reviewsBox_star2;

    @FXML
    private ImageView reviewsBox_star3;

    @FXML
    private ImageView reviewsBox_star4;

    @FXML
    private ImageView reviewsBox_star5;

    @FXML
    private Text reviews_totalRev_title;

    @FXML
    private Text reviews_total_box;

    @FXML
    private ImageView star1;

    @FXML
    private ImageView star2;

    @FXML
    private ImageView star3;

    @FXML
    private ImageView star4;

    @FXML
    private ImageView star5;

    @FXML
    private Text titreArt;
    @FXML
    private Text piece;
    @FXML
    private VBox CommentListContainer;
    @FXML
    private Text total_verif_reviews;
    Article article;
    public int idAuto;

    public void setData(Article article) {
        this.article = article;
        ServiceArticle serviceArticle = new ServiceArticle();
        if (article != null) {
            titreArt.setText(article.getTitre_art());
            category.setText(article.getCategorie_art());
            if (article.getDate_pub_art() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = article.getDate_pub_art().format(formatter);
                offre.setText(formattedDate);
            } else {
                offre.setText("Date de publication non disponible");
            }
            img.setImage(new Image("file:///C:/Users/Yesser/PI/InnovatixYesser/public/uploads_directory/"+ article.getImage_art()));

//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                String formattedDate = article.getDate_pub_art().format(formatter);
//                datepub.setText(formattedDate);
            description.setText(article.getContenu_art());
            idAuto = article.getId();
            System.out.println("setArticle is called avec id  " + idAuto);
            piece.setOnMouseClicked(this::openArticleImage);

        }


            //////////////////////Ajb/////////////////

            ServiceCommentaireHadhemi sch = new ServiceCommentaireHadhemi();
            List<CommentaireHadhemi> list = new ArrayList<>();
            try {
                System.out.println("000111 id of this article is :: " + idAuto);

                //list = sch.afficher();

                System.out.println("000222 id of this article is :: " + idAuto);
                list = sch.afficherById(idAuto);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (CommentaireHadhemi commentaireHadhemi : list) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/commentaire/commentaireItemFront.fxml"));
                    Parent offreItem = loader.load();
                    CommentaireItemFrontController ComItem = loader.getController();
                    System.out.println("contenue du comm page1 " + ComItem);
                    ComItem.initData(commentaireHadhemi);
                    CommentListContainer.getChildren().add(offreItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            article = this.article;
        }

    private void openArticleImage(MouseEvent mouseEvent) {
        Path destination = Paths.get("C:/Users/Yesser/PI/InnovatixYesser/public/uploads_directory", article.getPiecejointe_art());

        try {
            File file = destination.toFile();
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("File not found: " + destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
    }

}}