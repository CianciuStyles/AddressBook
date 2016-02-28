package it.cianciustyles.addressbook.model;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String telephoneNumber;
    private final int age;

    public Contact(String fn, String ln, String ad, String tn, int ag) {
        this.firstName = fn;
        this.lastName = ln;
        this.address = ad;
        this.telephoneNumber = tn;
        this.age = ag;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("First Name: %s, Last Name: %s, Telephone: %s", firstName, lastName, telephoneNumber);
    }

    public String toSaveFormat() {
        return String.format("%s;%s;%s;%s;%d", firstName, lastName, address, telephoneNumber, age);
    }
}
