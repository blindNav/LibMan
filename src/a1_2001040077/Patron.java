package a1_2001040077;

import common.PatronType;

public class Patron {
        private static int counter = 1;
        private String patronID;
        private String name;
        private String dob;
        private String email;
        private String phone;
        private PatronType patronType;

        public Patron(String name, String dob, String email, String phone, PatronType patronType) {
            this.patronID = generatePatronID();
            this.name = name;
            this.dob = dob;
            this.email = email;
            this.phone = phone;
            this.patronType = patronType;
        }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Patron.counter = counter;
    }

    public String getPatronID() {
        return patronID;
    }

    public void setPatronID(String patronID) {
        this.patronID = patronID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PatronType getPatronType() {
        return patronType;
    }

    public void setPatronType(PatronType patronType) {
        this.patronType = patronType;
    }

    public String generatePatronID() {
            String formattedId = String.format("P%03d", counter);
            counter++;
            return formattedId;
        }
}
