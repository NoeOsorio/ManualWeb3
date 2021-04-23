package ventas;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.StringBuffer;
import java.io.BufferedReader;
import com.google.gson.Gson;
import ventas.VentasDAO;
import ventas.Venta;

public class Ventas extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String json;

        json = getCorteDeCaja();

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String json, cantidad, categoriaID, vendedorID, subtotal, total;
        Venta venta;
        Gson gson = new Gson();

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        try {
            venta = gson.fromJson(jb.toString(), Venta.class);
        } catch (Exception e) {
            throw new IOException("Error parsing JSON request string");
        }

        json = registerSale(venta);
        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public String registerSale(Venta venta) {
        VentasDAO dao = new VentasDAO();
        dao.registrarVenta(venta);
        return dao.getResult();
    }

    public String getCorteDeCaja() {
        VentasDAO dao = new VentasDAO();
        return dao.corteDeCaja();
    }
}
