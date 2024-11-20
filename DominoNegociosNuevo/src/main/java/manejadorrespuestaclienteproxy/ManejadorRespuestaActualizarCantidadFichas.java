package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaActualizarCantidadFichas;
import dominodto.JugadorDominoDTO;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 * Manejador para procesar eventos de tipo {@link RespuestaActualizarCantidadFichas}.
 * 
 * Este manejador actualiza la cantidad de fichas de un jugador en la lógica de la partida
 * cuando recibe una respuesta del cliente proxy que contiene esta información.
 * 
 * Forma parte de la cadena de responsabilidad para manejar diferentes tipos de respuestas.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaActualizarCantidadFichas extends ManejadorRespuestaClienteProxy {

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor que inicializa el manejador con la lógica de la partida.
     * 
     * @param partidaDominoLogica la lógica de la partida de dominó.
     */
    public ManejadorRespuestaActualizarCantidadFichas(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Constructor que inicializa el manejador con la lógica de la partida y el siguiente manejador en la cadena.
     * 
     * @param partidaDominoLogica la lógica de la partida de dominó.
     * @param siguienteManejador el siguiente manejador en la cadena de responsabilidad.
     */
    public ManejadorRespuestaActualizarCantidadFichas(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }

    /**
     * Verifica si este manejador puede procesar el evento recibido.
     * 
     * @param evento el evento de respuesta a evaluar.
     * @return {@code true} si el evento es una instancia de {@link RespuestaActualizarCantidadFichas}, {@code false} en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaActualizarCantidadFichas;
    }

    /**
     * Procesa el evento actualizando la cantidad de fichas de un jugador en la lógica de la partida.
     * 
     * @param evento el evento de respuesta a procesar.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaActualizarCantidadFichas respuesta = (RespuestaActualizarCantidadFichas) evento;
        JugadorDominoDTO jugador = respuesta.getJugadorDominoDTO();
        int cantidadFichas = respuesta.getCantidadFichas();
        partidaDominoLogica.actualizarCantidadFichasDeJugador(jugador, cantidadFichas);
    }
}
