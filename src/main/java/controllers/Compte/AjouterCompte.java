package controllers.Compte;

import Entities.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import services.ServiceCompte;
import services.ShaymaService;
import services.ValidSaisie;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

//import static javafx.scene.control.skin.TableSkinUtils.getSelectionModel;
import static services.ValidSaisie.isValidEmail;

public class AjouterCompte implements Initializable {

    @FXML
    private Text ConfEmailInputError;

    @FXML
    private HBox ConfEmailInputErrorHbox;

    @FXML
    private Button ConfirmBtnCompte;

    @FXML
    private Text DateInputError;

    @FXML
    private HBox DateInputErrorHbox;

    @FXML
    private DatePicker DateNaiss;

    @FXML
    private Text DateNaissnputError;

    @FXML
    private HBox DateNaissnputErrorHbox;

    @FXML
    private DatePicker DatedelCin;

    @FXML
    private TextField Email;

    @FXML
    private Text EmailInputError;

    @FXML
    private HBox EmailInputErrorHbox;

    @FXML
    private TextField MontantDepot;

    @FXML
    private Text MontantInputError;

    @FXML
    private HBox MontantInputErrorHbox;

    @FXML
    private Text NatioInputError;

    @FXML
    private HBox NatioInputErrorHbox;

    @FXML
    private TextField Nationalite;

    @FXML
    private TextField Nom;

    @FXML
    private Text NomInputError;

    @FXML
    private HBox NomInputErrorHbox;

    @FXML
    private TextField NumCin;

    @FXML
    private Text NumCinInputError;

    @FXML
    private HBox NumCinInputErrorHbox;

    @FXML
    private TextField NumTel;

    @FXML
    private ComboBox<String> Sexe;

    @FXML
    private Text NumTelInputError;

    @FXML
    private HBox NumTelInputErrorHbox;

    @FXML
    private TextField Prenom;

    @FXML
    private Text PrenomnputError;

    @FXML
    private HBox PrenomnputErrorHbox;

    @FXML
    private TextField Profession;

    @FXML
    private Text ProffesionInputError;

    @FXML
    private HBox ProffesionInputErrorHbox;


    @FXML
    private Text SexenputError;

    @FXML
    private HBox SexenputErrorHbox;

    @FXML
    private Text StatutnputError;

    @FXML
    private HBox StatutnputErrorHbox;

    @FXML
    private ComboBox<String> TypeCompte;

    @FXML
    private Text TypeInputError;

    @FXML
    private HBox TypeInputErrorHbox;

    @FXML
    private Text TypeCiInputError;
    @FXML
    private ComboBox<String> StatutMarital;

    @FXML
    private HBox TypeCinInputErrorHbox;

    @FXML
    private TextField conformation_email;

    @FXML
    private ComboBox<String> typeCin;
    @FXML
    private VBox content_area;


