package controllers;

import Entities.Commentaire;
import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.IService;
import services.ServiceCommentaire;
import services.ServiceEvenement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class ShowEvenementCardController implements Initializable {

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
    private TextField reductionInput;

    @FXML
    private TextField couponInput;

    @FXML
    private Text reductionInputError;

    @FXML
    private Text couponInputError;


    @FXML
    private Text addNewCouponBtn;


    @FXML
    private HBox submitCouponBtn;

    @FXML
    private HBox submitOfferBtn;

    @FXML
    private ComboBox<String> couponCombobox;

    private int categId = -1;

    private int sortValue = -1;
    // by stock
    private int submitOfferTest = 0;


    private String selectedOption = null;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.setEvenementGridPaneList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.setCommentGridPaneList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void setEvenementGridPaneList() throws SQLException {
        IService evenementService = new ServiceEvenement();
        Evenement e1 = new Evenement();

        e1 = evenementService.getOneEvenement(Evenement.getIdEvenement());

        Evenement evenement = (Evenement) evenementService.afficher1(Evenement.getIdEvenement());

        int column = 0;
        int row = 1;
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setCommentGridPaneList() throws SQLException {
        IService evenementService = new ServiceEvenement();
        Evenement evenement = (Evenement) evenementService.afficher1(Evenement.getIdEvenement());
        System.out.println("comment code" + evenement.getId());
        IService commentaireService = new ServiceCommentaire();
        List<Commentaire> commentaires = commentaireService.show(evenement.getId());
        int column = 0;
        int row = 1;
        try {
            for (Commentaire commentaire : commentaires) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/project/OneCommentListCard.fxml"));
                HBox oneCommentCard = fxmlLoader.load();
                OneCommentListCardController commentCardController = fxmlLoader.getController();
                commentCardController.setCommentData(commentaire);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                CommentsListContainer.add(oneCommentCard, column++, row);
                GridPane.setMargin(oneCommentCard, new Insets(0, 10, 25, 10));
                oneCommentCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
