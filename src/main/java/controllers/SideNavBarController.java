package controllers;

import Entities.User;
import controllers.user.UserSession;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import services.ServiceUser;
import tests.TranslatorText;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.io.IOException;
import java.util.ResourceBundle;

public class SideNavBarController implements Initializable {

    public Label evenementsText;
    public ImageView evenementsIcon;
    public HBox evenementsBtn;
    @FXML
    private Pane content_area;

    public Pane getContent_area() {
        return content_area;
    }

    public void setContent_area(Pane content_area) {
        this.content_area = content_area;
    }

    @FXML
    private Text navFullname;

    @FXML
    private PieChart pieChart;

    @FXML
    private HBox InvestBtn;

    @FXML
    private HBox actualitesBtn;

    @FXML
    private ImageView actualitesIcon;

    @FXML
    private Label actualitesText;

    @FXML
    private HBox chartContainer;

    @FXML
    private HBox compteBtn;

    @FXML
    private ImageView comptesIcon;

    @FXML
    private Label comptesText;


    @FXML
    private HBox creditsBtn;

    @FXML
    private ImageView creditsIcon;

    @FXML
    private Label creditsText;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private Label dashboardText;

    @FXML
    private ImageView investissementsIcon;

    @FXML
    private Label investissementsText;

    @FXML
    private ImageView logo;

    @FXML
    private HBox navBarLogout;
    @FXML
    private HBox recBtn;

    @FXML
    private Label reclamationText;

    @FXML
    private ImageView reclamationsIcon;

    @FXML
    private HBox sideBarLogout;

    @FXML
    private HBox stagesBtn;

    @FXML
    private ImageView stagesIcon;

    @FXML
    private Label stagesText;

    @FXML
    private HBox usersBtn;

    @FXML
    private ImageView usersIcon;

    @FXML
    private Label usersText;
    @FXML
    private Label NAllUser;

    @FXML
    private Label NAllclients;

    @FXML
    private Label idAllEmplyoyeee;
    @FXML
    private VBox maryem;

    @FXML
    private Label name;
    @FXML
    private ImageView en;
    @FXML
    private ImageView fr;
    @FXML
    private ImageView tn;
    @FXML
    private AnchorPane content_area1;
    public static User user;


    @FXML
    void openUserList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UsersList.fxml"));
            Pane userListPane = loader.load();
            content_area.getChildren().setAll(userListPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void opencredit(ActionEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeCreditListClientUser.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openDashboardClientCredit(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/dashboardClientcreditredv.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logout(MouseEvent event) throws IOException, SQLException {
        UserSession.getInstance().cleanUserSession();
        ServiceUser serviceUser = new ServiceUser();
        user = serviceUser.getOneUserSession();
        serviceUser.modifierDiconnect(user);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

//    public void initData(User user) {
//        // Implémentez cette méthode pour initialiser des données spécifiques à l'utilisateur
//    }

    public void initializeDashboard() {
        try {
            ServiceUser serviceUser = new ServiceUser();
            int activeNB = serviceUser.getActiveNB();
            int unActiveNB = serviceUser.getunActiveNB();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Actifs", activeNB),
                    new PieChart.Data("Inactifs", unActiveNB)
            );

            pieChart.setData(pieChartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openArticleList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/listArticleAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openRecList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listRecAdmin.fxml"));
            Pane listRecAdminPane = loader.load();
            content_area.getChildren().setAll(listRecAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OpenCard(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CardAdmin.fxml"));
            Pane listCompteAdminPane = loader.load();
            content_area.getChildren().setAll(listCompteAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void open_ProjectList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/ProjectsList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
    }
    @FXML
    private void open_evenementsList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/EvenementsList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }


    public void CardStage(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/YesserTest/CardNavBar.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Traduire les labels
        translateLabels();

        // Récupérer le nombre d'utilisateurs actifs et inactifs
        ServiceUser serviceUser = new ServiceUser();

        try {
            User user;
            // Récupérer l'utilisateur actuel ou un utilisateur par défaut
            if (UserSession.getInstance().getEmail() == null) {
                user = serviceUser.getOneUser("mariem@gmail.com");
            } else {
                user = serviceUser.getOneUser(UserSession.getInstance().getEmail());
            }

            // Afficher le nom de l'utilisateur
            name.setText(user.getName());

            // Récupérer le nombre total d'utilisateurs, de clients et d'employés
            int totalUsers = serviceUser.getAllUser().size();
            int totalClients = serviceUser.getAllClient().size();
            int totalEmployees = serviceUser.getAllEmplyee().size();

            // Mettre à jour les labels avec les nombres
            NAllUser.setText(String.valueOf(totalUsers));
            NAllclients.setText(String.valueOf(totalClients));
            idAllEmplyoyeee.setText(String.valueOf(totalEmployees));

            try {
                // Récupérer le nombre d'utilisateurs actifs et inactifs
                int activeNB = serviceUser.getActiveNB();
                int unActiveNB = serviceUser.getunActiveNB();

                // Créer le graphique circulaire (PieChart)
                PieChart pieChart = new PieChart();
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Actifs", activeNB),
                        new PieChart.Data("Inactifs", unActiveNB)
                );

                // Remplir le graphique circulaire avec les données
                pieChart.setData(pieChartData);

                // Ajouter le graphique circulaire au conteneur
                VBox pieChartContainer = new VBox(pieChart);

                // Ajouter les pourcentages aux étiquettes
                for (PieChart.Data data : pieChart.getData()) {
                    double percentage = (data.getPieValue() / (activeNB + unActiveNB)) * 100;
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), " (", String.format("%.2f", percentage), "%)"
                            )
                    );
                }

                // Ajouter le graphique circulaire au conteneur existant
                maryem.getChildren().addAll(pieChartContainer);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void OpenDash(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SideNavBar.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area1.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void translateLabels() {
        TranslatorText translatorText = new TranslatorText();

        // Ajouter des événements pour chaque langue
        tn.setOnMouseClicked(mouseEvent -> translateLabelsTo("ar"));
        fr.setOnMouseClicked(mouseEvent -> translateLabelsTo("fr"));
        en.setOnMouseClicked(mouseEvent -> translateLabelsTo("en"));
    }

    private void translateLabelsTo(String language) {
        try {
            TranslatorText translatorText = new TranslatorText();
            String langFrom = "fr"; // La langue actuelle est le français
            String langTo = language; // La langue cible est passée en paramètre

            // Traduire chaque label vers la langue cible
            evenementsText.setText(translatorText.post(evenementsText.getText(), langFrom, langTo));
            actualitesText.setText(translatorText.post(actualitesText.getText(), langFrom, langTo));
            comptesText.setText(translatorText.post(comptesText.getText(), langFrom, langTo));
            creditsText.setText(translatorText.post(creditsText.getText(), langFrom, langTo));
            dashboardText.setText(translatorText.post(dashboardText.getText(), langFrom, langTo));
            investissementsText.setText(translatorText.post(investissementsText.getText(), langFrom, langTo));
            reclamationText.setText(translatorText.post(reclamationText.getText(), langFrom, langTo));
            stagesText.setText(translatorText.post(stagesText.getText(), langFrom, langTo));
            usersText.setText(translatorText.post(usersText.getText(), langFrom, langTo));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
