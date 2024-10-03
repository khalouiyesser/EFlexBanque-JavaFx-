package controllers;

import Entities.Cheque;
import Entities.User;
import Entities.Virement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;
import services.ServiceVirement;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.sun.javafx.logging.PulseLogger.addMessage;

public class DashboardClientOff implements Initializable {

    public TextField messageArea;
    public VBox conversationPane;
    @FXML
    private Pane content_area;

    @FXML
    private PieChart stat1;

    @FXML
    private PieChart stat2;
    public static User user;

    private int compteurVEcoresponsabilite = 0; // Déclaration et initialisation du compteur
    //public static User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            statistiqueVirement(); // Appel de la méthode pour afficher les statistiques des virements
            statistiqueCheque(); // Appel de la méthode pour afficher les statistiques des chèques
            //checkAndAwardFidelityPoints(); // Appel de la méthode pour vérifier et attribuer des points de fidélité
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void statistiqueVirement() throws SQLException {
        ServiceVirement serviceVirement = new ServiceVirement();

        List<Virement> virements = serviceVirement.afficher();

        int compteurVEcoresponsabilite = 0;
        int compteurPersonne = 0;

        for (Virement virement : virements) {
            String typeVirement = virement.getType_virement();
            if (typeVirement.equals("VEcoresponsabilite")) {
                compteurVEcoresponsabilite++;
            } else if (typeVirement.equals("Personne")) {
                compteurPersonne++;
            }
        }

        // Ajouter les données au diagramme circulaire
        stat1.getData().add(new PieChart.Data("VEcoresponsabilite", compteurVEcoresponsabilite));
        stat1.getData().add(new PieChart.Data("Personne", compteurPersonne));

        // Afficher les nombres dans le diagramme circulaire
        for (PieChart.Data data : stat1.getData()) {
            data.setName(data.getName() + " (" + (int) data.getPieValue() + ")");
        }
    }

    private void statistiqueCheque() throws SQLException {
        ServiceCheque serviceCheque = new ServiceCheque();

        List<Cheque> cheques = serviceCheque.afficher();

        int compteurPaiement = 0;
        int compteurPaiementEco = 0;

        for (Cheque cheque : cheques) {
            String beneficiaire = cheque.getBeneficiaire();
            if (beneficiaire.equals("Paiement")) {
                compteurPaiement++;
            } else if (beneficiaire.equals("PaiementEco")) {
                compteurPaiementEco++;
            }
        }

        // Ajouter les données au diagramme circulaire
        stat2.getData().add(new PieChart.Data("Paiement", compteurPaiement));
        stat2.getData().add(new PieChart.Data("Paiement Eco", compteurPaiementEco));

        // Afficher les nombres dans le diagramme circulaire
        for (PieChart.Data data : stat2.getData()) {
            if (data.getName().equals("Paiement")) {
                data.getNode().setStyle("-fx-pie-color: #00FF00;");
            } else if (data.getName().equals("Paiement Eco")) {
                data.getNode().setStyle("-fx-pie-color: #00DD00;");
            }

            data.setName(data.getName() + " (" + (int) data.getPieValue() + ")");
        }
    }

//    private void checkAndAwardFidelityPoints() {
//        // Vérifier si le nombre total de virements de type "VEcoresponsabilite" dépasse un multiple de 10
//        if (compteurVEcoresponsabilite % 10 == 0 && compteurVEcoresponsabilite > 0) {
//            // Appeler la méthode pour attribuer un pack de points de fidélité
//            attribuerPointsFidelite();
//        }
//    }
//
//    private void attribuerPointsFidelite() {
//        // Mettre en œuvre la logique pour attribuer un pack de points de fidélité au compte bancaire
//        // Cette méthode doit accéder au compte bancaire et ajouter le pack de points de fidélité
//        // Implémentez cette méthode selon la logique et la structure de votre application
//        // Par exemple, vous pouvez appeler un service qui gère les comptes bancaires pour ajouter les points de fidélité.
//        // Ici, je vais simplement afficher un message pour simuler l'attribution des points.
//        System.out.println("Pack de points de fidélité ajouté au compte bancaire !");
//    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().getName().equals("Enter")) {
            sendMessage();
        }
    }

//    public void sendMessage() {
//        String message = messageArea.getText().trim();
//        System.out.println(message);
//        if (!message.isEmpty()) {
//            try {
//                String response = getChatbotResponse(message);
//                System.out.println(response);
//                addMessage("User: " + message);
//                addMessage("Bot: " + response);
//                messageArea.clear();
//            } catch (IOException | InterruptedException | JSONException e) {
//                e.printStackTrace();
//                addMessage("Bot: Sorry, an error occurred while processing your request.");
//            }
//        }
//    }
//public void sendMessage() {
//    String message = messageArea.getText().trim();
//    System.out.println(message);
//    if (!message.isEmpty()) {
//        try {
//            String response = getChatbotResponse(message);
//            if (response.length() > 250) {
//                response = response.substring(0, 250);
//            }
//            System.out.println(response);
//            addMessage(user.getName()+":" + " " + message);
//            addMessage("Bot: " + response);
//            messageArea.clear();
//        } catch (IOException | InterruptedException | JSONException e) {
//            e.printStackTrace();
//            addMessage("Bot: Sorry, an error occurred while processing your request.");
//        }
//    }
//}

