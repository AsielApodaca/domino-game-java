/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import domino.listeners.IProxyListener;
import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaActualizarCantidadFichas;
import domino.respuestas.RespuestaAgregarFichaTablero;

/**
 *
 * @author castr
 */
public class GestorRespuestaASolicitud implements IProxyListener {

    @Override
    public void onRecibirRespuesta(EventoRespuesta eventoRespuesta) {
        if (eventoRespuesta instanceof RespuestaAgregarFichaTablero) {

        } else if (eventoRespuesta instanceof RespuestaActualizarCantidadFichas) {

        } else if (eventoRespuesta instanceof RespuestaAgregarFichaTablero) {

        } else {
            System.out.println("Respuesta no reconocida.");
        }
    }

    private void procesarRespuestaCasillaSeleccionada(EventoRespuesta eventoRespuesta) {

    }

}
