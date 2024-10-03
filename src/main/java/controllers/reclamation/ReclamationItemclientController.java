package controllers.reclamation;

import Entities.actualites.Reclamation;
import controllers.reponse.ListRepClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceReclamation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class ReclamationItemclientController {

    @FXML
    private Text ObjetRec;

    @FXML
    private ImageView show_rep;

    @FXML
    private Text contenuArt;

    @FXML
    private HBox contenuRec;

    @FXML
    private Text dateRec;

    @FXML
    private ImageView deleteRecBtn;

    @FXML
    private Text depRec;

    @FXML
    private ImageView editRecBtn;

    @FXML
    private HBox objetrec;

    @FXML
    private ImageView statRec;

    @FXML
    private ImageView telechargerPieceBtn;

    @FXML
    private ImageView voirPieceBtn;
    public static int idAn;


    public void initData(Reclamation reclamation) {
        ServiceReclamation serviceReclamation = new ServiceReclamation();

        Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
        voirPieceBtn.setImage(imageJ);
        dateRec.setText(String.valueOf(reclamation.getDate_rec()));
        //  RecItemStateText.setText(reclamation.getStatut_rec());
        voirPieceBtn.setOnMouseClicked(this::openArticleImage);
        contenuArt.setText(reclamation.getContenu_rec());
        depRec.setText(reclamation.getDep_rec());
        ObjetRec.setText(reclamation.getObjet_rec());
        if (reclamation.getStatut_rec().equals("En cours de traitement")) {
            Image imagestat = new Image(getClass().getResource("/imagesAct/chargement.gif").toExternalForm());
            statRec.setImage(imagestat);
        } else {
            Image imgstat = new Image(getClass().getResource("/imagesAct/tick-mark.png").toExternalForm());

            statRec.setImage(imgstat);
        }
        deleteRecBtn.setOnMouseClicked(event -> {
            System.out.println("ID du commentaire à supprimer : " + reclamation.getId());
//            try {
//                serviceReclamation.supprimer(reclamation);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listeRecClient.fxml"));
//            try {
//                Parent root = loader.load();
//
//                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
//
//                contentArea.getChildren().clear();
//                contentArea.getChildren().add(root);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

            // Afficher une alerte de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette réclamation ?");

            // Attendre la réponse de l'utilisateur
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // L'utilisateur a confirmé la suppression, supprimer la réclamation
                    System.out.println("ID de la réclamation à supprimer : " + reclamation.getId());
                    try {
                        serviceReclamation.supprimer(reclamation);
                        // Rafraîchir la vue après la suppression

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // L'utilisateur a annulé la suppression, ne rien faire
                    System.out.println("Suppression annulée");
                }
            });
        });


        //RecItemMail.setText(reclamation.getAdr_rec());
        //RecItemPieceJ.setOnMouseClicked(this::openArticleImage);

    }
    // Méthode pour rafraîchir la vue après la suppression
    private void refreshView(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listeRecClient.fxml"));
        try {
            Parent root = loader.load();
            Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    private void openArticleImage(MouseEvent mouseEvent) {
        Path destination = Paths.get("C:/Users/Yesser/PI/InnovatixYesser/public/uploads_directory/", reclamation.getPiece_jrec());

        try {
            File file = destination.toFile();
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("Fichier introuvable : " + destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Reclamation reclamation;

    public void initDataRec(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public void deleteRecBtn(MouseEvent mouseEvent) {
    }

    public void show_rep(MouseEvent mouseEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/listRepClient.fxml"));
//
//        try {
//
//            Parent addResponseParent = loader.load();
//
//            // Obtenez le contrôleur de vue d'ajout de réponse
//            ListRepClientController controller = loader.getController();
//
//
//            // Passez l'objet Reclamation actuel au contrôleur de vue d'ajout de réponse
//            controller.initDataRec(this.reclamation);
//            System.out.println(this.reclamation);
//            idAn=reclamation.getId();
//            controller.AfficherReponses(idAn);
////             Affichez le formulaire d'ajout de réponse dans un nouveau dialogue ou une nouvelle fenêtre
//            Stage stage = new Stage();
//            stage.setScene(new Scene(addResponseParent));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // Charger la vue de la liste des réponses
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/listRepClient.fxml"));
        Parent addResponseParent = loader.load();

        // Obtenir le contrôleur de la vue de la liste des réponses
        ListRepClientController controller = loader.getController();

        // Passer l'objet Reclamation actuel au contrôleur de la vue de la liste des réponses
        controller.initDataRec(this.reclamation);

        // Récupérer l'ID de la réclamation sélectionnée
        int idReclamation = this.reclamation.getId();

        // Afficher la liste des réponses correspondant à l'ID de la réclamation sélectionnée
        controller.AfficherReponses(idReclamation);

        // Afficher la vue de la liste des réponses dans une nouvelle fenêtre
        Stage stage = new Stage();
        stage.setScene(new Scene(addResponseParent));
        stage.show();

    }

}
