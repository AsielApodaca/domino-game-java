/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import adapterEntidades.AdapterCasilla;
import adapterEntidades.AdapterFichaDomino;
import adapterEntidades.AdapterJugadorDomino;
import adapterEntidades.IAdapterCasilla;
import adapterEntidades.IAdapterFichaDomino;
import adapterEntidades.IAdapterJugadorDomino;
import controladores.controladorfichas.ControladorFichas;
import controladores.controladorfichas.IControladorFichas;
import controladores.controladorjugadores.ControladorJugadores;
import controladores.controladorjugadores.IControladorJugadores;
import controladores.controladortablero.ControladorTablero;
import controladores.controladortablero.IControladorTablero;
import controladores.controladorturnos.ControladorTurnos;
import controladores.controladorturnos.IControladorTurnos;
import dominio.JugadorDominoEntity;
import domino.respuestas.RespuestaMostrarPantallaPartida;
import dominodto.CasillaDTO;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import dominodto.UsuarioDTO;
import generadorrespuestas.IGeneradorRespuestas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class PartidaServerLogica implements IPartidaServerLogica{
    
    private IGeneradorRespuestas generadorRespuestas;
    private IControladorFichas controladorFichas;
    private IControladorJugadores controladorJugadores;
    private IControladorTablero controladorTablero;
    private IControladorTurnos controladorTurnos;
    private IAdapterFichaDomino adapterFichaDomino;
    private IAdapterJugadorDomino adapterJugadorDomino;
    private IAdapterCasilla adapterCasilla;
    private ConfiguracionJuegoDTO configuracionJuegoDTO;

    public PartidaServerLogica(IGeneradorRespuestas generadorRespuestas) {
        this.generadorRespuestas = generadorRespuestas;
    }

    @Override
    public void procesarIniciarPartida(List<UsuarioDTO> usuariosDTO, ConfiguracionJuegoDTO configuracionPartida) {
        this.configuracionJuegoDTO = configuracionPartida;
        iniciarControladores();
        iniciarAdapters();
        registrarUsuariosComoJugadores(usuariosDTO);
        mostrarPantallaPartidaAJugadores();
        repartirFichas();
        repartirTurnos();
        mostrarFichasAJugadores();
        iniciarPartida();
        
    }

    @Override
    public void procesarFichaSeleccionada(FichaDominoDTO ficha, UsuarioDTO usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void procesarCasillaSeleccionada(CasillaDTO casilla, UsuarioDTO usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void iniciarControladores() {
        controladorFichas = new ControladorFichas();
        controladorFichas.crearPozo();
        controladorJugadores = new ControladorJugadores();
        controladorTablero = new ControladorTablero(adapterFichaDomino);
        controladorTurnos = new ControladorTurnos();
    }
    
    private void iniciarAdapters() {
        adapterFichaDomino = new AdapterFichaDomino(controladorFichas.obtenerTodasLasFichasDelJuego());
        adapterJugadorDomino = new AdapterJugadorDomino();
        adapterCasilla = new AdapterCasilla(adapterFichaDomino);
    }
    
    private void registrarUsuariosComoJugadores(List<UsuarioDTO> listaUsuarios) {
        List<JugadorDominoEntity> listaJugadores = new ArrayList<>();
        for(UsuarioDTO usuario : listaUsuarios) {
            JugadorDominoEntity jugador = adapterJugadorDomino.adaptToEntity(usuario);
            listaJugadores.add(jugador);
        }
        controladorJugadores.agregarJugadores(listaJugadores);
    }
    
    private void mostrarPantallaPartidaAJugadores() {
        List<JugadorDominoEntity> listaJugadoresEntity = controladorJugadores.obtenerJugadores();
        List<JugadorDominoDTO> listaJugadoresDTO = new ArrayList<>();
        for(JugadorDominoEntity jugadorEntity : listaJugadoresEntity) {
            JugadorDominoDTO jugadorDTO = adapterJugadorDomino.adaptToDTO(jugadorEntity);
            listaJugadoresDTO.add(jugadorDTO);
        }
        
        // Envia la respuesta
        generadorRespuestas.enviarRespuestaMostrarPantallaPartida(listaJugadoresDTO);
    }
    
    private void repartirFichas() {
        List<JugadorDominoEntity> jugadores = controladorJugadores.obtenerJugadores();
        int fichasPorJugador = configuracionJuegoDTO.getFichasPorJugador();
        controladorFichas.repartirFichasAJugadores(jugadores, fichasPorJugador);
    }
    
    private void repartirTurnos() {
        List<JugadorDominoEntity> jugadores = controladorJugadores.obtenerJugadores();
        controladorTurnos.asignarTurnosAJugadores(jugadores);
    }
    
    private void mostrarFichasAJugadores() {
        List<JugadorDominoEntity> jugadores = controladorJugadores.obtenerJugadores();
        for(JugadorDominoEntity jugador : jugadores) {
            // Obtiene las fichas del jugador
            List<FichaDominoDTO> listaFichas = adapterFichaDomino.adaptListToDTO(jugador.getListaFichasJugador());
            // Obtiene el idCliente del jugaador
            String idCliente = jugador.getIdCliente();
            // Adapta el jugador a DTO
            JugadorDominoDTO jugadorDTO = adapterJugadorDomino.adaptToDTO(jugador);
            // Obtiene cantidad de fichas del jugador
            int cantidadFichas = listaFichas.size();
            
            // Muestra al jugador sus fichas
            generadorRespuestas.enviarRespuestaMostrarFichasActualizadasDeJugador(idCliente, listaFichas);
            // Muestra su cantidad de fichas al resto de jugadores
            generadorRespuestas.enviarRespuestaActualizarCantidadFichas(jugadorDTO, cantidadFichas);
        }
    }
    
    private void iniciarPartida() {
        otorgarTurnoASiguienteJugador();
    }
    
    private void otorgarTurnoASiguienteJugador() {
        // Otorga el turno a un jugador
        JugadorDominoEntity jugadorConTurno = controladorTurnos.obtenerSiguienteTurno();
        // Adapta el jugador a DTO
        JugadorDominoDTO jugadorConTurnoDTO = adapterJugadorDomino.adaptToDTO(jugadorConTurno);
        // Obtiene idCliente del jugador
        String idCliente = jugadorConTurno.getIdCliente();
        // Obtiene las fichas del jugador con compatibilidad del tablero asignada
        List<FichaDominoDTO> fichasDTO = controladorTablero.asignarCompatibilidadAFichas(jugadorConTurno.getListaFichasJugador());
        
        
        // Envia a los jugadores de quien es el turno actual
        generadorRespuestas.enviarRespuestaOtorgarTurno(jugadorConTurnoDTO);
        // Envia al jugador con el turno las fichas con compatibilidad del tablero asignada
        generadorRespuestas.enviarRespuestaMostrarFichasActualizadasDeJugador(idCliente, fichasDTO);
        
    }
}
