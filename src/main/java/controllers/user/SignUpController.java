package controllers.user;

import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceUser;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.util.Duration;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class SignUpController implements Initializable{


    @FXML
    private TextField EmailField;
    @FXML
    private BorderPane CaptchaPane;

    @FXML
    private TextField fullnameField;
    @FXML
    private TextField code;

    @FXML
    private AnchorPane left;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField phoneField1;

    @FXML
    private PasswordField repassField;

    @FXML
    private Button signUpBTN;
    @FXML
    private ImageView imageView;

    @FXML
    private ImageView cap;
    @FXML
    private Button submit;
    @FXML
    private Button reset;
    private int Rib;

    private String imagePath;
    File selectedImageFile;
    @FXML
    void uploadImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        File selectedFile = fileChooser.showOpenDialog(new Stage());


        if (selectedFile != null) {
             imagePath = selectedFile.getAbsolutePath(); // Stockez le chemin de l'image sélectionnée dans la variable de classe
            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", imagePath);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            // Chargez l'image dans l'ImageView si nécessaire
            imageView.setImage(new Image(selectedFile.toURI().toString()));
        }
    }
    public void signUp(ActionEvent actionEvent) {
        String name = fullnameField.getText();
        String email = EmailField.getText();
        String tel = phoneField1.getText();
        String password = passField.getText();
        String repassword = repassField.getText();
        String image = imagePath;
        String roles = "ROLE_CLIENT";
        Rib = 2554455;

        // Créer un objet User avec les informations saisies
        User newUser = new User(name, email, tel, password, image, roles,BigInteger.valueOf(Rib));
        //User usd = new User(name,email,tel,password,fileName,roles);
        UserControleSaisie userControleSaisie = new UserControleSaisie();
        ServiceUser userService = new ServiceUser();

        // Appel à la fonction signUpValidator pour valider les saisies
        if (!userControleSaisie.signUpValidator(newUser, repassword, userService)) {
            return; // Sortir de la méthode si la validation échoue
        }

        // Ajouter l'utilisateur à la base de données ou effectuer toute autre action nécessaire
        addUserToDatabase(name,email,tel,password,image,roles, BigInteger.valueOf(Rib));
       // userService.ajouter(usd);
        clearInputFields();
    }

    // Méthode pour afficher une alerte d'erreur
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    private void addUserToDatabase(String name, String email, String tel, String password, String photo , String roles, BigInteger Rib) {
        // Créer un nouvel utilisateur avec les valeurs saisies
        User user = new User(name,email,tel,password, photo,roles,Rib);

        // Utiliser le service utilisateur pour inscrire l'utilisateur
        ServiceUser userService = new ServiceUser();
        userService.signUp(user);// Appel de la méthode signUp pour ajouter l'utilisateur à la base de données
    }

    // Méthode pour effacer les champs de saisie
    private void clearInputFields() {
        fullnameField.clear();
        EmailField.clear();
        phoneField1.clear();
        passField.clear();
        repassField.clear();
        imageView.setImage(null);
        imagePath = null;



    }
    @FXML
    void toLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public Captcha setCaptcha() {
        Captcha captchaV = new Captcha.Builder(250, 200)
                .addText()
                .addBackground(new GradiatedBackgroundProducer()) // Ajout d'un fond gradient
                .addNoise()
                .addBorder()
                .build();

        System.out.println(captchaV.getImage());
        Image image = SwingFXUtils.toFXImage(captchaV.getImage(), null);

        cap.setImage(image);

        return captchaV;
    }


    Captcha captcha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        captcha =  setCaptcha();

    }


    public void submit(ActionEvent actionEvent)   throws IOException {
        if (captcha.isCorrect(code.getText())) {

            String tilte = "Captcha";
            String message = "Vous avez saisi le code avec succés!";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

            //     try {
          //  Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/SuccesAjoutCompte.fxml"));
          //  CaptchaPane.getChildren().removeAll();
         //   CaptchaPane.getChildren().setAll(fxml);
//            stage.show();
         //   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         //   stage.close();

//        } catch (IOException ex) {
//            Logger.getLogger(Agent_mainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        else {

            String tilte = "Captcha";
            String message = "Vous avez saisi un faux code !";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));

            captcha =  setCaptcha();
            code.setText("");
        }

    }

    public void reseting(ActionEvent actionEvent) {
        captcha =  setCaptcha();
        code.setText("");
    }
}


