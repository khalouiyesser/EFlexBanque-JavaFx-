package controllers;

import Entities.Evenement;
import Entities.Project;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import services.IService;
import services.ServiceProjet;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectListControllerUser implements Initializable {
    @FXML
    private HBox updateProjectModel;

    @FXML
    private HBox ShowProjectModel;
    @FXML
    private VBox updateProjectModelContent;

    @FXML
    private VBox ShowProjectModelContent;

    @FXML
    private HBox addProjectModel;

    @FXML
    private VBox addProjectModelContent;

    @FXML
    private VBox projectListContainer;

    @FXML
    private Text projectListTitle;


    @FXML
    private Pane projectPane;

    @FXML
    private HBox projectTableHead;
    private static int updateProjectModelShow = 0;
    private static int ShowProjectModelShow = 0;
    private static int addProjectModelShow = 0;
    private static int projetIdToUpdate = 0;
    private static int projetIdToShow = 0;
    private static int filter = 0;

    @FXML
    private Pane content_area;

    public static int getupdateProjectModelShow() {
        return updateProjectModelShow;
    }
    public static int getShowProjectModelShow() {
        return ShowProjectModelShow;
    }
    public static void setupdateProjectModelShow(int updateProjectModelShow) {
        ProjectListControllerUser.updateProjectModelShow = updateProjectModelShow;
    }
    public static void setShowProjectModelShow(int ShowProjectModelShow) {
        ProjectListControllerUser.ShowProjectModelShow = ShowProjectModelShow;
    }


    @FXML
    void searchProject(KeyEvent event) throws IOException {
        Evenement.setSearchValue(((TextField) event.getSource()).getText());
        GridPane projectListContainer = (GridPane) content_area.lookup("#projectListContainer");
        projectListContainer.getChildren().clear();
        this.setProjectGridPaneList();
    }
    public static int getaddProjectModelShow() {
        return updateProjectModelShow;
    }
    @FXML
    private void open_ProjectList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/ProjectsListUser.fxml"));

    }

    public static void setaddProjectModelShow(int addProjectModelShow) {
        ProjectListControllerUser.addProjectModelShow = addProjectModelShow;
    }

    public static void setprojectEmailToUpdate(int projetIdToUpdate) {
        ProjectListControllerUser.projetIdToUpdate = projetIdToUpdate;
    }
    public static void setprojectEmailToShow(int projetIdToShow) {
        ProjectListControllerUser.projetIdToShow = projetIdToShow;
    }
