package services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.sql.SQLException;

public class AnalyseurCv {
    public int analyseCV(String cv, Array motsCles) throws IOException, InterruptedException, SQLException {
        String pythonScriptPath = "C:\\Users\\Yesser\\PI\\InnovatixYesser\\API\\Analyseur.py";


        Object[] motsClesObjects = (Object[]) motsCles.getArray();
        String[] motsClesStrings = new String[motsClesObjects.length];
        for (int i = 0; i < motsClesObjects.length; i++) {
            motsClesStrings[i] = motsClesObjects[i].toString();
        }
        String motsClesString = String.join(",", motsClesStrings);




        // Construction de la commande pour exécuter le script Python avec les arguments CV et mots-clés
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, cv, String.join(",", motsClesString));
        Process process = processBuilder.start();

        // Lecture de la sortie standard du processus
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        StringBuilder output = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            output.append(line).append("\n");
        }

        // Attente de la fin de l'exécution du processus
        int exitCode = process.waitFor();

        // Gestion des erreurs
        if (exitCode != 0) {
            throw new RuntimeException("Erreur lors de l'exécution du script Python : " + output.toString());
        }

        // Conversion de la sortie en nombre décimal
        double resultatDecimal = Double.parseDouble(output.toString().trim());

        // Conversion du nombre décimal en entier
        return (int) Math.round(resultatDecimal);
    }

    // Exemple d'utilisation
    public static void main(String[] args) throws IOException, InterruptedException {
        String cv = "C:/Users/Yesser/Desktop/Resume.pdf";
//        String[] motsCles = {"java", "Kotlin", "mot3"};
//
//        int resultat = analyseCV(cv, motsCles);
        System.out.println("Résultat de l'analyse : " );
    }
}