/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generadorrespuestas;

import domino.fachada.IFachadaServidorProxy;
import domino.respuestas.RespuestaActualizarCantidadFichas;
import domino.respuestas.RespuestaAprobarCreacionSala;
import domino.respuestas.RespuestaBloquearPozo;
import domino.respuestas.RespuestaCerrarSala;
import domino.respuestas.RespuestaColocarFichaTablero;
import domino.respuestas.RespuestaDesbloquearPozo;
import domino.respuestas.RespuestaMostrarCasillasDisponibles;
import domino.respuestas.RespuestaMostrarFichasActualizadasDeJugador;
import domino.respuestas.RespuestaMostrarJugadoresPartida;
import domino.respuestas.RespuestaMostrarPantallaPartida;
import domino.respuestas.RespuestaMostrarResultadoPartida;
import domino.respuestas.RespuestaOcultarCasillasDisponibles;
import domino.respuestas.RespuestaOtorgarTurno;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class GeneradorRespuestas implements IGeneradorRespuestas {

    private IFachadaServidorProxy fachadaServidorProxy;

    public GeneradorRespuestas() {
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

    @Override
    public void setFachadaServidorProxy(IFachadaServidorProxy fachadaServidorProxy) {
        this.fachadaServidorProxy = fachadaServidorProxy;
    }

    @Override
    public void enviarRespuestaDesbloquearPozo(String idCliente) {
        RespuestaDesbloquearPozo respuesta = new RespuestaDesbloquearPozo(idCliente);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }
    
    @Override
    public void enviarRespuestaMostrarJugadoresPartida(List<JugadorDominoDTO> jugadoresEnOrden, String idCliente) {
        RespuestaMostrarJugadoresPartida respuesta = new RespuestaMostrarJugadoresPartida(idCliente, jugadoresEnOrden);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    @Override
    public void enviarRespuestaBloquearPozo(String idCliente) {
        RespuestaBloquearPozo respuesta = new RespuestaBloquearPozo(idCliente);
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }

    @Override
    public void enviarRespuestaAprobarCreacionSala(String idCliente) {
        RespuestaAprobarCreacionSala respuesta = new RespuestaAprobarCreacionSala(idCliente) ;
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }
    
    @Override
    public void enviarRespuestaMostrarResultadoPartida(JugadorDominoDTO ganador) {
        RespuestaMostrarResultadoPartida respuesta = new RespuestaMostrarResultadoPartida(ganador) ;
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }
    
    @Override
    public void enviarRespuestaCerrarSaala() {
        RespuestaCerrarSala respuesta = new RespuestaCerrarSala() ;
        fachadaServidorProxy.enviarRespuestas(respuesta);
    }
    
}
