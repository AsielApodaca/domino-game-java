package notificador;

import listeners.IPresentacionCrearUsuarioListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PresentacionCrearUsuarioNotificador implements IPresentacionCrearUsuarioNotificador {

    private IPresentacionCrearUsuarioListener presentacionCrearUsuarioListener;

    @Override
    public void suscribirPresentacionListener(IPresentacionCrearUsuarioListener listener) {
        presentacionCrearUsuarioListener = listener;
    }

    @Override
    public void notificarCrearUsuario(String nombreUsuario, String rutaIcono) {
        presentacionCrearUsuarioListener.onCrearUsuario(nombreUsuario, rutaIcono);
    }

}
