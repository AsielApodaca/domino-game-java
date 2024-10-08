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
    private int locacionX; // posición sobre el tablero en el eje X
    private int locacionY; // posición sobre el tablero en el eje Y
    private int rotacion; // angulo de la ficha; 0, 90, 180, 270; rota en sentido contrario del reloj
    
    private int anchoCasilla; // ancho de la posición
    private int alturaCasilla; // alturaDeLaPosición
    
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

    public int getAnchoCasilla() {
        return anchoCasilla;
    }

    public void setAnchoCasilla(int anchoCasilla) {
        this.anchoCasilla = anchoCasilla;
    }

    public int getAlturaCasilla() {
        return alturaCasilla;
    }

    public void setAlturaCasilla(int alturaCasilla) {
        this.alturaCasilla = alturaCasilla;
    }

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }

    public void setFichaDominoDTO(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
    }

    
    
    
}
