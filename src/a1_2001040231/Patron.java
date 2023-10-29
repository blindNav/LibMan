package a1_2001040231;

import common.PatronType;

import java.util.Date;

public class Patron {
    private static int idCounter = 1;
    private String patronId;
    private String name;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private PatronType patronType;

    public Patron(String name, Date dateOfBirth, String email, String phoneNumber, PatronType patronType) {
        this.patronId = generatePatronID();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
        idCounter++;
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PatronType getPatronType() {
        return patronType;
    }

    public void setPatronType(PatronType patronType) {
        this.patronType = patronType;
    }

    public String generatePatronID() {
        return "P" + String.format("%03d", idCounter);
    }
}
