package mvc;

import dominodto.UsuarioDTO;
import javax.swing.JPanel;
import listeners.IContenedorListener;
import listeners.IViewListener;
import notificador.IPresentacionSalaEsperaNotificador;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SalaEsperaController implements IViewListener, IContenedorListener {

    private SalaEsperaModel salaEsperaModel;
    private SalaEsperaView salaEsperaView;
    private IPresentacionSalaEsperaNotificador presentacionNotificadorManager;

    public SalaEsperaController(SalaEsperaModel salaEsperaModel, SalaEsperaView salaEsperaView, IPresentacionSalaEsperaNotificador presentacionNotificadorManager) {
        this.presentacionNotificadorManager = presentacionNotificadorManager;
        this.salaEsperaModel = salaEsperaModel;
        this.salaEsperaView = salaEsperaView;
    }

    /**
     * Agrega un usuario al modelo y actualiza la vista. También notifica el
     * evento de que el botón "Iniciar Partida" fue presionado.
     *
     * @param usuarioDTO Objeto que contiene la información del usuario a
     * agregar.
     */
    public void agregarUsuario(UsuarioDTO usuarioDTO) {
        salaEsperaModel.agregarUsuarioPanel(usuarioDTO);
        salaEsperaView.repintarJugadores();
        presentacionNotificadorManager.notificarBtnIniciarPartidaPresionado();

    }

    /**
     * Remueve un usuario del modelo y actualiza la vista.
     *
     * @param usuarioDTO Objeto que contiene la información del usuario a
     * remover.
     */
    public void removerUsuario(UsuarioDTO usuarioDTO) {
        salaEsperaModel.removerUsuarioPanel(usuarioDTO);
        salaEsperaView.repintarJugadores();

    }

    /**
     * Define si la pantalla actual pertenece al anfitrión y actualiza la vista.
     *
     * @param esAnfitrion Indica si la pantalla corresponde al anfitrión (true)
     * o no (false).
     */
    public void declararPantallaDeAnfitriones(Boolean esAnfitrion) {
        salaEsperaModel.setEsPantallaDeAnfitrion(esAnfitrion);
        salaEsperaView.repintarPantalla();
    }

    /**
     * Maneja el evento cuando se presiona el botón "Iniciar Partida" en la
     * vista. Notifica al manejador correspondiente.
     */
    @Override
    public void onBtnIniciarPartidaPresionado() {
        presentacionNotificadorManager.notificarBtnIniciarPartidaPresionado();
    }

    /**
     * Maneja el evento cuando se presiona el botón "Salir" en la vista.
     * Notifica al manejador correspondiente.
     */
    @Override
    public void onBtnSalirPresionado() {
        presentacionNotificadorManager.notificarBtnSalirPresionado();
    }

    @Override
    public void onEscalaChange(float escala) {
        // por hacer
    }

    @Override
    public JPanel obtenerView() {
        return salaEsperaView;
    }
}
