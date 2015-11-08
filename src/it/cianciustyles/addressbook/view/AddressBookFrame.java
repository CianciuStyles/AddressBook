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
            Contact contact = tableModel.getContact(rowSelected);

            NewEditContactFrame editContactFrame = new NewEditContactFrame(tableModel, contact, addressBookTable.getSelectedRow());
            editContactFrame.setVisible(true);
        });

        JButton deleteButton = new JButton("Delete contact");

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
