package controllers;

import Entities.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import services.ServiceProjet;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

public class ShowProjectCardController implements Initializable {

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text nomProjetInput;

    @FXML
    private ImageView imageInput;
    @FXML
    private Text categorieInput;

    @FXML
    private Text descriptionProjetInput;

    @FXML
    private Text budgetProjetInput;

    @FXML
    private Text dateCreationInput;

    @FXML
    private Text dureeProjetInput;

    @FXML
    private Text statutProjetInput;

    @FXML
    private Button update_projectBtn;

    Project projectToUpdate;
    private File selectedImageFile;
    private String imageName;
    private static String FxmlToLoad;

    public static String getFxmlToLoad() {
        return FxmlToLoad;
    }

    public static void setFxmlToLoad(String FxmlToLoad) {
        ShowProjectCardController.FxmlToLoad = FxmlToLoad;
    }

    @FXML
    void ajouter_image(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("project.dir"), "java", "assets", "projectUploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @FXML
    void updateProject(MouseEvent event) {
        ServiceProjet projectService = new ServiceProjet();
        projectToUpdate.setNomProjet(nomProjetInput.getText());
        projectToUpdate.setDescriptionProjet(descriptionProjetInput.getText());
        projectToUpdate.setStatutProjet(Integer.parseInt(statutProjetInput.getText()));
        projectToUpdate.setDureeProjet(Integer.parseInt(dureeProjetInput.getText()));
        projectToUpdate.setBudgetProjet(Float.parseFloat(budgetProjetInput.getText()));
        projectToUpdate.setCategorie(categorieInput.getText());
        projectToUpdate.setDateCreation(LocalDateTime.parse(dateCreationInput.getText()));
        projectToUpdate.setImg(imageName);
        try {
            projectService.modifier(projectToUpdate);
            ProjectListController.setupdateProjectModelShow(0);
            ProjectListController.setupdateProjectModelShow(0);

            TrayNotificationAlert.notif("Mettre à jour le projet", "Projet mis à jour avec succès.",
                    NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/ProjectsList.fxml"));
            try {
                Parent root = loader.load();

                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setProjectUpdateData(Project project) {
        projectToUpdate = project;
        nomProjetInput.setText(project.getNomProjet());
        descriptionProjetInput.setText(project.getDescriptionProjet());
        categorieInput.setText(project.getCategorie());
        dureeProjetInput.setText(String.valueOf(project.getDureeProjet()));
        budgetProjetInput.setText(String.valueOf(project.getBudgetProjet()));
        statutProjetInput.setText(String.valueOf(project.getStatutProjet()));
        dateCreationInput.setText(String.valueOf(project.getDateCreation()));
        Image image = new Image(getClass().getResource("/assets/projectUploads/" +project.getImg()).toExternalForm());
        imageInput.setImage(image);
        imageName = project.getImg();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
