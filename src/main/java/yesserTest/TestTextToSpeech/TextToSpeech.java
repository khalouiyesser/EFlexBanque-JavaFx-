package yesserTest.TestTextToSpeech;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TextToSpeech {

    public String T(String textToConvert) throws IOException, InterruptedException {
        String url = "https://cloudlabs-text-to-speech.p.rapidapi.com/synthesize";
        String apiKey = "b090e6cdc7msh955335d5f298704p15c4e9jsnd258bf270b2e";
       // String textToConvert = "salut ayoub je suis text to speech et je travaille très bien"; // Remplacez "Votre texte ici" par le texte que vous souhaitez convertir en discours.

        try {
            String audioUrl = getAudioUrl(url, apiKey, textToConvert);
            System.out.println("URL de l'audio: " + audioUrl);
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return getAudioUrl(url, apiKey, textToConvert);
    }
    private static String getAudioUrl(String url, String apiKey, String textToConvert) throws IOException, InterruptedException, JSONException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "cloudlabs-text-to-speech.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("voice_code=fr-FR-5&text=" + textToConvert.replaceAll("\\s+", "%20") + "&speed=1.00&pitch=1.00&output_type=audio_url"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        String audioUrl = jsonResponse.getJSONObject("result").getString("audio_url");
        return audioUrl;
    }
}
