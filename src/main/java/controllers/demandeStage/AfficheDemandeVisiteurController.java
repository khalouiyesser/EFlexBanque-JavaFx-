package controllers.demandeStage;



import Entities.DemandeStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ServiceDemandeStage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficheDemandeVisiteurController implements Initializable {

    @FXML
    private HBox userTableHead;

    @FXML
    private VBox userListContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceDemandeStage projectService = new ServiceDemandeStage();
        List<DemandeStage> list = new ArrayList<>();
        try {
            list = projectService.afficher();
            for (DemandeStage i : list){
//                    System.out.println(i.getId());
            }
//                System.out.println(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (DemandeStage offre : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/DemandeItemVisiteur.fxml"));
                Parent offreItem = loader.load();
                DemandeItemVisiteurController offreStageItem = loader.getController();
                offreStageItem.initData(offre);
                userListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}