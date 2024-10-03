package controllers.demandeStage;

import Entities.DemandeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import services.ServiceDemandeStage;
import utils.MyDatabase;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

public class EditDemandeController implements Initializable {
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
    private TextField cv;
    private File selectedCvFile;
    private String fileName;


    @FXML
    private Button ajouterDemande;

//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        NomDemInput.setText("yesser");
//        PrenomDemInput.setText("khaloui");
//        EmailDemInput.setText("khaluiyesser@gmail.com");
        //    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NomDemInput.setText("yesser");
    }
    public void initData(DemandeStage demande) {
        // Initialiser les champs de l'interface utilisateur avec les données de la demande
        // Par exemple :
        NomDemInput.setText(demande.getNom());
        PrenomDemInput.setText(demande.getPrenom());
        // Autres initialisations...
    }
    @FXML
    void Upload(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedCvFile = fileChooser.showOpenDialog(upload.getScene().getWindow());

        if (selectedCvFile != null) {
            // Charger le PDF dans la WebView
//            String url = selectedCvFile.toURI().toString();
//            cv.getEngine().load(url);
            File cva = new File(selectedCvFile.toURI().toString());
            cv.setText(String.valueOf(cva));
            // Générer un nom de fichier unique pour le CV
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedCvFile.getName().substring(selectedCvFile.getName().lastIndexOf(".")); // L'extension est déjà spécifiée dans le filtre de l'FileChooser
            fileName = uniqueID + extension;

            // Copier le fichier PDF vers le répertoire de destination
            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", fileName);
            try {
                Files.copy(selectedCvFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    @FXML
    void ajouterDemande(ActionEvent event) {
        if (event.getSource() == ajouterDemande) {
            if (NomDemInput.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre activité.");
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
//                Date date = new Date(128, 04, 06);
                MyDatabase db = MyDatabase.getInstance();

                DemandeStage stage = new DemandeStage(nom, prenom, email, lettre, cv, domaine, Integer.parseInt(num), etat, new Date(124, 04, 06));
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
        String nom = NomDemInput.getText();
        String prenom = PrenomDemInput.getText();
        String email = EmailDemInput.getText();
        String lettre = LettreDemInput.getText();
        String num = NumeroDemInput.getText();
        String etat = "encours";
        String domaine = "Informatique";
        String cv = "fileName";
        Date date = new Date(128,04,06);
        MyDatabase db = MyDatabase.getInstance();
        DemandeStage demandeStage = new DemandeStage(nom,prenom,email,lettre,cv,domaine,Integer.parseInt(num),etat,date);
        ServiceDemandeStage serviceDemandeStage =new ServiceDemandeStage();
        try {
            serviceDemandeStage.ajouter(demandeStage);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}