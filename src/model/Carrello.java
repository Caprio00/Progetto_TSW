package model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Carrello {
    private ArrayList<Libro> libro = new ArrayList<Libro>();
    private int totale =0;
    private int totprodotti =0;


    public void setLibro(Libro l){
        libro.add(l);
        totale = totale + l.getPrezzo();
        totprodotti = totprodotti +1;
    }

    public int getTotprodotti(){
        return totprodotti;
    }

    public ArrayList<Libro> getLibro(){
        return libro;
    }

    public String getTotale(){
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return nf.format(((float)totale/100));
    }
}
