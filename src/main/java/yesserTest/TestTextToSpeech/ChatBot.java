import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatBot {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://askpdf1.p.rapidapi.com/api/v1/ai-model-settings/update"))
//                .header("content-type", "application/json")
//                .header("X-RapidAPI-Key", "6335f0e1b6mshe51bdb6be5175a1p1914fajsn7770537fdb84")
//                .header("X-RapidAPI-Host", "askpdf1.p.rapidapi.com")
//                .POST(HttpRequest.BodyPublishers.ofString("{\n" +
//                        "    \"openai_key\": \"sk-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\",\n" +
//                        "    \"chat_agent_model\": \"gpt-3.5-turbo-1106\",\n" +
//                        "    \"chat_agent_model_temp\": 0.5,\n" +
//                        "    \"chat_tools_model\": \"text-davinci-003\",\n" +
//                        "    \"chat_tools_model_temp\": 1\n" +
//                        "}"))
//                .build();
//
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://harley-the-chatbot.p.rapidapi.com/talk/bot"))
                .header("content-type", "application/json")
                .header("Accept", "application/json")
                .header("X-RapidAPI-Key", "6335f0e1b6mshe51bdb6be5175a1p1914fajsn7770537fdb84")
                .header("X-RapidAPI-Host", "harley-the-chatbot.p.rapidapi.com")
                .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                        "    \"client\": \"\",\n" +
                        "    \"bot\": \"harley\",\n" +
                        "    \"message\": \"Hello\"\n" +
                        "}"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
//    }
}
