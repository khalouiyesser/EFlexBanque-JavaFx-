package controllers.Cheque;

import Entities.Cheque;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

public class updateChequeCard  {

    @FXML
    private TextField Cin;

    @FXML
    private TextField Email;

    @FXML
    private TextField NometPrenom;

    @FXML
    private TextField RIB;

    @FXML
    private ComboBox<String> beneficiaire;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private DatePicker date;
    private File selectedImageFile;
    @FXML
    private ImageView imageInput;

    @FXML
    private TextField montant;

    @FXML
    private TextField tel;

    @FXML
    private Button update_Btn;

    Cheque chequeToUpdate;
    public static String FxmlToLoad;
    private String imageName = null;
    public static String getFxmlToLoad() {
        return FxmlToLoad;
    }
    public static void setFxmlToLoad(String FxmlToLoad) {
        updateChequeCard.FxmlToLoad = FxmlToLoad;
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
            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", imageName);
            try {
                Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("non");
            }
        }
    }
    public void setProjectUpdateData(Cheque cheque) {
        chequeToUpdate = cheque;
        Cin.setText(String.valueOf(cheque.getCin()));
        NometPrenom.setText(cheque.getNom_prenom());
        Email.setText(cheque.getEmail());
        tel.setText(String.valueOf(cheque.getTelephone()));
        date.setValue(LocalDate.parse(String.valueOf(cheque.getDate())));
        beneficiaire.setValue(cheque.getBeneficiaire());
        montant.setText(String.valueOf(cheque.getMontant()));
        Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", chequeToUpdate.getPhoto_cin());
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
        //Image image = new Image(getClass().getResource("/assets/projectUploads/" + cheque.getImg()).toExternalForm());
        //imageInput.setImage(image);
        //imageName = project.getImg();
    }



    @FXML
    void updateCheque(MouseEvent event) {
        ServiceCheque serviceCheque = new ServiceCheque();

        chequeToUpdate.setCin(Integer.parseInt(Cin.getText()));
        chequeToUpdate.setNom_prenom(NometPrenom.getText());
        chequeToUpdate.setEmail(Email.getText());
        chequeToUpdate.setTelephone(Integer.parseInt(tel.getText()));
        chequeToUpdate.setDate(Date.valueOf(date.getValue()));
        chequeToUpdate.setBeneficiaire(beneficiaire.getValue().toString());
        chequeToUpdate.setMontant(Double.parseDouble(montant.getText()));
        String imageURL = selectedImageFile.toURI().toString();
        chequeToUpdate.setPhoto_cin(imageURL);
        chequeToUpdate.setPhoto_cin(String.valueOf(imageInput));
        try {
            serviceCheque.modifier(chequeToUpdate);
            DemandeChequeListClient.setupdateChequeModelShow(0);
            DemandeChequeListClient.setupdateChequeModelShow(0);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
            try {
                Parent root = loader.load();

                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void ajouter_image(MouseEvent event) {

    }

}
