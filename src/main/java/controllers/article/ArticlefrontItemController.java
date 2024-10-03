package controllers.article;

import Entities.actualites.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import services.ServiceArticle;

import java.time.format.DateTimeFormatter;

public class ArticlefrontItemController {

    @FXML
    private AnchorPane Empty;

    @FXML
    private Button PreviousP;

    @FXML
    private Button ajoutPP;

    @FXML
    private BorderPane borderPost;

    @FXML
    private Label categorieart;

    @FXML
    private Label contenuArt;

    @FXML
    private Button dateArt;

    @FXML
    private ImageView imageart;

    @FXML
    private Button newtP;

    @FXML
    private Label postNbr;

    @FXML
    private Button titreArt;

    @FXML
    private ImageView userImg;

    @FXML
    private Label userNom;

    @FXML
    private ImageView voirPlus;

    @FXML
    void Go_New_Post(ActionEvent event) {

    }

    @FXML
    void PreviousPost(ActionEvent event) {

    }

    @FXML
    void go_details_Posts(ActionEvent event) {

    }

    @FXML
    void nextPost(ActionEvent event) {

    }

    @FXML
    void voirPlus(MouseEvent event) {

    }
    public void initializeData(Article article) {
        ServiceArticle serviceArticle = new ServiceArticle();
        if (article != null) {
            titreArt.setText(article.getTitre_art());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = article.getDate_pub_art().format(formatter);
            dateArt.setText(formattedDate);
            imageart.setImage(new Image("file:///C:/Users/Yesser/PI/InnovatixYesser/public/uploads_directory/"+ article.getImage_art()));

            contenuArt.setText(article.getContenu_art());
            categorieart.setText(article.getCategorie_art());

        }
    }




}
