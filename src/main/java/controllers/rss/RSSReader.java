//package controllers.rss;
//
////import java.net.URI;
////import java.net.http.HttpClient;
////import java.net.http.HttpRequest;
////import java.net.http.HttpResponse;
////import java.util.concurrent.CompletableFuture; // Importer CompletableFuture pour gérer les opérations asynchrones
////
////public class RSSReader {
////    public static void main(String[] args) {
////        // Créer un client HttpClient
////        HttpClient client = HttpClient.newHttpClient();
////
////        // Créer une requête HTTP GET vers l'URL du flux RSS
////        HttpRequest request = HttpRequest.newBuilder()
////                .uri(URI.create("https://rss.app/feeds/v1.1/tOgyrCZKdMWLeE5M.json"))
////                .build();
////
////        // Envoyer la requête de manière asynchrone et traiter la réponse
////        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
////
////        // Utiliser CompletableFuture pour traiter la réponse lorsque disponible//package controllers.rss;
////
//////import java.net.URI;
//////import java.net.http.HttpClient;
//////import java.net.http.HttpRequest;
//////import java.net.http.HttpResponse;
//////import java.util.concurrent.CompletableFuture; // Importer CompletableFuture pour gérer les opérations asynchrones
//////
//////public class RSSReader {
//////    public static void main(String[] args) {
//////        // Créer un client HttpClient
//////        HttpClient client = HttpClient.newHttpClient();
//////
//////        // Créer une requête HTTP GET vers l'URL du flux RSS
//////        HttpRequest request = HttpRequest.newBuilder()
//////                .uri(URI.create("https://rss.app/feeds/v1.1/tOgyrCZKdMWLeE5M.json"))
//////                .build();
//////
//////        // Envoyer la requête de manière asynchrone et traiter la réponse
//////        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//////
//////        // Utiliser CompletableFuture pour traiter la réponse lorsque disponible
//////        futureResponse.thenApply(HttpResponse::body) // Récupérer le corps de la réponse
//////                .thenAccept(System.out::println) // Afficher le corps de la réponse
//////                .join(); // Attendre que toutes les opérations se terminent
//////    }
//////}
////import com.fasterxml.jackson.databind.JsonNode;
////import com.fasterxml.jackson.databind.ObjectMapper;
////
////import java.io.IOException;
////import java.net.URL;
////import java.util.ArrayList;
////import java.util.List;
////
////public class RSSReader {
////    private String title;
////    private String content;
////
////    public RSSReader(String title, String content) {
////        this.title = title;
////        this.content = content;
////    }
////
////    public String getTitle() {
////        return title;
////    }
////
////    public String getContent() {
////        return content;
////    }
////
////
////    public static List<RSSReader> loadArticlesFromURL(String urlString) throws IOException {
////        List<RSSReader> articles = new ArrayList<>();
////
////        // Step 1: Load JSON data from API
////        ObjectMapper objectMapper = new ObjectMapper();
////        URL url = new URL("https://rss.app/feeds/v1.1/t9AIvL6SZLnwsI91.json");
////        JsonNode jsonNode = objectMapper.readTree(url);
////
////        // Step 2: Parse JSON data into RSSReader objects
////        for (JsonNode item : jsonNode.get("items")) {
////            String title = item.get("title").asText();
////            String content = item.get("content_text").asText();
////            articles.add(new RSSReader(title, content));
////        }
////
////        return articles;
////    }
////}
//package controllers.rss;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RSSReader {
//    private String title;
//    private String content;
//    private String datePublished;
//    private String author;
//    private String imageUrl;
//
//    public RSSReader(String title, String content, String datePublished, String author) {
//        this.title = title;
//        this.content = content;
//        this.datePublished = datePublished;
//        this.author = author;
//        this.imageUrl = imageUrl;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public String getDatePublished() {
//        return datePublished;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public static List<RSSReader> loadArticlesFromURL(String urlString) throws IOException {
//        List<RSSReader> articles = new ArrayList<>();
//
//        // Step 1: Load JSON data from API
//        ObjectMapper objectMapper = new ObjectMapper();
//        URL url = new URL("https://rss.app/feeds/v1.1/t9AIvL6SZLnwsI91.json");
//        JsonNode jsonNode = objectMapper.readTree(url);
//
//        // Step 2: Parse JSON data into RSSReader objects
//        for (JsonNode item : jsonNode.get("items")) {
//            String title = item.get("title").asText();
//            String content = item.get("content_text").asText();
//            String datePublished = item.get("date_published").asText();
//            String author = item.get("authors").get(0).get("name").asText();
//            //   String imageUrl = item.get("image").asText();
//            articles.add(new RSSReader(title, content, datePublished, author));
//        }
//
//        return articles;
//    }
//}
////        futureResponse.thenApply(HttpResponse::body) // Récupérer le corps de la réponse
////                .thenAccept(System.out::println) // Afficher le corps de la réponse
////                .join(); // Attendre que toutes les opérations se terminent
////    }
////}
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RSSReader {
//    private String title;
//    private String content;
//
//    public RSSReader(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//
//    public static List<RSSReader> loadArticlesFromURL(String urlString) throws IOException {
//        List<RSSReader> articles = new ArrayList<>();
//
//        // Step 1: Load JSON data from API
//        ObjectMapper objectMapper = new ObjectMapper();
//        URL url = new URL("https://rss.app/feeds/v1.1/t9AIvL6SZLnwsI91.json");
//        JsonNode jsonNode = objectMapper.readTree(url);
//
//        // Step 2: Parse JSON data into RSSReader objects
//        for (JsonNode item : jsonNode.get("items")) {
//            String title = item.get("title").asText();
//            String content = item.get("content_text").asText();
//            articles.add(new RSSReader(title, content));
//        }
//
//        return articles;
//    }
//}
package controllers.rss;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSReader {
    private String title;
    private String content;
    private String datePublished;
    private String author;
    private URL imageUrl;

    public RSSReader(String title, String content, String datePublished, String author,URL imageUrl) {
        this.title = title;
        this.content = content;
        this.datePublished = datePublished;
        this.author = author;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getAuthor() {
        return author;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public static List<RSSReader> loadArticlesFromURL(String urlString) throws IOException {
        List<RSSReader> articles = new ArrayList<>();

        // Step 1: Load JSON data from API
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL(urlString);
        JsonNode jsonNode = objectMapper.readTree(url);

        for (JsonNode item : jsonNode.get("items")) {
            String title = item.has("title") ? item.get("title").asText() : "";
            String content = item.has("content_text") ? item.get("content_text").asText() : "";
            String datePublished = item.has("date_published") ? item.get("date_published").asText() : "";
            String author = item.has("authors") ? item.get("authors").get(0).get("name").asText() : "";
            URL imageUrl = null;
            if (item.has("image")) {
                try {
                    imageUrl = new URL(item.get("image").asText());
                    System.out.println("image " + imageUrl );
                } catch (MalformedURLException e) {
                    // Handle invalid URL
                    System.err.println("Invalid image URL: " + e.getMessage());
                }
            }
            articles.add(new RSSReader(title, content, datePublished, author, imageUrl));
        }


        return articles;
    }


    @Override
    public String toString() {
        return "RSSReader{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", datePublished='" + datePublished + '\'' +
                ", author='" + author + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
