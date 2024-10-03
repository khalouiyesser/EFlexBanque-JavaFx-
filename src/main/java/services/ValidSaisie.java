package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidSaisie {
    public static boolean isValidNumber(String input) {
        Boolean etat = false ;

        if (input != null && !input.isEmpty()) {
            char firstChar = input.charAt(0);
            if (firstChar == '2' || firstChar == '5' || firstChar == '9'){
                etat = true;
            }
        }
        return etat;

    }
    public static boolean isValidCin (String input) {
        boolean etat = false;

        if (input != null && input.matches("\\d{8}")) { // Vérifie si input contient 8 chiffres
            char firstChar = input.charAt(0);
            if (firstChar == '0' || firstChar == '1') { // Vérifie si le premier chiffre est 0 ou 1
                etat = true;
            }
        }
        return etat;
    }



    public static boolean isValidEmail(String email) {
        // Vérifie si l'adresse e-mail est valide (c'est juste un exemple simplifié)
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public static boolean isValidEmpty(String input) {
        boolean etat = false;
        if(input != null)
        {
            etat = true;
        }
        return etat;
    }
    public static boolean isValidDate(LocalDate date) {
        // Vérifie si la date est antérieure à 2025 (puisque vous avez mentionné "après 2024")
        LocalDate limiteDate = LocalDate.of(2024, 1, 1);
        return date.isBefore(limiteDate);
    }
    public static void ValidDateV() {
        // Obtenir la date d'aujourd'hui
        LocalDate aujourdhui = LocalDate.now();

        // Formater la date pour l'affichage
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateAujourdhuiFormatee = aujourdhui.format(formatter);

        // Afficher la date d'aujourd'hui
        System.out.println("La date d'aujourd'hui est : " + dateAujourdhuiFormatee);
    }
    public static boolean isValidDateN(LocalDate date) {
        // Vérifie si la date est antérieure à aujourd'hui moins 18 ans
        LocalDate limiteDate = LocalDate.now().minusYears(18);
        return date.isBefore(limiteDate);
    }
    public static boolean isValidNumB(String input) {
        if (input != null && !input.isEmpty()) {
            // Vérifie si le numéro de compte a 24 chiffres ou si les 13 derniers chiffres sont supérieurs ou égaux à 12
            if (input.length() == 24) {
                return true; // Si le numéro de compte a 24 chiffres, retourne true
            } else if (input.length() >= 13) {
                // Si le numéro de compte a plus de 13 chiffres, vérifie les 13 derniers chiffres
                String last13Digits = input.substring(input.length() - 13);
                try {
                    long last13Number = Long.parseLong(last13Digits);
                    return last13Number >= 12; // Vérifie si les 13 derniers chiffres sont supérieurs ou égaux à 12
                } catch (NumberFormatException e) {
                    // Si une exception est levée, cela signifie que les 13 derniers chiffres ne sont pas valides
                    return false;
                }
            }
        }
        return false; // Retourne false si l'entrée est vide, nulle ou ne satisfait pas les critères
    }




}