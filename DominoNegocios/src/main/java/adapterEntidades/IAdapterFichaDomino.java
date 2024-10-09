/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapterEntidades;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;

/**
 *
 * @author castr
 */
public interface IAdapterFichaDomino {

    FichaDominoDTO adaptToDTO(FichaDominoEntity entity);
    
    FichaDominoEntity adaptToEntity(FichaDominoDTO dto);
}
