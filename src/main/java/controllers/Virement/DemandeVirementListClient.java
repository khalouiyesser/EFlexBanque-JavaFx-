package controllers.Virement;

import Entities.Cheque;
import Entities.Virement;
import controllers.CaptureEcran;
import controllers.Cheque.AjouterChequeCard;
import controllers.Cheque.DemandeChequeListClient;
import controllers.Cheque.updateChequeCard;
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
import services.ServiceVirement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class DemandeVirementListClient  implements Initializable {

    @FXML
    private GridPane FundsListContainer;
    @FXML
    private Button Refresh;
    @FXML
    private Button captureEcran;

    @FXML
    private HBox InvestBtn;

    @FXML
    private Button VirBtn;

    @FXML
    private HBox actualitesBtn;

    @FXML
    private GridPane VirementList;


    @FXML
    private ImageView actualitesIcon;

    @FXML
    private Label actualitesText;

    @FXML
    private HBox compteBtn;

    @FXML
    private ImageView comptesIcon;

    @FXML
    private Label comptesText;

    @FXML
    private Pane content_area;

    @FXML
    private HBox creditsBtn;

    @FXML
    private ImageView creditsIcon;

    @FXML
    private Label creditsText;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private Label dashboardText;

    @FXML
    private HBox evenementsBtn;

    @FXML
    private ImageView evenementsIcon;

    @FXML
    private Label evenementsText;

    @FXML
    private ImageView investissementsIcon;

    @FXML
    private Label investissementsText;

    @FXML
    private ImageView logo;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private HBox recBtn;

    @FXML
    private Label reclamationText;

    @FXML
    private ImageView reclamationsIcon;

    @FXML
    private HBox sideBarLogout;

    @FXML
    private HBox stagesBtn;

    @FXML
    private Pane content_areaV;
    @FXML
    private ImageView stagesIcon;

    @FXML
    private Label stagesText;

    @FXML
    private ComboBox<String> statusInput;

    @FXML
    private HBox usersBtn;

    @FXML
    private ImageView usersIcon;

    @FXML
    private Label usersText;

    @FXML
    private Button btnvir;
    @FXML
    private HBox updateVirementModel;

    @FXML
    private VBox updateVirementModelContent;
    private static int virementIdToUpdate = 0;
    private static int updateVirementModelShow = 0;





    public void AjouterVirement(MouseEvent mouseEvent) {
    }


//    public void ajouterV(MouseEvent mouseEvent) throws IOException {
//
//    }


    public void AjouterV(MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormCardVirement.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterVirementCard ajouterVirementCard = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }
    public static void setUpdateVirementModelShow(int updateVirementModelShow) {
        DemandeVirementListClient.updateVirementModelShow = updateVirementModelShow;
    }
    public static void setchequeEmailToUpdate(int virementIdToUpdate) {
        DemandeVirementListClient.virementIdToUpdate = virementIdToUpdate;
    }
    public static int getUpdateVirementModelShow() {
        return updateVirementModelShow;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> trie = FXCollections.observableArrayList(
                "Tous",
                "Personne",
                "VEcoresponsabilité"
        );
        statusInput.setItems(trie);
        Virement virement;
        ServiceVirement serviceVirement = new ServiceVirement();
        List<Virement> list = new ArrayList<>();
        try {
            list = serviceVirement.afficher();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (DemandeVirementListClient.getUpdateVirementModelShow() == 0) {
            updateVirementModel.setVisible(false);
        } else if (DemandeVirementListClient.getUpdateVirementModelShow() == 1) {
            updateVirementModel.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/UpdateVirementCard.fxml"));
            VBox updateProjectform;
            try {
                updateProjectform = fxmlLoader1.load();
                updateVirementCard updateUserCardController = fxmlLoader1.getController();
                updateVirementCard.setFxmlToLoad("DemandeVirementListClient.fxml");
                virement = serviceVirement.getById(virementIdToUpdate);

                updateUserCardController.setProjectUpdateData(virement);
                updateVirementModelContent.getChildren().add(updateProjectform);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        loadVirement(list);

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
    private void ShowListe() {
        ServiceVirement serviceVirement = new ServiceVirement();
        List<Virement> list = new ArrayList<>();
        try {
            list = serviceVirement.afficher();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadVirement(list);

    }
    private void loadVirement(List<Virement> virements) {
        // Nettoyer le conteneur actuel
        VirementList.getChildren().clear();

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
        VirementList.setVgap(verticalGap);
        VirementList.setHgap(horizontalGap);


        // Parcourir chaque chèque et charger sa carte dans le conteneur
        // Parcourir chaque virement et charger sa carte dans le conteneur
        for (Virement virement : virements) {
            try {
                // Charger la carte de virement à partir du fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementCard.fxml"));
                Parent virementCardParent = loader.load();

                // Récupérer le contrôleur de la carte de virement
                VirementCard virementCardController = loader.getController();

                // Initialiser les données du virement dans la carte de virement
                virementCardController.initData(virement);

                // Ajouter la carte de virement au conteneur
                VirementList.add(virementCardParent, column, row);

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
            }}}

    public void searchVirement(KeyEvent keyEvent) {
    }

    public void RetourBackV(MouseEvent mouseEvent) {
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

    public void close_updateProjectModel(MouseEvent mouseEvent) {
        updateVirementModel.setVisible(false);
        updateVirementModelShow = 0;
    }

    public void statusChange(ActionEvent actionEvent) throws SQLException {
        String selectedSortOption = statusInput.getValue();

        if (selectedSortOption != null) {
            try {
                // Créer une instance de ServiceVirement
                ServiceVirement serviceVirement = new ServiceVirement();

                // Get the list of virements
                List<Virement> virements = serviceVirement.afficher();

                // Filter and sort the virements based on the selected option
                switch (selectedSortOption) {
                    case "Personne":
                        virements = virements.stream()
                                .filter(virement -> virement.getType_virement().equals("Personne"))
                                .sorted(Comparator.comparing(Virement::getType_virement))
                                .toList();
                        break;
                    case "VEcoresponsabilité":
                        virements = virements.stream()
                                .filter(virement -> virement.getType_virement().equals("VEcoresponsabilité"))
                                .sorted(Comparator.comparing(Virement::getType_virement))
                                .toList();
                        break;
                    case "Tous":
                        // No need to filter, just sort all virements
                        virements.sort(Comparator.comparing(Virement::getType_virement));
                        break;
                    default:
                        break;
                }

                // Reload the virement cards with the filtered and sorted list
                loadVirement(virements);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception
            }
        } else {
            // Si aucune option n'est sélectionnée, rechargez simplement tous les virements sans tri
            ShowListe();
        }
    }





}
