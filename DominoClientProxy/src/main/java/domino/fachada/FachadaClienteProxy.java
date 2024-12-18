/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.fachada;

import com.google.gson.JsonObject;
import domino.clienteproxylogica.ClienteProxy;
import domino.listeners.IClientProxyListener;
import domino.serializador.Serializador;
import domino.solicitudes.EventoSolicitud;

/**
 *
 * @author castr
 */
public class FachadaClienteProxy implements IFachadaClienteProxy {

    private ClienteProxy clienteProxy;

    public FachadaClienteProxy(String host, int puerto) {
        this.clienteProxy = new ClienteProxy(host, puerto) ;
    }
    
    @Override
    public void enviarSolicitud(EventoSolicitud solicitud) {
        clienteProxy.enviarSolicitud(solicitud);
    }

    @Override
    public void suscribirClientProxyListener(IClientProxyListener clientProxyListener) {
        clienteProxy.agregarListener(clientProxyListener);
    }

}
