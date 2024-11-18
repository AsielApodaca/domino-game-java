/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.contenedorpantallaslogica;

import fachada.FachadaContenedorPantallas;
import fachada.IFachadaContenedorPantallas;
import listeners.IContenedorListener;

/**
 *
 * @author asielapodaca
 */
public class ContenedorPantallasLogica implements IContenedorPantallasLogica{
    
    private IFachadaContenedorPantallas fachadaContenedorPantallas;

    public ContenedorPantallasLogica() {
        this.fachadaContenedorPantallas = new FachadaContenedorPantallas();
    }
    
    @Override
    public void iniciarContenedorDePantallas() {
        fachadaContenedorPantallas.iniciarContenedorDePantallas();
    }

    @Override
    public void abrirPantalla(IContenedorListener pantalla) {
        fachadaContenedorPantallas.establecerPantalla(pantalla);
    }
    
}
