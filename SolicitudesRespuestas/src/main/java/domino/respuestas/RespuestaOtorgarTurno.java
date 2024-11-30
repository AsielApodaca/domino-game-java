package domino.respuestas;

import dominodto.JugadorDominoDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
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
