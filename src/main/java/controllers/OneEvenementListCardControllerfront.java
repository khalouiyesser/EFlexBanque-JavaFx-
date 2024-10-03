package controllers;

import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.IService;
import services.ServiceEvenement;

import java.io.IOException;

public class OneEvenementListCardControllerfront {


    @FXML
    private Text showEvenementP;

    @FXML
    private ImageView img;
    @FXML
    private HBox deleteEvenement;

    @FXML
    private HBox editEvenement;

    @FXML
    private HBox addC;
    @FXML
    private Text productName;


    @FXML
    private Text descfx;

    @FXML
    private Text pricefx;
    @FXML
    private Text pricefx1;

    @FXML
    private Text offrefx;
    @FXML
    private Text Datefx;

    @FXML
    private Text lieufx;
    @FXML
    private Text stockProduit;
    @FXML
    private HBox qrCodeEvenement;
    @FXML
    private HBox offerEvenement;
    @FXML
    private HBox priceHbox;
    @FXML
    private HBox showEvenement;
    IService EvenementService = new ServiceEvenement();
    public void setEvenementData(Evenement evenement) {

        showEvenement.setId(String.valueOf(evenement.getId()));
        showEvenement.setOnMouseClicked(event -> {
            System.out.println("ID du Evenement : " + evenement.getId());
            Evenement.setIdEvenement(evenement.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/ShowEvenementCard.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Image image = new Image(
                getClass().getResource("/assets/ProductUploads/" + evenement.getImg()).toExternalForm());
        img.setImage(image);
        productName.setText(evenement.getNom());
        descfx.setText(evenement.getDescription());
        lieufx.setText(evenement.getLieu());
        pricefx.setText(String.valueOf(evenement.getPrix()));
        pricefx1.setText(String.valueOf(evenement.getPrix()));
        offrefx.setText(String.valueOf(evenement.getRemise()));
        Datefx.setText(String.valueOf(evenement.getDateDebut()));

        addC.setId(String.valueOf(evenement.getId()));
        addC.setOnMouseClicked(event -> {
            System.out.println("Add comment of this event : " + evenement.getId());
            Evenement.setIdEvenement(evenement.getId());
            Evenement.actionTest = 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/AddComment.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        showEvenementP.setId(String.valueOf(evenement.getId()));
        showEvenementP.setOnMouseClicked(event -> {
            System.out.println("ID du Evenement : " + evenement.getId());
            Evenement.setIdEvenement(evenement.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/eventPayment.fxml"));
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
