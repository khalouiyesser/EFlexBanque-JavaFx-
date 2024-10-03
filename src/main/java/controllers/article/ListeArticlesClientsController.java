////package controllers;
////
////import Entities.actualites.Article;
////import javafx.event.ActionEvent;
////import javafx.fxml.FXML;
////import javafx.fxml.Initializable;
////import javafx.scene.control.*;
////import javafx.scene.image.ImageView;
////import javafx.scene.input.KeyEvent;
////import javafx.scene.input.MouseEvent;
////import javafx.scene.layout.*;
////import services.ServiceArticle;
////
////import java.net.URL;
////import java.time.format.DateTimeFormatter;
////import java.util.List;
////import java.util.ResourceBundle;
////
////public class ListeArticlesClientsController implements Initializable {
////
////    @FXML
////    private TextField ArticlesclientsfSearchInput;
////
////    @FXML
////    private AnchorPane Empty;
////
////    @FXML
////    private Button PreviousP;
////
////    @FXML
////    private Button ajoutPP;
////
////    @FXML
////    private BorderPane borderPost;
////
////    @FXML
////    private Label categorieart;
////
////    @FXML
////    private ComboBox<?> categoryInput;
////
////    @FXML
////    private Pane content_area;
////
////    @FXML
////    private Label contenuArt;
////
////    @FXML
////    private Button dateArt;
////
////    @FXML
////    private ComboBox<?> dateclientsfInput;
////
////    @FXML
////    private HBox getPromotionalItemsBtn;
////
////    @FXML
////    private ImageView imageP;
////
////    @FXML
////    private Button newtP;
////
////    @FXML
////    private ComboBox<?> notesfclientsInput;
////
////    @FXML
////    private Label postNbr;
////
////    @FXML
////    private GridPane productsListContainer;
////
////    @FXML
////    private Button titreArt;
////
////    @FXML
////    private ComboBox<?> titreclientsfInput;
////
////    @FXML
////    private ImageView userImg;
////
////    @FXML
////    private Label userNom;
////
////    private ServiceArticle serviceArticle;
////    private List<Article> articles;
////    private int currentArticleIndex = 0;
////
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////        serviceArticle = new ServiceArticle();
////        initializeData();
////    }
////
////    public void initializeData() {
////        try {
////            articles = serviceArticle.afficher();
////            if (!articles.isEmpty()) {
////                displayArticle(articles.get(currentArticleIndex));
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////            // Gérer l'exception d'une manière appropriée, par exemple, afficher un message à l'utilisateur
////        }
////    }
////
////    private void displayArticle(Article article) {
////        titreArt.setText(article.getTitre_art());
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
////        String formattedDate = article.getDate_pub_art().format(formatter);
////        dateArt.setText(formattedDate);
////        contenuArt.setText(article.getContenu_art());
////        categorieart.setText(article.getCategorie_art());
////    }
////
////    @FXML
////    void Go_New_Post(ActionEvent event) {
////        // Implémenter la logique pour créer un nouvel article
////    }
////
////    @FXML
////    void PreviousPost(ActionEvent event) {
////        try {
////            if (currentArticleIndex > 0) {
////                currentArticleIndex--;
////                displayArticle(articles.get(currentArticleIndex));
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////            // Gérer l'exception d'une manière appropriée
////        }
////    }
////
////    @FXML
////    void SearchByImage(MouseEvent event) {
////        // Implémenter la logique pour rechercher par image
////    }
////
////    @FXML
////    void getPromotionalItems(MouseEvent event) {
////        // Implémenter la logique pour obtenir des articles promotionnels
////    }
////
////    @FXML
////    void go_details_Posts(ActionEvent event) {
////        // Implémenter la logique pour afficher les détails d'un post
////    }
////
////    @FXML
////    void nextPost(ActionEvent event) {
////        try {
////            if (currentArticleIndex < articles.size() - 1) {
////                currentArticleIndex++;
////                displayArticle(articles.get(currentArticleIndex));
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////            // Gérer l'exception d'une manière appropriée
////        }
////    }
////
////    @FXML
////    void searchProduct(KeyEvent event) {
////        // Implémenter la logique pour rechercher un produit
////    }
////}
//package controllers;
//
//import Entities.actualites.Article;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.control.*;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.*;
//import javafx.scene.text.Font;
//import javafx.util.Duration;
//import services.ServiceArticle;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.text.DecimalFormat;
//import java.time.format.DateTimeFormatter;
//import java.util.Collections;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class ListeArticlesClientsController implements Initializable {
//
//    @FXML
//    private TextField ArticlesclientsfSearchInput;
//
//    @FXML
//    private AnchorPane Empty;
//
//    @FXML
//    private Button PreviousP;
//
//    @FXML
//    private Button ajoutPP;
//
//    @FXML
//    private BorderPane borderPost;
//
//    @FXML
//    private Label categorieart;
//
//    @FXML
//    private ComboBox<?> categoryInput;
//
//    @FXML
//    private Pane content_area;
//
//    @FXML
//    private Label contenuArt;
//
//    @FXML
//    private Button dateArt;
//
//    @FXML
//    private ComboBox<?> dateclientsfInput;
//
//    @FXML
//    private HBox getPromotionalItemsBtn;
//
//    @FXML
//    private ImageView imageP;
//
//    @FXML
//    private Button newtP;
//
//    @FXML
//    private ComboBox<?> notesfclientsInput;
//
//    @FXML
//    private Label postNbr;
//
//    @FXML
//    private GridPane productsListContainer;
//
//    @FXML
//    private Button titreArt;
//
//    @FXML
//    private ComboBox<?> titreclientsfInput;
//
//    @FXML
//    private ImageView userImg;
//
//    @FXML
//    private Label userNom;
//    public static int i = 0;
//
//
//    ServiceArticle serviceArticle = new ServiceArticle();
//    private static List<Article> articles;
//
//    private int currentArticleIndex = 0;
//
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////        serviceArticle = new ServiceArticle();
////        initializeData();
////    }
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            ListeArticlesClientsController.articles = new ServiceArticle().getAllArticles();
//            Collections.reverse(ListeArticlesClientsController.articles);
//        } catch (SQLException ex) {
//            Logger.getLogger(ListeArticlesClientsController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//
//    }
//    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
//
//        int i = 0;
//        i++;
//        if (ListeArticlesClientsController.articles.size() == i) {
//
//
//            showArticle();
//
//        } else {
//            showArticle();
//
//        }
//
//    }));
////    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
////
////        // Définir la configuration de la timeline
////        timeline.setCycleCount(Timeline.INDEFINITE);
////        timeline.play();}
//        public void initializeData () {
//            try {
//                articles = serviceArticle.afficher();
//                if (!articles.isEmpty()) {
//                    displayArticle(articles.get(currentArticleIndex));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Gérer l'exception d'une manière appropriée, par exemple, afficher un message à l'utilisateur
//            }
//        }
//
//        private void displayArticle (Article article){
//            titreArt.setText(article.getTitre_art());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String formattedDate = article.getDate_pub_art().format(formatter);
//            dateArt.setText(formattedDate);
//            contenuArt.setText(article.getContenu_art());
//            categorieart.setText(article.getCategorie_art());
//        }
//
//        @FXML
//        void Go_New_Post (ActionEvent event){
////            Parent fxml = FXMLLoader.load(getClass().getResource("AjouterArticl"));
////            ShowPostPane.getChildren().removeAll();
////            ShowPostPane.getChildren().setAll(fxml);
//          }
//
//        @FXML
//        void PreviousPost (){
//            try {
//                if (currentArticleIndex > 0) {
//                    currentArticleIndex--;
//                    displayArticle(articles.get(currentArticleIndex));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Gérer l'exception d'une manière appropriée
//            }
//        }
//
//        @FXML
//        void SearchByImage (MouseEvent event){
//            // Implémenter la logique pour rechercher par image
//        }
//
//        @FXML
//        void getPromotionalItems (MouseEvent event){
//            // Implémenter la logique pour obtenir des articles promotionnels
//        }
//
//        @FXML
//        void go_details_Posts (ActionEvent event){
//            // Implémenter la logique pour afficher les détails d'un post
//        }
//
//        @FXML
//        void nextPost (){
////            try {
////                if (currentArticleIndex < articles.size() - 1) {
////                    currentArticleIndex++;
////                    displayArticle(articles.get(currentArticleIndex));
////                }
////            } catch (Exception e) {
////                e.printStackTrace();
////                // Gérer l'exception d'une manière appropriée
////            }
//
//            i++;
//
//            if (ListeArticlesClientsController.articles.size() == i) {
//                i = 0;
//
//                showArticle();
//            } else {
//                Article article = ListeArticlesClientsController.articles.get(i);
//                titreArt.setText(article.getTitre_art());
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                String formattedDate = article.getDate_pub_art().format(formatter);
//                dateArt.setText(formattedDate);
//                userNom.setText(article.getNom_aut_art());
//                categorieart.setText(article.getNom_aut_art());
//                contenuArt.setText(article.getContenu_art());
//                postNbr.setText(i + 1 + "#");
//
//
//            }
//        }
//
//
//
//
//        @FXML
//        void searchProduct (KeyEvent event){
//            // Implémenter la logique pour rechercher un produit
//        }
//        ServiceArticle sa = new ServiceArticle();
//        public void showArticle () {
//
//            if (ListeArticlesClientsController.articles.size() == 0) {
//                Empty.getChildren().clear();
//                Pane pane = new Pane();
//                Label label2 = new Label("Désolé, Pas d'article.");
//                label2.setAlignment(Pos.CENTER);
//                label2.setLayoutY(69.0);
//                label2.setPrefHeight(93.0);
//                label2.setPrefWidth(458.0);
//                label2.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1 0 0 0;");
//                label2.setFont(Font.font("Calibri Italic", 30));
//                label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));
//                pane.getChildren().addAll(label2, ajoutPP);
//                Empty.getChildren().add(pane);
//            }
//            else {
//
//                int i = 0;
//                Article article = ListeArticlesClientsController.articles.get(i);
//                postNbr.setText(i + 1 + "#");
//                titreArt.setText(article.getTitre_art());
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                String formattedDate = article.getDate_pub_art().format(formatter);
//                dateArt.setText(formattedDate);
//                userNom.setText(article.getNom_aut_art());
//                categorieart.setText(article.getNom_aut_art());
//                contenuArt.setText(article.getContenu_art());
//
//
//            }
//
//        }
//    }
//package controllers;
//
//import Entities.actualites.Article;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.*;
//import services.ServiceArticle;
//
//import java.net.URL;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class ListeArticlesClientsController implements Initializable {
//
//    @FXML
//    private TextField ArticlesclientsfSearchInput;
//
//    @FXML
//    private AnchorPane Empty;
//
//    @FXML
//    private Button PreviousP;
//
//    @FXML
//    private Button ajoutPP;
//
//    @FXML
//    private BorderPane borderPost;
//
//    @FXML
//    private Label categorieart;
//
//    @FXML
//    private ComboBox<?> categoryInput;
//
//    @FXML
//    private Pane content_area;
//
//    @FXML
//    private Label contenuArt;
//
//    @FXML
//    private Button dateArt;
//
//    @FXML
//    private ComboBox<?> dateclientsfInput;
//
//    @FXML
//    private HBox getPromotionalItemsBtn;
//
//    @FXML
//    private ImageView imageP;
//
//    @FXML
//    private Button newtP;
//
//    @FXML
//    private ComboBox<?> notesfclientsInput;
//
//    @FXML
//    private Label postNbr;
//
//    @FXML
//    private GridPane productsListContainer;
//
//    @FXML
//    private Button titreArt;
//
//    @FXML
//    private ComboBox<?> titreclientsfInput;
//
//    @FXML
//    private ImageView userImg;
//
//    @FXML
//    private Label userNom;
//
//    private ServiceArticle serviceArticle;
//    private List<Article> articles;
//    private int currentArticleIndex = 0;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        serviceArticle = new ServiceArticle();
//        initializeData();
//    }
//
//    public void initializeData() {
//        try {
//            articles = serviceArticle.afficher();
//            if (!articles.isEmpty()) {
//                displayArticle(articles.get(currentArticleIndex));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Gérer l'exception d'une manière appropriée, par exemple, afficher un message à l'utilisateur
//        }
//    }
//
//    private void displayArticle(Article article) {
//        titreArt.setText(article.getTitre_art());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = article.getDate_pub_art().format(formatter);
//        dateArt.setText(formattedDate);
//        contenuArt.setText(article.getContenu_art());
//        categorieart.setText(article.getCategorie_art());
//    }
//
//    @FXML
//    void Go_New_Post(ActionEvent event) {
//        // Implémenter la logique pour créer un nouvel article
//    }
//
//    @FXML
//    void PreviousPost(ActionEvent event) {
//        try {
//            if (currentArticleIndex > 0) {
//                currentArticleIndex--;
//                displayArticle(articles.get(currentArticleIndex));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Gérer l'exception d'une manière appropriée
//        }
//    }
//
//    @FXML
//    void SearchByImage(MouseEvent event) {
//        // Implémenter la logique pour rechercher par image
//    }
//
//    @FXML
//    void getPromotionalItems(MouseEvent event) {
//        // Implémenter la logique pour obtenir des articles promotionnels
//    }
//
//    @FXML
//    void go_details_Posts(ActionEvent event) {
//        // Implémenter la logique pour afficher les détails d'un post
//    }
//
//    @FXML
//    void nextPost(ActionEvent event) {
//        try {
//            if (currentArticleIndex < articles.size() - 1) {
//                currentArticleIndex++;
//                displayArticle(articles.get(currentArticleIndex));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Gérer l'exception d'une manière appropriée
//        }
//    }
//
//    @FXML
//    void searchProduct(KeyEvent event) {
//        // Implémenter la logique pour rechercher un produit
//    }
//}
package controllers.article;

