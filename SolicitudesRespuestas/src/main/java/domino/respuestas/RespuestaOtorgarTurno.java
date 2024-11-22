
package domino.respuestas;

import dominodto.JugadorDominoDTO;

/**
 *
 * @author castr
 */
public class RespuestaOtorgarTurno extends EventoRespuesta {
    // Jugador que se le consederá el turno de colocar ficha, debe portar el id de este jugador
    private JugadorDominoDTO jugadorDominoDTO;

    public RespuestaOtorgarTurno(JugadorDominoDTO jugadorDominoDTO) {
        super(null);
        this.jugadorDominoDTO = jugadorDominoDTO;
    }

    public JugadorDominoDTO getJugadorDominoDTO() {
        return jugadorDominoDTO;
    }
}
