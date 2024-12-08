/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.utilities;

import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.ContactManager;
import javafx.stage.FileChooser;

/**
 * @file FileManager.java
 * @brief Classe di utilità per il salvataggio e il caricamento di contatti su file.
 * 
 * Questa classe fornisce metodi statici per salvare, caricare, importare ed esportare
 * oggetti ContactManager da/verso file o formati CSV. 
 * 
 * 
 * @author group05
 * @date Dicembre 08, 2024
 * @version 1.0
 */
public class FileManager {

    /**
     * @brief Salva i contatti gestiti da un ContactManager su un file.
     * @param contactManager L'istanza di ContactManager da salvare.
     * 
     */
    public static void saveContactManager(ContactManager contactManager) {
    }
    
    /**
     * @brief Carica un ContactManager da un file.
     * @return Un'istanza di ContactManager, restituisce null in caso di errore.
     */
    public static ContactManager loadContactManager() {
        return null;
    }
    
    /**
     * @brief Importa contatti da un file CSV.
     * @return Un ContactManager con i contatti importati , restituisce null in caso di errore.
     */
    public static ContactManager importContactsFromCSV() {
        return null;
    } 
    
    /**
     * @brief Esporta i contatti di un ContactManager in un file CSV.
     * @param contactManager L'istanza di ContactManager da esportare.
     * 
     */
    public static void exportContactsToCSV(ContactManager contactManager) {       
    }
}

