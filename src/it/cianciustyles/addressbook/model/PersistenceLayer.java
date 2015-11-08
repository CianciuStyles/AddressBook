package it.cianciustyles.addressbook.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

public class PersistenceLayer {
    public static Vector<Contact> loadContacts() {
        Vector<Contact> contacts = new Vector<>();

        File contactsFile = new File("contacts.txt");
        try {
            Scanner scanner = new Scanner(contactsFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] contactInfo = line.split(";");
                Contact contact = new Contact(contactInfo[0], contactInfo[1], contactInfo[2], contactInfo[3], Integer.parseInt(contactInfo[4]));
                contacts.add(contact);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No file contacts.txt found. Starting with an empty AddressBook.");
        }

        return contacts;
    }

    public static void saveContacts(Vector<Contact> contacts) {
        File contactsFile = new File("contacts.txt");
        try {
            PrintStream ps = new PrintStream(contactsFile);
            for (Contact contact : contacts) {
                ps.println(contact.toSaveFormat());
            }

            ps.close();
        } catch (FileNotFoundException e) {
            System.err.println("Some error occurred while writing the contacts.txt file");
        }
    }
}
