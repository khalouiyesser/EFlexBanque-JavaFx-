package controllers.Virement;

import Entities.Virement;
import Entities.Virement;
import controllers.Cheque.DemandeChequeListClient;
import controllers.Virement.DemandeVirementListClient;
import controllers.Virement.updateVirementCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import services.ServiceVirement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.UUID;

public class updateVirementCard {

    @FXML
    private TextField CIN;

    @FXML
    private TextField NometPrenom;

    @FXML
    private TextField NumBenef;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private ImageView imageInput;

    @FXML
    private TextField montant;

    @FXML
    private TextField tel;

    @FXML
    private TextField transferezA;

    @FXML
    private ComboBox<String> typeVirement;

    @FXML
    private Button update_Btn;
    Virement virementToUpdate;
    public static String FxmlToLoad;
    private String imageName = null;
    private File selectedImageFile;
    private EventObject event;

    public static String getFxmlToLoad() {
        return FxmlToLoad;
    }
    public static void setFxmlToLoad(String FxmlToLoad) {
        updateVirementCard.FxmlToLoad = FxmlToLoad;
    }

    public void ImporterImageV(ActionEvent event) {
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
    public void setProjectUpdateData(Virement virement) {
        virementToUpdate = virement;
        CIN.setText(String.valueOf(virement.getCin()));
        NometPrenom.setText(virement.getNomet_prenom());
        Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", virementToUpdate.getPhoto_cin_v());
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
        tel.setText(virement.getPhone_number());
        typeVirement.setValue(String.valueOf(virement.getType_virement()));
        transferezA.setText(virement.getTransferez_a());
        NumBenef.setText(String.valueOf(virement.getNum_beneficiare()));
        montant.setText(String.valueOf(virement.getMontant()));
    }

    @FXML
    void ajouter_image(MouseEvent event) {

    }


    public void updateVirement(MouseEvent mouseEvent) {
        ServiceVirement serviceVirement = new ServiceVirement();

        virementToUpdate.setCin(Integer.parseInt(CIN.getText()));
        virementToUpdate.setNomet_prenom(NometPrenom.getText());
        String imageURL = selectedImageFile.toURI().toString();
        virementToUpdate.setPhoto_cin_v(imageURL);
        virementToUpdate.setPhoto_cin_v(String.valueOf(imageInput));
        virementToUpdate.setPhone_number(String.valueOf(Integer.parseInt(tel.getText())));
        virementToUpdate.setType_virement(typeVirement.getValue());
        virementToUpdate.setTransferez_a(transferezA.getText());
        virementToUpdate.setNum_beneficiare(Integer.parseInt(NumBenef.getText()));
        virementToUpdate.setMontant(montant.getText());


        try {
            serviceVirement.modifier(virementToUpdate);
            DemandeVirementListClient.setUpdateVirementModelShow(0);
            DemandeVirementListClient.setUpdateVirementModelShow(0);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeVirementListClient.fxml"));
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


}

