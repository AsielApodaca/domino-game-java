package listeners;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IViewListener {

    /**
     * Notifica al presionar "Empezar" con un nombre de usuario.
     *
     * @param nombreUsuario el nombre ingresado.
     */
    void onBtnEmpezarPresionado(String nombreUsuario);

    /**
     * Notifica al presionar "Anterior".
     */
    void onBtnAnteriorPresionado();

    /**
     * Notifica al presionar "Siguiente".
     */
    void onBtnSiguientePresionado();
}
