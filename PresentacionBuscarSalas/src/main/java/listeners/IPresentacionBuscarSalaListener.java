/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package listeners;

import dominodto.SalaDTO;

/**
 *
 * @author olive
 */
public interface IPresentacionBuscarSalaListener {
    
    void onBtnUnirseSalaPresionado(SalaDTO sala) ;
    
    void onBtnSalirPresionado() ;
    
}
