package controllers.Credit;

import Entities.RDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;
import utils.MyDatabase;
//import utils.MyDatabase;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.net.URL;
import java.sql.*;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if(calendarActivities != null){
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getClientName() + ", " + calendarActivities.get(k).getDate().toLocalTime());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity: calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            // Check if the map already contains activities for this date
            if (!calendarActivityMap.containsKey(activityDate)) {
                // If not, create a new list and add the activity
                List<CalendarActivity> activitiesForDate = new ArrayList<>();
                activitiesForDate.add(activity);
                calendarActivityMap.put(activityDate, activitiesForDate);
            } else {
                // If activities for this date already exist, append the activity to the existing list
                calendarActivityMap.get(activityDate).add(activity);
            }
        }
        return calendarActivityMap;
    }

    int idd=0;
    public void initData(int i) {
        idd = i;
    }

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    private Connection connection;
    public Statement statement;
    public List<RDV> afficherRDVById(int rdvId) throws SQLException {
        System.out.println("eeeeeeeeeeeeeeeeeeee"+rdvId);
        List<RDV> list = new ArrayList<>();
        String query = "SELECT id, credit_id, idclient, heure, daterdv, methode, employename FROM rdv WHERE credit_id=?";
  idd=rdvId;
        try {
            con=  MyDatabase.getInstance().getConnection();
            st = con.prepareStatement(query);
            st.setInt(1, rdvId);
            rs = st.executeQuery();
            while (rs.next()) {
                RDV rdv = new RDV();
                rdv.setId(rs.getInt("id"));
                rdv.setCredit_id(rs.getInt("credit_id"));
                rdv.setIdclient(rs.getInt("idclient"));
                rdv.setHeure(rs.getTime("heure"));
                rdv.setDaterdv((rs.getDate("daterdv")));
                rdv.setMethode(rs.getString("methode"));
                rdv.setEmployename(rs.getString("employename"));
                list.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        System.out.println("mes rdv"+list);
        return list;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        Set<CalendarActivity> calendarActivities = new HashSet<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonthValue();

        try {
            System.out.println("haaw lid " + idd);
            List<RDV> rdvList = afficherRDVById(idd);

            for (RDV rdv : rdvList) {
                // Extract the year, month, and day of the month from the RDV's date
                Date sqlDate = (Date) rdv.getDaterdv();
                LocalDate localDate = sqlDate.toLocalDate();
                int rdvYear = localDate.getYear();
                int rdvMonth = localDate.getMonthValue();
                int rdvDayOfMonth = localDate.getDayOfMonth();

                // Check if the RDV is in the same year and month as the current focus
                if (rdvYear == year && rdvMonth == month) {
                    // Create ZonedDateTime for the RDV
                    ZonedDateTime time = ZonedDateTime.of(rdvYear, rdvMonth, rdvDayOfMonth,
                            rdv.getHeure().toLocalTime().getHour(), rdv.getHeure().toLocalTime().getMinute(), 0, 0, dateFocus.getZone());

                    // Create CalendarActivity and add it to the set
                    calendarActivities.add(new CalendarActivity(time, rdv.getEmployename(), rdv.getCredit_id()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert set to list if necessary and return
        List<CalendarActivity> uniqueActivities = new ArrayList<>(calendarActivities);
        return createCalendarMap(uniqueActivities);
    }




    @FXML
    void sendsms(MouseEvent event) {

       
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML

    public void sendsms(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/senssms.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}