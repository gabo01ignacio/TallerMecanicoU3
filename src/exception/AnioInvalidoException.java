/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author gabriel
 */
//[Requisito:Excepciones]
public class AnioInvalidoException extends RuntimeException{
    public AnioInvalidoException(int anio) {
        super("El anio " + anio + " es invalido. !!!Debe estar entre 1900 y 2027!!!.");
    }
}
