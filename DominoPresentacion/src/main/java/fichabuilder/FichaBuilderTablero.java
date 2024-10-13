/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fichabuilder;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.awt.Dimension;
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

    private FichaDominoTablero fichaDominoTablero;
    private CasillaDTO casillaDTO;

    @Override
    public void asignarExtremos() {
//        fichaDominoTablero.setValorExtremo1(ficha.getValorExtremo1());
//        fichaDominoTablero.setValorExtremo2(ficha.getValorExtremo2());
    }

    @Override
    public void cargarImagenesFicha(int extremo1, int extremo2) {
        fichaDominoTablero.setImgExtremo1(String.format("/multimedia/Domino%d.png", extremo1));
        fichaDominoTablero.setImgExtremo2(String.format("/multimedia/Domino%d.png", extremo2));
    }

    public void construirFicha(CasillaDTO casillaDTO) {
        this.casillaDTO = casillaDTO;
        this.fichaDominoTablero = new FichaDominoTablero();
        iniciarFichaParaTablero();
    }

    private void iniciarFichaParaTablero() {
        fichaDominoTablero.setFichaDominoDTO(casillaDTO.getFichaDominoDTO());
        int rotacion = casillaDTO.getRotacion();
        int anchoFicha = -1;
        int alturaFicha = -1;
        
        switch(rotacion) { // Asigna orientación de la ficha
            case 0: // Horizontal
                anchoFicha = 30;
                alturaFicha = 15;
            case 90: // Vertical
                anchoFicha = 15;
                alturaFicha = 30;
                voltearExtremosFicha();
                break;
            case 180: // Horizontal
                anchoFicha = 30;
                alturaFicha = 15;
                voltearExtremosFicha();
            case 270: // Vertical
                anchoFicha = 15;
                alturaFicha = 30;
        }
        
        fichaDominoTablero.setPreferredSize(new Dimension(anchoFicha, alturaFicha));
                
    }
    
    private void voltearExtremosFicha() {
        FichaDominoDTO fichaDTO = this.casillaDTO.getFichaDominoDTO();
        int valorExtremo1Copia = fichaDTO.getValorExtremo1();
        fichaDTO.setValorExtremo1(fichaDTO.getValorExtremo2());
        fichaDTO.setValorExtremo2(valorExtremo1Copia);
    }

}
