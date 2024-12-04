package mvc;

import javax.swing.JPanel;
import listeners.IContenedorListener;
import notificador.IPresentacionCrearUsuarioNotificador;
import listeners.ICrearUsuarioViewListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class CrearUsuarioController implements ICrearUsuarioViewListener, IContenedorListener {

    private CrearUsuarioModel crearUsuarioModel;
    private CrearUsuarioView crearUsuarioView;
    private IPresentacionCrearUsuarioNotificador presentacionCrearUsuarioNotificador;

    public CrearUsuarioController(CrearUsuarioModel crearUsuarioModel, CrearUsuarioView crearUsuarioView, IPresentacionCrearUsuarioNotificador presentacionCrearUsuarioNotificador) {
        this.presentacionCrearUsuarioNotificador = presentacionCrearUsuarioNotificador;
        this.crearUsuarioModel = crearUsuarioModel;
        this.crearUsuarioView = crearUsuarioView;
    }

    /**
     * Notifica que se ha iniciado el proceso de creación de usuario con el
     * nombre proporcionado.
     *
     * @param nombreUsuario el nombre del usuario ingresado.
     */
    @Override
    public void onBtnEmpezarPresionado(String nombreUsuario) {
        String rutaIcono = crearUsuarioModel.getIconoActual();
        presentacionCrearUsuarioNotificador.notificarCrearUsuario(nombreUsuario, rutaIcono);
    }

    /**
     * Maneja el evento cuando se presiona el botón "Anterior". Actualiza el
     * modelo para mostrar el icono anterior en la interfaz.
     */
    @Override
    public void onBtnAnteriorPresionado() {
        crearUsuarioModel.anteriorIcono();
    }

    /**
     * Maneja el evento cuando se presiona el botón "Siguiente". Actualiza el
     * modelo para mostrar el siguiente icono en la interfaz.
     */
    @Override
    public void onBtnSiguientePresionado() {
        crearUsuarioModel.siguienteIcono();

    }

    //No se necesita
    @Override
    public void onEscalaChange(float escala) {
    }

    @Override
    public JPanel obtenerView() {
        return crearUsuarioView;
    }

}
