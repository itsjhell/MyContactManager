/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author group05
 */
public class ContactTest {
    
    private Contact instance;
    
    @BeforeAll
    public static void setUpClass() {
        // Eseguito prima di tutti i test (una sola volta)
    }
    
    @AfterAll
    public static void tearDownClass() {
        // Eseguito dopo tutti i test (una sola volta)
    }
    
    @BeforeEach
    public void setUp() {
        // Inizializziamo un Contact con alcuni valori
        List<PhoneNumber> numbers = new ArrayList<>();
        numbers.add(new PhoneNumber(PhonePrefix.ITALY, "123456789"));
        
        List<String> emails = new ArrayList<>();
        emails.add("test@example.com");
        
        instance = new Contact("Mario", "Rossi", numbers, emails, "profile1.png", "Note di prova");
    }
    
    @AfterEach
    public void tearDown() {
        // Pulizia dopo ciascun test, se necessario
    }

    /**
     * Test del metodo getName di Contact.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Mario";
        String result = instance.getName();
        assertEquals(expResult, result, "Il nome dovrebbe essere Mario");
    }

    /**
     * Test del metodo setName di Contact.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Luigi";
        instance.setName(name);
        assertEquals(name, instance.getName(), "Il nome dovrebbe essere Luigi");
    }

    /**
     * Test del metodo getSurname di Contact.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        String expResult = "Rossi";
        String result = instance.getSurname();
        assertEquals(expResult, result, "Il cognome dovrebbe essere Rossi");
    }

    /**
     * Test del metodo setSurname di Contact.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String surname = "Verdi";
        instance.setSurname(surname);
        assertEquals(surname, instance.getSurname(), "Il cognome dovrebbe essere Verdi");
    }

    /**
     * Test del metodo getNumbers di Contact.
     */
    @Test
    public void testGetNumbers() {
        System.out.println("getNumbers");
        List<PhoneNumber> result = instance.getNumbers();
        assertNotNull(result, "La lista dei numeri non dovrebbe essere null");
        assertFalse(result.isEmpty(), "La lista dei numeri non dovrebbe essere vuota");
    }

    /**
     * Test del metodo getFirstNumber di Contact.
     */
    @Test
    public void testGetFirstNumber() {
        System.out.println("getFirstNumber");
        // Abbiamo impostato un primo numero: +39 123456789
        String result = instance.getFirstNumber();
        assertTrue(result.contains("+39"), "Il primo numero dovrebbe contenere il prefisso +39");
        assertTrue(result.contains("123456789"), "Il primo numero dovrebbe contenere 123456789");
    }

    /**
     * Test del metodo addNumber di Contact.
     */
    @Test
    public void testAddNumber() {
        System.out.println("addNumber");
        PhoneNumber number = new PhoneNumber(PhonePrefix.USA, "5551234");
        instance.addNumber(number);
        assertTrue(instance.getNumbers().contains(number), "La lista dovrebbe ora contenere il nuovo numero");
    }

    /**
     * Test del metodo removeNumber di Contact.
     */
    @Test
    public void testRemoveNumber() {
        System.out.println("removeNumber");
        PhoneNumber toRemove = instance.getNumbers().get(0);
        instance.removeNumber(toRemove);
        assertFalse(instance.getNumbers().contains(toRemove), "Il numero dovrebbe essere stato rimosso dalla lista");
    }

    /**
     * Test del metodo getEmailAddresses di Contact.
     */
    @Test
    public void testGetEmailAddresses() {
        System.out.println("getEmailAddresses");
        List<String> result = instance.getEmailAddresses();
        assertNotNull(result, "La lista delle email non dovrebbe essere null");
        assertFalse(result.isEmpty(), "La lista delle email non dovrebbe essere vuota");
    }

    /**
     * Test del metodo addEmail di Contact.
     */
    @Test
    public void testAddEmail() {
        System.out.println("addEmail");
        String email = "nuova@example.com";
        instance.addEmail(email);
        assertTrue(instance.getEmailAddresses().contains(email), "La lista dovrebbe contenere la nuova email");
    }

    /**
     * Test del metodo removeEmail di Contact.
     */
    @Test
    public void testRemoveEmail() {
        System.out.println("removeEmail");
        String existingEmail = "test@example.com";
        instance.removeEmail(existingEmail);
        assertFalse(instance.getEmailAddresses().contains(existingEmail), "L'email dovrebbe essere stata rimossa");
    }

    /**
     * Test del metodo getImagePath di Contact.
     */
    @Test
    public void testGetImagePath() {
        System.out.println("getImagePath");
        String result = instance.getImagePath();
        assertTrue(result.contains("profile1.png"), "Il percorso immagine dovrebbe contenere profile1.png");
        assertTrue(result.startsWith("images/"), "Il percorso immagine dovrebbe iniziare con images/");
    }

    /**
     * Test del metodo getImageName di Contact.
     */
    @Test
    public void testGetImageName() {
        System.out.println("getImageName");
        String expResult = "profile1.png";
        String result = instance.getImageName();
        assertEquals(expResult, result, "Il nome dell'immagine dovrebbe essere profile1.png");
    }

    /**
     * Test del metodo setImageName di Contact.
     */
    @Test
    public void testSetImageName() {
        System.out.println("setImageName");
        String imageName = "newImage.png";
        instance.setImageName(imageName);
        assertEquals("images/newImage.png".substring(7), instance.getImageName(), "Il nome dell'immagine dovrebbe essere newImage.png");
    }

    /**
     * Test del metodo getNotes di Contact.
     */
    @Test
    public void testGetNotes() {
        System.out.println("getNotes");
        String expResult = "Note di prova";
        String result = instance.getNotes();
        assertEquals(expResult, result, "Le note dovrebbero essere 'Note di prova'");
    }

    /**
     * Test del metodo setNotes di Contact.
     */
    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        String notes = "Nuove note";
        instance.setNotes(notes);
        assertEquals(notes, instance.getNotes(), "Le note dovrebbero essere 'Nuove note'");
    }

    /**
     * Test del metodo toString di Contact.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = instance.toString();
        assertTrue(result.contains("Mario"), "La stringa dovrebbe contenere il nome Mario");
        assertTrue(result.contains("Rossi"), "La stringa dovrebbe contenere il cognome Rossi");
        assertTrue(result.contains("profile1.png"), "La stringa dovrebbe contenere profile1.png");
        assertTrue(result.contains("Note di prova"), "La stringa dovrebbe contenere 'Note di prova'");
    }

    /**
     * Test del metodo clone di Contact.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Contact clone = instance.clone();
        assertNotNull(clone, "Il clone non dovrebbe essere null");
        assertEquals(instance.getName(), clone.getName(), "Il clone dovrebbe avere lo stesso nome");
        assertEquals(instance.getSurname(), clone.getSurname(), "Il clone dovrebbe avere lo stesso cognome");
        assertEquals(instance.getNumbers(), clone.getNumbers(), "Il clone dovrebbe avere la stessa lista di numeri");
        assertEquals(instance.getEmailAddresses(), clone.getEmailAddresses(), "Il clone dovrebbe avere la stessa lista di email");
        assertEquals(instance.getImageName(), clone.getImageName(), "Il clone dovrebbe avere la stessa immagine");
        assertEquals(instance.getNotes(), clone.getNotes(), "Il clone dovrebbe avere le stesse note");
    }
    
}