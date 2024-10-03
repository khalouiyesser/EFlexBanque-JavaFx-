package yesserTest.TestTextToSpeech;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LeaderboardEntry {
    private final StringProperty position;
    private final StringProperty username;
    private final StringProperty profit;

    public LeaderboardEntry(String position, String username, String profit) {
        this.position = new SimpleStringProperty(position);
        this.username = new SimpleStringProperty(username);
        this.profit = new SimpleStringProperty(profit);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getProfit() {
        return profit.get();
    }

    public StringProperty profitProperty() {
        return profit;
    }
}
