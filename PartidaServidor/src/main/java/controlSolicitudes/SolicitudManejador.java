package controlSolicitudes;

import domino.solicitudes.EventoSolicitud;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public abstract class SolicitudManejador {
    protected SolicitudManejador siguiente;

    public void setSiguiente(SolicitudManejador siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void manejarSolicitud(EventoSolicitud solicitud);
} 

