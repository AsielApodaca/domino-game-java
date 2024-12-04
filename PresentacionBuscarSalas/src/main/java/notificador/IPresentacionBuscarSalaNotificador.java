/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package notificador;

import dominodto.SalaDTO;
import listeners.IPresentacionBuscarSalaListener;

/**
 *
 * @author olive
 */
public interface IPresentacionBuscarSalaNotificador {
    
    void suscribirPresentacionListener(IPresentacionBuscarSalaListener listener) ;
    
    void notificarBtnUnirseSalaPresionado(SalaDTO sala) ;
    
    void notificarBtnSalirPresionado() ;
}
