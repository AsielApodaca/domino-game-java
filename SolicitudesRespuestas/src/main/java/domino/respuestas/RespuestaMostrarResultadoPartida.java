/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.JugadorDominoDTO;

/**
 *
 * @author olive
 */
public class RespuestaMostrarResultadoPartida extends EventoRespuesta {

    private boolean ES_PARA_TODOS = true ;
    private JugadorDominoDTO ganador ;
    
    public RespuestaMostrarResultadoPartida(JugadorDominoDTO ganador) {
        super(null);
        this.ganador = ganador ;
    }

    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS ;
    }

    public boolean isES_PARA_TODOS() {
        return ES_PARA_TODOS;
    }

    public void setES_PARA_TODOS(boolean ES_PARA_TODOS) {
        this.ES_PARA_TODOS = ES_PARA_TODOS;
    }

    public JugadorDominoDTO getGanador() {
        return ganador;
    }

    public void setGanador(JugadorDominoDTO ganador) {
        this.ganador = ganador;
    }
    
    
}
