package listeners;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPresentacionCrearUsuarioListener {

    /**
     * Evento que se ejecuta cuando se solicita crear un usuario.
     *
     * @param nombreUsuario El nombre del usuario a crear
     * @param rutaIcono La ruta que pertenece al icono seleccionado
     */
    public void onCrearUsuario(String nombreUsuario, String rutaIcono);
}
