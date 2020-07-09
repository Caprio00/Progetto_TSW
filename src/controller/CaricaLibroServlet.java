package controller;

import model.Categoria;
import model.CategoriaDAO;
import model.Libro;
import model.LibroDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;


@MultipartConfig
@WebServlet("/caricalibro")
public class CaricaLibroServlet extends HttpServlet {
    private static final String CARTELLA_UPLOAD = "img";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titolo = request.getParameter("titolo");
        String autore = request.getParameter("autore");
        String numeroPagine = request.getParameter("npage");
        String numeroDisponibili = request.getParameter("ndisp");
        String isbn = request.getParameter("isbn");
        String annoPubblicazione = request.getParameter("anno");
        String formato = request.getParameter("tipo");
        String descrizione = request.getParameter("descrizione");
        String prezzo = request.getParameter("prezzo");
        String edit = request.getParameter("edit");
        if(isbn == null){
            isbn = edit;
        }
        String errore = "";
        int nPagine =-1 ,nDisponibili = -1,aPub= -1;
        int prezzoint = -1;
        LibroDAO ldao = new LibroDAO();

        if(titolo.length() == 0){
            errore = errore + "Il campo titolo non può essere vuoto<br>";
        }
        if(autore.length() == 0){
            errore = errore + "Il campo autore non può essere vuoto<br>";
        }
        if(numeroPagine.length() == 0){
            errore = errore + "Il campo numero delle pagine non può essere vuoto<br>";
        }
        try {
            nPagine = Integer.parseInt(numeroPagine);
        }catch (NumberFormatException er){
            errore = errore + "Il campo numero delle pagine deve contenere solo numeri<br>";
        }
        if(prezzo.length() == 0){
            errore = errore + "Il campo prezzo non può essere vuoto<br>";
        }
        try {
            prezzo = prezzo.replace("-","");
            String[] array = new String[0];
            if(prezzo.contains(",") || prezzo.contains(".") ){
                if(prezzo.contains(",")) {
                    array = prezzo.split(",");
                }else if(prezzo.contains(".")){
                    array = prezzo.split(Pattern.quote("."));
                }
                if(array.length == 2){
                    try{
                        prezzoint = Integer.parseInt(array[0] + array[1].substring(0,2));
                    }catch (StringIndexOutOfBoundsException er){
                        prezzoint = Integer.parseInt(array[0] + array[1]);
                    }

                }else{
                    errore = errore + "Il prezzo non é valido";
                }
            }else{
                prezzoint = Integer.parseInt(prezzo);
            }
        }catch (NumberFormatException er){
            errore = errore + "Il campo prezzo deve contenere solo numeri<br>";
        }
        if(numeroDisponibili != null && numeroDisponibili.length() == 0 && formato.equals("cartaceo")){
            errore = errore + "Il campo numero disponibili non può essere vuoto<br>";
        }
        if(numeroDisponibili == null && formato.equals("ebook")){
            numeroDisponibili = "0";
        }
        if(numeroDisponibili != null){
        try {
            nDisponibili = Integer.parseInt(numeroDisponibili);
        }catch (NumberFormatException er){
            errore = errore + "Il campo numero disponibili deve contenere solo numeri<br>";
        }
        }
        if(isbn.length() == 0){
            errore = errore + "Il campo isbn non può essere vuoto<br>";
        }

        if(edit != null && edit.equals(isbn) == false){
            errore = errore + "Il campo isbn non può essere modificato<br>";
        }
        if(isbn.matches("/^[0-9]{13}$/g")){
            errore = errore + "Il campo isbn deve contenere solo numeri e deve essere lungo 13 caratteri<br>";
        }

        if(ldao.doRetrieveByIsbn(isbn) != null && edit == null){
            errore = errore + "Esiste già un libro con questo isbn<br>";
        }
        if(annoPubblicazione.length() ==0){
            errore = errore + "Il campo anno pubblicazioni non può essere vuoto<br>";
        }
        try {
            aPub = Integer.parseInt(annoPubblicazione);
        } catch (NumberFormatException er){
            errore = errore + "Il campo anno pubblicazione deve contenere solo numeri<br>";
        }
        if(edit == null && formato != null && formato.length() == 0){
            errore = errore + "Il campo formato non é valido<br>";
        }
        if(descrizione.length() ==0){
            errore = errore + "Il campo descrizione non può essere vuoto<br>";
        }
        CategoriaDAO cdao = new CategoriaDAO();
        List<Categoria> listCat = cdao.doRetrieveAll();
        ArrayList<Categoria> listCatform = new ArrayList<Categoria>();
        int id=-1;
        for(int i =0;i<listCat.size();i++){
            try {
                String f = request.getParameter(String.valueOf(listCat.get(i).getId()));
                if(f!=null) id = Integer.parseInt(f);
            }catch (NumberFormatException er){
                errore = errore + "Il campo categoria non é valido<br>";
                break;
            }
            if(listCat.get(i).getId() == id){
                listCatform.add(listCat.get(i));
            }
        }
        Libro l = null;
        if(edit != null){
            l= ldao.doRetrieveByIsbn(isbn);
        }else{
            l = new Libro();
            l.setTipo(formato);
            l.setIsbn(isbn);
        }
        l.setTitolo(titolo);
        l.setAutore(autore);
        l.setNumero_disponibili(nDisponibili);
        l.setNumero_pagine(nPagine);
        l.setAnno_pubblicazione(aPub);
        l.setDescrizione(descrizione);
        l.setPrezzo(prezzoint);
        l.setCategorie(listCatform);

        Part filePart = request.getPart("img");
        if(filePart.getSize() == 0 ){
            if(edit == null){
                errore = errore + "L'immagine non é stata caricata";
                    throw new MyServletException(errore);
            }
        }else{
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
        Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
        for (int i = 2; Files.exists(pathDestinazione); i++) {
            destinazione = CARTELLA_UPLOAD + File.separator + i + "_" + fileName;
            pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
        }
        InputStream fileInputStream = filePart.getInputStream();
        Files.createDirectories(pathDestinazione.getParent());
        Files.copy(fileInputStream, pathDestinazione);
        l.setPath(fileName);
        }
        if(errore.length() > 0) {
            throw new MyServletException(errore);
        }else if(edit == null) {
            ldao.doSave(l);
        }else if(edit !=null){
            ldao.doUpdate(l);
        }
        response.sendRedirect("libro?id="+ l.getIsbn());
    }
}
