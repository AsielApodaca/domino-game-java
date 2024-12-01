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
    public static final boolean ES_PARA_TODOS = true;
    private JugadorDominoDTO jugadorDominoDTO;

    public RespuestaRemoverJugadorPartida(JugadorDominoDTO jugadorDominoDTO) {
        super(null);
        this.jugadorDominoDTO = jugadorDominoDTO;
    }

    public JugadorDominoDTO getJugadorDominoDTO() {
        return jugadorDominoDTO;
    }
    
    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS;
    }
    
}

