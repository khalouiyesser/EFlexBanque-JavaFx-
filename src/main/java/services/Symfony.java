package services;

import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Symfony {

    public static void Shayma(int code){
//        String score;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://127.0.0.1:8000/Shayma/"+code);
            System.out.println("work");
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    System.out.println("work");
                } else {
                    System.err.println("Erreur lors de la requête : " + statusCode);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int score(int id, String cv) {
        int score = 0;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://127.0.0.1:8000/AnalyseurCvApi/"+id+"/"+cv);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    // Extract the score
                    score = jsonResponse.getInt("score");
                    System.out.println("Score: " + score);
                } else {
                    System.err.println("Erreur lors de la requête : " + statusCode);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return score;
    }
    public static void Recommendation(int id){
        String score;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://127.0.0.1:8000/Recommandation/"+id);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    // Extract the score
                    score = jsonResponse.getString("score");
                    System.out.println("Score: " + score);
                } else {
                    System.err.println("Erreur lors de la requête : " + statusCode);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void MdpOblie(String email, int code){
        String score;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://127.0.0.1:8000/MdpOublieA/"+email+"/"+code);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    // Extract the score
//                    score = jsonResponse.getString("score");
//                    System.out.println("Score: " + score);
                } else {
                    System.err.println("Erreur lors de la requête : " + statusCode);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mailingApprouver(String email) {
        int score = 0;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://127.0.0.1:8000/Mailing/"+email);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    // Extract the score
                    score = jsonResponse.getInt("score");
                    System.out.println("Score: " + score);
                } else {
                    System.err.println("Erreur lors de la requête : " + statusCode);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void mailingRefuser(String email, int id) {
        callMailingService("http://127.0.0.1:8000/mailingRefuser/" + email + "/" + id);
    }

//    public void mailingApprouver(String email, int id) {
//        callMailingService("http://127.0.0.1:8000/mailingApprouver/" + email + "/" + id);
//    }

    public void mailingApprouver(String email, int id) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            System.out.println(email+id);
            HttpGet request = new HttpGet("http://127.0.0.1:8000/mailingApprouver/"+ email+"/"+id);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    // Vérifier si la réponse est une chaîne de caractères "envoi avec succès"
                    if (responseBody.equals("envoi avec succès")) {
                        System.out.println("L'email a été envoyé avec succès.");
                    } else {
                        // Parse la réponse JSON
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        // Extraire le score si c'est un entier
                        if (jsonResponse.has("score")) {
                            int score = jsonResponse.getInt("score");
                            System.out.println("Score: " + score);
                        } else {
                            System.err.println("Réponse invalide : aucune clé 'score' trouvée.");
                        }
                    }
                } else {
                    System.err.println("Erreur lors de la requête : " + statusCode);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void callMailingService(String url) {
        int score = 0;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    // Extract the score
                    score = jsonResponse.getInt("score");
                    System.out.println("Score: " + score);
                } else {
                    System.err.println("Erreur lors de la requête : " + statusCode);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
//        int a = score(1,"Resume-65e5ed6c6fc54.pdf");
        Recommendation(1);
    }
}