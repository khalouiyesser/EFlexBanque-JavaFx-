package controllers.Compte;

import Entities.Cheque;
import Entities.Compte;
import controllers.Cheque.updateChequeCard;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import services.ServiceCompte;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static controllers.Cheque.updateChequeCard.FxmlToLoad;

public class ShowCompte {

    @FXML
    private Text Cin;

    @FXML
    private Text DateNaiss;

    @FXML
    private Text DatedelCin;

    @FXML
    private Text Email;

    @FXML
    private Text Montant;

    @FXML
    private Text Nom;

    @FXML
    private Text Prenom;

    @FXML
    private Text Profession;

    @FXML
    private Text Sexe;

    @FXML
    private Text TypeCin;

    @FXML
    private Text TypeCompte;
    @FXML
    private Text NumTel;

    @FXML
    private HBox addReviewsModel;

    @FXML
    private Pane content_area;

    @FXML
    private Text nameInputError;

    @FXML
    private HBox nameInputErrorHbox;

    @FXML
    private GridPane productDetailsContainer;

    @FXML
    private HBox updateBtnContainer;


    @FXML
    public void CloseDetails(MouseEvent mouseEvent) {
//        // Récupérez la scène parente à partir de la source de l'événement
//        Scene scene = ((Node) mouseEvent.getSource()).getScene();
//
//        // Fermez la scène parente
//        Stage stage = (Stage) scene.getWindow();
//        stage.close();
    }

    public static String getFxmlToLoad() {
        return FxmlToLoad;
    }
    public static void setFxmlToLoad(String FxmlToLoad) {
        updateChequeCard.FxmlToLoad = FxmlToLoad;
    }

    public void initData(Compte compte ) {
        ServiceCompte serviceCompte = new ServiceCompte();

        Email.setText(String.valueOf(compte.getEmail()));
        Nom.setText(String.valueOf(compte.getNom()));
        Prenom.setText(compte.getPrenom());
        TypeCin.setText(compte.getType_cin());
        Cin.setText(String.valueOf(compte.getCin()));
        DatedelCin.setText(String.valueOf(compte.getDate_delivrance_cin()));
        DateNaiss.setText(String.valueOf(compte.getDate_naissance()));
        TypeCompte.setText(compte.getType_compte());
        Profession.setText(compte.getProffesion());
        Sexe.setText(compte.getSexe());
        Montant.setText(String.valueOf(compte.getMontant()));
        NumTel.setText(String.valueOf(compte.getNumero_telephone()));
    }

    public void setProjectUpdateData(Compte compte) {

        Email.setText(String.valueOf(compte.getEmail()));
        Nom.setText(compte.getNom());
        Prenom.setText(compte.getPrenom());
        TypeCin.setText(String.valueOf(compte.getType_cin()));
        Cin.setText(String.valueOf(compte.getCin()));
        DatedelCin.setText(String.valueOf(compte.getDate_delivrance_cin()));
        DateNaiss.setText(String.valueOf(compte.getDate_naissance()));
        TypeCompte.setText(compte.getType_compte());
        Profession.setText(compte.getProffesion());
        Sexe.setText(compte.getSexe());
        Montant.setText(String.valueOf(compte.getMontant()));
        NumTel.setText(String.valueOf(compte.getNumero_telephone()));


    }

    public void close_addReviewsModel(MouseEvent mouseEvent) {
        Scene scene = ((Node) mouseEvent.getSource()).getScene();

        // Fermez la scène parente
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
}




