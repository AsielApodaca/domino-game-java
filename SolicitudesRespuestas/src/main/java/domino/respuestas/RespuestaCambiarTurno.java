/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.JugadorDominoDTO;

/**
 *
 * @author castr
 */
public class RespuestaCambiarTurno extends EventoRespuesta {
    // Jugador que se le consederá el turno de colocar ficha, debe portar el id de este jugador
    private JugadorDominoDTO jugadorDominoDTO;

    public RespuestaCambiarTurno(JugadorDominoDTO jugadorDominoDTO) {
        this.jugadorDominoDTO = jugadorDominoDTO;
    }

    public JugadorDominoDTO getJugadorDominoDTO() {
        return jugadorDominoDTO;
    }
}
