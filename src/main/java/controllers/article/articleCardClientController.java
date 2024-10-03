package controllers.article;

import Entities.actualites.Article;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import controllers.commentaireArticle.CommentArticleController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceArticle;
import tests.Yesser;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.ResourceBundle;

public class articleCardClientController implements Initializable {

    @FXML
    private HBox action;

    @FXML
    private VBox backArt;

    @FXML
    private Text contenuArtFront;

    @FXML
    private Text datepubArt;

    @FXML
    private HBox deleteArtBtn;
    @FXML
    private HBox pdf;
    @FXML
    private HBox editArt;

    @FXML
    private HBox textToSpeech;
    @FXML
    private ImageView imgArtFront;

    @FXML
    private HBox open_productDetails;

    @FXML
    private Text titreArtFront;

    @FXML
    private HBox viewdetailArt;

    @FXML
    private ImageView qrCodeImage;
    private Article article;
    private ListArticleAdminController listArticleController;
    private final Yesser yesser = new Yesser();






    public void setListArticleController(ListArticleAdminController listArticleController) {
        this.listArticleController = listArticleController;
    }

    public void initializeData(Article article) {
        ServiceArticle serviceArticle = new ServiceArticle();
        if (article != null) {
            titreArtFront.setText(article.getTitre_art());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = article.getDate_pub_art().format(formatter);
            datepubArt.setText(formattedDate);
//            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", imageName);
            imgArtFront.setImage(new Image("file:///C:/Users/Yesser/PI/InnovatixYesser/public/uploads_directory/" + article.getImage_art()));
            String base64QRCode = article.getQrCode();
            if (base64QRCode != null) {
                // Convert the Base64 string to byte array
                byte[] qrCodeBytes = Base64.getDecoder().decode(base64QRCode);

                // Load the byte array into an Image
                Image qrCode = new Image(new ByteArrayInputStream(qrCodeBytes));

                // Set the Image to the ImageView
                qrCodeImage.setImage(qrCode);}
            contenuArtFront.setText(article.getContenu_art());
            pdf.setOnMouseClicked(event -> {
                if (article != null) {
                    Document document = new Document();
                    try {
                        // Spécifier le chemin où le PDF sera enregistré
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Enregistrer le PDF");
                        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
                        File file = fileChooser.showSaveDialog(new Stage());

                        if (file != null) {
                            // Écrire le contenu de l'article dans le document PDF
                            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
                            document.open();

                            // Créer un cadre autour de tout le document
                            Rectangle rectangle = new Rectangle(document.getPageSize());
                            rectangle.setBorder(Rectangle.BOX);
                            rectangle.setBorderWidth(2);
                            rectangle.setBorderColor(new BaseColor(107, 175, 84)); // Vert foncé
                            PdfContentByte canvas = writer.getDirectContent();
                            canvas.rectangle(rectangle);

                            // Ajouter le logo de votre application en haut à gauche du PDF
                            String userDir = System.getProperty("user.dir");
                            String imagePath = userDir + "/src/main/resources/img/logoo.png";
                            com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(imagePath);
                            logo.scaleToFit(100, 100);
                            logo.setAlignment(Element.ALIGN_LEFT);
                            document.add(logo);

                            // Ajouter une ligne pour séparer le logo et le contenu
                            document.add(new LineSeparator());

                            // Ajouter le titre de l'article centré avec une belle police et en vert foncé
                            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD, new BaseColor(107, 175, 84)); // Vert foncé
                            Paragraph title = new Paragraph(article.getTitre_art(), titleFont);
                            title.setAlignment(Element.ALIGN_CENTER);
                            document.add(title);

                            // Ajouter l'image de l'article avec un cadre professionnel
                            if (article.getImage_art() != null && !article.getImage_art().isEmpty()) {
                                String imagePathArt = userDir + "/src/main/java/uploads/" + article.getImage_art();
                                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(imagePathArt);
                                image.setBorder(Rectangle.BOX);
                                image.setBorderWidth(2);
                                image.setBorderColor(BaseColor.BLACK);
                                image.scaleToFit(300, 300); // Redimensionner l'image
                                image.setAlignment(Element.ALIGN_CENTER);
                                document.add(image);
                            }

                            // Ajouter la catégorie de l'article
                            Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
                            Paragraph category = new Paragraph("Catégorie: " + article.getCategorie_art(), categoryFont);
                            category.setAlignment(Element.ALIGN_CENTER);
                            document.add(category);

                            // Ajouter le contenu de l'article
                            Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
                            Paragraph content = new Paragraph(article.getContenu_art(), contentFont);
                            content.setAlignment(Element.ALIGN_CENTER);
                            document.add(content);

                            // Ajouter la date de publication, l'auteur et la signature en bas à droite
                            Font dateFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
                            Paragraph footer = new Paragraph();
                            footer.setAlignment(Element.ALIGN_RIGHT);

                            // Ajouter la date de publication
                            String datePublication = "Publié le: " + article.getDate_pub_art();
                            Chunk dateChunk = new Chunk(datePublication, dateFont);
                            footer.add(dateChunk);

                            // Ajouter l'auteur
                            String auteur = "\nPar: " + article.getAdr_aut_art();
                            Chunk auteurChunk = new Chunk(auteur, dateFont);
                            footer.add(auteurChunk);

                            // Ajouter un retour à la ligne
                            Paragraph emptyLine = new Paragraph("\n");
                            document.add(emptyLine);

                            // Ajouter l'image de la signature
                            String imagePathSig = userDir + "/src/main/resources/img/signature.png";
                            com.itextpdf.text.Image imageSig = com.itextpdf.text.Image.getInstance(imagePathSig);
                            imageSig.scaleToFit(50, 50); // Redimensionner l'image de la signature
                            Chunk imageChunk = new Chunk(imageSig, 0, 0, true);
                            footer.add(imageChunk);

                            document.add(footer);

                            // Fermer le document
                            document.close();
                            System.out.println("PDF enregistré avec succès !");
                        } else {
                            System.out.println("L'enregistrement du PDF a été annulé.");
                        }
                    } catch (FileNotFoundException | DocumentException e) {
                        e.printStackTrace();
                        System.err.println("Erreur lors de la création du PDF.");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.err.println("L'article n'est pas initialisé.");
                }
            });

            viewdetailArt.setId(String.valueOf(article.getId()));
            viewdetailArt.setOnMouseClicked(event -> {
                try {
                    // Charger le fichier FXML du formulaire de modification d'article
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/commentaire/commentArticle.fxml"));
                    Parent editArticlePopupParent = loader.load();

                    // Récupérer le contrôleur du formulaire de modification d'article
                    CommentArticleController comment = loader.getController();

                    // Passer l'article à modifier au contrôleur
                    comment.setData(article);

                    // Créer une nouvelle fenêtre modale pour le formulaire de modification
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Commenter Article");
                    stage.setScene(new Scene(editArticlePopupParent));
                    stage.showAndWait(); // Attendre que la fenêtre se ferme avant de continuer

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Ajoutez un gestionnaire d'événements au bouton textToSpeech
        textToSpeech.setOnMouseClicked(this::handleTextToSpeech);
    }

    private void handleTextToSpeech(MouseEvent event) {
        try {
            // Récupérer le contenu de l'article
            String contenuArticle = contenuArtFront.getText(); // Supposons que contenuArtFront contient le texte de l'article

            // Obtenez l'URL audio en appelant la méthode getAudioUrl de Yesser avec le contenu de l'article
            String audioUrl = yesser.getAudioUrl("https://cloudlabs-text-to-speech.p.rapidapi.com/synthesize",
                    "209ace395amshc6b108de067144ep16a5dcjsne29d7659fb96",
                    contenuArticle);
            System.out.println(audioUrl);

            // Créez un objet Media à partir de l'URL audio
//            Media media = new Media(audioUrl);
            Media media = new Media(audioUrl);
            // Créez un lecteur multimédia à partir de l'objet Media
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            // Jouez l'audio
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void viewdetailArt(MouseEvent mouseEvent) {
    }

    public void pdf(MouseEvent mouseEvent) {
    }

    public void textToSpeech(MouseEvent mouseEvent) {
    }
}