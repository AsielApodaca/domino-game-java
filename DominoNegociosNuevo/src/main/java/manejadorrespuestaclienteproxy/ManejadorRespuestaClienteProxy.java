package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase abstracta que define un manejador en la cadena de responsabilidad para
 * procesar respuestas del cliente proxy.
 * 
 * Cada instancia concreta debe implementar los métodos para determinar si 
 * puede manejar un evento y para procesarlo.
 * 
 * Si el manejador actual no puede procesar el evento, este se delega al 
 * siguiente manejador en la cadena. Si no hay un manejador siguiente, se 
 * registra un mensaje de error.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public abstract class ManejadorRespuestaClienteProxy {

    private static final Logger LOG = Logger.getLogger(ManejadorRespuestaClienteProxy.class.getName());
    
    private ManejadorRespuestaClienteProxy siguienteManejador;

    /**
     * Constructor por defecto.
     */
    public ManejadorRespuestaClienteProxy() {
    }

    /**
     * Constructor que inicializa el manejador con un siguiente manejador en la cadena.
     * 
     * @param siguienteManejador el siguiente manejador en la cadena de responsabilidad.
     */
    public ManejadorRespuestaClienteProxy(ManejadorRespuestaClienteProxy siguienteManejador) {
        this.siguienteManejador = siguienteManejador;
    }

    /**
     * Establece el siguiente manejador en la cadena de responsabilidad.
     * 
     * @param siguienteManejador el siguiente manejador.
     */
    public void setSiguienteManejador(ManejadorRespuestaClienteProxy siguienteManejador) {
        this.siguienteManejador = siguienteManejador;
    }

    /**
     * Método principal que intenta manejar el evento recibido. Si el manejador actual
     * no puede procesarlo, delega la responsabilidad al siguiente manejador.
     * 
     * @param evento el evento de respuesta que se debe manejar.
     */
    public void manejar(EventoRespuesta evento) {
        if (puedeManejar(evento)) {
            procesar(evento);
        } else if (siguienteManejador != null) {
            siguienteManejador.manejar(evento);
        } else {
            LOG.log(Level.SEVERE, "Se recibió una respuesta del ClientProxy y no se encontró un manejador para esta respuesta.");
        }
    }

    /**
     * Método abstracto que determina si el manejador actual puede procesar el evento.
     * 
     * @param evento el evento a evaluar.
     * @return {@code true} si el manejador puede procesar el evento, {@code false} en caso contrario.
     */
    protected abstract boolean puedeManejar(EventoRespuesta evento);

    /**
     * Método abstracto que define cómo el manejador procesa el evento.
     * 
     * @param evento el evento a procesar.
     */
    protected abstract void procesar(EventoRespuesta evento);
}
