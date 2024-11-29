package notificador;

import listeners.IPresentacionSalaEsperaListener;



/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPresentacionSalaEsperaNotificador {

    public void subscribirPresentacionListener(IPresentacionSalaEsperaListener listener);

    public void notificarBtnIniciarPartidaPresionado();

    public void notificarBtnSalirPresionado();
}
