package controllers.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class frontController {

    @FXML
    private Text Login;

    @FXML
    private VBox userPane;

    @FXML
    void Tologin(MouseEvent event) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        LoginController ajoutUserController = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        userPane.getChildren().clear();
        userPane.getChildren().add(addArticleParent);


    }}

