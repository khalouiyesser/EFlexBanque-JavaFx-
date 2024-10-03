package controllers.user;
import javafx.scene.control.Alert;
import org.w3c.dom.Text;
import services.ServiceUser;
import Entities.User;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;



public class ForgotPassword3Controller implements Initializable {

    @FXML
    private Button confirmBTN;

    @FXML
    private AnchorPane left;

    @FXML
    private Hyperlink logInLink;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField rePasswordField;
    @FXML
    private Text txt;
    public static String userEmail;


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



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void SubmitMdp(MouseEvent event)  throws IOException, SQLException {
        Mailling sn = new Mailling();
        String Obj = "Reset Password";
        // Récupérer l'e-mail de l'utilisateur actuellement connecté
        //  String userEmail = UserSession.getInstance().getEmail();
        String Subject = "Bonjour " + userEmail + " Votre mot de passe a été modifié avec succès";
        ServiceUser uss = new ServiceUser();

        if (passwordField.getText().isEmpty() || rePasswordField.getText().isEmpty()) {
            showAlert("Champ Manquant", "Veuillez remplir tous les champs.");
        } else if (!passwordField.getText().equals(rePasswordField.getText())) {
            showAlert("Mot de passe non compatible", "Les mots de passe ne correspondent pas.");
        } else {
            uss.modifierMdp(userEmail,new User(userEmail, rePasswordField.getText()));
            showAlert("Mot de passe modifié", "Votre mot de passe a été modifié avec succès.");
            // System.out.println("hedha userEmail" + userEmail);
            System.out.println(userEmail);
            sn.envoyer(userEmail, Obj, Subject);

            Parent page2 = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        }

    }
}
