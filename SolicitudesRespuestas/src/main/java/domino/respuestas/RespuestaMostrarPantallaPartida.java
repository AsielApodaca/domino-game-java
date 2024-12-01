/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.JugadorDominoDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class RespuestaMostrarPantallaPartida extends EventoRespuesta{
    public static final boolean ES_PARA_TODOS = true;
    private List<JugadorDominoDTO> jugadores;
    
    public RespuestaMostrarPantallaPartida(List<JugadorDominoDTO> jugadores) {
        super(null);
        this.jugadores = jugadores;
        
    }

    public List<JugadorDominoDTO> getJugadores() {
        return jugadores;
    }
    
    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS;
    }
    
}
