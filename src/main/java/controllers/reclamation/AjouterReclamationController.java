package controllers.reclamation;

import Entities.User;
import Entities.actualites.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceReclamation;

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

public class AjouterReclamationController implements Initializable {

    @FXML
    private Text ContenuArtInputError;

    @FXML
    private VBox content_area;

    @FXML
    private HBox ObjetHboxErreur;

    @FXML
    private Button addRecBtn;

    @FXML
    private Text addpieceJBtn;

    @FXML
    private HBox contenuInputErrorHbox;

    @FXML
    private TextArea contenuRec;

    @FXML
    private Text depInputError;

    @FXML
    private HBox depRecErrorHbox;

    @FXML
    private ComboBox<String> departementRec;

    @FXML
    private TextField objetRec;

    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox pieceJInputErrorHbox;

    @FXML
    private Text piecejointeInputError;

    @FXML
    private Text titreInputError;
    private String pdfName;
    @FXML
    private TextField captchaInput;

    @FXML
    private TextField captchaTextField;
    private String captchaValue;

    public static User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(user);
        depRecErrorHbox.setVisible(false);
        contenuInputErrorHbox.setVisible(false);
        ObjetHboxErreur.setVisible(false);
        pieceJInputErrorHbox.setVisible(false);
        ObservableList<String> departement = FXCollections.observableArrayList(
                "RH",
                "Finance",
                "Crédits",
                "Autres"

        );
        departementRec.setItems(departement);
        generateCaptcha();


    }
    private void generateCaptcha() {
        Random random = new Random();
        // Générer un captcha aléatoire (par exemple, une chaîne de 4 chiffres)
        captchaValue = String.format("%04d", random.nextInt(10000));

        // Afficher le captcha dans l'interface utilisateur (par exemple, dans un champ de texte)
        captchaTextField.setText(captchaValue);
    }
    @FXML
    void ajouter_reclamation(MouseEvent event) throws SQLException, IOException {
        String nom = user.getName();
        String adresse = user.getEmail();

        ServiceReclamation sr = new ServiceReclamation();

        if (contenuRec.getText().isEmpty()) {
            contenuInputErrorHbox.setVisible(true);
            return;
        }

        if (departementRec.getSelectionModel().isEmpty()) {
            depRecErrorHbox.setVisible(true);
            return;
        }

        if (objetRec.getText().isEmpty()) {
            ObjetHboxErreur.setVisible(true);
            return;
        }
        LocalDateTime dateTime = LocalDateTime.now();

        String selectedDepartment = departementRec.getSelectionModel().getSelectedItem();
        String pieceJArt = pdfName;

        String statut ="En cours de traitement";
        Reclamation reclamation = new Reclamation( objetRec.getText(), contenuRec.getText(), adresse,nom, selectedDepartment,statut, pieceJArt, dateTime, user.getId());
        System.out.println(reclamation);
        sr.ajouter(reclamation);
        System.out.println("reclamationajoutee:"+reclamation);
        loadReclamationsList();

    }

    private void loadReclamationsList() {
        try {
            // Charger le fichier FXML de la liste des réclamations
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listeRecClient.fxml"));
            Pane listRecAdminPane = loader.load();

            // Remplacer le contenu de la pane actuelle par la liste des réclamations
            content_area.getChildren().setAll(listRecAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addpieceJBtn(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier PDF");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File selectedPDFFile = fileChooser.showOpenDialog(pieceJArtInput.getScene().getWindow());
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
        }}

    public void returnbackRec(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listeRecClient.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
