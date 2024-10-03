package controllers.article;

import Entities.User;
import Entities.actualites.Article;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceArticle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
public class AjouterArticleController implements Initializable {
    @FXML
    private Text CatArtInputError;
    private  File selectedImageFile;
    private String imageName = null ;
    @FXML

    private TextArea ContenuArt;
    private String captchaValue;

    @FXML
    private Text ContenuArtInputError;
    @FXML
    private WebView pdfWebView;
    @FXML
    private HBox ContenuHboxErreur;

    @FXML
    private Button addArticleBtn;

    @FXML
    private Text addpieceJBtn;

    @FXML
    private HBox categorieErrorHbox;

    @FXML
    private ComboBox<String> categoriechoice;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox pieceJInputErrorHbox;

    @FXML
    private Text piecejointeInputError;
    @FXML
    private Text captchaErrorText;

    @FXML
    private TextField captchaInput;

    @FXML
    private TextField captchaTextField;


    @FXML
    private TextField titreInput;

    @FXML
    private Text titreInputError;

    @FXML
    private HBox titreInputErrorHbox;
    @FXML
    private Pane content_area;

    @FXML
    private ScrollPane scrollPane;
    private File selectedCvFile;
    private String fileName;
    private String pdfName;

    public static User user;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        categorieErrorHbox.setVisible(false);
        ContenuHboxErreur.setVisible(false);
        pieceJInputErrorHbox.setVisible(false);
        titreInputErrorHbox.setVisible(false);

        ObservableList<String> categories = FXCollections.observableArrayList(
                "Développement durable",
                "Finance",
                "Banque",
                "Crédits"
        );
        categoriechoice.setItems(categories);
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
    void ajouter_article(MouseEvent event) throws SQLException, IOException {
        String nom = "hadhemi";
        String adresse = "mahmoud";
        ServiceArticle sa = new ServiceArticle();
        boolean champsVides = false;

        // Vérifier si le contenu est vide
        if (ContenuArt.getText().isEmpty()) {
            ContenuHboxErreur.setVisible(true);
            champsVides = true;
        } else {
            ContenuHboxErreur.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        // Vérifier si la catégorie est sélectionnée
        if (categoriechoice.getSelectionModel().isEmpty()) {
            categorieErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            categorieErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        // Vérifier si une image est sélectionnée
        if (imageInput.getImage() == null) {
            imageInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            imageInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        // Vérifier si le titre est vide
        if (titreInput.getText().isEmpty()) {
            titreInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            titreInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        // Vérifier si le titre commence par une majuscule
        String titre = titreInput.getText();
        if (!Character.isUpperCase(titre.charAt(0))) {
            titreInputErrorHbox.getChildren().setAll(new Text("Le titre doit commencer par une majuscule."));
            titreInputErrorHbox.setVisible(true);
            champsVides = true; // Mettre à jour champsVides pour indiquer qu'il y a une erreur
        } else {
            titreInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        // Vérifier si des champs sont vides
        if (champsVides) {
            return; // Sortir de la fonction si des champs sont vides
        }

        // Vérifier le capt
        String userCaptchaInput = captchaInput.getText();
        if (!userCaptchaInput.equals(captchaValue)) {
            captchaErrorText.setText("Captcha incorrect !");
            return; // Sortir de la fonction si le captcha est incorrect
        }
        LocalDateTime dateTime = LocalDateTime.now();
        int dureeArt=4;
        String img = imageName;
        String pieceJArt = pdfName;
        String selectedCategory = categoriechoice.getSelectionModel().getSelectedItem();


        String ArticleData =  "Titre: " + titre + "\n" +
                "Publié par: " + adresse + "\n" +
                "Catégorie: " + selectedCategory + "\n" +
                "Contenu: " + ContenuArt.getText() + "\n" ;


        String base64QRCode = generateBase64QRCode(ArticleData);
        Article article = new Article(nom ,adresse,dateTime , dureeArt, selectedCategory , titre , ContenuArt.getText(), pieceJArt ,img , base64QRCode);
        article.setQrCode(base64QRCode);

        boolean ajoutReussi = sa.ajouter(article);

        if (ajoutReussi) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/listArticleAdmin.fxml"));
            Pane listArtAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArtAdminPane);

        }

    }
    private String generateBase64QRCode(String data) {
        ByteArrayOutputStream stream = QRCode.from(data).to(ImageType.JPG).stream();
        byte[] byteArray = stream.toByteArray();
        return Base64.getEncoder().encodeToString(byteArray);
    }
    @FXML
    void returnbackarticle(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/listArticleAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void importerImage() throws IOException {
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
//            Path destination = Paths.get(System.getProperty("user.dir"), "src", "main","java","uploads", article.getImage_art());

            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", imageName);

//            Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }
    }
    public String getImageName() {
        return imageName;
    }

    //
    public void ajouterPiece(MouseEvent mouseEvent) {
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
            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory",pdfName);

            // Créer le chemin de destination pour le PDF
           // Path destination = Paths.get(destinationFolder, pdfName);


            try {
                // Copier le fichier sélectionné vers le dossier de destination
                Files.copy(selectedPDFFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // Gérer les erreurs de copie
                e.printStackTrace();
            }
        }
    }

    @FXML
    void PieceJArtInput(MouseEvent event) {
        if (pdfName != null) {
            String pdfFilePath = "C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory" + pdfName;
            File selectedPdfFile = new File(pdfFilePath);
            if (selectedPdfFile.exists()) {
                // Charger le fichier PDF dans la WebView
                WebEngine webEngine = pdfWebView.getEngine();
                String url = selectedPdfFile.toURI().toString();
                webEngine.load(url);
            } else {
                System.out.println("Le fichier PDF spécifié n'existe pas.");
            }
        } else {
            System.out.println("Aucun fichier PDF n'a été téléchargé.");
        }

    }}
