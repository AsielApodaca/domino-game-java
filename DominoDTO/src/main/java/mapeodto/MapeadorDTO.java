/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapeodto;

import dominio.FichaDominoEntity;
import dominio.CasillaEntity;
import dominio.TableroDominoEntity;
import dominodto.FichaDominoDTO;
import dominodto.CasillaDTO;
import dominodto.TableroDominoDTO;

/**
 * Clase MapeadorDTO.
 * Esta clase se encarga de mapear las entidades del dominio a sus respectivos Data Transfer Objects (DTO).
 * 
 * @author asielapodaca
 */
public class MapeadorDTO {

    /**
     * Mapea una entidad de ficha de dominó a su correspondiente DTO.
     *
     * @param ficha La entidad de la ficha de dominó a mapear.
     * @return Un objeto FichaDominoDTO que representa la ficha de dominó.
     */
    public FichaDominoDTO fichaEntityADTO(FichaDominoEntity ficha) {
        int extremo1 = ficha.getExtremo1();
        int extremo2 = ficha.getExtremo2();
        
        return new FichaDominoDTO(extremo1, extremo2);
    }
    
    /**
     * Mapea una entidad de casilla a su correspondiente DTO.
     *
     * @param posicion La entidad de la casilla a mapear.
     * @return Un objeto CasillaDTO que representa la casilla.
     */
    public CasillaDTO casillaEntityADTO(CasillaEntity posicion) {
        CasillaDTO casillaDTO = new CasillaDTO();
        
        casillaDTO.setLocacionX(posicion.getLocacionX());
        casillaDTO.setLocacionY(posicion.getLocacionY());
        casillaDTO.setRotacion(posicion.getRotacion());
        FichaDominoDTO fichaDominoDTO = fichaEntityADTO(posicion.getFichaDomino());
        if(fichaDominoDTO != null) {
            casillaDTO.setFichaDominoDTO(fichaDominoDTO);
        }
        
        return casillaDTO;
    }
    
    /**
     * Mapea una entidad de tablero de dominó a su correspondiente DTO.
     *
     * @param tableroDomino La entidad del tablero de dominó a mapear.
     * @return Un objeto TableroDominoDTO que representa el tablero de dominó.
     */
    public TableroDominoDTO tableroDominoEntityADTO(TableroDominoEntity tableroDomino) {
        int anchoTablero = tableroDomino.getAnchoTablero();
        int altoTablero = tableroDomino.getAltoTablero();
        TableroDominoDTO tableroDominoDTO = new TableroDominoDTO(anchoTablero, altoTablero);
        
        CasillaEntity casilla = tableroDomino.obtenerPrimerElemento();
        if(casilla != null) { // el tablero tiene por lo menos una ficha
            do { // Recorre todas las posiciones de fichas
                tableroDominoDTO.addPosicion(casillaEntityADTO(casilla));
            } while(casilla.getSiguienteCasilla() != null);
        }
        
        return tableroDominoDTO;
    }
}
