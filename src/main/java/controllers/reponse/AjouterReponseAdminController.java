package controllers.reponse;

import Entities.User;
import Entities.actualites.Reclamation;
import Entities.actualites.Reponse;
import controllers.reclamation.ListRecAdminController;
import controllers.reclamation.ReclamationItemAdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceReclamation;
import services.ServiceReponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

public class AjouterReponseAdminController implements Initializable {

    @FXML
    private Text addpieceJBtn;
    public static User user;

    @FXML
    private TextArea contenuInput;

    @FXML
    private Text contenuInputError;

    @FXML
    private HBox contenuInputErrorBox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text pieceInputError;

    @FXML
    private HBox pieceInputErrorBox;

    @FXML
    private Button reponseBtn;
    private Reclamation reclamation;
    private String pdfName;
    @FXML
    private Text captchaErrorText;

    @FXML
    private TextField captchaInput;

    @FXML
    private TextField captchaTextField;
    private String captchaValue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contenuInputErrorBox.setVisible(false);
        pieceInputErrorBox.setVisible(false);
        generateCaptcha();

    }
    @FXML
    void ajouter_image(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier PDF");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File selectedPDFFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedPDFFile != null) {
            // Générer un nom de fichier unique pour le PDF
            String uniqueID = UUID.randomUUID().toString();
            String extension = ".pdf";
            pdfName = uniqueID + extension; // Mettre à jour la variable de classe pdfName

            // Définir le répertoire de destination pour les PDF téléchargés
            String destinationFolder = "C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory"; // Chemin absolu du répertoire de destination

            // Créer le chemin de destination pour le PDF
            Path destination = Paths.get(destinationFolder, pdfName);

            try {
                // Copier le fichier sélectionné vers le dossier de destination
                Files.copy(selectedPDFFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // Gérer les erreurs de copie
                e.printStackTrace();
            }
        }

    }
    private void generateCaptcha() {
        Random random = new Random();
        // Générer un captcha aléatoire (par exemple, une chaîne de 4 chiffres)
        captchaValue = String.format("%04d", random.nextInt(10000));

        // Afficher le captcha dans l'interface utilisateur (par exemple, dans un champ de texte)
        captchaTextField.setText(captchaValue);
    }
    @FXML
    void addReponse(MouseEvent event) {
        int newId = ReclamationItemAdminController.idAn;

        ServiceReponse sr = new ServiceReponse();
        if (contenuInput.getText().isEmpty()) {
            contenuInputErrorBox.setVisible(true);
            return;
        }
        if (pdfName == null || pdfName.isEmpty()) { // Vérifie si le champ de la pièce jointe est vide
            pieceInputErrorBox.setVisible(true);
            return;
        }
        String userCaptchaInput = captchaInput.getText();
        if (!userCaptchaInput.equals(captchaValue)) {
            captchaErrorText.setText("Captcha incorrect !");
            return; // Sortir de la fonction si le captcha est incorrect
        }
        LocalDateTime dateTime = LocalDateTime.now();
        String adresse = user.getEmail();
        String pieceJointe = pdfName;
        Reponse reponse = new Reponse(newId, adresse, dateTime, contenuInput.getText(), pieceJointe);
        try {
            sr.ajouter(reponse);

            // Mettre à jour le statut de la réclamation associée à cette réponse
            ServiceReclamation serviceReclamation = new ServiceReclamation();
            if (reclamation != null) {
                reclamation.setStatut_rec("traitée");
                serviceReclamation.updateReclamationStatut(newId,"traitée");
            } else {
                System.out.println("La réclamation associée à cette réponse n'existe pas.");
            }

            // Actualiser la liste des réclamations
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listRecAdmin.fxml"));
            Parent listRecAdminPane = loader.load();
            ListRecAdminController listRecController = loader.getController();
            listRecController.refreshRecList();


            // Fermer la fenêtre d'ajout de réponse
            Stage stage = (Stage) contenuInput.getScene().getWindow(); // Obtenez la référence de la fenêtre
            stage.close(); // Fermez la fenêtre actuelle

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initDataRec(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

}