/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.CasillaDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class RespuestaColocarFichaTablero extends EventoRespuesta {
    private CasillaDTO casillaDTO;

    public RespuestaColocarFichaTablero(CasillaDTO casillaDTO) {
        super(null);
        this.casillaDTO = casillaDTO;
    }

    public CasillaDTO getCasillaDTO() {
        return casillaDTO;
    }
    
    
}
