/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto.mapeodto;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;
import dominodto.fichaDominoEnum.ExtremoCompatibleDTO;
import dominoenums.ExtremoCompatible;

/**
 *
 * @author asielapodaca
 */
public class MapeadorDTO {
    public FichaDominoDTO fichaEntityADTO(FichaDominoEntity ficha) {
        int extremo1 = ficha.getExtremo1();
        int extremo2 = ficha.getExtremo2();
        ExtremoCompatibleDTO extremoCompatibleDTO;
        switch(ficha.getPosicion()) {
            case ExtremoCompatible.EXTREMO1:
                extremoCompatibleDTO = ExtremoCompatibleDTO.EXTREMO1;
                break;
            case ExtremoCompatible.EXTREMO2:
                extremoCompatibleDTO = ExtremoCompatibleDTO.EXTREMO2;
                break;   
            default:
                extremoCompatibleDTO = null;
        }
        
        return new FichaDominoDTO(extremo1, extremo2, extremoCompatibleDTO);
        
    }
}
