package manejadorsolicitudserverproxy;

import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCrearSala;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.UsuarioDTO;
import logica.IPartidaServerLogica;

/**
 * Clase que maneja la solicitud de creación de una nueva sala en el servidor de dominó.
 * Procesa el evento de tipo {@link SolicitudCrearSala} para delegarlo a la lógica del servidor.
 */
public class ManejadorSolicitudCrearSala extends ManejadorSolicitudServerProxy {

    /**
     * Constructor que inicializa el manejador con un siguiente manejador en la cadena
     * y la lógica del servidor de partida.
     *
     * @param siguienteManejador el siguiente manejador en la cadena.
     * @param partidaServerLogica la lógica del servidor de partida.
     */
    public ManejadorSolicitudCrearSala(ManejadorSolicitudServerProxy siguienteManejador, IPartidaServerLogica partidaServerLogica) {
        super(siguienteManejador, partidaServerLogica);
    }

    /**
     * Constructor que inicializa el manejador con la lógica del servidor de partida.
     *
     * @param partidaServerLogica la lógica del servidor de partida.
     */
    public ManejadorSolicitudCrearSala(IPartidaServerLogica partidaServerLogica) {
        super(partidaServerLogica);
    }

    /**
     * Determina si este manejador puede procesar el evento.
     *
     * @param evento el evento recibido.
     * @return true si el evento es una instancia de {@link SolicitudCrearSala}, false en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoSolicitud evento) {
        return evento instanceof SolicitudCrearSala;
    }

    /**
     * Procesa el evento de solicitud de creación de sala, delegando la lógica al servidor.
     *
     * @param evento el evento a procesar.
     */
    @Override
    protected void procesar(EventoSolicitud evento) {
        SolicitudCrearSala solicitud = (SolicitudCrearSala) evento;
        ConfiguracionJuegoDTO configuracionJuegoDTO = solicitud.getConfiguracionJuegoDTO();
        UsuarioDTO anfitrion = evento.getUsuarioDTO();
        partidaServerLogica.procesarCrearSala(configuracionJuegoDTO, anfitrion);
    }
}
