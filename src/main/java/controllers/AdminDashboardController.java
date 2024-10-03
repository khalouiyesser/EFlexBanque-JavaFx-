package controllers;

import Entities.Project;
import controllers.user.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceProjet;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminDashboardController implements Initializable {

    @FXML
    private TextField textFieldProjet = new TextField();
    @FXML
    private TextField textField = new TextField();
    @FXML
    private TableView<Project> tableProjet = new TableView<>();

    @FXML
    private Circle circle;

    @FXML
    private Pane content_area;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private Label dashboardText;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private HBox frontbtn;

    @FXML
    private Label frontt;

    @FXML
    private ImageView fronti;

    @FXML
    private HBox usersBtn;

    @FXML
    private Label usersText;

    @FXML
    private ImageView usersIcon;

    @FXML
    private HBox fundrisingBtn;

    @FXML
    private Label fundrisingText;

    @FXML
    private ImageView fundrisingIcon;

    @FXML
    private HBox productsBtn;

    @FXML
    private HBox projetsBtn;
    @FXML
    private HBox evenementsBtn;
    @FXML
    private Label productsText;

    @FXML
    private Label projetsText;
    @FXML
    private Label evenementsText;
    @FXML
    private ImageView productsIcon;

    @FXML
    private ImageView ProjetIcon;
    @FXML
    private ImageView evenementsIcon;

    @FXML
    private HBox collectBtn;

    @FXML
    private Label collectText;

    @FXML
    private ImageView collectIcon;


    @FXML
    private HBox evenementBtn;

    @FXML
    private Label evenementText;

    @FXML
    private ImageView evenementIcon;
    @FXML
    private ImageView investissementsIcon;

    @FXML
    private HBox commandsBtn;

    @FXML
    private Label commandsText;

    @FXML
    private ImageView commandsIcon;


    @FXML
    private HBox commentaireBtn;

    @FXML
    private Label commentaireText;

    @FXML
    private ImageView commentaireIcon;

    @FXML
    private HBox sideBarLogout;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private HBox chartContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int indivNB = 0;
        int assocNB = 0;
        int activeNB = 0;
        int unActiveNB = 0;



        // create the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        // set the labels for the axes
        xAxis.setLabel("Number of users");
        // yAxis.setLabel("Value");

        // create the bar chart
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        // set the size of the bar chart
        // barChart.setPrefSize(500, 500);

        // create the data series
        final XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Individual", indivNB));

        // set the name of the data series
        series.setName("Individual");

        final XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data<>("Association", assocNB));

        // set the name of the data series2
        series2.setName("Association");

        // add the data to the bar chart
        barChart.getData().addAll(series, series2);

        final PieChart pieChart = new PieChart();

        // create the data
        final PieChart.Data data1 = new PieChart.Data("Active", activeNB);
        final PieChart.Data data2 = new PieChart.Data("Unactive", unActiveNB);

        // add the data to the pie chart
        pieChart.getData().addAll(data1, data2);

        // chartContainer.getChildren().addAll(barChart, pieChart);
    }

    @FXML
    private void open_dashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        //       ZeroWaste.stage.getScene().setRoot(root);
    }

    @FXML
    private void open_usersList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/userInterfaces/UsersList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!usersBtn.getStyleClass().contains("activeLink")) {
            usersBtn.getStyleClass().add("activeLink");
            usersText.getStyleClass().add("activeText");

            // Load the image
            Image image = new Image("assets/img/user-active.png");
            usersIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");
                dashboardText.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);
            } else if (fundrisingBtn.getStyleClass().contains("activeLink")) {
                fundrisingBtn.getStyleClass().remove("activeLink");
                fundrisingText.getStyleClass().remove("activeText");

                Image fundrisingImg = new Image("assets/img/heart.png");
                fundrisingIcon.setImage(fundrisingImg);
            } else if (productsBtn.getStyleClass().contains("activeLink")) {
                productsBtn.getStyleClass().remove("activeLink");
                productsText.getStyleClass().remove("activeText");

                Image productsImg = new Image("assets/img/store.png");
                productsIcon.setImage(productsImg);
            } else if (collectBtn.getStyleClass().contains("activeLink")) {
                collectBtn.getStyleClass().remove("activeLink");
                collectText.getStyleClass().remove("activeText");

                Image collectImg = new Image("assets/img/recycle.png");
                collectIcon.setImage(collectImg);
            } else if (commandsBtn.getStyleClass().contains("activeLink")) {
                commandsBtn.getStyleClass().remove("activeLink");
                commandsText.getStyleClass().remove("activeText");
                Image commandsImg = new Image("assets/img/shopping-cart.png");
                commandsIcon.setImage(commandsImg);
            }

        }
    }

    @FXML
    private void addProjet() throws IOException {
        Project projet = new Project(-1);
        clickProjet(projet);
    }

    private void getProjet() throws SQLException {
        final ObservableList<Project> projets = FXCollections.observableArrayList();
        ServiceProjet serviceProjet = new ServiceProjet();
        List<Project> mesProjet = serviceProjet.afficher();
        projets.addAll(mesProjet);
        tableProjet.setItems(projets);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Project> mesProjetCopy = mesProjet
                    .stream()
                    .filter(c ->
                            c.getNomProjet().contains(newValue) ||
                                    c.getDescriptionProjet().contains(newValue) ||
                                    c.getDateCreation().toString().contains(newValue) ||
                                    c.getCategorie().contains(newValue) ||
                                    String.valueOf(c.getBudgetProjet()).contains(newValue))
                    .collect(Collectors.toList());


            tableProjet.getItems().clear();
            projets.addAll(mesProjetCopy);
            tableProjet.setItems(projets);
        });
        tableProjet.setRowFactory(tv -> {
            TableRow<Project> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Project rowData = row.getItem();
                    try {
                        clickProjet(rowData);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
    }


    private void clickProjet(Project selectedProjet) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProjetDetail.fxml"));
        Parent root = fxmlLoader.load();
        FXML_ProjetDetailController fxmlProjetDetailController = fxmlLoader.getController();
        fxmlProjetDetailController.initData(selectedProjet);
        fxmlProjetDetailController.setCallback(updatedData -> {
            if (updatedData.getId() == -1) {
                ServiceProjet serviceProjet = new ServiceProjet();
                try {
                    serviceProjet.ajouter(updatedData);
                    getProjet();
                    tableProjet.refresh();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {

                tableProjet.getItems().set(tableProjet.getSelectionModel().getSelectedIndex(), updatedData);
                ServiceProjet serviceProjet = new ServiceProjet();
                try {
                    serviceProjet.modifier(updatedData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.showAndWait();
    }

    @FXML
    private void open_ProjectList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/ProjectsList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!projetsBtn.getStyleClass().contains("activeLink")) {
            projetsBtn.getStyleClass().add("activeLink");
            projetsText.getStyleClass().add("activeText");

            // Load the image
            Image image = new Image("../assets/img/project.png");
            investissementsIcon.setImage(image);

            if (projetsBtn.getStyleClass().contains("activeLink")) {
                projetsBtn.getStyleClass().remove("activeLink");
                projetsText.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/project.png");
                investissementsIcon.setImage(dashIcon);
            } else if (projetsBtn.getStyleClass().contains("activeLink")) {
                projetsBtn.getStyleClass().remove("activeLink");
                projetsText.getStyleClass().remove("activeText");
            }
        }
    }



    @FXML
    private void open_commentaireList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/eventPayment.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!commentaireBtn.getStyleClass().contains("activeLink")) {
            commentaireBtn.getStyleClass().add("activeLink");
            commentaireText.getStyleClass().add("activeText");

            // Load the image
            Image image = new Image("assets/img/store-active.png");
            commentaireIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");
                dashboardText.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);
            } else if (usersBtn.getStyleClass().contains("activeLink")) {
                usersBtn.getStyleClass().remove("activeLink");
                usersText.getStyleClass().remove("activeText");

                Image usersImg = new Image("assets/img/user.png");
                usersIcon.setImage(usersImg);
            } else if (fundrisingBtn.getStyleClass().contains("activeLink")) {
                fundrisingBtn.getStyleClass().remove("activeLink");
                fundrisingText.getStyleClass().remove("activeText");

                Image fundrisingImg = new Image("assets/img/heart.png");
                fundrisingIcon.setImage(fundrisingImg);
            } else if (collectBtn.getStyleClass().contains("activeLink")) {
                collectBtn.getStyleClass().remove("activeLink");
                collectText.getStyleClass().remove("activeText");

                Image collectImg = new Image("assets/img/recycle.png");
                collectIcon.setImage(collectImg);
            } else if (commentaireBtn.getStyleClass().contains("activeLink")) {
                commentaireBtn.getStyleClass().remove("activeLink");
                commentaireText.getStyleClass().remove("activeText");

                Image commandsImg = new Image("assets/img/shopping-cart.png");
                commentaireIcon.setImage(commandsImg);
            }

        }
    }

    @FXML
    private void open_evenementsList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/EvenementsList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!evenementsBtn.getStyleClass().contains("activeLink")) {
            evenementsBtn.getStyleClass().add("activeLink");
            evenementsText.getStyleClass().add("activeText");

            // Load the image
            Image image = new Image("assets/img/store-active.png");
            evenementsIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");
                dashboardText.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);
            } else if (usersBtn.getStyleClass().contains("activeLink")) {
                usersBtn.getStyleClass().remove("activeLink");
                usersText.getStyleClass().remove("activeText");

                Image usersImg = new Image("assets/img/user.png");
                usersIcon.setImage(usersImg);
            } else if (evenementsBtn.getStyleClass().contains("activeLink")) {
                evenementsBtn.getStyleClass().remove("activeLink");
                evenementsText.getStyleClass().remove("activeText");

                Image fundrisingImg = new Image("assets/img/heart.png");
                fundrisingIcon.setImage(fundrisingImg);
            } else if (collectBtn.getStyleClass().contains("activeLink")) {
                collectBtn.getStyleClass().remove("activeLink");
                collectText.getStyleClass().remove("activeText");

                Image collectImg = new Image("assets/img/recycle.png");
                collectIcon.setImage(collectImg);
            } else if (commandsBtn.getStyleClass().contains("activeLink")) {
                commandsBtn.getStyleClass().remove("activeLink");
                commandsText.getStyleClass().remove("activeText");

                Image commandsImg = new Image("assets/img/shopping-cart.png");
                commandsIcon.setImage(commandsImg);
            }

        }
    }

    @FXML
    private void open_evenementsListfront(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/EvenementsListfront.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
        if (!frontbtn.getStyleClass().contains("activeLink")) {
            frontbtn.getStyleClass().add("activeLink");
            frontt.getStyleClass().add("activeText");
            Image image = new Image("assets/img/store-active.png");
            fronti.setImage(image);

            if (frontbtn.getStyleClass().contains("activeLink")) {
                frontbtn.getStyleClass().remove("activeLink");
                frontt.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/menu.png");
                fronti.setImage(dashIcon);
            } else if (usersBtn.getStyleClass().contains("activeLink")) {
                usersBtn.getStyleClass().remove("activeLink");
                usersText.getStyleClass().remove("activeText");

                Image usersImg = new Image("assets/img/user.png");
                usersIcon.setImage(usersImg);
            }
        }
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        UserSession.getInstance().cleanUserSession();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
