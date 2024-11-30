/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.JugadorDominoDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class RespuestaActualizarCantidadFichas extends EventoRespuesta {

    private JugadorDominoDTO jugadorDominoDTO; // Jugador que se le verá el cambio de fichas a todos, debe portar el id de este jugador
    private int cantidadFichas; // Cantidad de fichas que tiene ahora el jugador

    public RespuestaActualizarCantidadFichas(JugadorDominoDTO jugadorDominoDTO, int cantidadFichas) {
        super(null);
        this.jugadorDominoDTO = jugadorDominoDTO;
        this.cantidadFichas = cantidadFichas;
    }

    public JugadorDominoDTO getJugadorDominoDTO() {
        return jugadorDominoDTO;
    }

    public int getCantidadFichas() {
        return cantidadFichas;
    }

}
