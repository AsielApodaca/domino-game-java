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
    private int posicionY; // posición sobre el tablero en el eje Y
    private int rotacion; // angulo de la ficha; 0, 90, 180, 270; rota en sentido contrario del reloj
    
    private int anchoPosicion; // ancho de la posición
    private int alturaPosicion; // alturaDeLaPosición
    
    private PosicionEntity siguientePosicion; // referencia posición siguiente
    private PosicionEntity anteriorPosicion; // referencia posición anterior
    
    private FichaDominoEntity fichaDomino; // referencia a la ficha colocada en esta posición

    public PosicionEntity() {
        this.rotacion = 0;
    }

    public int getAnchoPosicion() {
        return anchoPosicion;
    }

    public void setAnchoPosicion(int anchoPosicion) {
        this.anchoPosicion = anchoPosicion;
    }

    public int getAlturaPosicion() {
        return alturaPosicion;
    }

    public void setAlturaPosicion(int alturaPosicion) {
        this.alturaPosicion = alturaPosicion;
    }

    public int getRotacion() {
        return rotacion;
    }

    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
    }

    public PosicionEntity getSiguientePosicion() {
        return siguientePosicion;
    }

    public void setSiguientePosicion(PosicionEntity siguientePosicion) {
        this.siguientePosicion = siguientePosicion;
    }

    public PosicionEntity getAnteriorPosicion() {
        return anteriorPosicion;
    }

    public void setAnteriorPosicion(PosicionEntity anteriorPosicion) {
        this.anteriorPosicion = anteriorPosicion;
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
