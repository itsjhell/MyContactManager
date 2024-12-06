/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author group05
 */
public class Contact {
    private String name;
    private String surname;
    private final List<PhoneNumber> numbers;
    private final List<String> emailAddresses;
    private String imagePath;
    private String notes;
    
    //l'oggeto verrà istanziato inizialmente con i soli dati obbligatori
    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.numbers = new ArrayList<>();
        this.emailAddresses = new ArrayList<>();
        this.imagePath = "defaultpath/defaultimg.png";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void addNumber(PhoneNumber phoneNumber) {
   
    }
        
    public void addEmailAddress(String emailAddress) {
   
    }
    
    public void removeNumber(PhoneNumber phoneNumber) {
     
    }
        
    public void removeEmailAddress(String emailAddress) {
      
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(name + ";");
        sb.append(surname + ";");
        for(int i = 0; i < 3; i++) {
            if(i<numbers.size())
                sb.append(numbers.get(i));
            sb.append(";");
        }
        for(int i = 0; i < 3; i++) {
            if(i<emailAddresses.size())
                sb.append(emailAddresses.get(i));
            sb.append(";");
        }
        sb.append("\n");
        return sb.toString();
    }
}