
package controllers.commentaireArticle;

import Entities.User;
import Entities.actualites.Article;
import Entities.actualites.CommentaireHadhemi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import objects.Account;
import objects.Reactions;
import services.ServiceArticle;
import services.ServiceCommentaireHadhemi;

import java.io.IOException;
import java.util.ArrayList;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class CommentArticleController implements Initializable {

    @FXML
    private HBox articlehadhemi;

    @FXML
    private ImageView audience;

    @FXML
    private Text categArt;

    @FXML
    private VBox CommentListContainer;


    @FXML
    private Label commenter;

    @FXML
    private HBox commenterHbox;

    @FXML
    private Text contenuart;

    @FXML
    private Label datepub;

    @FXML
    private ImageView imgAngry;

    @FXML
    private ImageView imgCare;

    @FXML
    private ImageView imgHaha;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgPost;

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgReaction;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgVerified;

    @FXML
    private ImageView imgWow;

    @FXML
    private HBox likeContainer;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbComments1;

    @FXML
    private Label nbComments11;

    @FXML
    private Label nbReactions;

    @FXML
    private Label reactionName;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private VBox reductionForm;

    @FXML
    private HBox reductionInputErrorHbox;

    @FXML
    private HBox reductionInputErrorHbox2;

    @FXML
    private Text titreArt;

    @FXML
    private Label username;
    Article article;
    private long startTime = 0;
    private Reactions currentReaction;

    @FXML
    private TextField inputTextCommenta;

    public static User user;

//    @FXML
//    public void onLikeContainerPressed(MouseEvent me){
//        startTime = System.currentTimeMillis();
//    }

    public int idAuto ;
    List<CommentaireHadhemi> list = new ArrayList<>();
    @FXML
    public void onLikeContainerPressed(MouseEvent me) {
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased(MouseEvent me) throws SQLException {
        ServiceArticle serviceArticle = new ServiceArticle();

        if (System.currentTimeMillis() - startTime > 500) {
            reactionsContainer.setVisible(true);
        } else {
            if (reactionsContainer.isVisible()) {
                reactionsContainer.setVisible(false);
            }
            if (currentReaction == Reactions.NON) {
                // Appel de la méthode pour incrémenter les likes
                serviceArticle.incrementLikes(article);
            } else {
                // Appel de la méthode pour décrémenter les likes
                serviceArticle.decrementLikes(article);
            }
        }
    }




    // Liste de mots inappropriés
    private static List<String> badWords = new ArrayList<>();
    static {
        badWords.add("fuck");
        badWords.add("bhim");
        badWords.add("fuck u");
        badWords.add("stupid");
        badWords.add("mamstek");

        // Ajoutez d'autres mots inappropriés à cette liste
    }

    public String filterComment(String comment) {
        // Remplacer les mots inappropriés par des pasteurises
        for (String badWord : badWords) {
            // Utilisation d'une expression régulière pour remplacer les occurrences du mot entier,
            // indépendamment de sa casse et entouré de délimitations de mots
            comment = comment.replaceAll("\\b" + badWord + "\\b", "*".repeat(badWord.length()));
        }
        return comment;
    }

//    @FXML
//    public void onLikeContainerMouseReleased(MouseEvent me){
//        if(System.currentTimeMillis() - startTime > 500){
//            reactionsContainer.setVisible(true);
//        }else {
//            if(reactionsContainer.isVisible()){
//                reactionsContainer.setVisible(false);
//            }
//            if(currentReaction == Reactions.NON){
//                setReaction(Reactions.LIKE);
//            }else{
//                setReaction(Reactions.NON);
//            }
//        }
//    }


    @FXML
    public void onReactionImgPressed(MouseEvent me) throws SQLException {
        switch (((ImageView) me.getSource()).getId()){
            case "imgLove":
                setReaction(Reactions.LOVE);
                break;
            case "imgCare":
                setReaction(Reactions.CARE);
                break;
            case "imgHaha":
                setReaction(Reactions.HAHA);
                break;
            case "imgWow":
                setReaction(Reactions.WOW);
                break;
            case "imgSad":
                setReaction(Reactions.SAD);
                break;
            case "imgAngry":
                setReaction(Reactions.ANGRY);
                break;
            default:
                setReaction(Reactions.LIKE);
                break;
        }
        ServiceCommentaireHadhemi serviceCommentaireHadhemi = new ServiceCommentaireHadhemi();
        CommentaireHadhemi commentaireHadhemi = serviceCommentaireHadhemi.getCommentaireArtById(article.getId());
        serviceCommentaireHadhemi.modifierLike(commentaireHadhemi.getLike()+1,article.getId());
        System.out.println("work");
        reactionsContainer.setVisible(false);
    }

    public void setReaction(Reactions reaction) throws SQLException {
        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        imgReaction.setImage(image);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));

        if(currentReaction == Reactions.NON){
            article.setTotalReactions(article.getTotalReactions() + 1);
        }

        currentReaction = reaction;

        if(currentReaction == Reactions.NON){
            article.setTotalReactions(article.getTotalReactions() - 1);
        }

        nbReactions.setText(String.valueOf(article.getTotalReactions()));

    }

    public void setData(Article article) throws SQLException {
        this.article = article;
        ServiceArticle serviceArticle = new ServiceArticle();
        ServiceCommentaireHadhemi serviceCommentaireHadhemi = new ServiceCommentaireHadhemi();
        CommentaireHadhemi comm = serviceCommentaireHadhemi.getCommentaireArtById(article.getId());
        int a = serviceCommentaireHadhemi.countCommentsByArticleId(article.getId());
        nbComments1.setText(String.valueOf(a));
        nbComments.setText(String.valueOf(comm.getLike()));
        if (article != null) {
            titreArt.setText(article.getTitre_art());
            categArt.setText(article.getCategorie_art());
            imgPost.setImage(new Image("file:///C:/Users/Yesser/PI/InnovatixYesser/public/uploads_directory/" + article.getImage_art()));


            if (article.getDate_pub_art() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = article.getDate_pub_art().format(formatter);
                datepub.setText(formattedDate);
            } else {
                datepub.setText("Date de publication non disponible");
            }
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                String formattedDate = article.getDate_pub_art().format(formatter);
//                datepub.setText(formattedDate);
            contenuart.setText(article.getContenu_art());
            idAuto = article.getId();
            System.out.println("setArticle is called avec id  "+idAuto);

        }


        //////////////////////Ajb/////////////////

        ServiceCommentaireHadhemi sch = new ServiceCommentaireHadhemi() ;
        List<CommentaireHadhemi> list = new ArrayList<>();
        try {
            System.out.println("000111 id of this article is :: "+idAuto);

            //list = sch.afficher();

            System.out.println("000222 id of this article is :: "+idAuto);
            list = sch.afficherById(idAuto);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (CommentaireHadhemi commentaireHadhemi : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/commentaire/commentaireItemFront.fxml"));
                Parent offreItem = loader.load();
                CommentaireItemFrontController ComItem = loader.getController();
                System.out.println("contenue du comm page1 "+ComItem);
                ComItem.initData(commentaireHadhemi);
                CommentListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private Article getArticle(){
        Article post = new Article();
        Account account = new Account();
        account.setName("Mahmoud Hamwi");
        account.setProfileImg("/imagesAct/user.png");
        account.setVerified(true);
        post.setTotalReactions(0);
        System.out.println("getArticle is called ");


        return post;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        article=this.article;
        commenterHbox.setVisible(false);

    }
    @FXML
    public void toggleCommenterHbox() {
        commenterHbox.setVisible(!commenterHbox.isVisible());
    }


    public void commenter(MouseEvent mouseEvent) throws SQLException {



    }

    public void commenterArt(MouseEvent mouseEvent) throws SQLException {
        int id = article.getId();
        String nom = user.getName();
        String adresse = user.getEmail();
        ServiceCommentaireHadhemi sch = new ServiceCommentaireHadhemi();
        String commentairein = inputTextCommenta.getText();
        String image ="admin";
        LocalDateTime dateTime = LocalDateTime.now();
//        CommentaireHadhemi commentaire = new CommentaireHadhemi(commentairein,dateTime,nom,id,image);
//        sch.ajouter(commentaire);
        // Filtrer le commentaire

        String commentaireFiltre = filterComment(commentairein);
        ServiceCommentaireHadhemi serviceCommentaireHadhemi = new ServiceCommentaireHadhemi();
        CommentaireHadhemi comm = serviceCommentaireHadhemi.getCommentaireArtById(article.getId());

        // Créer un objet CommentaireHadhemi avec le commentaire filtré
        CommentaireHadhemi commentaire = new CommentaireHadhemi(commentaireFiltre, dateTime, nom, id, image,comm.getLike());

        // Ajouter le commentaire à la base de données
        sch.ajouter(commentaire);
        setData(article);

        commenterHbox.setVisible(false);

    }



}