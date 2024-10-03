package controllers.user;

import Entities.User;
import controllers.Credit.GMailer;
import javafx.fxml.Initializable;
import services.Symfony;
import services.imResetPassword;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.ServiceUser;
import Entities.Reset;

import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import javax.mail.MessagingException;

public class ForgotPasswordController implements Initializable {
    @FXML
    private TextField emailField;

    @FXML
    private AnchorPane left;

    @FXML
    private Hyperlink logInLink;

    @FXML
    private Button sendBTN;
    Random rnd = new Random();
    int number = rnd.nextInt(999999);
    long start = System.currentTimeMillis();
    String sTime = Long.toString(start);
    public static String sEmail;
    String Object="Réinitialiser Votre mot de passe";
    String Subject="Votre Code est :  "+number+"\n S'il te plait ne passe pas 10 min De maintenant";


    public void sendClicked(ActionEvent actionEvent) throws IOException, GeneralSecurityException {
        System.out.println("1"+emailField.getText());
        Symfony symfony = new Symfony();
//        symfony.MdpOblie(emailField.getText());
//        System.out.println(emailField);
        ServiceUser userService = new ServiceUser();
//        GMailer gMailer = new GMailer();

        try {
            // Récupérer l'utilisateur à partir de l'adresse e-mail saisie
            User user = userService.getOneUser(emailField.getText());
            ForgotPassword3Controller forgotPassword3Controller = new ForgotPassword3Controller();
            forgotPassword3Controller.userEmail = emailField.getText();

            // Vérifier si l'utilisateur existe et si son adresse e-mail est valide
            if (user == null || !UserControleSaisie.forgetPasswordValidator(emailField.getText(), user)) {
                // Afficher un message d'erreur si l'utilisateur n'existe pas ou si l'adresse e-mail est invalide
                showAlert("Utilisateur non trouvé", "L'utilisateur n'existe pas ou l'adresse e-mail est invalide");
                return;
            }

            imResetPassword imr = new imResetPassword();
//            Mailling sn = new Mailling();
            if (emailField.getText().isEmpty()) {
                showAlert("Champs manquants", "Veuillez saisir une adresse e-mail.");
            } else if (imr.ajout(new Reset(emailField.getText(), number, sTime))) {
                sEmail = emailField.getText();
                System.out.println(emailField.getText());
                symfony.MdpOblie(emailField.getText(),number);
//                gMailer.sendMailMimi(emailField.getText(),Object, Subject);
                Parent page2 = FXMLLoader.load(getClass().getResource("/FXML/ForgotPassword_2.fxml"));

                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();
            } else {
                showAlert("Compte n'existe pas", "Le compte n'existe pas.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Méthode pour générer un nouveau mot de passe aléatoire
    private String generateRandomPassword() {
        // Générer un mot de passe aléatoire de 8 caractères
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    // Méthode pour afficher une boîte de dialogue d'alerte
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML


    public void toLogin(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}