    @FXML
    void ajouterCompte(MouseEvent event) throws SQLException, IOException {
        ServiceCompte serviceCompte = new ServiceCompte();
        boolean champsVides = false; // Variable pour suivre les champs vides
        boolean champsInvalides = false; // Variable pour suivre les champs invalides

        // Vérification de chaque champ

        // Vérification de l'email
        if (!ValidSaisie.isValidEmail(Email.getText())) {
            // Si l'email est invalide, afficher un message d'erreur et marquer le champ comme vide
            EmailInputErrorHbox.setVisible(true);
            EmailInputError.setText("Adresse email invalide"); // Message d'erreur personnalisé
            champsVides = true;
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            // Sinon, masquer le message d'erreur
            EmailInputErrorHbox.setVisible(false);
        }
        if (!ValidSaisie.isValidEmail(conformation_email.getText())) {
            // Si l'email est invalide, afficher un message d'erreur et marquer le champ comme vide
            ConfEmailInputErrorHbox.setVisible(true);
            ConfEmailInputError.setText("Adresse email invalide"); // Message d'erreur personnalisé
            champsVides = true;
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            // Sinon, masquer le message d'erreur
            ConfEmailInputErrorHbox.setVisible(false);
        }

        // Vérification de la confirmation de l'email (si nécessaire)
        // Vous pouvez ajouter des vérifications supplémentaires ici si nécessaire

        // Vérification du type de CIN
        if (typeCin.getSelectionModel().isEmpty()) {
            TypeCinInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            TypeCinInputErrorHbox.setVisible(false);
        }

        // Vérification du numéro de CIN
        if (!ValidSaisie.isValidCin(NumCin.getText())) {
            NumCinInputErrorHbox.setVisible(true);
            NumCinInputError.setText("Le numéro de cin doit contenir 8 chiffres qui commence par 0 ou 1 ");
            champsVides = true;
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            NumCinInputErrorHbox.setVisible(false);
        }
        LocalDate dateDelivranceCIN = DatedelCin.getValue();
        if (dateDelivranceCIN != null && !ValidSaisie.isValidDate(dateDelivranceCIN)) {
            // Si la date de délivrance est après 2024, afficher un message d'erreur
            DateInputErrorHbox.setVisible(true);
            DateInputError.setText("La date ne doit pas être après 2024"); // Message d'erreur personnalisé
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            DateInputErrorHbox.setVisible(false);
        }


        // Vérification du numéro de téléphone
        if (!ValidSaisie.isValidNumber(NumTel.getText())) {
            NumTelInputErrorHbox.setVisible(true);
            NumTelInputError.setText("Le numéro de téléphone doit commencer par 2 ou 5 ou 9 et contenir 8 chiffres");
            champsVides = true;
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            NumTelInputErrorHbox.setVisible(false);
        }

        // Vérification des autres champs
        if (Nom.getText().isEmpty()) {
            NomInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            NomInputErrorHbox.setVisible(false);
        }

        if (Prenom.getText().isEmpty()) {
            PrenomnputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            PrenomnputErrorHbox.setVisible(false);
        }
        LocalDate dateNaissance = DateNaiss.getValue();
        if (dateNaissance != null && !ValidSaisie.isValidDateN(dateNaissance)) {
            // Si la date de naissance est après 2024, afficher un message d'erreur
            DateNaissnputErrorHbox.setVisible(true);
            DateNaissnputError.setText("La date de naissance n'est pas valide (moins de 18 ans)"); // Message d'erreur personnalisé
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            DateNaissnputErrorHbox.setVisible(false);
        }

        if (TypeCompte.getSelectionModel().isEmpty()) {
            TypeInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            TypeInputErrorHbox.setVisible(false);
        }

        if (MontantDepot.getText().isEmpty()) {
            MontantInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            MontantInputErrorHbox.setVisible(false);
        }

        if (Nationalite.getText().isEmpty()) {
            NatioInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            NatioInputErrorHbox.setVisible(false);
        }

        if (Profession.getText().isEmpty()) {
            ProffesionInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            ProffesionInputErrorHbox.setVisible(false);
        }

        if (StatutMarital.getSelectionModel().isEmpty()) {
            StatutnputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            StatutnputErrorHbox.setVisible(false);
        }

        if (Sexe.getSelectionModel().isEmpty()) {
            SexenputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            SexenputErrorHbox.setVisible(false);
        }

        // Vérification si les champs sont vides ou invalides
        if (champsVides || champsInvalides) {
            return; // Arrêter le traitement si des champs sont vides ou invalides
        }

        // Si tous les champs sont valides, continuer le traitement

        LocalDate selectedDate = LocalDate.now();

        // Récupération des données des champs
        String email = Email.getText();
        String confirmationEmail = conformation_email.getText();
        String typeCIN = typeCin.getSelectionModel().getSelectedItem();
        int numCIN = Integer.parseInt(NumCin.getText());
        int numTel = Integer.parseInt(NumTel.getText());
        String nom = Nom.getText();
        String prenom = Prenom.getText();
        String typeCompte = TypeCompte.getSelectionModel().getSelectedItem();
        double montant = Double.parseDouble(MontantDepot.getText());
        String nationalite = Nationalite.getText();
        String profession = Profession.getText();
        String statutMarital = StatutMarital.getSelectionModel().getSelectedItem();
        String sexe = Sexe.getSelectionModel().getSelectedItem();
        Date sqlDate = java.sql.Date.valueOf(selectedDate);
        String statut="encours";

        // Création du compte
        Compte compte = new Compte(email, confirmationEmail, numCIN, sqlDate, nom, prenom, sexe, sqlDate, profession, typeCompte, montant, statutMarital, nationalite, numTel, typeCIN,statut);

        // Ajout du compte
        ServiceCompte serviceCompte1= new ServiceCompte();
        serviceCompte.ajouter(compte);


        // Chargement de la vue de succès
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Captcha.fxml"));
        Pane demandeChequeListParent = loader.load();



        // Réinitialisation des champs après l'ajout
        resetFields();

        // Remplacement du contenu de content_area par la liste des demandes de chèques
        content_area.getChildren().setAll(demandeChequeListParent);
    }


//    @FXML
//    void ajouterCompte(MouseEvent event) throws SQLException, IOException {

//        ServiceCompte serviceCompte = new ServiceCompte();
//        boolean champsVides = false;
////
//        ValidSaisie validSaisie=new ValidSaisie();
//
//        if (!ValidSaisie.isValidEmail(Email.getText())) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            EmailInputErrorHbox.setVisible(true);
//            EmailInputError.setText("L'adresse e-mail n'est pas valide !");
//        }
//        if (!ValidSaisie.isValidEmail(conformation_email.getText())) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            ConfEmailInputErrorHbox.setVisible(true);
//            ConfEmailInputError.setText("L'adresse e-mail n'est pas valide !");
//        }
//        if (!ValidSaisie.isValidEmpty(String.valueOf(typeCin.getSelectionModel()))) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            TypeInputErrorHbox.setVisible(true);
//        }
//        if (!ValidSaisie.isValidCin(NumCin.getText())) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            NumCinInputErrorHbox.setVisible(true);
//            NumCinInputError.setText("Le numéro de cin doit contenir 8 chiffres !");
//            NumCinInputError.setText("Le numéro de cin doit commencer par 1 ou 0");
//        }
//        if (!ValidSaisie.isValidNumber(NumTel.getText())) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            NumTel.setVisible(true);
//            NumTelInputError.setText("Le numéro de téléphone doit contenir 8 chiffres !");
//            NumTelInputError.setText("Le numéro de téléphone doit commencer par 2 ou 5 ou 9 et contenir 8 chiffres");
//        }
//        if (!ValidSaisie.isValidEmpty(Nom.getText())) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            NomInputError.setVisible(true);
//        }
//        if (!ValidSaisie.isValidEmpty(Prenom.getText())) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            PrenomnputError.setVisible(true);
//        }
//        if (!ValidSaisie.isValidEmpty(String.valueOf(TypeCompte.getSelectionModel()))) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            TypeInputError.setVisible(true);
//        }
//         if (!ValidSaisie.isValidEmpty(String.valueOf(MontantDepot.getText()))) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//             MontantInputError.setVisible(true);
//        }
//         if (!ValidSaisie.isValidEmpty(Nationalite.getText())) {
//        // Afficher un message d'erreur si l'e-mail n'est pas valide
//        NatioInputError.setVisible(true);
//    }
//        if (!ValidSaisie.isValidEmpty(Profession.getText())) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            ProffesionInputError.setVisible(true);
//        }
//        if (!ValidSaisie.isValidEmpty(String.valueOf(StatutMarital.getSelectionModel()))) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            StatutnputError.setVisible(true);
//        }
//        if (!ValidSaisie.isValidEmpty(Sexe.getText())) {
//            // Afficher un message d'erreur si l'e-mail n'est pas valide
//            SexenputError.setVisible(true);
//        }
//        if( ValidSaisie.isValidEmail(Email.getText()) && ValidSaisie.isValidEmail(conformation_email.getText()) && ValidSaisie.isValidEmpty(String.valueOf(typeCin).getSelectionModel()) && ValidSaisie.isValidCin(NumCin.getText())
//                && ValidSaisie.isValidEmpty(NumTel.getText()) &&  ValidSaisie.isValidEmpty(Nom.getText() )
//                && ValidSaisie.isValidEmpty(Prenom.getText()) && ValidSaisie.isValidEmpty(String.valueOf(TypeCompte.getSelectionModel())) && ValidSaisie.isValidEmpty(MontantDepot.getText()) && ValidSaisie.isValidEmpty(Nationalite.getText())
//                && ValidSaisie.isValidEmpty(Profession.getText()) && ValidSaisie.isValidEmpty(String.valueOf(StatutMarital.getSelectionModel())) && ValidSaisie.isValidEmpty(Sexe.getText()))






//        // Vérification de chaque champ
//        if (Email.getText().isEmpty()) {
//            EmailInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            EmailInputErrorHbox.setVisible(false);
//        }
//
//        if (conformation_email.getText().isEmpty()) {
//            ConfEmailInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            ConfEmailInputErrorHbox.setVisible(false);
//        }
//
//        if (typeCin.getSelectionModel().isEmpty()) {
//            TypeCinInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            TypeCinInputErrorHbox.setVisible(false);
//        }
//
//        if (NumCin.getText().isEmpty()) {
//            NumCinInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            NumCinInputErrorHbox.setVisible(false);
//        }
//
//        if (NumTel.getText().isEmpty()) {
//            NumTelInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            NumTelInputErrorHbox.setVisible(false);
//        }
//
//        if (Nom.getText().isEmpty()) {
//            NomInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            NomInputErrorHbox.setVisible(false);
//        }
//
//        if (Prenom.getText().isEmpty()) {
//            PrenomnputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            PrenomnputErrorHbox.setVisible(false);
//        }
//
//        if (TypeCompte.getSelectionModel().isEmpty()) {
//            TypeInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            TypeInputErrorHbox.setVisible(false);
//        }
//
//        if (MontantDepot.getText().isEmpty()) {
//            MontantInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            MontantInputErrorHbox.setVisible(false);
//        }
//
//        if (Nationalite.getText().isEmpty()) {
//            NatioInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            NatioInputErrorHbox.setVisible(false);
//        }
//
//        if (Profession.getText().isEmpty()) {
//            ProffesionInputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            ProffesionInputErrorHbox.setVisible(false);
//        }
//
//        if (StatutMarital.getSelectionModel().isEmpty()) {
//            StatutnputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            StatutnputErrorHbox.setVisible(false);
//        }
//
//        if (Sexe.getText().isEmpty()) {
//            SexenputErrorHbox.setVisible(true);
//            champsVides = true;
//        } else {
//            SexenputErrorHbox.setVisible(false);
//        }
//
//        LocalDate dateDelivranceCIN = DatedelCin.getValue();
//        LocalDate dateNaissance = DateNaiss.getValue();
//
//        // Vérification si les dates sont après 2024
//        if (dateDelivranceCIN != null && dateDelivranceCIN.isAfter(LocalDate.of(2024, 12, 31))) {
//            // Afficher un message d'erreur ou prendre une autre action
//            DateInputErrorHbox.setVisible(true);
//            return; // Sortir de la méthode
//        } else {
//            DateInputErrorHbox.setVisible(false);
//        }
//
//        if (dateNaissance != null && dateNaissance.isAfter(LocalDate.of(2024, 12, 31))) {
//            // Afficher un message d'erreur ou prendre une autre action
//            DateNaissnputErrorHbox.setVisible(true);
//            return; // Sortir de la méthode
//        } else {
//            DateNaissnputErrorHbox.setVisible(false);
//        }
//        // Si au moins un champ est vide, afficher les messages d'erreur
//        if (champsVides) {
//            return;
//        }
//        LocalDate selectedDate = LocalDate.now();
//
//        // Récupération des données des champs
//        String email = Email.getText();
//        String confirmationEmail = conformation_email.getText();
//        String typeCIN = typeCin.getSelectionModel().getSelectedItem();
//        int numCIN = Integer.parseInt(NumCin.getText());
//        String numTel = NumTel.getText();
//        String nom = Nom.getText();
//        String prenom = Prenom.getText();
//        String typeCompte = TypeCompte.getSelectionModel().getSelectedItem();
//        double montant = Double.parseDouble(MontantDepot.getText());
//        String nationalite = Nationalite.getText();
//        String profession = Profession.getText();
//        String statutMarital = StatutMarital.getSelectionModel().getSelectedItem();
//        String sexe = Sexe.getText();
//        Date sqlDate = java.sql.Date.valueOf(selectedDate);
//        int statut=1;
//
//
//        // Création du compte
//        Compte compte = new Compte(email, confirmationEmail, numCIN, sqlDate, nom, prenom, sexe, sqlDate, profession, typeCompte, montant, statutMarital, nationalite, numTel, typeCIN,statut);
//
//        ServiceCompte serviceCompte1 = new ServiceCompte();
//        // Ajout du compte
//        serviceCompte.ajouter(compte);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SuccesAjoutCompte.fxml"));
//        Pane demandeChequeListParent = loader.load();
//
//        // Réinitialiser les champs après l'ajout
//        resetFields();
//
//        // Remplacer le contenu de content_area par le contenu de la liste des demandes de chèques
//        content_area.getChildren().setAll(demandeChequeListParent);
//
//    }

