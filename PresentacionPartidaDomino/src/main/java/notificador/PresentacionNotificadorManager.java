package notificador;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import listeners.IPresentacionPartidaDominoListener;

/**
 * Clase que gestiona las notificaciones de eventos relacionados con la presentación de la partida de dominó.
 * Permite la suscripción de un listener que se encargará de procesar los eventos de selección de casilla
 * y selección de ficha, notificándolo a través de métodos específicos.
 * 
 * <p>El listener, perteneciente a la capa de negocios, recibirá los eventos a través de la interfaz
 * {@link IPresentacionPartidaDominoListener}.</p>
 * 
 * @author asielapodaca
 */
public class PresentacionNotificadorManager implements IPresentacionNotificadorManager {
    
    /**
     * Listener que escucha los eventos de este componente. 
     * Normalmente, será una clase en la capa de negocios.
     */
    private IPresentacionPartidaDominoListener presentacionPartidaDominoListener;
    
    /**
     * Suscribe un listener para recibir los eventos de presentación de la partida de dominó.
     * 
     * @param listener el listener que implementa la interfaz {@link IPresentacionPartidaDominoListener}
     */
    @Override
    public void suscribirPresentacionListener(IPresentacionPartidaDominoListener listener) {
        this.presentacionPartidaDominoListener = listener;
    }

    /**
     * Notifica al listener que una casilla ha sido seleccionada.
     * 
     * @param casillaSeleccionada casilla que fué seleccionada
     */
    @Override
    public void notificarCasillaSeleccionada(CasillaDTO casillaSeleccionada) {
        presentacionPartidaDominoListener.onCasillaSeleccionada(casillaSeleccionada);
    }

    /**
     * Notifica al listener que una ficha ha sido seleccionada.
     * 
     * @param fichaSelecccionada la ficha seleccionada
     */
    @Override
    public void notificarFichaSeleccionada(FichaDominoDTO fichaSelecccionada) {
        presentacionPartidaDominoListener.onFichaSeleccionada(fichaSelecccionada);
    }
}
