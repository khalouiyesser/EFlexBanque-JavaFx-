package controllers.demandeStage;


import Entities.DemandeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import services.ServiceDemandeStage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DemandeItemVisiteurController {

    @FXML
    private Text date;

    @FXML
    private ImageView imageStaut;

    @FXML
    private Hyperlink cv;

    @FXML
    private Text numero;

    @FXML
    private Label modifier;

    @FXML
    private Text domaine;

    @FXML
    private Text Lettre;

    @FXML
    private Text etat;


    @FXML
    private Label supprimer;

    @FXML
    private Text Nom;

    @FXML
    private Text prenom;

    @FXML
    private Text email;
    DemandeStage stage ;
    ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();

    @FXML
    void showCv(ActionEvent event) {
        Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", stage.getCv());
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
    public void initData(DemandeStage demandeStage){
        this.stage = demandeStage;
        Nom.setText(demandeStage.getNom());
        prenom.setText(demandeStage.getPrenom());
        email.setText(demandeStage.getEmail());

        numero.setText(String.valueOf(demandeStage.getNumeroTelephone()));

        String typeText = demandeStage.getLettremotivation() != null && demandeStage.getLettremotivation().length() > 30 ? demandeStage.getLettremotivation().substring(0, 30) : demandeStage.getLettremotivation();


        Lettre.setText(typeText);
        domaine.setText(demandeStage.getDomaine());
        cv.setText(demandeStage.getCv());

//        cv.setVisible(false);
//        String img = null;
//        Path destination = Paths.get(System.getProperty("user.dir"), "src", "main","resources","img" ,img);

        if (demandeStage.getEtat() == "encours"){
            etat.setText("encours");
            etat.setFill(Color.YELLOW);

        }else if (demandeStage.getEtat() == "accepter"){
            etat.setText("accepté");
            etat.setFill(Color.GREEN);
        }else {
            etat.setText("réfusé");
            etat.setFill(Color.RED);
        }
        modifier.setOnMouseClicked(mouseEvent -> {

        });
        supprimer.setOnMouseClicked(mouseEvent -> {
            try {
                serviceDemandeStage.supprimer(demandeStage.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}