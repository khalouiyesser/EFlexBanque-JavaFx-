package controllers.offreDeStage;

import Entities.OffreDeStage;
import controllers.demandeStage.DemandeStageParOffreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsOffreController {

    public VBox content_area;
    @FXML
    private Text Description;

    @FXML
    private Text typeOffre;

    @FXML
    private Text Niveau;

    @FXML
    private Text Exigence;

    @FXML
    private Text poste;

    @FXML
    private Text langue;

    @FXML
    private Text experience;

    @FXML
    private Button Postuler;

    @FXML
    private Text MotCle;

    @FXML
    private Text datet1;

    @FXML
    private HBox DatePost;

    @FXML
    private ImageView img;

    @FXML
    private Text title;

    @FXML
    private Text productName;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void closeStage() {
        if (stage != null) {
            stage.close();
        }
    }

    public void initData(OffreDeStage offreDeStage) {
        title.setText(offreDeStage.getTitle());

        Description.setText(offreDeStage.getDescription());
        typeOffre.setText(offreDeStage.getTypeOffre());
//        Niveau.setText(String.valueOf(offreDeStage.getNiveau()));
        Exigence.setText(offreDeStage.getExigenceOffre());
        poste.setText(String.valueOf(offreDeStage.getPostePropose()));
        datet1.setText(String.valueOf(offreDeStage.getDatePostu()));
        MotCle.setText(String.valueOf(offreDeStage.getMotsCles()));

        // Load appropriate image into the ImageView
        // img.setImage(...);
        Postuler.setOnMouseClicked(mouseEvent -> {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/DemandeStageParOffre.fxml"));
            try {
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                primaryStage.setTitle("E-Flex Bank");
                primaryStage.setScene(scene);
                primaryStage.show();
                DemandeStageParOffreController demandeStageController = loader.getController();
                demandeStageController.initData(offreDeStage);
                demandeStageController.yy = offreDeStage.getPostePropose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void initialize() {
        // Initialize your controller
    }
}
