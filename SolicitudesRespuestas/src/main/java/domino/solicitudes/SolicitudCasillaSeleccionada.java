/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.solicitudes;

import dominodto.CasillaDTO;

/**
 *
 * @author castr
 */
public class SolicitudCasillaSeleccionada extends EventoSolicitud {

    private CasillaDTO casillaDTO;

    public SolicitudCasillaSeleccionada(CasillaDTO casillaDTO) {
        this.casillaDTO = casillaDTO;
    }

    public CasillaDTO getCasillaDTO() {
        return casillaDTO;
    }

}
