/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package notificador;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import listeners.IPresentacionPartidaDominoListener;


/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPresentacionPartidaDominoNotificador {
    public void suscribirPresentacionListener(IPresentacionPartidaDominoListener listener);

    public void notificarCasillaSeleccionada(CasillaDTO casillaSeleccionada);

    public void notificarFichaSeleccionada(FichaDominoDTO fichaSelecccionada);

    public void notificarPozoSeleccionado();
}
