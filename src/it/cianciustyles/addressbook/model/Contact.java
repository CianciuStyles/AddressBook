package it.cianciustyles.addressbook.model;

public class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String telephoneNumber;
    private int age;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "First Name: " + getFirstName() +
                ", Last Name: " + getLastName() +
                ", Telephone Number: " + getTelephoneNumber() +
                ", Address: " + getAddress() +
                ", Age: " + getAge();
    }
}
