package controllers.offreDeStage;

import Entities.OffreDeStage;
import controllers.demandeStage.DemandeStageParOffreController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Symfony;

import java.io.IOException;

public class DetailsBackController {

    public Button recommender;
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
    private Text productName;
    OffreDeStage offreDeStage;


    public void initData(OffreDeStage offreDeStage) {
//        this.offreDeStage = offreDeStage;
        recommender.setId(String.valueOf(1));
        Description.setText(offreDeStage.getDescription());
        typeOffre.setText(offreDeStage.getTypeOffre());
        Niveau.setText(String.valueOf(offreDeStage.getNiveau()));
        Exigence.setText(offreDeStage.getExigenceOffre());
        poste.setText(String.valueOf(offreDeStage.getPostePropose()));
        datet1.setText(String.valueOf(offreDeStage.getDatePostu()));
        offreDeStage.setPostePropose(2);
//        Postuler.setOnMouseClicked(mouseEvent -> {
//
//            System.out.println("work");
//            Symfony symfony = new Symfony();
//            symfony.Recommendation(offreDeStage.getPostePropose());
//        });
        recommender.setOnMouseClicked(mouseEvent -> {
            System.out.println("work");
            Symfony symfony = new Symfony();
            System.out.println(offreDeStage.getPostePropose());
            symfony.Recommendation(offreDeStage.getPostePropose());

        });


    }

    public void recommander(ActionEvent actionEvent) {
        System.out.println("yesser");
        Symfony symfony = new Symfony();
        symfony.Recommendation(1);

    }

    public void recommender(MouseEvent mouseEvent) {
    }
}