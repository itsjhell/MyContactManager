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

    public void setName(StringProperty name) {
        this.name = name;
    }

    public void setContactList(ObservableList<Contact> contactList) {
        this.contactList = contactList;
    }
    
    public StringProperty getName() {
        return name;
    }

    public ObservableList<Contact> getContactList() {
        return contactList;
    }
    
    public void addContact(Contact contact) {
    }
    
    public void removeContact(Contact contact) {
    }
      
    @Override
    public String toString() {
        return null;
    }
    
}