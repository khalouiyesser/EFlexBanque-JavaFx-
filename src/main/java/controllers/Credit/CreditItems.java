package controllers.Credit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.GeneralSecurityException;
import java.sql.*;

import Entities.Credit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.MyDatabase;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreditItems implements Initializable {
    @FXML
    private Text datedebut;

    @FXML
    private Text duree;

    @FXML
    private Text fraisretard;

    @FXML
    private Text idclient;

    @FXML
    private Text mensualite;

    @FXML
    private Text montant;

    @FXML
    private Text statutclient;

    @FXML
    private Text taux;

    @FXML
    private Label userItemStateBtn;

    @FXML
    private ImageView userItemStateBtnImg;

    @FXML
    private Text userItemStateText;

    @FXML
    private Label userItemUpdateBtn;

    @FXML
    private ImageView userItemUpdateBtnImg;

    @FXML
    private Text userid;
    @FXML
    private Label approuver;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // Ne rien faire dans l'initialisation par défaut
    public void initData(Credit i) {

        userid.setText(String.valueOf(i.getId()));
        idclient.setText(String.valueOf(i.getId_client()));
        montant.setText(String.valueOf(i.getMontant()));
        mensualite.setText(String.valueOf(i.getStatusclient()));
        datedebut.setText(String.valueOf(i.getDateDebut()));
        duree.setText(String.valueOf(i.getDuree()));
        taux.setText(String.valueOf(i.getTaux()));
        fraisretard.setText(String.valueOf(i.getFraisretard()));


    }

    @FXML
    public void DeleteCheque(MouseEvent mouseEvent) {

    }
    @FXML
    private HBox creditrow;

    @FXML
    private Pane content_area;

    @FXML
    private DatePicker dateupdatelabel;

    @FXML
    private TextField dureelabdureeupdatelabelel;

    @FXML
    private TextField fraisretardupdatlabel;

    @FXML
    private TextField idupdate;

    @FXML
    private TextField mensualitelabmensulaiteupdatelabelel;

    @FXML
    private TextField montantupdatelabel;

    @FXML
    private TextField tauxupdatelabel;

    @FXML
    public void UpdateCheque(MouseEvent mouseEvent) throws IOException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/updaterdv.fxml"));

    }
    public Connection con=  con = MyDatabase.getInstance().getConnection();;
    PreparedStatement st=null;
    ResultSet rs=null;



    @FXML
    private Text deleteitem;

    int id=0;

    @FXML
    int getdata(MouseEvent event) throws IOException {
        Integer rdvid = null;
        Connection con = null;

        try {
            con = MyDatabase.getInstance().getConnection();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/updatecredit.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateCreditController updaterdvController = loader.getController();

            String idvalue = userid.getText();

            Credit c11 = getCreditByUserId(con, idvalue);
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
    private String readHTMLFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }




    @FXML
    void approuvercredit(MouseEvent event) throws GeneralSecurityException, IOException, MessagingException, SQLException {
        System.out.println("rrrrrrrrrrrrrrrr");

        // Contenu de l'email
        String emailContent = "Cher CLIENT ,\n\n" +
                "Nous avons le plaisir de vous informer que votre demande de crédit a été approuvée avec succès. Félicitations!\n\n" +

                "Veuillez consulter le contrat de prêt joint à cet e-mail pour plus de détails. Si vous avez des questions ou avez besoin d'assistance supplémentaire, n'hésitez pas à nous contacter.\n\n" +
                "Nous vous remercions de votre confiance en notre institution financière et nous sommes impatients de vous accompagner dans la réalisation de vos projets.\n\n" +
                "Cordialement,\n" +
                "[L'équipe de votre institution financière]";

        // Envoyer l'email avec le contenu
        new GMailer().sendMail("Approuvaation de crédit", emailContent);
        String idvalue = userid.getText();

        Credit c11 = getCreditByUserId(con, idvalue);
        System.out.println("eeeeeee"+c11);
        c11.setStatusclient("accepte");
        System.out.println(c11.getStatus());
        String update = "update credit set user_id = ?, id_client = ?, montant = ?, taux = ?, datedebut = ?, mensualite = ?, duree = ?, fraisretard =?,status=? where id = ?" ;
        con=  MyDatabase.getInstance().getConnection();

        try {
            st=con.prepareStatement(update);
            st.setInt(1, 1); // Static value for user_id
            st.setInt(2, c11.getId_client()); // id_client
            st.setDouble(3,c11.getMontant()); // montant
            st.setDouble(4,c11.getTaux()); // taux
            st.setDate(5, new java.sql.Date(c11.getDateDebut().getTime()));
            st.setDouble(6, c11.getMensualite()); // mensualite
            st.setInt(7, c11.getDuree()); // duree
            st.setDouble(8, c11.getFraisRetard()); // fraisretard
            st.setString(9,"accepte ");
            st.setInt(10,c11.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    // Retrieve Credit object by user ID using an existing connection
    private Credit getCreditByUserId(Connection con, String userId) throws SQLException {
        Credit credit = null;
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM credit WHERE id = ?")) {
            int idClient = Integer.parseInt(userId);
            stmt.setInt(1, idClient);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int id_client = rs.getInt("id_client");
                    double montant = rs.getDouble("montant");
                    double taux = rs.getDouble("taux");
                    String status=rs.getString("status");
                    String statusclient=rs.getString("statusclient");
                    Date datedebut = rs.getDate("datedebut");
                    double mensualite = rs.getDouble("mensualite");
                    int duree = rs.getInt("duree");
                    double fraisretard = rs.getDouble("fraisretard");
                    credit = new Credit(id, id_client, montant, statusclient,taux,status, datedebut, mensualite, duree, fraisretard);

                }
            }
        }
        return credit;
    }



    @FXML
    void deletecredit(MouseEvent event) {


    }
    public void updatecredit(MouseEvent mouseEvent) throws IOException {
    }
}

