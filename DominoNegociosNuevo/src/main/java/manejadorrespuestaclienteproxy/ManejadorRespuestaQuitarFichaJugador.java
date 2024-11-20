/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaQuitarFichaJugador;
import dominodto.FichaDominoDTO;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 *
 * @author asielapodaca
 */
public class ManejadorRespuestaQuitarFichaJugador extends ManejadorRespuestaClienteProxy{
    
    private IPartidaDominoLogica partidaDominoLogica;

    public ManejadorRespuestaQuitarFichaJugador(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    public ManejadorRespuestaQuitarFichaJugador(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }

    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaQuitarFichaJugador;
    }

    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaQuitarFichaJugador respuesta = (RespuestaQuitarFichaJugador) evento;
        FichaDominoDTO fichaDomino = respuesta.getFichaDominoDTO();
        partidaDominoLogica.removerFichaJugadorLocal(fichaDomino);
    }
    
}
