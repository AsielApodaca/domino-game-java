/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.mediador;

import dominodto.FichaDominoDTO;
import java.awt.Dimension;
import java.util.List;
import presentacion.partidadomino.PartidaDominoModel;
import presentacion.partidadomino.fichadomino.FichaDominoModel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Mediador implements IMediador {

    private PartidaDominoModel partidaDomino;
    private FichaDominoModel fichaDominoModel;

    
    public Mediador() {
        this.partidaDomino = new PartidaDominoModel();
        this.fichaDominoModel = new FichaDominoModel();
    }

    @Override
    public void actualizarListaFichasJugadorLocal(
            List<FichaDominoDTO> fichas) {
        partidaDomino.setListaFichasJugadorLocal(fichas);
    }

    @Override
    public List<FichaDominoDTO> obtenerListaFichasJugadorLocal() {
        return partidaDomino.getListaFichasJugadorLocal();
    }

    @Override
    public float obtenerEscala() {
        return partidaDomino.getEscala();
    }

    @Override
    public void actualizarEscala(float escala) {
        partidaDomino.setEscala(escala);
    }

    @Override
    public void crearNuevaFicha(
            FichaDominoDTO fichaDominoDTO) {
        fichaDominoModel = new FichaDominoModel(fichaDominoDTO);
    }
    
    @Override
    public void redimencionarFichasJugadorLocal(){
        
    }

  
    

}