//    public void RetourBackC(MouseEvent mouseEvent) {
//
//        try {
//            // Charger le fichier FXML de listArticleAdmin
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/dashboardClient.fxml"));
//            Pane listArticleAdminPane = loader.load();
//
//            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
//            content_area.getChildren().setAll(listArticleAdminPane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceProjet projectService = new ServiceProjet();
        Project project;

        if (ProjectListController.getShowProjectModelShow() == 0) {
            ShowProjectModel.setVisible(false);
        } else if (ProjectListController.getShowProjectModelShow() == 1) {
            ShowProjectModel.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/project/ShowProjectCard.fxml"));
            VBox ShowProjectform;
            try {
                ShowProjectform = fxmlLoader1.load();
                ShowProjectCardController ShowUserCardController = fxmlLoader1.getController();
                ShowProjectCardController.setFxmlToLoad("ProjectsList.fxml");
                project = projectService.getOneProject(projetIdToUpdate);
                ShowUserCardController.setProjectUpdateData(project);
                ShowProjectModelContent.getChildren().add(ShowProjectform);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


         if (ProjectListControllerUser.getaddProjectModelShow() == 0) {
            addProjectModel.setVisible(false);
        } else if (ProjectListControllerUser.getaddProjectModelShow() == 1) {
            addProjectModel.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/project/updateProjectCard.fxml"));
            VBox updateProjectform;
            try {
                updateProjectform = fxmlLoader1.load();
                UpdateProjectCardController addUserCardController = fxmlLoader1.getController();
                UpdateProjectCardController.setFxmlToLoad("ProjectsListUser.fxml");
                project = projectService.getOneProject(projetIdToUpdate);

                addUserCardController.setProjectUpdateData(project);
                addProjectModelContent.getChildren().add(updateProjectform);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            ArrayList<Project> projectList;
            projectList = (ArrayList<Project>) projectService.getAllProject();
            for (int i = 0; i < projectList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/FXML/project/ProjectItemUser.fxml"));
                HBox projectItem = fxmlLoader.load();
                ProjectItemControllerUser projectItemControllerUser = fxmlLoader.getController();
                projectItemControllerUser.setprojectData(projectList.get(i));
                projectListContainer.getChildren().add(projectItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setProjectGridPaneList() {
        ServiceProjet projectService = new ServiceProjet();
        List<Project> projects = null;
        if (Evenement.getSearchValue() == null) {
            try {
                projects = projectService.afficher();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }} else {
            projects = ServiceProjet.searchProject(Project.getSearchValue());
        }

        try {
        ArrayList<Project> projectList;
        projectList = (ArrayList<Project>) projectService.getAllProject();
        for (int i = 0; i < projectList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/project/ProjectItemUser.fxml"));
            HBox projectItem = fxmlLoader.load();
            ProjectItemController projectItemController = fxmlLoader.getController();
            projectItemController.setprojectData(projectList.get(i));
            projectListContainer.getChildren().add(projectItem);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    void close_updateProjectModel(MouseEvent event) {
        updateProjectModel.setVisible(false);
        updateProjectModelShow = 0;
    }
    @FXML
    void close_ShowProjectModel(MouseEvent event) {
        ShowProjectModel.setVisible(false);
        ShowProjectModelShow = 0;
    }

    @FXML
    private void open_addProject(MouseEvent event) throws IOException {
        Evenement.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/AddProject.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
    private void showNotification(String title, String message, NotificationType type) {
        TrayNotificationAlert.notif(title, message, type, AnimationType.POPUP, Duration.millis(2500));
    }
    @FXML
    void pdf(MouseEvent event) throws SQLException {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {

            IService serviceProjet = new ServiceProjet();
            List<Project> projetList = serviceProjet.afficher();

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                Image image = Image.getInstance(System.getProperty("user.dir") + "/src/assets/img/logo.png");
                image.setAbsolutePosition(5, document.getPageSize().getHeight() - 120);
                image.scaleAbsolute(100, 100);
                document.add(image);


                Font fontDate = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                BaseColor color = new BaseColor(50, 187, 111); // Rouge: 50, Vert: 187, Bleu: 111
                fontDate.setColor(color);


                Paragraph tunis = new Paragraph("Tunis", fontDate);
                tunis.setIndentationLeft(455); //
                tunis.setSpacingBefore(-30);
                 document.add(tunis);

                LocalDate today = LocalDate.now();

                Paragraph date = new Paragraph(today.toString(), fontDate);

                date.setIndentationLeft(437);

                date.setSpacingBefore(1);

                document.add(date);

                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor titleColor = new BaseColor(67, 136, 43); //
                font.setColor(titleColor);

                Paragraph title = new Paragraph("Liste des Projets", font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50);
                title.setSpacingAfter(20);
                document.add(title);

                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                table.setSpacingBefore(30f);
                table.setSpacingAfter(30f);

                Font hrFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                BaseColor hrColor = new BaseColor(50, 89, 74);
                hrFont.setColor(hrColor);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Nom de projet", hrFont));
                BaseColor bgColor = new BaseColor(222, 254, 230);
                cell1.setBackgroundColor(bgColor);
                cell1.setBorderColor(titleColor);
                cell1.setPaddingTop(20);
                cell1.setPaddingBottom(20);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell2 = new PdfPCell(new Paragraph("Déscription", hrFont));
                cell2.setBackgroundColor(bgColor);
                cell2.setBorderColor(titleColor);
                cell2.setPaddingTop(20);
                cell2.setPaddingBottom(20);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell3 = new PdfPCell(new Paragraph("Catégorie", hrFont));
                cell3.setBackgroundColor(bgColor);
                cell3.setBorderColor(titleColor);
                cell3.setPaddingTop(20);
                cell3.setPaddingBottom(20);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell4 = new PdfPCell(new Paragraph("Budget", hrFont));
                cell4.setBackgroundColor(bgColor);
                cell4.setBorderColor(titleColor);
                cell4.setPaddingTop(20);
                cell4.setPaddingBottom(20);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell5 = new PdfPCell(new Paragraph("Date de creation", hrFont));
                cell5.setBackgroundColor(bgColor);
                cell5.setBorderColor(titleColor);
                cell5.setPaddingTop(20);
                cell5.setPaddingBottom(20);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell6 = new PdfPCell(new Paragraph("Durée par mois", hrFont));
                cell6.setBackgroundColor(bgColor);
                cell6.setBorderColor(titleColor);
                cell6.setPaddingTop(20);
                cell6.setPaddingBottom(20);
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);

                Font hdFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
                BaseColor hdColor = new BaseColor(50, 89, 74); //
                hrFont.setColor(hdColor);
                 for (Project projet : projetList) {
                    PdfPCell cellR1 = new PdfPCell(new Paragraph(String.valueOf(projet.getNomProjet()), hdFont));
                    cellR1.setBorderColor(titleColor);
                    cellR1.setPaddingTop(10);
                    cellR1.setPaddingBottom(10);
                    cellR1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR1);

                    PdfPCell cellR2 = new PdfPCell(new Paragraph(projet.getDescriptionProjet(), hdFont));
                    cellR2.setBorderColor(titleColor);
                    cellR2.setPaddingTop(10);
                    cellR2.setPaddingBottom(10);
                    cellR2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR2);

                    PdfPCell cellR3 = new PdfPCell(new Paragraph(projet.getCategorie(), hdFont));
                    cellR3.setBorderColor(titleColor);
                    cellR3.setPaddingTop(10);
                    cellR3.setPaddingBottom(10);
                    cellR3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR3);

                    PdfPCell cellR4 = new PdfPCell(new Paragraph(String.valueOf(projet.getBudgetProjet()), hdFont));
                    cellR4.setBorderColor(titleColor);
                    cellR4.setPaddingTop(10);
                    cellR4.setPaddingBottom(10);
                    cellR4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR4);
                    PdfPCell cellR5 = new PdfPCell(
                            new Paragraph(String.valueOf(projet.getDateCreation()), hdFont));
                    cellR5.setBorderColor(titleColor);
                    cellR5.setPaddingTop(10);
                    cellR5.setPaddingBottom(10);
                    cellR5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR5);
                    PdfPCell cellR6 = new PdfPCell(
                            new Paragraph(String.valueOf(projet.getDureeProjet()), hdFont));
                    cellR6.setBorderColor(titleColor);
                    cellR6.setPaddingTop(10);
                    cellR6.setPaddingBottom(10);
                    cellR6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR6);
                }
                table.setSpacingBefore(20);
                document.add(table);
                document.close();
                System.out.println("Le fichier PDF a été généré avec succès.");
                TrayNotificationAlert.notif("Projet", "Le fichier PDF a été généré avec succès.",
                        NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
