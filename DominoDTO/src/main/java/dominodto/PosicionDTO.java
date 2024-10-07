/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto;


/**
 *
 * @author asielapodaca
 */
public class PosicionDTO {
    private int posicionX; // posición sobre el tablero en el eje X
    private int posicionY; // posición sobre el tablero en el eje Y
    private int rotacion; // angulo de la ficha; 0, 90, 180, 270; rota en sentido contrario del reloj
    
    private int anchoPosicion; // ancho de la posición
    private int alturaPosicion; // alturaDeLaPosición
    
    private FichaDominoDTO fichaDominoDTO; // referencia a la ficha colocada en esta posición

    public PosicionDTO() {
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

    public int getRotacion() {
        return rotacion;
    }

    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
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

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }

    public void setFichaDominoDTO(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
    }
    
    
}
