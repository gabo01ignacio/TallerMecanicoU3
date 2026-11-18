// [REQUISITO: Package]
package vehiculo;

/**
 *
 * @author 4rtur
 */
    

public class moto extends vehiculo {
    private final int cc;

    public moto(String patente, String marca, String modelo, int año, tipovehiculo tipoMotor, int cc) {
        super(patente, marca, modelo, año, tipoMotor);
        this.cc = cc;
    }

    @Override
    public String obtenerDescripcion() {
        return "Moto " + marca + " " + modelo + " " + año + " de " +  cc + "cc, y de patente "+patente;
    }

    @Override
    public double calcularCostoBase() {
        return 1500.0; 
    }
    
    @Override
    public double calcularCostoTotal() {
        double impuesto = 1.19;
        double descuentomoto = 1.05;
    return calcularCostoBase() * impuesto * descuentomoto;
    }
}