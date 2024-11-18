package dominodto;


/**
 *
 * @author asielapodaca
 */
public class FichaDominoDTO {

    private int valorExtremo1; // Número del 0 al 6 en el extremo 1 de la ficha
    private int valorExtremo2; // Número del 0 al 6 en el extremo 2 de la ficha
    private boolean compatible; // Si la ficha es compatible con algún extremo


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

    public void setValorExtremo1(int valorExtremo1) {
        this.valorExtremo1 = valorExtremo1;
    }

    public void setValorExtremo2(int valorExtremo2) {
        this.valorExtremo2 = valorExtremo2;
    }

    public boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FichaDominoDTO other = (FichaDominoDTO) obj;
        if (this.valorExtremo1 != other.valorExtremo1) {
            return false;
        }
        return this.valorExtremo2 == other.valorExtremo2;
    }

    
    
}
