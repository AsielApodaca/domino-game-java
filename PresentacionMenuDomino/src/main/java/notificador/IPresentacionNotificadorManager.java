/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package notificador;

import listeners.IPresentacionMenuDominoListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPresentacionNotificadorManager {

    public void suscribirPresentacionListener(IPresentacionMenuDominoListener listener);

    public void notificarCrearSala();

    public void notificarUnirseSala();

}
