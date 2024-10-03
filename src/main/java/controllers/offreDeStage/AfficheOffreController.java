////package controllers.offreDeStage;
////
////import Entities.DemandeStage;
////import Entities.OffreDeStage;
////import controllers.demandeStage.EditDemandeController;
////import controllers.offreDeStage.EditOffreController;
////import javafx.event.ActionEvent;
////import javafx.fxml.FXML;
////import javafx.fxml.FXMLLoader;
////import javafx.fxml.Initializable;
////import javafx.scene.Parent;
////import javafx.scene.Scene;
////import javafx.scene.control.Button;
////import javafx.scene.control.ListView;
////import javafx.scene.layout.Pane;
////import javafx.stage.Stage;
////import services.ServiceOffreDeStage;
////
////import java.awt.event.MouseEvent;
////import java.io.IOException;
////import java.net.URL;
////import java.sql.SQLException;
////import java.util.List;
////import java.util.ResourceBundle;
////
////public class AffichOffreController implements Initializable {
////
////    @FXML
////    private ListView<String> listViewNom;
////
////    @FXML
////    private Button modifier;
////    @FXML
////    private Pane content_area;
////    @FXML
////    private Button refresh;
////
////    @FXML
////    private Button delete;
////    ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
////
////    @FXML
////    void deleteA(ActionEvent event) {
////
////    }
////    @FXML
////    void AjouterOffre(MouseEvent event) throws IOException {
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/AjouterOffre.fxml"));
////        Parent addArticleParent = loader.load();
////
////        // Récupération du contrôleur de la vue d'ajout d'article
////        AjouterOffreController ajouterOffreController = loader.getController();
////
////        // Remplacer le contenu actuel par la vue d'ajout d'article
////        content_area.getChildren().clear();
////        content_area.getChildren().add(addArticleParent);
////
////    }
////
////
////
////    @FXML
////    void EditA(ActionEvent event) {
////
////    }
////
////    @FXML
////    void refreshListA(ActionEvent event) {
////        try {
////            List<OffreDeStage> demandeStages = serviceOffreDeStage.afficher();
//////            System.out.println(demandeStages);
////            listViewNom.getItems().clear();
//////            listViewNom.getItems().add("Nom    | ");
////            StringBuilder Affichage = new StringBuilder();
////            Affichage.append("Nom        ");
////            Affichage.append("Prenom           ");
////            Affichage.append("Email           ");
////            Affichage.append("Numéro de téléphone           ");
////            Affichage.append("CV           ");
////            Affichage.append("Date           ");
////            Affichage.append("Score           ");
////
//////            listViewNom.getItems().add(Affichage.toString());
//////            listViewNom.getItems().add("ss");
////            listViewNom.getItems().add(Affichage.toString());
////
////            for (OffreDeStage i : demandeStages){
////                StringBuilder nom = new StringBuilder();
//////                StringBuilder prenom = new StringBuilder();
////
////                nom.append(i.getTitle()).append("     ");
////                nom.append(i.getExigenceOffre()).append("     ");
////                nom.append(i.getDescription()).append("     ");
////                nom.append(i.getDomaine()).append("     ");
////                nom.append(i.getExperience()).append("     ");
////                nom.append(i.getLanguage()).append("     ");
//////                if (i.getScore() == 0 )
//////                {
////
////                nom.append("Demande spontannée");
//////                }
//////                else {
//////                    nom.append(i.getScore());
////
//////                }
////                listViewNom.getItems().add(nom.toString());
//////                listViewNom.getItems().add(prenom.toString());
////            }
////        }catch (SQLException e){
////            e.printStackTrace();
////        }    }
//////    private void ref(){
//////        try {
//////            List<OffreDeStage> demandeStages = serviceOffreDeStage.afficher();
//////            listViewNom.getItems().clear();
////////            listViewNom.getItems().add("Nom    | ");
//////            StringBuilder Affichage = new StringBuilder();
//////            Affichage.append("Nom        ");
//////            Affichage.append("Prenom           ");
//////            Affichage.append("Email           ");
//////            Affichage.append("Numéro de téléphone           ");
//////            Affichage.append("CV           ");
//////            Affichage.append("Date           ");
//////            Affichage.append("Score           ");
//////
////////            listViewNom.getItems().add(Affichage.toString());
////////            listViewNom.getItems().add("ss");
//////            listViewNom.getItems().add(Affichage.toString());
//////            for (OffreDeStage i : demandeStages){
//////                StringBuilder nom = new StringBuilder();
////////                StringBuilder prenom = new StringBuilder();
//////
//////                nom.append(i.getTitle()).append("     ");
//////                nom.append(i.getExigenceOffre()).append("     ");
//////                nom.append(i.getDescription()).append("     ");
//////                nom.append(i.getDomaine()).append("     ");
//////                nom.append(i.getExperience()).append("     ");
//////                nom.append(i.getLanguage()).append("     ");
////////                if (i.getScore() == 0 )
////////                {
//////
//////                nom.append("Demande spontannée");
////////                }
////////                else {
////////                    nom.append(i.getScore());
//////
////////                }
//////                listViewNom.getItems().add(nom.toString());
////////                listViewNom.getItems().add(prenom.toString());
//////            }
//////        }catch (SQLException e){
//////            e.printStackTrace();
//////        }
//////    }
////    private OffreDeStage getSelectedDemande() throws SQLException {
////        int selectedIndex = listViewNom.getSelectionModel().getSelectedIndex();
////        if (selectedIndex >= 0) {
////            // Récupérer la demande correspondant à l'index sélectionné
////            List<OffreDeStage> demandeStages = serviceOffreDeStage.afficher();
////            return demandeStages.get(selectedIndex);
////        }
////        return null;
////    }
////
////    private void openEditWindow(OffreDeStage demande) {
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/EditOffre.fxml"));
////        Parent root;
////        try {
////            root = loader.load();
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////
////        // Get the controller after loading the FXML file
////        EditOffreController editDemandeController = loader.getController();
////
////        // Initialize the data in the editDemandeController
////        editDemandeController.initData(demande);
////
////        // Set the scene
////        Stage stage = new Stage();
////        Scene scene = new Scene(root);
////        stage.setScene(scene);
////        stage.show();
////    }
////
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
//////        ref();
////
////    }
////
////    public void statusChange(ActionEvent event) {
////    }
////
////    public void AjouterOffre(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
////        // Chargement de la vue FXML de la page d'ajout d'article
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/AjouterOffre.fxml"));
////        Parent addArticleParent = loader.load();
////
////        // Récupération du contrôleur de la vue d'ajout d'article
////        AjouterOffreController ajouterOffreController = loader.getController();
////
////        // Remplacer le contenu actuel par la vue d'ajout d'article
////        content_area.getChildren().clear();
////        content_area.getChildren().add(addArticleParent);
////    }
////    }
////
//package controllers.offreDeStage;
//
//import Entities.OffreDeStage;
//import controllers.offreDeStage.OffreStageItem;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import services.ServiceOffreDeStage;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class AfficheOffreController implements Initializable {
//
//    @FXML
//    private ComboBox<String> statusInput;
//
//    @FXML
//    private HBox userTableHead;
//
//    @FXML
//    private Button ajoutOffre;
//
//    @FXML
//    private Pane content_area;
//
//    @FXML
//    private VBox userListContainer;
//
//    private static int updateProjectModelShow = 0;
//    private static int addProjectModelShow = 0;
//    private static int projetIdToUpdate = 0;
//    private static int filter = 0;
//
//
//    @FXML
//    void statusChange(ActionEvent event) {
//
//    }
//
//    public static int getupdateProjectModelShow() {
//        return updateProjectModelShow;
//    }
//
//    public static void setupdateProjectModelShow(int updateProjectModelShow) {
//        AfficheOffreController.updateProjectModelShow = updateProjectModelShow;
//    }
//
//    public static int getaddProjectModelShow() {
//        return updateProjectModelShow;
//    }
//
//    public static void setaddProjectModelShow(int addProjectModelShow) {
//        AfficheOffreController.addProjectModelShow = addProjectModelShow;
//    }
//
//
//    public static void setprojectEmailToUpdate(int projetIdToUpdate) {
//        AfficheOffreController.projetIdToUpdate = projetIdToUpdate;
//    }
//
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ServiceOffreDeStage projectService = new ServiceOffreDeStage();
//        List<OffreDeStage> list = new ArrayList<>();
//        try {
//            list = projectService.afficher();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        OffreDeStage project;
//        if (AfficheOffreController.getupdateProjectModelShow() == 0) {
//            userTableHead.setVisible(false);
//        } else if (AfficheOffreController.getupdateProjectModelShow() == 1) {
//            userTableHead.setVisible(true);
//            FXMLLoader fxmlLoader1 = new FXMLLoader();
//            fxmlLoader1.setLocation(getClass().getResource("/FXML/offreDeStage/OffreStageItem.fxml"));
//            VBox updateProjectform;
//            try {
//                updateProjectform = fxmlLoader1.load();
//                EditOffreController editOffreController = fxmlLoader1.getController();
//                EditOffreController.setFxmlToLoad("ProjectsList.fxml");
//                project = projectService.afficheUne(projetIdToUpdate);
//
//                EditOffreController.setProjectUpdateData(project);
//                userListContainer.getChildren().add(updateProjectform);
////                for (int i =0; i <list.size() ; i++){
////                FXMLLoader loader = new FXMLLoader();
////                loader.setLocation(getClass().getResource("FXML/offreDeStage/OffreStageItem.fxml"));
////                VBox offreItem = loader.load();
////                OffreStageItem offreStageItem = loader.getController();
////                offreStageItem.initData(list.get(i));
////                userListContainer.getChildren().add(offreItem);
////            }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    public void AjouterOffre(MouseEvent mouseEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/AjouterOffre.fxml"));
//            Parent addArticleParent = null;
//            try {
//                addArticleParent = loader.load();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            // Récupération du contrôleur de la vue d'ajout d'article
//        AjouterOffreController ajouterOffreController = loader.getController();
//
//        // Remplacer le contenu actuel par la vue d'ajout d'article
//        content_area.getChildren().clear();
//        content_area.getChildren().add(addArticleParent);
//    }
//    }
//
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
////        OffreDeStage offreDeStage;
////        try {
////            List<OffreDeStage> lisOffre = serviceOffreDeStage.afficher();
////
////            for (int i =0; i <lisOffre.size() ; i++){
////                FXMLLoader loader = new FXMLLoader();
////                loader.setLocation(getClass().getResource("FXML/offreDeStage/OffreStageItem.fxml"));
////                VBox offreItem = loader.load();
////                OffreStageItem offreStageItem = loader.getController();
////                offreStageItem.initData(lisOffre.get(i));
////                userListContainer.getChildren().add(offreItem);
////            }
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////    }
//
//
package controllers.offreDeStage;

