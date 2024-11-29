package notificador;

import listeners.IPresentacionSalaEsperaListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PresentacionSalaEsperaNotificador implements IPresentacionSalaEsperaNotificador {

    private IPresentacionSalaEsperaListener presentacionSalaEsperaListener;

    public PresentacionSalaEsperaNotificador() {
    }
    
    

    /**
     * Método para registrar un listener que responderá a los eventos de presentación.
     * 
     * @param listener la instancia de IPresentacionSalaEsperaListener que será notificada.
     */
    @Override
    public void subscribirPresentacionListener(IPresentacionSalaEsperaListener listener) {
        this.presentacionSalaEsperaListener = listener;
    }

    /**
     * Notifica al listener registrado que el botón "Iniciar Partida" fue presionado.
     * Llama al método correspondiente en el listener.
     */
    @Override
    public void notificarBtnIniciarPartidaPresionado() {
        presentacionSalaEsperaListener.onBtnIniciarPartidaPresionado();
    }

    /**
     * Notifica al listener registrado que el botón "Salir" fue presionado.
     * Llama al método correspondiente en el listener.
     */
    @Override
    public void notificarBtnSalirPresionado() {
        presentacionSalaEsperaListener.onBtnSalirPresionado();
    }

}
