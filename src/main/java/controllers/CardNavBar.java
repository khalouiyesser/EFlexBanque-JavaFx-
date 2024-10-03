package controllers;

import controllers.demandeStage.AfficheDemandeController;
import controllers.demandeStage.DemandeStageController;
import controllers.offreDeStage.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class CardNavBar {

    @FXML
    private Button ListeOffres;

    @FXML
    private Button ListeStage;


    @FXML
    private Pane content_area;

    @FXML
    private Button demandeS;

    @FXML
    private Button listContrat;

    @FXML
    private HBox offreModel;

    @FXML
    private VBox reductionForm;


    @FXML
    void OpenListeContrat(MouseEvent event) {


    }

    // @FXML
    //void OpenListeStage(MouseEvent event) {
    //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/AfficheDemandes.fxml"));
    //Parent root = null;
    //try {
    //  root = loader.load();
    //} catch (IOException e) {
    //   throw new RuntimeException(e);
    // }

    //AfficheDemandeController afficheDemandeController = loader.getController();

    // Set the scene
    //Stage stage = (Stage) demandeS.getScene().getWindow();
    //Scene scene = new Scene(root);
    //stage.setScene(scene);
    // stage.show();

    //}

    @FXML
    public void OpenListOffre(MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/AffichageOffre.fxml"));
        Parent affichageOffre = loader.load();

        controllers.offreDeStage.AfficheOffreController affichOffreController = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(affichageOffre);
    }

    public void OpenListeStage(MouseEvent mouseEvent) {
    }


    public void OpenDemandeStage(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/AfficheDemandes.fxml"));
        Parent afficheDemande = loader.load();

        // Utilisez le type générique Controller pour obtenir le contrôleur
        Initializable controller = loader.getController();

        // Vérifiez si le contrôleur est une instance de AfficheDemandeController
        if (controller instanceof AfficheDemandeController) {
            AfficheDemandeController afficheDemandeController = (AfficheDemandeController) controller;
            // Utilisez votre contrôleur correctement ici
        } else {
            throw new ClassCastException("Le contrôleur n'est pas une instance de AfficheDemandeController");
        }

        content_area.getChildren().clear();
        content_area.getChildren().add(afficheDemande);
    }

}