package controllers.Compte;

import Entities.Cheque;
import Entities.Compte;
import services.*;
import controllers.Cheque.AjouterChequeCard;
import controllers.Cheque.ListeChequeAdmin;
import controllers.Cheque.ModifierCheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ShaymaService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CompteItems implements Initializable {

    @FXML
    private Label ApprouveBtn;
    @FXML
    private ImageView showC;

    @FXML
    private ImageView ApprouverC;

    @FXML
    private Text DateNaiss;

    @FXML
    private Text Email;

    @FXML
    private Text Nom;

    @FXML
    private Text NumCin;

    @FXML
    private Text Numtel;

    @FXML
    private Text Prenom;

    @FXML
    private Text Professon;

    @FXML
    private Label RefusBtn;

    @FXML
    private Text TypeCin;

    @FXML
    private Text TypeCompte;
    @FXML
    private Compte compte;
    @FXML
    private  ImageView reffuserC;
    @FXML
    private HBox HboxCompte;

    @FXML
    private Label ShowBtn;
    public void initData(Compte i){
        this.compte= i ;
        // System.out.println(compte.getId());
        ServiceCompte serviceCompte = new ServiceCompte();

        Email.setText(i.getEmail());
        Nom.setText(i.getNom());
        Prenom.setText(i.getPrenom());
        if ("Approuve".equals(compte.getStatut()) || "Refuser".equals(compte.getStatut())) {
            disableStatutButtons();
        }
        ApprouveBtn.setOnMouseClicked(mouseEvent -> {
            if (showConfirmationDialog("Approve", "Voullez vous Approuvez ce compte ?")) {
                updateCompteStatut("Approuve");
            }
            System.out.println(i.getId());
//            Symfony symfony = new Symfony();
//            symfony.mailingApprouver(i.getEmail());
            Symfony symfony = new Symfony();
//            symfony.MdpOblie(compte.getEmail(),2);
            symfony.Shayma(i.getId());

        });




    }
    @FXML
    private void ApprouverCompte(MouseEvent event) throws GeneralSecurityException, IOException {
        if (showConfirmationDialog("Approve", "Voullez vous Approuvez ce compte ?")) {
            updateCompteStatut("Approuvé");
            Symfony symfony = new Symfony();
//            symfony.MdpOblie(compte.getEmail(),2);
            symfony.Shayma(1);
        }

    }

//    public void ApprouverCompte(MouseEvent mouseEvent) throws GeneralSecurityException, IOException, MessagingException {
//
//        new MailingShayma().sendMail("Approbation de crédit", "ff");
//    }

    public void RefuserCompte(MouseEvent mouseEvent) {
        if (showConfirmationDialog("Reject", "Voulez vous réfuser ce compte bancaire?")) {
            updateCompteStatut("Réfusser" );
        }
    }

    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title + " Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> action = alert.showAndWait();
        return action.isPresent() && action.get() == ButtonType.OK;
    }

    private void updateCompteStatut(String newStatut) {
        try {
            compte.setStatut(newStatut);
            new ServiceCompte().modifierOne(compte);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Compte statut updated to " + newStatut + ".");
            disableStatutButtons();
            applyRejectedStyle();

            ListeCompteAdmin.getInstance().refreshCompteList();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update compte decision.");
            e.printStackTrace();
        }
    }
    private void disableStatutButtons() {
        ApprouveBtn.setDisable(true);
        RefusBtn.setDisable(true);
        ApprouverC.setOpacity(0.4);  // Optionally set opacity to visually indicate the button is disabled
        reffuserC.setOpacity(0.4);    // Optionally set opacity to visually indicate the button is disabled
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void applyRejectedStyle() {
        if ("Rejeté".equals(compte.getStatut())) {
            HboxCompte.getStyleClass().clear();
            HboxCompte.getStyleClass().add("compte-rejected");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void showCompte(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ShowDetailsCompte.fxml"));
            Parent parent = loader.load();

            // Obtenez le contrôleur de la nouvelle vue chargée
            ShowCompte ShowCompte = loader.getController();

            // Initialisez les données nécessaires au contrôleur de la nouvelle vue
            ShowCompte.initData(compte);

            // Créez une nouvelle scène avec la nouvelle vue
            Scene scene = new Scene(parent);

            // Créez une nouvelle fenêtre modale pour afficher la scène
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            // Centrez la fenêtre modale sur l'écran
            stage.centerOnScreen();

            // Affichez la fenêtre modale
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
