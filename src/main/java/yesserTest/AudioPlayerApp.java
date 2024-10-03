package yesserTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tests.Yesser;

import java.io.File;
import java.io.IOException;

public class AudioPlayerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        Yesser yesser = new Yesser();
        // Chemin vers votre fichier audio
        String audioFilePath = yesser.getAudioUrl("https://cloudlabs-text-to-speech.p.rapidapi.com/synthesize",
                "4123283f42mshd62cdbb1176ec54p18626bjsn5f84d65cdc45",
                "Bonjour");;

        // Création du lecteur média
        Media media = new Media(new File(audioFilePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Création du bouton
        Button button = new Button("Cliquez pour jouer l'audio");

        // Ajout d'un gestionnaire d'événements au clic sur le bouton
        button.setOnAction(event -> {
            // Vérifiez si le lecteur média est déjà en lecture, arrêtez-le puis jouez à nouveau
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.stop();
            }
            mediaPlayer.play();
        });

        // Création de la mise en page
        StackPane root = new StackPane();
        root.getChildren().add(button);

        // Création de la scène
        Scene scene = new Scene(root, 300, 250);

        // Configuration de la scène et affichage de la fenêtre
        primaryStage.setTitle("Lecture audio sur clic");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
