package manejadorsolicitudserverproxy;

import domino.listeners.IServidorProxyListener;
import domino.solicitudes.EventoSolicitud;

/**
 * Gestor que implementa la lógica para manejar solicitudes del servidor mediante
 * una cadena de manejadores.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class GestorSolicitudServerProxy implements IServidorProxyListener {
    private ManejadorSolicitudServerProxy manejadorCabeza;

    /**
     * Constructor que inicializa el gestor con la cabeza de la cadena de manejadores.
     * 
     * @param manejadorCabeza el primer manejador de la cadena.
     */
    public GestorSolicitudServerProxy(ManejadorSolicitudServerProxy manejadorCabeza) {
        this.manejadorCabeza = manejadorCabeza;
    }

    /**
     * Invocado al recibir una solicitud del servidor, delegando su manejo
     * a la cadena de responsabilidad.
     * 
     * @param eventoSolicitud la solicitud recibida.
     */
    @Override
    public void onRecibirSolicitud(EventoSolicitud eventoSolicitud) {
        manejadorCabeza.manejar(eventoSolicitud);
    }
}
