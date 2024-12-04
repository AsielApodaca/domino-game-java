/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaMostrarResultadoPartida;
import logica.buscarsalalogica.IBuscarSalaLogica;
import logica.partidadominologica.IPartidaDominoLogica;

/**
 *
 * @author castr
 */
public class ManejadorRespuestaResultadoPartida extends ManejadorRespuestaClienteProxy {

    private IPartidaDominoLogica partidaDominoLogica;

    /**
     * Constructor que inicializa la lógica de la partida.
     *
     * @param partidaDominoLogica instancia de la lógica de la partida de
     * dominó.
     */
    public ManejadorRespuestaResultadoPartida(IPartidaDominoLogica partidaDominoLogica) {
        this.partidaDominoLogica = partidaDominoLogica;
    }

    public ManejadorRespuestaResultadoPartida(IPartidaDominoLogica partidaDominoLogica, ManejadorRespuestaClienteProxy manejador) {
        super(manejador);
        this.partidaDominoLogica = partidaDominoLogica;
    }

    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaMostrarResultadoPartida;
    }

    @Override
    protected void procesar(EventoRespuesta evento) {
        RespuestaMostrarResultadoPartida respuesta = (RespuestaMostrarResultadoPartida) evento;

        partidaDominoLogica.mostrarResultadoPartida();
    }

}
