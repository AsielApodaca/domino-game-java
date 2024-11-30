/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaDesbloquearPozo;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaDesbloquearPozo extends ManejadorRespuestaClienteProxy {

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor que inicializa el manejador con la lógica de la partida.
     *
     * @param partidaDominoLogica la lógica de la partida de dominó.
     */
    public ManejadorRespuestaDesbloquearPozo(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

     /**
     * Constructor que permite encadenar con el siguiente manejador de la
     * cadena.
     *
     * @param partidaDominoLogica La lógica que maneja las acciones en la
     * partida.
     * @param siguienteManejador El siguiente manejador en la cadena de
     * responsabilidad.
     */
    public ManejadorRespuestaDesbloquearPozo(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }
    
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaDesbloquearPozo;
    }

    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaDesbloquearPozo respuesta= (RespuestaDesbloquearPozo) evento;
        partidaDominoLogica.mostrarPozoDisponible();
    }

}
