package controllers;

import Entities.Evenement;
import Entities.PDFExporter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.IService;
import services.ServiceEvenement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EvenementsListController implements Initializable {

    @FXML
    private GridPane evenementsListContainer;

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
    private int sortValue = -1;
    private int submitOfferTest = 0;
    private int submitCouponTest = 0;
    private static int categoryModelShow = 0;
    private String selectedOption = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        qrCodeImgModel.setVisible(false);
        offreModel.setVisible(false);
        reductionInputErrorHbox.setVisible(false);
        couponForm.setVisible(false);
        couponInputErrorHbox.setVisible(false);
        backToReductionBtn.setVisible(false);
        if (EvenementsListController.getCategoryModelShow() == 0) {
            categoriesModel.setVisible(false);
        } else if (EvenementsListController.getCategoryModelShow() == 1) {
            categoriesModel.setVisible(true);
        }
        this.setEvenementGridPaneList();
    }

    public static int getCategoryModelShow() {
        return categoryModelShow;
    }

    public static void setCategoryModelShow(int categoryModelShow) {
        EvenementsListController.categoryModelShow = categoryModelShow;
    }

    @FXML
    void sort__Byevent(MouseEvent event) {
        Evenement.setSearchValue(null);
        if (!stockBtn.getStyleClass().contains("sort__stockBtn-active")) {
            stockBtn.getStyleClass().add("sort__stockBtn-active");
            sortValue = 1;
        } else {
            stockBtn.getStyleClass().remove("sort__stockBtn-active");
            sortValue = -1;
        }
        GridPane productsListContainer = (GridPane) content_area.lookup("#evenementsListContainer");
        productsListContainer.getChildren().clear();
        this.setEvenementGridPaneList();
    }

    @FXML
    void searchEvenement(KeyEvent event) throws IOException {
        Evenement.setSearchValue(((TextField) event.getSource()).getText());
        categId = -1;
        GridPane evenementsListContainer = (GridPane) content_area.lookup("#evenementsListContainer");
        evenementsListContainer.getChildren().clear();
        this.setEvenementGridPaneList();
    }


    @FXML
    void excelBtn(MouseEvent event) throws IOException, SQLException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Evenement");
        IService serviceEvenement = new ServiceEvenement();
        List<Evenement> evenementList = serviceEvenement.afficher();
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nom");
        headerRow.createCell(1).setCellValue("Déscription");
        headerRow.createCell(2).setCellValue("Lieu");
        headerRow.createCell(3).setCellValue("Prix");
        int rowNum = 1;
        for (Evenement evenement : evenementList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(evenement.getNom());
            row.createCell(1).setCellValue(evenement.getDescription());
            row.createCell(2).setCellValue(evenement.getLieu());
            row.createCell(3).setCellValue(evenement.getPrix());
        }
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Excel", "*.xlsx"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            try (FileOutputStream outputStream = new FileOutputStream(selectedFile)) {
                workbook.write(outputStream);
            }
        }
    }

    @FXML
    private void open_addEvenement(MouseEvent event) throws IOException {
        Evenement.actionTest = 0;
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
        EvenementsListController.setCategoryModelShow(0);
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/project/OneEvenementListCard.fxml"));
                HBox oneEvenementCard = fxmlLoader.load();
                OneEvenementListCardController evenementCardController = fxmlLoader.getController();
                evenementCardController.setEvenementData(evenement);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                evenementsListContainer.add(oneEvenementCard, column++, row);
                GridPane.setMargin(oneEvenementCard, new Insets(0, 10, 25, 10));
                oneEvenementCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exportToPDF(ActionEvent event) {
        PDFExporter pdfExporter = new PDFExporter();
        pdfExporter.generatePDF(evenementsListContainer); // Assuming your HBox is named 'hbox'
    }

    @FXML
    void close_QrCodeModel(MouseEvent event) {
        qrCodeImgModel.setVisible(false);
    }

    @FXML
    void close_offerModel(MouseEvent event) {
        offreModel.setVisible(false);
    }

    @FXML
    void reductionTypedInput(KeyEvent event) {
        String reductionText = reductionInput.getText();
        if (!reductionText.matches("-?\\d+(\\.\\d+)?")) {
            reductionInputError.setText("reduction should be a positive number");
            reductionInputErrorHbox.setVisible(true);
            submitOfferTest = 0;
        } else {
            double reduction = Double.parseDouble(reductionText);
            if (reduction < 0) {
                reductionInputError.setText("reduction cannot be negative");
                reductionInputErrorHbox.setVisible(true);
                submitOfferTest = 0;
            } else {
                reductionInputErrorHbox.setVisible(false);
                submitOfferTest = 1;
            }
        }

        double reduction = Double.parseDouble(reductionText);
        if (reduction > 100) {
            reductionInputError.setText("reduction should be less then 100");
            reductionInputErrorHbox.setVisible(true);
            submitOfferTest = 0;
        }
    }


    @FXML
    void submit_offer(MouseEvent event) {
        Evenement evenement = new Evenement();
        evenement.setId(Evenement.getIdEvenement());
        evenement.setRemise(Integer.parseInt(reductionInput.getText()));
        IService serviceEvenement = new ServiceEvenement();
        if (submitOfferTest == 1) {
            serviceEvenement.AddEvenenemtOffer(evenement);

            TrayNotificationAlert.notif("Evenement", "Offre ajoutée avec succès.",
                    NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));

            offreModel.setVisible(false);
            reductionInput.clear();
            GridPane evenementsListContainer = (GridPane) content_area.lookup("#evenementsListContainer");
            evenementsListContainer.getChildren().clear();
            this.setEvenementGridPaneList();
        }
    }

    @FXML
    void addNewCoupon(MouseEvent event) throws IOException {
        reductionForm.setVisible(false);
        submitOfferBtn.setVisible(false);
        couponForm.setVisible(true);
        submitCouponBtn.setVisible(true);
        addNewCouponBtn.setVisible(false);
        backToReductionBtn.setVisible(true);
    }

    @FXML
    void couponTypedInput(KeyEvent event) {
        String couponText = couponInput.getText();
        if (!couponText.matches("-?\\d+(\\.\\d+)?")) {
            couponInputError.setText("coupon should be a positive number");
            couponInputErrorHbox.setVisible(true);
            submitCouponTest = 0;
        } else {
            double coupon = Double.parseDouble(couponText);
            if (coupon < 0) {
                couponInputError.setText("coupon cannot be negative");
                couponInputErrorHbox.setVisible(true);
                submitCouponTest = 0;
            } else {
                couponInputErrorHbox.setVisible(false);
                submitCouponTest = 1;
            }
        }

    }

    @FXML
    void back_toReduction(MouseEvent event) {
        reductionForm.setVisible(true);
        submitOfferBtn.setVisible(true);
        couponForm.setVisible(false);
        submitCouponBtn.setVisible(false);
        addNewCouponBtn.setVisible(true);
        backToReductionBtn.setVisible(false);

    }

}
