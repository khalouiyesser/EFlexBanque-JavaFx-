package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


public class Upload extends IOException {
    private File selectedCvFile;
    private String fileName;

    public String Upload(File selectedCvFile) {

        this.selectedCvFile = selectedCvFile;
        try {
            // Charger le PDF dans la WebView (si nécessaire)
//            String url = selectedCvFile.toURI().toString();
//            cv.getEngine().load(url);

            // Générer un nom de fichier unique pour le CV
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedCvFile.getName().substring(selectedCvFile.getName().lastIndexOf(".")); // L'extension est déjà spécifiée dans le filtre de l'FileChooser
            fileName = uniqueID + extension;

            // Copier le fichier PDF vers le répertoire de destination
            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", fileName);
            Files.copy(selectedCvFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
}