/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino;

import contenedorpantallas.IContenidoController;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import fichabuilder.FichaBuilderUsuario;
import fichabuilder.IFichaBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import listeners.IPartidaDominoModelListener;
import presentacion.mediador.IMediador;
import presentacion.mediador.Mediador;
import presentacion.partidadomino.fichadominojugador.FichaDominoController;
import presentacion.partidadomino.fichadominojugador.FichaDominoView;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaDominoController implements IContenidoController, IPartidaDominoModelListener {

    private PartidaDominoModel model;
    private PartidaDominoView view;
    private final Mediador mediador = Mediador.getInstance();

    public PartidaDominoController(PartidaDominoModel model, PartidaDominoView view) {
        this.model = model;
        this.view = view;
        //this.model.agregarListener(this);

        view.repintarVista();
//        view.actualizarListaFichasJugadorLocal();
    }

    public void colocarFichaTablero(CasillaDTO casillaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void mostrarCasillasParaColocarFicha(List<CasillaDTO> casillasDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public void onListaFichasDominoUsuarioChange() { // Escucha cuando la lista de fichas del jugador local cambia
//        crearMVCFichasJugadorLocal();
//        repintarFichasJugadorLocal();
//        

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

    private float getEscala() {
        return model.getEscala();
    }
}