import Entities.actualites.Article;
import controllers.rss.RSSReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import services.ServiceArticle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListeArticlesClientsController implements Initializable {

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
    private ComboBox<String> categoryInput;

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
    private List<RSSReader> articlesRss;
    private final ServiceArticle serviceArticle = new ServiceArticle();

//    @FXML
//    public void initialize() {
//
//        try {
//            List<Article> articles = serviceArticle.afficher();
//            loadArticleCards(articles);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private void loadArticleCards(List<Article> articles) {
        int column = 0;
        int row = 1;
//        int cardSpacing = 10;
//        int rowSpacing = 10;
//        int columnSpacing = 10;
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        productsListContainer.setVgap(verticalGap);
        productsListContainer.setHgap(horizontalGap);
        for (Article article : articles) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
                VBox articleCard = fxmlLoader.load();

                // Get the controller
                articleCardClientController articleCardController = fxmlLoader.getController();

                // Set article data
                articleCardController.initializeData(article);

                // Add the article card to the grid pane
                productsListContainer.add(articleCard, column, row);

                // Increment row and column
                column++;
                if (column >= 3) {
                    column = 0;
                    row++;
                }

            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception appropriée ici, par exemple afficher un message d'erreur à l'utilisateur ou journaliser l'erreur
            }
        }
    }

    public void loadArticles() throws IOException, SQLException {
        productsListContainer.getChildren().clear(); // Nettoyer le contenu actuel

        // Récupérez la liste des articles à partir du service ou du gestionnaire de données
        List<Article> articles = serviceArticle.getAllArticles(); // Par exemple

        // Ajoutez chaque article au GridPane
        int row = 0;
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        productsListContainer.setVgap(verticalGap);
        productsListContainer.setHgap(horizontalGap);
        for (Article article : articles) {
            // Créez un contrôleur de carte d'article pour chaque article
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
            Parent articleCardParent = loader.load();

            // Initialisez les données de l'article dans le contrôleur de carte d'article
            articleCardClientController articleCardController = loader.getController();
            articleCardController.initializeData(article);

            // Ajoutez la carte d'article au GridPane
            productsListContainer.addRow(row++, articleCardParent);

        }
    }

    public void refreshArticleList() throws SQLException {
        ////        ArtListContainer.getChildren().clear(); // Nettoyer le contenu actuel
        ////
        ////        try {
        ////            List<Article> articles = serviceArticle.afficher();
        ////            loadArticles(articles);
        ////        } catch (SQLException e) {
        ////            e.printStackTrace();
        ////            // Gérer l'exception appropriée ici
        ////        }
        //        // Nettoyer le contenu actuel
        //        ArtListContainer.getChildren().clear();
        //
        //        // Charger à nouveau la liste des articles depuis la base de données
        //        List<Article> articles = serviceArticle.afficher();
        //
//        // Charger à nouveau les cartes d'articles dans le conteneur
//        loadArticleCards(articles);
        // Nettoyer le contenu actuel
        productsListContainer.getChildren().clear();

        try {
            // Charger à nouveau la liste des articles depuis la base de données
            List<Article> articles = serviceArticle.afficher();

            // Charger à nouveau les cartes d'articles dans le conteneur
            loadArticles(articles);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }
    }

    //hedhy
    private void loadArticles(List<Article> articles) {
        // Nettoyer le conteneur actuel
        productsListContainer.getChildren().clear();

        // Réinitialiser les valeurs de la ligne et de la colonne
        int row = 1;
        int column = 0;
        int maxColumns = 3; // Nombre maximum de colonnes par ligne
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 20; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        productsListContainer.setVgap(verticalGap);
        productsListContainer.setHgap(horizontalGap);
        // Parcourir chaque article et charger sa carte dans le conteneur
        for (Article article : articles) {
            try {
                // Charger la carte d'article à partir du fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
                VBox articleCard = loader.load();

                // Récupérer le contrôleur de la carte d'article
                articleCardClientController articleCardController = loader.getController();

                // Initialiser les données de l'article dans la carte d'article
                articleCardController.initializeData(article);

                // Ajouter la carte d'article au conteneur
                productsListContainer.add(articleCard, column, row);

                // Incrémenter la colonne
                column++;

                // Vérifier si nous devons passer à la ligne suivante
                if (column >= maxColumns) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception ici, si nécessaire
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            refreshArticleList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void SearchByImage(MouseEvent mouseEvent) {
    }

    public void searchProduct(KeyEvent keyEvent) {
    }

    public void getPromotionalItems(MouseEvent mouseEvent) {
    }

    public void rssArt(MouseEvent mouseEvent) throws IOException {
        // Charger le fichier FXML de listArticleAdmin
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/listRssBal.fxml"));
        Pane listArticleAdminPane = loader.load();

        // Remplacer le contenu de content_area par le contenu de listArticleAdmin
        content_area.getChildren().setAll(listArticleAdminPane);

    }
}