package it.cianciustyles.addressbook.view;

import it.cianciustyles.addressbook.model.AddressBookTableModel;
import it.cianciustyles.addressbook.model.Contact;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewEditContactFrame extends JFrame {
    private AddressBookTableModel tableModel;
    private int contactIndex;

    private JTextField firstNameText;
    private JTextField lastNameText;
    private JTextField telephoneNumberText;
    private JTextField addressText;
    private JTextField ageText;

    private enum MODE{NEW_CONTACT, EDIT_CONTACT}
    private MODE mode;


    public NewEditContactFrame(AddressBookTableModel tm, Contact p, int ci) {
        tableModel = tm;
        contactIndex = ci;
        mode = p == null ? MODE.NEW_CONTACT : MODE.EDIT_CONTACT;

        setTitle(mode == MODE.NEW_CONTACT ? "New Contact" : "Edit Contact");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Container pane = getContentPane();
        BoxLayout boxLayout = new BoxLayout(pane, BoxLayout.PAGE_AXIS);
        pane.setLayout(boxLayout);

        addContactInfo(pane, p);
        addButtons(pane);

        setLocationRelativeTo(null);
    }

    private void addButtons(Container pane) {
        JPanel controlsPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        controlsPanel.setLayout(flowLayout);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();
            String address = addressText.getText();
            String telephoneNumber = telephoneNumberText.getText();
            String age = ageText.getText();

            if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || telephoneNumber.isEmpty() || age.isEmpty())
                return;

            Contact newContact = new Contact(firstName, lastName, address, telephoneNumber, Integer.parseInt(age));
            if (mode == MODE.NEW_CONTACT) {
                tableModel.addContact(newContact);
            } else if (mode == MODE.EDIT_CONTACT) {
                tableModel.editContact(newContact, contactIndex);
            }

            setVisible(false);
            dispose();
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        controlsPanel.add(saveButton);
        controlsPanel.add(cancelButton);
        pane.add(controlsPanel, BorderLayout.SOUTH);
    }

    public void addContactInfo(Container pane, Contact p) {
        JPanel contactInfoPanel = new JPanel();
        contactInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridLayout gridLayout = new GridLayout(5, 2);
        contactInfoPanel.setLayout(gridLayout);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameText = new JTextField();
        if (p != null)
            firstNameText.setText(p.getFirstName());

        contactInfoPanel.add(firstNameLabel);
        contactInfoPanel.add(firstNameText);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameText = new JTextField();
        if (p != null) {
            lastNameText.setText(p.getLastName());
        }

        contactInfoPanel.add(lastNameLabel);
        contactInfoPanel.add(lastNameText);

        JLabel telephoneNumberLabel = new JLabel("Telephone Number:");
        telephoneNumberText = new JTextField();
        if (p != null) {
            telephoneNumberText.setText(p.getTelephoneNumber());
        }

        contactInfoPanel.add(telephoneNumberLabel);
        contactInfoPanel.add(telephoneNumberText);

        JLabel addressLabel = new JLabel("Address:");
        addressText = new JTextField();
        if (p != null) {
            addressText.setText(p.getAddress());
        }

        contactInfoPanel.add(addressLabel);
        contactInfoPanel.add(addressText);

        JLabel ageLabel = new JLabel("Età:");
        ageText = new JTextField();
        if (p != null) {
            ageText.setText(String.valueOf(p.getAge()));
        }

        contactInfoPanel.add(ageLabel);
        contactInfoPanel.add(ageText);

        pane.add(contactInfoPanel, BorderLayout.CENTER);
    }
}
