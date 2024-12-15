/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.utilities;

import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author group05
 */
public class ContactGenerator {
      public static List<Contact> generateRandomContacts(int numContacts) {
        Random random = new Random();
        List<Contact> contactList = new ArrayList<>();
        
        String[] names = {"Anna", "Marco", "Luca", "Giulia", "Matteo", "Francesca", "Alessandro", "Maria", "Carlo", "Elena"};
        String[] surnames = {"Rossi", "Bianchi", "Verdi", "Neri", "Gialli", "Marroni", "Bianchi", "Luca", "Ferrari", "Lombardi"};
        
        PhonePrefix[] prefixes = PhonePrefix.values(); 
        String[] phoneNumbers = {"1234567890", "0987654321", "1122334455", "6677889900", "9988776655"};
        String[] domains = {"@example.com", "@gmail.com", "@yahoo.com", "@outlook.com"};
        
        
        for (int i = 0; i < numContacts; i++) {
            String name = names[random.nextInt(names.length)];
            String surname = surnames[random.nextInt(surnames.length)];
            
            List<PhoneNumber> numbers = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int k = random.nextInt(2);
                if (k == 0) numbers.add(new PhoneNumber(""));
                else numbers.add(new PhoneNumber(prefixes[random.nextInt(prefixes.length)], phoneNumbers[random.nextInt(phoneNumbers.length)]));
            }
            
            List<String> emails = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int k = random.nextInt(2);
                if (k == 0) emails.add("");
                else emails.add(name.toLowerCase() + surname.toLowerCase() + random.nextInt(1000) + domains[random.nextInt(domains.length)]);
            }
            
            String imageName = "profile" + (random.nextInt(5) + 1) + ".png";
            
            String notes = "Note for " + name;
            
            contactList.add(new Contact(name, surname, numbers, emails, imageName, notes));
        }
        return contactList;
    }
}
