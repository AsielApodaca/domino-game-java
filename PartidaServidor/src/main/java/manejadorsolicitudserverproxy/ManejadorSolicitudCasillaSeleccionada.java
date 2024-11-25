package manejadorsolicitudserverproxy;

import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import dominodto.CasillaDTO;
import dominodto.UsuarioDTO;
import logica.IPartidaServerLogica;

/**
 * Manejador de la solicitud de casilla seleccionada.
 * 
 * Este manejador procesa las solicitudes que indican la casilla seleccionada por un jugador durante la partida.
 * La solicitud incluye información sobre el jugador y la casilla seleccionada, y se comunica con 
 * la lógica del servidor de la partida para manejar esta acción.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorSolicitudCasillaSeleccionada extends ManejadorSolicitudServerProxy {

    /**
     * Constructor del manejador con la lógica del servidor de partidas.
     * 
     * @param partidaServerLogica La lógica del servidor de la partida.
     */
    public ManejadorSolicitudCasillaSeleccionada(IPartidaServerLogica partidaServerLogica) {
        super(partidaServerLogica);
    }

    /**
     * Constructor del manejador con un manejador siguiente y la lógica del servidor de partidas.
     * 
     * @param siguienteManejador El siguiente manejador en la cadena de responsabilidad.
     * @param partidaServerLogica La lógica del servidor de la partida.
     */
    public ManejadorSolicitudCasillaSeleccionada(ManejadorSolicitudServerProxy siguienteManejador, IPartidaServerLogica partidaServerLogica) {
        super(siguienteManejador, partidaServerLogica);
    }

    /**
     * Verifica si el evento es una instancia de {@link SolicitudCasillaSeleccionada}.
     * 
     * @param evento El evento que se quiere verificar.
     * @return {@code true} si el evento es una solicitud de casilla seleccionada.
     */
    @Override
    protected boolean puedeManejar(EventoSolicitud evento) {
        return evento instanceof SolicitudCasillaSeleccionada;
    }

    /**
     * Procesa el evento {@link SolicitudCasillaSeleccionada}.
     * 
     * Este método llama a la lógica del servidor de partidas para procesar la casilla seleccionada por el jugador.
     * 
     * @param evento El evento que contiene la información de la solicitud.
     */
    @Override
    protected void procesar(EventoSolicitud evento) {
        SolicitudCasillaSeleccionada solicitud = (SolicitudCasillaSeleccionada) evento;
        CasillaDTO casillaDTO = solicitud.getCasillaDTO();
        UsuarioDTO usuarioDTO = solicitud.getUsuarioDTO();
        partidaServerLogica.procesarCasillaSeleccionada(casillaDTO, usuarioDTO);
    }
}
