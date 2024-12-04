/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domino.listeners;

import domino.solicitudes.EventoSolicitud;

/**
 *
 * @author castr
 */
public interface IServidorProxyListener {

    public void onRecibirSolicitud(EventoSolicitud eventoSolicitud);
}
