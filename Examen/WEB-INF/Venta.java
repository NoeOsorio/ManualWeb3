package ventas;

public class Venta {
    int id, cantidad, categoriaID, vendedorID;
    double subtotal, total;

    public Venta(int id, int cantidad, int categoriaID, int vendedorID, double subtotal, double total) {
        this.id =id;
        this.cantidad = cantidad;
        this.categoriaID = categoriaID;
        this.vendedorID = vendedorID;
        this.subtotal = subtotal;
        this.total = total;
    }
}
