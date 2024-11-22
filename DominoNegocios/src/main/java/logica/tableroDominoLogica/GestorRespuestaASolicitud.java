/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaActualizarCantidadFichas;
import domino.respuestas.RespuestaColocarFichaTablero;
import domino.listeners.IClientProxyListener;

/**
 *
 * @author castr
 */
public class GestorRespuestaASolicitud implements IClientProxyListener {

    @Override
    public void onRecibirRespuesta(EventoRespuesta eventoRespuesta) {
        if (eventoRespuesta instanceof RespuestaColocarFichaTablero) {

        } else if (eventoRespuesta instanceof RespuestaActualizarCantidadFichas) {

        } else if (eventoRespuesta instanceof RespuestaColocarFichaTablero) {

        } else {
            System.out.println("Respuesta no reconocida.");
        }
    }

    private void procesarRespuestaCasillaSeleccionada(EventoRespuesta eventoRespuesta) {
        
    }

}
