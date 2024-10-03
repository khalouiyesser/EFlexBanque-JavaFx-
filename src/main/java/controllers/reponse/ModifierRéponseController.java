package controllers.reponse;

import Entities.actualites.Reponse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceReponse;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ModifierRéponseController implements Initializable {

    @FXML
    private Text addpieceJBtn;

    @FXML
    private TextArea contenuInput;

    @FXML
    private Text contenuInputError;

    @FXML
    private HBox contenuInputErrorBox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text pieceJRepInputError;

    @FXML
    private HBox pieceJointeInputErrorBox;

    @FXML
    private Button reponseBtn;
    private Reponse reponse;

    @FXML
    void addHistory(MouseEvent event) {

    }

    @FXML
    void ajouter_image(MouseEvent event) {

    }

    public void initData(Reponse reponse) {
        this.reponse=reponse;
        initializeRepFields();
    }

    private void initializeRepFields() {
        if (reponse != null) {
            contenuInput.setText(reponse.getContenu_rep());

        }
    }
    @FXML
    void reponseBtn(MouseEvent mouseEvent) {
        try {


            // Vos opérations de validation et de modification de l'article ici...

            if (reponse != null) {
                // Mettre à jour les données de l'article avec les nouvelles valeurs
                reponse.setContenu_rep(contenuInput.getText());
                LocalDateTime dateTime = LocalDateTime.now();
                reponse.setDate_rep(dateTime);

                ServiceReponse serviceReponse = new ServiceReponse();
                serviceReponse.modifier(reponse);


                Stage stage = (Stage) contenuInput.getScene().getWindow();
                stage.close();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeRepFields();}
}
