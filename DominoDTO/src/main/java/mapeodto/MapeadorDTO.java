/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapeodto;

import dominio.FichaDominoEntity;
import dominio.CasillaEntity;
import dominio.TableroDominoEntity;
import dominodto.FichaDominoDTO;
import dominodto.PosicionDTO;
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
    
    public PosicionDTO posicionEntityADTO(CasillaEntity posicion) {
        PosicionDTO posicionDTO = new PosicionDTO();
        
        posicionDTO.setPosicionX(posicion.getPosicionX());
        posicionDTO.setPosicionY(posicion.getPosicionY());
        posicionDTO.setRotacion(posicion.getRotacion());
        posicionDTO.setAnchoPosicion(posicion.getAlturaPosicion());
        posicionDTO.setAlturaPosicion(posicion.getAlturaPosicion());
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
            }while(posicion.getSiguientePosicion() != null);
            
        }
        
        return tableroDominoDTO;
    }
}
