package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaCambiarTurno;
import dominodto.JugadorDominoDTO;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador para procesar eventos de tipo {@link RespuestaCambiarTurno}.
 * 
 * Este manejador se encarga de actualizar la lógica de la partida para reflejar 
 * el cambio de turno a un nuevo jugador cuando se recibe una respuesta del cliente proxy.
 * 
 * Forma parte de la cadena de responsabilidad para manejar diferentes tipos de respuestas.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaOtorgarTurno extends ManejadorRespuestaClienteProxy {

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor que inicializa el manejador con la lógica de la partida.
     * 
     * @param partidaDominoLogica la lógica de la partida de dominó.
     */
    public ManejadorRespuestaOtorgarTurno(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor que inicializa el manejador con la lógica de la partida y el siguiente manejador en la cadena.
     * 
     * @param partidaDominoLogica la lógica de la partida de dominó.
     * @param siguienteManejador el siguiente manejador en la cadena de responsabilidad.
     */
    public ManejadorRespuestaOtorgarTurno(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Verifica si este manejador puede procesar el evento recibido.
     * 
     * @param evento el evento de respuesta a evaluar.
     * @return {@code true} si el evento es una instancia de {@link RespuestaCambiarTurno}, {@code false} en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaCambiarTurno;
    }

    /**
     * Procesa el evento actualizando la lógica de la partida con el nuevo jugador en turno.
     * 
     * @param evento el evento de respuesta a procesar.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaCambiarTurno respuesta = (RespuestaCambiarTurno) evento;
        JugadorDominoDTO jugadorConNuevoTurno = respuesta.getJugadorDominoDTO();
        partidaDominoLogica.cambiarTurno(jugadorConNuevoTurno);
    }
}