    // Méthode pour réinitialiser les champs après l'ajout
    private void resetFields() {
        Email.clear();
        conformation_email.clear();
        typeCin.getSelectionModel().clearSelection();
        NumCin.clear();
        NumTel.clear();
        Nom.clear();
        Prenom.clear();
        TypeCompte.getSelectionModel().clearSelection();
        MontantDepot.clear();
        Nationalite.clear();
        Profession.clear();
        DateNaiss.setValue(null);
        DatedelCin.setValue(null);
        StatutMarital.getSelectionModel().clearSelection();
        Sexe.getSelectionModel().clearSelection();


        // Masquer tous les messages d'erreur
        EmailInputErrorHbox.setVisible(false);
        ConfEmailInputErrorHbox.setVisible(false);
        TypeCinInputErrorHbox.setVisible(false);
        NumCinInputErrorHbox.setVisible(false);
        NumTelInputErrorHbox.setVisible(false);
        NomInputErrorHbox.setVisible(false);
        PrenomnputErrorHbox.setVisible(false);
        TypeInputErrorHbox.setVisible(false);
        MontantInputErrorHbox.setVisible(false);
        NatioInputErrorHbox.setVisible(false);
        ProffesionInputErrorHbox.setVisible(false);
        StatutnputErrorHbox.setVisible(false);
        SexenputErrorHbox.setVisible(false);
        DateNaissnputErrorHbox.setVisible(false);
        DateInputErrorHbox.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailInputErrorHbox.setVisible(false);
        ConfEmailInputErrorHbox.setVisible(false);
        TypeCinInputErrorHbox.setVisible(false);
        NumCinInputErrorHbox.setVisible(false);
        NumTelInputErrorHbox.setVisible(false);
        NomInputErrorHbox.setVisible(false);
        PrenomnputErrorHbox.setVisible(false);
        DateInputErrorHbox.setVisible(false);
        DateNaissnputErrorHbox.setVisible(false);
        TypeInputErrorHbox.setVisible(false);
        NatioInputErrorHbox.setVisible(false);
        ProffesionInputErrorHbox.setVisible(false);
        StatutnputErrorHbox.setVisible(false);
        SexenputErrorHbox.setVisible(false);
        MontantInputErrorHbox.setVisible(false);







        ObservableList<String> TypeComptes = FXCollections.observableArrayList(
                "Epargne",
                "Courant"
        );
        ObservableList<String> TypeCin = FXCollections.observableArrayList(
                "Cin",
                "Passeport"
        );
        ObservableList<String> StatutMaritals = FXCollections.observableArrayList(
                "Célibataire",
                "Marié",
                "Divorcé"
        );
        ObservableList<String> Sexes =FXCollections.observableArrayList(
                "Femme",
                "Homme"
        );


        TypeCompte.setItems(TypeComptes);

        typeCin.setItems(TypeCin);

        StatutMarital.setItems(StatutMaritals);


        Sexe.setItems(Sexes);




    }

}

