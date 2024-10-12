/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fachada;

import contenedorpantallas.SingletonContenedorContenido;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.List;
import presentacion.partidadomino.PartidaDominoController;
import presentacion.partidadomino.PartidaDominoModel;
import presentacion.partidadomino.PartidaDominoView;
import presentacion.partidadomino.fichadomino.FichaDominoModel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FachadaPartidaDomino implements IFachadaPartidaDomino{

    private final SingletonContenedorContenido sgContenedorContenido = SingletonContenedorContenido.getInstance();
    private PartidaDominoController partidaDominoController;
    private PartidaDominoModel partidaDominoModel;
    private PartidaDominoView partidaDominoView;
    

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
        sgContenedorContenido.setContenidoController(partidaDominoController);
        sgContenedorContenido.mostrarPantalla();
        this.partidaDominoController.showView();
        
    }

    @Override
    public void actualizarFichasJugadorLocal(List<FichaDominoDTO> listaFichasDomino) {
        partidaDominoModel.setListaFichasJugadorLocal(listaFichasDomino);
    }
    
    @Override
    public void mostrarFichas(List<FichaDominoDTO> fichasJugador) {
        partidaDominoController.mostrarFichas(fichasJugador);
    }

    @Override
    public void colocarFichaTablero(CasillaDTO casillaDTO) {
        partidaDominoController.colocarFichaTablero(casillaDTO);
    }

    @Override
    public void mostrarCasillaParaColocarFicha(CasillaDTO casillaDTO) {
        partidaDominoController.mostrarCasillaParaColocarFicha(casillaDTO);
    }
}
