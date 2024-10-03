package controllers;

import Entities.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import services.IService;
import services.ServiceProjet;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.google.gson.internal.bind.TypeAdapters.UUID;

public class AddProjectCardController implements Initializable {
    @FXML
    private HBox nameInputErrorHbox;
    @FXML
    public TextField fxStatut;
    @FXML
    private TextField fxBudget;
    @FXML
    private DatePicker fxdate;
    @FXML
    private DatePicker fxdateFin;
    @FXML
    private TextArea fxDescription;

    @FXML
    private TextField nameInput;
    @FXML
    private TextField fxDuree;
    @FXML
    private TextField fxCategorie;
    @FXML
    private TextField prixInput;

    @FXML
    private Button add_new_EvenementBtn;

    @FXML
    private Button update_EvenementBtn;

    @FXML
    private ImageView imageInput;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text nameInputError;

    @FXML
    private Text descriptionInputError;
    @FXML
    private Text categoryInputError;
    @FXML
    private Text numberInputError;
    @FXML
    private Text StatutInputError;
    @FXML
    private Text DureeInputError;
    @FXML
    private Text priceInputError;
    @FXML
    private Text pointsInputError;
    @FXML
    private Text BudgetInputError;

    @FXML
    private Text photoInputError;

    @FXML
    private Text dateInputError;
    @FXML
    private HBox descriptionInputErrorHbox;
    @FXML
    private HBox BudgetInputErrorHbox;
    @FXML
    private HBox categoryInputErrorHbox;
    @FXML
    private HBox StatutInputErrorHbox;
    @FXML
    private HBox CategorieInputErrorHbox;

    @FXML
    private HBox dateInputErrorHbox;

    @FXML
    private HBox DureeInputErrorHbox;

    @FXML
    private HBox numberInputErrorHbox;

    @FXML
    private HBox priceInputErrorHbox;

    @FXML
    private HBox pointsInputErrorHbox;

    @FXML
    private HBox photoInputErrorHbox;

    private int categId = -1;
    private File selectedImageFile;
    private String imageName = null;

    private int nomTest = 0;
    private int numberTest = 0;
    private int BudgetTest = 0;
    private int descriptionTest = 0;
    private int DureeTest = 0;
    private int priceTest = 0;
    private int CategorieTest = 0;
    private int pointsTest = 0;
    private int StatutTest = 0;
    private int photoTest = 0;
    private int DateTest = 0;
    private String etiquette = null;

    private double score;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nameInputErrorHbox.setVisible(false);
        descriptionInputErrorHbox.setVisible(false);
        BudgetInputErrorHbox.setVisible(false);
        StatutInputErrorHbox.setVisible(false);
        CategorieInputErrorHbox.setVisible(false);
        DureeInputErrorHbox.setVisible(false);
        dateInputErrorHbox.setVisible(false);

