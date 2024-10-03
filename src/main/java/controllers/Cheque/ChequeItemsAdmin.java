package controllers.Cheque;

import Entities.Cheque;
import Entities.Compte;
import controllers.Cheque.ChequeItemsAdmin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import services.ServiceCompte;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChequeItemsAdmin implements Initializable {

    @FXML
    private Label ApprouveBtnC;

    @FXML
    private ImageView Approuver;
    @FXML
    private ImageView Refuser;
    @FXML
    private Text Cin;

    @FXML
    private Text Email;

    @FXML
    private Text Montant;

    @FXML
    private Text NometPrenom;

    @FXML
    private Label RefusBtnC;

    @FXML
    private Text Rib;

    @FXML
    private Text benificaire;

    @FXML
    private ImageView photoCin;

    @FXML
    private Text tel;
    private Cheque cheque;
    @FXML
    private HBox Hboxcheque;


    public void initData(Cheque i) {
        this.cheque = i;
        ServiceCheque serviceCheque = new ServiceCheque();

        Rib.setText(String.valueOf(i.getRib()));
        Cin.setText(String.valueOf(i.getCin()));
        Image image = new Image("file:" + i.getPhoto_cin());
        ImageView imageView = new ImageView(image);
        // photoCin.setGraphic(imageView);
        NometPrenom.setText(i.getNom_prenom());
        Email.setText(i.getEmail());
        benificaire.setText(i.getBeneficiaire());
        tel.setText(String.valueOf(i.getTelephone()));
        Montant.setText(String.valueOf(i.getMontant()));

        if ("Approuvé".equals(cheque.getDecision()) || "Refuser".equals(cheque.getDecision())) {
            disableDecisionButtons();
        }
    }

    @FXML
    private void ApprouverCheque(MouseEvent event) {
        if (showConfirmationDialog("Approve", "Voullez vous Approuvez ce chéque ?")) {
            updateChequeDecision("Approuvé");
        }
    }

    @FXML
    private void RefuserCheque(MouseEvent event) {
        if (showConfirmationDialog("Refus", "Voulez vous réfuser ce Chéque?")) {
            updateChequeDecision("Refuser");
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

    private void updateChequeDecision(String newDecision) {
        try {
            cheque.setDecision(newDecision);
            new ServiceCheque().modifier(cheque);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Cheque decision updated to " + newDecision + ".");
            disableDecisionButtons();
            applyRejectedStyle();

            ListeChequeAdmin.getInstance().refreshChequeList();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update cheque decision.");
            e.printStackTrace();
        }
    }

    private void disableDecisionButtons() {
        ApprouveBtnC.setDisable(true);
        RefusBtnC.setDisable(true);
        Approuver.setOpacity(0.4);  // Optionally set opacity to visually indicate the button is disabled
        Refuser.setOpacity(0.4);    // Optionally set opacity to visually indicate the button is disabled
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void applyRejectedStyle() {
        if ("Refuser".equals(cheque.getDecision())) {
            Hboxcheque.getStyleClass().clear();
            Hboxcheque.getStyleClass().add("cheque-rejected");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        photoCin.setOnMouseClicked(this::openCINImage);


    }

    public void openCINImage(MouseEvent mouseEvent) {
        String imagePath = cheque.getPhoto_cin(); // Récupérer le chemin de l'image à partir de l'objet Cheque
        File file = new File(imagePath);

        try {
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("Fichier non trouvé : " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
