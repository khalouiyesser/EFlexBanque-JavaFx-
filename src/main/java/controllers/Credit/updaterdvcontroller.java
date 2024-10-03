package controllers.Credit;

import Entities.RDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.MyDatabase;
//import utils.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class updaterdvcontroller {

    @FXML
    private Button btnsave;

    @FXML
    private HBox chartContainer;

    @FXML
    private Pane content_area;

    @FXML
    private DatePicker datedebutlabel;

    @FXML
    private TextField employename;

    @FXML
    private TextField heurelabel;

    @FXML
    private TextField idclientlabel;

    @FXML

    private ChoiceBox<Integer> idcreditchoise;
    ;

    @FXML
    private TextField methodelabel;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private HBox sideBarLogout;
    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;
    private int id;
    public void initData(RDV rdv) {
        id=rdv.getId();
        idcreditchoise.getItems().add(rdv.getCredit_id());

        idclientlabel.setText(String.valueOf(rdv.getIdclient()));
        heurelabel.setText(String.valueOf(rdv.getHeure()));
        Date dateDebut =rdv.getDaterdv();

        datedebutlabel.setValue(LocalDate.parse(dateDebut.toString()));
        methodelabel.setText(rdv.getMethode());
        employename.setText(rdv.getEmployename());


    }

    @FXML
    public void saverdv(ActionEvent actionEvent) {
        String update = "UPDATE rdv SET credit_id = ?, idclient = ?, heure = ?, daterdv = ?, methode = ?, employename = ? WHERE id = ?";
        con = MyDatabase.getInstance().getConnection();

        try {
            st = con.prepareStatement(update);
            st.setInt(1, idcreditchoise.getValue()); // Assuming idcreditchoise is a ChoiceBox<Integer>
            st.setInt(2, Integer.parseInt(idclientlabel.getText()));

            String timeString = heurelabel.getText();
            String[] parts = timeString.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            java.sql.Time time = new java.sql.Time(hours, minutes, 0);
            st.setTime(3, time);

            st.setDate(4, java.sql.Date.valueOf(datedebutlabel.getValue()));
            st.setString(5, methodelabel.getText());
            st.setString(6, employename.getText());
            st.setInt(7, id); // Assuming id is initialized somewhere
            System.out.println("doneeee");
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void deleterdv(ActionEvent event) {
        String Delete="delete from rdv where id = ?";
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