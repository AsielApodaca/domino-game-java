/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.solicitudes;

import dominodto.ConfiguracionJuegoDTO;
import dominodto.UsuarioDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class SolicitudIniciarPartida extends EventoSolicitud{
    private List<UsuarioDTO> usuarios; // jugadores de la partida
    private ConfiguracionJuegoDTO configuracionJuegoDTO; // Configuracion de la partida
    
    public SolicitudIniciarPartida(UsuarioDTO usuarioDTO, List<UsuarioDTO> usuarios, ConfiguracionJuegoDTO configuracionJuegoDTO) {
        super(usuarioDTO);
        this.usuarios = usuarios;
        this.configuracionJuegoDTO = configuracionJuegoDTO;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public ConfiguracionJuegoDTO getConfiguracionJuegoDTO() {
        return configuracionJuegoDTO;
    }
    
    
}
