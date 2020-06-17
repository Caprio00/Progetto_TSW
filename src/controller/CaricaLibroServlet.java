package controller;

import model.*;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/addlibroform")

public class CaricaLibroServlet extends HttpServlet {

    private static final String DATA_DIRECTORY = "web/img";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titolo = request.getParameter("titolo");
        String autore = request.getParameter("autore");
        String npage = request.getParameter("npage");
        String ndisp = request.getParameter("ndisp");
        if(ndisp == null) {
            ndisp="0";
        }
        String isbn = request.getParameter("isbn");
        String anno = request.getParameter("anno");
        String tipo = request.getParameter("tipo");
        //String descrizione = request.getParameter("subject");
        List<Categoria> categorie = new ArrayList<>();
        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> c = dao.doRetrieveAll();
        int i;
        for(i=0;i<c.size();i++){
            if(request.getParameter(String.valueOf(c.get(i).getId())) != null){
                categorie.add(c.get(i));
            }
        }

        LibroDAO ldao =  new LibroDAO();
        Libro libro = new Libro();
        libro.setTitolo(titolo);
        //libro.setDescrizione(descrizione);
        /*libro.setIsbn(isbn);
        libro.setCategorie(categorie);
        libro.setAnno_pubblicazione(Integer.parseInt(anno));
        libro.setNumero_pagine(Integer.parseInt(npage));
        libro.setNumero_disponibili(Integer.parseInt(ndisp));
        libro.setAutore(autore);*/
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            return;
        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored
        String uploadFolder = getServletContext().getRealPath("")
                + File.separator + DATA_DIRECTORY;

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String filePath = null;
        try {
            // Parse the request
            List items = upload.parseRequest((RequestContext) request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    filePath = uploadFolder + File.separator + fileName;
                    File uploadedFile = new File(filePath);
                    System.out.println(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);
                }
            }
            libro.setPath(request.getParameter("img"));
            ldao.doSave(libro);
            throw new MyServletException("ok");
            // displays done.jsp page after upload finished
            //getServletContext().getRequestDispatcher("/done.jsp").forward(request, response);

        } catch (FileUploadException ex) {
            throw new MyServletException("Errore upload file....");
        } catch (Exception ex) {
            throw new MyServletException("Errore!");
        }





    }
}
