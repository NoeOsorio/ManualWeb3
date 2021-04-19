package opiniones;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;
import opiniones.OBGuardar;

public class Opinion extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String name, lastname, rating, comments;
        name = request.getParameter("name");
        lastname = request.getParameter("lastname");
        rating = request.getParameter("rating");
        comments = request.getParameter("comments");
        response.setContentType("text/html");

        String res = SaveOpinion(name, lastname, rating, comments);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                     "<HTML lang='es'>\n" +
                     "<HEAD>\n<meta charset='utf-8' />\n" +
                     "<title> Lectura de todos los parámetros de petición </title>\n</head>\n" +
                     "<BODY>\n" +
                    "<H1 ALIGN=CENTER>Valores recogidos del formulario: </H1>\n" +
                     "<H2 ALIGN=CENTER>Nombre: " + name + "</H2>\n" +
                     "<H2 ALIGN=CENTER>Apellido: " + lastname + "</H2>\n" +
                     "<H2 ALIGN=CENTER> Opinión: " + rating + "</H2>\n" +
                    "<H2 ALIGN=CENTER>Comentarios: " + comments + "</H2>\n");
        out.println("</BODY></HTML>");
    }

    public String SaveOpinion(String name, String lastname, String rating, String comments) {
        OBGuardar guardar = new OBGuardar(name, lastname, rating, comments);
        return guardar.getResult();
    }
}
