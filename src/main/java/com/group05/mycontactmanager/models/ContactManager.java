package com.group05.mycontactmanager.models;

import com.group05.mycontactmanager.utilities.FileManager;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @file ContactManager.java
 * @brief Classe per la gestione di una lista di contatti.
 * 
 * La classe raggruppa funzioni che permettono la manipolazione di una lista di contatti;
 * sono presenti funzioni di aggiunta e rimozione dei contatti.

 * @author group05
 * @date Dicembre 08,2024
 * @version 1.0
 */
public class ContactManager implements Serializable, FileManager{
    private String name;
    private List<Contact> contactList;
    
    /**
     * @brief Crea un nuovo ContactManager con un dato nome.
     * @param[in] name Il nome associato alla lista di contatti.
     */
    public ContactManager(String name) {
        this.name = name;
        this.contactList = new ArrayList<>(); 
    }

    /**
     * @brief Imposta il nome della lista di contatti.
     * @param[in] name Il nuovo nome.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief Imposta la lista di contatti.
     * @param[in] contactList Una lista di oggetti Contact.
     */
    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
    
    /**
     * @brief Restituisce il nome della lista di contatti.
     * @return Il nome attuale.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Restituisce la lista di contatti.
     * @return La lista di Contact.
     */
    public List<Contact> getContactList() {
        return contactList;
    }
    
    /**
     * @brief Aggiunge un contatto alla lista (non implementato).
     * @param[in] contact Il contatto da aggiungere.
     */
    public void addContact(Contact contact) {
       
    }
    
    /**
     * @brief Rimuove un contatto dalla lista (non implementato).
     * @param[in] contact Il contatto da rimuovere.
     */
    public void removeContact(Contact contact) {
        // TODO: Implementare logica per rimuovere un contatto.
    }
      
    /**
     * @brief Restituisce una rappresentazione testuale del ContactManager.
     * @return Una stringa descrittiva .
     */
    @Override
    public String toString() {
        return null;
    }

    @Override
    public ContactManager importContactsFromCSV(String nameFile) {
        return null;
    }

    @Override
    public void exportContactsToCSV(String nameFile) {
        try(PrintWriter o = new PrintWriter( new BufferedWriter( new FileWriter(nameFile)))) {
            for (Contact c : contactList) {
                o.print(c.getName()+";"+c.getSurname()+";");
                
                if(c.getNumbers() != null) {
                    for(PhoneNumber pn: c.getNumbers()) {
                        if(pn != null)
                            o.print(pn.toString());
                        o.print(";");
                    }
                } else {
                   o.print(";;;");
                }
                
                if(c.getEmailAddresses() != null) {
                    for(String email: c.getEmailAddresses()) {
                        if(email != null)
                            o.print(email.toString());
                        o.print(";");
                    }
                } else {
                    o.print(";;;");
                }
                
                o.print(c.getImagePath()+";\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    private void writeObject(String nameFile) {
        
    }
    private void readObject(String nameFile) {
        
    }
}
