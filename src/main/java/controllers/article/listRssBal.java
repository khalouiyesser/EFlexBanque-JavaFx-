//package controllers.article;
//
//        import javafx.animation.KeyFrame;
//        import javafx.animation.Timeline;
//        import javafx.event.ActionEvent;
//        import javafx.fxml.FXML;
//        import javafx.fxml.Initializable;
//        import javafx.scene.control.*;
//        import javafx.scene.image.Image;
//        import javafx.scene.image.ImageView;
//        import javafx.scene.input.KeyEvent;
//        import javafx.scene.input.MouseEvent;
//        import javafx.scene.layout.AnchorPane;
//        import javafx.scene.layout.BorderPane;
//        import javafx.scene.layout.GridPane;
//        import javafx.scene.text.Font;
//        import javafx.scene.text.Text;
//        import javafx.util.Duration;
//        import controllers.rss.RSSReader;
//
//        import java.io.IOException;
//        import java.net.URL;
//        import java.util.List;
//        import java.util.ResourceBundle;
//
//public class listRssBal implements Initializable {
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
//    private Text contenuArt;
//
//    @FXML
//    private Button dateArt;
//
//    @FXML
//    private ComboBox<?> dateclientsfInput;
//
//    @FXML
//    private ImageView imageP;
//
//    @FXML
//    private ComboBox<?> notesfclientsInput;
//
//    @FXML
//    private Label postNbr;
//
//    @FXML
//    private ImageView imageart;
//
//    @FXML
//    private GridPane productsListContainer;
//
//
//    @FXML
//    private Text titreArt;
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
//    private List<RSSReader> articles;
//
//    private int currentArticleIndex = 0;
//
//    private Timeline timeline;
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            articles = RSSReader.loadArticlesFromURL("https://rss.app/feeds/v1.1/t9AIvL6SZLnwsI91.json");
//            if (!articles.isEmpty()) {
//                displayArticle(articles.get(currentArticleIndex));
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            // Handle IOException appropriately, e.g., show a message to the user
//        }
//
//        // Initialize and start the timeline for automatic article display
//        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> nextArticle()));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }
//
//    @FXML
//    void Go_New_Post() {
//        nextArticle();
//    }
//
//    @FXML
//    void PreviousPost() {
//        if (currentArticleIndex > 0) {
//            currentArticleIndex--;
//        } else {
//            currentArticleIndex = articles.size() - 1;
//        }
//        displayArticle(articles.get(currentArticleIndex));
//    }
//
//    @FXML
//    void nextPost() {
//        nextArticle();
//    }
//
//    private void nextArticle() {
//        if (currentArticleIndex < articles.size() - 1) {
//            currentArticleIndex++;
//        } else {
//            currentArticleIndex = 0;
//        }
//        displayArticle(articles.get(currentArticleIndex));
//    }
//
//    private void displayArticle(RSSReader article) {
//        titreArt.setText(article.getTitle());
//        dateArt.setText(article.getDatePublished());
//        contenuArt.setText(article.getContent());
//        categorieart.setText(article.getAuthor());
//
//        if (!article.getImageUrl().isEmpty()) {
//            try {
//                Image image = new Image(article.getImageUrl());
//                imageP.setImage(image);
//            } catch (Exception e) {
//                System.err.println("Error loading image: " + e.getMessage());
//            }
//        }
//    }
//
//    public void searchProduct(KeyEvent keyEvent) {
//    }
//
//    public void SearchByImage(MouseEvent mouseEvent) {
//    }
//
//    public void getPromotionalItems(MouseEvent mouseEvent) {
//    }
//
//    public void go_details_Posts(ActionEvent actionEvent) {
//    }
//
//    // Other methods
//
//}
package controllers.article;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import controllers.rss.RSSReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class listRssBal implements Initializable {

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
    private Text contenuArt;

    @FXML
    private Button dateArt;

    @FXML
    private ComboBox<?> dateclientsfInput;

    @FXML
    private ImageView imageP;

    @FXML
    private ComboBox<?> notesfclientsInput;

    @FXML
    private Label postNbr;

    @FXML
    private ImageView imageart;

    @FXML
    private Pane content_area;
    @FXML
    private GridPane productsListContainer;

    @FXML
    private Text titreArt;

    @FXML
    private ComboBox<?> titreclientsfInput;

    @FXML
    private ImageView userImg;

    @FXML
    private Label userNom;
    @FXML
    private ImageView returnback;
    private List<RSSReader> articles;

    private int currentArticleIndex = 0;

    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            articles = RSSReader.loadArticlesFromURL("https://rss.app/feeds/v1.1/tXZL88RGnCmxrXxR.json");
            if (!articles.isEmpty()) {
                displayArticle(articles.get(currentArticleIndex));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle IOException appropriately, e.g., show a message to the user
        }

        // Initialize and start the timeline for automatic article display
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> nextArticle()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        returnback.setOnMouseClicked(mouseEvent -> {
            try {
                // Charger le fichier FXML de listArticleAdmin
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/listeArticlesClients.fxml"));
                Pane listArticleAdminPane = loader.load();

                // Remplacer le contenu de content_area par le contenu de listArticleAdmin
                content_area.getChildren().setAll(listArticleAdminPane);
            } catch (IOException e) {
                e.printStackTrace();
            }});}

    @FXML
    void Go_New_Post() {
        nextArticle();
    }

    @FXML
    void PreviousPost() {
        if (currentArticleIndex > 0) {
            currentArticleIndex--;
        } else {
            currentArticleIndex = articles.size() - 1;
        }
        displayArticle(articles.get(currentArticleIndex));
    }

    @FXML
    void nextPost() {
        nextArticle();
    }

    private void nextArticle() {
        if (currentArticleIndex < articles.size() - 1) {
            currentArticleIndex++;
        } else {
            currentArticleIndex = 0;
        }
        displayArticle(articles.get(currentArticleIndex));
    }

    private void displayArticle(RSSReader article) {
        titreArt.setText(article.getTitle());
        dateArt.setText(article.getDatePublished());
        String wrappedContent = wrapText(article.getContent(), 60);
        contenuArt.setText(wrappedContent);
        categorieart.setText(article.getAuthor());

        URL imageUrl = article.getImageUrl();
        if (imageUrl != null) {
            try {
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                Image image = new Image(inputStream);
                imageP.setImage(image);
                inputStream.close();
            } catch (IOException e) {
                String imagePathArt = "C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory\\0cf5ce9f-7746-4cd0-aaf6-27580468a1d7.png";
                Image image = new Image("file://" + imagePathArt); // Chargement d'une image à partir du système de fichiers local
                imageP.setImage(image);
                System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
            }
        }
    }



    // Méthode pour envelopper le texte avec un retour à la ligne chaque 50 caractères
    private String wrapText(String text, int wrapLength) {
        StringBuilder sb = new StringBuilder(text);
        int i = 0;
        while ((i = sb.indexOf(" ", i + wrapLength)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        return sb.toString();
    }

    public void searchProduct(KeyEvent keyEvent) {
    }

    public void SearchByImage(MouseEvent mouseEvent) {
    }

    public void getPromotionalItems(MouseEvent mouseEvent) {
    }

    public void go_details_Posts(ActionEvent actionEvent) {
    }

    public void returnbackarticle(MouseEvent mouseEvent) {


    }
}
