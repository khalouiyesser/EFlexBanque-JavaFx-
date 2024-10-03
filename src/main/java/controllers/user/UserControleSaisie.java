package controllers.user;

import java.sql.SQLException;
import java.util.regex.Pattern;

import Entities.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import services.ServiceUser;

import org.mindrot.jbcrypt.BCrypt;

public class UserControleSaisie {

    public static boolean signUpValidator(User user, String repassword, ServiceUser userService) {
        if (user.getName().isEmpty() || user.getEmail().isEmpty() || user.getTel().isEmpty() || user.getPhoto().isEmpty()
                || user.getPassword().isEmpty()) {
            showAlert(AlertType.WARNING, "Sign Up Error", "Please fill out all required fields.");
            return false;
        }

        Pattern fullnamePattern = Pattern.compile(".*\\d.*");
        if (fullnamePattern.matcher(user.getName()).matches()) {
            showAlert(AlertType.WARNING, "Sign Up Error", "Your name cannot contain a number.");
            return false;
        }

        if (user.getName().length() < 3) {
            showAlert(AlertType.WARNING, "Sign Up Error", "Not a valid name.");
            return false;
        }

        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@gmail\\.com");

        if (!emailPattern.matcher(user.getEmail()).matches()) {
            showAlert(AlertType.WARNING, "Sign Up Error", "The email " + user.getEmail() + " is not a valid email.");
            return false;
        }

       /* Pattern telPattern = Pattern.compile("[0-9]+");
        if (!(telPattern.matcher(user.getTel()).matches() && user.getTel().length() >= 8)) {
            showAlert(AlertType.WARNING, "Sign Up Error", "Not a valid phone number.");
            return false;
        }

        if (!user.getRoles().equals("[\"ROLE_CLIENT\"]")) {
            showAlert(AlertType.WARNING, "Sign Up Error", "Hello little hacker 🐱‍💻👩‍💻");
            return false;
        }*/

        if (user.getPassword().length() < 8) {
            showAlert(AlertType.WARNING, "Sign Up Error", "Your password must be at least 8 characters long.");
            return false;
        }

        if (!user.getPassword().equals(repassword)) {
            showAlert(AlertType.WARNING, "Sign Up Error", "Passwords do NOT match.");
            return false;
        }

        try {
            if (userService.getOneUser(user.getEmail()).getId() != -999) {
                showAlert(AlertType.WARNING, "Sign Up Error", "Email already in use.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    // Les autres méthodes de validation peuvent être ajustées de la même manière
    // sans utiliser TrayNotificationAlert.

    private static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static boolean forgetPasswordValidator(String email, User user) {
        if (email.equals("")) {
            showAlert("Forgot password", "Please fill out all required fields.");
            return false;
        }

        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        if (!emailPattern.matcher(email).matches()) {
            showAlert("sign Up", "The email " + email + " is not a valid email.");
            return false;
        }

        if (user.getId() == -999) {
            showAlert("Forgot password", "Email not associated with EflexBank account");
            return false;
        }

        return true;
    }


    public boolean loginInputValidator(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Login", "Please fill out all required fields.");
            return false;
        }

        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        if (!emailPattern.matcher(email).matches()) {
            showAlert("Login", "The email " + email + " is not valid.");
            return false;
        }

        return true;
    }
    public static boolean updateAccountValidator(User user) {
        if (user.getName().isEmpty() || user.getEmail().isEmpty() || user.getTel().isEmpty() || user.getPhoto().isEmpty()
        || user.getAdresse().isEmpty() || user.getCin().isEmpty()) {
            showAlert("Update Account", "Please fill out all required fields.");
            return false;
        }

        Pattern fullnamePattern = Pattern.compile(".*\\d.*");
        if (fullnamePattern.matcher(user.getName()).matches()) {
            showAlert("Update Account", "Your name cannot contain a number.");
            return false;
        }

        if (user.getName().length() < 3) {
            showAlert("Update Account", "Not a valid fullname.");
            return false;
        }



        return true;
    }
    public static boolean changePasswordValidator(User user, String oldPassword, String password, String rePassword) {
        if (password.equals("") || rePassword.equals("")) {
            showAlert("Change Password", "Please fill out all required fields.");
            return false;
        }

        if (!BCrypt.checkpw(oldPassword, user.getPassword().replace("$2y$", "$2a$"))) {
            showAlert("Change Password", "Please verify your old password.");
            return false;
        }

        if (password.length() < 8) {
            showAlert("Change Password", "Your password must be at least 8 characters long.");
            return false;
        }

        if (!password.equals(rePassword)) {
            showAlert("Change Password", "Passwords do NOT match.");
            return false;
        }

        return true;
    }

    // Méthode pour afficher une boîte de dialogue d'alerte
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

