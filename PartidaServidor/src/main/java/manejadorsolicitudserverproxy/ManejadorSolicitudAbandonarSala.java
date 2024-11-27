package manejadorsolicitudserverproxy;

import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudAbandonarSala;
import dominodto.UsuarioDTO;
import logica.IPartidaServerLogica;

/**
 * Clase que maneja la solicitud de abandonar una sala en el servidor de dominó.
 * Procesa eventos de tipo {@link SolicitudAbandonarSala} y delega la operación
 * correspondiente a la lógica del servidor.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorSolicitudAbandonarSala extends ManejadorSolicitudServerProxy {

    /**
     * Constructor que inicializa el manejador con un siguiente manejador en la cadena
     * y la lógica del servidor de partida.
     *
     * @param siguienteManejador el siguiente manejador en la cadena.
     * @param partidaServerLogica la lógica del servidor de partida.
     */
    public ManejadorSolicitudAbandonarSala(ManejadorSolicitudServerProxy siguienteManejador, IPartidaServerLogica partidaServerLogica) {
        super(siguienteManejador, partidaServerLogica);
    }

    /**
     * Constructor que inicializa el manejador con la lógica del servidor de partida.
     *
     * @param partidaServerLogica la lógica del servidor de partida.
     */
    public ManejadorSolicitudAbandonarSala(IPartidaServerLogica partidaServerLogica) {
        super(partidaServerLogica);
    }

    /**
     * Determina si este manejador puede procesar el evento.
     *
     * @param evento el evento recibido.
     * @return true si el evento es una instancia de {@link SolicitudAbandonarSala}, false en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoSolicitud evento) {
        return evento instanceof SolicitudAbandonarSala;
    }

    /**
     * Procesa el evento de solicitud para abandonar una sala, delegando la lógica al servidor.
     *
     * @param evento el evento a procesar.
     */
    @Override
    protected void procesar(EventoSolicitud evento) {
        SolicitudAbandonarSala solicitud = (SolicitudAbandonarSala) evento;
        UsuarioDTO usuarioDTO = solicitud.getUsuarioDTO();
        partidaServerLogica.procesarAbandonarSala(usuarioDTO.getIdCliente());
    }
}
