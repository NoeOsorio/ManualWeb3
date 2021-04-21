package opiniones;

import java.sql.*;
import java.util.ArrayList;

public class OBGuardar {
    private String result;
    private ArratList<Animal> animals = new ArratList<Animal>();

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
            switch (animal) {
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

            rset = stmt.executeQuery("SELECT * FROM animales");

            while (rset.next()) {
                id = rset.getString("id");
                nombre = rset.getString("name");
                total = rset.getInt("total");
                porcentaje = rset.getDouble("porcentaje");

                Animal current = new Animal(id, nombre, total, porcentaje);
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

    public ArratList<Animal> getResult() {
        return animals;
    }
}

public class Animal {
    String id;
    String nombre;
    int total;
    double porcentaje;

    public Animal(String id, String nombre, int total, double porcentaje) {
        this.id = id;
        this.nombre = nombre;
        this.total = total;
        this.porcentaje = porcentaje;
    }

}