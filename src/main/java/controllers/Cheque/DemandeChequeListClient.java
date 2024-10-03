package controllers.Cheque;

import Entities.Cheque;
import Entities.Virement;
import controllers.CaptureEcran;
import controllers.Cheque.AjouterChequeCard;
import controllers.ChequeItemsController;
import controllers.Virement.AjouterVirementCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import javafx.scene.control.TextField;
import services.ServiceVirement;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class DemandeChequeListClient  implements  Initializable {
    @FXML
    private TextField ChequeclientsfSearchInput;

    @FXML
    private TextField fxrecherches;

    @FXML
    private Button chequebtn;
    @FXML
    private Button retourCh;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<String> statusInput;


    //    @FXML
//    private TextField fxrecherche;
    @FXML
    private GridPane ChequeListContainer;
    @FXML
    private GridPane userListContainer;

    @FXML
    private VBox updateChequeModelContent;




    @FXML
    private HBox userTableHead;
    private Cheque cheque;
    private Throwable e;

    @FXML
    private Button captureEcran;

    @FXML
    private Button Refresh;

    private static int chequeIdToUpdate = 0;
    private static int updateChequeModelShow = 0;
    @FXML
    private HBox updateChequeModel;



    @FXML
    void AjouterC(MouseEvent event) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormCradCheque.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterChequeCard ajouterChequeCard = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    public static void setupdateChequeModelShow(int updateChequeModelShow) {
        DemandeChequeListClient.updateChequeModelShow = updateChequeModelShow;
    }

    public static void setchequeEmailToUpdate(int chequeIdToUpdate) {
        DemandeChequeListClient.chequeIdToUpdate = chequeIdToUpdate;
    }

    public static int getupdateChequeModelShow() {
        return updateChequeModelShow;
    }


    @FXML
    void close_updateChequeModel(MouseEvent event) {
        updateChequeModel.setVisible(false);
        updateChequeModelShow = 0;
    }


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ServiceCheque serviceCheque = new ServiceCheque();
//        List<Cheque> list = new ArrayList<>();
//        try {
//            list = serviceCheque.afficher(); // Notez le changement ici
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        for (Cheque cheque : list) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
//                Parent offreItem = loader.load();
//                ChequeItems offreStageItem = loader.getController();
//                offreStageItem.initData(cheque);
//                userListContainer.getChildren().add(offreItem);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> trie1 = FXCollections.observableArrayList(
                "tous",
                "Paiement",
                "PaiementEco",
                "Personne"
        );
        statusInput.setItems(trie1);
        Cheque cheque;
        ServiceCheque serviceCheque = new ServiceCheque();
        List<Cheque> list = new ArrayList<>();
        try {
            list = serviceCheque.afficher();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Refresh.setOnAction((ActionEvent event) -> {
            //    ShowListe();
        });
        if (DemandeChequeListClient.getupdateChequeModelShow() == 0) {
            updateChequeModel.setVisible(false);
        } else if (DemandeChequeListClient.getupdateChequeModelShow() == 1) {
            updateChequeModel.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/updateChequeCard.fxml"));
            VBox updateProjectform;
            try {
                updateProjectform = fxmlLoader1.load();
                updateChequeCard updateUserCardController = fxmlLoader1.getController();
                updateChequeCard.setFxmlToLoad("DemandeChequeListClient.fxml");
                cheque = serviceCheque.getById(chequeIdToUpdate);

                updateUserCardController.setProjectUpdateData(cheque);
                updateChequeModelContent.getChildren().add(updateProjectform);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        loadCheques(list);
        captureEcran.setOnAction(event -> {
            CaptureEcran cap = new CaptureEcran();
            try {
                cap.capturer(content_area);
                System.out.println("La capture d'écran a été générée avec succès !");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Capture d'écran");
                alert.setHeaderText("Capture d'écran générée avec succès !");
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println("Erreur lors de la capture d'écran : " + ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la capture d'écran !");
                alert.setContentText("Une erreur est survenue lors de la capture d'écran : " + ex.getMessage());
                alert.showAndWait();
            }
        });
        Refresh.setOnAction((ActionEvent event) -> {
            ShowListe();
        });


    }

    @FXML
    void fixType(KeyEvent event) {
        filterCheques(fxrecherches.getText());
    }

    private void ShowListe() {
        ServiceCheque serviceCheque = new ServiceCheque();
        List<Cheque> list = new ArrayList<>();
        try {
            list = serviceCheque.afficher();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadCheques(list);

    }

    private void loadCheques(List<Cheque> cheques) {
        // Nettoyer le conteneur actuel
        userListContainer.getChildren().clear();

        // Réinitialiser les valeurs de la ligne et de la colonne
        int row = 0;
        int column = 0;
        int maxColumns = 3; // Nombre maximum de colonnes par ligne
        // Espacement entre les cartes
        double verticalGap = 12; // Environ 9 mm
        double horizontalGap = 12; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 10; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        userListContainer.setVgap(verticalGap);
        userListContainer.setHgap(horizontalGap);


        // Parcourir chaque chèque et charger sa carte dans le conteneur
        for (Cheque cheque : cheques) {
            try {
//                System.out.println(cheque.getId());
                // Charger la carte de chèque à partir du fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
                Parent chequeItem = loader.load();

                // Récupérer le contrôleur de la carte de chèque
                ChequeItemsController chequeItemController = loader.getController();

                // Initialiser les données du chèque dans la carte de chèque
                chequeItemController.initData(cheque);

                // Ajouter la carte de chèque au conteneur
                userListContainer.add(chequeItem, column, row);

                // Incrémenter la colonne
                column++;

                // Vérifier si nous devons passer à la ligne suivante
                if (column >= maxColumns) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception ici, si nécessaire
            }
        }

    }


    public void RetourBackC(MouseEvent mouseEvent) {

        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/dashboardClient.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void SearchCheque(KeyEvent keyEvent) {
//        String keyword = fxrecherches.getText().toLowerCase().trim(); // Récupérer le texte de la zone de recherche et le convertir en minuscules
//
//        if (keyword.isEmpty()) {
//            // Si le champ de recherche est vide, afficher tous les chèques
//            loadCheques(getAllCheques());
//        } else {
//            // Sinon, filtrer les chèques en fonction du nom du bénéficiaire
//            List<Cheque> filteredCheques = filterCheques(keyword);
//            loadCheques(filteredCheques);
//        }
//    }

    // Méthode pour récupérer tous les chèques depuis le service de chèques
    private List<Cheque> getAllCheques() {
        ServiceCheque serviceCheque = new ServiceCheque();
        try {
            return serviceCheque.afficher();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retourner une liste vide en cas d'erreur
        }
    }

    // Méthode pour filtrer les chèques en fonction du nom du bénéficiaire
    private List<Cheque> filterCheques(String keyword) {
        ServiceCheque serviceCheque = new ServiceCheque();
        try {
            List<Cheque> allCheques = serviceCheque.afficher();
            List<Cheque> filteredCheques = new ArrayList<>();

            for (Cheque cheque : allCheques) {
                // Vérifier si le nom du bénéficiaire contient le mot-clé de recherche
                if (cheque.getNom_prenom().toLowerCase().contains(keyword)) {
                    filteredCheques.add(cheque);
                }
            }

            return filteredCheques;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retourner une liste vide en cas d'erreur
        }
    }


    @FXML
    void close_updateProjectModel(MouseEvent event) {
        updateChequeModel.setVisible(false);
        updateChequeModelShow = 0;
    }

    public void ChequeclientsfSearchInput(KeyEvent keyEvent) throws SQLException {
        ServiceCheque serviceCheque = new ServiceCheque(); // Créer une instance de ServiceCheque
        String searchKeyword = ChequeclientsfSearchInput.getText();

        if (searchKeyword.isEmpty()) {
            // Si le mot-clé de recherche est vide, actualiser la liste des articles
            refreshChequeList();
        } else {
            // Rechercher les articles en fonction de l'attribut et du mot-clé
            List<Cheque> searchResults = serviceCheque.searchCheque(searchKeyword);

            // Définir une fabrique de cellules personnalisée pour le GridPane
            loadCheques(searchResults);
        }
    }

    public void refreshChequeList() throws SQLException {
        // Nettoyer le contenu actuel
        userListContainer.getChildren().clear();

        try {
            // Créer une instance de ServiceCheque
            ServiceCheque serviceCheque = new ServiceCheque();

            // Charger à nouveau la liste des chèques depuis la base de données
            List<Cheque> cheques = serviceCheque.afficher();

            // Charger à nouveau les cartes de chèques dans le conteneur
            loadCheques(cheques);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }
    }



    public void statusChange(ActionEvent actionEvent) throws SQLException {
        String selectedSortOption = statusInput.getValue();

        if (selectedSortOption != null) {
            try {
                // Créer une instance de ServiceCheque
                ServiceCheque serviceCheque = new ServiceCheque();

                // Get the list of cheques
                List<Cheque> cheques = serviceCheque.afficher();

                // Filter and sort the cheques based on the selected option
                switch (selectedSortOption) {
                    case "Paiement":
                        cheques = cheques.stream()
                                .filter(cheque -> cheque.getBeneficiaire().equals("Paiement"))
                                .sorted(Comparator.comparing(Cheque::getDecision))
                                .toList();
                        break;
                    case "Personne":
                        cheques = cheques.stream()
                                .filter(cheque -> cheque.getBeneficiaire().equals("Personne"))
                                .sorted(Comparator.comparing(Cheque::getBeneficiaire))
                                .toList();
                        break;
                    case "PaiementEco":
                        cheques = cheques.stream()
                                .filter(cheque -> cheque.getBeneficiaire().equals("PaiementEco"))
                                .sorted(Comparator.comparing(Cheque::getBeneficiaire))
                                .toList();
                        break;
                    case "Tous":
                        // No need to filter, just sort all cheques
                        cheques.sort(Comparator.comparing(Cheque::getBeneficiaire));
                        break;
                    default:
                        break;
                }

                // Reload the cheque cards with the filtered and sorted list
                loadCheques(cheques);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception
            }
        } else {
            // If no option is selected, simply reload all cheques without sorting
            ShowListe();
        }
    }

}


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ServiceCheque serviceCheque = new ServiceCheque();
//        List<Cheque> list = new ArrayList<>();
//        try {
//            list = serviceCheque.afficher(); // Notez le changement ici
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        loadCheques(list);
//
////        for (Cheque cheque : list) {
////            try {
////                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
////                Parent offreItem = loader.load();
////                ChequeItems offreStageItem = loader.getController();
////                offreStageItem.initData(cheque);
////                userListContainer.getChildren().add(offreItem);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//
//    }
//
//
//    private void loadCheques(List<Cheque> cheques) {
//        // Nettoyer le conteneur actuel
//        userListContainer.getChildren().clear();
//
//        // Réinitialiser les valeurs de la ligne et de la colonne
//        int row = 0;
//        int column = 0;
//        int maxColumns = 3; // Nombre maximum de colonnes par ligne
//
//        // Parcourir chaque chèque et charger sa carte dans le conteneur
//        for (Cheque cheque : cheques) {
//            try {
//                // Charger la carte de chèque à partir du fichier FXML
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
//                Parent chequeItem = loader.load();
//
//                // Récupérer le contrôleur de la carte de chèque
//                ChequeItems chequeItemController = loader.getController();
//
//                // Initialiser les données du chèque dans la carte de chèque
//                chequeItemController.initData(cheque);
//
//                // Ajouter la carte de chèque au conteneur
//                userListContainer.getChildren().add(chequeItem);
//
//                // Incrémenter le nombre de colonnes
//                column++;
//
//                // Vérifier si nous avons atteint le nombre maximum de colonnes par ligne
//                if (column >= maxColumns) {
//                    // Réinitialiser la colonne à zéro et passer à la ligne suivante
//                    column = 0;
//                    row++;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                // Gérer l'exception ici, si nécessaire
//            }
//        }
//    }




//    private void loadCheques() {
//        userListContainer.getChildren().clear();
//
//        ServiceCheque serviceCheque = new ServiceCheque();
//        try {
//            List<Cheque> list = serviceCheque.afficher();
//            int columnCount = 0;
//            VBox column = new VBox();
//            for (Cheque cheque : list) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
//                Parent chequeItem = loader.load();
//                ChequeItems chequeItemController = loader.getController();
//                chequeItemController.initData(cheque);
//                column.getChildren().add(chequeItem);
//                columnCount++;
//                if (columnCount == 3) {
//                    userListContainer.getChildren().add(column);
//                    column = new VBox();
//                    columnCount = 0;
//                }
//            }
//            // Ajouter la dernière colonne si elle contient moins de 3 cartes
//            if (columnCount > 0) {
//                userListContainer.getChildren().add(column);
//            }
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//private void loadCheques(List<Cheque> cheques) {
//    // Nettoyer le conteneur actuel
//    userListContainer.getChildren().clear();
//
//    // Réinitialiser les valeurs de la ligne et de la colonne
//    int row = 0;
//    int column = 0;
//    int maxColumns = 3; // Nombre maximum de colonnes par ligne
//
//    // Parcourir chaque chèque et charger sa carte dans le conteneur
//    for (Cheque cheque : cheques) {
//        try {
//            // Charger la carte de chèque à partir du fichier FXML
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
//            GridPane chequeItem = loader.load();
//
//            // Récupérer le contrôleur de la carte de chèque
//            ChequeItems chequeItemController = loader.getController();
//
//            // Initialiser les données du chèque dans la carte de chèque
//            chequeItemController.initData(cheque);
//
//            // Ajouter la carte de chèque au conteneur
//            userListContainer.add(chequeItem, column, row);
//
//            // Incrémenter la colonne
//            column++;
//
//            // Vérifier si nous devons passer à la ligne suivante
//            if (column >= maxColumns) {
//                column = 0;
//                row++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Gérer l'exception ici, si nécessaire
//        }
//    }
//}
//




// @Override
//public void initialize(URL url, ResourceBundle resourceBundle) {
      /*  ServiceCheque serviceOffreDeStage = new ServiceCheque();
        Cheque offreDeStage;
        try {
            List<Cheque> lisOffre = serviceOffreDeStage.afficher();

            for (int i =0; i <lisOffre.size() ; i++){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("FXML/ChequeItems.fxml"));
                VBox offreItem = loader.load();
                ChequeItems offreStageItem = loader.getController();
                offreStageItem.initData(lisOffre.get(i));
                userListContainer.getChildren().add(offreItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/