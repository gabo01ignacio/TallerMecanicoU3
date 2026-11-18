// [REQUISITO: Package]
package vehiculo;

import exception.AnioInvalidoException;
import java.io.Serializable;
import sistemas.calculos;
/**
 *
 * @author 4rtur
 */


public abstract class vehiculo implements Serializable, calculos, Comparable<vehiculo> {
    private static final long serialVersionUID = 1L;
    protected String patente;
    protected String marca;
    protected String modelo;
    protected int año;
    protected tipovehiculo tipoMotor;

    public vehiculo(String patente, String marca, String modelo, int año, tipovehiculo tipoMotor) {
        if (año<1900||año>2027){
            throw new AnioInvalidoException(año);
            
        }
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.tipoMotor = tipoMotor;
    }

    public String getPatente() {
        return patente;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public int getAño() {
        return año;
    }

    public tipovehiculo getTipoMotor() { 
        return tipoMotor; 
    }
    
    @Override
    public abstract double calcularCostoTotal();
    
    @Override 
    public int compareTo(vehiculo otrovehiculo) {
        return Integer.compare(this.año, otrovehiculo.getAño());
    }
    
    public abstract String obtenerDescripcion();
    public abstract double calcularCostoBase();
}