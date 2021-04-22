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

    public void registrarVenta(Venta venta) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM ventas");

            rs.moveToInsertRow();
            rs.updateInt("id", venta.id);
            rs.updateInt("cantidad", venta.cantidad);
            rs.updateInt("categoriaID", venta.categoriaID);
            rs.updateInt("vendedorID", venta.vendedorID);
            rs.updateDouble("subtotal", venta.subtotal);
            rs.updateDouble("total", venta.total);
            rs.insertRow();
            rs.moveToCurrentRow();

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void corteDeCaja() {
    }

  public ArrayList < Producto > getProductos(int categoriaID) {

    ArrayList < Producto > productos = new ArrayList < Producto > ();
    int id, idCategoria, cantidad;
    String nombre, marca;
    double precio;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `productos` WHERE categoriaID = " + categoriaID);

      while (rs.next()) {
        id = rs.getInt("id");
        nombre = rs.getString("nombre");
        marca = rs.getString("marca");
        idCategoria = rs.getInt("categoriaID");
        cantidad = rs.getInt("cantidad");
        precio = rs.getInt("precio");

        productos.add(new Producto(id, nombre, marca, idCategoria, cantidad, precio));
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return productos;
  }

  public String getResult() {
    return result;
  }
}
