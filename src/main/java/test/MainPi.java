package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPi extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource("/FXML/project/AdminDashboard.fxml"));


            Scene scene = new Scene(root);

            primaryStage.getIcons().add(new Image("/img/logo.png"));
            primaryStage.setTitle("pidev");
            primaryStage.setScene(scene);
            MainPi.stage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
         launch(args);
    }
}
