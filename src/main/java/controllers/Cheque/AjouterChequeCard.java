package controllers.Cheque;

import Entities.Cheque;
import Entities.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceCheque;
import services.ValidSaisie;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;


public class AjouterChequeCard implements Initializable {

    @FXML
    private TextField Cin;

    @FXML
    private TextField Email;

    @FXML
    private Text EmailInputError;

    @FXML
    private HBox EmailInputErrorHbox;

    @FXML
    private TextField NometPrenom;

    @FXML
    private Pane content_area;

    @FXML
    private Text NometPrenomInputError;

    @FXML
    private Text DateInputError;

    @FXML
    private HBox DateInputErrorHbox;

    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private TextField RIB;

    @FXML
    private Button add_new_chequetBtn;

    @FXML
    private ComboBox<String> beneficiaire;

    @FXML
    private Text beneficiaireInputError;

    @FXML
    private HBox beneficiaireInputErrorHbox;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text cinInputError;

    @FXML
    private HBox cinInputErrorHbox;

    @FXML
    private DatePicker date;

    @FXML
    private Button ChequeImg;

    @FXML
    private TextField montant;

    @FXML
    private Text montantInputError;

    @FXML
    private HBox montantInputErrorHbox;

    @FXML
    private TextField tel;

    @FXML
    private Text telInputError;

    @FXML
    private HBox telInputErrorHbox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;

    private File selectedImageFile;
    private String imageName = null;
    public static User user;
    public static BigInteger rib;

    public static void setRib(BigInteger rib) {
        AjouterChequeCard.rib = user.getRib();
    }

    public static BigInteger getRib() {
        return rib;
    }

