package controllers.user;
import javafx.scene.control.Alert;
import services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Entities.Reset;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgotPassword2Conroller  implements Initializable{

    @FXML
    private TextField codeField;

    @FXML
    private Button confirmBTN;

    @FXML
    private AnchorPane left;

    @FXML
    private Hyperlink logInLink;
    public String email;

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


    @FXML
    void Verif(MouseEvent event) throws IOException {
        ServiceUser uss = new ServiceUser();
        String userEmail = UserSession.getInstance().getEmail();
        if (codeField.getText().isEmpty()) {
            showAlert("Champs manquants", "Veuillez entrer le code de vérification.");
        } else if (uss.reset(new Reset(Integer.parseInt(codeField.getText())))) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForgotPassword_3.fxml"));
            Parent root = loader.load();
            codeField.getScene().setRoot(root);

            ForgotPassword2Conroller apc = loader.getController();
            // Passer l'e-mail à l'autre contrôleur
            apc.setTxt1(email);

        } else {
            showAlert("Erreur", "Une erreur est survenue lors de la réinitialisation du mot de passe.");
        }
    }

    private void setTxt1(String email) {
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}