// [REQUISITO: Package]
package modelos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 4rtur
 */


public enum estadoorden {
    PENDIENTE("Pendiente"),
    REPARACION("En Reparación"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");
    
    private final String descripcion;
    
    estadoorden(String descripcion) {
        this.descripcion = descripcion; 
    } 
    
    public String getDescripcion() {
        return descripcion; 
    }

}