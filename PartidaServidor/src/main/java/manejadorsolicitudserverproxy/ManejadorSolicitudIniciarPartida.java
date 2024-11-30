package manejadorsolicitudserverproxy;

import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudIniciarPartida;
import logica.IPartidaServerLogica;

/**
 * Clase que maneja la solicitud para iniciar una partida. Extiende de
 * ManejadorSolicitudServerProxy y es responsable de procesar el evento de
 * iniciar partida, llamando al servicio de lógica correspondiente.
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorSolicitudIniciarPartida extends ManejadorSolicitudServerProxy {

    /**
     * Constructor que inicializa el manejador con la lógica de partida.
     *
     * @param partidaServerLogica Lógica de la partida a la que se delegan las
     * operaciones relacionadas con la partida.
     */
    public ManejadorSolicitudIniciarPartida(IPartidaServerLogica partidaServerLogica) {
        super(partidaServerLogica);
    }

    /**
     * Constructor del manejador con un manejador siguiente y la lógica del
     * servidor de partidas.
     *
     * @param siguienteManejador El siguiente manejador en la cadena de
     * responsabilidad.
     * @param partidaServerLogica La lógica del servidor de la partida.
     */
    public ManejadorSolicitudIniciarPartida(ManejadorSolicitudServerProxy siguienteManejador, IPartidaServerLogica partidaServerLogica) {
        super(siguienteManejador, partidaServerLogica);
    }

    /**
     * Verifica si el evento recibido es una solicitud para iniciar una partida.
     *
     * @param evento El evento que se desea verificar.
     * @return {@code true} si el evento es una instancia de {@link SolicitudIniciarPartida},
     *         {@code false} en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoSolicitud evento) {
        return evento instanceof SolicitudIniciarPartida;
    }

    /**
     * Procesa una solicitud de inicio de partida, delegando el procesamiento de
     * la partida a la lógica del servidor.
     *
     * @param evento El evento de solicitud de iniciar partida.
     */
    @Override
    protected void procesar(EventoSolicitud evento) {
        partidaServerLogica.procesarIniciarPartida();
    }
}
