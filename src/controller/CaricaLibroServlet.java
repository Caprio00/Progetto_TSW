package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;


@MultipartConfig
@WebServlet("/caricalibro")
public class CaricaLibroServlet extends HttpServlet {
    private static final String CARTELLA_UPLOAD = "img";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        if(user != null && user.isAdmin() ==  true){
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
                prezzoint = prezzoint * 100;
            }
        }catch (NumberFormatException er){
            errore = errore + "Il campo prezzo deve contenere solo numeri<br>";
        }
        if(numeroDisponibili != null && numeroDisponibili.length() == 0 && formato.equals("cartaceo")){
            errore = errore + "Il campo numero disponibili non può essere vuoto<br>";
        }
        if(numeroDisponibili == null){
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
            Date date =  new Date();
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            aPub = Integer.parseInt(annoPubblicazione);
            if(aPub > year){
                errore = errore + "L'anno inserito da te non é valido, l'anno deve essere inferiore alla date attuale<br>";
            }
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
        if(filePart.getSize() == 0 && edit == null){
            errore = errore + "Non hai inserito alcuna copertina<br>";
        }else if(edit == null && filePart.getContentType().endsWith("jpg") == false && filePart.getContentType().endsWith("jpeg") == false && filePart.getContentType().endsWith("png") == false){
            errore = errore + "La copertina non ha un estensione valida<br>";
        }

        if(errore.length() > 0) {
                throw new MyServletException("Sono stati trovati i seguenti errori:<br><br>" + errore);
        }

        if(filePart.getSize() != 0){
            if(edit != null){
                String destinazione = CARTELLA_UPLOAD + File.separator + l.getPath();
                String pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione)).toString();
                File file = new File(pathDestinazione);
                if(file.exists() == true && file.delete() == false)
                {
                    throw new MyServletException("Errore durante la cancellazione");
                }
            }
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
            Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
            for (int i = 2; Files.exists(pathDestinazione); i++) {
                fileName = i + "_" + fileName;
                destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
            }
            InputStream fileInputStream = filePart.getInputStream();
            Files.createDirectories(pathDestinazione.getParent());
            Files.copy(fileInputStream, pathDestinazione);
            l.setPath(fileName);
        }

        if(edit == null) {
            ldao.doSave(l);
        }else if(edit !=null){
            ldao.doUpdate(l);
        }
        response.sendRedirect("libro?id="+ l.getIsbn());
    } else{
        throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
    }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {throw new MyServletException("Metodo non permesso");}

}