    @FXML
    void ImporterImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            javafx.scene.image.Image image = new javafx.scene.image.Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", imageName);
            try {
                Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("non");
            }
        }

    }


    @FXML
    public void ajouterCheque(MouseEvent mouseEvent) throws Exception {

        ServiceCheque sc = new ServiceCheque();
        boolean champsInvalides = false;

        // Vérification du numéro de CIN
        if (Cin.getText().isEmpty() || !ValidSaisie.isValidCin(Cin.getText())) {
            cinInputErrorHbox.setVisible(true);
            cinInputError.setText("Le numéro de cin doit contenir 8 chiffres qui commence par 0 ou 1");
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            cinInputErrorHbox.setVisible(false);
        }

        // Vérification du nom et prénom
        if (NometPrenom.getText().isEmpty()) {
            NometPrenomInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            NometPrenomInputErrorHbox.setVisible(false);
        }

        // Vérification du bénéficiaire
        if (beneficiaire.getSelectionModel().isEmpty()) {
            beneficiaireInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            beneficiaireInputErrorHbox.setVisible(false);
        }

        // Vérification de l'email
        if (Email.getText().isEmpty() || !ValidSaisie.isValidEmail(Email.getText())) {
            EmailInputErrorHbox.setVisible(true);
            EmailInputError.setText("Adresse email invalide");
            champsInvalides = true;

        } else {
            EmailInputErrorHbox.setVisible(false);
        }

        // Vérification du montant
        if (montant.getText().isEmpty()) {
            montantInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            montantInputErrorHbox.setVisible(false);
        }

        // Vérification du numéro de téléphone
        if (tel.getText().isEmpty() || !ValidSaisie.isValidNumber(tel.getText())) {
            telInputErrorHbox.setVisible(true);
            telInputError.setText("Le numéro de téléphone doit commencer par 2 ou 5 ou 9 et contenir 8 chiffres");
            champsInvalides = true;
        } else {
            telInputErrorHbox.setVisible(false);
        }

        // Vérification si au moins un champ est invalide
        if (champsInvalides) {
            return; // Arrêter le traitement si des champs sont invalides
        }

        // Select for combo
        String selectedBeneficiaire = beneficiaire.getSelectionModel().getSelectedItem();
        // Date Now
        Date selectedDate = Date.valueOf(java.time.LocalDate.now());
        // Create a new instance of cheque from View
        String image=imageName;
//        Integer Rib = 345678644;
        String telText = tel.getText();
        Integer aa = Integer.parseInt(telText);
        String cin = Cin.getText();
        Integer Cin = Integer.parseInt(cin);
        String Nom = NometPrenom.getText();
        String beneficiairee = beneficiaire.getValue();
        Double montantn = Double.valueOf(montant.getText());
        String email = Email.getText();
        String decision = "encours";

        Cheque cheque = new Cheque(beneficiairee,montantn,aa,email,Cin,Nom, selectedDate,imageName,decision,1,1,new BigDecimal(user.getRib()));


        ServiceCheque serviceCheque = new ServiceCheque();
        String pdfFilePath = createPdfCheque(cheque);

       sendEmail(Email.getText(), "Cheque Confirmation", "Please find attached the PDF for your cheque.", pdfFilePath);


        serviceCheque.ajouterS(cheque);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
        Pane demandeChequeListParent = loader.load();

        // Remplacer le contenu de content_area par le contenu de la liste des demandes de chèques
        content_area.getChildren().setAll(demandeChequeListParent);

    }

    private String createPdfCheque(Cheque cheque) throws Exception {
        Document document = new Document();
        String pdfFileName = "cheque_" + UUID.randomUUID().toString() + ".pdf";
        String relativePath = "src/main/resources/pdf/";
        String outputPath = System.getProperty("user.dir") + "/" + relativePath + pdfFileName;
        // Écrire le contenu de l'article dans le document PDF
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();
        // Créer un cadre autour de tout le document
        Rectangle rectangle = new Rectangle(document.getPageSize());
        rectangle.setBorder(Rectangle.BOX);
        rectangle.setBorderWidth(2);
        rectangle.setBorderColor(new BaseColor(107, 175, 84)); // Vert foncé
        PdfContentByte canvas = writer.getDirectContent();
        canvas.rectangle(rectangle);

        // Logo
        String userDir = System.getProperty("user.dir");
        String imagePath = userDir + "/src/main/resources/img/logo.png";
        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(imagePath);
        logo.scaleToFit(100, 100);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        // Espacement vertical avant le titre
        document.add(Chunk.NEWLINE);

        // Titre
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 32, Font.BOLD, new BaseColor(107, 175, 84)); // Vert foncé
        Paragraph title = new Paragraph("Demande de Chèque", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        // title.setBorder(Rectangle.NO_BORDER);
        title.setSpacingAfter(20f); // Espacement après le titre
        document.add(title);

        // Décoration autour du titre
        LineSeparator line = new LineSeparator();
        line.setLineColor(new BaseColor(107, 175, 84)); // Couleur de la ligne
        line.setLineWidth(2f); // Épaisseur de la ligne
        line.setPercentage(100f); // Longueur de la ligne
        document.add(line);

        // Espacement vertical après le titre
        document.add(Chunk.NEWLINE);

        // Détails du chèque
        Font detailsFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
        document.add(new Paragraph("Voici les Détails du Chéque :  ", detailsFont));
        document.add(new Paragraph("Nom et Prénom: " +  cheque.getNom_prenom(), detailsFont));
        document.add(new Paragraph("Email: " + cheque.getEmail(), detailsFont));
        document.add(new Paragraph("CIN: " + cheque.getCin(), detailsFont));
        document.add(new Paragraph("Bénéficiaire: " + cheque.getBeneficiaire(), detailsFont));
        document.add(new Paragraph("Montant: " +cheque.getMontant(), detailsFont));
        document.add(new Paragraph("Téléphone: " + cheque.getTelephone(), detailsFont));
        document.add(new Paragraph("Date: " + cheque.getDate(), detailsFont));

        // Espacement vertical après les détails
        document.add(Chunk.NEWLINE);

        document.close();
        return outputPath; // Return the path of the created PDF
    }
    private void sendEmail(String recipient, String subject, String text, String pdfFilePath) throws MessagingException, IOException {
        String from = "shayma.ouerhani@esprit.tn";
        final String username = "shayma.ouerhani@esprit.tn";
        final String password = "Sug12879";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Utiliser TLSv1.2
        props.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256"); // Exemple de suite de chiffrement

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(text);

        MimeBodyPart pdfAttachment = new MimeBodyPart();
        pdfAttachment.attachFile(new File(pdfFilePath));

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(pdfAttachment); // Attach the PDF

        message.setContent(multipart);
        Transport.send(message);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        telInputErrorHbox.setVisible(false);
        montantInputErrorHbox.setVisible(false);
        EmailInputErrorHbox.setVisible(false);
        cinInputErrorHbox.setVisible(false);
        NometPrenomInputErrorHbox.setVisible(false);
        beneficiaireInputErrorHbox.setVisible(false);
        DateInputErrorHbox.setVisible(false);
        NometPrenom.setText(user.getName());
        Cin.setText(user.getCin());
        Email.setText(user.getEmail());
        tel.setText(user.getTel());

        RIB.setText(String.valueOf(user.getRib()));
        System.out.println(rib);

        ObservableList<String> beneficiaires = FXCollections.observableArrayList(
                "Paiement",
                "PaiementEco",
                "Personne"
        );

        beneficiaire.setItems(beneficiaires);
    }

    public void RetourBackC(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
