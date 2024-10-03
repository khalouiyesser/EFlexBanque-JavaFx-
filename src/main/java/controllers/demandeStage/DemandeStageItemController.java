package controllers.demandeStage;

import Entities.DemandeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import services.Symfony;
import utils.MyDatabase;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DemandeStageItemController {


    @FXML
    private Text date;

    @FXML
    private Hyperlink cv;


    @FXML
    private Text numero;

    @FXML
    private Label Approuver;

    @FXML
    private Text domaine;

    @FXML
    private Label refuser;

    @FXML
    private Text Nom;

    @FXML
    private Text prenom;

    @FXML
    private Text email;

    @FXML
    private Text Lettre;
    DemandeStage stage ;
    @FXML
    void showCv(ActionEvent event) {

            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", stage.getCv());
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
    }
    Symfony symfony = new Symfony();

    public void initData(DemandeStage offre) {
        this.stage = offre;
        prenom.setText(offre.getPrenom());
        Nom.setText(offre.getNom());
        email.setText(offre.getEmail());
        numero.setText(String.valueOf(offre.getNumeroTelephone()));
        domaine.setText(offre.getDomaine());
        date.setText(String.valueOf(offre.getDate()));
        String titreText = offre.getLettremotivation() != null && offre.getLettremotivation().length() > 30 ? offre.getLettremotivation().substring(0, 30) : offre.getLettremotivation();
        Lettre.setText(titreText);
        cv.setText(offre.getCv());
        Approuver.setOnMouseClicked(mouseEvent -> {
//            System.out.println(offre.getId());
            // code lorsqu'approuve
            String updateQuery = "UPDATE demandestage SET etat = ? WHERE id = ?";
            Connection connection = MyDatabase.getInstance().getConnection(); // Reestablishing connection
            try (PreparedStatement st = connection.prepareStatement(updateQuery)) {
                st.setString(1, "accepter");
                st.setInt(2, offre.getId());
                st.executeUpdate();
                symfony.mailingApprouver(offre.getEmail(),offre.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        refuser.setOnMouseClicked(mouseEvent -> {
            // code lorsqu'il réfuse
            String updateQuery = "UPDATE demandestage SET etat = ? WHERE id = ?";
            Connection connection = MyDatabase.getInstance().getConnection(); // Reestablishing connection
            try (PreparedStatement st = connection.prepareStatement(updateQuery)) {
                st.setString(1, "refuser");
                st.setInt(2, offre.getId());
                st.executeUpdate();
                symfony.mailingRefuser(offre.getEmail(),offre.getId());

//                System.out.println("signale a9al mn 10" + a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

    }


}
