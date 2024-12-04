/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaRemoverUsuarioSalaEspera;
import dominodto.UsuarioDTO;
import logica.salaesperalogica.ISalaEsperaLogica;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaRemoverUsuarioSalaEspera extends ManejadorRespuestaClienteProxy {

    private ISalaEsperaLogica salaEsperaLogica;

    public ManejadorRespuestaRemoverUsuarioSalaEspera(ISalaEsperaLogica salaEsperaLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.salaEsperaLogica = salaEsperaLogica;
    }

    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaRemoverUsuarioSalaEspera;
    }

    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaRemoverUsuarioSalaEspera respuesta = (RespuestaRemoverUsuarioSalaEspera) evento;
        UsuarioDTO usuarioDTO = respuesta.getUsuarioDTO();
        salaEsperaLogica.removerUsuarioDeSala(usuarioDTO);
    }

}
