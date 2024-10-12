/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fichabuilder;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import presentacion.partidadomino.fichadominojugador.FichaDominoModel;
import presentacion.partidadomino.fichadominotablero.FichaDominoTablero;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FichaBuilderTablero implements IFichaBuilder {

    private FichaDominoModel fichaDominoModel;
    private FichaDominoTablero fichaDominoTablero;

    @Override
    public void asignarExtremos(FichaDominoDTO ficha) {
        fichaDominoTablero.setValorExtremo1(ficha.getValorExtremo1());
        fichaDominoTablero.setValorExtremo2(ficha.getValorExtremo2());
    }

    @Override
    public void cargarImagenesFicha(int extremo1, int extremo2) {
        fichaDominoTablero.setImgExtremo1(String.format("/multimedia/Domino%d.png", extremo1));
        fichaDominoTablero.setImgExtremo2(String.format("/multimedia/Domino%d.png", extremo2));
    }

    public void construirFicha(CasillaDTO casillaDTO) {

    }

}
