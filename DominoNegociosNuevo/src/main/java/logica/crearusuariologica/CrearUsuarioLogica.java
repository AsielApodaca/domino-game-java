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
import setup.Setup;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class CrearUsuarioLogica implements ICrearUsuarioLogica, IPresentacionCrearUsuarioListener {

    private IContenedorListener contenedorListener;
    private Setup setup;
    private IFachadaClienteProxy fachadaClienteProxy;
    private IFachadaCrearUsuario fachadaCrearUsuario;

    public CrearUsuarioLogica(Setup setup) {
        this.setup = setup;
        fachadaClienteProxy = setup.getFachadaClienteProxy();
        fachadaCrearUsuario = new FachadaCrearUsuario();
    }

    @Override
    public IContenedorListener iniciar() {
        mostrarPresentacionCrearUsuario();
        return contenedorListener;
    }

    private void mostrarPresentacionCrearUsuario() {
        this.contenedorListener = fachadaCrearUsuario.iniciarPantalla();
        fachadaCrearUsuario.suscribirPresentacionListener(this);
    }

    @Override
    public void onCrearUsuario(String nombreUsuario, String rutaIcono) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
