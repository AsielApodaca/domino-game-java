package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaMostrarFichasActualizadasDeJugador;
import dominodto.FichaDominoDTO;
import java.util.List;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador que procesa las respuestas relacionadas con las fichas actualizadas de un jugador.
 * Este manejador recibe una respuesta de tipo {@link RespuestaMostrarFichasActualizadasDeJugador}
 * y comunica las fichas actualizadas al sistema para mostrarlas en la interfaz del jugador.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaMostrarFichasActualizadasDeJugador extends ManejadorRespuestaClienteProxy {
    
    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor principal del manejador, que establece la lógica de la partida.
     *
     * @param partidaDominoLogica La lógica que maneja las acciones en la partida.
     */
    public ManejadorRespuestaMostrarFichasActualizadasDeJugador(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor que permite encadenar manejadores adicionales.
     *
     * @param partidaDominoLogica La lógica que maneja las acciones en la partida.
     * @param siguienteManejador El siguiente manejador en la cadena de responsabilidad.
     */
    public ManejadorRespuestaMostrarFichasActualizadasDeJugador(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Verifica si este manejador puede procesar un evento de respuesta.
     *
     * @param evento El evento de respuesta a evaluar.
     * @return true si el evento es una instancia de {@link RespuestaMostrarFichasActualizadasDeJugador}, 
     *         de lo contrario, false.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaMostrarFichasActualizadasDeJugador;
    }

    /**
     * Procesa la respuesta de fichas actualizadas del jugador y las pasa a la lógica de la partida.
     *
     * @param evento El evento de respuesta que contiene las fichas actualizadas del jugador.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaMostrarFichasActualizadasDeJugador respuesta = (RespuestaMostrarFichasActualizadasDeJugador) evento;
        List<FichaDominoDTO> fichas = respuesta.getFichasDomino();
        partidaDominoLogica.mostrarFichasJugadorLocal(fichas);
    }
}
