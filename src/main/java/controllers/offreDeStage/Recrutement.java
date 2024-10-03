package controllers.offreDeStage;

import Entities.DemandeStage;
import Entities.OffreDeStage;
import controllers.demandeStage.DemandeItemVisiteurController;
import controllers.demandeStage.DemandeStageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.RechercheStream;
import services.ServiceDemandeStage;
import services.ServiceOffreDeStage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Recrutement implements Initializable {

    @FXML
    private VBox ListeOffre;

    @FXML
    private ComboBox<String> filtrage;

    @FXML
    private Button search;

    @FXML
    private Button DemandeSansOffre;
    @FXML
    private AnchorPane pane;

    @FXML
    private Button filtrer;

    @FXML
    private Button retour;

//    @FXML
//    private Pane paneDem;
    @FXML
    private AnchorPane content_area;

    @FXML
    private TextField NumRech;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> choices = FXCollections.observableArrayList(
                "Informatique",
                "Marketing",
                "Finance",
                "RH",
                "Comptabilité",
                "Management"
        );

        filtrage.setItems(choices);
        ServiceOffreDeStage projectService = new ServiceOffreDeStage();
        List<OffreDeStage> list = new ArrayList<>();
        try {
            list = projectService.afficherId();
            for (OffreDeStage i : list){
//                    System.out.println(i.getId());
            }
//                System.out.println(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ListeOffre.setSpacing(10); // Espacement vertical ajouté entre les cartes



        for (OffreDeStage offre : list) {
            try {
//                ListeOffre.setSpacing(0.5);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/OffreStageVisiteurItem.fxml"));
                Parent offreItem = loader.load();
               // offreItem.getStyleClass().add("userProductCard");

                OffreStageVisiteurItemController offreStageItem = loader.getController();
                offreStageItem.initData(offre);
                ListeOffre.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        paneDem.setOpacity(0);
    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        System.out.println("marche");
        ListeOffre.getChildren().clear();

        ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();
        RechercheStream rechercheStream = new RechercheStream();

        // Récupérer les demandes correspondantes au numéro saisi dans le TextField
//        List<DemandeStage> list = rechercheStream.rechercheDemandeParOffre(Integer.parseInt(NumRech.getText()));
       // System.out.println(list);
        List<DemandeStage>list = serviceDemandeStage.afficher();
        List<DemandeStage> filteredList = list.stream()
                .filter(demandeStage -> demandeStage.getNumeroTelephone() == Integer.parseInt(NumRech.getText()) )
                .collect(Collectors.toList());
        System.out.println("stram" +Integer.parseInt(NumRech.getText()) );


        for (DemandeStage demande : filteredList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/DemandeItemVisiteur.fxml"));
                Parent demandeItem = loader.load();
                demandeItem.getStyleClass().add("userProductCard");

                DemandeItemVisiteurController demandeItemController = loader.getController();
                demandeItemController.initData(demande);
                ListeOffre.getChildren().add(demandeItem);
                System.out.println(demande);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//        paneDem.setOpacity(0);


    @FXML
    void demandeSansOffre(ActionEvent event) throws IOException {
        // Charger la nouvelle page dans une nouvelle fenêtre
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/DemandeStage.fxml"));
        Parent demandeStage = loader.load();
        DemandeStageController demandeStageController = loader.getController();
        content_area.getChildren().clear();
//        ListeOffre.getChildren().clear(); // Efface tout contenu précédent
        content_area.getChildren().add(demandeStage);


        // Ajuster l'opacité de l'AnchorPane actuelle
//        paneDem.setOpacity(1);
//        pane.setOpacity(0);
    }

    @FXML
    void filtrer(ActionEvent event) throws SQLException {
        System.out.println("a");
        ListeOffre.getChildren().clear();

        // Récupérer le domaine choisi dans la ComboBox
        String domaineF = filtrage.getSelectionModel().getSelectedItem();

        ServiceOffreDeStage projectService = new ServiceOffreDeStage();
        List<OffreDeStage> list = projectService.afficherId();

//        System.out.println("lista" + list);

        // Filtrer la liste des offres de stage en fonction du domaine choisi
        List<OffreDeStage> filteredList = list.stream()
                .filter(offre -> offre.getDomaine().equals(domaineF))
                .collect(Collectors.toList());

//        System.out.println(filteredList);

        for (OffreDeStage offre : filteredList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/OffreStageVisiteurItem.fxml"));
                Parent offreItem = loader.load();
                offreItem.getStyleClass().add("userProductCard");

                OffreStageVisiteurItemController offreStageItemController = loader.getController();
                offreStageItemController.initData(offre);
                ListeOffre.getChildren().add(offreItem);
//                System.out.println("recrutement" + offre.getLanguage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void RetourBack(MouseEvent mouseEvent) {

        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Front.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }

