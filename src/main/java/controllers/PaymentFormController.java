package controllers;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class PaymentFormController {

    private static final String STRIPE_SECRET_KEY = "sk_test_51Lk7ECHRO68yDgiHJ5rHXuuuasOa3PSHS7hnXB38ZN6xYpZ08L9rq5ibV1bp3VIMZWOhtMWaThSyfbAuprSdaqGu00y07LQRmm";

    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField expMonthField;
    @FXML
    private TextField expYearField;
    @FXML
    private TextField cvcField;
    @FXML
    private TextField amountField;
    @FXML
    private void processPayment() {
        Stripe.apiKey = STRIPE_SECRET_KEY;
        String cardNumber = cardNumberField.getText();
        int expMonth = Integer.parseInt(expMonthField.getText());
        int expYear = Integer.parseInt(expYearField.getText());
        String cvc = cvcField.getText();
        int amount = Integer.parseInt(amountField.getText());

        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);

        Map<String, Object> params = new HashMap<>();
        params.put("card", cardParams);

        try {
            Token token = Token.create(params);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount * 100); // amount in cents
            chargeParams.put("currency", "usd");
            chargeParams.put("source", token.getId());
            chargeParams.put("description", "Example Charge");

            Charge charge = Charge.create(chargeParams);
            showInfoAlert("Payment successful: " + charge.getId());
        } catch (StripeException e) {
            showErrorAlert("Payment failed: " + e.getMessage());
        }
    }

    private void showErrorAlert(String message) {
        System.out.println(message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Payment Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
