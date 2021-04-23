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

        PrintWriter out = response.getWriter();
        String token = LogIn(uname, passwd);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(token);
        out.flush();

    }

    private String LogIn(String uname, String passwd) {
        AuthDAO auth = new AuthDAO();
        return auth.LogIn(uname, passwd);
        //return auth.Register(uname, passwd);
    }
}
