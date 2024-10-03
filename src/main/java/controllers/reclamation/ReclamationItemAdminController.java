package controllers.reclamation;

import Entities.actualites.Reclamation;
import controllers.reponse.AjouterReponseAdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceReclamation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReclamationItemAdminController implements Initializable {

    @FXML
    private Label RecItemDateLabel;

    @FXML
    private Text RecItemDateText;

    @FXML
    private Text RecItemDepText;

    @FXML
    private ImageView RecItemImg;

    @FXML
    private Text RecItemMail;

    @FXML
    private Text RecItemObjet;

    @FXML
    private Label RecItemPieceJLabel;

    @FXML
    private Text RecItemPieceJText;

    @FXML
    private ImageView RecItemStateBtnImg;

    @FXML
    private Label RecItemStateLabel;

    @FXML
    private Text RecItemStateText;

    @FXML
    private Label RecItemUpdateBtn;

    @FXML
    private Text RecItemcontenu;

    @FXML
    private ImageView RecItemState;
    @FXML
    private Label RecPieceJBtn;

    @FXML
    private ImageView RecReplyBtnImg;

    @FXML
    private ImageView RecdetailBtnImg;

    @FXML
    private Label RecrItemStateBtn;

    @FXML
    private Label ReplyBtn;
    @FXML
    private ImageView RecItemPieceJ;
    @FXML
    private Pane content_area;
    public static int idAn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Reclamation reclamation) {
        ServiceReclamation serviceReclamation =new ServiceReclamation() ;
        Image image = new Image(getClass().getResource("/imagesAct/admin.png").toExternalForm());
        RecItemImg.setImage(image);
        Rectangle clip = new Rectangle(
                RecItemImg.getFitWidth(), RecItemImg.getFitHeight());
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        RecItemImg.setClip(clip);
//        RecItemPieceJ.setText(reclamation.getPiece_jrec());
        Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
        RecItemPieceJ.setImage(imageJ);
        RecItemDateText.setText(String.valueOf(reclamation.getDate_rec()));
        if (reclamation.getStatut_rec() == "En cours de traitement") {
            Image imagestat=new Image(getClass().getResource("/imagesAct/chargement.gif").toExternalForm());
            RecItemState.setImage(imagestat);
        } else { Image imgstat=new Image(getClass().getResource("/imagesAct/tick-mark.png").toExternalForm());

            RecItemState.setImage(imgstat);}

            RecItemDepText.setText(reclamation.getDep_rec());
        RecItemObjet.setText(reclamation.getObjet_rec());
        RecItemMail.setText(reclamation.getAdr_rec());
        RecItemPieceJ.setOnMouseClicked(this::openArticleImage);




    }
    private void openArticleImage(MouseEvent mouseEvent) {
       // String destinationFolder = "C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory"; // Chemin absolu du répertoire de destination
     //   Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploadsPdfH", reclamation.getPiece_jrec());
        Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", reclamation.getPiece_jrec());

        try {
            File file = destination.toFile();
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("File not found: " + destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void RecReplyBtn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/ajouterReponseAdmin.fxml"));

        try {

            Parent addResponseParent = loader.load();

            // Obtenez le contrôleur de vue d'ajout de réponse
            AjouterReponseAdminController controller = loader.getController();

            // Passez l'objet Reclamation actuel au contrôleur de vue d'ajout de réponse
            controller.initDataRec(this.reclamation);
            idAn=reclamation.getId();
            // Affichez le formulaire d'ajout de réponse dans un nouveau dialogue ou une nouvelle fenêtre
            Stage stage = new Stage();
            stage.setScene(new Scene(addResponseParent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Reclamation reclamation;
    public void initDataRec(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public void RecItemDelete(MouseEvent mouseEvent) {
        try {
            if (reclamation != null) {
                ServiceReclamation serviceReclamation = new ServiceReclamation();
                serviceReclamation.supprimer(reclamation);


            } else {
                // Affichez un message d'erreur ou faites une action appropriée si la réclamation est null
                System.err.println("La réclamation est null. Impossible de la supprimer.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void RecItemPieceJ(MouseEvent mouseEvent) {
    }
}