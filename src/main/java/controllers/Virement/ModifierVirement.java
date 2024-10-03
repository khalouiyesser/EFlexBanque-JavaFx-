package controllers.Virement;

import Entities.Cheque;
import Entities.Virement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCheque;
import services.ServiceVirement;

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

public class ModifierVirement implements Initializable {

    @FXML
    private TextField Cin;

    @FXML
    private TextField NometPrenom;

    @FXML
    private Button add_new_VirementtBtn;

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
    private TextField benef;

    @FXML
    private Text benefInputError;


    @FXML
    private Button ImporterImage;

    @FXML
    private HBox benefInputErrorHbox;

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
    private Button update_VirementtBtn;
    @FXML
    private Button updateVirement_Btn;

    private Virement virement;
    private File selectedImageFile;
    private String imageName;
    public  static int idV;




    public void initData(Virement virement){
        this.virement = virement;
        Cin.setText(String.valueOf(virement.getCin()));
        NometPrenom.setText(virement.getNomet_prenom());
        transferez.setText(virement.getTransferez_a());
        Num.setText(String.valueOf(virement.getPhone_number()));
        montant.setText(virement.getMontant());
        type.setValue(virement.getType_virement());
        benef.setText(String.valueOf(virement.getNum_beneficiare()));
        if (virement.getPhoto_cin_v() != null && !virement.getPhoto_cin_v().isEmpty()) {
            Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", virement.getPhoto_cin_v());
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
//        if (virement != null){
//            if (virement.getPhoto_cin_v() != null && !virement.getPhoto_cin_v().isEmpty()){
//                Image image =new Image(virement.getPhoto_cin_v());
//                imageInput.setImage(image);
//            }
//        }





    }
    public void initializeVirementFields(){
        if (virement != null){
            this.Cin.setText(String.valueOf(virement.getCin()));
            NometPrenom.setText(virement.getNomet_prenom());
            Num.setText(virement.getPhone_number());
            benef.setText(String.valueOf(virement.getNum_beneficiare()));
            type.setValue(virement.getType_virement());
            transferez.setText(virement.getTransferez_a());
            // Charger et afficher l'image si elle est déjà définie
            if (virement.getPhoto_cin_v() != null && !virement.getPhoto_cin_v().isEmpty()) {
                Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", virement.getPhoto_cin_v());
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

    @FXML
    void ajouter_imageV(MouseEvent event) {
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
                System.out.println();
            }
        }

    }

    public void updatevirement(MouseEvent mouseEvent) {
        try {
            ServiceVirement serviceVirement = new ServiceVirement();

            //System.out.println(cheque);
            Virement virement = serviceVirement.getById(idV);
            if (virement != null) {
                virement.setNomet_prenom(NometPrenom.getText());
                    virement.setType_virement(type.getSelectionModel().getSelectedItem());
                    virement.setTransferez_a(transferez.getText());
                    virement.setMontant(montant.getText());
                    virement.setPhone_number(Num.getText());
                    virement.setNum_beneficiare(Integer.parseInt(benef.getText()));
                    virement.setCin(Integer.parseInt(Cin.getText()));

                // Assurez-vous que l'image du chèque n'est pas vide
                if (selectedImageFile != null) {
                    // Utilisez l'URI du fichier sélectionné pour obtenir l'URL de l'image
                    String imageURL = selectedImageFile.toURI().toString();
                    virement.setPhoto_cin_v(imageURL);
                }
                // Appeler la méthode de service pour effectuer la mise à jour du chèque dans la base de données
                // ServiceCheque serviceCheque = new ServiceCheque();
                serviceVirement.modifier(virement);

                // Fermer la fenêtre après la mise à jour
                Stage stage = (Stage) updateVirement_Btn.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }


    }


//    @FXML
//    void updatevirement(MouseEvent mouseEvent) {
////        Virement virement = new Virement();
////        System.out.println("g");
//     //   System.out.println(virement);
//            try {
//                ServiceVirement serviceVirement = new ServiceVirement();
//                Virement virement1= serviceVirement.getById(idS);
////                if (virement != null) {
//                    virement.setNomet_prenom(NometPrenom.getText());
//                    virement.setType_virement(type.getSelectionModel().getSelectedItem());
//                    virement.setTransferez_a(transferez.getText());
//                    virement.setMontant(montant.getText());
//                    virement.setPhone_number(Num.getText());
//                    virement.setNum_beneficiare(Integer.parseInt(benef.getText()));
//                    virement.setCin(Integer.parseInt(Cin.getText()));
//
//                    // Assurez-vous que l'image du chèque n'est pas vide
//                // Assurez-vous que l'image du chèque n'est pas vide
//                if (imageInput.getImage() != null) {
//                    // Si une image a été sélectionnée dans imageInput
//
//                    String imageUrl = selectedImageFile.toURI().toString();
//                    virement.setPhoto_cin_v(imageUrl);
//                }
//
//                // Afficher les informations de virement
//               // System.out.println(virement);
//
//
//                    // Appeler la méthode de service pour effectuer la mise à jour du virement dans la base de données
//                    ServiceVirement serviceVirement = new ServiceVirement();
//                    serviceVirement.modifier(virement);
//
//                    // Fermer la fenêtre après la mise à jour
//                    Stage stage = (Stage) update_VirementtBtn.getScene().getWindow();
//                    stage.close();
//              //  }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Gérer l'exception appropriée ici
//            }
//        }
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        imageInputErrorHbox.setVisible(false);
        NumInputErrorHbox.setVisible(false);
        montantInputErrorHbox.setVisible(false);
        transferezInputErrorHbox.setVisible(false);
        cinInputErrorHbox.setVisible(false);
        NometPrenomInputErrorHbox.setVisible(false);
        beneficiaireInputErrorHbox.setVisible(false);
        benefInputErrorHbox.setVisible(false);


        ObservableList<String> types = FXCollections.observableArrayList(
                "Personne",
                "VEcoresponsabilité"
        );
        type.setItems(types);


    }


}
