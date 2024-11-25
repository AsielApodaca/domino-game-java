package manejadorsolicitudserverproxy;

import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudFichaSeleccionada;
import dominodto.FichaDominoDTO;
import dominodto.UsuarioDTO;
import logica.IPartidaServerLogica;

/**
 * Manejador de la solicitud de ficha seleccionada.
 * 
 * Este manejador procesa las solicitudes que indican la ficha seleccionada por un jugador durante la partida.
 * La solicitud contiene información sobre el jugador y la ficha seleccionada, 
 * y se comunica con la lógica del servidor de la partida para gestionar esta acción.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorSolicitudFichaSeleccionada extends ManejadorSolicitudServerProxy {

    /**
     * Constructor del manejador con la lógica del servidor de partidas.
     * 
     * @param partidaServerLogica La lógica del servidor de la partida.
     */
    public ManejadorSolicitudFichaSeleccionada(IPartidaServerLogica partidaServerLogica) {
        super(partidaServerLogica);
    }

    /**
     * Constructor del manejador con un manejador siguiente y la lógica del servidor de partidas.
     * 
     * @param siguienteManejador El siguiente manejador en la cadena de responsabilidad.
     * @param partidaServerLogica La lógica del servidor de la partida.
     */
    public ManejadorSolicitudFichaSeleccionada(ManejadorSolicitudServerProxy siguienteManejador, IPartidaServerLogica partidaServerLogica) {
        super(siguienteManejador, partidaServerLogica);
    }

    /**
     * Verifica si el evento es una instancia de {@link SolicitudFichaSeleccionada}.
     * 
     * @param evento El evento que se quiere verificar.
     * @return {@code true} si el evento es una solicitud de ficha seleccionada.
     */
    @Override
    protected boolean puedeManejar(EventoSolicitud evento) {
        return evento instanceof SolicitudFichaSeleccionada;
    }

    /**
     * Procesa el evento {@link SolicitudFichaSeleccionada}.
     * 
     * Este método llama a la lógica del servidor de partidas para procesar la ficha seleccionada por el jugador.
     * 
     * @param evento El evento que contiene la información de la solicitud.
     */
    @Override
    protected void procesar(EventoSolicitud evento) {
        SolicitudFichaSeleccionada solicitud = (SolicitudFichaSeleccionada) evento;
        FichaDominoDTO fichaDominoDTO = solicitud.getFichaDominoDTO();
        UsuarioDTO usuarioDTO = solicitud.getUsuarioDTO();
        partidaServerLogica.procesarFichaSeleccionada(fichaDominoDTO, usuarioDTO);
    }
}
