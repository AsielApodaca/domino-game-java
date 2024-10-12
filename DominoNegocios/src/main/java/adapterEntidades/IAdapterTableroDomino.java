/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapterEntidades;

import dominio.TableroDominoEntity;
import dominodto.TableroDominoDTO;

/**
 *
 * @author castr
 */
public interface IAdapterTableroDomino {

    TableroDominoDTO convertToDTO(TableroDominoEntity entity);

    TableroDominoEntity convertToEntity(TableroDominoDTO dto);

}
