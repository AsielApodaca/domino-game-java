/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.solicitudes;

import dominodto.CasillaDTO;
import dominodto.JugadorDominoDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SolicitudCasillaSeleccionada extends EventoSolicitud {

    private CasillaDTO casillaDTO;
    private JugadorDominoDTO jugadorDominoDTO;


    public SolicitudCasillaSeleccionada(CasillaDTO casillaDTO, JugadorDominoDTO jugadorDominoDTO) {
        this.casillaDTO = casillaDTO;
        this.jugadorDominoDTO = jugadorDominoDTO;
    }

    public CasillaDTO getCasillaDTO() {
        return casillaDTO;
    }

}
