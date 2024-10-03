package controllers.Virement;

import Entities.Cheque;
import Entities.Compte;
import Entities.Virement;
import controllers.Cheque.ChequeItemsAdmin;
import controllers.Cheque.ListeChequeAdmin;
import controllers.Compte.CompteItems;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;
import services.ServiceCompte;
import services.ServiceVirement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ListVirementAdmin  implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private Button retourVir;
    @FXML
    private ComboBox<String > trie;

    @FXML
    private VBox VireContainer;
    @FXML
    private TextField VirementclientsfSearchInputAdmin;
    private static ListVirementAdmin instance;

    public ListVirementAdmin() {
        instance = this;
    }
    public static ListVirementAdmin getInstance(){return  instance ; }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> trieList = FXCollections.observableArrayList(
                "Tous",
                "Personne",
                "VEcoresponsabilité"
        );
        trie.setItems(trieList);

        ServiceVirement serviceVirement = new ServiceVirement();
        List<Virement> allVirements = new ArrayList<>();
        List<Virement> filtredVirements = new ArrayList<>();

        try {
            allVirements = serviceVirement.afficher(); // Fetch all cheques from the database
            // Filter out cheques that are "Approuvé"
            for (Virement virement : allVirements) {
                if (!"Approuvé".equals(virement.getDecision_v())) {
                    filtredVirements.add(virement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Virement virement : filtredVirements) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent offreItem = loader.load();
                VirementItemsAdmin offreStageItem = loader.getController();
                offreStageItem.initData(virement);
                VireContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void refreshVirementList() {
        VireContainer.getChildren().clear();  // Clear the existing views
        List<Virement> filtredVirements = new ArrayList<>();
        try {
            List<Virement> allVirements = new ServiceVirement().afficher();
            for (Virement virement : allVirements) {
                if (!"Approuvé".equals(virement.getDecision_v())) {
                    filtredVirements.add(virement);
                }

            }

            for (Virement virement : filtredVirements) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent item = loader.load();
                VirementItemsAdmin controller = loader.getController();
                controller.initData(virement);
                VireContainer.getChildren().add(item);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }



    @FXML
    void returnBackVir (MouseEvent event){

        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CardAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void VirementclientsfSearchInputAdmin(KeyEvent keyEvent) throws SQLException {
        ServiceVirement serviceVirement = new ServiceVirement(); // Créer une instance de ServiceCheque
        String searchKeyword = VirementclientsfSearchInputAdmin.getText();

        if (searchKeyword.isEmpty()) {
            // Si le mot-clé de recherche est vide, actualiser la liste des articles
            refreshVirementList();
        } else {
            try {
                List<Virement> searchResults = serviceVirement.searchVirement(searchKeyword);
                loadVirement(searchResults);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    private void loadVirement(List<Virement> searchResults) {
        VireContainer.getChildren().clear();
        for (Virement virement : searchResults) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent item = loader.load();
                VirementItemsAdmin controller = loader.getController();
                controller.initData(virement);
                VireContainer.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void trier(ActionEvent actionEvent) throws SQLException {
        String selectedSortOption = trie.getValue();

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


}