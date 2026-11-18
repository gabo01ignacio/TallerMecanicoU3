/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author gabriel
 */
 // [REQUISITO: Excepciones]
public class PatenteDupliException extends RuntimeException {
   
    public PatenteDupliException(String patente) {
        super("La patente '" + patente + "' ya existe en los registros del taller.");
    }
}

