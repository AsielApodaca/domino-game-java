/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package notificador;

import listeners.IPresentacionPartidaDominoListener;
import notificador.eventos.CasillaSeleccionadaEvento;
import notificador.eventos.FichaSeleccionadaEvento;
import notificador.eventos.PozoSeleccionadoEvento;

/**
 *
 * @author asielapodaca
 */
public interface IPresentacionNotificadorManager {

    public void suscribirPresentacionListener(IPresentacionPartidaDominoListener listener);

    public void notificarCasillaSeleccionada(CasillaSeleccionadaEvento evento);

    public void notificarFichaSeleccionada(FichaSeleccionadaEvento evento);
    
    public void notificarPozoSeleccionado(PozoSeleccionadoEvento evento);
}
