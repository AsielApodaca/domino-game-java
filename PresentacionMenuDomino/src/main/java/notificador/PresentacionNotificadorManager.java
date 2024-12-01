/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class PresentacionNotificadorManager implements IPresentacionNotificadorManager{
    
     private IPresentacionMenuDominoListener presentacionPartidaDominoListener;

    @Override
    public void suscribirPresentacionListener(IPresentacionMenuDominoListener listener) {
        presentacionPartidaDominoListener = listener;
    }

    @Override
    public void notificarCrearSala() {
        presentacionPartidaDominoListener.onCrearSala();
    }

    @Override
    public void notificarUnirseSala() {
        presentacionPartidaDominoListener.onUnirseSala();
    }
    
}
