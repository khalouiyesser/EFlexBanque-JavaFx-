package controllers.reponse;

import Entities.actualites.Reponse;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import services.ServiceReponse;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReponseItemController {
    @FXML
    private Text ContenuRep;

    @FXML
    private Text dateRep;

    @FXML
    private HBox itemRep;

    @FXML
    private Text mailRep;

    @FXML
    private ImageView piecejrep;

    public void piecejrep(MouseEvent mouseEvent) {
    }

    public void initData(Reponse reponse) {

        ServiceReponse serviceReponse = new ServiceReponse();

        if (reponse != null) {
            Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
            piecejrep.setImage(imageJ);
            dateRep.setText(String.valueOf(reponse.getDate_rep()));

            ContenuRep.setText(reponse.getContenu_rep());
//            mailRep.setText(reponse.getReclamation().getAdr_rec());
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


        }
    }
}