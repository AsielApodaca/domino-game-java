package controlSolicitudes;

import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudFichaSeleccionada;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ServidorSolicitudFichaSeleccionada extends SolicitudManejador {

    /**
     * Procesa una solicitud de tipo SolicitudFichaSeleccionada o la
     * delega al siguiente manejador en la cadena de responsabilidad.
     *
     * @param solicitud la solicitud a procesar, una instancia de
     * manejador.
     */
    @Override
    public void manejarSolicitud(EventoSolicitud solicitud) {
        if (solicitud instanceof SolicitudFichaSeleccionada solicitudFichaSeleccionada) {

        } else if (siguiente != null) {
            siguiente.manejarSolicitud(solicitud);
        }
    }

}
