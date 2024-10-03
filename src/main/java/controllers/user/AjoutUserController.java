package controllers.user;

import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceUser;

import javax.management.relation.Role;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.fxml.Initializable;

import java.io.File;

public class AjoutUserController implements Initializable {

    @FXML
    private Button ChequeImg;

    @FXML
    private Button Retour;

    @FXML
    private VBox content_area;

    @FXML
    private TextField Cin;

    @FXML
    private TextField Email;

    @FXML
    private Text EmailInputError;

    @FXML
    private HBox EmailInputErrorHbox;

    @FXML
    private Text NometPrenomInputError;

    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private Button add_new_tBtn;

    @FXML
    private TextField adresse;

    @FXML
    private Text beneficiaireInputError;

    @FXML
    private HBox beneficiaireInputErrorHbox;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text cinInputError;

    @FXML
    private HBox cinInputErrorHbox;



    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;



    @FXML
    private PasswordField montant;

    @FXML
    private Text montantInputError;

    @FXML
    private HBox montantInputErrorHbox;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> role;

    @FXML
    private TextField tel;

    @FXML
    private Text telInputError;

    @FXML
    private HBox telInputErrorHbox;
    @FXML
    private Text NameinputError;
    @FXML
    private HBox nameInputErrorHbox;
    String fileName = null;
    File selectedImageFile;
    @FXML
    private void uploadImage()  {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);
        }
        fileName = selectedImageFile.toURI().toString();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NometPrenomInputError.setVisible(false);
        imageInputError.setVisible(false);
        EmailInputError.setVisible(false);
        montantInputError.setVisible(false);
        cinInputError.setVisible(false);
        beneficiaireInputError.setVisible(false);
        NameinputError.setVisible(false);
        telInputError.setVisible(false);


        ObservableList<String> roles = FXCollections.observableArrayList(
                "ROLE_CLIENT",
                "ROLE_EMPLOYEE"

        );
        role.setItems(roles);


    }



    public void AjouterUser(ActionEvent actionEvent) throws IOException, SQLException {
        ServiceUser sa = new ServiceUser();
//
        boolean champsVides = false;
        if (name.getText().isEmpty()) {
            NameinputError.setVisible(true);
            champsVides = true;
        } else {
            nameInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }
        if (adresse.getText().isEmpty()) {
            NometPrenomInputError.setVisible(true);
            champsVides = true;
        } else {
            NometPrenomInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }
        if (montant.getText().isEmpty()) {
            montantInputError.setVisible(true);
            champsVides = true;
        } else {
            montantInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        if (role.getSelectionModel().isEmpty()) {
            beneficiaireInputError.setVisible(true);
            champsVides = true;
        } else {
            beneficiaireInputError.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }
        if (imageInput.getImage() == null) {
            imageInputError.setVisible(true);
            champsVides = true;
        } else {
            imageInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        if (Cin.getText().isEmpty()) {
            cinInputError.setVisible(true);
            champsVides = true;
        } else {
            cinInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }
        if (Email.getText().isEmpty()) {
            EmailInputError.setVisible(true);
            champsVides = true;
        } else {
            EmailInputErrorHbox.setVisible(false);
        }// Masquer le message d'erreur si le champ est rempli
        if (tel.getText().isEmpty()) {
            telInputError.setVisible(true);
            champsVides = true;
        }
        else {
            telInputErrorHbox.setVisible(false);}
        // Masquer le message d'erreur si le champ est rempli


// Si au moins un champ est vide, afficher les messages d'erreur
        if (champsVides) {
            return;
        }
        ServiceUser serviceUser = new ServiceUser();

        String image = imageInput.getImage().getUrl();
        String selectedCategory = role.getSelectionModel().getSelectedItem();
        User user = new User(name.getText(), Cin.getText(), fileName, adresse.getText(), Email.getText(), selectedCategory, tel.getText(), montant.getText());
        serviceUser.ajouter(user);
        // if (sa.ajouter(user)) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UsersList.fxml"));
        Pane listArtAdminPane = loader.load();

        // Remplacer le contenu de content_area par le contenu de listArticleAdmin
        content_area.getChildren().setAll(listArtAdminPane);

        //}

    }
    @FXML
    void TOUser(MouseEvent event) {


    }

    public void RetourBack(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UsersList.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
