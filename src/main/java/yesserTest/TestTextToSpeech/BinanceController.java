package yesserTest.TestTextToSpeech;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BinanceController {
    @FXML
    private TableView<LeaderboardEntry> leaderboardTable;

    @FXML
    private TableColumn<LeaderboardEntry, String> positionColumn;

    @FXML
    private TableColumn<LeaderboardEntry, String> usernameColumn;

    @FXML
    private TableColumn<LeaderboardEntry, String> profitColumn;

    public void initialize() {
        // Initialisez les colonnes du tableau
        positionColumn.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        profitColumn.setCellValueFactory(cellData -> cellData.getValue().profitProperty());

        // Appelez votre méthode pour récupérer les données de l'API et peupler le tableau
        try {
            populateLeaderboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateLeaderboard() throws Exception {
        // Votre code pour appeler l'API et récupérer les données
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://binance-futures-leaderboard1.p.rapidapi.com/v2/getTraderPositions?encryptedUid=%3CREQUIRED%3E"))
                .header("X-RapidAPI-Key", "6335f0e1b6mshe51bdb6be5175a1p1914fajsn7770537fdb84")
                .header("X-RapidAPI-Host", "binance-futures-leaderboard1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        // Analyser la réponse JSON et peupler le TableView
        JSONArray positions = new JSONObject(responseBody).getJSONArray("positions");

        for (int i = 0; i < positions.length(); i++) {
            JSONObject position = positions.getJSONObject(i);
            String positionStr = position.getString("position");
            String username = position.getString("username");
            String profit = position.getString("profit");

            leaderboardTable.getItems().add(new LeaderboardEntry(positionStr, username, profit));
        }
    }
}
