package com.group05.mycontactmanager.utilities;

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
public interface FileManager {

    /**
     * @brief Salva i contatti gestiti da un ContactManager su un file.
     * @param[in] contactManager L'istanza di ContactManager da salvare.
     * 
     */
    //public void saveContactManager(ContactManager contactManager);
    
    /**
     * @brief Carica un ContactManager da un file.
     * @return Un'istanza di ContactManager, restituisce null in caso di errore.
     */
    //public ContactManager loadContactManager();
    
    /**
     * @param nameFile
     * @brief Importa contatti da un file CSV.
     */
    public void importContactsFromCSV(String nameFile);
    
    /**
     * @param nameFile
     * @brief Esporta i contatti di un ContactManager in un file CSV.
     */
    public void exportContactsToCSV(String nameFile);
}

