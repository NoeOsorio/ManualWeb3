package opiniones;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import opiniones.*;

public class Opinion extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String animal;
        animal = request.getParameter("animal");
        response.setContentType("text/html");

        ArrayList<Animal> res = SaveOpinion(animal);

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" + "<HTML lang='es'>\n" + "<HEAD>\n<meta charset='utf-8' />\n"
                + "<title> Lectura de todos los parámetros de petición </title>\n</head>\n");
        out.println("<BODY>\n");
        out.println("<H1 ALIGN=CENTER>Gracias por su participación</H1>\n" + "<H1 ALIGN=CENTER>Resultados: </H1>&nbsp;");

        for (Animal animalSQL : res) {
            out.println("<P ALIGN=CENTER> <h2>" + animalSQL.nombre + "</h2>" + "<span><b>Total: </b>" + animalSQL.total
                        + "</span>&nbsp;" + "<span><b>Porcentaje: </b>" + animalSQL.percent + "</span>" + " </P>\n");
        }

        out.println("</BODY></HTML>");
    }

    public ArrayList<Animal> SaveOpinion(String animal) {
        OBGuardar guardar = new OBGuardar(animal);
        return guardar.getResults();
    }

    public String getResult(String animal) {
        OBGuardar guardar = new OBGuardar(animal);
        return guardar.getResult();
    }
}
