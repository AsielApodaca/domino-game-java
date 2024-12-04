/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.buscarsalalogica;

import domino.fachada.IFachadaClienteProxy;
import domino.solicitudes.SolicitudObtenerSalasDisponibles;
import dominodto.SalaDTO;
import dominodto.UsuarioDTO;
import fachada.FachadaBuscarSala;
import fachada.IFachadaBuscarSala;
import listeners.IContenedorListener;
import listeners.IPresentacionBuscarSalaListener;
import logica.padrelogica.Logica;
import mapeodto.MapeadorDTO;
import setup.Setup;

/**
 *
 * @author olive
 */
public class BuscarSalaLogica extends Logica implements IBuscarSalaLogica, IPresentacionBuscarSalaListener {
    
    private IFachadaBuscarSala fachadaBuscarSala ;
    private IFachadaClienteProxy proxy ;
    
    public BuscarSalaLogica(Setup setup) {
        super(setup);
        proxy = setup.getFachadaClienteProxy() ;
        fachadaBuscarSala = new FachadaBuscarSala() ;
    }

    @Override
    public IContenedorListener iniciar() {
        this.operando = true ;
        mostrarPresentacionBuscarSala();
        enviarBuscarSalasDisponibles();
        return mvcController ;
    }

    private void mostrarPresentacionBuscarSala() {
        mvcController = fachadaBuscarSala.iniciarPantalla() ;
        fachadaBuscarSala.suscribirPresentacionListener(this);
    }
    
    @Override
    public void cerrar() {
        this.operando = false ;
        this.fachadaBuscarSala = null ;
    }

    @Override
    public boolean estaOperando() {
        return this.operando ;
    }

    @Override
    public void mostrarSala(SalaDTO sala) {
        fachadaBuscarSala.mostrarSala(sala) ;
    }

    @Override
    public void quitarSala(SalaDTO sala) {
        fachadaBuscarSala.ocultarSala(sala) ;
    }

    @Override
    public void onBtnUnirseSalaPresionado(SalaDTO sala) {
        
    }

    @Override
    public void onBtnSalirPresionado() {
        
    }
    
    private void enviarBuscarSalasDisponibles() {
        UsuarioDTO usuario = MapeadorDTO.UsuarioEntityADTO(setup.getGestorUsuario().getUsuario()) ;
        SolicitudObtenerSalasDisponibles solicitud = new SolicitudObtenerSalasDisponibles(usuario) ;
        fachadaClienteProxy.enviarSolicitud(solicitud);
    }
}
