package ventas;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import com.google.gson.Gson;
import ventas.Producto;
import ventas.Categoria;

public class ProductoDAO {
  String result;

  public String getProductos(int categoriaID) {
    Gson gson = new Gson();
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
        precio = rs.getDouble("precio");

        productos.add(new Producto(id, nombre, marca, idCategoria, cantidad, precio, 0));
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return gson.toJson(productos);
  }

  public String getCategorias() {
    Gson gson = new Gson();
    ArrayList < Categoria > categorias = new ArrayList < Categoria > ();
    int id;
    String nombre;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `categorias`");

      while (rs.next()) {
        id = rs.getInt("id");
        nombre = rs.getString("nombre");
        categorias.add(new Categoria(id, nombre));
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return gson.toJson(categorias);
  }

  public String getResult() {
    return result;
  }
}
