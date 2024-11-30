/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package generadorrespuestas;

import domino.fachada.IFachadaServidorProxy;
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
public interface IGeneradorRespuestas {

    void enviarRespuestaMostrarPantallaPartida(List<JugadorDominoDTO> jugadoresDTO);

    void enviarRespuestaMostrarFichasActualizadasDeJugador(String idCliente, List<FichaDominoDTO> fichasDomino);

    void enviarRespuestaActualizarCantidadFichas(JugadorDominoDTO jugador, int cantidadFichas);

    void enviarRespuestaOtorgarTurno(JugadorDominoDTO jugador);

    void enviarRespuestaMostrarCasillasDisponibles(String idCliente, List<CasillaDTO> casillas);

    void enviarRespuestaOcultarCasillasDisponibles(String idCliente);

    void enviarRespuestaColocarFichaTablero(CasillaDTO ficha);

    void enviarRespuestaDesbloquearPozo(String idCliente);

    void enviarRespuestaBloquearPozo(String idCliente);

    void setFachadaServidorProxy(IFachadaServidorProxy fachadaServidorProxy);
}
