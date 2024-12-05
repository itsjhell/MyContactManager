/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.models;

/**
 *
 * @author group05
 */
public enum PhonePrefix {
    OTHERS("-"),
    USA("+1"),
    FRANCE("+33"),
    ITALY("+39"),
    UK("+44"),
    GERMANY("+49"),
    PORTUGAL("+351");

    private final String prefix;

    PhonePrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return prefix; 
    }
    
}