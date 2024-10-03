package tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class TranslatorText {
    private static String key = "eb516db5e5524ac7bf31811fa1dbd071";
    private static String location = "francecentral";

    // Cette fonction effectue une requête POST.
    public String post(String text, String fromLanguage, String toLanguage) throws IOException, InterruptedException {
        String url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=" +
                fromLanguage + "&to=" + toLanguage;
        String requestBody = "[{\"Text\": \"" + text + "\"}]";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Ocp-Apim-Subscription-Key", key)
                .header("Ocp-Apim-Subscription-Region", location)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Vérifiez si la réponse est un succès (code 200)
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body());

            // Obtenez la traduction du texte à partir de la réponse JSON
            String translatedText = rootNode.get(0).get("translations").get(0).get("text").asText();
            System.out.println(translatedText); // Afficher la traduction
            return translatedText; // Retourner le texte traduit
        } else {
            System.out.println("Erreur lors de la requête : " + response.statusCode() + " " + response.body());
            return null;
        }
    }




    public static void main(String[] args) {
        try {
            TranslatorText translateRequest = new TranslatorText();
            String text = "I would really like to drive your car around the block a few times!";
            String fromLanguage = "en";
            String toLanguage = "ar";

            String response = translateRequest.post(text, fromLanguage, toLanguage);

            if (response != null) {
                String[] parts = response.split("\"text\":\"");
                if (parts.length > 1) {
                    String translatedText = parts[1].split("\"")[0];
                    System.out.println(translatedText);
                } else {
                    System.out.println("Erreur : Impossible de récupérer la traduction.");
                }
            } else {
                System.out.println("Erreur : Réponse nulle.");
            }
        } catch (Exception e) {
            System.out.println("Une exception s'est produite : " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Cette fonction formate joliment la réponse JSON.
    public static String prettify(String json_text) {
        // Vous pouvez réutiliser la fonction prettify() existante
        return json_text;
    }
}
