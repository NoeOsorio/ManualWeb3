package ventas;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ventas.ProductoDAO;

public class Productos extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String option, json;
        option = request.getParameter("category");

        if (option == null) {
            json = getCategories();
        } else {
            json = getProducts(Integer.parseInt(option));
        }

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public String getProducts(int category) {
        ProductoDAO dao = new ProductoDAO();
        return dao.getProductos(category);
    }

    public String getCategories() {
        ProductoDAO dao = new ProductoDAO();
        return dao.getCategorias();
    }
}

