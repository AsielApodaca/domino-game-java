/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.solicitudes;

import dominodto.UsuarioDTO;

/**
 *
 * @author castr
 */
public class SolicitudUnirseSala extends EventoSolicitud {
    
    private String idSala;

    public SolicitudUnirseSala(UsuarioDTO usuarioDTO, String idSala) {
        super(usuarioDTO);
        this.idSala = idSala;
    }

    public String getIdSala() {
        return idSala;
    }
    
}
