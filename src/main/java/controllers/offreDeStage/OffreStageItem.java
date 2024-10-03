
package controllers.offreDeStage;

import Entities.OffreDeStage;
import controllers.SideNavBarController;
import controllers.demandeStage.AffichageParOffreController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceDemandeStage;
import services.ServiceOffreDeStage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OffreStageItem implements Initializable {

    public Label deleteOffre;
    public Label refuser;
    public Label Approuver;
    @FXML
    private Label listeDesDemandes;

    @FXML
    private Label UpdateOffre;


    @FXML
    private Text titre;

    @FXML
    private Text exigence;

    @FXML
    private Text description;



    @FXML
    private Text type;

    @FXML
    private Text experience;


    @FXML
    private Button ListeDesDemandes;

    @FXML
    private Button OffreUpdate;

    @FXML
    private Button OffreDetails;

    @FXML
    private Text poste;

    @FXML
    void ListeDesDemandes(ActionEvent event) {

    }

    @FXML
    void OffreUpdate(ActionEvent event) {

    }

    @FXML
    void OffreDetails(ActionEvent event) {

    }

    //    public void initData(OffreDeStage i) {
//        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
//
//        // Vérification de null pour les mots clés
//        String motsClesText = i.getMotsCles() != null ? i.getMotsCles().toString() : "N/A";
//        motsCles.setText(motsClesText);
//
//        titre.setText(i.getTitle());
//        domaine.setText(i.getDomaine());
//        description.setText(i.getDescription());
//
//        // Vérification de null pour le langage
//        String langageText = i.getLanguage() != null ? i.getLanguage().toString() : "N/A";
//        language.setText(langageText);
//
//        type.setText(i.getTypeOffre());
//        experience.setText(i.getExperience());
//
//        // Vérification de null pour le niveau
//        String niveauText = i.getNiveau() != null ? i.getNiveau().toString() : "N/A";
//        niveau.setText(niveauText);
//
//        exigence.setText(i.getExigenceOffre());
//        poste.setText(String.valueOf(i.getPostePropose()));
//    }
    public void initE(){
        titre.setText("Titre");
        description.setText("Description");

    }
    OffreDeStage stage;

    int a ;

    public void initData(OffreDeStage i) {
//    System.out.println(i.getPostePropose());
//    System.out.println(i);
//    System.out.println(i.getPostePropose());
        a = i.getPostePropose();
//    System.out.println(a);

//    System.out.println(i.getPostePropose());
//    System.out.println(i.getId());
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();



        // Limiter la longueur du titre à 30 caractères
        String titreText = i.getTitle() != null && i.getTitle().length() > 30 ? i.getTitle().substring(0, 30) : i.getTitle();
        titre.setText(titreText);



        // Limiter la longueur de la description à 30 caractères
        String descriptionText = i.getDescription() != null && i.getDescription().length() > 30 ? i.getDescription().substring(0, 100) : i.getDescription();
        description.setText(descriptionText);


        // Limiter la longueur du type à 30 caractères
        String typeText = i.getTypeOffre() != null && i.getTypeOffre().length() > 30 ? i.getTypeOffre().substring(0, 30) : i.getTypeOffre();
        type.setText(typeText);

        // Limiter la longueur de l'expérience à 30 caractères
        String experienceText = i.getExperience() != null && i.getExperience().length() > 30 ? i.getExperience().substring(0, 30) : i.getExperience();
        experience.setText(experienceText);


        // Limiter la longueur de l'exigence à 30 caractères
        String exigenceText = i.getExigenceOffre() != null && i.getExigenceOffre().length() > 30 ? i.getExigenceOffre().substring(0, 30) : i.getExigenceOffre();
        exigence.setText(exigenceText);

        // Limiter la longueur du poste à 30 caractères
        String posteText = String.valueOf(i.getPostePropose());

        deleteOffre.setId(String.valueOf(i.getPostePropose()));
        UpdateOffre.setId(String.valueOf(i.getPostePropose()));
        listeDesDemandes.setId(String.valueOf(i.getPostePropose()));
        deleteOffre.setOnMouseClicked(mouseEvent -> {
                    try {
                        serviceOffreDeStage.supprimer(Integer.parseInt(deleteOffre.getId()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        UpdateOffre.setOnMouseClicked(mouseEvent -> {
//        System.out.println("update mouse click" );
            Stage primaryStage = new Stage();
            try {

                OffreDeStage offreDeStage = serviceOffreDeStage.afficheUne(Integer.parseInt(UpdateOffre.getId()));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/EditOffre.fxml")) ;
                Parent  parent = loader.load();
                Scene scene = new Scene(parent);
                primaryStage.setTitle("E-Flex Bank");
                primaryStage.setScene(scene);
                primaryStage.show();
                EditOffreController editOffreController = loader.getController();
                editOffreController.initData(Integer.parseInt(UpdateOffre.getId()));
                AfficheOffreController afficheOffreController = new AfficheOffreController();

            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
//        listeDesDemandes.setOnMouseClicked(mouseEvent -> {
//            ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();
//            AffichageParOffreController affichageParOffreController = new AffichageParOffreController();
//            Stage primaryStage = new Stage();
//            try {
//                affichageParOffreController.list = serviceDemandeStage.afficherParOffre(Integer.parseInt(listeDesDemandes.getId()));
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/AfficheDemandesParOffre.fxml"));
//                Parent parent = loader.load();
//                Pane pane = new Pane();
//                pane.getChildren().add(parent);
//                Scene scene = new Scene(parent);
//                primaryStage.setTitle("E-Flex Bank");
//                primaryStage.setScene(scene);
//                primaryStage.show();
//                AfficheOffreController sideNavBarController = new AfficheOffreController();
//                sideNavBarController.setContent_area(pane);
//
//
//
//                // Passer les données au contrôleur AffichageParOffreController
//                AffichageParOffreController editOffreController = loader.getController();
//                editOffreController.setList(affichageParOffreController.list);
//            } catch (SQLException | IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

        listeDesDemandes.setOnMouseClicked(mouseEvent -> {
            ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();
            AffichageParOffreController  affichageParOffreController =  new AffichageParOffreController();
            Stage primaryStage = new Stage();
            try {
                affichageParOffreController.list = serviceDemandeStage.afficherParOffre(Integer.parseInt(listeDesDemandes.getId()));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/AfficheDemandesParOffre.fxml")) ;
                Parent  parent = loader.load();
                System.out.println("yesser");
                Scene scene = new Scene(parent);
                primaryStage.setTitle("E-Flex Bank");
                primaryStage.setScene(scene);
                primaryStage.show();
                AffichageParOffreController editOffreController = loader.getController();
                System.out.println("hello");
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        titre.setOnMouseClicked(mouseEvent -> {
            Stage primaryStage = new Stage();
//        DetailsBackController detailsBackController = new DetailsBackController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/DetailBack.fxml")) ;
            Parent  parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);

            primaryStage.setTitle("E-Flex Bank");
            primaryStage.setScene(scene);
            primaryStage.show();
            DetailsBackController detailsBackController = loader.getController();
            detailsBackController.initData(i);
        });

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ne rien faire dans l'initialisation par défaut


    }

    @FXML
    void ListeOffre(ActionEvent event) {
        System.out.println("liste");
    }

    @FXML
    void edit(ActionEvent event) {
        System.out.println("edit");
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
        try {
            OffreDeStage offreDeStage = serviceOffreDeStage.afficheUne(Integer.parseInt(UpdateOffre.getId()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/EditOffre.fxml")) ;
            Parent  parent = loader.load();
            EditOffreController editOffreController = loader.getController();
            editOffreController.initData(Integer.parseInt(UpdateOffre.getId()));
            AfficheOffreController afficheOffreController = new AfficheOffreController();
//            afficheOffreController.
//            afficheOffreController.content_area.getChildren().clear();
//            afficheOffreController.content_area.getChildren().add(parent);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(MouseEvent mouseEvent) {
    }



}
