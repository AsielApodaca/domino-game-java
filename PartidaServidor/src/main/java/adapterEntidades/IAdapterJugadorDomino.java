/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapterEntidades;

import dominio.JugadorDominoEntity;
import dominodto.JugadorDominoDTO;
import dominodto.UsuarioDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IAdapterJugadorDomino {
    
    JugadorDominoEntity adaptToEntity(UsuarioDTO usuario) ;
    
    JugadorDominoDTO adaptToDTO(JugadorDominoEntity jugadorEntity) ;
    
}
