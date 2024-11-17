/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.FichaDominoDTO;

/**
 *
 * @author castr
 */
public class RespuestaQuitarFichaJugador extends EventoRespuesta {
    private FichaDominoDTO fichaDominoDTO;

    public RespuestaQuitarFichaJugador(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
    }

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }
    
}
