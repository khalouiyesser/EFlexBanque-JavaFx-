package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class AddEvenementController implements Initializable {

    @FXML
    private GridPane AddEvenementContainer;

    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/project/AddEvenementCard.fxml"));
            VBox EvenementContainer1 = fxmlLoader1.load();
            AddEvenementContainer.add(EvenementContainer1, 0, 1);
            GridPane.setMargin(EvenementContainer1, new Insets(0, 10, 25, 10));
            EvenementContainer1.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
