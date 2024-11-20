package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaAgregarFichaJugador;
import dominodto.FichaDominoDTO;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador para procesar eventos de tipo {@link RespuestaAgregarFichaJugador}.
 * 
 * Este manejador se encarga de actualizar la lógica de la partida para agregar 
 * una ficha específica al jugador local cuando se recibe una respuesta del cliente proxy.
 * 
 * Forma parte de la cadena de responsabilidad para manejar diferentes tipos de respuestas.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaAgregarFichaJugador extends ManejadorRespuestaClienteProxy {

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor que inicializa el manejador con la lógica de la partida.
     * 
     * @param partidaDominoLogica la lógica de la partida de dominó.
     */
    public ManejadorRespuestaAgregarFichaJugador(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor que inicializa el manejador con la lógica de la partida y el siguiente manejador en la cadena.
     * 
     * @param partidaDominoLogica la lógica de la partida de dominó.
     * @param siguienteManejador el siguiente manejador en la cadena de responsabilidad.
     */
    public ManejadorRespuestaAgregarFichaJugador(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Verifica si este manejador puede procesar el evento recibido.
     * 
     * @param evento el evento de respuesta a evaluar.
     * @return {@code true} si el evento es una instancia de {@link RespuestaAgregarFichaJugador}, {@code false} en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaAgregarFichaJugador;
    }

    /**
     * Procesa el evento agregando una ficha al jugador local en la lógica de la partida.
     * 
     * @param evento el evento de respuesta a procesar.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaAgregarFichaJugador respuesta = (RespuestaAgregarFichaJugador) evento;
        FichaDominoDTO fichaDomino = respuesta.getFichaDominoDTO();
        partidaDominoLogica.agregarFichaJugadorLocal(fichaDomino);
    }
}