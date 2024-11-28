package notificador;

import listeners.IPresentacionSalaEsperaListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PresentacionNotificadorManager implements IPresentacionNotificadorManager {

    private IPresentacionSalaEsperaListener presentacionSalaEsperaListener;

    @Override
    public void subscribirPresentacionListener(IPresentacionSalaEsperaListener listener) {
        this.presentacionSalaEsperaListener = listener;
    }

    @Override
    public void notificarBtnIniciarPartidaPresionado() {
        presentacionSalaEsperaListener.onBtnIniciarPartidaPresionado();
    }

    @Override
    public void notificarBtnSalirPresionado() {
        presentacionSalaEsperaListener.onBtnSalirPresionado();
    }

}
