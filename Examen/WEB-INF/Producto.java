package ventas;

public class Producto {
    int id, cantidad, categoriaID;
    String nombre, marca;
    double precio;

    public Producto(int id, String nombre, String marca, int categoriaID, int cantidad, double precio) {
        this.id =id;
        this.nombre = nombre;
        this.marca = marca;
        this.categoriaID = categoriaID;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
