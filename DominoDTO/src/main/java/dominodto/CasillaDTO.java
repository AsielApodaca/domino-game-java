/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto;


/**
 *
 * @author asielapodaca
 */
public class CasillaDTO {
    
    
    public final int MULA = 0;
    public final int EXTREMO1 = 1;
    public final int EXTREMO2 = 2;
    private int extremo;
    
    private int locacionX; // posición sobre el tablero en el eje X
    private int locacionY; // posición sobre el tablero en el eje Y
    private int rotacion; // angulo de la ficha; 0, 90, 180, 270; rota en sentido contrario del reloj
    
    private FichaDominoDTO fichaDominoDTO; // referencia a la ficha colocada en esta posición

    public CasillaDTO() {
    }

    public int getLocacionX() {
        return locacionX;
    }

    public void setLocacionX(int locacionX) {
        this.locacionX = locacionX;
    }

    public int getLocacionY() {
        return locacionY;
    }

    public void setLocacionY(int locacionY) {
        this.locacionY = locacionY;
    }

    public int getRotacion() {
        return rotacion;
    }

    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
    }

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }

    public void setFichaDominoDTO(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
    }

    public int getExtremo() {
        return extremo;
    }

    public void setExtremo(int extremo) {
        this.extremo = extremo;
    }

    
    
    
    
}
