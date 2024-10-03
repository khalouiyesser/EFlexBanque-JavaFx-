package controllers;

import Entities.actualites.Article;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import services.ServiceArticle;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListeArticlesFrontController implements Initializable {

    @FXML
    private TextField ArticlesclientsfSearchInput;

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
    private ComboBox<?> categoryInput;

    @FXML
    private Pane content_area;

    @FXML
    private Label contenuArt;

    @FXML
    private Button dateArt;

    @FXML
    private ComboBox<?> dateclientsfInput;

    @FXML
    private HBox getPromotionalItemsBtn;

    @FXML
    private ImageView imageP;

    @FXML
    private Button newtP;

    @FXML
    private ComboBox<?> notesfclientsInput;

    @FXML
    private Label postNbr;
    @FXML
    private ImageView imageart;
    @FXML
    private GridPane productsListContainer;

    @FXML
    private Button titreArt;

    @FXML
    private ComboBox<?> titreclientsfInput;

    @FXML
    private ImageView userImg;

    @FXML
    private Label userNom;
    public static int i = 0;


    ServiceArticle serviceArticle = new ServiceArticle();
    private static List<Article> articles;

    private int currentArticleIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ListeArticlesFrontController.articles = new ServiceArticle().afficher();
            //Collections.reverse(ListeArticlesFrontController.articles);
            System.out.println(articles);
        } catch (SQLException ex) {
            Logger.getLogger(ListeArticlesFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
        if (currentArticleIndex < ListeArticlesFrontController.articles.size() ) {
            displayArticle(ListeArticlesFrontController.articles.get(currentArticleIndex));
            currentArticleIndex++; // Increment index to display next article
            System.out.println(currentArticleIndex);

        } else {
            // Reset to the first index
            currentArticleIndex = 1;
            displayArticle(ListeArticlesFrontController.articles.get(currentArticleIndex));
            System.out.println(currentArticleIndex);

        }
    }));


    public void initializeData () {
        try {
            articles = serviceArticle.afficher();
            if (!articles.isEmpty()) {
                displayArticle(articles.get(currentArticleIndex));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception d'une manière appropriée, par exemple, afficher un message à l'utilisateur
        }
    }

    private void displayArticle (Article article){
        titreArt.setText(article.getTitre_art());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = article.getDate_pub_art().format(formatter);
        dateArt.setText(formattedDate);
        contenuArt.setText(article.getContenu_art());
        categorieart.setText(article.getCategorie_art());
//        Path destination =  imageName);

        Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory",article.getImage_art());
//        System.out.println("destination");
        if (Files.exists(destination)) {
            try {
//                System.out.println("destination");
                Image image = new Image(destination.toUri().toString());
                imageP.setImage(image);
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
                // Gérer l'erreur de chargement de l'image ici
            }
        } else {
            System.err.println("Le fichier image n'existe pas : " + destination);
            // Gérer l'absence du fichier image ici
        }

    }

    @FXML
    void Go_New_Post() {
        if (currentArticleIndex < ListeArticlesFrontController.articles.size() - 1) {
            currentArticleIndex++; // Increment the index to display the next article
        } else {
            // Reset to the first article
            currentArticleIndex = 0;
        }
        displayArticle(ListeArticlesFrontController.articles.get(currentArticleIndex));
    }

    @FXML
    void PreviousPost() {
        if (currentArticleIndex > 0) {
            currentArticleIndex--; // Decrease the index to display the previous article
            displayArticle(ListeArticlesFrontController.articles.get(currentArticleIndex));
        } else {
            // Loop to the last article
            currentArticleIndex = ListeArticlesFrontController.articles.size() - 1;
            displayArticle(ListeArticlesFrontController.articles.get(currentArticleIndex));
        }
    }



    @FXML
    void SearchByImage (MouseEvent event){
        // Implémenter la logique pour rechercher par image
    }

    @FXML
    void getPromotionalItems (MouseEvent event){
        // Implémenter la logique pour obtenir des articles promotionnels
    }

    @FXML
    void go_details_Posts (ActionEvent event){
        // Implémenter la logique pour afficher les détails d'un post
    }

    @FXML
    void nextPost (){
//            try {
//                if (currentArticleIndex < articles.size() - 1) {
//                    currentArticleIndex++;
//                    displayArticle(articles.get(currentArticleIndex));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Gérer l'exception d'une manière appropriée
//            }

        i++;

        if (ListeArticlesFrontController.articles.size() == i) {
            i = 1;

            showArticle();
        } else {
            Article article = ListeArticlesFrontController.articles.get(i);
            titreArt.setText(article.getTitre_art());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = article.getDate_pub_art().format(formatter);
            dateArt.setText(formattedDate);
            userNom.setText(article.getNom_aut_art());
            categorieart.setText(article.getNom_aut_art());
            contenuArt.setText(article.getContenu_art());
            postNbr.setText(i + 1 + "#");


        }
    }




    @FXML
    void searchProduct (KeyEvent event){
        // Implémenter la logique pour rechercher un produit
    }
    ServiceArticle sa = new ServiceArticle();
    public void showArticle () {

        if (ListeArticlesFrontController.articles.size() == 0) {
            Empty.getChildren().clear();
            Pane pane = new Pane();
            Label label2 = new Label("Désolé, Pas d'article.");
            label2.setAlignment(Pos.CENTER);
            label2.setLayoutY(69.0);
            label2.setPrefHeight(93.0);
            label2.setPrefWidth(458.0);
            label2.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1 0 0 0;");
            label2.setFont(Font.font("Calibri Italic", 30));
            label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));
            pane.getChildren().addAll(label2, ajoutPP);
            Empty.getChildren().add(pane);
        }
        else {

            int i = 0;
            Article article = ListeArticlesFrontController.articles.get(i);
            postNbr.setText(i + 1 + "#");
            titreArt.setText(article.getTitre_art());
            String url = "file:///C:/Users/Yesser/PI/InnovatixYesser/public/uploads_directory/" + article.getImage_art();
            System.out.println(url);
//            imageP.setImage(new Image("file:///" + System.getProperty("user.dir") + "/src/main/java/uploads/" + article.getImage_art()));
            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory",article.getImage_art());
            System.out.println("destination");
            if (Files.exists(destination)) {
                try {
                    System.out.println("destination");
                    Image image = new Image(destination.toUri().toString());
                    imageP.setImage(image);
                } catch (Exception e) {
                    System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
                    // Gérer l'erreur de chargement de l'image ici
                }
            } else {
                System.err.println("Le fichier image n'existe pas : " + destination);
                // Gérer l'absence du fichier image ici
            }


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = article.getDate_pub_art().format(formatter);
            dateArt.setText(formattedDate);


            userNom.setText(article.getNom_aut_art());
            categorieart.setText(article.getNom_aut_art());
            contenuArt.setText(article.getContenu_art());


        }

    }
}