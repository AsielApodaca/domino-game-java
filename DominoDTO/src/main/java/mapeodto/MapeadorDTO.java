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
 *
 * @author asielapodaca
 */
public class MapeadorDTO {
    public FichaDominoDTO fichaEntityADTO(FichaDominoEntity ficha) {
        int extremo1 = ficha.getExtremo1();
        int extremo2 = ficha.getExtremo2();
        
        return new FichaDominoDTO(extremo1, extremo2);
        
    }
    
    public CasillaDTO posicionEntityADTO(CasillaEntity posicion) {
        CasillaDTO posicionDTO = new CasillaDTO();
        
        posicionDTO.setLocacionX(posicion.getLocacionX());
        posicionDTO.setLocacionY(posicion.getLocacionY());
        posicionDTO.setRotacion(posicion.getRotacion());
        posicionDTO.setAnchoCasilla(posicion.getAlturaCasilla());
        posicionDTO.setAlturaCasilla(posicion.getAlturaCasilla());
        FichaDominoDTO fichaDominoDTO = fichaEntityADTO(posicion.getFichaDomino());
        if(fichaDominoDTO != null) {
            posicionDTO.setFichaDominoDTO(fichaDominoDTO);
        }
        
        return posicionDTO;
    }
    
    public TableroDominoDTO tableroDominoEntityADTO(TableroDominoEntity tableroDomino) {
        
        int anchoTablero = tableroDomino.getAnchoTablero();
        int altoTablero = tableroDomino.getAltoTablero();
        TableroDominoDTO tableroDominoDTO = new TableroDominoDTO(anchoTablero, altoTablero);
        
        CasillaEntity posicion = tableroDomino.obtenerPrimerElemento();
        if(posicion != null) { // el tablero tiene por lo menos una ficha
            do { // Recorre todas las posiciones de fichas
                tableroDominoDTO.addPosicion(posicionEntityADTO(posicion));
            }while(posicion.getSiguienteCasilla() != null);
            
        }
        
        return tableroDominoDTO;
    }
}
