package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaOcultarCasillasDisponibles;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador de la respuesta para ocultar las casillas disponibles en el tablero de la partida.
 * 
 * Este manejador se encarga de procesar las respuestas que indican que las casillas disponibles
 * deben ocultarse, lo cual puede ocurrir después de realizar una jugada o cambiar el estado del juego.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaOcultarCasillasDisponibles extends ManejadorRespuestaClienteProxy{

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor de la clase ManejadorRespuestaOcultarCasillasDisponibles.
     * 
     * @param partidaDominoLogica La lógica de la partida de dominó que gestionará la acción.
     */
    public ManejadorRespuestaOcultarCasillasDisponibles(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor con manejador de respuesta siguiente.
     * 
     * @param partidaDominoLogica La lógica de la partida de dominó.
     * @param siguienteManejador El siguiente manejador a ejecutar si este no puede procesar el evento.
     */
    public ManejadorRespuestaOcultarCasillasDisponibles(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }
    
    /**
     * Verifica si el evento es una instancia de {@link RespuestaOcultarCasillasDisponibles}.
     * 
     * @param evento El evento que se quiere verificar.
     * @return {@code true} si el evento es una respuesta para ocultar las casillas disponibles.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaOcultarCasillasDisponibles;
    }

    /**
     * Procesa el evento {@link RespuestaOcultarCasillasDisponibles}.
     * 
     * Este método llama a la lógica de la partida para ocultar las casillas disponibles en el tablero.
     * 
     * @param evento El evento que contiene la información de la respuesta.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        partidaDominoLogica.ocultarCasillasDisponibles();
    }
}
