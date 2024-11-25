/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapterEntidades;

import dominio.CasillaEntity;
import dominodto.CasillaDTO;

/**
 *
 * @author castr
 */
public interface IAdapterCasilla {

    CasillaDTO adaptToDTO(CasillaEntity entity);
}
