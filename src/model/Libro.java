package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class Libro {
	private String isbn;
	private String titolo;
	private String descrizione;
	private int prezzo;
	private String tipo;
	private int anno_pubblicazione;
	private int numero_pagine;
	private int numero_disponibili;
	private String autore;
	private String path;
	private ArrayList<Categoria> categorie;
	private String sdescrizione;

	public ArrayList<Categoria> getCategorie(){
		return categorie;
	}

	public void setCategorie(ArrayList<Categoria> c){
		categorie=c;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getSdescrizione() {
		return sdescrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
		if(descrizione.length() <=250) {
			sdescrizione = descrizione;
		}else {
			sdescrizione = descrizione.substring(0,250) + "...";
		}

	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public String getprezzoEuro() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		return nf.format(((float)prezzo/100));
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAnno_pubblicazione() {
		return anno_pubblicazione;
	}

	public void setAnno_pubblicazione(int anno_pubblicazione) {
		this.anno_pubblicazione = anno_pubblicazione;
	}

	public int getNumero_pagine() {
		return numero_pagine;
	}

	public void setNumero_pagine(int numero_pagine) {
		this.numero_pagine = numero_pagine;
	}

	public int getNumero_disponibili() {
		return numero_disponibili;
	}

	public void setNumero_disponibili(int numero_disponibili) {
		this.numero_disponibili = numero_disponibili;
	}

	@Override
	public String toString() {
		return "Libro{" +
				"isbn=" + isbn +
				", titolo='" + titolo + '\'' +
				", descrizione='" + descrizione + '\'' +
				", prezzo=" + prezzo +
				", tipo='" + tipo + '\'' +
				", anno_pubblicazione=" + anno_pubblicazione +
				", numero_pagine=" + numero_pagine +
				", numero_disponibili=" + numero_disponibili +
				", descrizione='" + descrizione + '\'' +
				", autore='" + autore + '\'' +
				", path='" + path + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Libro)) return false;
		Libro libro = (Libro) o;
		return getIsbn() == libro.getIsbn() &&
				getPrezzo() == libro.getPrezzo() &&
				getAnno_pubblicazione() == libro.getAnno_pubblicazione() &&
				getNumero_pagine() == libro.getNumero_pagine() &&
				getNumero_disponibili() == libro.getNumero_disponibili() &&
				Objects.equals(getTitolo(), libro.getTitolo()) &&
				Objects.equals(getDescrizione(), libro.getDescrizione()) &&
				Objects.equals(getTipo(), libro.getTipo()) &&
				Objects.equals(getDescrizione(), libro.getDescrizione()) &&
				Objects.equals(getAutore(), libro.getAutore()) &&
				Objects.equals(getPath(), libro.getPath());
	}
}
