package opiniones;

import java.sql.*;
import java.util.ArrayList;

public class OBGuardar {
    private String result;

    OBGuardar(String animal) {
        try {
            int total, totalGlobal;
            double porcentaje;
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex3", "root", "");
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rset = stmt.executeQuery("SELECT * FROM animales");

             rset.absolute(1);
             totalGlobal = rset.getInt("total");
             rset.updateInt("total", totalGlobal + 1);
             rset.updateRow();

             result = animal;
             switch(animal) {
             case "perro":
                 rset.absolute(2);
             case "gato":
                 rset.absolute(3);
             case "pajaro":
                 rset.absolute(4);
             case "serpiente":
                 rset.absolute(5);
             case "ninguno":
                 rset.absolute(6);
             }

             total = rset.getInt("total");
             rset.updateInt("total", total + 1);
             rset.updateDouble("porcentaje", ((total + 1) / totalGlobal) * 100);
             rset.updateRow();

             rset.close();
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
  }

    public String getResult() {
        return result;
    }
}
