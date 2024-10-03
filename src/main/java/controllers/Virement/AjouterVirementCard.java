package controllers.Virement;

import Entities.Cheque;
import Entities.User;
import Entities.Virement;
import controllers.Cheque.AjouterChequeCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
//import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import services.ServiceCheque;
import services.ServiceVirement;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import javafx.stage.FileChooser.ExtensionFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ValidSaisie;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import static services.ValidSaisie.isValidNumB;

public class AjouterVirementCard implements Initializable {

    @FXML
    private TextField Cin;

    @FXML
    private TextField NometPrenom;

    @FXML
    private Text NometPrenomInputError;

    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private TextField Num;

    @FXML
    private Text NumInputError;

    @FXML
    private HBox NumInputErrorHbox;

    @FXML
    private TextField RIB;

    @FXML
    private Button ajouterVirement;

    @FXML
    private TextField benef;
    @FXML
    private Pane content_areaV;

    @FXML
    private Text benefInputError;

    @FXML
    private HBox benefInputErrorHbox;

    @FXML
    private HBox beneficiaireInputErrorHbox;
    @FXML
    private VBox content_area;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text cinInputError;

    @FXML
    private HBox cinInputErrorHbox;

    @FXML
    private ImageView imageInput;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private Button imp;

    @FXML
    private TextField montant;

    @FXML
    private Text montantInputError;

    @FXML
    private HBox montantInputErrorHbox;

    @FXML
    private TextField transferez;

    @FXML
    private Text transferezInputError;

    @FXML
    private HBox transferezInputErrorHbox;

    @FXML
    private ComboBox<String> type;

    @FXML
    private Text typeInputError;
    private File selectedImageFile;
    private String imageName = null;
    public static User user;
    public static BigInteger rib;

    public static void setRib(BigInteger rib) {
        AjouterVirementCard.rib = user.getRib();
    }
    public static BigInteger getRib() {
        return rib;
    }



