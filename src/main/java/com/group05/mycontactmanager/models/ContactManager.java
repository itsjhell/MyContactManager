/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author group05
 */
public class ContactManager {
    private StringProperty name;
    private ObservableList<Contact> contactList;
    
    public ContactManager(String name) {
        this.name = new SimpleStringProperty(name);
        this.contactList = FXCollections.observableArrayList(); 
    }
    
    public ObservableList getContactList() {
        return contactList;
    }
    
    public void addContact(Contact contact) {
    }
    
    public void removeContact(Contact contact) {
    }
    
    
    public ObservableList searchBySurnameAndName(StringProperty Surname) {
        return null;
    }
    
    public ObservableList searchByEmail(StringProperty email) {
        return null;
    }
  
    public ObservableList searchByPhoneNumber(StringProperty phone) {
        return null;
    }
    
    @Override
    public String toString() {
        return null;
    }
    
}