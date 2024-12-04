/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorsolicitudserverproxy;

import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudObtenerSalasDisponibles;
import dominodto.SalaDTO;
import logica.IPartidaServerLogica;

/**
 *
 * @author olive
 */
public class ManejadorSolicitudObtenerSalasDisponibles extends ManejadorSolicitudServerProxy {

    public ManejadorSolicitudObtenerSalasDisponibles(IPartidaServerLogica partidaServerLogica) {
        super(partidaServerLogica);
    }
    
    public ManejadorSolicitudObtenerSalasDisponibles(ManejadorSolicitudServerProxy manejador, IPartidaServerLogica partidaServerLogica) {
        super(manejador, partidaServerLogica);
    }

    @Override
    protected boolean puedeManejar(EventoSolicitud evento) {
        return evento instanceof SolicitudObtenerSalasDisponibles ;
    }

    @Override
    protected void procesar(EventoSolicitud evento) {
        partidaServerLogica.procesarMostrarSalaDisponible();
    }
    
}
