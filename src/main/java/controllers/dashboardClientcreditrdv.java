package controllers;

import Entities.Credit;
import Entities.User;
import controllers.Credit.DemandeCreditListClient;
import controllers.Credit.DemandeCreditListClientUser;
import controllers.Credit.DemandeRdvListClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceCredit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class dashboardClientcreditrdv implements Initializable {

    public Text userPointText1;
    @FXML
    private Button ListeCheque;

    @FXML
    private Button ListeVirement;

    @FXML
    private Pane content_area;

    @FXML
    private VBox fundListContainer;

    public static User user;

    @FXML
    private Text userPointText;
//    @FXML
//    private BarChart<?, ?> stat1;
    @FXML
    private HBox userTableHead;

    @FXML
    public void listcredit(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeCreditListClient.fxml"));
        System.out.println("e");
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        // DemandeCreditListClientUser demandeCreditListClient = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }


    @FXML
    void listrdv(MouseEvent event) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeRdvListClient.fxml"));
        System.out.println("e");
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        DemandeRdvListClient demandeRdvListClient = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Récupérer la liste des crédits
        List<Credit> credits;

        ServiceCredit serviceCredit = new ServiceCredit();
        try {
            if (user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
                credits = serviceCredit.afficherAdmin();
            } else {
                credits = serviceCredit.afficher();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Obtenir le nombre de crédits pour chaque année et par durée
        Map<Integer, Integer> creditCountByYear = serviceCredit.countCreditsByYear(credits);
        Map<Integer, Integer> creditCountByDuration = serviceCredit.countCreditsByDuration(credits);

        // Créer l'axe des abscisses
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Durée");

        // Créer l'axe des ordonnées
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de crédits");

        // Créer le graphique en barres
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        // Préparer la série de données pour le graphique en barres
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Remplir la série de données avec le nombre de crédits par durée
        for (Map.Entry<Integer, Integer> entry : creditCountByDuration.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }

        // Ajouter la série au graphique en barres
        barChart.getData().add(series);

        // Créer le graphique circulaire (PieChart)
        PieChart pieChart = new PieChart();

        // Remplir le graphique circulaire avec les mêmes données que le graphique en barres
        for (Map.Entry<Integer, Integer> entry : creditCountByYear.entrySet()) {
            pieChart.getData().add(new PieChart.Data(entry.getKey().toString(), entry.getValue()));
        }

        // Créer des conteneurs pour les graphiques
        VBox barChartContainer = new VBox(barChart);
        VBox pieChartContainer = new VBox(pieChart);

        // Ajouter les graphiques aux conteneurs
        barChartContainer.setLayoutX(20); // Position en X pour le graphique en barres
        barChartContainer.setLayoutY(20); // Position en Y pour le graphique en barres

        pieChartContainer.setLayoutX(400); // Position en X pour le graphique circulaire
        pieChartContainer.setLayoutY(50); // Position en Y pour le graphique circulaire

        // Ajouter les conteneurs à votre disposition
        content_area.getChildren().addAll(pieChartContainer, barChart);
    }
}
