package fichabuilder;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import presentacion.partidadomino.fichadominotablero.FichaDominoTablero;

public class FichaBuilderTablero implements IFichaBuilder {
    private FichaDominoTablero fichaDominoTablero;
    private CasillaDTO casillaDTO;
    private FichaDominoDTO fichaDTO;

    public FichaDominoTablero construirFicha(CasillaDTO casillaDTO) {
        this.casillaDTO = casillaDTO;
        this.fichaDTO = casillaDTO.getFichaDominoDTO();
        this.fichaDominoTablero = new FichaDominoTablero();
        iniciarFichaParaTablero();
        cargarImagenesFicha();
        fichaDominoTablero.cargarFondo();
        
        return fichaDominoTablero;
    }

    private void iniciarFichaParaTablero() {
        fichaDominoTablero.setFichaDominoDTO(casillaDTO.getFichaDominoDTO());
        int rotacion = casillaDTO.getRotacion();
        fichaDominoTablero.setRotacion(rotacion);
    }
    
    @Override
    public void cargarImagenesFicha() {
        int extremo1 = fichaDTO.getValorExtremo1();
        int extremo2 = fichaDTO.getValorExtremo2();
        int rotacion = casillaDTO.getRotacion();
        String img1;
        String img2;
        
        if(rotacion == 0 || rotacion == 90) {
            img1 = String.format("/multimedia/Domino%d.png", extremo1);
            img2 = String.format("/multimedia/Domino%d.png", extremo2);
        } else {
            img1 = String.format("/multimedia/Domino%d.png", extremo2);
            img2 = String.format("/multimedia/Domino%d.png", extremo1);
        }
        
        
        fichaDominoTablero.setImgExtremo1(img1);
        fichaDominoTablero.setImgExtremo2(img2);
    }
}