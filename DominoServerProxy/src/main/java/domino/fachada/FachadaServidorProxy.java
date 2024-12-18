/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.fachada;

import domino.listeners.IServidorProxyListener;
import domino.respuestas.EventoRespuesta;
import domino.servidorproxylogica.ServidorProxy;

/**
 *
 * @author castr
 */
public class FachadaServidorProxy implements IFachadaServidorProxy {

    private ServidorProxy servidorProxy;
    
    public FachadaServidorProxy() {
        this.servidorProxy = new ServidorProxy("localhost", 3000) ;
    }
    
    @Override
    public void enviarRespuestas(EventoRespuesta eventoRespuesta) {
       servidorProxy.enviarRespuestas(eventoRespuesta);
    }

    @Override
    public void agregarListener(IServidorProxyListener listener) {
        servidorProxy.agregarListener(listener);
    }

}
