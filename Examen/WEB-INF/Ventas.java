package ventas;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ventas.VentasDAO;

public class Ventas extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String json;

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String json, cantidad, categoriaID, vendedorID, subtotal, total;

        cantidad = request.getParameter("cantidad");
        categoriaID = request.getParameter("categoriaID");
        vendedorID = request.getParameter("vendedorID");
        subtotal = request.getParameter("subtotal");
        total = request.getParameter("total");

        Venta venta = new Venta(Integer.parseInt(cantidad), Integer.parseInt(categoriaID), Integer.parseInt(vendedorID), Doubl.parseDouble(subtotal), Double.parseDouble(total));

        json = registerSale(venta)

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public String registerSale(Venta venta) {
        VentasDAO dao = new VentasDAO();
        return dao.registrarVenta(venta);
    }

    public String getCategories() {
        ProductoDAO dao = new ProductoDAO();
        return dao.getCategorias();
    }
}

