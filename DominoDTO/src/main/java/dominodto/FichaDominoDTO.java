package dominodto;


/**
 *
 * @author asielapodaca
 */
public class FichaDominoDTO {

    private int valorExtremo1; // Número del 0 al 6 en el extremo 1 de la ficha
    private int valorExtremo2; // Número del 0 al 6 en el extremo 2 de la ficha


    public FichaDominoDTO(int valorExtremo1, int valorExtremo2) {
        this.valorExtremo1 = valorExtremo1;
        this.valorExtremo2 = valorExtremo2;
    }

    public int getValorExtremo1() {
        return valorExtremo1;
    }

    public int getValorExtremo2() {
        return valorExtremo2;
    }

    
}
