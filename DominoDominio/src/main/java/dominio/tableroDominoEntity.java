/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author asielapodaca
 */
public class tableroDominoEntity {
    private PosicionEntity posicionMula; // referencia a la posición que contiene la ficha mula
    private PosicionEntity posicionExtremo1; // referencia a la posición del extremo 1
    private PosicionEntity posicionExtremo2; // referencia a la posición del extremo 2
    
    private int anchoTablero; // ancho usable del tablero para colocar fichas, cada unidad es el ancho de una ficha
    private int altoTablero; // altura usable del tablero para colocar fichas, cada unidad es el ancho de una ficha

    public tableroDominoEntity(int anchoTablero, int altoTablero) {
        this.posicionMula = new PosicionEntity();
        this.anchoTablero = anchoTablero;
        this.altoTablero = altoTablero;
    }
    
    
}
