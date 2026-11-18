// [REQUISITO: Package]
package vehiculo;

/**
 *
 * @author 4rtur
 */



public class auto extends vehiculo {
    private final int cantidadPuertas;

    public auto(String patente, String marca, String modelo, int año, tipovehiculo tipoMotor, int cantidadPuertas) {
        super(patente, marca, modelo, año, tipoMotor);
        this.cantidadPuertas = cantidadPuertas;
    }

    @Override
    public String obtenerDescripcion() {
        return "Auto " + marca + " " + modelo + " " +  año + " con " + cantidadPuertas + " puertas, de patente "+patente;
    }

    @Override
    public double calcularCostoBase() {
        return 18000.0; 
    }

//implementacion de la interface calculos
    @Override
    public double calcularCostoTotal() {
        double costoBase = calcularCostoBase();
        double impuesto = 1.19; 
        return costoBase * impuesto;
    }
}