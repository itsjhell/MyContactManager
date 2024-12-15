
package com.group05.mycontactmanager.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *@brief Classe di test per PhoneNumber
 * 
 * @author group05
 * @date Dicembre 13,2024
 * @version 1.0
 */
public class PhoneNumberTest {
    
    private PhoneNumber pn;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        pn = new PhoneNumber(PhonePrefix.ITALY, "123456789");
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetPrefix() {
        assertEquals(PhonePrefix.ITALY, pn.getPrefix(), "Il prefisso dovrebbe essere ITALY");
    }
    
    @Test
    public void testSetPrefix() {
        pn.setPrefix(PhonePrefix.USA);
        assertEquals(PhonePrefix.USA, pn.getPrefix(), "Il prefisso dovrebbe essere cambiato in USA");
    }
    
    @Test
    public void testGetNumber() {
        assertEquals("123456789", pn.getNumber(), "Il numero dovrebbe essere 123456789");
    }
    
    @Test
    public void testSetNumber() {
        pn.setNumber("987654321");
        assertEquals("987654321", pn.getNumber(), "Il numero dovrebbe essere cambiato in 987654321");
    }
    
    @Test
    public void testToString() {
        String str = pn.toString();
        assertTrue(str.contains("+39"), "La stringa dovrebbe contenere '+39'");
        assertTrue(str.contains("987654321") || str.contains("123456789"), "La stringa dovrebbe contenere il numero");
    }
}