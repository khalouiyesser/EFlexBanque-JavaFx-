package controllers.textToSpeech;

import java.io.FileOutputStream;
//import com.voicerss.tts.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TextToSpeechReader {
//    public static void main(String[] args) throws Exception {
//        // Remplacez "<Votre clé API>" par votre propre clé API fournie par VoiceRSS
//        String apiKey = "<55b84a792cf94a90b6422de17a8dd24d>";
//
//        // Créer une instance de VoiceProvider avec votre clé API
//        VoiceProvider tts = new VoiceProvider(apiKey);
//
//        // Paramètres de la voix
//        VoiceParameters params = new VoiceParameters("Bonjour tout le monde !", Languages.French_France);
//        params.setCodec(AudioCodec.MP3);
//        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
//        params.setBase64(false);
//        params.setSSML(false);
//        params.setRate(0);
//
//        // Appel synchrone à l'API Text-to-Speech
//        byte[] voice = tts.speech(params);
//
//        // Écrire le fichier audio résultant
//        FileOutputStream fos = new FileOutputStream("voice.mp3");
//        fos.write(voice);
//        fos.flush();
//        fos.close();
//
//        System.out.println("Conversion du texte en discours terminée !");
//
//    }

//@FXML
//private TextField textField;
//
//    public void speakText() {
//        String text = textField.getText();
//        if (!text.isEmpty()) {
//            try {
//                // Remplacez "<Votre clé API>" par votre propre clé API fournie par VoiceRSS
//                String apiKey = "<Votre clé API>";
//
//                // Créer une instance de VoiceProvider avec votre clé API
//                VoiceProvider tts = new VoiceProvider(apiKey);
//
//                // Paramètres de la voix
//                VoiceParameters params = new VoiceParameters(text, Languages.French_France);
//                params.setCodec(AudioCodec.MP3);
//                params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
//                params.setBase64(false);
//                params.setSSML(false);
//                params.setRate(0);
//
//                // Appel synchrone à l'API Text-to-Speech
//                byte[] voice = tts.speech(params);
//
//                // Écrire le fichier audio résultant
//                FileOutputStream fos = new FileOutputStream("voice.mp3");
//                fos.write(voice);
//                fos.flush();
//                fos.close();
//
//                System.out.println("Conversion du texte en discours terminée !");
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
}