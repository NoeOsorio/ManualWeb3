package opiniones;

import java.sql.*;
import java.util.ArrayList;

public class OBGuardar {
    private String result;

    OBGuardar(String name, String lastname, String rating, String comments) {
        try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex1", "root", "");
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery("SELECT * FROM opiniones");

             rs.moveToInsertRow();
             rs.updateString(2, name);
             rs.updateString(3, lastname);
             rs.updateString(4, rating);
             rs.updateString(5, comments);
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

    public String getResult() {
        return result;
    }
}
