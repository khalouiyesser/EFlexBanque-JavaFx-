package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidatorOffre {
    public static boolean isValidNumber(String input) {
        Boolean etat = false ;

        if (input != null && !input.isEmpty()) {
            char firstChar = input.charAt(0);
            if (firstChar == '2' || firstChar == '5' || firstChar == '9' || input.length()==8){
                etat = true;
            }
        }
        return etat;

    }
    public static boolean isValidDateDemande(String input) {
        boolean isValid = false;

        // Vérifie si l'entrée n'est pas vide
        if (input != null && !input.isEmpty()) {
            // Convertit la chaîne en LocalDate
            LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Vérifie si la date est antérieure à 7 jours par rapport à la date d'aujourd'hui
            LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
            isValid = date.isBefore(sevenDaysAgo);
        }

        return isValid;
    }


    public static boolean isValidEmail(String email) {
        // Vérifie si l'adresse e-mail est valide (c'est juste un exemple simplifié)
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    public static boolean isValidTitreOffre(String input) {
        boolean etat = false;
        if(input != null && input.length() > 20)
        {
            etat = true;
        }
        return etat;
    }
    public static boolean isValidDescriptionOffre(String input) {
        boolean etat = false;
        if(input != null && input.length() > 100)
        {
            etat = true;
        }
        return etat;
    }
    public static boolean isValidEmpty(String input) {
        boolean etat = false;
        if(input != null)
        {
            etat = true;
        }
        return etat;
    }




}