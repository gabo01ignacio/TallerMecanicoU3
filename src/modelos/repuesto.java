package modelos; // [REQUISITO: Package]

import java.io.Serializable;


/**
 *
 * @author 4rtur
 */


public class repuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    public repuesto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void aumentarStock(int cantidad) {
        this.stock += cantidad;
    }
    public void disminuirStock(int cantidad) {
        this.stock -= cantidad;
    }

}