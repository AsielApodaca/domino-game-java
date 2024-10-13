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
        
        return fichaDominoTablero;
    }

    private void iniciarFichaParaTablero() {
        fichaDominoTablero.setFichaDominoDTO(casillaDTO.getFichaDominoDTO());
        int rotacion = casillaDTO.getRotacion();
        boolean isHorizontal = (rotacion == 0 || rotacion == 180);
        
        fichaDominoTablero.setRotacion(rotacion);
        fichaDominoTablero.setHorizontal(isHorizontal);
        
        // Set the base size (this will be scaled later)
        fichaDominoTablero.setAnchoSinEscala(isHorizontal ? 30 : 15);
        fichaDominoTablero.setAlturaSinEscala(isHorizontal ? 15 : 30);
    }
    
    @Override
    public void cargarImagenesFicha() {
        int extremo1 = fichaDTO.getValorExtremo1();
        int extremo2 = fichaDTO.getValorExtremo2();
        
        String img1 = String.format("/multimedia/Domino%d.png", extremo1);
        String img2 = String.format("/multimedia/Domino%d.png", extremo2);
        
        // The order of images depends on the rotation
        switch (casillaDTO.getRotacion()) {
            case 0:
                fichaDominoTablero.setImgExtremo1(img1);
                fichaDominoTablero.setImgExtremo2(img2);
                break;
            case 90:
                fichaDominoTablero.setImgExtremo1(img2);
                fichaDominoTablero.setImgExtremo2(img1);
                break;
            case 180:
                fichaDominoTablero.setImgExtremo1(img2);
                fichaDominoTablero.setImgExtremo2(img1);
                break;
            case 270:
                fichaDominoTablero.setImgExtremo1(img1);
                fichaDominoTablero.setImgExtremo2(img2);
                break;
        }
    }
}