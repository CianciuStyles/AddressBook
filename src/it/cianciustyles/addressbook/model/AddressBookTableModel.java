package it.cianciustyles.addressbook.model;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class AddressBookTableModel extends AbstractTableModel {
    private Vector<String> columnNames;
    private Vector<Persona> contacts;

    public static final int FIRST_NAME_INDEX = 0;
    public static final int LAST_NAME_INDEX = 1;
    public static final int TELEPHONE_NUMBER_INDEX = 2;

    public AddressBookTableModel() {
        columnNames = new Vector<>();
        columnNames.add("First Name");
        columnNames.add("Last Name");
        columnNames.add("Telephone Number");

        contacts = new Vector<>();
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
        Persona currentContact = contacts.get(rowIndex);

        switch (columnIndex) {
            case FIRST_NAME_INDEX:
                return currentContact.getNome();
            case LAST_NAME_INDEX:
                return currentContact.getCognome();
            case TELEPHONE_NUMBER_INDEX:
                return currentContact.getTelefono();
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
}
