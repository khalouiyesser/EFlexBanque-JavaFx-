package tests;

import services.*;


import java.io.IOException;
import java.sql.*;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
//        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
////        OffreDeStage a = serviceOffreDeStage.afficheUne(1);
////        System.out.println(a.getPostePropose());
//        Symfony symfony = new Symfony();
//        symfony.mailingApprouver("khaluiyesser@gmail.com");
//        ServiceCredit serviceCredit = new ServiceCredit();
            ServiceOffreDeStage serviceCompte = new ServiceOffreDeStage();
        System.out.println(serviceCompte.afficher());;
//        System.out.println(serviceCompte.afficher());

//        ServiceCommentaireHadhemi serviceCommentaireHadhemi = new ServiceCommentaireHadhemi();
//        System.out.println(serviceCommentaireHadhemi.afficher());
//        int a = symfony.score(1,"56f16ce8-cb5c-49a1-9784-02b47a63d389.pdf");
//        System.out.println(a);
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://ai-chatbot.p.rapidapi.com/chat/free?message=What's%20your%20name%3F&uid=user1"))
//                .header("X-RapidAPI-Key", "8b61275eedmshe9d32ea6e4a9f06p109cc6jsnfe3c6c62e564")
//                .header("X-RapidAPI-Host", "ai-chatbot.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        //Genre
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://gender-from-name.p.rapidapi.com/gender/jhon"))
//                .header("X-RapidAPI-Key", "8b61275eedmshe9d32ea6e4a9f06p109cc6jsnfe3c6c62e564")
//                .header("X-RapidAPI-Host", "gender-from-name.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
        // tal5iss texte
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://text-summarization13.p.rapidapi.com/data"))
//                .header("content-type", "application/x-www-form-urlencoded")
//                .header("X-RapidAPI-Key", "8b61275eedmshe9d32ea6e4a9f06p109cc6jsnfe3c6c62e564")
//                .header("X-RapidAPI-Host", "text-summarization13.p.rapidapi.com")
//                .method("POST", HttpRequest.BodyPublishers.ofString("text=Il semble que vous receviez une réponse de votre requête HTTP, mais avec un message d'erreur provenant du chatbot. Le message d'erreur indique : \"Désolé, j'ai besoin de plus d'informations.\"\n" +
//                        "\n" +
//                        "Cela pourrait signifier que le chatbot n'a pas compris la question ou qu'il lui manque des informations pour fournir une réponse appropriée.\n" +
//                        "\n" +
//                        "Pour résoudre ce problème, vous pouvez examiner le format attendu des requêtes pour ce chatbot spécifique et vous assurer que vous fournissez toutes les informations nécessaires. Vous pouvez également vérifier s'il existe des restrictions d'utilisation de l'API ou des erreurs dans la manière dont vous envoyez la requête."))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
        // translation
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://translator82.p.rapidapi.com/api/translate"))
//                .header("content-type", "application/json")
//                .header("X-RapidAPI-Key", "8b61275eedmshe9d32ea6e4a9f06p109cc6jsnfe3c6c62e564")
//                .header("X-RapidAPI-Host", "translator82.p.rapidapi.com")
//                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"language\": \"en\",\r\n    \"text\": \"\"\r\n}"))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        //Copilot


//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://copilot5.p.rapidapi.com/copilot"))
//                .header("content-type", "application/json")
//                .header("X-RapidAPI-Key", "8b61275eedmshe9d32ea6e4a9f06p109cc6jsnfe3c6c62e564")
//                .header("X-RapidAPI-Host", "copilot5.p.rapidapi.com")
//                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"message\": \"Hello\",\r\n    \"conversation_id\": null,\r\n    \"tone\": \"BALANCED\",\r\n    \"markdown\": false,\r\n    \"photo_url\": null\r\n}"))
//                .build();
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        // Extraire le corps de la réponse JSON
//        String jsonResponse = response.body();
//
//        // Analyser la chaîne JSON en un objet JSON
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(jsonResponse);
//
//        // Extraire le message du champ "data"
//        String message = jsonNode.get("data").get("message").asText();
//
//        System.out.println(message);
//        ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();
//        System.out.println(serviceDemandeStage.afficherParOffre(5));
    }


//--module-path "C:\Users\Yesser\Downloads\openjfx-21.0.2_windows-x64_bin-sdk\javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml
}





