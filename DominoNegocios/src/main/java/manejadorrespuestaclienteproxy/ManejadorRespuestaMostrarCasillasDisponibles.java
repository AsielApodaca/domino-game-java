package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaMostrarCasillasDisponibles;
import dominodto.CasillaDTO;
import java.util.List;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador que procesa las respuestas relacionadas con las casillas disponibles
 * para colocar fichas en el tablero.
 * Este manejador recibe una respuesta de tipo {@link RespuestaMostrarCasillasDisponibles}
 * y comunica las casillas disponibles a la lógica de la partida.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaMostrarCasillasDisponibles extends ManejadorRespuestaClienteProxy {
    
    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor principal del manejador, que establece la lógica de la partida.
     *
     * @param partidaDominoLogica La lógica que maneja las acciones en la partida.
     */
    public ManejadorRespuestaMostrarCasillasDisponibles(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor que permite encadenar manejadores adicionales.
     *
     * @param partidaDominoLogica La lógica que maneja las acciones en la partida.
     * @param siguienteManejador El siguiente manejador en la cadena de responsabilidad.
     */
    public ManejadorRespuestaMostrarCasillasDisponibles(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Verifica si este manejador puede procesar un evento de respuesta.
     *
     * @param evento El evento de respuesta a evaluar.
     * @return true si el evento es una instancia de {@link RespuestaMostrarCasillasDisponibles}, 
     *         de lo contrario, false.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaMostrarCasillasDisponibles;
    }

    /**
     * Procesa la respuesta de casillas disponibles y las pasa a la lógica de la partida.
     *
     * @param evento El evento de respuesta que contiene las casillas disponibles.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaMostrarCasillasDisponibles respuesta = (RespuestaMostrarCasillasDisponibles) evento;
        List<CasillaDTO> casillasDisponibles = respuesta.getCasillas();
        partidaDominoLogica.mostrarCasillasDisponibles(casillasDisponibles);
    }
}
