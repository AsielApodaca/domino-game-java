package mvc;

import dominodto.UsuarioDTO;
import listeners.IViewListener;
import notificador.IPresentacionNotificadorManager;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SalaEsperaController implements IViewListener {

    private SalaEsperaModel salaEsperaModel;
    private SalaEsperaView salaEsperaView;
    private IPresentacionNotificadorManager presentacionNotificadorManager;

    public SalaEsperaController(SalaEsperaModel salaEsperaModel, SalaEsperaView salaEsperaView) {
        this.salaEsperaModel = salaEsperaModel;
        this.salaEsperaView = salaEsperaView;
    }

    public void agregarUsuario(UsuarioDTO usuarioDTO) {
        salaEsperaModel.agregarUsuarioPanel(usuarioDTO);
        salaEsperaView.repintarJugadores();
        presentacionNotificadorManager.notificarBtnIniciarPartidaPresionado();

    }

    public void removerUsuario(UsuarioDTO usuarioDTO) {
        salaEsperaModel.removerUsuarioPanel(usuarioDTO);
        salaEsperaView.repintarJugadores();

    }

    public void declararPantallaDeAnfitriones(Boolean esAnfitrion) {
        salaEsperaModel.setEsPantallaDeAnfitrion(esAnfitrion);
        salaEsperaView.repintarPantalla();
    }

    @Override
    public void onBtnIniciarPartidaPresionado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onBtnSalirPresionado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
