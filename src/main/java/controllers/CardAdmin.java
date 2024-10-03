package controllers;

import controllers.Cheque.HistoriqueChequeAdmin;
import controllers.Cheque.ListeChequeAdmin;
import controllers.Compte.HistoriqueComptAdmin;
import controllers.Compte.ListeCompteAdmin;
import controllers.Virement.AjouterVirementCard;
import controllers.Virement.HistoriqueVirementAdmin;
import controllers.Virement.ListVirementAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.lang.ClassCastException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class CardAdmin {



    @FXML
    private Button HistorCheque;

    @FXML
    private Button HistorCompte;

    @FXML
    private Button HistorVir;

    @FXML
    private Pane content_area;
    @FXML
    private Pane content_areaC;

    @FXML
    private Button listC;

    @FXML
    private Button listCh;

    @FXML
    private Button listV;

    @FXML
    private VBox reductionForm;


    public void OpenListeCompteAd(MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListCompteAdmin.fxml"));
        Parent addArticleParent = loader.load();

        ListeCompteAdmin listeCompteAdmin = loader.getController();

        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    public void OpenListeChequeAd(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListChequeAdmin.fxml"));
        Parent ajoutChequeParent = loader.load();

        ListeChequeAdmin listeChequeAdmin = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(ajoutChequeParent);
    }


    public void OpenListeVirAd(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListVirementAdmin.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        ListVirementAdmin listVirementAdmin = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    public void OpenHistoriqueCheque(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/HistoriqueChequeAdmin.fxml"));
        Parent HistoriqueParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        HistoriqueChequeAdmin historiqueChequeAdmin = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(HistoriqueParent);
    }

    public void OpenHistoriqueVirement(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/HistoriqueVirementAdmin.fxml"));
        Parent HistoriqueParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        HistoriqueVirementAdmin historiqueVirementAdmin = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(HistoriqueParent);
    }

    public void OpenHistoriqueCompte(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/HistoriqueCompteA.fxml"));
        Parent historiqueParent = loader.load();

        // Récupération du contrôleur de la vue d'historique des comptes
        HistoriqueComptAdmin historiqueComptAdmin = loader.getController();

        // Remplacer le contenu actuel par la vue d'historique des comptes
        content_area.getChildren().clear();
        content_area.getChildren().add(historiqueParent);
    }


}

