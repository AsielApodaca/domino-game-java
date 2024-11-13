/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.fachada;

import com.google.gson.JsonObject;
import domino.clienteproxylogica.ClienteProxy;
import domino.serializador.Serializador;
import domino.solicitudes.EventoSolicitud;

/**
 *
 * @author castr
 */
public class FachadaClienteProxy implements IFachadaClienteProxy {

    private final ClienteProxy clienteProxy;

    public FachadaClienteProxy() {
        this.clienteProxy = new ClienteProxy("localhost", 3000);

    }

    @Override
    public void enviarSolicitud(EventoSolicitud solicitud) {
        clienteProxy.enviarSolicitud(solicitud);
    }

}
