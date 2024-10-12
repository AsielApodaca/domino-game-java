/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterEntidades;

import dominio.JugadorDominoEntity;
import dominodto.JugadorDominoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class AdapterJugadorDomino implements IAdapterJugadorDomino {
    
    private IAdapterFichaDomino adapterFichaDomino ;
    
    public AdapterJugadorDomino() {
        this.adapterFichaDomino = new AdapterFichaDomino() ;
    }

    @Override
    public JugadorDominoEntity adaptToEntity(JugadorDominoDTO jugadorDTO) {
        JugadorDominoEntity jugadorEntity = new JugadorDominoEntity(jugadorDTO.getNombre(), jugadorDTO.getIcono()) ;
        jugadorEntity.getListaFichasJugador().addAll(adapterFichaDomino.adaptListToEntity(jugadorDTO.getListaFichasJugador())) ;
        return jugadorEntity ;
    }

    @Override
    public JugadorDominoDTO adaptToDTO(JugadorDominoEntity jugadorEntity) {
        JugadorDominoDTO jugadorDTO = new JugadorDominoDTO(jugadorEntity.getNombre(), jugadorEntity.getIcon()) ;
        jugadorDTO.getListaFichasJugador().addAll(adapterFichaDomino.adaptListToDTO(jugadorEntity.getListaFichasJugador())) ;
        return jugadorDTO ;
    }

    @Override
    public List<JugadorDominoEntity> adaptListToEntity(List<JugadorDominoDTO> listaJugadoresDTO) {
        List<JugadorDominoEntity> listaJugadoresEntity = new ArrayList<>() ;
        
        listaJugadoresDTO.forEach(jugador -> {
            listaJugadoresEntity.add(adaptToEntity(jugador)) ;
        });
        
        return listaJugadoresEntity ;
    }

    @Override
    public List<JugadorDominoDTO> adaptListToDTO(List<JugadorDominoEntity> listaJugadoresEntity) {
        List<JugadorDominoDTO> listaJugadoresDTO = new ArrayList<>() ;
        
        listaJugadoresEntity.forEach(jugador -> {
            listaJugadoresDTO.add(adaptToDTO(jugador)) ;
        });
        
        return listaJugadoresDTO ;
    }
    
}
