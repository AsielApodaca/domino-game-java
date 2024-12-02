/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.JugadorDominoDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class RespuestaMostrarJugadoresPartida extends EventoRespuesta{
    public static final boolean ES_PARA_TODOS = false;
    private List<JugadorDominoDTO> jugadoresExternosEnOrden;
    
    public RespuestaMostrarJugadoresPartida(String idCliente, List<JugadorDominoDTO> jugadoresExternosEnOrden) {
        super(idCliente);
        this.jugadoresExternosEnOrden = jugadoresExternosEnOrden;
    }

    public List<JugadorDominoDTO> getJugadoresExternos() {
        return jugadoresExternosEnOrden;
    }
    
    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS;
    }
    
}
