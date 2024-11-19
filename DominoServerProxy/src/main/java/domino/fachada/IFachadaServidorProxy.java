/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domino.fachada;

import domino.listeners.IServidorProxyListener;
import domino.respuestas.EventoRespuesta;

/**
 *
 * @author castr
 */
public interface IFachadaServidorProxy {

    public void enviarRespuestas(EventoRespuesta eventoRespuesta);
    
    public void agregarListener(IServidorProxyListener listener);
}
