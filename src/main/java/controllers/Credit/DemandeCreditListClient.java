package controllers.Credit;

import Entities.Credit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import services.ServiceCredit;
import utils.MyDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DemandeCreditListClient implements Initializable {

    @FXML
    private Button chequebtn;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<String> statusInput;

    @FXML
    private VBox userListContainer;

    @FXML
    private HBox userTableHead;

    @FXML
    private Button addcreditbtn;



    @FXML
    void ajoutercredit(MouseEvent event) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormCardCreditAdmin.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterCreditCard ajouterCreditCard = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);

    }
    @FXML
    private Button deletebtn;

    @FXML
    void statusChange(ActionEvent event) {
        userListContainer.getChildren().clear();

        if (statusInput.getValue().equals("Montant")) {
            // Appeler la fonction afficherCreditParMontant
            ServiceCredit serviceCredit = new ServiceCredit();
            System.out.println("f");
            List<Credit> list = new ArrayList<>();
            try {
                list = serviceCredit.affichercreditparmontant(); // Notez le changement ici
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (Credit credit : list) {
                try {
                    System.out.println(credit);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CreditItems.fxml"));
                    Parent offreItem = loader.load();
                    CreditItems offreStageItem = loader.getController();
                    offreStageItem.initData(credit);
                    userListContainer.getChildren().add(offreItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else  if (statusInput.getValue().equals("Year")) {
            // Appeler la fonction afficherCreditParMontant
            ServiceCredit serviceCredit = new ServiceCredit();
            System.out.println("f");
            List<Credit> list = new ArrayList<>();
            try {
                list = serviceCredit.affichercreditpardatedebut(); // Notez le changement ici
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (Credit credit : list) {
                try {
                    System.out.println(credit);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CreditItems.fxml"));
                    Parent offreItem = loader.load();
                    CreditItems offreStageItem = loader.getController();
                    offreStageItem.initData(credit);
                    userListContainer.getChildren().add(offreItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusInput.getItems().addAll("Montant", "Year");
        ServiceCredit serviceCredit = new ServiceCredit();
        System.out.println("f");
        List<Credit> list = new ArrayList<>();
        try {
            list = serviceCredit.afficher(); // Notez le changement ici
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("xiii");
        for (Credit credit : list) {
            try {
                System.out.println(credit);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CreditItems.fxml"));
                Parent offreItem = loader.load();
                CreditItems offreStageItem = loader.getController();
                offreStageItem.initData(credit);
                userListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;
    public void AjouterC(MouseEvent mouseEvent) {


    }
    int iddel = 0; // Variable globale pour stocker l'ID à supprimer

    // Méthode pour affecter une valeur à la variable iddel
    void rendid(int id){
        iddel = id;
    }

    @FXML
    void deletecredit(MouseEvent event) {
        String Delete = "DELETE FROM credit WHERE id = ?";

        Connection con = MyDatabase.getInstance().getConnection();
        try {
            // Préparation de la requête SQL
            PreparedStatement st = con.prepareStatement(Delete);
            // Affectation de la valeur de iddel au paramètre de la requête
            st.setInt(1, iddel);
            // Exécution de la requête de suppression
            st.executeUpdate();
        } catch (SQLException e) {
            // Gestion des exceptions
            throw new RuntimeException(e);
        }
    }
    @FXML
    void prendrerdv(MouseEvent event) {


    }
}