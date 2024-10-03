package controllers;

import Entities.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.function.Consumer;
public class FXML_ProjetDetailController implements Initializable {
  private Project projet;
  private Consumer<Project> callback;

  @FXML
  private TextField fxImg;

  @FXML
  private TextField fxCategorie;


  @FXML
  private TextField fxDateCreation;

  @FXML
  private TextField fxDureeProjet;

  @FXML
  private TextField fxStatutProjet;

  @FXML
  private TextField fxDesc;

  @FXML
  private TextField fxNom;

  @FXML
  private TextField fxBudget;
  @FXML
  private javafx.scene.control.Button saveButton;
  private boolean saveClicked = false;



  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public boolean isSaveClicked() {
    return saveClicked;
  }






  public void initData(Project projet) {
    this.projet = projet;

    fxNom.setText(projet.getNomProjet());
    fxImg.setText(projet.getImg());
    fxCategorie.setText(projet.getCategorie());
    fxDesc.setText(projet.getDescriptionProjet());
    fxBudget.setText(String.valueOf(projet.getBudgetProjet()));
    LocalDateTime dateCreation = projet.getDateCreation();
    fxDateCreation.setText(dateCreation != null ? dateCreation.toString() : "");
    fxDureeProjet.setText(String.valueOf(projet.getDureeProjet()));
    fxStatutProjet.setText(String.valueOf(projet.getStatutProjet()));
  }

  public void setCallback(Consumer<Project> callback) {
    this.callback = callback;
  }

  @FXML
  private void onSaveButtonClick(ActionEvent event) {
    if (fxNom.getText().isEmpty() || fxCategorie.getText().isEmpty() || fxDesc.getText().isEmpty() || fxBudget.getText().isEmpty() || fxDateCreation.getText().isEmpty() || fxDureeProjet.getText().isEmpty() || fxStatutProjet.getText().isEmpty() || fxImg.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Vérifier tous les champs sont remplis !", "Modification échouée", 2);
    } else {
      projet.setNomProjet(fxNom.getText());
      projet.setCategorie(fxCategorie.getText());
      projet.setDescriptionProjet(fxDesc.getText());
      projet.setBudgetProjet(Double.parseDouble(fxBudget.getText()));
      projet.setDureeProjet(Integer.parseInt(fxDureeProjet.getText()));
      projet.setStatutProjet(Integer.parseInt(fxStatutProjet.getText()));
      projet.setDateCreation(LocalDateTime.parse(fxDateCreation.getText()));


      // call the callback function to update the data in the parent
      callback.accept(projet);
      // Close the window
      Stage stage = (Stage) saveButton.getScene().getWindow();
      stage.close();
    }
  }

  @FXML
  private void onCancel(ActionEvent event) {
    Stage stage = (Stage) fxNom.getScene().getWindow();
    stage.close();
  }


}
