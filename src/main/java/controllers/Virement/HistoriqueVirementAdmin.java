package controllers.Virement;

import Entities.Cheque;
import Entities.Virement;
import controllers.Cheque.ChequeItemsAdmin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;
import services.ServiceVirement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HistoriqueVirementAdmin implements Initializable {

    @FXML
    private VBox VireContainer;
    @FXML
    private TextField VirementclientsfSearchInputAdmin;

    @FXML
    private Pane content_area;

    @FXML
    private Button retourcard;

    @FXML
    void returnBackVir(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CardAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceVirement serviceVirement = new ServiceVirement();
        List<Virement> allVirements = new ArrayList<>();
        List<Virement> filteredVirements = new ArrayList<>();
        try {
            allVirements = serviceVirement.afficher();
            // Filtering for only "Approuvé" cheques
            for (Virement virement : allVirements) {
                if ("Approuvé".equals(virement.getDecision_v())) {
                    filteredVirements.add(virement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Virement virement: filteredVirements) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent offreItem = loader.load();
                VirementItemsAdmin offreStageItem = loader.getController();
                offreStageItem.initData(virement);
                VireContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void VirementclientsfSearchInputAdmin(KeyEvent keyEvent) throws SQLException {
        ServiceVirement serviceVirement = new ServiceVirement(); // Créer une instance de ServiceVirement
        String searchKeyword = VirementclientsfSearchInputAdmin.getText();

        if (searchKeyword.isEmpty()) {
            // Si le mot-clé de recherche est vide, actualiser la liste des virements
            refreshVirementList();
        } else {
            try {
                List<Virement> searchResults = serviceVirement.searchVirement(searchKeyword);
                loadVirement(searchResults);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void loadVirement(List<Virement> searchResults) {
        VireContainer.getChildren().clear(); // Clear existing views
        for (Virement virement : searchResults) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent item = loader.load();
                VirementItemsAdmin controller = loader.getController();
                controller.initData(virement);
                VireContainer.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void refreshVirementList() {
        VireContainer.getChildren().clear();  // Clear the existing views
        List<Virement> filtredVirements = new ArrayList<>();
        try {
            List<Virement> allVirements = new ServiceVirement().afficher();
            for (Virement virement : allVirements) {
                if (!"Approuvé".equals(virement.getDecision_v())) {
                    filtredVirements.add(virement);
                }

            }

            for (Virement virement : filtredVirements) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent item = loader.load();
                VirementItemsAdmin controller = loader.getController();
                controller.initData(virement);
                VireContainer.getChildren().add(item);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }



}
