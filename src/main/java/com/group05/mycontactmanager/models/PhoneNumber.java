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
public class PhoneNumber {
    private PhonePrefix prefix;
    private String number;

    // COSTRUTTORE
    public PhoneNumber(PhonePrefix prefix, String number) {
        this.prefix = prefix;
        this.number = number;
    }

    public PhonePrefix getPrefix() {
        return prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setPrefix(PhonePrefix prefix) {
        this.prefix = prefix;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getPrefix()+" "+number);
        return sb.toString();
    }
}