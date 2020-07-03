package controller;
import model.*;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/addlibroform")
@MultipartConfig

public class CaricaLibroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "C:\\Users\\vr99\\Google Drive\\Università\\Tecnologie per il software web\\IdeaProjects\\o\\web\\img";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //doPost(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest((RequestContext) request);

            for(FileItem item : multiparts) {
                String name = new File(item.getName()).getName();
                item.write(new File(DATA_DIRECTORY + File.separator + name));
            }
        }catch (Exception ex){
            throw new MyServletException("cc");
        }
    }
}
