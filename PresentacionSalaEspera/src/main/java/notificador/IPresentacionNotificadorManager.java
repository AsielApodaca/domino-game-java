/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package notificador;

import listeners.IPresentacionSalaEsperaListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPresentacionNotificadorManager {
    public void subscribirPresentacionListener(IPresentacionSalaEsperaListener listener);
    public void notificarBtnIniciarPartidaPresionado();
    public void notificarBtnSalirPresionado();
}
