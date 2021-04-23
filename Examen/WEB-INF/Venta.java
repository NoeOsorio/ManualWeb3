package ventas;

import ventas.Producto;

public class Venta {
    Producto[] products;
    Vendedor seller;
    double subtotal, total;

    public Venta(Producto[] products, Vendedor seller, int subtotal, int total) {
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
