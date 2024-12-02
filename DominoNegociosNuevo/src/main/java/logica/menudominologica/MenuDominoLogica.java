/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.menudominologica;

import domino.fachada.IFachadaClienteProxy;
import fachada.FachadaMenuDomino;
import fachada.IFachadaMenuDomino;
import listeners.IContenedorListener;
import listeners.IPresentacionMenuDominoListener;
import setup.Setup;

/**
 *
 * @author asielapodaca
 */
public class MenuDominoLogica implements IMenuDominoLogica, IPresentacionMenuDominoListener{

    private IContenedorListener contenedorListener;
    private Setup setup;
    private IFachadaClienteProxy fachadaClienteProxy;
    private IFachadaMenuDomino fachadaMenuDomino;

    public MenuDominoLogica(Setup setup) {
        this.setup = setup;
        fachadaClienteProxy = setup.getFachadaClienteProxy();
        fachadaMenuDomino = new FachadaMenuDomino();
    }
    
    
    @Override
    public IContenedorListener iniciar() {
        mostrarPresentacionMenu();
        
        return contenedorListener;
    }
    
    private void mostrarPresentacionMenu() {
        this.contenedorListener = fachadaMenuDomino.iniciarPantalla();
        fachadaMenuDomino.suscribirPresentacionListener(this);
    }

    @Override
    public void onCrearSala() {
        setup.getMediadorNegocio().irASalaEspera();
        setup.getMediadorNegocio().crearSala();
    }

    @Override
    public void onUnirseSala() {
        setup.getMediadorNegocio().irASalaEspera();
        setup.getMediadorNegocio().unirseSala();
    }
    
}
