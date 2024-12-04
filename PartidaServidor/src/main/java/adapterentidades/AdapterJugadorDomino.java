/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterentidades;

import dominio.JugadorDominoEntity;
import dominodto.JugadorDominoDTO;
import dominodto.UsuarioDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class AdapterJugadorDomino implements IAdapterJugadorDomino {
    
    
    public AdapterJugadorDomino() {
    }

    @Override
    public JugadorDominoEntity adaptToEntity(UsuarioDTO usuario) {
        JugadorDominoEntity jugadorEntity = new JugadorDominoEntity(usuario.getIdCliente(), usuario.getNombre(), usuario.getFuenteIcono()) ;
        return jugadorEntity ;
    }

    @Override
    public JugadorDominoDTO adaptToDTO(JugadorDominoEntity jugadorEntity) {
        JugadorDominoDTO jugadorDTO = new JugadorDominoDTO(jugadorEntity.getIdCliente(), jugadorEntity.getNombre(), jugadorEntity.getFuenteIcono()) ;
        return jugadorDTO ;
    }

    @Override
    public UsuarioDTO adaptToUsuarioDTO(JugadorDominoEntity jugadorEntity) {
        UsuarioDTO usuarioDTO = new UsuarioDTO() ;
        usuarioDTO.setFuenteIcono(jugadorEntity.getFuenteIcono());
        usuarioDTO.setIdCliente(jugadorEntity.getIdCliente());
        usuarioDTO.setNombre(jugadorEntity.getNombre());
        return usuarioDTO ;
    }
    
}
