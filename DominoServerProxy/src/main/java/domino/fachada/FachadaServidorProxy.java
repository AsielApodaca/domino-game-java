/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.fachada;

import domino.respuestas.EventoRespuesta;
import domino.servidorproxylogica.ServidorProxy;

/**
 *
 * @author castr
 */
public class FachadaServidorProxy implements IFachadaServidorProxy {

    private ServidorProxy servidorProxy;
    
    @Override
    public void enviarRespuestas(EventoRespuesta eventoRespuesta) {
       servidorProxy.enviarRespuestas(eventoRespuesta);
    }

}
