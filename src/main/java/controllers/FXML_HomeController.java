/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Commentaire;
import Entities.Evenement;
import Entities.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceCommentaire;
import services.ServiceEvenement;
import services.ServiceProjet;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
public class FXML_HomeController implements Initializable {

  @FXML
  private TableView<Evenement> tableEvenement = new TableView<>();
  @FXML
  private TableView<Commentaire> tableCommentaire = new TableView<>();
  @FXML
  private TableView<Project> tableProjet = new TableView<>();

  @FXML
  private ComboBox<String> fxProjectName;

  @FXML
  private Button registerButton;
  @FXML
  private TextField textField = new TextField();

  @FXML
  private TextField textFieldEvenement = new TextField();
  @FXML
  private TextField textFieldCommentaire = new TextField();
  @FXML
  private TextField textFieldProjet = new TextField();
  @FXML
  private TextField textFieldParnter = new TextField();


  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    try {
      getProjet();

      getEvenement();

      getCommentaire();


    } catch (SQLException e) {
      System.out.println(e);
    }
  }

//  private void getCommentaire() throws SQLException {
//    final ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
//    ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
//    List<Commentaire> mesCommentaire = serviceCommentaire.readAll();
//    commentaires.addAll(mesCommentaire);
//    tableCommentaire.setItems(commentaires);
//    textFieldCommentaire.textProperty().addListener((observable, oldValue, newValue) -> {
//      List<Commentaire> mesClientCopy = mesCommentaire
//        .stream()
//        .filter(c -> c.getNom().contains(newValue) || c.getPrenom().contains(newValue))
//        .collect(Collectors.toList());
//      // mesAvions.stream().filter(name -> name.startsWith(newValue));
//      System.out.println(mesClientCopy);
//      tableCommentaire.getItems().clear();
//      clients.addAll(mesClientCopy);
//      tableCommentaire.setItems(clients);
//    });
//  }
  private void getEvenement() throws SQLException {
    final ObservableList<Evenement> evenements = FXCollections.observableArrayList();
    ServiceEvenement serviceEvenement = new ServiceEvenement();
    List<Evenement> mesEvenement = serviceEvenement.afficher();
    evenements.addAll(mesEvenement);
    tableEvenement.setItems(evenements);

    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      List<Evenement> mesEvenementCopy = mesEvenement
        .stream()
        .filter(c -> c.getNom().contains(newValue) ||
          c.getDescription().contains(newValue) ||
          c.getDateDebut().toString().contains(newValue) ||
          c.getDateFin().toString().contains(newValue) ||
          c.getLieu().contains(newValue) ||
          c.getOrganisateur().contains(newValue) ||
          String.valueOf(c.getPrix()).contains(newValue) ||
          String.valueOf(c.getLikes()).contains(newValue) ||
          String.valueOf(c.getDislikes()).contains(newValue) ||
          String.valueOf(c.getProjectId()).contains(newValue))
        .collect(Collectors.toList());
      tableEvenement.getItems().clear();
      evenements.addAll(mesEvenementCopy);
      tableEvenement.setItems(evenements);
    });
    tableEvenement.setRowFactory(tv -> {
      TableRow<Evenement> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && !row.isEmpty()) {
          Evenement rowData = row.getItem();
          try {
            clickEvenement(rowData);
          } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
          }
        }
      });
      return row;
    });
  }

  private void getCommentaire() throws SQLException {
    final ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
    ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
    List<Commentaire> mesCommentaire = serviceCommentaire.afficher();
    commentaires.addAll(mesCommentaire);
    tableCommentaire.setItems(commentaires);

    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      List<Commentaire> mesCommentaireCopy = mesCommentaire
        .stream()
        .filter(c -> c.getContenu().contains(newValue) || c.getDate().contains(newValue)||
          c.getNomuser().contains(newValue)|| c.getImg().contains(newValue))
        .collect(Collectors.toList());
      tableCommentaire.getItems().clear();
      commentaires.addAll(mesCommentaireCopy);
      tableCommentaire.setItems(commentaires);
    });
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

  private void alertDial(String msg) throws IOException {
    JOptionPane.showMessageDialog(null, msg);
  }
  @FXML
  private void deleteEvenement() {
    int selectedRowIndex = tableEvenement.getSelectionModel().getSelectedIndex();
    if (selectedRowIndex >= 0) {
      int selectedIndex = tableEvenement.getSelectionModel().getSelectedItem().getId();
      System.out.println("Evenement to delete ID  : " + selectedIndex);

      // delete selected item in the database
      ServiceEvenement serviceEvenement = new ServiceEvenement();
      try {
        serviceEvenement.supprimer(selectedIndex);
        getEvenement();
        tableEvenement.refresh();
        try {
          alertDial("Evenement supprimé");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      try {
        alertDial("Vous devez choisir une ligne !");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }



  @FXML
  private void deleteProjet() {
    int selectedRowIndex = tableProjet.getSelectionModel().getSelectedIndex();
    if (selectedRowIndex >= 0) {
      int selectedIndex = tableProjet.getSelectionModel().getSelectedItem().getId();
      System.out.println("Projet to delete ID  : " + selectedIndex);

      // delete selected item in the database
      ServiceProjet serviceProjet = new ServiceProjet();
      try {
        serviceProjet.supprimer(selectedIndex);
        getProjet();
        tableProjet.refresh();
        try {
          alertDial("Projet supprimé");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      try {
        alertDial("Vous devez choisir une ligne !");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
  @FXML
  private void addEvenement() throws IOException, SQLException {
    Evenement evenement = new Evenement(-1);
    clickEvenement(evenement); // Pass -1 as the project ID for a new event
  }
  private void clickEvenement(Evenement selectedEvenement) throws IOException, SQLException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EvenementDetail.fxml"));
    Parent root = fxmlLoader.load();
    FXML_EvenementDetailController evenementDetailController = fxmlLoader.getController();


    evenementDetailController.initData(selectedEvenement); // Pass the project ID
    evenementDetailController.setCallback(updatedData -> {
      if (updatedData.getId() == -1) {
        ServiceEvenement serviceEvenement = new ServiceEvenement();
        try {
          int projectId = new ServiceEvenement().getProjectIdByName("Ayoub"); // Get the project ID from the selected project name

          serviceEvenement.ajouter1(updatedData, projectId); // Use ajouter1 method with project ID
          getEvenement();
          tableEvenement.refresh();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } else {
        // Update the data in the table view
        tableEvenement.getItems().set(tableEvenement.getSelectionModel().getSelectedIndex(), updatedData);

        // Update the data in the database
        ServiceEvenement serviceEvenement = new ServiceEvenement();
        try {
          serviceEvenement.modifier(updatedData);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    });

    Stage newStage = new Stage();
    newStage.initModality(Modality.APPLICATION_MODAL);
    Scene newScene = new Scene(root);
    newStage.setScene(newScene);
    // Show the new stage
    newStage.showAndWait();
  }
  @FXML
  private void addProjet() throws IOException {
    Project projet = new Project(-1);
    clickProjet(projet);
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
}
