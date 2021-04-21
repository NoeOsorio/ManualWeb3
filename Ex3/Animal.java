package opiniones;

import java.text.DecimalFormat;

public class Animal {
    String id;
    String nombre;
    int total;
    double porcentaje;
    String percent;

    public Animal(String id, String nombre, int total, double porcentaje) {
        this.id = id;
        this.nombre = nombre;
        this.total = total;
        this.porcentaje = porcentaje;
        this.percent = formatPercent(porcentaje, 2);
    }

    public static String formatPercent(double done, int digits) {
        DecimalFormat percentFormat = new DecimalFormat("0.0%");
        percentFormat.setDecimalSeparatorAlwaysShown(false);
        percentFormat.setMinimumFractionDigits(digits);
        percentFormat.setMaximumFractionDigits(digits);
        return percentFormat.format(done);
    }

}
