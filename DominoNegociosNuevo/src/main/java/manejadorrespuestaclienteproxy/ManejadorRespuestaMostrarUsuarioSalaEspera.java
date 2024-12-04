/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaMostrarUsuarioSalaEspera;
import dominodto.UsuarioDTO;
import logica.salaesperalogica.ISalaEsperaLogica;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaMostrarUsuarioSalaEspera extends ManejadorRespuestaClienteProxy {

    private ISalaEsperaLogica salaEsperaLogica;

    public ManejadorRespuestaMostrarUsuarioSalaEspera(ISalaEsperaLogica salaEsperaLogica) {
        this.salaEsperaLogica = salaEsperaLogica;
    }

    public ManejadorRespuestaMostrarUsuarioSalaEspera(ISalaEsperaLogica salaEsperaLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.salaEsperaLogica = salaEsperaLogica;
    }

    /**
     * Verifica si el manejador puede procesar el evento recibido.
     * 
     * @param evento Evento que se desea procesar.
     * @return {@code true} si el evento es de tipo {@link RespuestaMostrarUsuarioSalaEspera}, {@code false} en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaMostrarUsuarioSalaEspera;
    }

     /**
     * Procesa el evento de tipo {@link RespuestaMostrarUsuarioSalaEspera}.
     * Extrae la información del usuario y lo agrega a la sala de espera utilizando la lógica correspondiente.
     * 
     * @param evento Evento de respuesta que contiene la información del usuario a mostrar.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaMostrarUsuarioSalaEspera respuesta = (RespuestaMostrarUsuarioSalaEspera) evento;
        UsuarioDTO usuarioDTO = respuesta.getUsuarioDTO();
        salaEsperaLogica.agregarUsuarioASala(usuarioDTO);
    }

}
