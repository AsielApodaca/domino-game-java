/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import dominodto.SalaDTO;
import listeners.IContenedorListener;
import listeners.IPresentacionBuscarSalaListener;
import mvcbuscarsalas.BuscarSalaController;
import mvcbuscarsalas.BuscarSalaModel;
import mvcbuscarsalas.BuscarSalaView;
import notificador.IPresentacionBuscarSalaNotificador;
import notificador.PresentacionBuscarSalaNotificador;

/**
 *
 * @author olive
 */
public class FachadaBuscarSala implements IFachadaBuscarSala {

    private BuscarSalaController controller ;
    private BuscarSalaModel model ;
    private BuscarSalaView view ;
    private IPresentacionBuscarSalaNotificador buscarSalaNotificador ;
    
    public FachadaBuscarSala() {
        
    }
    
    @Override
    public IContenedorListener iniciarPantalla() {
        this.buscarSalaNotificador = new PresentacionBuscarSalaNotificador() ;
        this.model = new BuscarSalaModel() ;
        this.view = new BuscarSalaView(model) ; 
        this.controller = new BuscarSalaController(view, model) ;
        controller.setBuscarSalaNotificador(buscarSalaNotificador);
        view.setViewListener(controller);
        return controller ;
    }

    @Override
    public void suscribirPresentacionListener(IPresentacionBuscarSalaListener listener) {
        buscarSalaNotificador.suscribirPresentacionListener(listener);
    }

    @Override
    public void mostrarSala(SalaDTO sala) {
        controller.mostrarSala(sala);
    }

    @Override
    public void ocultarSala(SalaDTO sala) {
        controller.ocultarSala(sala);
    }
    
}
