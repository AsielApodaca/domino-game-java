package dominodto;

import dominodto.fichaDominoEnum.ExtremoCompatible;

/**
 *
 * @author asielapodaca
 */
public class FichaDominoDTO {

    private int valorExtremo1; // Número del 0 al 6 en el extremo 1 de la ficha
    private int valorExtremo2; // Número del 0 al 6 en el extremo 2 de la ficha

    /**
     * Extremo de la ficha compatible con la cadena de fichas en el tablero
     * Posibles valores: EXTREMO1; EXTREMO2.
     */
    private ExtremoCompatible extremoCompatible;

    public FichaDominoDTO(int valorExtremo1, int valorExtremo2) {
        this.valorExtremo1 = valorExtremo1;
        this.valorExtremo2 = valorExtremo2;
    }

    public FichaDominoDTO(int valorExtremo1, int valorExtremo2, ExtremoCompatible extremoCompatible) {
        this.valorExtremo1 = valorExtremo1;
        this.valorExtremo2 = valorExtremo2;
        this.extremoCompatible = extremoCompatible;
    }

    public ExtremoCompatible getExtremoCompatible() {
        return extremoCompatible;
    }

    public void setExtremoCompatible(ExtremoCompatible extremoCompatible) {
        this.extremoCompatible = extremoCompatible;
    }

    public int getValorExtremo1() {
        return valorExtremo1;
    }

    public int getValorExtremo2() {
        return valorExtremo2;
    }

    
}
