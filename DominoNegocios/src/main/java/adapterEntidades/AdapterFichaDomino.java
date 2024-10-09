/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterEntidades;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;

/**
 *
 * @author castr
 */
public class AdapterFichaDomino implements IAdapterFichaDomino {

    @Override
    public FichaDominoDTO adaptToDTO(FichaDominoEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("La entidad Ficha Domino no puede ser nula");
        }
        return new FichaDominoDTO(
                entity.getExtremo1(),
                entity.getExtremo2()
        );
    }

    @Override
    public FichaDominoEntity adaptToEntity(FichaDominoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        return new FichaDominoEntity(
                dto.getValorExtremo1(),
                dto.getValorExtremo2()
        );
    }

}
