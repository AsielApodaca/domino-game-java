/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generadorrespuestas;

import domino.fachada.IFachadaServidorProxy;
import domino.respuestas.RespuestaActualizarCantidadFichas;
import domino.respuestas.RespuestaColocarFichaTablero;
import domino.respuestas.RespuestaMostrarCasillasDisponibles;
import domino.respuestas.RespuestaMostrarFichasActualizadasDeJugador;
import domino.respuestas.RespuestaMostrarPantallaPartida;
import domino.respuestas.RespuestaOcultarCasillasDisponibles;
import domino.respuestas.RespuestaOtorgarTurno;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class GeneradorRespuestas implements IGeneradorRespuestas{

    private IFachadaServidorProxy fachadaServidorProxy;

    public GeneradorRespuestas(IFachadaServidorProxy fachadaServidorProxy) {
        this.fachadaServidorProxy = fachadaServidorProxy;
    }
    
    @Override
    public void enviarRespuestaMostrarPantallaPartida(List<JugadorDominoDTO> jugadoresDTO) {
        RespuestaMostrarPantallaPartida respuesta = new RespuestaMostrarPantallaPartida(jugadoresDTO);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    @Override
    public void enviarRespuestaMostrarFichasActualizadasDeJugador(String idCliente, List<FichaDominoDTO> fichasDomino) {
        RespuestaMostrarFichasActualizadasDeJugador respuesta = new RespuestaMostrarFichasActualizadasDeJugador(idCliente, fichasDomino);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    @Override
    public void enviarRespuestaActualizarCantidadFichas(JugadorDominoDTO jugador, int cantidadFichas) {
        RespuestaActualizarCantidadFichas respuesta = new RespuestaActualizarCantidadFichas(jugador, cantidadFichas);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    @Override
    public void enviarRespuestaOtorgarTurno(JugadorDominoDTO jugador) {
        RespuestaOtorgarTurno respuesta = new RespuestaOtorgarTurno(jugador);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    @Override
    public void enviarRespuestaMostrarCasillasDisponibles(String idCliente, List<CasillaDTO> casillas) {
        RespuestaMostrarCasillasDisponibles respuesta = new RespuestaMostrarCasillasDisponibles(idCliente, casillas);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    @Override
    public void enviarRespuestaOcultarCasillasDisponibles(String idCliente) {
        RespuestaOcultarCasillasDisponibles respuesta = new RespuestaOcultarCasillasDisponibles(idCliente);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    @Override
    public void enviarRespuestaColocarFichaTablero(CasillaDTO ficha) {
        RespuestaColocarFichaTablero respuesta = new RespuestaColocarFichaTablero(ficha);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    
    
}