    @FXML
    public void ajouterVirement(MouseEvent mouseEvent) throws SQLException, IOException {

        ServiceVirement sv = new ServiceVirement();
        boolean champsInvalides = false;

        if (Cin.getText().isEmpty() || !ValidSaisie.isValidCin(Cin.getText())) {
            cinInputErrorHbox.setVisible(true);
            cinInputError.setText("Le numéro de cin doit contenir 8 chiffres qui commence par 0 ou 1");
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            cinInputErrorHbox.setVisible(false);
        }

        // Vérification du nom et prénom
        if (NometPrenom.getText().isEmpty()) {
            NometPrenomInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            NometPrenomInputErrorHbox.setVisible(false);
        }

        // Vérification du numéro de téléphone
        if (Num.getText().isEmpty() || !ValidSaisie.isValidNumber(Num.getText())) {
            NumInputErrorHbox.setVisible(true);
            NumInputError.setText("Le numéro de téléphone doit commencer par 2 ou 5 ou 9 et contenir 8 chiffres");
            champsInvalides = true;
        } else {
            NumInputErrorHbox.setVisible(false);
        }

        // Vérification du type de virement
        if (type.getSelectionModel().isEmpty()) {
            beneficiaireInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            beneficiaireInputErrorHbox.setVisible(false);
        }

        // Vérification du montant
        if (montant.getText().isEmpty()) {
            montantInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            montantInputErrorHbox.setVisible(false);
        }

        // Vérification du champ transferez
        if (transferez.getText().isEmpty()) {
            transferezInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            transferezInputErrorHbox.setVisible(false);
        }

        // Vérification du champ benef
        if (benef.getText().isEmpty()) {
            benefInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            benefInputErrorHbox.setVisible(false);
        }

        // Vérification si au moins un champ est invalide
        if (champsInvalides) {
            return; // Arrêter le traitement si des champs sont invalides
        }


        if (Cin.getText().isEmpty()) {
            cinInputErrorHbox.setVisible(true);
            if (NometPrenom.getText().isEmpty()) {
                NometPrenomInputErrorHbox.setVisible(true);
                if (Num.getText().isEmpty()) {
                    NumInputErrorHbox.setVisible(true);
                    if (type.getSelectionModel().isEmpty()) {
                        beneficiaireInputErrorHbox.setVisible(true);
                        if (transferez.getText().isEmpty()) {
                            transferezInputErrorHbox.setVisible(true);
                            if (benef.getText().isEmpty()) {
                                benefInputErrorHbox.setVisible(true);
                                if (montant.getText().isEmpty()) {
                                    montantInputErrorHbox.setVisible(true);
                                }
                            }
                        }
                    }
                } else {
                    if (Num.getText().isEmpty()) {
                        NumInputErrorHbox.setVisible(true);
                        if (type.getSelectionModel().isEmpty()) {
                            beneficiaireInputErrorHbox.setVisible(true);
                            if (transferez.getText().isEmpty()) {
                                transferezInputErrorHbox.setVisible(true);
                                if (benef.getText().isEmpty()) {
                                    benefInputErrorHbox.setVisible(true);
                                    if (montant.getText().isEmpty()) {
                                        montantInputErrorHbox.setVisible(true);
                                    }

                                }
                            }
                        }

                    } else {
                        if (type.getSelectionModel().isEmpty()) {
                            beneficiaireInputErrorHbox.setVisible(true);
                            if (transferez.getText().isEmpty()) {
                                transferezInputErrorHbox.setVisible(true);
                                if (benef.getText().isEmpty()) {
                                    benefInputErrorHbox.setVisible(true);
                                    if (montant.getText().isEmpty()) {
                                        montantInputErrorHbox.setVisible(true);
                                    }
                                }
                            } else {
                                if (transferez.getText().isEmpty()) {
                                    transferezInputErrorHbox.setVisible(true);
                                    if (benef.getText().isEmpty()) {
                                        benefInputErrorHbox.setVisible(true);
                                    } else {
                                        if (montant.getText().isEmpty()) {
                                            montantInputErrorHbox.setVisible(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else {
            if (!Cin.getText().isEmpty()) {
                cinInputErrorHbox.setVisible(false);
                if (!NometPrenom.getText().isEmpty()) {
                    NometPrenomInputErrorHbox.setVisible(false);
                    if (!Num.getText().isEmpty()) {
                        NumInputErrorHbox.setVisible(false);
                        if (!type.getSelectionModel().isEmpty()) {
                            beneficiaireInputErrorHbox.setVisible(false);
                            if (!transferez.getText().isEmpty()) {
                                transferezInputErrorHbox.setVisible(false);
                                if (!benef.getText().isEmpty()) {
                                    benefInputErrorHbox.setVisible(false);
                                    if (!montant.getText().isEmpty()) {
                                        montantInputErrorHbox.setVisible(false);
                                    }

                                }
                            }
                        }
                    }
                }
            }


        }
        // Select for combo
        SingleSelectionModel<String> selectionModel = type.getSelectionModel();

        String selectedType = selectionModel.getSelectedItem();
        // Date Now
        LocalDate selectedDate = LocalDate.now();
        // Create a new instance of cheque from View
        String image = imageName;
        Integer Rib = 345678644;
        String type_virement = selectedType;
        System.out.println(type_virement);
        String transf = transferez.getText();
        String benefr = benef.getText();
        String montantv = montant.getText();
        String nom_prenom = NometPrenom.getText();
        int cin;
        try {
            cin = Integer.parseInt(Cin.getText());
        } catch (NumberFormatException e) {
            // Handle the case where Cin is not a valid integer
            // You can show an error message or handle it as per your application logic
            e.printStackTrace(); // Print the stack trace for debugging
            return; // Exit the method if the Cin value is not valid
        }
        String phone_number = Num.getText();
        String decision = "encours";

        // change the date to sqlDate
        Date sqlDate = java.sql.Date.valueOf(selectedDate);
        String virementData =  "Type: " + type_virement + "\n" +
                "Transferez à: " + transf + "\n" +
                "Benef: " + benefr + "\n" +
                "Montant: " + montantv + "\n" +
                "Nom & Prenom: " + nom_prenom + "\n" +
                "CIN: " + cin + "\n" +
                "Numéro de téléphone: " + phone_number;

        String base64QRCode = generateBase64QRCode(virementData);

        //String nomet_prenom, String type_virement, String transferez_a, int num_beneficiare, String montant, int cin, int rib, String decision_v, String photo_cin_v, String phone_number
        Virement virement1 = new Virement(nom_prenom, type_virement, transf, Integer.parseInt(benefr), montantv, cin, new BigDecimal(Rib), decision, imageName, phone_number);
        virement1.setQrCode(base64QRCode);
        ServiceVirement serviceVirement = new ServiceVirement();
        serviceVirement.ajouterV(virement1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeVirementListClient.fxml"));
        Pane demandeVirement = loader.load();

        // Remplacer le contenu de content_area par le contenu de la liste des demandes de chèques
        content_area.getChildren().setAll(demandeVirement);
    }
    private String generateBase64QRCode(String data) {
        ByteArrayOutputStream stream = QRCode.from(data).to(ImageType.JPG).stream();
        byte[] byteArray = stream.toByteArray();
        return Base64.getEncoder().encodeToString(byteArray);
    }

    private void resetFields() {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(user);
        imageInputErrorHbox.setVisible(false);
        NumInputErrorHbox.setVisible(false);
        montantInputErrorHbox.setVisible(false);
        transferezInputErrorHbox.setVisible(false);
        cinInputErrorHbox.setVisible(false);
        NometPrenomInputErrorHbox.setVisible(false);
        beneficiaireInputErrorHbox.setVisible(false);
        benefInputErrorHbox.setVisible(false);
        RIB.setText(String.valueOf(user.getRib()));


        ObservableList<String> types = FXCollections.observableArrayList(
                "Personne",
                "VEcoresponsabilité"
        );
        type.setItems(types);


    }

//    private void hideErrorMessages() {
//        NometPrenomInputError.setVisible(false);
//        typeInputError.setVisible(false);
//        NumInputError.setVisible(false);
//        benefInputError.setVisible(false);
//        montantInputError.setVisible(false);
//        cinInputError.setVisible(false);
//        transferezInputError.setVisible(false);
//
//    }


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

    public void retourBackDash(MouseEvent mouseEvent) {

        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeVirementListClient.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}