package controllers.commentaireArticle;

import Entities.actualites.CommentaireHadhemi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceCommentaireHadhemi;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListCommentAdminController implements Initializable {

    @FXML
    private VBox commentListContainer;

    @FXML
    private Text commentListTitle;

    @FXML
    private Pane content_area;

    @FXML
    private HBox commentTableHead;

    @FXML
    private ComboBox<?> triecommentInput;

    @FXML
    void returnBackListArt(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/listArticleAdmin.fxml"));
            Pane listComAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin

            content_area.getChildren().setAll(listComAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCommentaireHadhemi sch = new ServiceCommentaireHadhemi() ;
        List<CommentaireHadhemi> list = new ArrayList<>();
        try {
            list = sch.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // userListContainer.getChildren().add();
//           OffreStageItem offreStageItem = new OffreStageItem();
//            userListContainer.getChildren().add(offreStageItem.initE());

        for (CommentaireHadhemi commentaireHadhemi : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/commentaire/commentaireItemAdmin.fxml"));
                Parent offreItem = loader.load();
                CommentaireItemAdminController ComItem = loader.getController();
                ComItem.initData(commentaireHadhemi);
                commentListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
