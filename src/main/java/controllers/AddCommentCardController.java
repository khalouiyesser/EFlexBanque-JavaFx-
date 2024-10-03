package controllers;

import Entities.Commentaire;
import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import services.IService;
import services.ServiceCommentaire;
import services.ServiceEvenement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;


public class AddCommentCardController implements Initializable {
    @FXML
    public TextField fxNom;
    @FXML
    private TextField fxDesc;
    @FXML
    private DatePicker fxdateDebut;
    @FXML
    private DatePicker fxdateFin;
    @FXML
    private TextField fxLieu;

    @FXML
    private TextField nameInput;
    @FXML
    private TextArea ContenuInput;
    @FXML
    private TextArea descriptionInput;

    @FXML
    private TextField prixInput;
    @FXML
    private Button add_new_EvenementBtn;

    @FXML
    private Button update_EvenementBtn;

    @FXML
    private ImageView imageInput;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text nameInputError;

    @FXML
    private Text descriptionInputError;

    @FXML
    private Text categoryInputError;

    @FXML
    private Text numberInputError;

    @FXML
    private Text priceInputError;

    @FXML
    private Text pointsInputError;

    @FXML
    private Text photoInputError;

    @FXML
    private HBox categoryInputErrorHbox;

    @FXML
    private HBox numberInputErrorHbox;

    @FXML
    private HBox priceInputErrorHbox;

    @FXML
    private HBox pointsInputErrorHbox;

    @FXML
    private HBox photoInputErrorHbox;
    @FXML
    private ComboBox<String> fxProjectName;


    private int categId = -1;
    private File selectedImageFile;
    private String imageName = null;
    private int nomTest = 0;
    private int descriptionTest = 0;
    private int categoryTest = 0;
    private int numberTest = 0;
    private int priceTest = 0;
    private int pointsTest = 0;
    private int photoTest = 0;
    private String etiquette = null;

    private double score;


    @FXML
    private HBox nameInputErrorHbox;
    @FXML
    private HBox fxdateDebutErrorHbox;
    @FXML
    private HBox fxdateFinErrorHbox;
    @FXML
    private HBox fxLieuErrorHbox;
    @FXML
    private HBox descriptionInputErrorHbox;
    @FXML
    private HBox prixInputErrorHbox;
    private int nameInputTest = 0;


    private int descriptionInputTest = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Evenement.actionTest);
