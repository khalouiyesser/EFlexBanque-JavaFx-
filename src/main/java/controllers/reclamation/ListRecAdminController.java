package controllers.reclamation;

import Entities.actualites.Article;
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
import services.IServiceReclamation;
import services.ServiceReclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListRecAdminController implements Initializable {

    @FXML
    private VBox RecListContainer;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<?> inputRectrie;

    @FXML
    private HBox recTableHead;
    private final ServiceReclamation sr; // Ajoutez cette ligne

    public ListRecAdminController() {
        this.sr = new ServiceReclamation(); // Initialisez sr dans le constructeur
    }

    @FXML
    void openListRep(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/listRepAdmin.fxml"));
            Pane listRepAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listRepAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> list = new ArrayList<>();
        try {
            list = sr.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Reclamation reclamation : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/reclamationItemAdmin.fxml"));
                Parent offreItem = loader.load();
                ReclamationItemAdminController RecItem = loader.getController();
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
    private void loadReclamations() {
        List<Reclamation> list;
        try {
            list = sr.afficher();
            for (Reclamation reclamation : list) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/reclamationItemAdmin.fxml"));
                Parent offreItem = loader.load();
                ReclamationItemAdminController RecItem = loader.getController();
                RecItem.initData(reclamation);
                RecItem.initDataRec(reclamation);
                RecListContainer.getChildren().add(offreItem);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }
    }
    public void refreshRecList() {
        try {
            // Nettoyer le contenu actuel
            RecListContainer.getChildren().clear();

            // Charger à nouveau la liste des réclamations depuis la base de données
            ServiceReclamation sr = new ServiceReclamation();
            List<Reclamation> reclamations = sr.afficher();

            // Charger à nouveau les réclamations dans le conteneur
            for (Reclamation reclamation : reclamations) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/reclamationItemAdmin.fxml"));
                Parent offreItem = loader.load();
                ReclamationItemAdminController RecItem = loader.getController();

                // Initialiser les données de réclamation pour chaque élément de réclamation
                RecItem.initData(reclamation);

                // Passez la réclamation au contrôleur d'ajout de réponse
                RecItem.initDataRec(reclamation);

                RecListContainer.getChildren().add(offreItem);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }
    }

}