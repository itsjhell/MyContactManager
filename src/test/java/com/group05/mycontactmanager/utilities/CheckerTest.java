
package com.group05.mycontactmanager.utilities;

import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @file CheckerTest.java
 * @brief Classe di test per Checker
 * 
 * Verifica la validazione di email e numeri di telefono.
 * 
 * @author group05
 * @date Dicembre 13,2024
 * @version 1.0
 * 
 */
public class CheckerTest {
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCheckEmail() {
        assertTrue(Checker.checkEmail("test@example.com"), "L'email test@example.com dovrebbe essere valida");
        assertFalse(Checker.checkEmail("notAnEmail"), "La stringa notAnEmail non è una email valida");
        assertTrue(Checker.checkEmail(""), "Campo email vuoto considerato valido secondo il codice");
    }
    
    @Test
    public void testCheckNumber() {
        PhoneNumber pn = new PhoneNumber(PhonePrefix.ITALY, "1234567");
        assertTrue(Checker.checkNumber(pn), "Questo numero italiano dovrebbe essere valido");
        
        PhoneNumber invalidPn = new PhoneNumber(PhonePrefix.USA, "abc"); // non numerico
        assertFalse(Checker.checkNumber(invalidPn), "Numero non numerico non dovrebbe essere valido");
        
        PhoneNumber emptyPn = new PhoneNumber(PhonePrefix.UK, "");
        assertTrue(Checker.checkNumber(emptyPn), "Campo vuoto considerato valido");
    }
}
