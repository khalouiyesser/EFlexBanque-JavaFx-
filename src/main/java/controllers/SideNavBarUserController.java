package controllers;

import Entities.User;
import controllers.user.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceUser;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.ResourceBundle;

public class SideNavBarUserController implements Initializable {

    @FXML
    private HBox Compte;

    @FXML
    private HBox CreditBtn;

    @FXML
    private HBox achatBtn;

    @FXML
    private HBox achatBtn1;

    @FXML
    private ImageView achatIcon;

    @FXML
    private ImageView achatIcon1;

    @FXML
    private HBox collectBtn;

    @FXML
    private ImageView collectIcon;

    @FXML
    private HBox commandsBtn;

    @FXML
    private ImageView commandsIcon;

    @FXML
    private Pane content_area;

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
    private HBox favBtn;

    @FXML
    private ImageView favIcon;

    @FXML
    private ImageView fundrisingIcon;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private ImageView productsIcon;

    @FXML
    private HBox profileBtn;

    @FXML
    private ImageView profileIcon;

    @FXML
    private Text totalNotif;
   public static User user;

    public void openProfil(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProfileUser.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void open_evenementsList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/EvenementsListUser.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);


    }
    public void logout(MouseEvent mouseEvent) throws IOException, SQLException {
        UserSession.getInstance().cleanUserSession();
        ServiceUser serviceUser = new ServiceUser();
        user = serviceUser.getOneUserSession();
        serviceUser.modifierDiconnect(user);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        EventObject event;
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void initialize(URL url, ResourceBundle rb) {


        ServiceUser userService = new ServiceUser();

        try {
            // user = userService.getOneUser(UserSession.getInstance().getEmail());
            if (UserSession.getInstance().getEmail() == null) {
                user = userService.getOneUser("mariem@gmail.com");
            } else {
                user = userService.getOneUser(UserSession.getInstance().getEmail());
            }
            //   Image img = new Image("img/admin.png" + user.getPhoto());


            navFullname.setText(user.getName());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DashboardClientOff.fxml"));
            Pane dashboardPane = loader.load();
            content_area.getChildren().setAll(dashboardPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void openArticleList(MouseEvent mouseEvent) {

        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/listeArticlesClients.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openRecList(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listeRecClient.fxml"));
            Pane listRecAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listRecAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void open_profile(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProfileUser.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void open_Compte(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/dashboardClient.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open_Credit(MouseEvent mouseEvent) {
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


    public void open_stage(MouseEvent mouseEvent) {
    }

    public void open_evenement(MouseEvent mouseEvent) {
    }

    public void openInv(MouseEvent mouseEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/ProjectsListUser.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    public void open_notifModel(MouseEvent mouseEvent) {
    }

    public void open_dashboard(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DashboardClientOff.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
