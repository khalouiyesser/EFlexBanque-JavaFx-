package controllers.user;

import Entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;

public class EdituserProfilController {

    @FXML
    private TextField AdresseInput;

    @FXML
    private TextField CinInput;

    @FXML
    private HBox Close;

    @FXML
    private TextField TelInput;

    @FXML
    private HBox changePassModel;

    @FXML
    private Button confirmChangeBtn;

    @FXML
    private TextField fullnameinput;

    @FXML
    private Pane profilePane;

    private User user; // Ajout de la variable user

    // Méthode pour initialiser le profil de l'utilisateur avec ses données existantes
    public void initialize(User user) {
        this.user = user;
        // Remplir les champs avec les données de l'utilisateur existant
        fullnameinput.setText(user.getName());
        CinInput.setText(user.getCin());
        TelInput.setText(user.getTel());
        AdresseInput.setText(user.getAdresse());
    }

    @FXML
    void Close(MouseEvent event) {
        // Code pour fermer la fenêtre
    }

    @FXML
    void close_ChangePassModel(MouseEvent event) {
        // Code pour fermer le modèle de changement de mot de passe
    }

    @FXML
    void confirmChange(MouseEvent event) {
        ServiceUser serviceUser = new ServiceUser();

        // Vérifier si l'utilisateur est null et initialiser si nécessaire
        if (user == null) {
            user = new User();
        }

        // Mettre à jour les informations de l'utilisateur
        user.setName(fullnameinput.getText());
        user.setCin(CinInput.getText());
        user.setTel(TelInput.getText());
        user.setAdresse(AdresseInput.getText());

        try {
            // Mettre à jour l'utilisateur dans la base de données
            serviceUser.modifier(user);

            // Afficher un message d'alerte pour indiquer que la mise à jour du compte est réussie
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Account");
            alert.setHeaderText(null);
            alert.setContentText("Account updated successfully.");
            alert.showAndWait();

            // Recharger la vue de la liste des utilisateurs
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProfileUser.fxml"));
            Pane listArtAdminPane = loader.load();

            // Remplacer le contenu de profilePane par le contenu de listArtAdminPane
            profilePane.getChildren().setAll(listArtAdminPane);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }}