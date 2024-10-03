package controllers.Credit;

import Entities.RDV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.MyDatabase;
//import utils.MyDatabase;

import java.io.IOException;
import java.sql.*;

public class Rdvitems {

    @FXML
    private Text credit_id;
    @FXML
    private HBox rdvrow;
    @FXML
    private HBox creditrow;

    @FXML
    private Text daterdv;

    @FXML
    private Text deleteitem;

    @FXML
    private Text fraisretard;

    @FXML
    private Text heure;

    @FXML
    private Text idclient;

    @FXML
    private Text methode;

    @FXML
    private Label userItemStateBtn;

    @FXML
    private ImageView userItemStateBtnImg;

    @FXML
    private Label userItemUpdateBtn;

    @FXML
    private ImageView userItemUpdateBtnImg;

    @FXML
    void DeleteCheque(MouseEvent event) {

    }

    @FXML
    void UpdateCheque(MouseEvent event) {

    }

    @FXML
    void deletecredit(MouseEvent event) {

    }





    public void initData(RDV i) {

        credit_id.setText(String.valueOf(i.getCredit_id()));
        idclient.setText(String.valueOf(i.getIdclient()));
        heure.setText(String.valueOf(i.getHeure()));
        methode.setText(String.valueOf(i.getMethode()));
        daterdv.setText(String.valueOf(i.getDaterdv()));
        fraisretard.setText(String.valueOf(i.getEmployename()));



    }
    @FXML
    int getdata(MouseEvent event) throws IOException {
        Integer rdvid = null;
        Connection con = null;

        try {
            con = MyDatabase.getInstance().getConnection();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/updaterdv.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updaterdvcontroller updaterdvController = loader.getController();
          // CalendarController cal=loader.getController();
            String idvalue = credit_id.getText();

            RDV c11 = getrdvByUserId(con, idvalue);
            int id = c11.getId();
            updaterdvController.initData(c11);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


            // Get the value of the user ID
            System.out.println("User ID: " + idvalue); // Debug statement

            // Retrieve Credit object by user ID
            System.out.println("Credit object: " + c11); // Debug statement

            // Access controller and pass Credit object
            updaterdvController.initData(c11);

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions...
        }

        return rdvid != null ? rdvid : -1;
    }
    private RDV getrdvByUserId(Connection con, String rdv_id) throws SQLException {
        RDV rdv = null;
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM rdv WHERE credit_id = ?")) {
            int rdvId = Integer.parseInt(rdv_id);
            System.out.println(rdv_id);
            stmt.setInt(1, Integer.parseInt(rdv_id));
            System.out.println("ee");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int id_client = rs.getInt("idclient");
                    Time heure = rs.getTime("heure");
                    Date daterdv = rs.getDate("daterdv");
                    String methode = rs.getString("methode");
                    String employename = rs.getString("employename");

                    rdv = new RDV(id, rdvId, id_client, heure, daterdv, methode, employename);
                    System.out.println(rdv);
                }
            }
        }
        return rdv;
    }

}

