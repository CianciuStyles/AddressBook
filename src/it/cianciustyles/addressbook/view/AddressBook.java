package it.cianciustyles.addressbook.view;

import it.cianciustyles.addressbook.model.AddressBookTableModel;

import javax.swing.*;
import java.awt.*;

public class AddressBook extends JFrame {
    public AddressBook() {
        // Initialize the JFrame
        setTitle("AddressBook by CianciuStyles");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        BoxLayout boxLayout = new BoxLayout(pane, BoxLayout.PAGE_AXIS);
        pane.setLayout(boxLayout);

        // Add the JTable to the JFrame
        JPanel tableContainer = new JPanel();
        AddressBookTableModel tableModel = new AddressBookTableModel();
        JTable addressBookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(addressBookTable);
        addressBookTable.setFillsViewportHeight(true);
        tableContainer.add(scrollPane);

        pane.add(tableContainer, BorderLayout.CENTER);

        // Add the buttons to the JFrame
        JPanel controlsContainer = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        controlsContainer.setLayout(flowLayout);

        JButton newButton = new JButton("Add new contact...");
        JButton editButton = new JButton("Edit contact...");
        JButton deleteButton = new JButton("Delete contact");

        controlsContainer.add(newButton);
        controlsContainer.add(editButton);
        controlsContainer.add(deleteButton);

        pane.add(controlsContainer, BorderLayout.SOUTH);

        pack();
    }
}
