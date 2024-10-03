//package controllers;
//
//import Entities.actualites.Reclamation;
//import Entities.actualites.Reponse;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Text;
//import services.ServiceReclamation;
//import services.ServiceReponse;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class ReponseItemAdminController implements Initializable {
//
//
//    @FXML
//    private Text ContenuRep;
//
//    @FXML
//    private Text dateRep;
//
//    @FXML
//    private ImageView deleteRep;
//
//    @FXML
//    private HBox deleterep;
//
//    @FXML
//    private ImageView editRep;
//
//    @FXML
//    private HBox editrep;
//
//    @FXML
//    private HBox itemRep;
//
//    @FXML
//    private Text mailRep;
//    @FXML
//    private Text dateRec;
//    @FXML
//    private ImageView piecejrep;
//    private Reponse reponse;
//
//    @FXML
//    void deleteRep(MouseEvent event) {
//        try {
//            if (reponse != null) {
//                ServiceReponse serviceReponse = new ServiceReponse();
//                serviceReponse.supprimer(reponse);
//
//
//            } else {
//                // Affichez un message d'erreur ou faites une action appropriée si la réclamation est null
//                System.err.println("La réponse est null. Impossible de la supprimer.");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @FXML
//    void editRep(MouseEvent event) {
//
//    }
//
//    public void initData(Reponse reponse) {
//
////        RecItemPieceJ.setText(reclamation.getPiece_jrec());
//        Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
//        piecejrep.setImage(imageJ);
//        dateRep.setText(String.valueOf(reponse.getDate_rep()));
////        mailRep.setText(reponse.getAdr_rep());
//        ContenuRep.setText(reponse.getContenu_rep());
//        dateRec.setText(String.valueOf(reponse.getReclamation().getDate_rec()));
//        mailRep.setText(reponse.getReclamation().getAdr_rec());
//
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//
//    }
//}
package controllers.reponse;

import Entities.actualites.Reponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceReponse;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class ReponseItemAdminController{




    @FXML
    private Text ContenuRep;

    @FXML
    private Text dateRec;

    @FXML
    private Text dateRep;

    @FXML
    private ImageView deleterep;

    @FXML
    private HBox deleteRep;

    @FXML
    private ImageView editRep;

    @FXML
    private HBox editrep;

    @FXML
    private HBox itemRep;

    @FXML
    private Text mailRep;

    @FXML
    private ImageView piecejrep;
    private ReponseItemAdminController reponse;


    public void setreponse(ReponseItemAdminController reponse) {
        this.reponse = reponse;
    }

    public void initData(Reponse reponse) {
        ServiceReponse serviceReponse = new ServiceReponse();

        if (reponse != null) {
            Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
            piecejrep.setImage(imageJ);
            dateRep.setText(String.valueOf(reponse.getDate_rep()));

            ContenuRep.setText(reponse.getContenu_rep());
            dateRec.setText(String.valueOf(reponse.getReclamation().getDate_rec()));
            mailRep.setText(reponse.getReclamation().getAdr_rec());
            editRep.setId(String.valueOf(reponse.getId()));
            piecejrep.setOnMouseClicked(event -> {
                Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", reponse.getPiece_jrep());

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
            });
            editRep.setOnMouseClicked(event -> {
                System.out.println("reponse contenu: " + reponse.getContenu_rep());
                ListRepAdminController.setUpdateRepModelShow(1);
                ListRepAdminController.setArticleEmailToUpdate(reponse.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/listRepAdmin.fxml"));
                try {
                    Parent root = loader.load();
                    Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                    contentArea.getChildren().clear();
                    contentArea.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }



            });

            deleterep.setId(String.valueOf(reponse.getId()));
            deleterep.setOnMouseClicked(event -> {
                System.out.println("ID de l'article à supprimer : " + reponse.getId());
                try {
                    serviceReponse.supprimerParId(reponse.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/listRepAdmin.fxml"));
                try {
                    Parent root = loader.load();

                    Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                    contentArea.getChildren().clear();
                    contentArea.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    @FXML
    void deleteRep(MouseEvent event) {


    }

    @FXML
    void editRep(MouseEvent event) {

    }

    public void piecejrep(MouseEvent mouseEvent) {

    }
}