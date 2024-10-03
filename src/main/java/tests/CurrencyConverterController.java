package tests;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CurrencyConverterController {
    @FXML
    private VBox root;

    public void addCurrency(String currency, double rate, String flagUrl) {
        Label rateLabel = new Label(currency + ": " + rate);
        Image flagImage = new Image(flagUrl);
        ImageView flagImageView = new ImageView(flagImage);
        flagImageView.setFitWidth(32);
        flagImageView.setFitHeight(32);

        VBox currencyBox = new VBox(rateLabel, flagImageView);
        root.getChildren().add(currencyBox);
    }
}