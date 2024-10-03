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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;
import services.ServiceCompte;
import services.ServiceCompte;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeCompteAdmin  implements Initializable {

    @FXML
    private Pane content_area;
    @FXML
    private VBox CompteContainer;
    private static ListeCompteAdmin instance;
    public ListeCompteAdmin() {
        instance = this;
    }
    public static ListeCompteAdmin getInstance() {
        return instance;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCompte serviceCompte = new ServiceCompte();
        List<Compte> allComptes = new ArrayList<>();
        List<Compte> filteredComptes = new ArrayList<>();
        try {
            allComptes = serviceCompte.afficherSE();

//            for (Compte compte : allComptes) {
//                System.out.println(compte);
//                if (!"Approuvé".equals(compte.getStatut())) {
//                    filteredComptes.add(compte);
//                }
//            }
            filteredComptes = allComptes.stream().filter(compte -> compte.getStatut() == "encours").toList();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Compte compte : filteredComptes) {
            System.out.println(compte.getStatut());
            try {
                //System.out.println(compte);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
                Parent offreItem = loader.load();
                CompteItems offreStageItem = loader.getController();
                offreStageItem.initData(compte);
                CompteContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        refreshCompteList();

    }
//    public void refreshCompteList() {
//        CompteContainer.getChildren().clear();  // Clear the existing views
//        List<Compte> filteredComptes = new ArrayList<>();
//        try {
//            List<Compte> allComptes = new ServiceCompte().afficher();
//            for (Compte compte : allComptes) {
//                if (!"Approuvé".equals(compte.getStatut())) {
//                    filteredComptes.add(compte);
//                }
//
//            }
//
//            for (Compte compte : filteredComptes) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
//                Parent item = loader.load();
//                CompteItems controller = loader.getController();
//                controller.initData(compte);
//                CompteContainer.getChildren().add(item);
//            }
//        } catch (SQLException | IOException e) {
//            e.printStackTrace(); // Proper error handling should be implemented
//        }
//    }

    public void refreshCompteList() {
        CompteContainer.getChildren().clear();  // Clear the existing views
        List<Compte> filteredComptes = new ArrayList<>();
        try {
            List<Compte> allCompte = new ServiceCompte().afficherSE();
            for (Compte compte : allCompte) {
                if (!"Approuvé".equals(compte.getStatut())) {
                    filteredComptes.add(compte);
                }

            }

            for (Compte compte : filteredComptes) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
                Parent item = loader.load();
                CompteItems controller = loader.getController();
                controller.initData(compte);
                CompteContainer.getChildren().add(item);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }

    private void loadCompteItem(List<Compte> searchResults) {
        CompteContainer.getChildren().clear();
        for (Compte compte : searchResults) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
                Parent item = loader.load();
                CompteItems controller = loader.getController();
                //System.out.println("List" + compte);
                controller.initData(compte);
                CompteContainer.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    private Parent loadCompteItem(Compte compte) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
//        Parent item = loader.load();
//        CompteItems controller = loader.getController();
//        controller.initData(compte);
//        return item;
//    }

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

    public void CloseDetails(MouseEvent mouseEvent) {
    }
}