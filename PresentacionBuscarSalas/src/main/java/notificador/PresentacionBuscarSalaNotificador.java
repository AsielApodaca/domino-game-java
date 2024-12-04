/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificador;

import dominodto.SalaDTO;
import listeners.IPresentacionBuscarSalaListener;

/**
 *
 * @author olive
 */
public class PresentacionBuscarSalaNotificador implements IPresentacionBuscarSalaNotificador {

    private IPresentacionBuscarSalaListener presentacionListener ;
    
    @Override
    public void suscribirPresentacionListener(IPresentacionBuscarSalaListener listener) {
        this.presentacionListener = listener ;
    }

    @Override
    public void notificarBtnUnirseSalaPresionado(SalaDTO sala) {
        presentacionListener.onBtnUnirseSalaPresionado(sala);
    }

    @Override
    public void notificarBtnSalirPresionado() {
        presentacionListener.onBtnSalirPresionado();
    }
    
}
