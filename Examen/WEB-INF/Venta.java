package ventas;

import ventas.Producto;
import ventas.Vendedor;

public class Venta {
    Producto[] products;
    Vendedor seller;
    double subtotal, total;

    public Venta(Producto[] products, Vendedor seller, double subtotal, double total) {
        this.products = products;
        this.seller = seller;
        this.subtotal = subtotal;
        this.total = total;
    }
}

class Vendedor {
    String type, uid, uname;

    Vendedor(String type, String uid, String uname) {
        this.type = type;
        this.uid = uid;
        this.uname = uname;
    }
}
