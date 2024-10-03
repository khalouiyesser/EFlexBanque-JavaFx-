package controllers.offreDeStage;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RatingController {

    public ImageView reviewsBox_star5;
    public ImageView reviewsBox_star4;
    public ImageView reviewsBox_star3;
    public VBox content_area;
    public ImageView reviewsBox_star1;
    public ImageView reviewsBox_star2;
    @FXML
    private Text star1;

    @FXML
    private Text star2;

    @FXML
    private Text star3;

    @FXML
    private Text star4;

    @FXML
    private Text star5;
    public static int rate;

    public void initialize() {
        // Initialiser les étoiles
        Text[] stars = {star1, star2, star3, star4, star5};
//        for (Text star : stars) {
//            star.setFont(Font.font(30));
//            star.setFill(Color.WHITE);
//        }
        star1.setFont(Font.font(30));
        star1.setFill(Color.WHITE);
        star2.setFont(Font.font(30));
        star2.setFill(Color.WHITE);
        star3.setFont(Font.font(30));
        star3.setFill(Color.WHITE);
        star4.setFont(Font.font(30));
        star4.setFill(Color.WHITE);
        star5.setFont(Font.font(30));
        star5.setFill(Color.WHITE);
        star1.setOnMouseClicked(mouseEvent -> {
            star1.setText("\u2605"); // étoile remplie
            star1.setFill(Color.YELLOW);
            star2.setFont(Font.font(30));
            star2.setFill(Color.WHITE);
            star3.setFont(Font.font(30));
            star3.setFill(Color.WHITE);
            star4.setFont(Font.font(30));
            star4.setFill(Color.WHITE);
            star5.setFont(Font.font(30));
            star5.setFill(Color.WHITE);
            rate = 1;
        });
        star2.setOnMouseClicked(mouseEvent -> {
            star1.setText("\u2605"); // étoile remplie
            star1.setFill(Color.YELLOW);
            star2.setText("\u2605");
            star2.setFill(Color.YELLOW);
            star3.setFont(Font.font(30));
            star3.setFill(Color.WHITE);
            star4.setFont(Font.font(30));
            star4.setFill(Color.WHITE);
            star5.setFont(Font.font(30));
            star5.setFill(Color.WHITE);
            rate = 2;
        });
        star3.setOnMouseClicked(mouseEvent -> {
            star1.setText("\u2605"); // étoile remplie
            star1.setFill(Color.YELLOW);
            star2.setText("\u2605");
            star2.setFill(Color.YELLOW);
            star3.setText("\u2605");
            star3.setFill(Color.YELLOW);
            star4.setFont(Font.font(30));
            star4.setFill(Color.WHITE);
            star5.setFont(Font.font(30));
            star5.setFill(Color.WHITE);
            rate = 3;

        });
        star4.setOnMouseClicked(mouseEvent -> {
            star1.setText("\u2605"); // étoile remplie
            star1.setFill(Color.YELLOW);
            star2.setText("\u2605");
            star2.setFill(Color.YELLOW);
            star3.setText("\u2605");
            star3.setFill(Color.YELLOW);
            star4.setText("\u2605");
            star4.setFill(Color.YELLOW);
            star5.setFont(Font.font(30));
            star5.setFill(Color.WHITE);
            rate = 4;

        });
        star5.setOnMouseClicked(mouseEvent -> {
            star1.setText("\u2605"); // étoile remplie
            star1.setFill(Color.YELLOW);
            star2.setText("\u2605");
            star2.setFill(Color.YELLOW);
            star3.setText("\u2605");
            star3.setFill(Color.YELLOW);
            star4.setText("\u2605");
            star4.setFill(Color.YELLOW);
            star5.setText("\u2605");
            star5.setFill(Color.YELLOW);
            rate = 5;
        });


    }

    @FXML
    private void handleRating(javafx.scene.input.MouseEvent event) {
////        Text clickedStar = (Text) event.getSource();
//        int rating = 0;
////        switch (clickedStar.getText()) {
////            case "\u2605": // étoile remplie
////                rating = 1;
////                break;
////            case "\u2606": // étoile vide
////                rating = 0;
////                break;
////        }
//
//        // Mettre en jaune les étoiles sélectionnées
//        Text[] stars = {star1, star2, star3, star4, star5};
//
//        for (int i = 0; i < stars.length; i++) {
//            if (i < rating) {
//                stars[i].setText("\u2605"); // étoile remplie
//                stars[i].setFill(Color.YELLOW);
//            } else {
//                stars[i].setText("\u2606"); // étoile vide
//                stars[i].setFill(Color.BLACK);
//            }
//        }
//
//
    }
}
