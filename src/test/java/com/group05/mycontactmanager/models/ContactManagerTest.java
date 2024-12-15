
package com.group05.mycontactmanager.models;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Classe di test per ContactManager
 * @author group05
 * @date Dicembre 13,2024
 * @version 1.0
 */
public class ContactManagerTest {
    
    private ContactManager manager;
    private Contact c1;
    private Contact c2;
    
    @BeforeAll
    public static void setUpClass() {
        // Eseguito una sola volta prima di tutti i test
    }
    
    @AfterAll
    public static void tearDownClass() {
        // Eseguito una sola volta dopo tutti i test
    }
    
    @BeforeEach
    public void setUp() {
        manager = new ContactManager("MyContacts");
        List<PhoneNumber> nums = new ArrayList<>();
        nums.add(new PhoneNumber(PhonePrefix.ITALY, "123456"));
        c1 = new Contact("Mario", "Rossi", nums, new ArrayList<>(), "profile1.png", "Note1");
        c2 = new Contact("Anna", "Bianchi", new ArrayList<>(), new ArrayList<>(), "profile2.png", "Note2");
    }
    
    @AfterEach
    public void tearDown() {
        // Pulizia se necessario
    }

    @Test
    public void testGetName() {
        assertEquals("MyContacts", manager.getName(), "Il nome dovrebbe essere MyContacts");
    }
    
    @Test
    public void testSetName() {
        manager.setName("NewName");
        assertEquals("NewName", manager.getName(), "Il nome dovrebbe essere cambiato in NewName");
    }
    
    @Test
    public void testSetContactList() {
        List<Contact> newList = new ArrayList<>();
        newList.add(c1);
        manager.setContactList(newList);
        assertEquals(1, manager.getContactList().size(), "La lista dovrebbe contenere 1 contatto");
    }
}