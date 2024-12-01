/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public abstract class EventoRespuesta {
    private String idCliente;
    private String nombreEvento;

    public EventoRespuesta(String idCliente) {
        this.idCliente = idCliente;
        this.nombreEvento = this.getClass().getSimpleName();
    }
    
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }
    
    public abstract boolean esParaTodos();
}
