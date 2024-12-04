package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaMostrarJugadoresPartida;
import dominodto.JugadorDominoDTO;
import java.util.List;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador de respuesta que procesa eventos de tipo {@link RespuestaMostrarJugadoresPartida}.
 * Este manejador extrae la lista de jugadores de la respuesta y la envía a la lógica de la
 * partida de dominó para su visualización.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaMostrarJugadoresPartida extends ManejadorRespuestaClienteProxy {

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor que inicializa la lógica de la partida.
     * 
     * @param partidaDominoLogica instancia de la lógica de la partida de dominó.
     */
    public ManejadorRespuestaMostrarJugadoresPartida(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor que inicializa la lógica de la partida y el siguiente manejador en la cadena de responsabilidad.
     * 
     * @param partidaDominoLogica instancia de la lógica de la partida de dominó.
     * @param siguienteManejador manejador siguiente en la cadena de responsabilidad.
     */
    public ManejadorRespuestaMostrarJugadoresPartida(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }
    
    /**
     * Verifica si el manejador puede procesar el evento recibido.
     * 
     * @param evento el evento de respuesta a evaluar.
     * @return true si el evento es una instancia de {@link RespuestaMostrarJugadoresPartida}, false de lo contrario.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaMostrarJugadoresPartida;
    }

    /**
     * Procesa el evento de respuesta extrayendo la lista de jugadores y enviándola a la lógica de la partida.
     * 
     * @param evento el evento de respuesta a procesar.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaMostrarJugadoresPartida respuesta = (RespuestaMostrarJugadoresPartida) evento;
        List<JugadorDominoDTO> listaJugadores = respuesta.getJugadoresExternos();
        partidaDominoLogica.mostrarJugadoresDePartida(listaJugadores);
    }
}
