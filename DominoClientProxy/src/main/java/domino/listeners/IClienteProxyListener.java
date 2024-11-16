/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domino.listeners;

import domino.respuestas.EventoRespuesta;

/**
 *
 * @author castr
 */
public interface IClienteProxyListener {

    public void onRecibirRespuesta(EventoRespuesta eventoRespuesta);
}
