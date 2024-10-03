package controllers.Credit;

import Entities.Credit;
import Entities.RDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceCredit;
import services.ServiceRdv;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DemandeRdvListClient implements Initializable {

    @FXML
    private Button addcreditbtn;

    @FXML
    private Pane content_area;

    @FXML
    private Button deletebtn;

    @FXML
    private ComboBox<?> statusInput;

    @FXML
    private VBox userListContainer;

    @FXML
    private HBox userTableHead;

    @FXML
    void getrdv(MouseEvent event) throws IOException {
        System.out.println("Opening SensSMS page...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/senssms.fxml"));
        Parent root = loader.load();

        // Create a new stage for the SensSMS page
        Stage newStage = new Stage();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void deletecredit(MouseEvent event) {

    }

    @FXML
    void statusChange(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("zeeb");
        ServiceRdv serviceRdv = new ServiceRdv();
        System.out.println("f");
        List<RDV> list = new ArrayList<>();
        try {
            list = serviceRdv.afficher(); // Notez le changement ici
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (RDV rdv : list) {
            try {
                System.out.println("rrrrrrrrrrrrrrrr");
                System.out.println(rdv);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/rdvitems.fxml"));
                Parent offreItem = loader.load();
                Rdvitems offreStageItem = loader.getController();
                offreStageItem.initData(rdv);
                userListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }private Stage stage;
    private Scene scene;
    private Parent root;



}