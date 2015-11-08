package it.cianciustyles.addressbook.model;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class AddressBookTableModel extends AbstractTableModel {
    private final Vector<String> columnNames;
    private final Vector<Contact> contacts;

    private static final int FIRST_NAME_INDEX = 0;
    private static final int LAST_NAME_INDEX = 1;
    private static final int TELEPHONE_NUMBER_INDEX = 2;

    public AddressBookTableModel() {
        columnNames = new Vector<>();
        columnNames.add("First Name");
        columnNames.add("Last Name");
        columnNames.add("Telephone Number");

        contacts = PersistenceLayer.loadContacts();
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact currentContact = contacts.get(rowIndex);

        switch (columnIndex) {
            case FIRST_NAME_INDEX:
                return currentContact.getFirstName();
            case LAST_NAME_INDEX:
                return currentContact.getLastName();
            case TELEPHONE_NUMBER_INDEX:
                return currentContact.getTelephoneNumber();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Class getColumnClass(int column) {
        return String.class;
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void addContact(Contact p) {
        contacts.add(p);
        fireTableRowsInserted(contacts.size() - 1, contacts.size() - 1);
        PersistenceLayer.saveContacts(contacts);
    }

    public void editContact(Contact p, int index) {
        contacts.set(index, p);
        fireTableRowsUpdated(index, index);
        PersistenceLayer.saveContacts(contacts);
    }

    public void deleteContact(int index) {
        contacts.remove(index);
        fireTableRowsDeleted(index, index);
        PersistenceLayer.saveContacts(contacts);
    }
}
