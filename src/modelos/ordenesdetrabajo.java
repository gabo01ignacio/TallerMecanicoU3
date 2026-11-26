// [REQUISITO: Package]
package modelos;

import vehiculo.vehiculo;
import sistemas.registros;
import java.io.Serializable;
import java.util.ArrayList;



/**
 *
 * @author 4rtur
 */


// [REQUISITO: Clase e Interfaz]
public class ordenesdetrabajo implements Serializable, registros {
    private static final long serialVersionUID = 1L;

    // [REQUISITO: Atributo static]
    private static int contadorordenes = 1;

    private String numeroorden;
    private double costomecanico;
    private vehiculo vehiculo;
    private mecanico mecanicoasignado;
    private ArrayList<repuesto> repuestosusado;
    private estadoorden estado; 
    private double costoTotal;
    //cambios
    private String observaciones = "";
    
    public String getObservaciones() {
    return observaciones;
    }
    
    public void setObservaciones(String Obs) {
        this.observaciones=Obs;
    }
    

    
    
    public ordenesdetrabajo(vehiculo vehiculo, mecanico mecanicoasignado, double costomecanico) {
        this.numeroorden = "trabajo n" + contadorordenes++;
        this.vehiculo = vehiculo;
        this.mecanicoasignado = mecanicoasignado;
        this.costomecanico = costomecanico;
        this.repuestosusado = new ArrayList<>();
        this.estado = estadoorden.PENDIENTE;
        this.costoTotal = calcularCostoTotal();
    }

    public ordenesdetrabajo() {
        this.numeroorden = "trabajo n°" + contadorordenes++;
        this.repuestosusado = new ArrayList<>();
        this.estado = estadoorden.PENDIENTE;
        this.costoTotal = 0.0;
    }

    public double calcularCostoTotal() {
        double suma = 0.0;
        if (vehiculo != null) {
            suma += vehiculo.calcularCostoBase();
        }
        suma += costomecanico;
        for (repuesto r : repuestosusado) {
            suma += r.getPrecio();
        }
        this.costoTotal = suma;
        return suma;
    }

    public void cambiarEstado(estadoorden nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String obtenerDetalleCompleto() {
        return "Orden: " + numeroorden + " | Vehiculo: " + vehiculo.getPatente() + " | Mecanico: " + mecanicoasignado.getNombre() + " | Estado: " + estado + " | Total: $" + (int)costoTotal;
    }

    @Override
    public String obtenerId() {
        return numeroorden;
    }

    @Override
    public void registrarEnSistema() {
        System.out.println("Orden " + numeroorden + " registrada correctamente.");
    }

    public static int getContadorordenes() {
        return contadorordenes;
    }

    public static void setContadorordenes(int aContadorordenes) {
        contadorordenes = aContadorordenes;
    }

    public String getNumeroorden() {
        return numeroorden;
    }

    public void setNumeroorden(String numeroorden) {
        this.numeroorden = numeroorden;
    }

    public double getCostomecanico() {
        return costomecanico;
    }

    public void setCostomecanico(double costomecanico) {
        this.costomecanico = costomecanico;
    }

    public vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public mecanico getMecanicoasignado() {
        return mecanicoasignado;
    }

    public void setMecanicoasignado(mecanico mecanicoasignado) {
        this.mecanicoasignado = mecanicoasignado;
    }

    public ArrayList<repuesto> getRepuestosusado() {
        return repuestosusado;
    }

    public void setRepuestosusado(ArrayList<repuesto> repuestosusado) {
        this.repuestosusado = repuestosusado;
    }

    public estadoorden getEstado() {
        return estado;
    }

    public void setEstado(estadoorden estado) {
        this.estado = estado;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
   
}

