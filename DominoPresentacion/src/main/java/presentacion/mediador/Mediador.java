/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.mediador;


import java.util.ArrayList;
import java.util.List;
import presentacion.partidadomino.PartidaDominoModel;
import presentacion.partidadomino.PartidaDominoView;
import presentacion.partidadomino.fichadomino.FichaDominoModel;
import presentacion.partidadomino.fichadomino.FichaDominoView;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Mediador implements IMediador {

    private PartidaDominoModel partidaDomino;
    private FichaDominoModel fichaModel;
    private List<FichaDominoModel> fichasModels;
    private PartidaDominoView partidaDominoView;
    

    public Mediador() {
        this.fichasModels = new ArrayList<>();
        this.fichaModel = new FichaDominoModel();

    }
    
    @Override
    public void crearFichasJugadorLocal() {
       for(FichaDominoModel ficha : fichasModels){
           fichaModel.notifyObservers();
           
       }
    }
   

    
    
//    @Override
//    public void redimencionarFichasJugadorLocal() {
//        float escala = partidaDomino.getEscala();
//        int anchoFicha = (int) (partidaDomino.getAnchoFichaJugadorLocal() * escala);
//        int altoFicha = (int) (partidaDomino.getAlturaFichaJugadorLocal() * escala);
//
//        for (FichaDominoModel model : fichasModels) {
//            model.setDimensiones(anchoFicha, altoFicha);
//        }
//    }

    @Override
    public void redimencionarFichasJugadorLocal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
