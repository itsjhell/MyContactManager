package com.group05.mycontactmanager.utilities;

/**
 * @brief Interfaccia di utilità per il salvataggio e il caricamento di contatti su file.
 * 
 * Questa interfaccia dichiara i metodi per importare ed esportare
 * oggetti ContactManager da/verso file o formati CSV. 
 * 
 * 
 * @author group05
 * @date Dicembre 08, 2024
 * @version 1.0
 */
public interface FileManager {
    /**
     * @param nameFile
     *
     * @brief Importa contatti da un file CSV.
     */
    public void importContactsFromCSV(String nameFile);
    
    /**
     * @param nameFile
     *
     * @brief Esporta i contatti di un ContactManager in un file CSV.
     */
    public void exportContactsToCSV(String nameFile);
}

