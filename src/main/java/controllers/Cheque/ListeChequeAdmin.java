package controllers.Cheque;

import Entities.Cheque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ListeChequeAdmin implements Initializable {


    @FXML
    private VBox ChequeContainer;
    @FXML
    private Pane content_area;
    @FXML
    private TextField ChequeclientsfSearchInputAdmin;
    @FXML
    private ComboBox<String > trie;

    private static ListeChequeAdmin instance;

    public ListeChequeAdmin() {
        instance = this;
    }

    public static ListeChequeAdmin getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> trie1 = FXCollections.observableArrayList(
                "tous",
                "Paiement",
                "PaiementEco",
                "Personne"
        );
        trie.setItems(trie1);
        ServiceCheque serviceCheque = new ServiceCheque();
        List<Cheque> allCheques = new ArrayList<>();
        List<Cheque> filteredCheques = new ArrayList<>();
        try {
            allCheques = serviceCheque.afficher(); // Fetch all cheques from the database
            // Filter out cheques that are "Approuvé"
            for (Cheque cheque : allCheques) {
                if (!"Approuvé".equals(cheque.getDecision())) {
                    filteredCheques.add(cheque);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Cheque cheque : filteredCheques) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItemsAdmin.fxml"));
                Parent offreItem = loader.load();
                ChequeItemsAdmin offreStageItem = loader.getController();
                offreStageItem.initData(cheque);
                ChequeContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        refreshChequeList();

    }
    // ListeChequeAdmin.java

    public void refreshChequeList() {
        ChequeContainer.getChildren().clear();  // Clear the existing views
        List<Cheque> filteredCheques = new ArrayList<>();
        try {
            List<Cheque> allCheques = new ServiceCheque().afficher();
            for (Cheque cheque : allCheques) {
                if (!"Approuvé".equals(cheque.getDecision())) {
                    filteredCheques.add(cheque);
                }

            }

            for (Cheque cheque : filteredCheques) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItemsAdmin.fxml"));
                Parent item = loader.load();
                ChequeItemsAdmin controller = loader.getController();
                controller.initData(cheque);
                ChequeContainer.getChildren().add(item);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }

    public void returnbackCardCH(MouseEvent mouseEvent) {

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

    public void ChequeclientsfSearchInputAdmin(KeyEvent keyEvent) throws SQLException {
        ServiceCheque serviceCheque = new ServiceCheque(); // Créer une instance de ServiceCheque
        String searchKeyword = ChequeclientsfSearchInputAdmin.getText();

        if (searchKeyword.isEmpty()) {
            // Si le mot-clé de recherche est vide, actualiser la liste des articles
            refreshChequeList();
        } else {
            try {
                List<Cheque> searchResults = serviceCheque.searchCheque(searchKeyword);
                loadCheques(searchResults);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadCheques(List<Cheque> searchResults) {
        ChequeContainer.getChildren().clear();
        for (Cheque cheque : searchResults) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItemsAdmin.fxml"));
                Parent item = loader.load();
                ChequeItemsAdmin controller = loader.getController();
                controller.initData(cheque);
                ChequeContainer.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void trierC(ActionEvent event) {
        String selectedSortOption = trie.getValue();

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

    private void ShowListe() {
    }
}
