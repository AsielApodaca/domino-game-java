package manejadorrespuestaclienteproxy;

import domino.listeners.IClientProxyListener;
import domino.respuestas.EventoRespuesta;

/**
 * Clase encargada de gestionar las respuestas recibidas del cliente proxy
 * mediante una cadena de manejadores de respuestas.
 * 
 * Actúa como el punto inicial que delega la responsabilidad de manejar la 
 * respuesta al manejador correspondiente en la cadena.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class GestorRespuestaClienteProxy implements IClientProxyListener {

    private ManejadorRespuestaClienteProxy manejadorCabeza;

    /**
     * Constructor que inicializa el gestor con el primer manejador de la cadena.
     * 
     * @param manejadorCabeza el manejador inicial de la cadena de responsabilidad.
     */
    public GestorRespuestaClienteProxy(ManejadorRespuestaClienteProxy manejadorCabeza) {
        this.manejadorCabeza = manejadorCabeza;
    }

    /**
     * Método invocado al recibir una respuesta del cliente proxy. 
     * La respuesta es procesada por el primer manejador en la cadena.
     * 
     * @param eventoRespuesta el evento de respuesta recibido.
     */
    @Override
    public void onRecibirRespuesta(EventoRespuesta eventoRespuesta) {
        manejadorCabeza.manejar(eventoRespuesta);
    }
}
