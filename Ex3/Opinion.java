package opiniones;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;
import opiniones.OBGuardar;

public class Opinion extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String animal;
        animal = request.getParameter("animal");
        response.setContentType("text/html");

        String res = SaveOpinion(animal);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                     "<HTML lang='es'>\n" +
                     "<HEAD>\n<meta charset='utf-8' />\n" +
                     "<title> Lectura de todos los parámetros de petición </title>\n</head>\n" +
                     "<BODY>\n" +
                    "<H1 ALIGN=CENTER>Gracias por su participación</H1>\n" +
                    "<H1 ALIGN=CENTER>Resultados: </H1>\n" +
                     "<P ALIGN=CENTER>" + res + "</P>\n" +
                     "<H2 ALIGN=CENTER>Gato: " + "</H2>\n" +
                     "<H2 ALIGN=CENTER>Pájaro: " + "</H2>\n" +
                     "<H2 ALIGN=CENTER>Serpiente: " + "</H2>\n" +
                    "<H2 ALIGN=CENTER>Pájaro: " + "</H2>\n" +
                    "<H2 ALIGN=CENTER>Ninguno: " + "</H2>\n");
        out.println("</BODY></HTML>");
    }

    public String SaveOpinion(String animal) {
        OBGuardar guardar = new OBGuardar(animal);
        return guardar.getResult();
    }
}
