package model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Carrello {
    private ArrayList<Libro> libro = new ArrayList<Libro>();
    private int totale =0;
    private int totprodotti =0;

    public void setListlibro(ArrayList<Libro> libro) {
        this.libro = libro;
    }

    public void setLibro(Libro l){
        for (Libro lib: libro) {
            if(l.getIsbn().equals(lib.getIsbn())){
                lib.setQuantitaCarrello(lib.getQuantitaCarrello()+1);
                totale = totale + lib.getPrezzo();
                totprodotti = totprodotti +1;
                return;
            }
        }
        l.setQuantitaCarrello(1);
        libro.add(l);
        totale = totale + l.getPrezzo();
        totprodotti = totprodotti +1;
    }

    public void removeLibro(Libro l){
        for (int i=0; i<libro.size(); i++){
            if(libro.get(i).getIsbn().equals(l.getIsbn())){
                libro.remove(i);
                break;
            }
        }
    }

    public int getTotprodotti(){
        return totprodotti;
    }

    public ArrayList<Libro> getLibro(){
        return libro;
    }

    public void aggiornaTotProdotti(){
        totprodotti = 0;
        for (Libro l: libro) {
            totprodotti += l.getQuantitaCarrello();
        }
    }

    public int getTotale(){
        int sum = 0;
        for (Libro l: libro) {
            sum += (l.getPrezzo()*l.getQuantitaCarrello());
        }
        return sum;
    }

    public int getTotaleNetto(){
        return getTotale()-(getTotale()*22/100);
    }

    public int getIva(){
        return getTotale()-getTotaleNetto();
    }

    public int getCostoSpedizione(){
        if(getTotale()>10000)
            return 0;
        if(getTotale()>5000)
            return 750;
        else
            return 1500;
    }

    public int getTotaleLordo(){
        return getTotale()+getCostoSpedizione();
    }

    public String convertiEuro(int prezzo){
        int x = prezzo/100;
        int y = prezzo%100;
        if(y == 0){
            return  x + ",00";
        }
        return  x + "," + y;
    }

}
