package controllers.Credit;

import Entities.Credit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.MyDatabase;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class credititemsuser implements Initializable {

    @FXML
    private HBox accepteritem;

    @FXML
    private Text dateitem;

    @FXML
    private Text dureeitem;

    @FXML
    private Text mensualiteuserrole;

    @FXML
    private Text montantuseritem;

    @FXML
    private Text productName;

    @FXML
    private HBox rdvcredit;

    @FXML
    private HBox refuseritem;

    @FXML
    private Text tauxitem;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text useriditem;

    @FXML
    private Text useritemidclient;


    private DemandeCreditListClientUser demandeCreditListClientUser;

    Pane content_areap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        DemandeCreditListClientUser d=new DemandeCreditListClientUser();
        Pane content_areap=d.getContentArea();


    }
    @FXML
    void getrdv(MouseEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/calandar.fxml"));
        Parent root = null;
        Connection con = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        con = MyDatabase.getInstance().getConnection();

        String idvalue = useriditem.getText();

        CalendarController cal=loader.getController();
        cal.initData(Integer.parseInt(idvalue));
        cal.afficherRDVById(Integer.parseInt(idvalue));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void approuvercredit(MouseEvent event) throws GeneralSecurityException, IOException, MessagingException {
        System.out.println("rrrrrrrrrrrrrrrr");
        new GMailer().sendMail("subject","credit approuve ");


    }

    @FXML
    void UpdateCheque(MouseEvent event) {

    }
    public void initData(Credit i) {
        System.out.println("azzazaz"+i);
        useriditem.setText(String.valueOf(i.getId()));
        useritemidclient.setText(String.valueOf(i.getStatusclient()));
        montantuseritem.setText(String.valueOf(i.getMontant()));
        mensualiteuserrole.setText(String.valueOf(i.getMensualite()));
        dateitem.setText(String.valueOf(i.getDateDebut()));
        dureeitem.setText(String.valueOf(i.getDuree()));
        tauxitem.setText(String.valueOf(i.getTaux()));
        userItemStateText.setText(i.getStatus());


    }
    public Pane setContentArea(Pane content_area) {
        content_areap=content_area;
        return  content_area;
    }
    @FXML
    void takerdv(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormCardCredit.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_areap.getChildren().clear();
        content_areap.getChildren().add(addArticleParent);

    }


    public void ajoutercredit(MouseEvent mouseEvent) {
    }




    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;

    public void deletecredit(MouseEvent mouseEvent) {
        String Delete="delete from credit where id = ?";
        String idvalue = useriditem.getText();

        con = MyDatabase.getInstance().getConnection();
        try {
            st=con.prepareStatement(Delete);
            st.setInt(1, Integer.parseInt(idvalue));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}