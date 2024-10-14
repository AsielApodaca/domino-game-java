/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificaciones;

import listeners.IPresentacionListener;
import notificaciones.eventos.Evento;

/**
 *
 * @author asielapodaca
 */
public class PresentacionNotificacionesManager implements IPresentacionNotificacionesManager{
    private IPresentacionListener presentacionListener; // TableroDominoLogica

    @Override
    public void setPresentacionListener(IPresentacionListener presentacionListener) {
        this.presentacionListener = presentacionListener;
    }
    
    public void notificarCambioAPresentacionListener(Evento evento) {
        presentacionListener.onPresentacionCambio(evento);
    }
}
