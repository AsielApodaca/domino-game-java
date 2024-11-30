/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.JugadorDominoDTO;

/**
 *
 * @author asielapodaca
 */
public class RespuestaRemoverJugadorPartida extends EventoRespuesta{
    
    private JugadorDominoDTO jugadorDominoDTO;

    public RespuestaRemoverJugadorPartida(JugadorDominoDTO jugadorDominoDTO, String idCliente) {
        super(idCliente);
        this.jugadorDominoDTO = jugadorDominoDTO;
    }

    public JugadorDominoDTO getJugadorDominoDTO() {
        return jugadorDominoDTO;
    }
    
    
}

