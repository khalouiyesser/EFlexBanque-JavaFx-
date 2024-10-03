package controllers.reclamation;

import Entities.User;
import Entities.actualites.Reclamation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceReclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeRecClientController implements Initializable {
    @FXML
    private VBox RecListContainer;


    @FXML
    private HBox articleaddBtn;

    @FXML
    private ComboBox<?> inputRectrie;

    @FXML
    private Pane content_area;

    @FXML
    private HBox recTableHead;
    public static User user;

    public void AjouterRec(MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/ajouterReclamation.fxml"));
        Parent addRecParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterReclamationController addRecController = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addRecParent);

    }

    public void Affichge() {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> list = new ArrayList<>();
        try {
            //list = sr.afficher();
            //Affichage by id ///
            list = sr.afficherById(user.getId());
            System.out.println("liste RecClient"+user);
            System.out.println("list: "+list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Reclamation reclamation : list) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/reclamationItemclient.fxml"));
                Parent offreItem = loader.load();
                ReclamationItemclientController RecItem = loader.getController();

                // Initialisez les données de réclamation pour chaque élément de réclamation
                RecItem.initData(reclamation);

                // Passez la réclamation au contrôleur d'ajout de réponse
                RecItem.initDataRec(reclamation);

                RecListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Affichge();
    }

    public void list_rep(MouseEvent mouseEvent) {

    }
}