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
import logica.padrelogica.Logica;
import setup.Setup;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MenuDominoLogica extends Logica implements IMenuDominoLogica, IPresentacionMenuDominoListener {

    private IFachadaClienteProxy fachadaClienteProxy;
    private IFachadaMenuDomino fachadaMenuDomino;

    public MenuDominoLogica(Setup setup) {
        super(setup);
        fachadaClienteProxy = setup.getFachadaClienteProxy();
        
    }

    @Override
    public IContenedorListener iniciar() {
        this.operando = true;
        fachadaMenuDomino = new FachadaMenuDomino();
        mostrarPresentacionMenu();

        return mvcController;
    }
    
    @Override
    public void cerrar() {
        mvcController = null;
        this.operando = false;
        fachadaMenuDomino = null;
    }
    
    public boolean estaOperando() {
        return this.operando;
    }

    private void mostrarPresentacionMenu() {
        this.mvcController = fachadaMenuDomino.iniciarPantalla();
        fachadaMenuDomino.suscribirPresentacionListener(this);
    }

    @Override
    public void onCrearSala() {
        setup.getMediadorNegocio().irASalaEspera();
        setup.getMediadorNegocio().crearSala();
    }

    @Override
    public void onUnirseSala() {
        setup.getMediadorNegocio().irABuscarSala();
    }

}
