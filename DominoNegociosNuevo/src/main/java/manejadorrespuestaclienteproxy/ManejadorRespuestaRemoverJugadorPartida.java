package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaRemoverJugadorPartida;
import dominodto.JugadorDominoDTO;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador de respuesta que procesa eventos de tipo {@link RespuestaRemoverJugadorPartida}.
 * Este manejador extrae la información del jugador a eliminar de la respuesta y la envía
 * a la lógica de la partida de dominó para su eliminación.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaRemoverJugadorPartida extends ManejadorRespuestaClienteProxy {

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor que inicializa la lógica de la partida.
     * 
     * @param partidaDominoLogica instancia de la lógica de la partida de dominó.
     */
    public ManejadorRespuestaRemoverJugadorPartida(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor que inicializa la lógica de la partida y el siguiente manejador en la cadena de responsabilidad.
     * 
     * @param partidaDominoLogica instancia de la lógica de la partida de dominó.
     * @param siguienteManejador manejador siguiente en la cadena de responsabilidad.
     */
    public ManejadorRespuestaRemoverJugadorPartida(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }
    
    /**
     * Verifica si el manejador puede procesar el evento recibido.
     * 
     * @param evento el evento de respuesta a evaluar.
     * @return true si el evento es una instancia de {@link RespuestaRemoverJugadorPartida}, false de lo contrario.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaRemoverJugadorPartida;
    }

    /**
     * Procesa el evento de respuesta extrayendo la información del jugador a eliminar y enviándola
     * a la lógica de la partida para su eliminación.
     * 
     * @param evento el evento de respuesta a procesar.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaRemoverJugadorPartida respuesta = (RespuestaRemoverJugadorPartida) evento;
        JugadorDominoDTO jugadorDominoDTO = respuesta.getJugadorDominoDTO();
        partidaDominoLogica.removerJugadorDePartida(jugadorDominoDTO);
    }
}
