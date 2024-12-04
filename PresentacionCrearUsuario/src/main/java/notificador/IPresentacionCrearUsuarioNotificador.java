package notificador;

import listeners.IPresentacionCrearUsuarioListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPresentacionCrearUsuarioNotificador {

    public void suscribirPresentacionListener(IPresentacionCrearUsuarioListener listener);

    public void notificarCrearUsuario(String nombreUsuario, String rutaIcono);
}
