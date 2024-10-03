package controllers.Compte;

// import controllers.FrontControlleur;
import controllers.FrontControlleur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.EventObject;


public class Succes {


    @FXML
    private AnchorPane content_area;
    @FXML
    private Button okBtn;

    private EventObject event;

    @FXML
    public void retourFront(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Front.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        FrontControlleur frontControlleur = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);

        // Fermer la fenêtre actuelle
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }

    }