        IService serviceProject = new ServiceProjet();
        Project p = new Project();
        nameInput.setText(p.getNomProjet());
        fxDescription.setText(p.getDescriptionProjet());
        fxCategorie.setText(p.getCategorie());
        fxStatut.setText(String.valueOf(p.getStatutProjet()));
        fxBudget.setText(String.valueOf(p.getBudgetProjet()));
        fxDuree.setText(String.valueOf(p.getDureeProjet()));
        if (p.getImg() != null) {
            Image image = new Image(getClass().getResource("/assets/ProductUploads/" + p.getImg()).toExternalForm());
            imageInput.setImage(image);
        } else {
            Image image = new Image(getClass().getResource("/assets/ProductUploads/" + "pngwing1.com.png").toExternalForm());
            imageInput.setImage(image);
        }
        imageName = p.getImg();
        nomTest = 1;
        descriptionTest = 1;
        BudgetTest = 1;
        StatutTest = 1;
        CategorieTest = 1;
        DureeTest = 1;
        DateTest = 1;
    }


    @FXML
    void addNewProject(MouseEvent event) throws SQLException {
        Project project = new Project();

        if (nameInput.getText()== null) {
            nomTest = 0;
            nameInputErrorHbox.setVisible(true);
        } else {
            if (nomTest == 1) {
                project.setNomProjet(nameInput.getText());
            }
        }

        if (fxDescription.getText()== null) {
            descriptionTest = 0;
            descriptionInputErrorHbox.setVisible(true);
        } else {
            if (descriptionTest == 1) {
                project.setDescriptionProjet(fxDescription.getText());
            }
        }

        if (fxCategorie.getText()== null) {

            CategorieInputErrorHbox.setVisible(true);
        }else {
            if (CategorieTest == 1) {
                project.setCategorie(fxCategorie.getText());
            }
        }
        if (fxBudget.getText().isEmpty()) {
            BudgetTest = 0;
            BudgetInputErrorHbox.setVisible(true);
        } else {
            if (BudgetTest == 1) {
                project.setBudgetProjet(Double.parseDouble(fxBudget.getText()));
            }
        }
        if (fxStatut.getText()== null) {
            StatutTest = 0;
            StatutInputErrorHbox.setVisible(true);
        } else {
            if (StatutTest == 1) {
                project.setStatutProjet(Integer.parseInt(fxStatut.getText()));
            }
        }
        if (fxDuree.getText()== null) {
            DureeTest = 0;
            DureeInputErrorHbox.setVisible(true);
        } else {
            if (DureeTest == 1) {
                project.setDureeProjet(Integer.parseInt(fxDuree.getText()));
            }
        }
        if (imageName == null) {
            photoTest = 0;
            photoInputErrorHbox.setVisible(true);
        } else {
            photoTest = 1;
            project.setImg(imageName);

        }
        if (fxdate.getValue() != null && fxdate.getValue().isEqual(LocalDate.now())) {
            project.setDateCreation(fxdate.getValue().atStartOfDay());

        } else {
            dateInputErrorHbox.setVisible(true);
            TrayNotificationAlert.notif("Date", " Insérer date correcte!!!",
                    NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
        }
        project.setNomProjet(nameInput.getText());
        project.setDescriptionProjet(fxDescription.getText());
        project.setBudgetProjet(Double.parseDouble(fxBudget.getText()));
        project.setStatutProjet(Integer.parseInt(fxStatut.getText()));
        project.setCategorie(fxCategorie.getText());
        project.setDureeProjet(Integer.parseInt(fxDuree.getText()));
        project.setDateCreation(fxdate.getValue() != null ? fxdate.getValue().atStartOfDay() : null);
        project.setImg(imageName);

        if (nomTest == 1 && descriptionTest == 1 && CategorieTest == 1 && DateTest == 1 &&  StatutTest== 1 && BudgetTest== 1
                && DureeTest == 1 && photoTest == 1) {
            IService serviceProject = new ServiceProjet();
            try {
                serviceProject.ajouter(project);
                TrayNotificationAlert.notif("Projet", "Projet ajouté avec succès!!!!",
                        NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/ProjectsList.fxml"));
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            imageName = selectedImageFile.getName();
            Path destination = Paths.get(System.getProperty("user.dir"), "src", "assets", "ProductUploads", imageName);
            photoTest = 1;
            photoInputErrorHbox.setVisible(false);
        }
    }
    public String getImageName() {
        return imageName;
    }

    @FXML
    void priceTypedInput(KeyEvent event) {
        String priceText = ((TextField) event.getSource()).getText();
        if (!priceText.matches("-?\\d+(\\.\\d+)?")) {
            priceInputError.setText("price should be a positive number");
            priceInputErrorHbox.setVisible(true);
            priceTest = 0;
        } else {
            double price = Double.parseDouble(priceText);
            if (price < 0) {
                priceInputError.setText("price cannot be negative");
                priceInputErrorHbox.setVisible(true);
                priceTest = 0;
            } else {
                priceInputErrorHbox.setVisible(false);
                priceTest = 1;
            }
        }

    }

    @FXML
    void pointsTypedInput(KeyEvent event) {
        String pointsText = ((TextField) event.getSource()).getText();
        if (!pointsText.matches("-?\\d+")) {
            pointsInputError.setText("points should be a positive number");
            pointsInputErrorHbox.setVisible(true);
            pointsTest = 0;
        } else {
            int points = Integer.parseInt(pointsText);
            if (points < 0) {
                pointsInputError.setText("points cannot be negative");
                pointsInputErrorHbox.setVisible(true);
                pointsTest = 0;
            } else {
                pointsInputErrorHbox.setVisible(false);
                pointsTest = 1;
            }
        }

    }

    @FXML
    void descriptionTypedInput(KeyEvent event) {
        String descriptionText = ((TextArea) event.getSource()).getText();
        if (!descriptionText.isEmpty()) {
            descriptionInputErrorHbox.setVisible(false);
            descriptionTest = 1;
        }
    }

    @FXML
    void nameTypedInput(KeyEvent event) {
        String nameText = ((TextField) event.getSource()).getText();
        if (!nameText.isEmpty()) {
            nameInputErrorHbox.setVisible(false);
            nomTest = 1;
        }
    }

    @FXML
    void numberTypedInput(KeyEvent event) {
        String numberText = ((TextField) event.getSource()).getText();
        if (!numberText.matches("-?\\d+")) {
            numberInputError.setText("number should be positive");
            numberInputErrorHbox.setVisible(true);
            numberTest = 0;
        } else {
            int number = Integer.parseInt(numberText);
            if (number < 0) {
                numberInputError.setText("number cannot be negative");
                numberInputErrorHbox.setVisible(true);
                numberTest = 0;
            } else {
                numberInputErrorHbox.setVisible(false);
                numberTest = 1;
            }
        }
    }

}

