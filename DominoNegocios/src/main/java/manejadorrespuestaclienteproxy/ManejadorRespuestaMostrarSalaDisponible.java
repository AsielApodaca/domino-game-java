/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaMostrarSalaDisponible;
import dominodto.SalaDTO;
import logica.buscarsalalogica.IBuscarSalaLogica;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 *
 * @author olive
 */
public class ManejadorRespuestaMostrarSalaDisponible extends ManejadorRespuestaClienteProxy {
    
    private IBuscarSalaLogica buscarSalaLogica ;
    
    public ManejadorRespuestaMostrarSalaDisponible(IBuscarSalaLogica buscarSalaLogica) {
        this.buscarSalaLogica = buscarSalaLogica ;
    }
    
    public ManejadorRespuestaMostrarSalaDisponible(IBuscarSalaLogica buscarSalaLogica, ManejadorRespuestaClienteProxy manejador) {
        super(manejador) ;
        this.buscarSalaLogica = buscarSalaLogica ;
    }

    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaMostrarSalaDisponible ;
    }

    @Override
    protected void procesar(EventoRespuesta evento) {
        if(buscarSalaLogica.estaOperando()) {
            RespuestaMostrarSalaDisponible respuesta = (RespuestaMostrarSalaDisponible) evento ;
            SalaDTO sala = respuesta.getSalaDisponible() ;
            buscarSalaLogica.mostrarSala(sala);
        }
    }
    
}
