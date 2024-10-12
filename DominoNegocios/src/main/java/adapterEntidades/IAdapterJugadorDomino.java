/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapterEntidades;

import dominio.JugadorDominoEntity;
import dominodto.JugadorDominoDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IAdapterJugadorDomino {
    
    JugadorDominoEntity adaptToEntity(JugadorDominoDTO jugadorDTO) ;
    
    JugadorDominoDTO adaptToDTO(JugadorDominoEntity jugadorEntity) ;
    
    List<JugadorDominoEntity> adaptListToEntity(List<JugadorDominoDTO> listaJugadoesDTO) ;
    
    List<JugadorDominoDTO> adaptListToDTO(List<JugadorDominoEntity> listaJugadoresEntity) ;
    
}
