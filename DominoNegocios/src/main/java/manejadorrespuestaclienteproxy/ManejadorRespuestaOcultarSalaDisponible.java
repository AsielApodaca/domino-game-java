/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaOcultarSalaDisponible;
import dominodto.SalaDTO;
import logica.buscarsalalogica.IBuscarSalaLogica;

/**
 *
 * @author olive
 */
public class ManejadorRespuestaOcultarSalaDisponible extends ManejadorRespuestaClienteProxy {
    
    private IBuscarSalaLogica buscarSalaLogica ;
    
    public ManejadorRespuestaOcultarSalaDisponible(IBuscarSalaLogica buscarSalaLogica) {
        this.buscarSalaLogica = buscarSalaLogica ;
    }
    
    public ManejadorRespuestaOcultarSalaDisponible(IBuscarSalaLogica buscarSalaLogica, ManejadorRespuestaClienteProxy manejador) {
        super(manejador) ;
        this.buscarSalaLogica = buscarSalaLogica ;
    }

    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaOcultarSalaDisponible ;
    }

    @Override
    protected void procesar(EventoRespuesta evento) {
        if(buscarSalaLogica.estaOperando()) {
            RespuestaOcultarSalaDisponible respuesta = (RespuestaOcultarSalaDisponible) evento ;
            SalaDTO sala = respuesta.getSalaDisponible() ;
            buscarSalaLogica.quitarSala(sala);
        }
    }
    
}
