/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterentidades;

import dominio.CasillaEntity;
import dominodto.CasillaDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class AdapterCasilla implements IAdapterCasilla {

    private final IAdapterFichaDomino adapterFichaDomino;

    public AdapterCasilla(IAdapterFichaDomino adapterFichaDomino) {
        this.adapterFichaDomino = adapterFichaDomino;
    }

    @Override
    public CasillaDTO adaptToDTO(CasillaEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("La entidad Casilla no puede ser nula");
        }
        CasillaDTO dto = new CasillaDTO();
        dto.setLocacionX(entity.getLocacionX());
        dto.setLocacionY(entity.getLocacionY());
        dto.setRotacion(entity.getRotacion());
        dto.setExtremo(entity.getExtremo());
        
        if (entity.getFichaDomino() != null) {
            dto.setFichaDominoDTO(adapterFichaDomino.adaptToDTO(entity.getFichaDomino()));
        }

        return dto;
    }

}
