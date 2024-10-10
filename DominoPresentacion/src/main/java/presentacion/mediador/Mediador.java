/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.mediador;

import dominodto.FichaDominoDTO;
import java.awt.Dimension;
import java.util.ArrayList;
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
    private List<FichaDominoModel> fichaModels;

    public Mediador() {

    }

    @Override
    public void crearFichasJugadorLocal() {
        List<FichaDominoDTO> fichas = partidaDomino.getListaFichasJugadorLocal();
        for (FichaDominoDTO ficha : fichas) {
            FichaDominoModel model = new FichaDominoModel(ficha);
            fichaModels.add(model);
        }
    }

    @Override
    public List<FichaDominoModel> obtenerFichasJugadorLocal() {
        return fichaModels;
    }

    @Override
    public void redimencionarFichasJugadorLocal() {
        float escala = partidaDomino.getEscala();
        int anchoFicha = (int) (partidaDomino.getAnchoFichaJugadorLocal() * escala);
        int altoFicha = (int) (partidaDomino.getAlturaFichaJugadorLocal() * escala);

        for (FichaDominoModel model : fichaModels) {
            model.setDimensiones(anchoFicha, altoFicha);
        }
    }

}
