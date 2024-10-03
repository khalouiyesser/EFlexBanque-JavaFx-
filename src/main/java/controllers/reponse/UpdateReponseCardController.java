package controllers.reponse;

import Entities.actualites.Reponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdateReponseCardController implements Initializable {

    @FXML
    private Text addpieceJBtn;

    @FXML
    private TextArea contenuInput;

    @FXML
    private Text contenuInputError;

    @FXML
    private HBox contenuInputErrorBox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Pane hadhemiOsket;

    @FXML
    private ImageView pieceJArtInput;
    @FXML
    private Text pieceJRepInputError;

    @FXML
    private HBox pieceJointeInputErrorBox;

    @FXML
    private Button reponseBtn;
    private static String FxmlToLoad;
    Reponse reponseToUpdate;
    @FXML
    void addHistory(MouseEvent event) {

    }
    public static String getFxmlToLoad() {
        return FxmlToLoad;
    }
    public static void setFxmlToLoad(String FxmlToLoad) {
        UpdateReponseCardController.FxmlToLoad = FxmlToLoad;
    }


    public void initData(Reponse reponse) {
        this.reponseToUpdate = reponse;
        initializeRepFields();
    }

    private void initializeRepFields() {
        if (reponseToUpdate != null) {
            contenuInput.setText(reponseToUpdate.getContenu_rep());

        }
    }

    @FXML
    void reponseBtn(MouseEvent mouseEvent) {
        try {

            System.out.println("tekhdem");

            // Vos opérations de validation et de modification de l'article ici...

            if (reponseToUpdate != null) {
                // Mettre à jour les données de l'article avec les nouvelles valeurs
                reponseToUpdate.setContenu_rep(contenuInput.getText());
                LocalDateTime dateTime = LocalDateTime.now();
                reponseToUpdate.setDate_rep(dateTime);
                reponseToUpdate.setPiece_jrep(pdfName);
                reponseToUpdate.setAdr_rep("hahaha");
                ServiceReponse serviceReponse = new ServiceReponse();
                serviceReponse.modifier(reponseToUpdate);
                System.out.println(reponseToUpdate);

                Stage stage = (Stage) contenuInput.getScene().getWindow();
                stage.close();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeRepFields();
    }

    public void ajouterPiece(MouseEvent mouseEvent) {
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
        }
    }

    public void PieceJArtInput(MouseEvent mouseEvent) {

    }
    private String  pdfName;
    public void updateProject(MouseEvent mouseEvent) {
        try {

            System.out.println("tekhdem");

            // Vos opérations de validation et de modification de l'article ici...

            if (reponseToUpdate != null) {
                // Mettre à jour les données de l'article avec les nouvelles valeurs
                reponseToUpdate.setContenu_rep(contenuInput.getText());
                LocalDateTime dateTime = LocalDateTime.now();
                reponseToUpdate.setDate_rep(dateTime);
                reponseToUpdate.setPiece_jrep(pdfName);
                reponseToUpdate.setAdr_rep("hahaha");
                ServiceReponse serviceReponse = new ServiceReponse();
                serviceReponse.modifier(reponseToUpdate);
                ListRepAdminController.setUpdateRepModelShow(0);
                ListRepAdminController.setShowArticleModelShow(0);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/listRepAdmin.fxml"));
                try {
                    Parent root = loader.load();
                    Pane contentArea = (Pane) ((Node) mouseEvent.getSource()).getScene().lookup("#content_area");
                    contentArea.getChildren().clear();
                    contentArea.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(reponseToUpdate);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setProjectUpdateData(Reponse reponse) {
        reponseToUpdate = reponse;

        contenuInput.setText(reponseToUpdate.getContenu_rep());

        pdfName=reponse.getPiece_jrep();
    }
}