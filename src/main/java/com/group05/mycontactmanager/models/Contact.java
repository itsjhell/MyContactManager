package com.group05.mycontactmanager.models;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private String surname;
    private List<PhoneNumber> numbers;
    private List<String> emailAddresses;
    private String imagePath;
    private String notes;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.numbers = new ArrayList<>();
        this.emailAddresses = new ArrayList<>();
    }

    // Getter e setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<PhoneNumber> getNumbers() {
        return numbers;
    }

    public void addNumber(PhoneNumber number) {
        if (number != null && !numbers.contains(number)) {
            numbers.add(number);
        }
    }

    public void removeNumber(PhoneNumber number) {
        numbers.remove(number);
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void addEmail(String email) {
        if (email != null && !emailAddresses.contains(email)) {
            emailAddresses.add(email);
        }
    }

    public void removeEmail(String email) {
        emailAddresses.remove(email);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name);
        sb.append(" Surname: ").append(surname);
        sb.append(" Numbers: ").append(numbers);
        sb.append(" Emails: ").append(emailAddresses);
        sb.append(" Image Path: ").append(imagePath);
        sb.append(" Notes: ").append(notes);
        return sb.toString();
    }
}
