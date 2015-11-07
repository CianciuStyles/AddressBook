package it.cianciustyles.addressbook.model;

public class Persona {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private int eta;

    public Persona(String n, String c, String i, String t, int e) {
        this.nome = n;
        this.cognome = c;
        this.indirizzo = i;
        this.telefono = t;
        this.eta = e;
    }
}
