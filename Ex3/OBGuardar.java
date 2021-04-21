package opiniones;

import java.sql.*;
import java.util.ArrayList;

public class OBGuardar {
    private String result;
    private ArrayList<Animal> animals = new ArrayList<Animal>();

    OBGuardar(String animal) {
        try {
            int total, totalGlobal, id;
            String nombre;
            double porcentaje;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex3", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rset = stmt.executeQuery("SELECT * FROM animales");

            rset.absolute(1);
            totalGlobal = rset.getInt("total") + 1;
            rset.updateInt("total", totalGlobal);
            rset.updateRow();

            if (animal.equals("perro")) {
                rset.absolute(2);
            } else if (animal.equals("gato")) {
                rset.absolute(3);
            } else if (animal.equals("pajaro")) {
                rset.absolute(4);
            } else if (animal.equals("serpiente")) {
                rset.absolute(5);
            } else if (animal.equals("ninguno")) {
                rset.absolute(6);
            }

            total = rset.getInt("total") + 1;
            rset.updateInt("total", total);
            rset.updateRow();

            rset = stmt.executeQuery("SELECT * FROM animales");

            while (rset.next()) {
                id = rset.getInt("id");
                nombre = rset.getString("nombre");
                total = rset.getInt("total");

                if (totalGlobal > 0) {
                    double totalD = total;
                    double totalGlobalD = totalGlobal;
                    porcentaje = (totalD / totalGlobalD);
                } else {
                    porcentaje = 1;
                }

                rset.updateDouble("porcentaje", porcentaje);
                rset.updateRow();

                Animal current = new Animal(String.valueOf(id), nombre, total, porcentaje);
                System.out.println(current);
                animals.add(current);
            }

            rset.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            result = e.getMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            result = e.getMessage();
            e.printStackTrace();
        }
    }

    public ArrayList<Animal> getResults() {
        return animals;
    }

    public String getResult() {
        return result;
    }
}
