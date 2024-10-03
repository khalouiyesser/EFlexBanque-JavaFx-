//package controllers.textToSpeech;
//
//import com.github.sarxos.webcam.Webcam;
//import com.github.sarxos.webcam.WebcamPanel;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.io.File;
//import java.io.IOException;
//import java.util.Random;
//
//public class Test {
//
//    @FXML
//    private ImageView imageView;
//
//    private String imagePath;
//
//    @FXML
//    private void camera(ActionEvent event) {
//        Webcam webcam = Webcam.getDefault();
//        if (webcam != null) {
//            webcam.open();
//
//            // Ouvrir une fenêtre pour afficher l'aperçu de la caméra
//            JFrame window = new JFrame("Camera Preview");
//            window.setLayout(new BorderLayout());
//            window.setSize(640, 480);
//
//            WebcamPanel panel = new WebcamPanel(webcam);
//            panel.setMirrored(true);
//            window.add(panel, BorderLayout.CENTER);
//
//            JButton captureButton = new JButton("Capture");
//            captureButton.addActionListener(e -> {
//                // Prendre une capture d'écran et la sauvegarder
//                try {
//                    Random rnd = new Random();
//                    int number = rnd.nextInt(999999999);
//                    String filename = number + "_capture.jpg";
//                    String filePath = "C:\\Users\\siwar\\OneDrive\\Bureau\\final\\Integration\\src\\main\\resources\\img\\uploads\\" + filename;
//                    File file = new File(filePath);
//                    ImageIO.write(webcam.getImage(), "JPG", file);
//                    System.out.println("Capture saved: " + filename);
//
//                    // Définir l'image capturée dans l'ImageView
//                    Image image = new Image(file.toURI().toString());
//                    imageView.setImage(image);
//
//                    // Assigner le chemin de l'image capturée à l'attribut imagePath
//                    imagePath = filePath;
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
//            window.add(captureButton, BorderLayout.SOUTH);
//
//            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            window.setVisible(true);
//        } else {
//            System.out.println("Aucune webcam détectée.");
//        }
//    }
//
//    public void uploadImage(ActionEvent actionEvent) {
//    }
//}
