package it.cianciustyles.addressbook.view;

import it.cianciustyles.addressbook.model.AddressBookTableModel;
import it.cianciustyles.addressbook.model.Contact;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

public class AddressBookFrame extends JFrame {
    private AddressBookTableModel tableModel;
    private JTable addressBookTable;

    private final ActionListener newContactListener = e -> {
        NewEditContactFrame newContactFrame = new NewEditContactFrame(tableModel, null, -1);
        newContactFrame.setVisible(true);
    };

    private final ActionListener editContactListener = e -> {
        int rowSelected = addressBookTable.getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(this, "Please select a contact to be edited!", "Edit Contact...", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contact contact = tableModel.getContact(rowSelected);
        NewEditContactFrame editContactFrame = new NewEditContactFrame(tableModel, contact, addressBookTable.getSelectedRow());
        editContactFrame.setVisible(true);
    };

    private final ActionListener deleteContactListener = e -> {
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
    };

    private final ActionListener quitListener = e -> {
        setVisible(false);
        dispose();
        System.exit(0);
    };

    private final ActionListener aboutListener = e -> JOptionPane.showMessageDialog(this,
                                                                                    "AddressBook has been written by Stefano Cianciulli for the InfoSons hiring process.",
                                                                                    "About this program",
                                                                                    JOptionPane.INFORMATION_MESSAGE);

    public AddressBookFrame() {
        // Initialize the JFrame
        setTitle("AddressBook");
        setSize(480, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        BoxLayout boxLayout = new BoxLayout(pane, BoxLayout.PAGE_AXIS);
        pane.setLayout(boxLayout);

        // Add the JMenu to the JFrame
        addMenu(this);

        // Add the JTable to the JFrame
        addTable(pane);

        // Add the buttons to the JFrame
        addButtons(pane);
    }

    private void addButtons(Container pane) {
        JPanel controlsContainer = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        controlsContainer.setLayout(flowLayout);

        JButton newButton = new JButton("Add new contact...");
        newButton.addActionListener(newContactListener);

        JButton editButton = new JButton("Edit contact...");
        editButton.addActionListener(editContactListener);

        JButton deleteButton = new JButton("Delete contact");
        deleteButton.addActionListener(deleteContactListener);

        controlsContainer.add(newButton);
        controlsContainer.add(editButton);
        controlsContainer.add(deleteButton);

        pane.add(controlsContainer, BorderLayout.SOUTH);
    }

    private void addTable(Container pane) {
        JPanel tableContainer = new JPanel();
        tableModel = new AddressBookTableModel();
        addressBookTable = new JTable(tableModel);
        addressBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(addressBookTable);
        addressBookTable.setFillsViewportHeight(true);
        tableContainer.add(scrollPane);

        pane.add(tableContainer, BorderLayout.CENTER);
    }

    private void addMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // Create the Contacts Menu
        JMenu contactsMenu = new JMenu("Contacts");
        contactsMenu.setMnemonic(KeyEvent.VK_C);

        JMenuItem newContact = new JMenuItem("New Contact...");
        newContact.setMnemonic(KeyEvent.VK_N);
        newContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newContact.addActionListener(newContactListener);
        contactsMenu.add(newContact);

        JMenuItem editContact = new JMenuItem("Edit Selected Contact...");
        editContact.setMnemonic(KeyEvent.VK_E);
        editContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        editContact.addActionListener(editContactListener);
        contactsMenu.add(editContact);

        JMenuItem deleteContact = new JMenuItem("Delete Selected Contact");
        deleteContact.setMnemonic(KeyEvent.VK_D);
        deleteContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        deleteContact.addActionListener(deleteContactListener);
        contactsMenu.add(deleteContact);

        contactsMenu.add(new JSeparator());

        JMenuItem quit = new JMenuItem("Quit");
        quit.setMnemonic(KeyEvent.VK_Q);
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        quit.addActionListener(quitListener);
        contactsMenu.add(quit);

        menuBar.add(contactsMenu);

        // Create the ? Menu
        JMenu aboutMenu = new JMenu("?");
        JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        aboutItem.addActionListener(aboutListener);
        aboutMenu.add(aboutItem);
        menuBar.add(aboutMenu);

        frame.setJMenuBar(menuBar);
    }
}
