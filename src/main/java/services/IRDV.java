package services;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface IRDV <T> {
    void initialize(URL url, ResourceBundle resourceBundle);

    void saverdv(T t) throws SQLException;
}