//
//        nameInputErrorHbox.setVisible(false);
//        descriptionInputErrorHbox.setVisible(false);
//        prixInputErrorHbox.setVisible(false);
//        fxLieuErrorHbox.setVisible(false);
//        fxdateFinErrorHbox.setVisible(false);
//        fxdateDebutErrorHbox.setVisible(false);
//        if (Evenement.actionTest == 0) {
//            update_EvenementBtn.setVisible(false);
//        } else {
//            add_new_EvenementBtn.setVisible(false);
//            IService evenementService = new ServiceEvenement();
//            Evenement e1 = new Evenement();
//            try {
//                e1 = evenementService.getOneEvenement(Evenement.getIdEvenement());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            nameInput.setText(e1.getNom());
//            fxLieu.setText(e1.getLieu());
//            descriptionInput.setText(e1.getDescription());
//            prixInput.setText(String.valueOf(e1.getPrix()));
//            LocalDateTime dateDebut = e1.getDateDebut();
//            fxdateDebut.setValue(dateDebut != null ? dateDebut.toLocalDate() : null);
//            LocalDateTime dateFin = e1.getDateFin();
//            fxdateFin.setValue(dateFin != null ? dateFin.toLocalDate() : null);
//            descriptionInput.setText(e1.getDescription());
//            if (e1.getImg() != null) {
//                Image image = new Image(getClass().getResource("/assets/ProductUploads/" + e1.getImg()).toExternalForm());
//                imageInput.setImage(image);
//            } else {
//                Image image = new Image(getClass().getResource("/assets/ProductUploads/" + "pngwing1.com.png").toExternalForm());
//                imageInput.setImage(image);
//            }
//            imageName = e1.getImg();
//        }
//        nameInputTest = 1;
//        fxdateDebutTest = 1;
//        fxdateFinTest = 1;
//        fxLieuTest = 1;
//        descriptionInputTest = 1;
//        prixInputTest = 1;
    }

    @FXML
    void addNewComments(MouseEvent event) throws SQLException {
        Commentaire commentaire = new Commentaire();
        commentaire.setNomuser(nameInput.getText());
        commentaire.setContenu(ContenuInput.getText());
     //   commentaire.setContenu("bitch");
        commentaire.setEvenement_id(Evenement.getIdEvenement());
        IService serviceCommentaire = new ServiceCommentaire();
        try {
            serviceCommentaire.ajouter(commentaire);
            showNotification("Commentaire ", "Commentaire  ajouté avec succès.", NotificationType.SUCCESS);
            System.out.println("ID du Evenement : " + Evenement.getIdEvenement());
            Evenement.setIdEvenement(Evenement.getIdEvenement());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/ShowEvenementCard.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void nameTypedInput(KeyEvent event) {
        String nameText = ((TextField) event.getSource()).getText();
        if (!nameText.isEmpty()) {
            nameInputErrorHbox.setVisible(false);
            nomTest = 1;
        }
    }



    private void setEvenementFields(Evenement e) {

        nameInput.setText(e.getNom());
        fxLieu.setText(e.getLieu());
        descriptionInput.setText(e.getDescription());
        prixInput.setText(String.valueOf(e.getPrix()));
        LocalDateTime dateDebut = e.getDateDebut();
        fxdateDebut.setValue(dateDebut != null ? dateDebut.toLocalDate() : null);
        LocalDateTime dateFin = e.getDateFin();
        fxdateFin.setValue(dateFin != null ? dateFin.toLocalDate() : null);
        descriptionInput.setText(e.getDescription());
        if (e.getImg() != null) {
            Image image = new Image(getClass().getResource("/assets/ProductUploads/" + e.getImg()).toExternalForm());
            imageInput.setImage(image);
        } else {
            Image image = new Image(getClass().getResource("/assets/ProductUploads/" + "pngwing1.com.png").toExternalForm());
            imageInput.setImage(image);
        }
        imageName = e.getImg();
    }
    private void showNotification(String title, String message, NotificationType type) {
        TrayNotificationAlert.notif(title, message, type, AnimationType.POPUP, Duration.millis(2500));
    }

    private void switchToEvenementsList(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/ShowEvenementCardFront.fxml"));
        Parent root = loader.load();
        Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);
    }


    @FXML
    void ajouter_image(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);
            imageName = selectedImageFile.getName();
            Path destination = Paths.get(System.getProperty("user.dir"), "src", "assets", "ProductUploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            photoTest = 1;
            photoInputErrorHbox.setVisible(false);
        }
    }
    @FXML
    void updateEvenement(MouseEvent event) throws IOException, SQLException {
        Evenement evenement = new Evenement();
        ServiceEvenement eventService = new ServiceEvenement();
        evenement.setNom(nameInput.getText());
        evenement.setLieu(fxLieu.getText());
        evenement.setDescription(descriptionInput.getText());
        evenement.setDateDebut(fxdateDebut.getValue() != null ? fxdateDebut.getValue().atStartOfDay() : null);
        evenement.setDateFin(fxdateFin.getValue() != null ? fxdateFin.getValue().atStartOfDay() : null);
        evenement.setPrix(Double.parseDouble(prixInput.getText()));
        evenement.setImg(imageName);
        ServiceEvenement serviceEvenement = new ServiceEvenement();
        try {
            serviceEvenement.modifier(evenement);
            showNotification("Evenement", "Événement mis à jour avec succès.", NotificationType.SUCCESS);
            switchToEvenementsList(event);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/EvenementsList.fxml"));

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            showNotification("Erreur", "Format de date incorrect. Veuillez saisir les dates au format aaaa-MM-jj HH:mm:ss.", NotificationType.ERROR);
        }
    }
}
