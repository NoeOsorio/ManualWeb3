package ventas;

import ventas.TotalVendedor;

public class Reporte {
    TotalVendedor[] vendedores;
    double total;

    public Reporte(double total, TotalVendedor[] vendedores) {
        this.vendedores = vendedores;
        this.total = total;
    }
}
