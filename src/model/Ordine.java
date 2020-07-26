package model;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Ordine {

    public String getData(){
        long unixSeconds = Long.parseLong(oraordine);
        Date date = new java.util.Date(unixSeconds*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public String getOra(){
        long unixSeconds = Long.parseLong(oraordine);
        Date date = new java.util.Date(unixSeconds*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public String getOraordine() {
        return oraordine;
    }

    public void setOraordine(String oraordine) {
        this.oraordine = oraordine;
    }

    public ArrayList<Libro> getLibri() {
        return libri;
    }

    public void setLibri(ArrayList<Libro> libri) {
        this.libri = libri;
    }

    public void addLibro(Libro libro) {
       libri.add(libro);
    }

    public int getTotale() {
        return totale;
    }

    public String getTotaleEuro(){
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return nf.format(((float)totale/100));
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String convertiEuro(int prezzo){
        int x = prezzo/100;
        int y = prezzo%100;
        if(y == 0){
            return  x + ",00";
        }
        return  x + "," + y;
    }

    public String getNomeutente() {
        return nomeutente;
    }

    public void setNomeutente(String nomeutente) {
        this.nomeutente = nomeutente;
    }

    public int getIdutente() {
        return idutente;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    private String oraordine;
    private String nomeutente;
    private int idutente;
    private ArrayList<Libro> libri = new ArrayList<Libro>();
    private int totale;
    private int quantita;
}

