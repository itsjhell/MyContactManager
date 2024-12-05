/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.utilities;

import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.ContactManager;
import javafx.stage.FileChooser;

/**
 *
 * @author group05
 */
public class FileManager {
    
    public static String chooseFile(String task, String extension) {
        FileChooser fileChooser = new FileChooser();
        return null;
    }
     
    public static void loadImage(Contact contact) {
    }

    public static void saveContactManager(ContactManager contactManager) {
    }
    
    public static ContactManager loadContactManager() {
        return null;
    }
    
    public static ContactManager importContactsFromCSV() {
        return null;
    } 
    
    public static void exportContactsToCSV(ContactManager contactManager) {       
    }

}
