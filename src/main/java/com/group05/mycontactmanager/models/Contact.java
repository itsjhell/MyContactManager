package com.group05.mycontactmanager.models;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 * @file Contact.java
 * @brief Questo file implementa la scheda del contatto.
 * 
 * La classe raggruppa funzioni che permettono di gestire le informazioni
 * relative al contatto, tra cui nome, cognome, note, foto, email e numero di telefono.
 * 
 * @author Group 05
 * @date Dicembre 8, 2024
 * @version 1.0
 */
public class Contact {
    private String name;
    private String surname;
    private List<PhoneNumber> numbers;
    private List<String> emailAddresses;
    private String imageName;
    private String notes;

    /**
     * @param name
     * @param surname
     * @param numbers
     * @param emailAddresses
     * @param imagePath
     * @param notes

     * @brief Costruttore di un contatto con due campi obbligatori.
     * 
     * Viene inizializzata di default una lista vuota.
     * 
     * @param[in] name nome del contatto.
     * @param[in] surname cognome del contatto.
     * @param[in] numbers, la lista di  nome del contatto.
     * @param[in] surname cognome del contatto.
     */
    
    public Contact(String name, String surname, List<PhoneNumber> numbers, List<String> emailAddresses, String imageName, String notes) {
        this.name = name;
        this.surname = surname;
        this.numbers = numbers;
        this.emailAddresses = emailAddresses;
        if(imageName.equals("") )
            this.imageName = "default.png";
        else
            this.imageName = imageName;
        
        this.notes = notes;
    }

    /**
     * @brief La funzione restituisce il nome del contatto.
     * 
     * @return nome del contatto.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief La funzione imposta il nome del contatto.
     * 
     * @param[in] name nome del contatto.
     */
    public void setName(String name) {
        this.name = name;
    }
   
    /**
     * @brief La funzione restituisce il cognome del contatto.
     * 
     * @return cognome del contatto.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @brief La funzione imposta il cognome del contatto.
     * 
     * @param[in] surname cognome del contatto.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @brief La funzione restituisce i numeri di teleofono del contatto.
     * 
     * @return numeri del contatto.
     */
    public List<PhoneNumber> getNumbers() {
        return numbers;
    }

    /**
     * @brief La funzione aggiunge un nuovo numero di telefono al contatto.
     * 
     * @pre "!numbers.contains(number)" il numero non deve essere già presente.
     * @pre "number != null" il numero non deve essere uguale a null.
     * 
     * @param[in] number numero da aggiungere.
     */
    public void addNumber(PhoneNumber number) {
        if (number != null && !numbers.contains(number)) {
            numbers.add(number);
        }
    }

    /**
     * @brief La funzione rimuove un numero di telefono dalla lista del contatto.
     * 
     * @param[in] number il numero da rimuovere.
     */
    public void removeNumber(PhoneNumber number) {
        numbers.remove(number);
    }

    /**
     * @brief La funzione restituisce le email del contatto.
     * 
     * @return la lista delle email.
     */
    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    /**
     * @brief La funzione aggiunge un nuovo indirizzo email al contatto.
     * 
     * @pre 'email != null' email non deve essere uguale a null.
     * @pre '!emailAddresses.contains(email)' email non deve essere già presente.
     * 
     * @param[in] email la nuova email da inserire.
     */
    public void addEmail(String email) {
        if (email != null && !emailAddresses.contains(email)) {
            emailAddresses.add(email);
        }
    }

    /**
     * @brief La funzione rimuove l'email dalla lista del contatto.
     * 
     * @param[in] email l'email che si intende rimuovere 
     */
    public void removeEmail(String email) {
        emailAddresses.remove(email);
    }

    /**
     * @brief La funzione restituisce il percorso dell'immagine profilo del contatto.
     * 
     * @return immagine profilo.
     */
    public String getImagePath() {
        return "images/" + imageName;
    }
    
    public String getImageName() {
        return imageName;
    }
    /**
     * @brief La funzione imposta il percorso di una nuova immagine profilo del contatto.
     * 
     * @param[in] imagePath nuovo percorso immagine.
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @brief La funzione restituisce le note associate al contatto.
     * 
     * @return note del contatto.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @brief La funzione imposta un blocco note associato al contatto.
     * 
     * @param[in] notes nuove note.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @brief Fornisce una rappresentazione testuale del contatto.
     * @return Una stringa contenente nome, cognome, numeri di telefono, email, percorso immagine e note.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name);
        sb.append(" Surname: ").append(surname);
        sb.append(" Numbers: ").append(numbers);
        sb.append(" Emails: ").append(emailAddresses);
        sb.append(" Image Name: ").append(imageName);
        sb.append(" Notes: ").append(notes);
        return sb.toString();
    }
    
    public Contact clone() {
        return new Contact(name, surname,  numbers, emailAddresses, imageName, notes);
    }
    
}
