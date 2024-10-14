/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estrategiacomparador;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class EstrategiaComparadorEntidades implements IEstrategiaComparadorEntidades{

    @Override
    public FichaDominoEntity comparar(FichaDominoDTO fichaDTO, List<FichaDominoEntity> listaFichasEntity) {
        int dtoExtremo1 = fichaDTO.getValorExtremo1();
        int dtoExtremo2 = fichaDTO.getValorExtremo2();
        
        for(FichaDominoEntity fichaEntity : listaFichasEntity) {
            int entityExtremo1 = fichaEntity.getExtremo1();
            int entityExtremo2= fichaEntity.getExtremo2();
            
            if( // compara si los valores de los extremos son iguales
                (dtoExtremo1 == entityExtremo1 && dtoExtremo2 == entityExtremo2) ||
                (dtoExtremo1 == entityExtremo2 && dtoExtremo2 == entityExtremo1)
            ) {
                return fichaEntity;
            }
        }
        
        return null; // Null si no encontró una ficha igual
    }
    
}
