package controllers.offreDeStage;

import Entities.OffreDeStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceOffreDeStage;
import services.Upload;
import services.ValidatorOffre;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class AjouterOffreController implements Initializable {

    @FXML
    private TextField motsCles;

    @FXML
    private Text TitreError;

    @FXML
    private Text LanguageError;

    @FXML
    private Text experienceError;

    @FXML
    private Text DomaineError;

    @FXML
    private Text PosteError;

    @FXML
    private Text TypeError;

    @FXML
    private Label ExigenceError;

    @FXML
    private Text NiveauError;

    @FXML
    private Text DescriptionError;

    @FXML
    private Button upload;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField titre;

    @FXML
    private TextField domaine;

    @FXML
    private TextField description;

    @FXML
    private TextField language;

    @FXML
    private ComboBox<String> experience;


    @FXML
    private TextField niveau;
    @FXML
    private ImageView pfeImg;

    @FXML
    private TextField pfeBook;

    @FXML
    private Button updateOffre;

    @FXML
    private TextField exigence;

    @FXML
    private TextField poste;
    @FXML
    public Pane content_area;

    @FXML
    private Label MotsClesError;
    File selectedCvFile;
    String fileName;


    @FXML
    void ajouterOffre(ActionEvent event) throws SQLException {
        SingleSelectionModel<String> experienceSelectionModel = experience.getSelectionModel();
        String selectedexperience = experienceSelectionModel.getSelectedItem();

        SingleSelectionModel<String> experienceSelectionModel1 = type.getSelectionModel();
        String selectedtype = experienceSelectionModel1.getSelectedItem();

        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();

        // Vérifie si le champ "poste" n'est pas vide
//        String posteText = poste.getText();
//
//        // Convertit la valeur du champ "poste" en entier
//        int posteValue = Integer.parseInt(posteText);

        // Obtient la date d'aujourd'hui
        LocalDate today = LocalDate.now();
        // Controle de saisie
        ValidatorOffre validatorOffre = new ValidatorOffre();


        if (!validatorOffre.isValidTitreOffre(titre.getText())){
            TitreError.setVisible(true);
            TitreError.setText("Le titre doit contient plus que 20 caractère !");
        }
        if (!validatorOffre.isValidEmpty(domaine.getText())){
            DomaineError.setVisible(true);
        }
        if (!validatorOffre.isValidEmpty(selectedtype)){
            TypeError.setVisible(true);
        }
        if (!validatorOffre.isValidEmpty(selectedexperience)){
            ExigenceError.setVisible(true);
        }
        if (!validatorOffre.isValidEmpty(niveau.getText())){
            NiveauError.setVisible(true);
        }
        if (!validatorOffre.isValidEmpty(language.getText())){
            LanguageError.setVisible(true);
        }
        if (!validatorOffre.isValidDescriptionOffre(description.getText())){
            DescriptionError.setVisible(true);
            DescriptionError.setText("La description doit conetient plus que 100 caractère !");
        }
        if (!validatorOffre.isValidDescriptionOffre(exigence.getText())){
            ExigenceError.setVisible(true);
            ExigenceError.setText("L'éxigence  doit conetient plus que 100 caractère !");
        }
        if (    validatorOffre.isValidDescriptionOffre(exigence.getText()) && validatorOffre.isValidDescriptionOffre(description.getText())
                && validatorOffre.isValidEmpty(language.getText()) && validatorOffre.isValidEmpty(niveau.getText())&&
                validatorOffre.isValidEmpty(selectedexperience) && validatorOffre.isValidEmpty(selectedtype)
                && validatorOffre.isValidEmpty(domaine.getText()) && validatorOffre.isValidTitreOffre(titre.getText()) )
        {
            OffreDeStage stage = new OffreDeStage(Integer.parseInt(poste.getText()),titre.getText(),domaine.getText(),selectedtype,selectedexperience,description.getText(),exigence.getText(),Date.valueOf(today));
            serviceOffreDeStage.ajouter(stage);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification effectuée");
            alert.setHeaderText(null);
            alert.setContentText("L'offre de stage a été ajouté avec succès.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Echec");
            alert.setHeaderText(null);
            alert.setContentText("Les champs ne sont pas validés.");
            alert.showAndWait();
        }

    }


    @FXML
    void upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedCvFile = fileChooser.showOpenDialog(upload.getScene().getWindow());

        if (selectedCvFile != null) {
            Upload serviceUpload = new Upload();
            fileName = serviceUpload.Upload(selectedCvFile);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> beneficiaires = FXCollections.observableArrayList(
                "Offre d'emploi",
                "Offre de stage"
        );
        ObservableList<String> beneficiaire = FXCollections.observableArrayList(
                "0-2 ans",
                "2-4 ans",
                "5 ans +"
        );

        experience.setItems(beneficiaire);
        type.setItems(beneficiaires);
        TitreError.setVisible(false);
        LanguageError.setVisible(false);
        experienceError.setVisible(false);
        DomaineError.setVisible(false);
        PosteError.setVisible(false);
        TypeError.setVisible(false);
        ExigenceError.setVisible(false);
        NiveauError.setVisible(false);
        DescriptionError.setVisible(false);
        MotsClesError.setVisible(false);
    }

    public void RetourBack(MouseEvent mouseEvent) {

        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/AffichageOffre.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
