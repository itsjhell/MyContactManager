
package com.group05.mycontactmanager.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *@file PhonePrefixTest.java
 *@brief Classe di test per PhonePrefix
 * 
 * @author group05
 * @date Dicembre 13,2024
 * @version 1.0
 */
public class PhonePrefixTest {
    
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
    public void testFromString() {
        assertEquals(PhonePrefix.ITALY, PhonePrefix.fromString("+39"), "Dovrebbe riconoscere il prefisso italiano");
        assertEquals(PhonePrefix.OTHERS, PhonePrefix.fromString("+999"), "Un prefisso non noto dovrebbe restituire OTHERS");
    }
    
    @Test
    public void testToString() {
        assertEquals("+39", PhonePrefix.ITALY.toString(), "Il toString di ITALY dovrebbe essere +39");
    }
}