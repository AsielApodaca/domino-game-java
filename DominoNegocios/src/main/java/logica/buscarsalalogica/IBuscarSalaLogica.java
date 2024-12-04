/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.buscarsalalogica;

import dominodto.SalaDTO;
import listeners.IContenedorListener;

/**
 *
 * @author olive
 */
public interface IBuscarSalaLogica {
    
    IContenedorListener iniciar() ;
    
    void cerrar() ;
    
    boolean estaOperando() ;
    
    void mostrarSala(SalaDTO sala) ;
    
    void quitarSala(SalaDTO sala) ;
    
}
