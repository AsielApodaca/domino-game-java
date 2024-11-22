/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorsolicitudserverproxy;

import domino.solicitudes.EventoSolicitud;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.IPartidaServerLogica;

/**
 *
 * @author asielapodaca
 */
public abstract class ManejadorSolicitudServerProxy {
    
    private static final Logger LOG = Logger.getLogger(ManejadorSolicitudServerProxy.class.getName());
    private ManejadorSolicitudServerProxy siguienteManejador;
    private IPartidaServerLogica partidaServerLogica;
    
    

    public ManejadorSolicitudServerProxy(IPartidaServerLogica partidaServerLogica) {
        this.partidaServerLogica = partidaServerLogica;
    }

    public ManejadorSolicitudServerProxy(ManejadorSolicitudServerProxy siguienteManejador, IPartidaServerLogica partidaServerLogica) {
        this.siguienteManejador = siguienteManejador;
        this.partidaServerLogica = partidaServerLogica;
    }
    
    public void manejar(EventoSolicitud evento) {
        if (puedeManejar(evento)) {
            procesar(evento);
        } else if (siguienteManejador != null) {
            siguienteManejador.manejar(evento);
        } else {
            LOG.log(Level.SEVERE, "Se recibió una solicitud del ServerProxy y no se encontró un manejador para esta solicitud.");
        }
    }
    
    public abstract boolean puedeManejar(EventoSolicitud evento);
    
    public abstract void procesar(EventoSolicitud evento);
}
