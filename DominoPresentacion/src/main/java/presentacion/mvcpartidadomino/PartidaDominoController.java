/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.mvcpartidadomino;

import contenedorpantallas.IContenidoController;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import fichabuilder.CasillaBuilder;
import fichabuilder.FichaBuilderTablero;
import fichabuilder.FichaBuilderUsuario;
import fichabuilder.IFichaBuilder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import listeners.IPartidaDominoModelListener;
import notificaciones.PresentacionNotificacionesManager;
import notificaciones.eventos.CasillaSeleccionadaEvento;
import presentacion.mediador.IMediador;
import presentacion.mediador.Mediador;
import presentacion.partidadomino.fichadominojugador.FichaDominoController;
import presentacion.partidadomino.fichadominojugador.FichaDominoView;
import presentacion.partidadomino.tablero.CasillaPanel;
import presentacion.partidadomino.tablero.FichaDominoTablero;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaDominoController implements IContenidoController{

    private PartidaDominoModel model;
    private PartidaDominoView view;
    private final Mediador mediador = Mediador.getInstance();
    private final FichaBuilderTablero fichaBuilderTablero = new FichaBuilderTablero();
    private final CasillaBuilder casillaBuilder = new CasillaBuilder();

    public PartidaDominoController(PartidaDominoModel model, PartidaDominoView view) {
        this.model = model;
        this.view = view;
        //this.model.agregarListener(this);

        view.repintarVista();
//        view.actualizarListaFichasJugadorLocal();
        //simularFichaTablero();
    }
    
    private void simularFichaTablero() {
        
        FichaDominoDTO fichaDominoDTO = new FichaDominoDTO(2, 6);
        CasillaDTO casillaDTO = new CasillaDTO();
        casillaDTO.setFichaDominoDTO(fichaDominoDTO);
        casillaDTO.setLocacionX(9);
        casillaDTO.setLocacionY(5);
        casillaDTO.setRotacion(270);
        
        colocarFichaTablero(casillaDTO);
    }

    public void colocarFichaTablero(CasillaDTO casillaDTO) {
        FichaDominoTablero fichaDominoTablero;
        fichaDominoTablero = fichaBuilderTablero.construirFicha(casillaDTO);
        model.agregarPanelFichaSobreTablero(fichaDominoTablero);
        view.colocarFichaTablero(fichaDominoTablero);
        view.repintarFichasTablero();
    }

    public void mostrarCasillasParaColocarFicha(List<CasillaDTO> casillasDTO) {
        
        List<CasillaPanel> listaCasillas = new ArrayList<>();
        for(CasillaDTO casillaDTO : casillasDTO) {
            listaCasillas.add(casillaBuilder.construirCasilla(casillaDTO));
        }
        model.setListaPanelesCasillasParaColocarFichas(listaCasillas);
        agregarMouseListenersACasillas();
        view.repintarCasillasTablero();
    }
    
    private void agregarMouseListenersACasillas() {
        for(CasillaPanel casilla : model.getListaPanelesCasillasParaColocarFichas()) {
            casilla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CasillaSeleccionadaEvento evento = new CasillaSeleccionadaEvento();
                evento.setCasilla(casilla.getCasillaDTO());
                
                PresentacionNotificacionesManager presentacionNotificacionesManager = 
                        model.getPresentacionNotificacionesManager();
                
                presentacionNotificacionesManager.notificarCambioAPresentacionListener(evento);
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

    public void showView() {
        view.setVisible(true);
    }

    @Override
    public void actualizarEscala(float escala) {
        model.setEscala(escala);
        mediador.redimencionarFichasJugadorLocal(escala);
        view.repintarVista();

    }

    @Override
    public JPanel obtenerView() {
        return this.view;
    }

    private void crearMVCFichasJugadorLocal() {
        FichaBuilderUsuario fichaBuilder = new FichaBuilderUsuario();
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

    public PartidaDominoModel getModel() {
        return model;
    }

    public PartidaDominoView getView() {
        return view;
    }

    private float getEscala() {
        return model.getEscala();
    }
}
