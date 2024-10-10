/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fachada;

import dominodto.FichaDominoDTO;
import java.util.List;
import presentacion.mediador.IMediador;
import presentacion.mediador.Mediador;
import presentacion.partidadomino.PartidaDominoController;
import presentacion.partidadomino.PartidaDominoModel;
import presentacion.partidadomino.PartidaDominoView;

/**
 *
 * @author asielapodaca
 */
public class FachadaPartidaDomino implements IFachadaPartidaDomino{

    private final IMediador mediador = Mediador.getInstance();
    private PartidaDominoModel model;
    private PartidaDominoView view;
    private PartidaDominoController controller;

    public FachadaPartidaDomino() {
    }
    
    @Override
    public void iniciarPantalla() {
        this.model = new PartidaDominoModel();
        this.view = new PartidaDominoView(model);
        this.controller = new PartidaDominoController(model, view);
        
        mediador.setContenidoController(controller);
        mediador.mostrarPantalla();
        this.controller.showView();
        
    }

    @Override
    public void colocarFichaTablero(FichaDominoDTO fichaDominoDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarFichasJugadorLocal(List<FichaDominoDTO> listaFichasDomino) {
        model.setListaFichasJugadorLocal(listaFichasDomino);
    }
    
}
