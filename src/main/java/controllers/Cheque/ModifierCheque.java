package controllers.Cheque;

import Entities.Cheque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCheque;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

public class ModifierCheque implements Initializable {

    @FXML
    private Pane content_areah;
    @FXML
    private TextField Cin;

    @FXML
    private TextField Email;

    @FXML
    private Text EmailInputError;

    @FXML
    private HBox EmailInputErrorHbox;

    @FXML
    private TextField NometPrenom;

    @FXML
    private Text NometPrenomInputError;

    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private TextField RIB;

    @FXML
    private ComboBox<String> beneficiaire;

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
    private Text DateInputError;

    @FXML
    private HBox DateInputErrorHbox;
    @FXML
    private DatePicker date;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private TextField montant;

    @FXML
    private Text montantInputError;

    @FXML
    private HBox montantInputErrorHbox;

    @FXML
    private TextField tel;

    @FXML
    private Text telInputError;

    @FXML
    private HBox telInputErrorHbox;

    @FXML
    private Button update_chequetBtn;
    private Cheque cheque;
    Image selectedCvFile;
    private String imageName = null;

    private File selectedImageFile;

    @FXML
    private HBox updateBtnContainer;
    @FXML
    private Button update_Btn;

    @FXML
    private HBox addReviewsModel;
    private Object value;
    private Object Collecte;
    public static int idy;

    @FXML
    void ajouter_image(MouseEvent event) {

    }
    @FXML
    void ImporterImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", imageName);
            try {
                Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("non");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        telInputErrorHbox.setVisible(false);
        montantInputErrorHbox.setVisible(false);
        EmailInputErrorHbox.setVisible(false);
        cinInputErrorHbox.setVisible(false);
        NometPrenomInputErrorHbox.setVisible(false);
        beneficiaireInputErrorHbox.setVisible(false);
        DateInputErrorHbox.setVisible(false);

        ObservableList<String> beneficiaires = FXCollections.observableArrayList(
                "Paiement",
                "PaiementEco",
                "Personne"
        );

        beneficiaire.setItems(beneficiaires);


    }

    public void edit(String text) {
        ServiceCheque serviceCheque = new ServiceCheque();
    }

    public void initData(Cheque cheque) {

        NometPrenom.setText(cheque.getNom_prenom());
        Email.setText(cheque.getEmail());
        date.setValue(cheque.getDate().toLocalDate());
        beneficiaire.setValue(cheque.getBeneficiaire());
        tel.setText(String.valueOf(cheque.getTelephone()));
        montant.setText(String.valueOf(cheque.getMontant()));
        Cin.setText(String.valueOf(cheque.getCin()));
        if (cheque.getPhoto_cin() != null && !cheque.getPhoto_cin().isEmpty()) {
            Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", cheque.getPhoto_cin());
            if (Files.exists(destination)) {
                try {
                    Image image = new Image(destination.toUri().toString());
                    imageInput.setImage(image);
                } catch (Exception e) {
                    System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
                    // Gérer l'erreur de chargement de l'image ici
                }
            } else {
                System.err.println("Le fichier image n'existe pas : " + destination);
                // Gérer l'absence du fichier image ici
            }
        }
    }

    private void initializeChequeFields() {
        if (cheque != null) {
            this.Cin.setText(String.valueOf(cheque.getCin()));
            NometPrenom.setText(cheque.getNom_prenom());
            Email.setText(cheque.getEmail());
            date.setValue(cheque.getDate().toLocalDate());
            beneficiaire.setValue(cheque.getBeneficiaire());
            tel.setText(String.valueOf(cheque.getTelephone()));
            montant.setText(String.valueOf(cheque.getMontant()));
            // Assurez-vous que l'image de l'article n'est pas vide
            if (cheque.getPhoto_cin() != null && !cheque.getPhoto_cin().isEmpty()) {
                Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", cheque.getPhoto_cin());
                if (Files.exists(destination)) {
                    try {
                        Image image = new Image(destination.toUri().toString());
                        imageInput.setImage(image);
                    } catch (Exception e) {
                        System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
                        // Gérer l'erreur de chargement de l'image ici
                    }
                } else {
                    System.err.println("Le fichier image n'existe pas : " + destination);
                    // Gérer l'absence du fichier image ici
                }
            }

        }
    }

    public void UpdateCheque(MouseEvent mouseEvent) {
        try {
            ServiceCheque serviceCheque = new ServiceCheque();

            //System.out.println(cheque);
            Cheque cheque = serviceCheque.getById(idy);
            if (cheque != null) {
                System.out.println(cheque.getTelephone());
                cheque.setBeneficiaire(beneficiaire.getValue().toString());
                cheque.setMontant(Double.parseDouble(montant.getText()));
                cheque.setTelephone(Integer.parseInt(tel.getText()));
                if (date.getValue() != null) {
                    cheque.setDate(Date.valueOf(date.getValue()));
                }
                // Assurez-vous que l'image du chèque n'est pas vide
                if (selectedImageFile != null) {
                    // Utilisez l'URI du fichier sélectionné pour obtenir l'URL de l'image
                    String imageURL = selectedImageFile.toURI().toString();
                    cheque.setPhoto_cin(imageURL);
                }
                // Appeler la méthode de service pour effectuer la mise à jour du chèque dans la base de données
               // ServiceCheque serviceCheque = new ServiceCheque();
                serviceCheque.modifier(cheque);

                // Fermer la fenêtre après la mise à jour
                Stage stage = (Stage) update_Btn.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }


    }
}

