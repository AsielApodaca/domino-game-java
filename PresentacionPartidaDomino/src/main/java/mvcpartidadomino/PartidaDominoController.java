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
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import listeners.IContenedorListener;
import mediador.MediadorSingletonPantallaFichasJugador;
import partidadomino.elementostablero.CasillaPanel;
import partidadomino.elementostablero.FichaDominoTableroPanel;
import partidadomino.fichadominojugadormvc.FichaDominoController;
import partidadomino.fichadominojugadormvc.FichaDominoView;
import partidadomino.jugadores.JugadorArribaPanel;
import partidadomino.jugadores.JugadorDerechaPanel;
import partidadomino.jugadores.JugadorIzquierdaPanel;
import partidadomino.jugadores.JugadorPanel;
import notificador.IPresentacionPartidaDominoNotificador;
import partidadomino.elementostablero.ResultadoPartidaPanel;

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

                    IPresentacionPartidaDominoNotificador presentacionNotificacionesManager
                            = model.getPresentacionNotificadorsManager();

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
        view.getPozoPanel().setBloqueado(false);
        view.repintarPozo();
    }

    public void ocultarPozoDisponible() {
        model.setPozoBloqueado(true);
        view.getPozoPanel().setBloqueado(true);
        view.repintarPozo();
    }

    public void sacarFichaPozo() {
        if (!model.isPozoBloqueado()) {
            IPresentacionPartidaDominoNotificador presentacionNotificadorManager
                    = model.getPresentacionNotificadorsManager();

            presentacionNotificadorManager.notificarPozoSeleccionado();
            ocultarPozoDisponible();
        }
    }

    public void addMouseListenerToPozo() {
        this.view.getPozoPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sacarFichaPozo();
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

    public void mostrarJugadores(List<JugadorDominoDTO> jugadoresEnOrden) {
        System.out.println("Metodo mostrarJugadores");
        crearJugadoresPaneles(jugadoresEnOrden);
        view.cargarJugadoresPaneles();
    }

    private void crearJugadoresPaneles(List<JugadorDominoDTO> jugadoresEnOrden) {
        int cantidadJugadores = jugadoresEnOrden.size();
        switch (cantidadJugadores) {
            case 3 -> {
                JugadorDominoDTO jugadorIzquierda = jugadoresEnOrden.get(0);
                JugadorIzquierdaPanel panelIzquierda = new JugadorIzquierdaPanel(jugadorIzquierda);
                JugadorDominoDTO jugadorArriba = jugadoresEnOrden.get(1);
                JugadorArribaPanel panelArriba = new JugadorArribaPanel(jugadorArriba);
                JugadorDominoDTO jugadorDerecha = jugadoresEnOrden.get(2);
                JugadorDerechaPanel panelDerecha = new JugadorDerechaPanel(jugadorDerecha);
                model.agregarJugadorPanel(panelIzquierda);
                model.agregarJugadorPanel(panelArriba);
                model.agregarJugadorPanel(panelDerecha);
            }
            case 2 -> {
                JugadorDominoDTO jugadorIzquierda = jugadoresEnOrden.get(0);
                JugadorIzquierdaPanel panelIzquierda = new JugadorIzquierdaPanel(jugadorIzquierda);
                JugadorDominoDTO jugadorDerecha = jugadoresEnOrden.get(1);
                JugadorDerechaPanel panelDerecha = new JugadorDerechaPanel(jugadorDerecha);
                model.agregarJugadorPanel(panelIzquierda);
                model.agregarJugadorPanel(panelDerecha);
            }
            case 1 -> {
                JugadorDominoDTO jugadorArriba = jugadoresEnOrden.get(0);
                JugadorArribaPanel panelArriba = new JugadorArribaPanel(jugadorArriba);
                model.agregarJugadorPanel(panelArriba);
            }

        }
    }

    public void removerJugador(JugadorDominoDTO jugadorDominoDTO) {
        JugadorPanel jugadorPanel = model.obtenerJugadorPanel(jugadorDominoDTO);
        view.remove(jugadorPanel);
        model.removerJugadorPanel(jugadorDominoDTO);
        System.out.println(jugadorDominoDTO.getNombre() + " salió de la partida.");
    }

    public void actualizarCantidadFichasJugador(JugadorDominoDTO jugadorDominoDTO, int cantidadFichas) {
        System.out.println("metodoActualizarCantidadFichasJugador");
        JugadorPanel jugadorPanel = model.obtenerJugadorPanel(jugadorDominoDTO);
        if (jugadorPanel != null) {
            jugadorPanel.actualizarCantidadFichas(cantidadFichas);
        }
    }

    public void otorgarTurnoAJugador(JugadorDominoDTO jugadorDominoDTO) {
        Collection<JugadorPanel> jugadores = model.getMapaJugadoresExternos().values();
        // Quita el turno a los jugadores
        for (JugadorPanel jugadorPanel : jugadores) {
            jugadorPanel.actualizarTurno(false);
        }
        // Falta logica para quitar turno a jugador local

        // Asigna turno a un jugador
        JugadorPanel jugadorPanel = model.obtenerJugadorPanel(jugadorDominoDTO);
        if (jugadorPanel != null) {
            jugadorPanel.actualizarTurno(true);
        } else {
            // Falta logica para otorgar turno a jugador local
        }
    }

    public void mostrarResultadoPartida() {
        JugadorDominoDTO ganador = model.obtenerGanador();
        if (ganador != null) {
            ResultadoPartidaPanel resultadoPanel = new ResultadoPartidaPanel(ganador);
            resultadoPanel.reescalar(model.getEscala());
            view.add(resultadoPanel);
            view.revalidate();
            view.repaint();
        }
    }

    @Override
    public void onEscalaChange(float escala
    ) {
        model.setEscala(escala);
        mediador.redimencionarFichasJugadorLocal(escala);
        view.repintarVista();
    }
}
