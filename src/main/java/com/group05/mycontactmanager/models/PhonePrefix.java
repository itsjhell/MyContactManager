package com.group05.mycontactmanager.models;

/**
 * @brief Enum per rappresentare prefissi telefonici internazionali.
 * 
 * Questo enum definisce alcuni prefissi telefonici comuni, restituibili come stringhe.
 * L'utilizzo di un enum garantisce la disponibilità di un insieme limitato di prefissi validi.
 * 
 * @author group05
 * @date Dicembre 08, 2024
 * @version 1.1
 */
public enum PhonePrefix {
    OTHERS("-"),
    USA("+1"),
    FRANCE("+33"),
    ITALY("+39"),
    UK("+44"),
    GERMANY("+49"),
    PORTUGAL("+351");

    private final String prefix;

    /**
     * @brief Costruttore per assegnare un prefisso all'enumerazione.
     *
     * @param[in] prefix La stringa che rappresenta il prefisso.
     */
    PhonePrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @brief Restituisce il valore di tipo PhonePrefix associato alla stringa.
     *
     * @param[in] strPrefix valore di tipo PhonePrefix.
     */
    public static PhonePrefix fromString (String strPrefix) {
        for (PhonePrefix p: PhonePrefix.values()) {
            if(p.prefix.equals(strPrefix))
            return p;
        }
        return OTHERS;
    }

    /**
     * @brief Restituisce la stringa del prefisso.
     *
     * @return La stringa associata al prefisso.
     */
    @Override
    public String toString() {
        return prefix; 
    }
}
