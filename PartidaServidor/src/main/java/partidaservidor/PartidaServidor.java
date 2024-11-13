/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidaservidor;

import domino.respuestas.EventoRespuesta;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaServidor {

    public void runRequest(EventoSolicitud eventoSolicitud) {
        if (eventoSolicitud instanceof SolicitudCasillaSeleccionada solicitudCasillaSeleccionada) {
            PartidaServidorSolicitudCasillaSeleccionada partidaServidorSolicitudCasillaSeleccionada
                    = new PartidaServidorSolicitudCasillaSeleccionada();

            partidaServidorSolicitudCasillaSeleccionada.colocarFichaSeleccionadaEnTableroEntity(
                    solicitudCasillaSeleccionada
                    .getCasillaDTO());
        } 

    }

    public void sendResponse(EventoRespuesta eventorespuesta) {
        
    }
}
