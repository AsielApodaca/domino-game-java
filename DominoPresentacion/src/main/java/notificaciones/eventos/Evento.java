/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificaciones.eventos;

/**
 *
 * @author asielapodaca
 */
public abstract class Evento{
    private String nombreEvento;

    public Evento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }
    
    
}
