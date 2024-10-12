/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapterEntidades;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IAdapterFichaDomino {

    FichaDominoDTO adaptToDTO(FichaDominoEntity entity);
    
    FichaDominoEntity adaptToEntity(FichaDominoDTO dto);
    
    List<FichaDominoDTO> adaptListToDTO(List<FichaDominoEntity> listaFichasEntity);
}
