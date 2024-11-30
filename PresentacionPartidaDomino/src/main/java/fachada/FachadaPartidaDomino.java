package fachada;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.List;
import listeners.IContenedorListener;
import listeners.IPresentacionPartidaDominoListener;
import mediador.MediadorSingletonPantallaFichasJugador;
import mediadorsingleton.MediadorSingletonContenedorContenido;
import mvcpartidadomino.PartidaDominoController;
import mvcpartidadomino.PartidaDominoModel;
import mvcpartidadomino.PartidaDominoView;
import notificador.IPresentacionNotificadorManager;
import notificador.PresentacionNotificadorManager;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FachadaPartidaDomino implements IFachadaPartidaDomino {

    private final MediadorSingletonContenedorContenido mdSgContenedorContenido = MediadorSingletonContenedorContenido.getInstance();
    private PartidaDominoController partidaDominoController;
    private PartidaDominoModel partidaDominoModel;
    private PartidaDominoView partidaDominoView;
    private final MediadorSingletonPantallaFichasJugador mediador = MediadorSingletonPantallaFichasJugador.getInstance();

    public FachadaPartidaDomino() {
    }

    /**
     *
     * @param fichasJugador
     */
    @Override
    public IContenedorListener iniciarPantalla() {
        this.partidaDominoModel = new PartidaDominoModel();
        this.partidaDominoView = new PartidaDominoView(partidaDominoModel);
        this.partidaDominoController = new PartidaDominoController(partidaDominoModel, partidaDominoView);
        this.mediador.setPartidaDominoController(partidaDominoController);
        mdSgContenedorContenido.setContenedorListener(partidaDominoController);
        mdSgContenedorContenido.mostrarPantalla();
        this.partidaDominoController.showView();

        return (IContenedorListener) this.partidaDominoController;
    }

    @Override
    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichasJugador) {
        partidaDominoController.mostrarFichasJugadorLocal(fichasJugador);
    }

    @Override
    public void quitarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO) {
        partidaDominoController.removerFichaJugadorLocal(fichaDominoDTO);
    }

    @Override
    public void agregarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO) {
        partidaDominoController.agregarFichaJugadorLocal(fichaDominoDTO);
    }

    @Override
    public void colocarFichaTablero(CasillaDTO casillaDTO) {
        partidaDominoController.colocarFichaTablero(casillaDTO);
    }

    @Override
    public void mostrarCasillasParaColocarFicha(List<CasillaDTO> casillasDTO) {
        partidaDominoController.mostrarCasillasParaColocarFicha(casillasDTO);
    }

    @Override
    public void ocultarCasillasParaColocarFicha() {
        partidaDominoController.ocultarCasillasParaColocarFicha();
    }

    @Override
    public void suscribirPresentacionListener(IPresentacionPartidaDominoListener listener) {
        IPresentacionNotificadorManager presentacionNotificadorManager
                = new PresentacionNotificadorManager();
        presentacionNotificadorManager.suscribirPresentacionListener(listener);
        partidaDominoModel.setPresentacionNotificacionesManager(presentacionNotificadorManager);
    }

    @Override
    public void mostrarPozo() {
        partidaDominoController.mostrarPozoDisponible();
    }

    @Override
    public void ocultarPozo() {
        partidaDominoController.ocultarPozoDisponible();
    }


}
