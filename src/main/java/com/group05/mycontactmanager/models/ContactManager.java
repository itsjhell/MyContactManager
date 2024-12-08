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
public class ContactManager {
    private String name;
    private List<Contact> contactList;
    
    public ContactManager(String name) {
        this.name = name;
        this.contactList = new ArrayList<>(); 
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactList(List contactList) {
        this.contactList = contactList;
    }
    
    public String getName() {
        return name;
    }

    public List getContactList() {
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