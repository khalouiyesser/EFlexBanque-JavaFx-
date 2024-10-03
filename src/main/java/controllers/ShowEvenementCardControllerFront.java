package controllers;

import Entities.Commentaire;
import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.IService;
import services.ServiceCommentaire;
import services.ServiceEvenement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class ShowEvenementCardControllerFront implements Initializable {

    @FXML
    private GridPane evenementsListContainerfront;

    @FXML
    private GridPane CommentsListContainer;

    @FXML
    private Pane content_area;
    @FXML
    private HBox categoriesModel;

    @FXML
    private TextField productSearchInput;
    @FXML
    private GridPane categoriesListContainer;

    @FXML
    private TextField evenementsearchInput;

    @FXML
    private Button stockBtn;

    @FXML
    private ComboBox<String> categoryInput;

    @FXML
    private ImageView qrCodeImg;

    @FXML
    private HBox qrCodeImgModel;

    @FXML
    private HBox offreModel;

    @FXML
    private TextField reductionInput;

    @FXML
    private TextField couponInput;

    @FXML
    private Text reductionInputError;

    @FXML
    private Text couponInputError;

    @FXML
    private Text backToReductionBtn;

    @FXML
    private Text addNewCouponBtn;

    @FXML
    private HBox reductionInputErrorHbox;

    @FXML
    private HBox couponInputErrorHbox;

    @FXML
    private VBox couponForm;

    @FXML
    private VBox reductionForm;

    @FXML
    private HBox submitCouponBtn;

    @FXML
    private HBox submitOfferBtn;

    @FXML
    private ComboBox<String> couponCombobox;

    private int categId = -1;

    private int sortValue = -1; // 1: sort by stock *** 0: filter by category *** 2: filter by category and sort
    // by stock
    private int submitOfferTest = 0;
    private int submitCouponTest = 0;

    private static int categoryModelShow = 0;
    private String selectedOption = null;

    @FXML
    private TextField nameInput;
    public static int getCategoryModelShow() {
        return categoryModelShow;
    }

    @FXML
    private void open_eventList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/ShowEvenementCardFront.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    private void switchToEvenementsList(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/ShowEvenementCardFront.fxml"));
        Parent root = loader.load();
        Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);
    }
    private void showNotification(String title, String message, NotificationType type) {
        TrayNotificationAlert.notif(title, message, type, AnimationType.POPUP, Duration.millis(2500));
    }
    @FXML
    void addNewComments(MouseEvent event) throws SQLException {
        Commentaire commentaire = new Commentaire();


        commentaire.setNomuser(nameInput.getText());
        //commentaire.setContenu(ContenuInput.getText());
        commentaire.setContenu("bitch");

        IService serviceCommentaire = new ServiceCommentaire();
        try {
            serviceCommentaire.ajouter(commentaire);
            showNotification("Commentaire ", "Commentaire  ajouté avec succès.", NotificationType.SUCCESS);
            switchToEvenementsList(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        qrCodeImgModel.setVisible(false);
//        offreModel.setVisible(false);
//        reductionInputErrorHbox.setVisible(false);
//        couponForm.setVisible(false);
//        couponInputErrorHbox.setVisible(false);
//        backToReductionBtn.setVisible(false);

        this.setEvenementGridPaneList();
     //   this.setCommentGridPaneList();

    }
    private void setEvenementGridPaneList() {
        IService evenementService = new ServiceEvenement();

        List<Evenement> evenements = null;
        if (Evenement.getSearchValue() == null) {
            if (sortValue == 1) {
                evenements = evenementService.sortEvent(1, -1);
            } else {
                try {
                    evenements = evenementService.afficher();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            evenements = ServiceEvenement.searchEvenement(Evenement.getSearchValue());
        }
        int column = 0;
        int row = 1;
        try {
            for (Evenement evenement : evenements) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/project/OneEvenementListCardfront.fxml"));
                HBox oneEvenementCard = fxmlLoader.load();
                OneEvenementListCardControllerfront evenementCardController = fxmlLoader.getController();
                evenementCardController.setEvenementData(evenement);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                evenementsListContainerfront.add(oneEvenementCard, column++, row);
                GridPane.setMargin(oneEvenementCard, new Insets(0, 10, 25, 10));
                oneEvenementCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void setCommentGridPaneList(){
//        IService commentaireService = new ServiceCommentaire();
//        List<Commentaire> commentaires = null;
//        try {
//            commentaires = commentaireService.afficher();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        int column = 0;
//        int row = 1;
//        try {
//            for (Commentaire commentaire : commentaires) {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/OneCommentListCard.fxml"));
//                HBox oneCommentCard = fxmlLoader.load();
//                OneCommentListCardController commentCardController = fxmlLoader.getController();
//                commentCardController.setCommentData(commentaire);
//                if (column == 1) {
//                    column = 0;
//                    ++row;
//                }
//                CommentsListContainer.add(oneCommentCard, column++, row);
//                GridPane.setMargin(oneCommentCard, new Insets(0, 10, 25, 10));
//                oneCommentCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    void searchEvenement() throws IOException, SQLException {

    }

    @FXML
    private void open_addEvenement(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/AddEvenement.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    void open_CategoriesModel(MouseEvent event) {
        categoriesModel.setVisible(true);
    }

    @FXML
    void close_CategoriesModel(MouseEvent event) {
        categoriesModel.setVisible(false);
        EvenementsListControllerfront.setCategoryModelShow(0);
    }

    @FXML
    void close_QrCodeModel(MouseEvent event) {
        qrCodeImgModel.setVisible(false);
    }

    @FXML
    void close_offerModel(MouseEvent event) {
        offreModel.setVisible(false);
    }

}
