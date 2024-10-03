package yesserTest.TestTextToSpeech;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;


public class Controller {
    @FXML
    private TextArea chatArea;
    @FXML
    private TextArea messageArea;
    @FXML
    private VBox conversationPane;

    public void sendMessage() {
        String message = messageArea.getText().trim();
        if (!message.isEmpty()) {
            try {
                String response = getChatbotResponse(message);
                addMessage("User: " + message);
                addMessage("Bot: " + response);
                messageArea.clear();
            } catch (IOException | InterruptedException | JSONException e) {
                e.printStackTrace();
                addMessage("Bot: Sorry, an error occurred while processing your request.");
            }
        }
    }

    private void addMessage(String message) {
        Text text = new Text(message);
        text.setWrappingWidth(350);
        text.setTabSize(20);
        conversationPane.getChildren().add(text);
    }

    private String getChatbotResponse(String message) throws IOException, InterruptedException, JSONException {
        // Vos paramètres d'URL, clé API, etc.
        String url = "https://custom-chatbot-api.p.rapidapi.com/chatbotapi";
        String apiKey = "6335f0e1b6mshe51bdb6be5175a1p1914fajsn7770537fdb84";
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

        // Extraire le message de la réponse JSON
        JSONObject jsonResponse = new JSONObject(response.body());
        String botResponse = jsonResponse.getString("result");

        return botResponse;
    }

    @FXML
    private void handleKeyPress(javafx.scene.input.KeyEvent keyEvent) {
        if (keyEvent.getCode().getName().equals("Enter")) {
            sendMessage();
        }
    }
}
