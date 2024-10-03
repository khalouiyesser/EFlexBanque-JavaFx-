package controllers.user;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceUser;
import Entities.User;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javax.swing.text.html.ImageView;


public class UsersListController implements Initializable {
    @FXML
    private HBox content_area;
    @FXML
    private ImageView ToUser;

    @FXML
    private ComboBox<String> roleInput;

    @FXML
    private VBox userListContainer;


    @FXML
    private Text userListTitle;

    @FXML
    private Pane userPane;

    @FXML
    private HBox userTableHead;
    private static int updateUserModelShow = 0;
    private static String userEmailToUpdate = "";
    private static int filter = 0;
    public static int getupdateUserModelShow() {
        return updateUserModelShow;
    }

    public static void setupdateUserModelShow(int updateUserModelShow) {
        UsersListController.updateUserModelShow = updateUserModelShow;
    }

    public static String getuserToUpdate() {
        return userEmailToUpdate;
    }

    public static void setuserEmailToUpdate(String userEmailToUpdate) {
        UsersListController.userEmailToUpdate = userEmailToUpdate;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceUser projectService = new ServiceUser();
        List<User> list = new ArrayList<>();
        try {
            list = projectService.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // userListContainer.getChildren().add();
//           OffreStageItem offreStageItem = new OffreStageItem();
//            userListContainer.getChildren().add(offreStageItem.initE());

        for (User offre : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/TEST.fxml"));
                Parent offreItem = loader.load();
                TESTCONTROLLER offreStageItem = loader.getController();
                offreStageItem.initData(offre);
                userListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ArrayList<User> userList;
                if (filter == 0) {
                    userList = projectService.getAllUser();
                } else if (filter == 1) {
                    userList = projectService.getAllClient();
                } else {
                    userList = projectService.getAllEmplyee();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        ObservableList<String> items = roleInput.getItems();

        // Add new items to the list
        items.addAll("Tout", "Client", "Admin");

        if (filter == 0) {
            roleInput.setValue("Tout");
        } else if (filter == 1) {
            roleInput.setValue("Client");
        } else {
            roleInput.setValue("Admin");
        }
    }
    public void toAdduser(ActionEvent actionEvent)  throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AjoutUser.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjoutUserController ajouterUSER = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        userPane.getChildren().clear();
        userPane.getChildren().add(addArticleParent);
    }
    @FXML
    void roleChange(ActionEvent event) {
        if (roleInput != null && roleInput.getValue() != null) { // Vérifier si roleInput et sa valeur ne sont pas null
            if (roleInput.getValue().equals("Tout")) {
                filter = 0;
            } else if (roleInput.getValue().equals("Client")) {
                filter = 1;
            } else {
                filter = 2;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersList.fxml"));
            try {
                Parent  userPane = loader.load(); // Charger le fichier FXML
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de UsersList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(userPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Gérer le cas où roleInput ou sa valeur est null
            System.out.println("Role input is null or has no value.");
        }
    }


}


