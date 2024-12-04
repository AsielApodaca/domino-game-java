/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachada;

import dominodto.SalaDTO;
import listeners.IContenedorListener;
import listeners.IPresentacionBuscarSalaListener;

/**
 *
 * @author olive
 */
public interface IFachadaBuscarSala {
    
    IContenedorListener iniciarPantalla() ;
    
    void suscribirPresentacionListener(IPresentacionBuscarSalaListener listener) ;
    
    void mostrarSala(SalaDTO sala) ;
    
    void ocultarSala(SalaDTO sala) ;
    
}
