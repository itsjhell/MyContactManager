package com.group05.mycontactmanager.utilities;
import com.group05.mycontactmanager.models.Contact;
import java.util.Comparator;

/**
 * @brief Comparator che definisce il criterio di ordinamento della rubrica.
 * 
 * Questa classe confronta tra loro due contatti.
 * Viene utilizzata dalla SortedList caricata nella TableView, per ordinare i contatti in base a cognome-nome.
 * 
 * @author group05
 * @date Dicembre 08, 2024
 * @version 1.1
 */
public class ContactComparator implements Comparator<Contact> {

    /**
     * @brief Definisce il criterio di confronto tra due contatti.
     *
     * Se i contatti hanno entrambi un cognome, allora confronta i due in base ad esso.
     * Altrimenti effettua l'ordinamento in base a cognome-nome oppure nome-nome.
     * 
     * @param[in] o1 Il primo contatto da confrontare.
     * @param[in] o2 Il secondo contatto da confrontare.
     * 
     * @return cmp Un intero negativo, zero o positivo che indica se il primo argomento del confronto è minore, uguale o maggiore del secondo.
     */
    @Override
    public int compare(Contact o1, Contact o2) {
        // se è inserito solo il cognome, usa il nome per il confronto
        String surname1;
        if (o1.getSurname() == null || o1.getSurname().isEmpty()) {
            surname1 = o1.getName();
        } else {
            surname1 = o1.getSurname();
        }

        // di nuovo, sceglie se usare nome o cognome per il secondo contatto
        String surname2;
        if (o2.getSurname() == null || o2.getSurname().isEmpty()) {
            surname2 = o2.getName();
        } else {
            surname2 = o2.getSurname();
        }

        // confronta i cognomi se possibile, altrimenti i nomi (case insensitive)
        int cmp = surname1.compareToIgnoreCase(surname2);
        if (cmp != 0) {
            return cmp;
        }

        // se i cognomi sono uguali, confronta i nomi (case insensitive)
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}