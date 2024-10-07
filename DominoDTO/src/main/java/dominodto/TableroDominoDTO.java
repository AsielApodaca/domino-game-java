/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class TableroDominoDTO {
    private int anchoTablero; // ancho usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)
    private int altoTablero;// altura usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)
    private List<PosicionDTO> listaPosiciones; // lista de posiciones utilizadas en el tablero

    public TableroDominoDTO(int anchoTablero, int altoTablero) {
        this.anchoTablero = anchoTablero;
        this.altoTablero = altoTablero;
        this.listaPosiciones = new ArrayList<>();
    }

    public int getAnchoTablero() {
        return anchoTablero;
    }

    public void setAnchoTablero(int anchoTablero) {
        this.anchoTablero = anchoTablero;
    }

    public int getAltoTablero() {
        return altoTablero;
    }

    public void setAltoTablero(int altoTablero) {
        this.altoTablero = altoTablero;
    }

    public List<PosicionDTO> getListaPosiciones() {
        return listaPosiciones;
    }

    public void setListaPosiciones(List<PosicionDTO> listaPosiciones) {
        this.listaPosiciones = listaPosiciones;
    }
    
    public void addPosicion(PosicionDTO posicionDTO) {
        this.listaPosiciones.add(posicionDTO);
    }
    
    
}
