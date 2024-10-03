



package controllers.Credit;

        import Entities.Credit;
        import javafx.fxml.FXML;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.Pane;
        import utils.MyDatabase;
//        import utils.MyDatabase;

        import java.awt.event.ActionEvent;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.time.LocalDate;
        import java.util.Date;

public class updateCreditController {

    @FXML
    private HBox chartContainer;

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
    private TextField teeest;

    @FXML
    private Label test;
    private int id;

    public void initData(Credit credit) {
        id=credit.getId();

        System.out.println("my credit passe"+credit);
        System.out.println("f");
        System.out.println(credit);
        System.out.println("rr");
        idupdate.setText(String.valueOf(credit.getId_client()));
        montantupdatelabel.setText(String.valueOf(credit.getMontant()));
        tauxupdatelabel.setText(String.valueOf(credit.getTaux()));
        Date dateDebut = credit.getDateDebut();
        dateupdatelabel.setValue(LocalDate.parse(dateDebut.toString()));
         mensualitelabmensulaiteupdatelabelel.setText(String.valueOf(credit.getMensualite()));
        dureelabdureeupdatelabelel.setText(String.valueOf(credit.getDuree()));
        fraisretardupdatlabel.setText(String.valueOf(credit.getFraisRetard()));


}
    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;


    @FXML
    void updatecredit(ActionEvent event) {
        System.out.println("fff");
    }


    public void updatecredit(javafx.event.ActionEvent actionEvent) {
        System.out.println("fff");
        String update = "update credit set user_id = ?, id_client = ?, montant = ?, taux = ?, datedebut = ?, mensualite = ?, duree = ?, fraisretard =? where id = ?" ;
        con=  MyDatabase.getInstance().getConnection();

        try {
            st=con.prepareStatement(update);
            st.setInt(1, 1); // Static value for user_id
            st.setInt(2, Integer.parseInt(idupdate.getText())); // id_client
            st.setDouble(3, Double.parseDouble(montantupdatelabel.getText())); // montant
            st.setDouble(4, Double.parseDouble(tauxupdatelabel.getText())); // taux
            st.setDate(5, java.sql.Date.valueOf(dateupdatelabel.getValue())); // datedebut
            st.setDouble(6, Double.parseDouble(mensualitelabmensulaiteupdatelabelel.getText())); // mensualite
            st.setInt(7, Integer.parseInt(dureelabdureeupdatelabelel.getText())); // duree
            st.setDouble(8, Double.parseDouble(fraisretardupdatlabel.getText())); // fraisretard
            st.setInt(9,id);
            st.executeUpdate();
            System.out.println(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void deletecredit(ActionEvent event) {
        String Delete="delete from credit where id_client = ?";
        System.out.println("test delete");
        con=  MyDatabase.getInstance().getConnection();
        try {

            st=con.prepareStatement(Delete);
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletecredit(javafx.event.ActionEvent actionEvent) {
        String Delete="delete from credit where id = ?";
        System.out.println("test delete");
        System.out.println(id);
        con=  MyDatabase.getInstance().getConnection();
        try {

            st=con.prepareStatement(Delete);
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}