//        ServiceCompte serviceCompte = new ServiceCompte();
//        if (Email.getText().isEmpty()) {
//            EmailInputErrorHbox.setVisible(true);
//            if (conformation_email.getText().isEmpty()) {
//                ConfEmailInputErrorHbox.setVisible(true);
//                if (typeCin.getSelectionModel().isEmpty()) {
//                    TypeCinInputErrorHbox.setVisible(true);
//                    if (NumCin.getText().isEmpty()) {
//                        NumCinInputErrorHbox.setVisible(true);
//                        if (NumTel.getText().isEmpty()) {
//                            NumTelInputErrorHbox.setVisible(true);
//                            if (Nom.getText().isEmpty()) {
//                                NomInputErrorHbox.setVisible(true);
//                                if (Prenom.getText().isEmpty()) {
//                                    PrenomnputErrorHbox.setVisible(true);
//                                    if (TypeCompte.getSelectionModel().isEmpty()) {
//                                        TypeInputErrorHbox.setVisible(true);
//                                        if (MontantDepot.getText().isEmpty()) {
//                                            MontantInputErrorHbox.setVisible(true);
//                                            if (Nationalite.getText().isEmpty()) {
//                                                NatioInputErrorHbox.setVisible(true);
//                                                if (Profession.getText().isEmpty()) {
//                                                    ProffesionInputErrorHbox.setVisible(true);
//                                                    if (StatutMarital.getText().isEmpty()) {
//                                                        StatutnputErrorHbox.setVisible(true);
//                                                        if (Sexe.getText().isEmpty()) {
//                                                            SexenputErrorHbox.setVisible(true);
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (typeCin.getSelectionModel().isEmpty()) {
//                        TypeCinInputErrorHbox.setVisible(true);
//                        if (NumCin.getText().isEmpty()) {
//                            NumCinInputErrorHbox.setVisible(true);
//                            if (NumTel.getText().isEmpty()) {
//                                NumTelInputErrorHbox.setVisible(true);
//                                if (Nom.getText().isEmpty()) {
//                                    NomInputErrorHbox.setVisible(true);
//                                    if (Prenom.getText().isEmpty()) {
//                                        PrenomnputErrorHbox.setVisible(true);
//                                        if (TypeCompte.getSelectionModel().isEmpty()) {
//                                            TypeInputErrorHbox.setVisible(true);
//                                            if (MontantDepot.getText().isEmpty()) {
//                                                MontantInputErrorHbox.setVisible(true);
//                                                if (Nationalite.getText().isEmpty()) {
//                                                    NatioInputErrorHbox.setVisible(true);
//                                                    if (Profession.getText().isEmpty()) {
//                                                        ProffesionInputErrorHbox.setVisible(true);
//                                                        if (StatutMarital.getText().isEmpty()) {
//                                                            StatutnputErrorHbox.setVisible(true);
//                                                            if (Sexe.getText().isEmpty()) {
//                                                                SexenputErrorHbox.setVisible(true);
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    } else {
//                if (NumCin.getText().isEmpty()) {
//                    NumCinInputErrorHbox.setVisible(true);
//                    if (NumTel.getText().isEmpty()) {
//                        NumTelInputErrorHbox.setVisible(true);
//                        if (Nom.getText().isEmpty()) {
//                            NomInputErrorHbox.setVisible(true);
//                            if (Prenom.getText().isEmpty()) {
//                                PrenomnputErrorHbox.setVisible(true);
//                                if (TypeCompte.getSelectionModel().isEmpty()) {
//                                    TypeInputErrorHbox.setVisible(true);
//                                    if (MontantDepot.getText().isEmpty()) {
//                                        MontantInputErrorHbox.setVisible(true);
//                                        if (Nationalite.getText().isEmpty()) {
//                                            NatioInputErrorHbox.setVisible(true);
//                                            if (Profession.getText().isEmpty()) {
//                                                ProffesionInputErrorHbox.setVisible(true);
//                                                if (StatutMarital.getText().isEmpty()) {
//                                                    StatutnputErrorHbox.setVisible(true);
//                                                }
//