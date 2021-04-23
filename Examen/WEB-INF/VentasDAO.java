package ventas;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import com.google.gson.Gson;
import ventas.Producto;
import ventas.Categoria;
import ventas.Venta;

public class VentasDAO {
  String result;

    public String registrarVenta(Venta venta) {
        Gson gson = new Gson();
        int ventaID = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM ventas");

            rs.moveToInsertRow();
            rs.updateInt("vendedorID", Integer.parseInt(venta.seller.uid));
            rs.updateDouble("subtotal", venta.subtotal);
            rs.updateDouble("total", venta.total);
            rs.insertRow();
            rs.moveToCurrentRow();

            while(rs.next()) {
                ventaID = rs.getInt("id");
            }

            rs = stmt.executeQuery("SELECT * FROM `productos-vendidos`");

            for(Producto producto : venta.products) {
                rs.moveToInsertRow();
                rs.updateInt("ventaID", ventaID);
                rs.updateInt("productoID", producto.id);
                rs.updateInt("cantidad", producto.quantity);
                rs.insertRow();
                rs.moveToCurrentRow();
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            result = e.getMessage();
            e.printStackTrace();
        }
        catch (SQLException e) {
            result = e.getMessage();
            e.printStackTrace();
        }
        return gson.toJson(venta);
    }

    public void corteDeCaja() {
    }

  public String getResult() {
    return result;
  }
}
