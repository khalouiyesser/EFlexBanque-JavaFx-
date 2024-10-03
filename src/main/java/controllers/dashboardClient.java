package controllers;

import controllers.Cheque.AjouterChequeCard;
import controllers.Cheque.DemandeChequeListClient;
import controllers.Virement.DemandeVirementListClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class dashboardClient {

    @FXML
    private Button ListeCheque;

    @FXML
    private Button ListeVirement;

    @FXML
    private Pane content_area;

    @FXML
    private VBox fundListContainer;

    @FXML
    private Text userPointText;

    @FXML
    private HBox userTableHead;


    @FXML
    public void ListVirement(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeVirementListClient.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        DemandeVirementListClient demandeVirementListClient = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }
    @FXML

    public void listCheque(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        DemandeChequeListClient demandeChequeListClient = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }
}