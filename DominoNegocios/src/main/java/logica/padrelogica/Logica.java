/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.padrelogica;

import domino.fachada.IFachadaClienteProxy;
import listeners.IContenedorListener;
import setup.Setup;

/**
 *
 * @author olive
 */
public abstract class Logica {
    
    protected boolean operando ;
    protected IContenedorListener mvcController ;
    protected Setup setup ;
    protected IFachadaClienteProxy fachadaClienteProxy ;
    
    public Logica(Setup setup) {
        this.setup = setup ;
        this.fachadaClienteProxy = setup.getFachadaClienteProxy() ;
    }
    
}
