package controllers.demandeStage;

import Entities.DemandeStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
//import javafx.scene.web.WebView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import services.ServiceDemandeStage;
import services.Upload;
import utils.MyDatabase;

import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

//import static controllers.offreDeStage.Recrutement.pane;
//import javafx.scene.web.WebView;

public class DemandeStageController implements Initializable {

    public VBox content_area;
//    @FXML
//    private WebView CvDemInput;

    @FXML
    private TextField LettreDemInput;

    @FXML
    private TextField EmailDemInput;

    @FXML
    private TextField PrenomDemInput;

    @FXML
    private TextField NumeroDemInput;

    @FXML
    private TextField NomDemInput;
    @FXML
    private Button upload;
    @FXML
    private DatePicker date;
    @FXML
    private TextField cv;
    private File selectedCvFile;
    private String fileName;
    @FXML
    private ComboBox<String> domaine;
    @FXML
    private Button retour;
//    @FXML
//    private Text dateError;
//    @FXML
//    private Text prenomError;
//
//    @FXML
//    private Text NumeroError;
//
//    @FXML
//    private Text CvError;
//
//    @FXML
//    private Text nomError;
//    @FXML
//    private Text domainError;
//    @FXML
//    private Text EmailError;
//
//    @FXML
//    private Text lettreError;


    @FXML
    private Button ajouterDemande;
//    public static domaine;
//    private int i = 0;

    @FXML
    void Upload(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedCvFile = fileChooser.showOpenDialog(upload.getScene().getWindow());

        if (selectedCvFile != null) {
            Upload ServiceUpload = new Upload();
           fileName =  ServiceUpload.Upload(selectedCvFile);
           cv.setText(fileName);
        }
    }
    @FXML
    void ajouterDemande(ActionEvent event) {
//        ValidatorOffre validatorOffre = new ValidatorOffre();
//        if (!validatorOffre.isValidEmpty(NomDemInput.getText())){
//            nomError.setVisible(true);
//        }
//        if (!validatorOffre.isValidEmpty(PrenomDemInput.getText())){
//            prenomError.setVisible(true);
//        }
//        if (!validatorOffre.isValidEmail(EmailDemInput.getText())){
//            EmailError.setVisible(true);
//        }





        if (event.getSource() == ajouterDemande){


            if (NomDemInput.getText().isEmpty() &&  LettreDemInput.getText().isEmpty() && EmailDemInput.getText().isEmpty() && PrenomDemInput.getText().isEmpty() && NumeroDemInput.getText().isEmpty() && cv.getText().isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Toutes les champs sont vides.");
                Optional<ButtonType> option = alert.showAndWait();
            }
            if (NomDemInput.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Le nom est vide.");
                Optional<ButtonType> option = alert.showAndWait();
            } else {
                String nom = NomDemInput.getText();
                String prenom = PrenomDemInput.getText();
                String email = EmailDemInput.getText();
                String lettre = LettreDemInput.getText();
                String num = NumeroDemInput.getText();
                String etat = "encours";
                String domaine = "Informatique";
                String cv = fileName;
                if (date.getValue() == null){
                    System.out.println("null");
                }else {
                    Date date1 = java.sql.Date.valueOf(date.getValue());

                }
                Date date2 = new Date(200,02,01);
//                Date date = new Date(128,04,06);
                MyDatabase db = MyDatabase.getInstance();

                DemandeStage  stage = new DemandeStage(nom,prenom,email,lettre,cv,domaine,Integer.parseInt(num),etat,date2);
                ServiceDemandeStage demandeStage = new ServiceDemandeStage();
                try {
                    demandeStage.ajouter(stage);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ajouté avec succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Votre activité a été ajoutée avec succès.");
                    Optional<ButtonType> option = alert.showAndWait();
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ajouté Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed.");
                    Optional<ButtonType> option = alert.showAndWait();

                }
            }
    }
}

    private void AjoutDem(){

        SingleSelectionModel<String> domaineChoisi = domaine.getSelectionModel();
        String domaineF = domaineChoisi.getSelectedItem();


        String nom = NomDemInput.getText();
        String prenom = PrenomDemInput.getText();
        String email = EmailDemInput.getText();
        String lettre = LettreDemInput.getText();
        String num = NumeroDemInput.getText();
        String etat = "encours";
        String domaine = domaineF;
        String cv = "fileName";
        Date date1 = java.sql.Date.valueOf(date.getValue());
        MyDatabase db = MyDatabase.getInstance();
        DemandeStage demandeStage = new DemandeStage(nom,prenom,email,lettre,cv,domaine,Integer.parseInt(num),etat,date1);
        ServiceDemandeStage serviceDemandeStage =new ServiceDemandeStage();
        try {
            serviceDemandeStage.ajouter(demandeStage);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initData(int postePropose) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> choices = FXCollections.observableArrayList(
                "Informatique",
                "Marketing",
                "Finance",
                "RH",
                "Comptabilité",
                "Management"
        );

        domaine.setItems(choices);

    }

    public void retour(ActionEvent actionEvent) throws IOException {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/Recrutement.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
