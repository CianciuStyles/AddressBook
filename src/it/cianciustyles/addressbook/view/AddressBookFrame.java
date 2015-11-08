package it.cianciustyles.addressbook.view;

import it.cianciustyles.addressbook.model.AddressBookTableModel;
import it.cianciustyles.addressbook.model.Contact;

import javax.swing.*;
import java.awt.*;

public class AddressBookFrame extends JFrame {
    AddressBookTableModel tableModel;
    JTable addressBookTable;

    public AddressBookFrame() {
        // Initialize the JFrame
        setTitle("AddressBook by CianciuStyles");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        BoxLayout boxLayout = new BoxLayout(pane, BoxLayout.PAGE_AXIS);
        pane.setLayout(boxLayout);

        // Add the JTable to the JFrame
        addTable(pane);

        // Add the buttons to the JFrame
        addButtons(pane);
    }

    public void addButtons(Container pane) {
        JPanel controlsContainer = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        controlsContainer.setLayout(flowLayout);

        JButton newButton = new JButton("Add new contact...");
        newButton.addActionListener(e -> {
            NewEditContactFrame newContactFrame = new NewEditContactFrame(tableModel, null, -1);
            newContactFrame.setVisible(true);
        });

        JButton editButton = new JButton("Edit contact...");
        editButton.addActionListener(e -> {
            int rowSelected = addressBookTable.getSelectedRow();
            if (rowSelected == -1) {
                JOptionPane.showMessageDialog(this, "Please select a contact to be edited!", "Edit Contact...", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contact contact = tableModel.getContact(rowSelected);
            NewEditContactFrame editContactFrame = new NewEditContactFrame(tableModel, contact, addressBookTable.getSelectedRow());
            editContactFrame.setVisible(true);
        });

        JButton deleteButton = new JButton("Delete contact");
        deleteButton.addActionListener(e -> {
            int rowSelected = addressBookTable.getSelectedRow();
            if (rowSelected == -1) {
                JOptionPane.showMessageDialog(this, "Please select a contact to be deleted!", "Delete Contact...", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contact selectedContact = tableModel.getContact(rowSelected);
            String firstName = selectedContact.getFirstName();
            String lastName = selectedContact.getLastName();
            int result = JOptionPane.showConfirmDialog(this, String.format("Are you you want to delete %s %s?", firstName, lastName), "Delete Contact...", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
                tableModel.deleteContact(rowSelected);
        });

        controlsContainer.add(newButton);
        controlsContainer.add(editButton);
        controlsContainer.add(deleteButton);

        pane.add(controlsContainer, BorderLayout.SOUTH);
    }

    public void addTable(Container pane) {
        JPanel tableContainer = new JPanel();
        tableModel = new AddressBookTableModel();
        addressBookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(addressBookTable);
        addressBookTable.setFillsViewportHeight(true);
        tableContainer.add(scrollPane);

        pane.add(tableContainer, BorderLayout.CENTER);
    }
}
