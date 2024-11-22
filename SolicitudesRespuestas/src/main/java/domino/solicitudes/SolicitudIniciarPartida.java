/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.solicitudes;

import dominodto.UsuarioDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class SolicitudIniciarPartida extends EventoSolicitud{
    private List<UsuarioDTO> usuarios; // jugadores de la partida
    
    public SolicitudIniciarPartida(UsuarioDTO usuarioDTO) {
        super(usuarioDTO);
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }
    
}
