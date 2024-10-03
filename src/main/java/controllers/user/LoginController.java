package controllers.user;
import Entities.User;
import controllers.*;
import controllers.Cheque.AjouterChequeCard;
import controllers.ChequeItemsController;
import controllers.Credit.AjouterCreditCard;
import controllers.Credit.GMailer;
import controllers.Virement.AjouterVirementCard;
import controllers.Virement.VirementCard;
import controllers.article.AjouterArticleController;
import controllers.commentaireArticle.CommentArticleController;
import controllers.reclamation.AjouterReclamationController;
import controllers.reclamation.ListeRecClientController;
import controllers.reponse.AjouterReponseAdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import services.ServiceCredit;
import services.ServiceUser;



public class LoginController implements Initializable {

    public ImageView toHOME;
    @FXML
    private TextField emailField;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private AnchorPane left;

    @FXML
    private Button loginBTN;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signUpLink;


    public void ToSignUp(MouseEvent mouseEvent) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SignUp.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Si nécessaire, récupérer le contrôleur associé à l'interface SignUp.fxml
            // SignUpController signUpController = loader.getController();

            // Set the scene
            Stage stage = new Stage(); // Crée une nouvelle fenêtre pour l'interface d'inscription
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    public void toForgotPassword(ActionEvent actionEvent)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ForgotPassword.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void logIn(ActionEvent actionEvent) {
        String email = emailField.getText();
        String password = passField.getText();
        UserControleSaisie userControleSaisie = new UserControleSaisie();

        // Vérification des champs email et mot de passe avec la fonction loginInputValidator
        if (!userControleSaisie.loginInputValidator(email, password)) {
            return; // Sortir de la méthode si la validation échoue
        }

        ServiceUser userService = new ServiceUser();
        User user;

        try {
            user = userService.getOneUser(email);
            System.out.println(user);
            if (user.getId() == -999) {
                AlertUtil.showAlert("Login", "Invalid credentials.", Alert.AlertType.INFORMATION);
            } else {
                if (BCrypt.checkpw(password, user.getPassword().replace("$2y$", "$2a$"))) {
                    if (user.getIs_blocked() == 1) {
                        AlertUtil.showAlert("Login", "Your account is blocked.", Alert.AlertType.ERROR);
                    } else {
                        AlertUtil.showAlert("Login", "Logged in successfully.", Alert.AlertType.INFORMATION);
                        UserSession.getInstance().setEmail(user.getEmail());
                        System.out.println("to the DASHBOARD");
                        if (user.getRoles().equals("[\"ROLE_CLIENT\"]")
                                || user.getRoles().equals("[\"ROLE_EMPLOYEE\"]")) {
                            userService.modifierConnect(user);
                            AjouterCreditCard ajouterCreditCard = new AjouterCreditCard();
                            ajouterCreditCard.user = user;
                            ServiceCredit  serviceCredit = new ServiceCredit();
                            serviceCredit.user = user;
                            DashboardClientOff dashboardClientOff = new DashboardClientOff();
                            dashboardClientOff.user = user;
                            ListeRecClientController serviceRec = new ListeRecClientController();
                            dashboardClientcreditrdv dashboardClientcreditrdv = new dashboardClientcreditrdv();
                            dashboardClientcreditrdv.user = user;
                            serviceRec.user = user;
                            AjouterChequeCard ajouterChequeCard = new AjouterChequeCard();
                            ajouterChequeCard.user= user;

//                            ChequeItemsController chequeItemsController = new ChequeItemsController();
                            ChequeItemsController chequeItemsController = new ChequeItemsController();
                            chequeItemsController.user = user;
                            AjouterVirementCard ajouterVirementCard = new AjouterVirementCard();
                            ajouterVirementCard.user = user;
                            VirementCard virementCard = new VirementCard();
                            virementCard.user = user;
                            AjouterArticleController ajouterArticleController = new AjouterArticleController();
                            ajouterArticleController.user = user;
                            AjouterReclamationController ajouterReclamationController = new AjouterReclamationController();
                            ajouterReclamationController.user = user;
                            SideNavBarUserController sideNavBarUserController = new SideNavBarUserController();
                            sideNavBarUserController.user = user;
//                            GMailer gMailer = new GMailer();
//                            gMailer.TEST_EMAIL = user.getEmail();
                            CommentArticleController commentArticleController = new CommentArticleController();
                            commentArticleController.user = user;

                            System.out.println("to the USERDASHBOARD");
                            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBarUser.fxml"));
                           // System.out.println("hahaha 3ersna");
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else if (user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
                            userService.modifierConnect(user);
                            AjouterReponseAdminController ajouterReponseAdminController = new AjouterReponseAdminController();
                            ajouterReponseAdminController.user = user;
                            dashboardClientcreditrdv dashboardClientcreditrdv = new dashboardClientcreditrdv();
                            dashboardClientcreditrdv.user = user;
                            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBar.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                } else {
                    AlertUtil.showAlert("Login", "Invalid credentials.", Alert.AlertType.INFORMATION);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Ne créez pas une nouvelle instance UserSession ici
        // UserSession userSession = new UserSession();
        // userSession.email = emailField.getText();
        // System.out.println(userSession.email);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSession userSession = new UserSession();
        if (userSession.email != null){
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBarUser.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            ServiceUser serviceUser  = new ServiceUser();
            try {
                User user = serviceUser.getOneUser(userSession.email);
                SideNavBarController sideNavBarController = new SideNavBarController();
                sideNavBarController.user = user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public class AlertUtil {

        public static void showAlert(String title, String message, AlertType alertType) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
    @FXML
    void toHOME(MouseEvent event) throws  IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/front.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}




