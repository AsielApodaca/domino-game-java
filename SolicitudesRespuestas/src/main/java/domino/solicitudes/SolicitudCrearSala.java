/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.solicitudes;

import dominodto.ConfiguracionJuegoDTO;
import dominodto.UsuarioDTO;

/**
 *
 * @author castr
 */
public class SolicitudCrearSala extends EventoSolicitud{

    private ConfiguracionJuegoDTO configuracionJuegoDTO;
    private int limiteJugadores;

    public SolicitudCrearSala(ConfiguracionJuegoDTO configuracionJuegoDTO, UsuarioDTO usuarioDTO) {
        super(usuarioDTO);
        this.configuracionJuegoDTO = configuracionJuegoDTO;
        this.limiteJugadores = configuracionJuegoDTO.getLimiteJugadores();
    }
    
    public SolicitudCrearSala(UsuarioDTO usuarioDTO) {
        super(usuarioDTO);
    }

    public ConfiguracionJuegoDTO getConfiguracionJuegoDTO() {
        return configuracionJuegoDTO;
    }

    public int getLimiteJugadores() {
        return limiteJugadores;
    }
    
    
    
}
