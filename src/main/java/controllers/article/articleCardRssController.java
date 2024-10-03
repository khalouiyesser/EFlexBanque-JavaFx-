//package controllers;
//
//import controllers.rss.RSSReader;
//import javafx.fxml.FXML;
//import javafx.scene.image.ImageView;
//import javafx.scene.text.Text;
//
//import java.awt.*;
//import java.io.IOException;
//import java.util.List;
//
//public class articleCardRssController {
//
//    @FXML
//    private Text titreArtFront;
//    @FXML
//    private ImageView imgArtFront;
//    @FXML
//    private Text contenuArtFront;
//    @FXML
//    private Text datepubArt;
//    @FXML
//    private Text auteurArtFront;
//    // Méthode appelée lors de l'initialisation du contrôleur
//    @FXML
//    public void initialize() {
//        // Charger les articles RSS
//        List<RSSReader> articles = null;
//        try {
//            articles = RSSReader.loadArticlesFromURL("https://rss.app/feeds/v1.1/tOgyrCZKdMWLeE5M.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Afficher le premier article dans l'interface utilisateur
//        if (articles != null && !articles.isEmpty()) {
//            RSSReader firstArticle = articles.get(1);
//            titreArtFront.setText(firstArticle.getTitle());
//            contenuArtFront.setText(firstArticle.getContent());
//            datepubArt.setText(firstArticle.getDatePublished());
////            auteurArtFront.setText(firstArticle.getAuthor());
//            String url = firstArticle.getImageUrl();
//            javafx.scene.image.Image image = new javafx.scene.image.Image(url);
//
//
//            imgArtFront.setImage(image);
//        }
//    }
//}
package controllers;

import controllers.rss.RSSReader;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class articleCardRssController {

    @FXML
    private Text titreArtFront;
    @FXML
    private ImageView imgArtFront;
    @FXML
    private Text contenuArtFront;
    @FXML
    private Text datepubArt;
    @FXML
    private Text auteurArtFront;

    // Méthode appelée lors de l'initialisation du contrôleur
    @FXML
    public void initialize() throws IOException {
        // Charger les articles RSS
        List<RSSReader> articles = null;
        try {
            articles = RSSReader.loadArticlesFromURL("https://rss.app/feeds/v1.1/tXZL88RGnCmxrXxR.json");
            System.out.println(articles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Afficher le premier article dans l'interface utilisateur
        if (articles != null && !articles.isEmpty()) {
//            RSSReader firstArticle = articles.get(1);
//            titreArtFront.setText(firstArticle.getTitle());
//            contenuArtFront.setText(truncateContent(firstArticle.getContent(), 4)); // Affiche uniquement les 4 premières lignes
//            datepubArt.setText(formatDate(firstArticle.getDatePublished())); // Formate la date
////            auteurArtFront.setText(firstArticle.getAuthor());
//            String url = firstArticle.getImageUrl();
//            javafx.scene.image.Image image = new javafx.scene.image.Image(url);
//            imgArtFront.setImage(image);
            RSSReader secondArticle = articles.get(2);
            titreArtFront.setText(secondArticle.getTitle());
            contenuArtFront.setText(secondArticle.getContent());
            datepubArt.setText(formatDate(secondArticle.getDatePublished()));
//            String url = secondArticle.getImageUrl();
            javafx.scene.image.Image image = new javafx.scene.image.Image(secondArticle.getImageUrl().openStream());
            if (image == null){
                String imagePathArt = "C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory\\0cf5ce9f-7746-4cd0-aaf6-27580468a1d7.png" ;
                imgArtFront.setImage(new Image(imagePathArt));

            }else {
                imgArtFront.setImage(image);
            }

        }
    }



    // Méthode pour formater la date
    private String formatDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Date invalide";
        }
    }
}
