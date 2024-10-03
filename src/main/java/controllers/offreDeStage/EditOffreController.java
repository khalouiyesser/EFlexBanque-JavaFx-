package controllers.offreDeStage;

import Entities.DemandeStage;
import Entities.OffreDeStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceOffreDeStage;
import services.Upload;
import services.ValidatorOffre;

public class EditOffreController implements Initializable {

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
    private Label MotsClesError;
    File selectedCvFile;
    String fileName;
    int id ;

    public void initData(int a) throws SQLException {
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
//         List <OffreDeStage> stage = serviceOffreDeStage.afficherId();
        OffreDeStage stage = serviceOffreDeStage.afficheUne(a);

//         System.out.println(stage);
        if (stage.getDescription() != null){
            description.setText(stage.getDescription());
        }else {
            description.setText("description est vide");
        }
        if (stage.getTitle() != null){
            titre.setText(stage.getTitle());

        }else {
            titre.setText("titre est vide");

        }
        if (stage.getExigenceOffre() != null){
            exigence.setText(stage.getExigenceOffre());

        }else {
            exigence.setText("exigence est vide");

        }
        if (stage.getDomaine() != null){
            domaine.setText(stage.getDomaine());

        }else {
            domaine.setText("domaine est vide");

        }
        if (stage.getLanguage() != null){
            language.setText(String.valueOf(stage.getLanguage()));

        }else {
            language.setText("Language est vide");

        }
        if (stage.getMotsCles() != null){
            motsCles.setText(String.valueOf(stage.getMotsCles()));

        }else {
            motsCles.setText("Mots clés est vide");

        }
        if (stage.getNiveau() != null){
            niveau.setText(String.valueOf(stage.getNiveau()));

        }else {
            niveau.setText("Mots Niveau est vide");

        }
        StringBuilder stringBuilder = new StringBuilder();
        List aa = (List) stage.getLanguage();
        id = a;
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

    public void upload(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedCvFile = fileChooser.showOpenDialog(upload.getScene().getWindow());

        if (selectedCvFile != null) {
            Upload serviceUpload = new Upload();
            fileName = serviceUpload.Upload(selectedCvFile);
//            pfeImg.setImage(new Image("C:\\Users\\Yesser\\IdeaProjects\\InnovatixPiDevJava\\src\\main\\resources\\img\\PDF_file_icon.svg.png"));
            System.out.println(fileName);
            // Charger l'image depuis le fichier sélectionné
//            pfeImg.setImage(new Image(selectedCvFile.toURI().toString()));
        }

    }
    public void updateOffre(ActionEvent actionEvent) throws SQLException {
        SingleSelectionModel<String> experienceSelectionModel = experience.getSelectionModel();
        String selectedexperience = experienceSelectionModel.getSelectedItem();

        SingleSelectionModel<String> experienceSelectionModel1 = type.getSelectionModel();
        String selectedtype = experienceSelectionModel1.getSelectedItem();

        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();

        // Vérifie si le champ "poste" n'est pas vide
        String posteText = poste.getText();

        // Convertit la valeur du champ "poste" en entier
        int posteValue = Integer.parseInt(posteText);

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
            OffreDeStage stage = new OffreDeStage(id, Integer.parseInt(poste.getText()), titre.getText(), domaine.getText(), selectedtype, selectedexperience, description.getText(), exigence.getText(), Date.valueOf(today));
            serviceOffreDeStage.modifier(stage);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification effectuée");
            alert.setHeaderText(null);
            alert.setContentText("L'offre de stage a été modifiée avec succès.");
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

}