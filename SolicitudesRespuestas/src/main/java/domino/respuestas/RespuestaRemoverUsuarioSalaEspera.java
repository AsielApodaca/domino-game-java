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
    public static final boolean ES_PARA_TODOS = true;
    private UsuarioDTO usuarioDTO;

    public RespuestaRemoverUsuarioSalaEspera(UsuarioDTO usuarioDTO) {
        super(null);
        this.usuarioDTO = usuarioDTO;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }
    
    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS;
    }
}
