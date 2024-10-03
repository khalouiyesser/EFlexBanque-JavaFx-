package controllers.Compte;

import Entities.Cheque;
import Entities.Compte;
import Entities.Compte;
import controllers.Cheque.ChequeItemsAdmin;
import controllers.Compte.CompteItems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCompte;
import services.ServiceCompte;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HistoriqueComptAdmin  implements Initializable {
    @FXML
    private Pane content_area;
    @FXML
    private VBox CompteContainer;
    private List<Compte> comptes = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCompte serviceCompte = new ServiceCompte();
        List<Compte> allComptes = new ArrayList<>();
        List<Compte> filteredComptes = new ArrayList<>();
        try {
            allComptes = serviceCompte.afficher();
            // Filtering for only "Approuvé" cheques
            for (Compte compte : allComptes) {
                if ("Approuve".equals(compte.getStatut())) {
                    filteredComptes.add(compte);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Compte compte : filteredComptes) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
                Parent offreItem = loader.load();
                CompteItems offreStageItem = loader.getController();
                offreStageItem.initData(compte);
                CompteContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void returnbackCard(MouseEvent mouseEvent) {
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
//    public void addCompte(Compte compte) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
//            AnchorPane compteItem = loader.load();
//            CompteItems compteController = loader.getController();
//            compteController.initData(compte);
//            CompteContainer.getChildren().add(compteItem);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
public void addCompte(Compte compte) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
        AnchorPane compteItem = loader.load();
        CompteItems compteController = loader.getController();
        compteController.initData(compte);
        CompteContainer.getChildren().add(compteItem);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void loadCompte(List<Compte> searchResults) {
        CompteContainer.getChildren().clear(); // Clear existing views
        for (Compte compte : searchResults) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
                Parent item = loader.load();
                CompteItems controller = loader.getController();
                controller.initData(compte);
                CompteContainer.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}