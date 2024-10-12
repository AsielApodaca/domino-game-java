/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.mediador;


import dominodto.FichaDominoDTO;
import java.util.ArrayList;
import java.util.List;
import presentacion.partidadomino.PartidaDominoController;
import presentacion.partidadomino.PartidaDominoModel;
import presentacion.partidadomino.PartidaDominoView;
import presentacion.partidadomino.fichadominojugador.FichaDominoController;
import presentacion.partidadomino.fichadominojugador.FichaDominoModel;
import presentacion.partidadomino.fichadominojugador.FichaDominoView;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Mediador implements IMediador {

    private static Mediador instancia; // Instancia única de si mismo
    private PartidaDominoController partidaDominoController; // MVC con el que se comunican las fichas
    private List<FichaDominoController> listaFichasJugadorLocal;
    

    private Mediador() {
    }
    
    public static Mediador getInstance() {
        if(instancia == null) {
            instancia = new Mediador();
        }
        return (Mediador) instancia;
    }
    
    @Override
    public void crearFichasJugadorLocalView() { // no lo use
       
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
    public void redimencionarFichasJugadorLocal(float escala) {
        if(listaFichasJugadorLocal != null)
        for(FichaDominoController fichaController : listaFichasJugadorLocal) {
            fichaController.actualizarEscala(escala);
        }
    }
    

    @Override
    public void notificarFichaSeleccionada(FichaDominoDTO fichaSeleccionada) {
        //partidaDominoModel.setFichaSeleccionada(fichaSeleccionada);
    }

    @Override
    public void notificarColocarFicha() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PartidaDominoController getPartidaDominoController() {
        return partidaDominoController;
    }

    public void setPartidaDominoController(PartidaDominoController partidaDominoController) {
        this.partidaDominoController = partidaDominoController;
    }

    public List<FichaDominoController> getListaFichasJugadorLocal() {
        return listaFichasJugadorLocal;
    }

    public void setListaFichasJugadorLocal(List<FichaDominoController> listaFichasJugadorLocal) {
        this.listaFichasJugadorLocal = listaFichasJugadorLocal;
    }
    
    

}