//    private void addMessage(String message) {
//        Text text = new Text(message);
//        text.setWrappingWidth(350);
//        text.setTabSize(20);
//        conversationPane.getChildren().add(text);
//    }
//private void addMessage(String message) {
//    boolean isUserMessage = message.startsWith("User: ");
//    Label messageLabel = new Label(message);
//    messageLabel.setMaxWidth(500);
//    messageLabel.setWrapText(true);
//    messageLabel.setPadding(new Insets(10, 10, 10, 10));
//    messageLabel.setStyle("-fx-background-radius: 10px; -fx-font-size: 14px;");
//
//    HBox messageContainer = new HBox();
//    messageContainer.setMaxWidth(500);
//    messageContainer.setPadding(new Insets(5, 5, 5, 5));
//
//    if (isUserMessage) {
//        messageContainer.setAlignment(Pos.CENTER_RIGHT);
//        messageLabel.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 10px; -fx-padding: 10px; -fx-font-size: 14px;");
//    } else {
//        messageContainer.setAlignment(Pos.CENTER_LEFT);
//        messageLabel.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 10px; -fx-padding: 10px; -fx-font-size: 14px;");
//    }
//
//    messageContainer.getChildren().add(messageLabel);
//    conversationPane.getChildren().add(messageContainer);
//}

    public void sendMessage() {
        String message = messageArea.getText().trim();
        System.out.println(message);
        if (!message.isEmpty()) {
            try {
                String response = getChatbotResponse(message);
                if (response.length() > 250) {
                    response = response.substring(0, 250) + "."; // Ajoute "..." pour indiquer que le texte a été tronqué
                }
                System.out.println(response);
                addMessage(user.getName() + ": " + message, true); // true pour les messages de l'utilisateur
                addMessage("Bot: " + response, false); // false pour les messages du bot
                messageArea.clear();
            } catch (IOException | InterruptedException | JSONException e) {
                e.printStackTrace();
                addMessage("Bot: Sorry, an error occurred while processing your request.", false);
            }
        }
    }

    private void addMessage(String message, boolean isUserMessage) {
        Label messageLabel = new Label(message);
        messageLabel.setMaxWidth(500);
        messageLabel.setWrapText(true);
        messageLabel.setPadding(new Insets(10, 10, 10, 10));
        messageLabel.setStyle("-fx-background-radius: 10px; -fx-font-size: 14px;");

        HBox messageContainer = new HBox();
        messageContainer.setMaxWidth(500);
        messageContainer.setPadding(new Insets(5, 5, 5, 5));

        if (isUserMessage) {
            messageContainer.setAlignment(Pos.CENTER_RIGHT);
            messageLabel.setStyle("-fx-background-color: #2AAD1B; -fx-text-fill: white; -fx-background-radius: 10px; -fx-padding: 10px; -fx-font-size: 14px;");
        } else {
            messageContainer.setAlignment(Pos.CENTER_LEFT);
            messageLabel.setStyle("-fx-background-color: #D6D9D6; -fx-text-fill: black; -fx-background-radius: 10px; -fx-padding: 10px; -fx-font-size: 14px;");
        }

        messageContainer.getChildren().add(messageLabel);
        conversationPane.getChildren().add(messageContainer);
    }




    private String getChatbotResponse(String message) throws IOException, InterruptedException, JSONException {
        // Vos paramètres d'URL, clé API, etc.
        String url = "https://custom-chatbot-api.p.rapidapi.com/chatbotapi";
        String apiKey = "209ace395amshc6b108de067144ep16a5dcjsne29d7659fb96";
        String botId = "OEXJ8qFp5E5AwRwymfPts90vrHnmr8yZgNE171101852010w2S0bCtN3THp448W7kDSfyTf3OpW5TUVefz";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "custom-chatbot-api.p.rapidapi.com")
                .POST(HttpRequest.BodyPublishers.ofString("{\r\n    \"bot_id\": \"" + botId + "\",\r\n    \"messages\": [\r\n        {\r\n            \"role\": \"user\",\r\n            \"content\": \"" + message + "\"\r\n        }\r\n    ],\r\n    \"user_id\": \"\",\r\n    \"temperature\": 0.9,\r\n    \"top_k\": 5,\r\n    \"top_p\": 0.9,\r\n    \"max_tokens\": 256,\r\n    \"model\": \"matag2.0\"\r\n}"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println("shayma" + );
        // Extraire le message de la réponse JSON
        JSONObject jsonResponse = new JSONObject(response.body());
        String botResponse = jsonResponse.getString("result");

        return botResponse;
    }
}