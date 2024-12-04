/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.crearusuariologica;

import domino.fachada.IFachadaClienteProxy;
import fachada.FachadaCrearUsuario;
import fachada.IFachadaCrearUsuario;
import listeners.IContenedorListener;
import listeners.IPresentacionCrearUsuarioListener;
import logica.padrelogica.Logica;
import setup.Setup;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class CrearUsuarioLogica extends Logica implements ICrearUsuarioLogica, IPresentacionCrearUsuarioListener {

    private IContenedorListener contenedorListener;
    private Setup setup;
    private IFachadaClienteProxy fachadaClienteProxy;
    private IFachadaCrearUsuario fachadaCrearUsuario;

    public CrearUsuarioLogica(Setup setup) {
        super(setup);
        fachadaClienteProxy = setup.getFachadaClienteProxy();
        
    }

    @Override
    public IContenedorListener iniciar() {
        this.operando = true;
        fachadaCrearUsuario = new FachadaCrearUsuario();
        mostrarPresentacionCrearUsuario();
        return contenedorListener;
    }
    
    @Override
    public void cerrar() {
        this.operando = false;
        this.mvcController = null;
        this.fachadaCrearUsuario = null;
    }    
    @Override
    public boolean estaOperando() {
        return this.operando;
    }

    private void mostrarPresentacionCrearUsuario() {
        this.contenedorListener = fachadaCrearUsuario.iniciarPantalla();
        fachadaCrearUsuario.suscribirPresentacionListener(this);
    }

    @Override
    public void onCrearUsuario(String nombreUsuario, String rutaIcono) {
        setup.iniciarGestoresConUsuario(nombreUsuario, rutaIcono);
        setup.getMediadorNegocio().irAMenu();
    }

}
