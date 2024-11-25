package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaColocarFichaTablero;
import dominodto.CasillaDTO;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador para procesar eventos de tipo {@link RespuestaColocarFichaTablero}.
 * 
 * Este manejador se encarga de actualizar la visualización del tablero en la lógica de la partida
 * cuando se recibe una respuesta del cliente proxy que contiene la información de una nueva ficha colocada.
 * 
 * Forma parte de la cadena de responsabilidad para manejar diferentes tipos de respuestas.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaColocarFichaTablero extends ManejadorRespuestaClienteProxy {

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor que inicializa el manejador con la lógica de la partida.
     * 
     * @param partidaDominoLogica la lógica de la partida de dominó.
     */
    public ManejadorRespuestaColocarFichaTablero(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor que inicializa el manejador con la lógica de la partida y el siguiente manejador en la cadena.
     * 
     * @param partidaDominoLogica la lógica de la partida de dominó.
     * @param siguienteManejador el siguiente manejador en la cadena de responsabilidad.
     */
    public ManejadorRespuestaColocarFichaTablero(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Verifica si este manejador puede procesar el evento recibido.
     * 
     * @param evento el evento de respuesta a evaluar.
     * @return {@code true} si el evento es una instancia de {@link RespuestaColocarFichaTablero}, {@code false} en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaColocarFichaTablero;
    }

    /**
     * Procesa el evento actualizando el tablero con la nueva ficha colocada.
     * 
     * @param evento el evento de respuesta a procesar.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaColocarFichaTablero respuesta = (RespuestaColocarFichaTablero) evento;
        CasillaDTO fichaTablero = respuesta.getCasillaDTO();
        partidaDominoLogica.mostrarFichaTablero(fichaTablero);
    }
}
