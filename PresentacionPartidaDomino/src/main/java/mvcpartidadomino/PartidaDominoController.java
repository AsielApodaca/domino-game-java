package mvcpartidadomino;

import builders.CasillaBuilder;
import builders.FichaTableroBuilder;
import builders.FichaUsuarioBuilder;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import listeners.IContenedorListener;
import mediador.MediadorSingletonPantallaFichasJugador;
import notificador.IPresentacionNotificadorManager;
import partidadomino.elementostablero.CasillaPanel;
import partidadomino.elementostablero.FichaDominoTableroPanel;
import partidadomino.fichadominojugadormvc.FichaDominoController;
import partidadomino.fichadominojugadormvc.FichaDominoView;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaDominoController implements IContenedorListener {

    private PartidaDominoModel model;
    private PartidaDominoView view;
    private final MediadorSingletonPantallaFichasJugador mediador = MediadorSingletonPantallaFichasJugador.getInstance();
    private final FichaTableroBuilder fichaBuilderTablero = new FichaTableroBuilder();
    private final CasillaBuilder casillaBuilder = new CasillaBuilder();

    public PartidaDominoController(PartidaDominoModel model, PartidaDominoView view) {
        this.model = model;
        this.view = view;
        addMouseListenerToPozo();
        view.repintarVista();
    }

    public void colocarFichaTablero(CasillaDTO casillaDTO) {
        FichaDominoTableroPanel fichaDominoTablero;
        fichaDominoTablero = fichaBuilderTablero.construirFicha(casillaDTO);
        model.agregarPanelFichaSobreTablero(fichaDominoTablero);
        view.colocarFichaTablero(fichaDominoTablero);
        view.repintarFichasTablero();
    }

    public void mostrarCasillasParaColocarFicha(List<CasillaDTO> casillasDTO) {

        List<CasillaPanel> listaCasillas = new ArrayList<>();
        for (CasillaDTO casillaDTO : casillasDTO) {
            listaCasillas.add(casillaBuilder.construirCasilla(casillaDTO));
        }
        model.setListaPanelesCasillasParaColocarFichas(listaCasillas);
        agregarMouseListenersACasillas();
        view.repintarCasillasTablero();
    }

    private void agregarMouseListenersACasillas() {
        for (CasillaPanel casilla : model.getListaPanelesCasillasParaColocarFichas()) {
            casilla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CasillaDTO casillaSeleccionada = casilla.getCasillaDTO();
                
                IPresentacionNotificadorManager presentacionNotificacionesManager = 
                        model.getPresentacionNotificadorsManager();
                
                presentacionNotificacionesManager.notificarCasillaSeleccionada(casillaSeleccionada);
            }
        });
        }
    }

    public void ocultarCasillasParaColocarFicha() {
        // Settea una lista vacia
        model.setListaPanelesCasillasParaColocarFichas(new ArrayList<>());
        view.repintarCasillasTablero();
    }

    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichasJugador) {
        model.setListaFichasJugadorLocal(fichasJugador);
        crearMVCFichasJugadorLocal();
        repintarFichasJugadorLocal();
    }

    public void removerFichaJugadorLocal(FichaDominoDTO fichaDominoDTO) {
        model.removerFichaDeFichasJugadorLocal(fichaDominoDTO);
    }

    public void agregarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO) {
        model.agregarFichaAJugadorLocal(fichaDominoDTO);
    }

    public void showView() {
        view.setVisible(true);
    }

    @Override
    public JPanel obtenerView() {
        return this.view;
    }

    private void crearMVCFichasJugadorLocal() {
        FichaUsuarioBuilder fichaBuilder = new FichaUsuarioBuilder();
        List<FichaDominoController> listaFichasMVC = new ArrayList<>(); // Lista de instancias de mvc de fichas
        List<FichaDominoView> listaPanelesFichasJugadorLocal = new ArrayList<>();

        for (FichaDominoDTO fichaDTO : model.getListaFichasJugadorLocal()) {
            FichaDominoController fichaController = null;
            try {
                fichaController = fichaBuilder.construirFicha(fichaDTO);
            } catch (IOException ex) {
                Logger.getLogger(PartidaDominoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            fichaController.actualizarEscala(model.getEscala());
            listaFichasMVC.add(fichaController);
            listaPanelesFichasJugadorLocal.add(fichaController.getView());
        }

        mediador.setListaFichasJugadorLocal(listaFichasMVC);
        model.setListaPanelesFichasJugadorLocal(listaPanelesFichasJugadorLocal);

    }

    private void repintarFichasJugadorLocal() {
        view.repintarContenedorFichasJugadorLocal();
        view.repintarFichasJugadorLocal();
    }

    public void mostrarPozoDisponible() {
        model.setPozoBloqueado(false);
        view.repintarPozo();
    }

    public void ocultarPozoDisponible() {
        model.setPozoBloqueado(true);
        view.repintarPozo();
    }

    public void sacarFichaPozo() {
        if (!model.isPozoBloqueado()) {
            IPresentacionNotificadorManager presentacionNotificadorManager
                    = model.getPresentacionNotificadorsManager();
            PozoSeleccionadoEvento evento = new PozoSeleccionadoEvento();
            presentacionNotificadorManager.notificarPozoSeleccionado(evento);
            ocultarPozoDisponible();
        }
    }

    public void addMouseListenerToPozo() {
        this.view.getPozoPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sacarFichaPozo(); // Reutilizamos el método existente
                view.repintarPozo();
            }
        });
    }

    public PartidaDominoModel getModel() {
        return model;
    }

    public PartidaDominoView getView() {
        return view;
    }
    
    public void mostrarJugador(JugadorDominoDTO jugadorDominoDTO) {
        
    }
    
    public void removerJugador(JugadorDominoDTO jugadorDominoDTO) {
        
    }
    
    public void actualizarCantidadFichasJugador(JugadorDominoDTO jugadorDominoDTO, int cantidadFichas) {
        
    }

    @Override
    public void onEscalaChange(float escala) {
        model.setEscala(escala);
        mediador.redimencionarFichasJugadorLocal(escala);
        view.repintarVista();
    }
}
