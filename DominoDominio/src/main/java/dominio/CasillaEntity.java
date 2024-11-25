/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author asielapodaca
 */
public class CasillaEntity {
    
    public static final int MULA = 0;
    public static final int EXTREMO1 = 1;
    public static final int EXTREMO2 = 2;
    
    private int locacionX; // posición sobre el tablero en el eje X
    private int locacionY; // posición sobre el tablero en el eje Y
    private int rotacion; // angulo de la ficha; 0, 90, 180, 270; rota en sentido contrario del reloj
    
    private CasillaEntity siguienteCasilla; // referencia casilla siguiente de la double linked list
    private CasillaEntity anteriorCasilla; // referencia casilla anterior de la double linked list
    
    private FichaDominoEntity fichaDomino; // referencia a la ficha colocada en esta posición

    public CasillaEntity() {
        this.rotacion = 0;
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

    public CasillaEntity getSiguienteCasilla() {
        return siguienteCasilla;
    }

    public void setSiguienteCasilla(CasillaEntity siguienteCasilla) {
        this.siguienteCasilla = siguienteCasilla;
    }

    public CasillaEntity getAnteriorCasilla() {
        return anteriorCasilla;
    }

    public void setAnteriorCasilla(CasillaEntity anteriorCasilla) {
        this.anteriorCasilla = anteriorCasilla;
    }

    public FichaDominoEntity getFichaDomino() {
        return fichaDomino;
    }

    public void setFichaDomino(FichaDominoEntity fichaDomino) {
        this.fichaDomino = fichaDomino;
    }

    
    
    
}
