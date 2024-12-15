package com.group05.mycontactmanager.models;

import com.group05.mycontactmanager.utilities.FileManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @brief Classe per la gestione di una lista di contatti.
 * 
 * La classe raggruppa funzioni che permettono la manipolazione di una lista di contatti;
 * sono presenti funzioni di aggiunta e rimozione dei contatti.

 * @author group05
 * @date Dicembre 08, 2024
 * @version 1.1
 */
public class ContactManager implements Serializable, FileManager{
    private String name;
    private List<Contact> contactList;
    
    /**
     * @brief Crea un nuovo ContactManager con un dato nome.
     *
     * @param[in] name Il nome associato alla lista di contatti.
     */
    public ContactManager(String name) {
        this.name = name;
        this.contactList = new ArrayList<>(); 
    }

    /**
     * @brief Imposta il nome della lista di contatti.
     *
     * @param[in] name Il nuovo nome.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief Imposta la lista di contatti.
     *
     * @param[in] contactList Una lista di oggetti Contact.
     */
    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
    
    /**
     * @brief Restituisce il nome della lista di contatti.
     *
     * @return Il nome del contatto.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Restituisce la lista di contatti.
     *
     * @return La lista di Contact.
     */
    public List<Contact> getContactList() {
        return contactList;
    }
      
    /**
     * @brief Restituisce una rappresentazione testuale del ContactManager.
     *
     * @return Una stringa descrittiva .
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Nome rubrica: ").append(name).append("[\n");
        for(Contact c: contactList) {
            sb.append(c).append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * @brief Importa una lista di contatti da file CSV.
     *
     * @param[in] nameFile file CSV da importare.
     */
    @Override
    public void importContactsFromCSV(String nameFile) {
        try (Scanner i = new Scanner(new BufferedReader(new FileReader(nameFile)))) {
            i.useDelimiter(";");
            contactList = new ArrayList<>();
            while (i.hasNextLine()) {
                String line = i.nextLine().trim();
                //System.out.println("HO LETTO LA RIGA: " + line);
                // Gestisci righe vuote o malformattate
                if (line.isEmpty())
                    continue;
                
                List<PhoneNumber> numbers = new ArrayList<>();
                List<String> emailAddresses = new ArrayList<>();
                String[] fields = line.trim().split(";");
                
                // Verifica che ci siano abbastanza campi
                if (fields.length < 10) {
                    System.out.println("Riga formattata male e ignorata: " + line);
                    continue;
                }

                // Componimento degli array numbers
                for (int j = 2; j < 5; j++) {
                    if (!fields[j].equals("- ")){
                        String[] phoneNumberFields = fields[j].trim().split(" ");
                        try {
                            PhonePrefix prefix = PhonePrefix.fromString(phoneNumberFields[0]);
                            String number = phoneNumberFields[1];
                            numbers.add(new PhoneNumber(prefix, number));
                        } catch (Exception e) {
                            System.out.println("Errore nel parsing del numero di telefono: " + fields[j]);
                        }
                    } else {
                        numbers.add(new PhoneNumber(PhonePrefix.fromString(fields[j]), ""));
                    }
                }

                // Componimento degli array emailAddresses
                for (int j = 5; j < 8; j++) {
                    emailAddresses.add(fields[j]);
                }

                // Creazione del contatto
                try {
                    //System.out.println("Sto provado a creare il contatto: " + fields[0] + fields[1] + numbers + emailAddresses + fields[8] + fields[9]);
                    this.contactList.add(new Contact(
                        fields[0], // Nome
                        fields[1], // Cognome
                        numbers, 
                        emailAddresses, 
                        fields[8], // NomeImmagine
                        fields[9]  // Note
                    ));
                    //System.out.println("OK sono riuscito a caricare: " + fields[0] + " " + fields[1]);
                } catch (Exception e) {
                    System.out.println("Errore nella creazione del contatto: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
    }
    
    /**
     * @brief Esporta una lista di contatti da file CSV.
     *
     * Stampa i seguenti dati: nome, cognome, pref1 numero1, pref2 numero2,
     * pref3 numero3, email1, email2, email3, imagePath, note. 
     *
     * @param[in] nameFile file CSV da esportare.
     */
    @Override
    public void exportContactsToCSV(String nameFile) {
        try(PrintWriter o = new PrintWriter( new BufferedWriter( new FileWriter(nameFile)))) {
            for (Contact c : contactList) {
                //nome;cognome;
                o.print(c.getName()+";"+c.getSurname()+";");
                //number1;number2;number3;
                if(c.getNumbers() != null) {
                    for(PhoneNumber pn: c.getNumbers()) {
                        if(pn != null && pn.getPrefix() != null) {
                            o.print(pn.toString()+";");
                        }
                    }
                } else {
                   o.print("-;-;-;");
                }
                //email1;email2;email3;
                if(c.getEmailAddresses() != null) {
                    for(String email: c.getEmailAddresses()) {
                        if(email != null)
                            o.print(email.toString());
                        o.print(";");
                    }
                } else {
                    o.print(";;;");
                }
                //imagePath;notes;
                o.print(c.getImageName()+";");
                o.print(c.getNotes()+" ;\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * @brief Scrive su un file binario l'istanza della classe ContactManager in questione.
     *
     * @param[in] nameFile file binario da scrivere.
     */
    public void writeObject(String nameFile) {
        try(ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nameFile)))){
            o.writeObject(this);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * @brief Legge su un file binario l'istanza della classe ContactManager in questione.
     *
     * @param[in] nameFile file binario da leggere.
     */
    public void readObject(String nameFile) {
        
        try(ObjectInputStream o = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nameFile))) ) {
            Object obj = o.readObject();
            if (obj instanceof ContactManager) {
                // Copia i campi da `loaded` a `this`
                ContactManager loadedContactManager = (ContactManager) obj;
                this.contactList = loadedContactManager.getContactList();
                this.name = loadedContactManager.getName();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
