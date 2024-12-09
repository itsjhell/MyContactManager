package com.group05.mycontactmanager.utilities;

import com.group05.mycontactmanager.models.PhoneNumber;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @file Checker.java
 * @brief Classe di utilità per la validazione di email e numeri di telefono.
 * 
 *
 * 
 * @author group05
 * @date Dicembre 08, 2024
 * @version 1.0
 */
public class Checker {
    
    /**
     * @brief Verifica se l'email fornita è considerata valida.
     * @param[in] email L'indirizzo email da controllare.
     * @return true se la validazione è andata a buon fine e false per il contrario.
     */
    public static boolean checkEmail(String email) {
        if (email.equals("")) return true; // se l'utente lascia il campo vuoto
       
        String regex = "^([^@]+)@([^@]+\\.[^@]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    /**
     * @brief Verifica se il numero di telefono fornito è considerato valido.
     * @param[in] number L'oggetto PhoneNumber da controllare.
     * @return true se la validazione è andata a buon fine e false per il contrario.
     */
     public static boolean checkNumber(PhoneNumber number) {
        //Controllo che siano tutte cifre
         if (number.getPrefix() == null || !number.getNumber().matches("\\d+")) {
            return false;
        }
        //Controllo sulla lunghezza del numero
        int minLength = 0;
        int maxLength = 0;
        switch (number.getPrefix()) {
            case ITALY:
                minLength = 6;
                maxLength = 10;
                break;
            case USA:
                minLength = 10;
                maxLength = 10;
                break;
            case UK:
                minLength = 9;
                maxLength = 10;
                break;
            case FRANCE:
                minLength = 9;
                maxLength = 9;
                break;
            case GERMANY:
                minLength = 7;
                maxLength = 12;
                break;
            case PORTUGAL:
                minLength = 9;
                maxLength = 12;
                break;
            case OTHERS:
                minLength = 3;
                maxLength = 12;
                break;
            //default può essere omesso poiché nello switch sono già elencati tutti i casi possibili
        }
        
        int length = number.getNumber().length();
        return length >= minLength && length <= maxLength;
    }
    
}
