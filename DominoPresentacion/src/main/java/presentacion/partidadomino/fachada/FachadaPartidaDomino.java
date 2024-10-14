/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fachada;

import contenedorpantallas.SingletonContenedorContenido;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.List;
import logica.tableroDominoLogica.TableroDominoLogica;
import presentacion.mediador.Mediador;
import presentacion.partidadomino.PartidaDominoController;
import presentacion.partidadomino.PartidaDominoModel;
import presentacion.partidadomino.PartidaDominoView;
import presentacion.partidadomino.fichadominojugador.FichaDominoModel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FachadaPartidaDomino implements IFachadaPartidaDomino {

    private final SingletonContenedorContenido sgContenedorContenido = SingletonContenedorContenido.getInstance();
    private PartidaDominoController partidaDominoController;
    private PartidaDominoModel partidaDominoModel;
    private PartidaDominoView partidaDominoView;
    private final Mediador mediador = Mediador.getInstance();

    public FachadaPartidaDomino() {
    }

    /**
     *
     * @param fichasJugador
     */
    @Override
    public void iniciarPantalla() {
        this.partidaDominoModel = new PartidaDominoModel();
        this.partidaDominoView = new PartidaDominoView(partidaDominoModel);
        this.partidaDominoController = new PartidaDominoController(partidaDominoModel, partidaDominoView);
        this.mediador.setPartidaDominoController(partidaDominoController);
        sgContenedorContenido.setContenidoController(partidaDominoController);
        sgContenedorContenido.mostrarPantalla();
        this.partidaDominoController.showView();

    }

    @Override
    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichasJugador) {
        partidaDominoController.mostrarFichasJugadorLocal(fichasJugador);
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
    public void escucharFichaSeleccionada(TableroDominoLogica tableroDominoLogica) {
        partidaDominoModel.setListenerTableroDominoLogica(tableroDominoLogica);
    }
}
