/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto.mapeodto;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;

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
}
