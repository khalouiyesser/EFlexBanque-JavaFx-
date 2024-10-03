package controllers;

import Entities.Commentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DataSource;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class FXML_CommentaireDetailController implements Initializable {

    private Consumer<Commentaire> callback;

    @FXML
    public TextField fxNom;
    @FXML
    private TextField fxDesc;
    @FXML
    private TextField fxdate;
    @FXML
    private TextField fxdateFin;
    @FXML
    private TextField fxLieu;
    @FXML
    private TextField fxOrganisateur;
    @FXML
    private TextField fxPrix;
    @FXML
    private TextField fxLikes;
    @FXML
    private TextField fxDislikes;
    @FXML
    private ComboBox<String> fxProjectName;
    private Commentaire commentaire;
    @FXML
    private javafx.scene.control.Button saveButton;

    private boolean saveClicked = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public void initData(Commentaire commentaire) {
        this.commentaire = commentaire;
        fxNom.setText(commentaire.getNomuser());
        fxDesc.setText(commentaire.getContenu());
        fxdate.setText(commentaire.getDate());


    }


    private int getProjectIdByName(String projectName) throws SQLException {
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement("SELECT id FROM project WHERE nomProjet = ?")) {
            preparedStatement.setString(1, projectName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new SQLException("Project not found: " + projectName);
            }
        }
    }


//  @FXML
//  private void onSaveButtonClick(ActionEvent event) throws SQLException {
//    // Save the changes to the Avion object
//    if (fxNom.getText().isEmpty() ||
//      fxDesc.getText().isEmpty() ||
//      fxdateDebut.getText().isEmpty() ||
//      fxdateFin.getText().isEmpty() ||
//      fxLieu.getText().isEmpty() ||
//      fxOrganisateur.getText().isEmpty() ||
//      fxPrix.getText().isEmpty()) {
//      JOptionPane.showMessageDialog(null, "Vérifier tous les champs sont remplis !", "Modification échouée", 2);
//    } else {
//      evenement.setNom(fxNom.getText());
//      evenement.setDescription(fxDesc.getText());
//      evenement.setDateDebut(LocalDateTime.parse(fxdateDebut.getText()));
//      evenement.setDateFin(LocalDateTime.parse(fxdateDebut.getText())); // <-- Corrected to parse fxdateFin.getText()
//      evenement.setLieu(fxLieu.getText());
//      evenement.setPrix(Float.parseFloat(fxPrix.getText()));
//      evenement.setOrganisateur(fxOrganisateur.getText());
//
//      // Set the project ID based on the selected project name
//      evenement.setProjectId(getProjectIdByName(fxProjectName.getValue())); // Adjust index if necessary
//
//      // Call the callback function to update the data in the parent
//      callback.accept(evenement);
//
//      // Close the window
//      Stage stage = (Stage) saveButton.getScene().getWindow();
//      stage.close();
//    }
//  }

    @FXML
    private void onCancel(ActionEvent event) {
        ((Stage) fxNom.getScene().getWindow()).hide();
    }

}
