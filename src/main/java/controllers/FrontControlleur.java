package controllers;

import Entities.Evenement;
import Entities.User;
import Entities.actualites.Article;
import controllers.Cheque.AjouterChequeCard;
import controllers.ChequeItemsController;
import controllers.Credit.AjouterCreditCard;
import controllers.Credit.GMailer;
import controllers.Virement.AjouterVirementCard;
import controllers.Virement.VirementCard;
import controllers.article.*;
import controllers.commentaireArticle.CommentArticleController;
import controllers.reclamation.AjouterReclamationController;
import controllers.reclamation.ListeRecClientController;
import controllers.reponse.AjouterReponseAdminController;
import controllers.rss.RSSReader;
import controllers.user.LoginController;
import controllers.user.UserSession;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.*;


import Entities.actualites.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;
import services.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class FrontControlleur implements Initializable {
    public AnchorPane Empty;
    public BorderPane borderPost;
    public Label contenuArt;
    public Label categorieart;
    public Button titreArt;
    public Label userNom;
    public ImageView userImg;
    public Button dateArt;
    public ImageView imageP;
    public Button newtP;
    public Button PreviousP;
    public Button ajoutPP;
    @FXML
    private GridPane ArtListContainer;
    @FXML
    private VBox Rss;
    private List<RSSReader> articlesRss;

    private int currentArticleIndex = 0;

    private Timeline timeline;
    private final ServiceArticle serviceArticle = new ServiceArticle();


    public AnchorPane content_area;
    @FXML
    private GridPane evenementsListContainerfront;
    public HBox OpenStage;
    public HBox Tologin;
    @FXML
    private HBox Stage;

    @FXML
    private Button CreerCompte;
    @FXML
    private ImageView evenementsIcon;
  //  @FXML
    //private Pane content_area;
    @FXML
    private Label evenementsText;
    @FXML
    private HBox evenementsBtn;

    @FXML
    private Label dashboardText;
    @FXML
    private HBox dashboardBtn;
    @FXML
    private ImageView dashboardIcon;

    @FXML
    private Label usersText;

    @FXML
    private ImageView usersIcon;
    @FXML
    private HBox usersBtn;
    private  int sortValue =-1;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("yesser");
        try {
            // Call the method to load articles
            loadArticles();
            System.out.println("yesser load");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Handle any exceptions here, such as displaying an error message
        }
        // Nettoyer le contenu actuel
        ArtListContainer.getChildren().clear();
        System.out.println("yesser children");

        // Récupérez la liste des articles à partir du service ou du gestionnaire de données
        List<Article> articles = new ArrayList<>(); // Par exemple
        try {
            articles = serviceArticle.afficher();
            System.out.println(articles);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Créer un ScrollPane pour permettre le défilement des articles
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true); // Ajuster la largeur du ScrollPane à celle de la GridPane
        scrollPane.setContent(ArtListContainer);

        // Ajouter le ScrollPane à la vue parent
        // Assurez-vous que ArtListContainer est déjà ajouté à une vue parent dans votre scène FXML
        // Si ce n'est pas le cas, ajoutez d'abord ArtListContainer à la vue parent dans votre FXML
        // Ensuite, vous pouvez ajouter le ScrollPane à la même vue parent
        // Par exemple, si ArtListContainer est déjà ajouté à une VBox nommée container dans votre FXML :
        // container.getChildren().add(scrollPane);

        // Ajoutez chaque article au GridPane
        int row = 1;
        int column = 0;
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);

        for (Article article : articles) {
            System.out.println("loadArticles" + article.getId());
            // Charger la carte d'article à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
            Parent articleCardParent = null;
            try {
                articleCardParent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Initialisez les données de l'article dans le contrôleur de carte d'article
            articleCardClientController articleCardController = loader.getController();
            articleCardController.initializeData(article);

            // Ajoutez la carte d'article au GridPane
            ArtListContainer.add(articleCardParent, column, row);

            // Incrémentez la colonne et passez à la ligne suivante si nécessaire
            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }


        try {
            articlesRss = RSSReader.loadArticlesFromURL("https://rss.app/feeds/v1.1/tXZL88RGnCmxrXxR.json");
            if (!articles.isEmpty()) {
                displayArticle(articlesRss.get(currentArticleIndex)); // Appel de la méthode avec un objet de type RSSReader
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Gérer IOException de manière appropriée
        }



        // Initialize and start the timeline for automatic article display
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> nextArticle()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Tologin.setOnMouseClicked(mouseEvent -> {
            ServiceUser serviceUser = new ServiceUser();
            User userSession = null;
            try {
                userSession = serviceUser.getOneUserSession();
                System.out.println(userSession);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println("try");
                if (userSession.getSessionconnect() == 0) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
                    Parent parent = loader.load();
                    LoginController  loginController = loader.getController();
                    content_area.getChildren().clear();
                    content_area.getChildren().add(parent);

                } else {

                    String password = userSession.getPassword();
                    System.out.println("password" + password);
                    if (userSession.getIs_blocked() == 1) {
                        LoginController.AlertUtil.showAlert("Login", "Your account is blocked.", Alert.AlertType.ERROR);
                    } else {
                        UserSession.getInstance().setEmail(userSession.getEmail());
                        System.out.println("to the DASHBOARD");
                        if (userSession.getRoles().equals("[\"ROLE_CLIENT\"]")
                                || userSession.getRoles().equals("[\"ROLE_EMPLOYEE\"]")) {
                            AjouterCreditCard ajouterCreditCard = new AjouterCreditCard();
                            ajouterCreditCard.user = userSession;
                            ServiceCredit serviceCredit = new ServiceCredit();
                            serviceCredit.user = userSession;
                            ListeRecClientController serviceRec = new ListeRecClientController();
                            dashboardClientcreditrdv dashboardClientcreditrdv = new dashboardClientcreditrdv();
                            dashboardClientcreditrdv.user = userSession;
                            serviceRec.user = userSession;
                            AjouterChequeCard ajouterChequeCard = new AjouterChequeCard();
                            ajouterChequeCard.user= userSession;
//                                    controllers.ChequeItemsController ch GMaileequeItemsController = new controllers.ChequeItemsController();
                            ChequeItemsController chequeItemsController = new ChequeItemsController();
                            chequeItemsController.user = userSession;
                            AjouterVirementCard ajouterVirementCard = new AjouterVirementCard();
                            ajouterVirementCard.user = userSession;
                            VirementCard virementCard = new VirementCard();
                            virementCard.user = userSession;
                            DashboardClientOff dashboardClientOff = new DashboardClientOff();
                            dashboardClientOff.user = userSession;
                            AjouterArticleController ajouterArticleController = new AjouterArticleController();
                            ajouterArticleController.user = userSession;
                            AjouterReclamationController ajouterReclamationController = new AjouterReclamationController();
                            ajouterReclamationController.user = userSession;

                            CommentArticleController commentArticleController = new CommentArticleController();
                            commentArticleController.user = userSession;
                            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBarUser.fxml"));
                            content_area.getChildren().clear();
                            content_area.getChildren().add(root);

                            // Initialisation des contrôleurs et redirection vers le tableau de bord utilisateur
                        }
                        else if (userSession.getRoles().equals("[\"ROLE_ADMIN\"]")) {
                            AjouterReponseAdminController ajouterReponseAdminController = new AjouterReponseAdminController();
                            ajouterReponseAdminController.user = userSession;
                            dashboardClientcreditrdv dashboardClientcreditrdv = new dashboardClientcreditrdv();
                            dashboardClientcreditrdv.user = userSession;
                            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBar.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();

                        }


                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        CreerCompte.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormAjoutCompte.fxml"));
                AnchorPane captchaPane = loader.load();
                Scene scene = new Scene(captchaPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Stage) CreerCompte.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void setEvenementGridPaneList() {
        IService evenementService = new ServiceEvenement();

        List<Evenement> evenements = null;
        if (Evenement.getSearchValue() == null) {
            if (sortValue == 1) {
                evenements = evenementService.sortEvent(1, -1);
            } else {
                try {
                    evenements = evenementService.afficher();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            evenements = ServiceEvenement.searchEvenement(Evenement.getSearchValue());
        }
        int column = 0;
        int row = 1;
        try {
            for (Evenement evenement : evenements) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/project/OneEvenementListCardfront.fxml"));
                HBox oneEvenementCard = fxmlLoader.load();
                OneEvenementListCardControllerfront evenementCardController = fxmlLoader.getController();
                evenementCardController.setEvenementData(evenement);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                evenementsListContainerfront.add(oneEvenementCard, column++, row);
                GridPane.setMargin(oneEvenementCard, new Insets(0, 10, 25, 10));
                oneEvenementCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void open_evenementsList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/FrontEvenement.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }




    public void ToLogin(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        AnchorPane captchaPane = loader.load();
        Scene scene = new Scene(captchaPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Stage) CreerCompte.getScene().getWindow()).close();
    }




    @FXML
    void Tologin(MouseEvent event) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        LoginController ajoutUserController = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
      /*  userPane.getChildren().clear();
        userPane.getChildren().add(addArticleParent);*/
    }

    public void CreateCompte(MouseEvent mouseEvent) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormAjoutCompte.fxml"));
            AnchorPane captchaPane = loader.load();
            Scene scene = new Scene(captchaPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ((Stage) CreerCompte.getScene().getWindow()).close();
    }

    public void OpenStage(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/Recrutement.fxml"));
        Parent recrutementParent = loader.load();
        content_area.getChildren().clear();
        content_area.getChildren().add(recrutementParent);
    }

    public void CreerCompte(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormAjoutCompte.fxml"));
            AnchorPane captchaPane = loader.load();
            Scene scene = new Scene(captchaPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ((Stage) CreerCompte.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void Go_New_Post() {
        nextArticle();
    }

    @FXML
    void PreviousPost() {
        if (currentArticleIndex > 0) {
            currentArticleIndex--;
        } else {
            currentArticleIndex = articlesRss.size() - 1;
        }
        displayArticle(articlesRss.get(currentArticleIndex));
    }

    @FXML
    void nextPost() {
        nextArticle();
    }

    private void nextArticle() {
        if (currentArticleIndex < articlesRss.size() - 1) {
            currentArticleIndex++;
        } else {
            currentArticleIndex = 0;
        }
        displayArticle(articlesRss.get(currentArticleIndex));
    }

    private void displayArticle(RSSReader article) {
        titreArt.setText(article.getTitle());
        dateArt.setText(article.getDatePublished());
        String wrappedContent = wrapText(article.getContent(), 60);
        contenuArt.setText(wrappedContent);
        categorieart.setText(article.getAuthor());

        URL imageUrl = article.getImageUrl();
        if (imageUrl != null && !imageUrl.toString().isEmpty()) {
            try {
                Image image = new Image(imageUrl.toString());
                imageP.setImage(image);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
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





    private void loadArticleCards(List<Article> articles) {
        int column = 0;
        int row = 1;

        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);
        for (Article article : articles) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
                VBox articleCard = fxmlLoader.load();

                // Get the controller
                articleCardClientController articleCardController = fxmlLoader.getController();

                // Set article data
                articleCardController.initializeData(article);

                // Add the article card to the grid pane
                ArtListContainer.add(articleCard, column, row);

                // Increment row and column
                column++;
                if (column >= 4) {
                    column = 0;
                    row++;
                }

            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception appropriée ici, par exemple afficher un message d'erreur à l'utilisateur ou journaliser l'erreur
            }
        }
    }

//
//    public void loadArticles() throws IOException, SQLException {
//        ArtListContainer.getChildren().clear(); // Nettoyer le contenu actuel
//
//        // Récupérez la liste des articles à partir du service ou du gestionnaire de données
//        List<Article> articles = serviceArticle.getAllArticles(); // Par exemple
//        System.out.println(serviceArticle);
//        // Ajoutez chaque article au GridPane
//        int row = 1;
//        // Espacement entre les cartes
//        double verticalGap = 18; // Environ 9 mm
//        double horizontalGap = 50; // Environ 9 mm
//        // Espacement entre le GridPane et les cartes
//        double margin = 5; // Environ 10 mm
//        // Définir l'espacement vertical et horizontal
//        ArtListContainer.setVgap(verticalGap);
//        ArtListContainer.setHgap(horizontalGap);
//        for (Article article : articles) {
//            // Créez un contrôleur de carte d'article pour chaque article
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
//            Parent articleCardParent = loader.load();
//
//            // Initialisez les données de l'article dans le contrôleur de carte d'article
//            articleCardClientController articleCardController = loader.getController();
//            articleCardController.initializeData(article);
//
//            // Ajoutez la carte d'article au GridPane
//            ArtListContainer.addRow(row++, articleCardParent);
//
//        }
//    }


    public void loadArticles() throws IOException, SQLException {

        // Nettoyer le contenu actuel
        ArtListContainer.getChildren().clear();

        // Récupérez la liste des articles à partir du service ou du gestionnaire de données
        List<Article> articles = serviceArticle.getAllArticles(); // Par exemple

        // Créer un ScrollPane pour permettre le défilement des articles
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true); // Ajuster la largeur du ScrollPane à celle de la GridPane
        scrollPane.setContent(ArtListContainer);

        // Ajouter le ScrollPane à la vue parent
        // Assurez-vous que ArtListContainer est déjà ajouté à une vue parent dans votre scène FXML
        // Si ce n'est pas le cas, ajoutez d'abord ArtListContainer à la vue parent dans votre FXML
        // Ensuite, vous pouvez ajouter le ScrollPane à la même vue parent
        // Par exemple, si ArtListContainer est déjà ajouté à une VBox nommée container dans votre FXML :
        // container.getChildren().add(scrollPane);

        // Ajoutez chaque article au GridPane
        int row = 1;
        int column = 0;
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);

        for (Article article : articles) {
            System.out.println("loadArticles"+article.getId());
            // Charger la carte d'article à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
            Parent articleCardParent = loader.load();

            // Initialisez les données de l'article dans le contrôleur de carte d'article
            articleCardClientController articleCardController = loader.getController();
            articleCardController.initializeData(article);

            // Ajoutez la carte d'article au GridPane
            ArtListContainer.add(articleCardParent, column, row);

            // Incrémentez la colonne et passez à la ligne suivante si nécessaire
            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }
    }

    public void refreshArticleList() throws SQLException {


        // Nettoyer le contenu actuel
        ArtListContainer.getChildren().clear();

        try {
            // Charger à nouveau la liste des articles depuis la base de données
            List<Article> articles = serviceArticle.afficher();
            System.out.println("refresh" + articles);
            // Charger à nouveau les cartes d'articles dans le conteneur
            loadArticles(articles);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }
    }


    private void loadArticles(List<Article> articles) {
        // Nettoyer le conteneur actuel
        ArtListContainer.getChildren().clear();

        // Réinitialiser les valeurs de la ligne et de la colonne
        int row = 1;
        int column = 0;
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);
        // Parcourir chaque article et charger sa carte dans le conteneur
        for (Article article : articles) {
            try {
                System.out.println("loadArticles" + article.getId());
                // Charger la carte d'article à partir du fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
                VBox articleCard = loader.load();

                // Récupérer le contrôleur de la carte d'article
                articleCardClientController articleCardController = loader.getController();

                // Initialiser les données de l'article dans la carte d'article
                articleCardController.initializeData(article);

                // Ajouter la carte d'article au conteneur
                ArtListContainer.add(articleCard, column, row);

                // Incrémenter la colonne
                column++;

                // Vérifier si nous devons passer à la ligne suivante
                if (column >= 4) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception ici, si nécessaire
            }
        }
    }


    public void actualiteee(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Front.fxml"));
        Parent recrutementParent = loader.load();
        content_area.getChildren().clear();
        content_area.getChildren().add(recrutementParent);
    }

    public void actualitee(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Front.fxml"));
        Parent recrutementParent = loader.load();
        content_area.getChildren().clear();
        content_area.getChildren().add(recrutementParent);
    }
}

