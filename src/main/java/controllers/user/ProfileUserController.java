package controllers.user;

import Entities.User;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceUser;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
//import io.camunda.connector.Connector;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import tests.TranslatorText;

public class ProfileUserController  implements Initializable {
    /*
        @FXML
        private Button changePassBTN;

        @FXML
        private ImageView downloadPdfButton;

        @FXML
        private Text descriptionText;

        @FXML
        private Text emailText;

        @FXML
        private ImageView en;

        @FXML
        private ImageView fr;

        @FXML
        private ImageView tn;

        @FXML
        private Text fullnameText;

        @FXML
        private Pane profilePane;

        @FXML
        private Text telText;

        @FXML
        private ImageView userItemImg;

        @FXML
        private Label userItemUpdateBtn;

        @FXML
        private ImageView userItemUpdateBtnImg;


        @FXML
        private Text EmailLab;

        @FXML
        private Text PhoneNumberLabel;

        @FXML
        private Text fullnameLab;
    */
    @FXML
    private Text EmailLab;

    @FXML
    private Text PhoneNumberLabel;

    @FXML
    private Button changePassBTN;

    @FXML
    private Text descriptionText;

    @FXML
    private ImageView downloadPdfButton;

    @FXML
    private Text emailText;

    @FXML
    private ImageView en;

    @FXML
    private ImageView fr;

    @FXML
    private Text fullnameLab;

    @FXML
    private Text fullnameText;
    @FXML
    private Text AdresseLabel;
    @FXML
    private Text adressetext;

    @FXML
    private Text profile;

    @FXML
    private Pane profilePane;

    @FXML
    private Text role;

    @FXML
    private Text telText;

    @FXML
    private ImageView tn;

    @FXML
    private ImageView userItemImg;

    @FXML
    private Label userItemUpdateBtn;

    String from = "";
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        ServiceUser userService = new ServiceUser();
        try {
            // user = userService.getOneUser(UserSession.getInstance().getEmail());
            User user;
            if (UserSession.getInstance().getEmail() == null) {
                user = userService.getOneUser("mariem@gmail.com");
            } else {
                user = userService.getOneUser(UserSession.getInstance().getEmail());
            }


            // Image image = new Image(
            // getClass().getResource("/img/admin.png/" + user.getPhoto()).toExternalForm());
            // userItemImg.setImage(image);


            fullnameText.setText(user.getName());
            descriptionText.setText(user.getRoles());
            emailText.setText(user.getEmail());
            telText.setText(user.getTel());
            adressetext.setText(user.getAdresse());
            TranslatorText translatorText = new TranslatorText();

            tn.setOnMouseClicked(mouseEvent -> {
                from = "ar";
                try {
                    fullnameLab.setText(translatorText.post(fullnameLab.getText(),"fr","ar"));
                    role.setText(translatorText.post(role.getText(),"fr","ar"));
                    profile.setText(translatorText.post(profile.getText(),"fr","ar"));
                    EmailLab.setText(translatorText.post(EmailLab.getText(),"fr","ar"));
                    PhoneNumberLabel.setText(translatorText.post(PhoneNumberLabel.getText(),"fr","ar"));
                    AdresseLabel.setText(translatorText.post(AdresseLabel.getText(),"fr","ar"));
                    changePassBTN.setText(translatorText.post(changePassBTN.getText(),"fr","ar"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
            fr.setOnMouseClicked(mouseEvent -> {
                from = "fr";
                try {
                    fullnameLab.setText(translatorText.post(fullnameLab.getText(),"ar","fr"));
                    role.setText(translatorText.post(role.getText(),"ar","fr"));
                    profile.setText(translatorText.post(profile.getText(),"ar","fr"));
                    EmailLab.setText(translatorText.post(EmailLab.getText(),"ar","fr"));
                    PhoneNumberLabel.setText(translatorText.post(PhoneNumberLabel.getText(),"ar","fr"));
                    AdresseLabel.setText(translatorText.post(AdresseLabel.getText(),"ar","fr"));
                    changePassBTN.setText(translatorText.post(changePassBTN.getText(),"ar","fr"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            en.setOnMouseClicked(mouseEvent -> {
                from = "en";
                try {
                    fullnameLab.setText(translatorText.post(fullnameLab.getText(),"ar","en"));
                    role.setText(translatorText.post(role.getText(),"ar","en"));
                    profile.setText(translatorText.post(profile.getText(),"ar","en"));
                    EmailLab.setText(translatorText.post(EmailLab.getText(),"ar","en"));
                    PhoneNumberLabel.setText(translatorText.post(PhoneNumberLabel.getText(),"ar","en"));
                    AdresseLabel.setText(translatorText.post(AdresseLabel.getText(),"ar","en"));
                    changePassBTN.setText(translatorText.post(changePassBTN.getText(),"ar","en"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleDownloadPdf() {
        try {
            // Instancier ServiceUser
            ServiceUser userService = new ServiceUser();
            // Utiliser l'instance pour appeler la méthode getOneUser
            User user = userService.getOneUser(UserSession.getInstance().getEmail());
            generatePdf(user);
        } catch (SQLException e) {
            e.printStackTrace(); // handle exception properly
        }
    }


    private void generatePdf(User user) {
        Document document = new Document();
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.setInitialFileName(user.getName() + "_profile.pdf");
            File file = fileChooser.showSaveDialog(downloadPdfButton.getScene().getWindow());
            if (file != null) {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                document.add(new Paragraph("Full Name: " + user.getName()));
                document.add(new Paragraph("Description: " + user.getRoles()));
                document.add(new Paragraph("Email: " + user.getEmail()));
                document.add(new Paragraph("Telephone: " + user.getTel()));
                document.add(new Paragraph("Adresse: " + user.getAdresse()));
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); // handle exception properly
        }
    }


    @FXML
    void change(MouseEvent event) throws IOException{
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/EdituserProfil.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            profilePane.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






    public void generatePdf(MouseEvent mouseEvent) {
        try {
            ServiceUser userService = new ServiceUser();
            User user = userService.getOneUser(UserSession.getInstance().getEmail());
            Document document = new Document();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.setInitialFileName(user.getName() + "_profile.pdf");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                // Ajouter l'image de logo
                String userDir = System.getProperty("user.dir");
                String imagePath = userDir + "/src/main/resources/img/logo.png";
                com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(imagePath);
                logo.scaleToFit(100, 100); // Adapter la taille de l'image
                logo.setAlignment(Element.ALIGN_LEFT); // Aligner l'image à gauche
                document.add(logo);

                // Espacement vertical après l'image
                document.add(Chunk.NEWLINE);

                // Ajouter les informations de l'utilisateur
                document.add(new Paragraph("Full Name: " + user.getName()));
                document.add(new Paragraph("Description: " + user.getRoles()));
                document.add(new Paragraph("Email: " + user.getEmail()));
                document.add(new Paragraph("Telephone: " + user.getTel()));
                document.add(new Paragraph("Adresse: " + user.getAdresse()));

                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); // handle exception properly
        }
    }

    public void changePass(ActionEvent actionEvent) {
    }
}