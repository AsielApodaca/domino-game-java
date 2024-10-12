/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino;

import contenedorpantallas.IContenidoController;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import presentacion.partidadomino.fichadominojugador.FichaDominoView;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaDominoController implements IContenidoController{

    private List<FichaDominoView> fichasDominoUsuarioLocal;
    private PartidaDominoModel model;
    private PartidaDominoView view;

    public PartidaDominoController(PartidaDominoModel model, PartidaDominoView view) {
        this.model = model;
        this.view = view;
        this.fichasDominoUsuarioLocal = new ArrayList<>();
        
        view.repintarComponentes();
//        view.actualizarListaFichasJugadorLocal();
    }

    public void colocarFichaTablero(CasillaDTO casillaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void mostrarCasillaParaColocarFicha(CasillaDTO casillaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichasJugador){
        model.setListaFichasJugadorLocal(fichasJugador);
    }
    
    public void showView() {
        view.setVisible(true);
    }

    @Override
    public void actualizarEscala(float escala) {
        model.setEscala(escala);
        view.repintarComponentes();
    }

    @Override
    public JPanel obtenerView() {
        return this.view;
    }
    
     private void repintarFichasJugadorLocal() {
        for(FichaDominoView ficha:fichasDominoUsuarioLocal ){
            
        }
    }
    
}