import Entities.OffreDeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceOffreDeStage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficheOffreController implements Initializable {

    @FXML
    private ComboBox<String> statusInput;
    @FXML
    private Button RetourL;

    @FXML
    private HBox userTableHead;

    @FXML
    private Button ajoutOffre;

    @FXML
    public Pane content_area;

    public Pane getContent_area() {
        return content_area;
    }

    public void setContent_area(Pane content_area) {
        this.content_area = content_area;
    }

    @FXML
    private VBox userListContainer;

    private static int updateProjectModelShow = 0;
    private static int addProjectModelShow = 0;
    private static int projetIdToUpdate = 0;
    private static int filter = 0;


    @FXML
    void statusChange(ActionEvent event) {

    }


    //    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ServiceOffreDeStage projectService = new ServiceOffreDeStage();
//        List<OffreDeStage> list = new ArrayList<>();
//        try {
//            list = projectService.afficher();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        OffreDeStage project;
//        if (AfficheOffreController.getupdateProjectModelShow() == 0) {
//            userTableHead.setVisible(false);
//        } else if (AfficheOffreController.getupdateProjectModelShow() == 1) {
//            userTableHead.setVisible(true);
//            FXMLLoader fxmlLoader1 = new FXMLLoader();
//            fxmlLoader1.setLocation(getClass().getResource("/FXML/offreDeStage/OffreStageItem.fxml"));
//            VBox updateProjectform;
//            try {
//                updateProjectform = fxmlLoader1.load();
//                EditOffreController editOffreController = fxmlLoader1.getController();
//                EditOffreController.setFxmlToLoad("ProjectsList.fxml");
//                project = projectService.afficheUne(projetIdToUpdate);
//
//                EditOffreController.setProjectUpdateData(project);
//                userListContainer.getChildren().add(updateProjectform);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceOffreDeStage projectService = new ServiceOffreDeStage();
        List<OffreDeStage> list = new ArrayList<>();
        try {
            list = projectService.afficherId();
            for (OffreDeStage i : list) {
//                    System.out.println(i.getId());
            }
//                System.out.println(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (OffreDeStage offre : list) {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/OffreStageItem.fxml"));
                Parent offreItem = loader.load();
                Scene scene = new Scene(offreItem);
                primaryStage.setTitle("E-Flex Bank");
                primaryStage.setScene(scene);
                OffreStageItem offreStageItem = loader.getController();
                offreStageItem.initData(offre);
                userListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void AjouterOffre(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/AjouterOffre.fxml"));
        Parent addArticleParent = null;
        try {
            addArticleParent = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterOffreController ajouterOffreController = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    public void RetourBack(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/YesserTest/CardNavBar.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}