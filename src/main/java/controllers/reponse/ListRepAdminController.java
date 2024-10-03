package controllers.reponse;

import Entities.actualites.Reponse;
import controllers.article.UpdateArtcileCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceReponse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListRepAdminController implements Initializable {

    @FXML
    private HBox updateArticleModel;
    @FXML
    private ImageView backBtn;

    @FXML
    private Pane content_area;

    @FXML
    private VBox repListContainer;
    @FXML
    private VBox updateArticleModelContent;
    @FXML
    private Text repListTitle;

    @FXML
    private HBox repTableHead;

    @FXML
    private ComboBox<?> trierepInput;
    private static int updaterepModelShow = 0;
    private static int ShowProjectModelShow = 0;
    private static int RepIdToUpdate = 0;
    private final ServiceReponse serviceReponse = new ServiceReponse();

    public static void setUpdateRepModelShow(int updaterepModelShow) {
        ListRepAdminController.updaterepModelShow = updaterepModelShow;
    }
    public static void setShowArticleModelShow(int ShowArticleModelShow) {
        ListRepAdminController.ShowProjectModelShow = ShowArticleModelShow;
    }
    public static void setArticleEmailToUpdate(int RepIdToUpdate) {
        ListRepAdminController.RepIdToUpdate = RepIdToUpdate;
    }
    public static int getUpdateRepModelShow() {
        return updaterepModelShow;
    }
    @FXML
    void close_updateProjectModel(MouseEvent event) {
        updateArticleModel.setVisible(false);
        updaterepModelShow = 0;
    }
    @FXML
    private VBox updateRepModelContent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Reponse reponse;

        if (ListRepAdminController.getUpdaterepModelShow() == 0) {
            updateArticleModel.setVisible(false);
        } else if (ListRepAdminController.getUpdaterepModelShow() == 1) {
            updateArticleModel.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/reponse/updateReponseCard.fxml"));
            VBox updateRepform;
            try {
                updateRepform = fxmlLoader1.load();
                UpdateReponseCardController updateRepCardControllerr = fxmlLoader1.getController();
                UpdateArtcileCardController.setFxmlToLoad("listRepAdmin.fxml");
                reponse = serviceReponse.getOneProject(RepIdToUpdate);
                updateRepCardControllerr.setProjectUpdateData(reponse);
                updateArticleModelContent.getChildren().add(updateRepform);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        refreshReponseList();

    }

    private void refreshReponseList() {
        // Nettoyer le contenu actuel
        repListContainer.getChildren().clear();

        try {
            // Charger à nouveau la liste des articles depuis la base de données
            List<Reponse> reponses = serviceReponse.afficher();

            // Charger à nouveau les cartes d'articles dans le conteneur
            load();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }

    }

    @FXML
    void returnBack(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listRecAdmin.fxml"));
            Pane listRecAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listRecAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load()
    {
        ServiceReponse sr = new ServiceReponse() ;

        List<Reponse> list = new ArrayList<>();
        try {
            list = sr.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // userListContainer.getChildren().add();
//           OffreStageItem offreStageItem = new OffreStageItem();
//            userListContainer.getChildren().add(offreStageItem.initE());

        for (Reponse reponse : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponse/reponseItemAdmin.fxml"));
                Parent offreItem = loader.load();

                ReponseItemAdminController RepItem = loader.getController();
                RepItem.initData(reponse);
                repListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static int getUpdaterepModelShow() {
        return updaterepModelShow;
    }

}