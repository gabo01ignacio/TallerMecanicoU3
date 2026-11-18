package vehiculo;
import sistemas.calculos;

/**
 *
 * @author 4rtur
 */

// [REQUISITO: Package] 
public class camioneta extends vehiculo implements calculos{
    private final int capacidadcarga;

    public camioneta(String patente, String marca, String modelo, int año, tipovehiculo tipoMotor, int capacidadcarga) {
        super(patente, marca, modelo, año, tipoMotor);
        this.capacidadcarga = capacidadcarga;
    }

    @Override
    public String obtenerDescripcion() {
        return "Auto " + marca + " " + modelo + " " +  año + " con " + capacidadcarga + " puertas, de patente "+patente;
    }

    @Override
    public double calcularCostoBase() {
        return 25000.0; 
    }

//implementacion de la interface calculos
    @Override
    public double calcularCostoTotal() {
        double costoBase = calcularCostoBase();
        double impuesto = 1.39; 
        return costoBase * impuesto;
    }
}
