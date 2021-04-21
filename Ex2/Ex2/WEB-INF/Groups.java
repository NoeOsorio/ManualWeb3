package groups;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import groups.ReadStudents;
import groups.Student;

public class Groups extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String group = request.getParameter("group");
        response.setContentType("text/html");

        ArrayList<Student> res = GetStudents(group);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" + "<HTML lang='es'>\n" + "<HEAD>\n<meta charset='utf-8' />\n"
                + "<title> Lectura de todos los parámetros de petición </title>\n</head>\n");
        out.println("<BODY>\n" + "<H1 ALIGN=CENTER>Lista de Alumnos del Grupo " + group + "</H1>\n");

        out.println("<table> ");
        out.println("<tr> ");
        out.println("<th>Carnet</th>");
        out.println("<th>Nombre</th>");
        out.println("<th>Apellidos</th>");
        out.println("<th>Curso</th>");
        out.println("</tr> ");
        for (Student student : res) {
            out.println("<tr> ");
            out.println("<td>" + student.school_id + "</td>");
            out.println("<td>" + student.name + "</td>");
            out.println("<td>" + student.last_name + "</td>");
            out.println("<td>" + student.subject + "</td>");
            out.println("</tr> ");
        }
        out.println("</table> ");
        out.println("</BODY></HTML>");
    }

    public ArrayList<Student> GetStudents(String group) {
        ReadStudents students = new ReadStudents(group);
        return students.getStudents();
    }
}
