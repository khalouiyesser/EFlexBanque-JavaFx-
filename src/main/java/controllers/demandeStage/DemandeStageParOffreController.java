package controllers.demandeStage;

import Entities.DemandeStage;
import Entities.OffreDeStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import services.ServiceDemandeStage;
import services.Upload;
import services.ValidatorOffre;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DemandeStageParOffreController implements Initializable {

    public AnchorPane demandeStagePane;
    @FXML
    private DatePicker date;

    @FXML
    private TextField LettreDemInput;

    @FXML
    private TextField EmailDemInput;

    @FXML
    private TextField cv;

    @FXML
    private Button upload;

    @FXML
    private Button ajouterDemande;

    @FXML
    private TextField PrenomDemInput;

    @FXML
    private TextField NumeroDemInput;

    @FXML
    private TextField NomDemInput;


//    private int idOffre;
    @FXML
    private VBox content_area;
    @FXML
    private ComboBox<String> Domaine;

    private File selectedCvFile;
    private String fileName;
    private OffreDeStage stageYesser;

    @FXML
    void Upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedCvFile = fileChooser.showOpenDialog(upload.getScene().getWindow());

        if (selectedCvFile != null) {
            Upload ServiceUpload = new Upload();
            fileName = ServiceUpload.Upload(selectedCvFile);
            cv.setText(fileName);
        }
    }
    public static int yy ;

    public void initData(OffreDeStage offreDeStage) {
        this.stageYesser = offreDeStage;
        yy = offreDeStage.getPostePropose();
//        this.yy = offreDeStage.getPostePropose();
        System.out.println("init = " + yy );


//        System.out.println(yy);

//        idOffre.setText(String.valueOf(offreDeStage.getPostePropose()));
//        System.out.println("offre fel init   " + idOffre.getText());
    }

    @FXML
    void ajouterDemande(ActionEvent event) {
        ValidatorOffre validatorOffre = new ValidatorOffre();

        System.out.println("button = " + yy);
//        System.out.println("button " + yy);
        if (event.getSource() == ajouterDemande) {
            // Vérifier si stageYesser n'est pas null avant de l'utiliser
//            if (stageYesser != null) {
                // Le reste de votre code ici...

            SingleSelectionModel<String> domaineChoisi = Domaine.getSelectionModel();
            String domaineF = domaineChoisi.getSelectedItem();

                String nom = NomDemInput.getText();
                String prenom = PrenomDemInput.getText();
                String email = EmailDemInput.getText();
                String lettre = LettreDemInput.getText();
                String num = NumeroDemInput.getText();
                String etat = "encours";
                String domaine = domaineF;
                String cv = fileName;
                Date date1 = java.sql.Date.valueOf(date.getValue());

                // Vérifier si fileName n'est pas null
                if (fileName != null) {
                    DemandeStage stage = new DemandeStage(nom, prenom, email, lettre, cv, domaine, Integer.parseInt(num), etat, date1);
                    ServiceDemandeStage demandeStage = new ServiceDemandeStage();

                    try {
                        demandeStage.ajouterParOffre(stage, yy);
                        showAlert(Alert.AlertType.CONFIRMATION, "Ajouté avec succès", null, "Votre activité a été ajoutée avec succès.");
                    } catch (SQLException | IOException | InterruptedException e) {
                        showAlert(Alert.AlertType.ERROR, "Ajouté Failed", null, "Failed.");
                        e.printStackTrace();
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Information manquante", null, "Veuillez sélectionner un fichier CV.");
                }
////            } else {
//                showAlert(Alert.AlertType.WARNING, "Information manquante", null, "L'offre de stage n'a pas été correctement initialisée.");
//            }
        }
    }




    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialize = " + yy);
        ObservableList<String> choices = FXCollections.observableArrayList(
                "Informatique",
                "Marketing",
                "Finance",
                "RH",
                "Comptabilité",
                "Management"
        );

        Domaine.setItems(choices);

    }

    public void RetourBack(MouseEvent mouseEvent) {

        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/DetailsOffre.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

