package controllers.reponse;

import Entities.actualites.Reclamation;
import Entities.actualites.Reponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceReponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListRepClientController {

    @FXML
    private VBox RepListContainer;

    @FXML
    private HBox articleaddBtn;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<?> inputRectrie;

    @FXML
    private HBox list_rep;

    @FXML
    private HBox recTableHead;


    @FXML
    private HBox repTableHead;
    private Reclamation reclamation;

    public void AfficherReponses(int idReclamation) {
        ServiceReponse sr = new ServiceReponse();
        List<Reponse> listReponses = new ArrayList<>();
        listReponses = sr.afficherReponsesParIdReclamation(idReclamation);

        for (Reponse reponse : listReponses) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/reponseItem.fxml"));
                Parent reponseItem = loader.load();
                ReponseItemController reponseItemController = loader.getController();

                // Initialisez les données de la réponse pour chaque élément de réponse
                reponseItemController.initData(reponse);

                RepListContainer.getChildren().add(reponseItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void initDataRec(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

}
