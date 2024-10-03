package controllers.offreDeStage;

import Entities.OffreDeStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceOffreDeStage;

import java.io.IOException;
import java.sql.SQLException;

public class OffreStageVisiteurItemController {

    //    @FXML
//    private Text date;
//
//    @FXML
//    private Text Titre;
//
//    @FXML
//    private Text Description;
//
//    @FXML
//    private Button VoirDetail;
    @FXML
    private Text Titre;

    @FXML
    private Text Text;

    @FXML
    private Button details;


    public void initData(OffreDeStage offreDeStage) {
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
//        String a = offreDeStage.getExperience().substring(0, 200);
        details.setId(String.valueOf(offreDeStage.getPostePropose()));
//        date.setText(String.valueOf(offreDeStage.getDatePostu()));
//        Text.setText(offreDeStage.getDescription().substring(0,200));
        Text.setText(offreDeStage.getDescription().substring(0, Math.min(offreDeStage.getDescription().length(), 200)));
        Titre.setText(offreDeStage.getTitle());

        details.setOnMouseClicked(mouseEvent -> {
            Stage primaryStage = new Stage();
            try {
                OffreDeStage offreDeStage1 = serviceOffreDeStage.afficheUne(offreDeStage.getPostePropose());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/DetailsOffre.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                primaryStage.setTitle("E-Flex Bank");
                primaryStage.setScene(scene);
                primaryStage.show();
                DetailsOffreController detailsOffreController = loader.getController();
                detailsOffreController.initData(offreDeStage1);
            } catch (SQLException | IOException e) {
                e.printStackTrace(); // Gérer l'exception de manière appropriée ici
            }
        });
    }
}