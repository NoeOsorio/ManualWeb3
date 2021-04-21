package auth;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import auth.AuthDAO;

public class Auth extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uname, passwd;
        uname = request.getParameter("uname");
        passwd = request.getParameter("passwd");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String token = LogIn(uname, passwd);

        if (token != null) {
            out.println("<!DOCTYPE html>\n" +
                        "<HTML lang='es'>\n" +
                        "<HEAD>\n<meta charset='utf-8' />\n" +
                        "<title> Lectura de todos los parámetros de petición </title>\n</head>\n" +
                        "<BODY>\n" +
                        "<p>" + token + "</p>" +
                        "<h1>Acceso permitido</h1>");
            out.println("</BODY></HTML>");
        } else {
            out.println("<!DOCTYPE html>\n" +
                        "<HTML lang='es'>\n" +
                        "<HEAD>\n<meta charset='utf-8' />\n" +
                        "<title> Lectura de todos los parámetros de petición </title>\n</head>\n" +
                        "<BODY>\n" +
                        "<p>" + token + "</p>" +
                        "<h1>Acceso denegado</h1>");
            out.println("</BODY></HTML>");
        }
    }

    private String LogIn(String uname, String passwd) {
        AuthDAO auth = new AuthDAO();
        return auth.LogIn(uname, passwd);
        //return auth.Register(uname, passwd);
    }
}
