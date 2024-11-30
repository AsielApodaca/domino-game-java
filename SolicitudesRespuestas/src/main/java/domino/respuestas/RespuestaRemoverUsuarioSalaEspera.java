/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.UsuarioDTO;

/**
 *
 * @author asielapodaca
 */
public class RespuestaRemoverUsuarioSalaEspera extends EventoRespuesta{
    private UsuarioDTO usuarioDTO;

    public RespuestaRemoverUsuarioSalaEspera(UsuarioDTO usuarioDTO, String idCliente) {
        super(idCliente);
        this.usuarioDTO = usuarioDTO;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }
    
}
