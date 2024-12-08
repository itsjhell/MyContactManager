/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.models;

/**
 * @file PhoneNumber.java
 * @brief Classe che rappresenta un numero di telefono con prefisso.
 * 
 * La classe modella l’entità del numero di telefono, 
 * combinando il prefisso con il numero fornito per la corretta rappresentazione.
 * 
 * @author group05
 * @date Dicembre 08, 2024
 * @version 1.0
 */
public class PhoneNumber {
    private PhonePrefix prefix;
    private String number;

    /**
     * @brief Costruttore per creare un PhoneNumber con prefisso e numero.
     * @param prefix Il prefisso telefonico.
     * @param number La parte numerica del telefono.
     */
    public PhoneNumber(PhonePrefix prefix, String number) {
        this.prefix = prefix;
        this.number = number;
    }

    /**
     * @brief Imposta il prefisso del numero di telefono.
     * @param prefix Il nuovo prefisso.
     */
    public void setPrefix(PhonePrefix prefix) {
        this.prefix = prefix;
    }

    /**
     * @brief Imposta la parte numerica del telefono.
     * @param number Il nuovo numero.
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
    /**
     * @brief Restituisce il prefisso del numero di telefono.
     * @return Il prefisso PhonePrefix.
     */
    public PhonePrefix getPrefix() {
        return prefix;
    }

    /**
     * @brief Restituisce il numero di telefono senza prefisso.
     * @return Il numero come stringa.
     */
    public String getNumber() {
        return number;
    }
    
    /**
     * @brief Ritorna una stringa rappresentante il numero completo (prefisso + numero).
     * @return Una stringa con prefisso e numero concatenati.
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getPrefix()+" "+number);
        return sb.toString();
    }
}
