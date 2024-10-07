/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author asielapodaca
 */
public class PosicionEntity {
    
    private int posicionX; // posición sobre el tablero en el eje X
    private int posicionY; // posición sobre el tablero en el eje X
    private FichaDominoEntity fichaDomino; // referencia a la ficha colocada en esta posición

    public PosicionEntity() {
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public FichaDominoEntity getFichaDomino() {
        return fichaDomino;
    }

    public void setFichaDomino(FichaDominoEntity fichaDomino) {
        this.fichaDomino = fichaDomino;
    }
    
    
}
