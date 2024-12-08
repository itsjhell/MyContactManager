/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.models;

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
public class ContactManager {
    private String name;
    private List<Contact> contactList;
    
    /**
     * @brief Crea un nuovo ContactManager con un dato nome.
     * @param name Il nome associato alla lista di contatti.
     */
    public ContactManager(String name) {
        this.name = name;
        this.contactList = new ArrayList<>(); 
    }

    /**
     * @brief Imposta il nome della lista di contatti.
     * @param name Il nuovo nome.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief Imposta la lista di contatti.
     * @param contactList Una lista di oggetti Contact.
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
     * @param contact Il contatto da aggiungere.
     */
    public void addContact(Contact contact) {
        // TODO: Implementare logica per aggiungere un contatto.
    }
    
    /**
     * @brief Rimuove un contatto dalla lista (non implementato).
     * @param contact Il contatto da rimuovere.
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
}
