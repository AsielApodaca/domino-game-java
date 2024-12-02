/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.solicitudes;

import dominodto.SalaDTO;
import dominodto.UsuarioDTO;
import java.util.List;

/**
 *
 * @author olive
 */
public class SolicitudObtenerSalasDisponibles extends EventoSolicitud {
    
    private SalaDTO sala ;
    
    public SolicitudObtenerSalasDisponibles(UsuarioDTO usuarioDTO) {
        super(usuarioDTO);
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO salas) {
        this.sala = salas;
    }
    
}
