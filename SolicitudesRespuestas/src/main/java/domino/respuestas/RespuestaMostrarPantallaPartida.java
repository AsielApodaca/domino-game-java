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
public class RespuestaMostrarPantallaPartida extends EventoRespuesta{
    
    private List<JugadorDominoDTO> jugadores;
    
    public RespuestaMostrarPantallaPartida(List<JugadorDominoDTO> jugadores) {
        super(null);
        this.jugadores = jugadores;
        
    }

    public List<JugadorDominoDTO> getJugadores() {
        return jugadores;
    }
    
    
    
}
