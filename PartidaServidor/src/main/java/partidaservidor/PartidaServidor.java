/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidaservidor;

import controlSolicitudes.ServidorSolicitudCasillaSeleccionada;
import domino.fachada.FachadaServidorProxy;
import domino.fachada.IFachadaServidorProxy;
import domino.respuestas.EventoRespuesta;
import domino.servidorproxylogica.ServidorProxy;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.listeners.IServidorProxyListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaServidor implements IServidorProxyListener {

    private IFachadaServidorProxy fachadaServidorProxy;
    
    public PartidaServidor() {
        this.fachadaServidorProxy = new FachadaServidorProxy() ;
        fachadaServidorProxy.agregarListener(this);
    }

    /**
     * Procesa una solicitud recibida y ejecuta la acción correspondiente según
     * el tipo de solicitud.
     *
     * @param eventoSolicitud El evento de solicitud que se va a procesar, puede
     * ser de cualquier tipo que extienda de EventoSolicitud.
     */
    public void runRequest(EventoSolicitud eventoSolicitud) {
        // Verifica de que instancia fue recibido el evento
        if (eventoSolicitud instanceof SolicitudCasillaSeleccionada solicitudCasillaSeleccionada) {
            ServidorSolicitudCasillaSeleccionada partidaServidorSolicitudCasillaSeleccionada
                    = new ServidorSolicitudCasillaSeleccionada();

            // Llama al método para colocar la ficha seleccionada en el tablero
            partidaServidorSolicitudCasillaSeleccionada.colocarFichaSeleccionadaEnTableroEntity(
                    solicitudCasillaSeleccionada
                            .getCasillaDTO());
        }

    }

    /**
     * Método para enviar una respuesta al proxy.
     *
     * @param eventoRespuesta El evento de respuesta que se enviará después de
     * procesar la solicitud.
     */
    public void sendResponse(EventoRespuesta eventoRespuesta) {
        fachadaServidorProxy.enviarRespuestas(eventoRespuesta);
    }

    @Override
    public void onRecibirSolicitud(EventoSolicitud eventoSolicitud) {
        runRequest(eventoSolicitud);
    }
}
