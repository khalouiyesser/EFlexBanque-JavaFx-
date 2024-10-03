package controllers;

import Entities.Cheque;
//import Entities.User;
import Entities.User;
import controllers.Cheque.DemandeChequeListClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceCheque;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.awt.Desktop;
import java.util.ResourceBundle;

public class ChequeItemsController implements Initializable {

    @FXML
    private Text date;

    @FXML
    private Text userItemEmail;

    @FXML
    private Text personne;
    @FXML
    private Text montant;
    @FXML
    private HBox Pdf;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text userItemTel;

    @FXML
    private Text hhh;

    @FXML
    private HBox userItemUpdateBtn;

    @FXML
    private Text userItemRole;

    @FXML
    private Text productName;

    @FXML
    private Text userItemName;

    @FXML
    private HBox userItemStateBtn;
    @FXML
    private Node pdf;

    @FXML
    private HBox shayma;

    public static User user;
    private Cheque cheque;


    public void initData(Cheque i) {

        this.cheque = i;
        ServiceCheque serviceCheque = new ServiceCheque();

        userItemUpdateBtn.setId(String.valueOf(i.getId()));

        userItemStateBtn.setId(String.valueOf(i.getId()));


        userItemEmail.setText(String.valueOf(i.getCin()));
        // userItemName.setText(String.valueOf(user.g()));
        userItemTel.setText(i.getNom_prenom());
        userItemRole.setText(i.getEmail());
        userItemStateText.setText(i.getDecision());
        date.setText(String.valueOf(i.getDate()));
        hhh.setText(String.valueOf(i.getTelephone()));
        personne.setText(i.getBeneficiaire().toString());
        montant.setText(String.valueOf(i.getMontant()));
        if ("Approuvé".equals(cheque.getDecision()) || "Rejeté".equals(cheque.getDecision())) {
            disableDecisionButtons();
            shayma.setOpacity(0); // Rendre le HBox shayma invisible
        }

        userItemUpdateBtn.setOnMouseClicked(event -> {
            System.out.println("Cheque Name: " + i.getNom_prenom());
            DemandeChequeListClient.setupdateChequeModelShow(1);
            DemandeChequeListClient.setchequeEmailToUpdate(i.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        userItemStateBtn.setOnMouseClicked(event -> {
            System.out.println("Cheque Name: " + i.getNom_prenom());
            try {
                serviceCheque.supprimer(Integer.parseInt(userItemStateBtn.getId()));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
            try {
                Parent root = loader.load();

                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        pdf.setOnMouseClicked(event -> {
            if (i != null) {
                Document document = new Document();
                try {
                    String pdfFileName = "cheque.pdf";
                    // Spécifier le chemin relatif où le PDF sera enregistré
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
                    document.add(new Paragraph("Nom et Prénom: " + userItemTel.getText(), detailsFont));
                    document.add(new Paragraph("Email: " + userItemRole.getText(), detailsFont));
                    document.add(new Paragraph("CIN: " + userItemEmail.getText(), detailsFont));
                    document.add(new Paragraph("Bénéficiaire: " + personne.getText(), detailsFont));
                    document.add(new Paragraph("Montant: " + montant.getText(), detailsFont));
                    document.add(new Paragraph("Téléphone: " + hhh.getText(), detailsFont));
                    document.add(new Paragraph("Date: " + date.getText(), detailsFont));

                    // Espacement vertical après les détails
                    document.add(Chunk.NEWLINE);

                    // Fermer le document
                    document.close();
                    System.out.println("PDF enregistré avec succès !");

                    // Ouvrir le PDF avec l'application par défaut du système
                    File pdfFile = new File(outputPath);
                    Desktop.getDesktop().open(pdfFile);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Erreur lors de la création ou de l'ouverture du PDF.");
                }
            } else {
                System.err.println("Le Chèque n'est pas initialisé.");
            }
        });
    }


    public void UpdateCheque(MouseEvent mouseEvent) {

    }

    public void DeleteCheque(MouseEvent mouseEvent) {
    }

    public void genererPDF(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (cheque != null && ("Approuvé".equals(cheque.getDecision()) || "Rejeté".equals(cheque.getDecision()))) {
            shayma.setOpacity(0); // Rendre le HBox invisible en réglant l'opacité à 0
        }
    }

    private void disableDecisionButtons() {
        userItemStateText.setDisable(true);
        userItemStateText.setDisable(true);
        shayma.setOpacity(0.4);  // Optionnel : définir l'opacité pour indiquer visuellement que le bouton est désactivé
        shayma.setOpacity(0.4);    // Optionnel : définir l'opacité pour indiquer visuellement que le bouton est désactivé


    }
}