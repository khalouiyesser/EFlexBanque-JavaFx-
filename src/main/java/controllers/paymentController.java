package controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class paymentController implements Initializable {

    @FXML
    private GridPane commandsListContainer;

    @FXML
    private Pane content_area;

    @FXML
    private HBox checkoutModel;

    @FXML
    private HBox paymentModel;

    @FXML
    private TextField addressInput;

    @FXML
    private Text addressInputError;

    @FXML
    private HBox addressInputErrorHbox;

    @FXML
    private TextField cityInput;

    @FXML
    private Text cityInputError;

    @FXML
    private HBox cityInputErrorHbox;

    @FXML
    private TextField emailInput;

    @FXML
    private Text emailInputError;

    @FXML
    private HBox emailInputErrorHbox;

    @FXML
    private TextField fullnameInput;

    @FXML
    private Text fullnameInputError;

    @FXML
    private HBox fullnameInputErrorHbox;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private Text phoneInputError;

    @FXML
    private HBox phoneInputErrorHbox;

    @FXML
    private TextField zipcodeInput;

    @FXML
    private Text zipcodeInputError;

    @FXML
    private HBox zipcodeInputErrorHbox;

    @FXML
    private Text address;

    @FXML
    private Text city;

    @FXML
    private Text fullname;

    @FXML
    private Text phone;

    @FXML
    private HBox addCheckoutBtn;

    @FXML
    private HBox updateCheckoutBtn;

    @FXML
    private Text totalPoints;

    @FXML
    private Text totalPrice;

    @FXML
    private HBox selectPaymentMethod;

    @FXML
    private Text paymentMethod;

    @FXML
    private HBox paymentQuestion;

    @FXML
    private HBox paymentValidate;

    @FXML
    private Text paymentModelTitle;

    @FXML
    private Text totalPointsValidate;

    @FXML
    private Text priceSymbole;

    @FXML
    private HBox stripeInputs;

    @FXML
    private TextField cardNumberInput;

    @FXML
    private Text cardNumberInputError;

    @FXML
    private HBox cardNumberInputErrorHbox;

    @FXML
    private TextField mmInput;

    @FXML
    private Text mmInputError;

    @FXML
    private HBox mmInputErrorHbox;

    @FXML
    private TextField yyInput;

    @FXML
    private Text yyInputError;

    @FXML
    private HBox yyInputErrorHbox;

    @FXML
    private TextField cvcInput;

    @FXML
    private Text cvcInputError;

    @FXML
    private HBox cvcInputErrorHbox;

    @FXML
    private TextField zipInput;

    @FXML
    private TextField promoInput;

    @FXML
    private Text zipInputError;

    @FXML
    private HBox zipInputErrorHbox;

    @FXML
    private HBox backTo_selectPayment_btn;

    @FXML
    private HBox couponModel;

    @FXML
    private Text oneFreeProduct;

    private int achatId = -1;

    private int totalPts = 0;
    private float totalPrx = 0;
    private int command_Id;
    private int user_Id;
    private int point;
    private int cardNumberTest = -1;
    private int mmTest = -1;
    private int yyTest = -1;
    private int cvcTest = -1;
    private int zipTest = -1;

    private int fullnameTest = -1;
    private int cityTest = -1;
    private int phoneTest = -1;
    private int addressTest = -1;
    private int zipcodeTest = -1;
    private int emailTest = -1;

    private int ApplyCouponVerified = -1;
    private float totalPrxWithCoupon = -1;

    int couponCode = -1;
    String email = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkoutModel.setVisible(false);
        paymentModel.setVisible(false);
        fullnameInputErrorHbox.setVisible(false);
        emailInputErrorHbox.setVisible(false);
        cityInputErrorHbox.setVisible(false);
        phoneInputErrorHbox.setVisible(false);
        addressInputErrorHbox.setVisible(false);
        zipcodeInputErrorHbox.setVisible(false);
        updateCheckoutBtn.setVisible(false);
        paymentValidate.setVisible(false);
        cardNumberInputErrorHbox.setVisible(false);
        mmInputErrorHbox.setVisible(false);
        yyInputErrorHbox.setVisible(false);
        cvcInputErrorHbox.setVisible(false);
        zipInputErrorHbox.setVisible(false);
        stripeInputs.setVisible(false);
        backTo_selectPayment_btn.setVisible(false);
        couponModel.setVisible(false);

        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/project/UsercommandsHeader.fxml"));
            HBox commandinfoCard = fxmlLoader1.load();

            commandsListContainer.add(commandinfoCard, 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void on_Stripe_methodPayment_click(MouseEvent event) throws IOException {
        paymentQuestion.setVisible(false);
        selectPaymentMethod.setVisible(false);
        stripeInputs.setVisible(true);
        backTo_selectPayment_btn.setVisible(true);
    }
    @FXML
    void open_checkoutModel(MouseEvent event) throws SQLException {

        paymentModel.setVisible(true);

    }


    @FXML
    void close_commandPaymentModel(MouseEvent event) {
        paymentModel.setVisible(false);
    }



    private void StripeFunction() {
        String STRIPE_SECRET_KEY = "sk_test_51MgYOOFXYK38vFYwOPGPKxftWYpStBWSuhx2ltC4jYfuyWkTxrXbpuVAGx6VrBBehZQtX5uJFFA7os4WQTVCFORz00pGTPG1FH";
        // Set up the Stripe API key
        Stripe.apiKey = STRIPE_SECRET_KEY;
        // Get the credit card details from the text fields
        String cardNumber = cardNumberInput.getText();// "4242 4242 4242 4242"
        int expMonth = Integer.parseInt(mmInput.getText());// 03
        int expYear = Integer.parseInt(yyInput.getText());// 45
        String cvc = cvcInput.getText();// "678"
        String zip = zipInput.getText();// "12345"

        // Create a map of the credit card details
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);
        cardParams.put("address_zip", zip); // Add the zip code to the cardParams map



        // Create a Stripe token for the credit card details
        Token token = null;
        try {
            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("card", cardParams);
            token = Token.create(tokenParams);
            System.out.println("Stripe token ID: " + token.getId());
        } catch (StripeException e) {
            e.printStackTrace();
            // handle the error appropriately
        }

        // Create a charge for the payment
        Charge charge = null;
        try {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", 1000);
            chargeParams.put("currency", "usd");
            chargeParams.put("source", token.getId()); // Use the token ID as the source for the charge
            charge = Charge.create(chargeParams);
        } catch (StripeException e) {
            System.out.println("Error creating charge: " + e.getMessage());
            e.printStackTrace();
        }

        if (charge == null || charge.getFailureMessage() != null) {
            System.out.println("Charge failed: " + charge.getFailureMessage());
        }
    }

    @FXML
    void close_couponModel(MouseEvent event) {
        couponModel.setVisible(false);

    }

}
