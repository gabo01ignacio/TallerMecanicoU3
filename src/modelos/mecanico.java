// [REQUISITO: Package]
package modelos; 
    
import java.io.Serializable;

/**
 *
 * @author 4rtur
 */

public class mecanico implements Serializable {
    private String rut;
    private String nombre;
    private String cargo;

    public mecanico(String rut, String nombre, String cargo) {
        this.rut = rut;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    
}