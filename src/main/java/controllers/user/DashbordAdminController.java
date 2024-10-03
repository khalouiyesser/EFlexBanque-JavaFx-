package controllers.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class DashbordAdminController {


    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private PieChart gender_piechart;
    @FXML
    private BarChart<String, Number> age_barchart;


    private ServiceUser userService = new ServiceUser();


}



