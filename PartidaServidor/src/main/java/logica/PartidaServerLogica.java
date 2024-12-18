/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import adapterentidades.AdapterCasilla;
import adapterentidades.AdapterFichaDomino;
import adapterentidades.AdapterJugadorDomino;
import adapterentidades.IAdapterCasilla;
import adapterentidades.IAdapterFichaDomino;
import adapterentidades.IAdapterJugadorDomino;
import controladores.controladorfichas.ControladorFichas;
import controladores.controladorfichas.IControladorFichas;
import controladores.controladorjugadores.ControladorJugadores;
import controladores.controladorjugadores.IControladorJugadores;
import controladores.controladortablero.ControladorTablero;
import controladores.controladortablero.IControladorTablero;
import controladores.controladorturnos.ControladorTurnos;
import controladores.controladorturnos.IControladorTurnos;
import dominio.CasillaEntity;
import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import dominodto.CasillaDTO;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import dominodto.SalaDTO;
import dominodto.UsuarioDTO;
import generadorrespuestas.IGeneradorRespuestas;
import java.util.ArrayList;
import java.util.List;
import mapeodto.MapeadorDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaServerLogica implements IPartidaServerLogica {

    private IGeneradorRespuestas generadorRespuestas;
    private IControladorFichas controladorFichas;
    private IControladorJugadores controladorJugadores;
    private IControladorTablero controladorTablero;
    private IControladorTurnos controladorTurnos;
    private IAdapterFichaDomino adapterFichaDomino;
    private IAdapterJugadorDomino adapterJugadorDomino;
    private IAdapterCasilla adapterCasilla;
    private ConfiguracionJuegoDTO configuracionJuegoDTO;
    private boolean partidaIniciada ;
   
    public PartidaServerLogica() {
        partidaIniciada = false ;
    }

    @Override
    public void procesarCrearSala(ConfiguracionJuegoDTO configuracionJuegoDTO, UsuarioDTO anfitrion) {
        this.configuracionJuegoDTO = configuracionJuegoDTO;
        iniciarControladores();
        iniciarAdapters();
        registrarUsuarioComoJugador(anfitrion);
        this.generadorRespuestas.enviarRespuestaAprobarCreacionSala(anfitrion.getIdCliente());
        if(configuracionJuegoDTO.getLimiteJugadores() != 1) {
            procesarMostrarSalaDisponible() ;
        }
    }

    @Override
    public void procesarUnirseSala(UsuarioDTO usuarioDTO) {
        if(controladorJugadores.obtenerJugadores().size() < configuracionJuegoDTO.getLimiteJugadores()) {
            registrarUsuarioComoJugador(usuarioDTO);
            procesarMostrarSalaDisponible() ;
        }
    }

    @Override
    public void procesarAbandonarSala(String idCliente) {
        eliminarJugadorPorIdCliente(idCliente);
        generadorRespuestas.enviarRespuestaRemoverUsuarioSalaEspera(new UsuarioDTO(idCliente));
        if(!controladorJugadores.obtenerJugadores().isEmpty() && !partidaIniciada) {
            procesarMostrarSalaDisponible() ;
        } else {
            generadorRespuestas.enviarRespuestaOcultarSalaDisponible();
        }
    }

    @Override
    public void procesarIniciarPartida() {
        mostrarPantallaPartidaAJugadores();
        repartirFichas();
        repartirTurnos();
        mostrarJugadores();
        mostrarFichasAJugadores();
        iniciarPartida();
        generadorRespuestas.enviarRespuestaOcultarSalaDisponible();
    }

    @Override
    public void procesarFichaSeleccionada(FichaDominoDTO ficha, UsuarioDTO usuario) {
        String idCliente = usuario.getIdCliente();
        if (ficha == null) {
            controladorFichas.desseleccionarFicha();
            generadorRespuestas.enviarRespuestaOcultarCasillasDisponibles(idCliente);
        } else {
            FichaDominoEntity fichaDominoEntity = adapterFichaDomino.adaptToEntity(ficha);
            controladorFichas.seleccionarFicha(fichaDominoEntity);
            List<CasillaEntity> casillasEntity = controladorTablero.obtenerCasillasCompatibles(fichaDominoEntity);
            List<CasillaDTO> casillasDTO = new ArrayList<>();
            for (CasillaEntity casillaEntity : casillasEntity) {
                CasillaDTO casillaDTO = adapterCasilla.adaptToDTO(casillaEntity);
                casillasDTO.add(casillaDTO);
            }
            generadorRespuestas.enviarRespuestaMostrarCasillasDisponibles(idCliente, casillasDTO);
        }

    }

    @Override
    public void procesarCasillaSeleccionada(CasillaDTO casilla, UsuarioDTO usuario) {
        // Obtiene el idCliente del usuario y busca al jugador correspondiente
        String idCliente = usuario.getIdCliente();
        JugadorDominoEntity jugador = controladorJugadores.obtenerJugadorPorIdCliente(idCliente);
        // Obtiene la ficha seleccionada y se la quita al jugador
        FichaDominoEntity fichaSeleccionada = controladorFichas.obtenerFichaSeleccionada();
        controladorFichas.quitarFichaAJugador(jugador, fichaSeleccionada);
        // Coloca la ficha en el tablero y obtiene la casilla con la ficha
        CasillaEntity casillaEntity = controladorTablero.colocarFichaEnCasilla(casilla, fichaSeleccionada);
        CasillaDTO casillaDTO = adapterCasilla.adaptToDTO(casillaEntity);
        // Deselecciona la ficha
        controladorFichas.desseleccionarFicha();
        // Obtiene las fichas del jugador y adapta las fichas a fichasDTO con
        // estado asignado de incopatibilidad para que el jugador ya no pueda interactuar con las fichas
        List<FichaDominoEntity> fichasJugador = jugador.getListaFichasJugador();
        List<FichaDominoDTO> fichasJugadorDTO = adapterFichaDomino.adaptListToDTO(fichasJugador);
        // Obtiene la cantidad de fichas del jugador
        int cantidadFichas = fichasJugador.size();
        // Adapta el jugador a DTO
        JugadorDominoDTO jugadorDTO = adapterJugadorDomino.adaptToDTO(jugador);
        // Envia al jugador que oculte la casilla libre
        generadorRespuestas.enviarRespuestaOcultarCasillasDisponibles(idCliente);
        // Envia al jugador la lista actualizada de fichas
        generadorRespuestas.enviarRespuestaMostrarFichasActualizadasDeJugador(idCliente, fichasJugadorDTO);
        // Envia al resto de jugadores la cantidad actualizada de fichas del jugador
        generadorRespuestas.enviarRespuestaActualizarCantidadFichas(jugadorDTO, cantidadFichas);
        // Envia a los jugadores la ficha que se mostrará sobre el tablero
        generadorRespuestas.enviarRespuestaColocarFichaTablero(casillaDTO);
        // Compara si la cantidad de fichas del jugador es 0 para anunciar el ganador
        if(cantidadFichas == 0) {
            anunciarGanadorPartida(jugadorDTO);
            return ;
        }
        
        // Otorga el turno al siguiente jugador
        otorgarTurnoASiguienteJugador();
    }

    @Override
    public void setGeneradorRespuestas(IGeneradorRespuestas generadorRespuestas) {
        this.generadorRespuestas = generadorRespuestas;
    }

    private void iniciarControladores() {
        controladorFichas = new ControladorFichas();
        controladorFichas.crearPozo();
        controladorJugadores = new ControladorJugadores();
        controladorTablero = new ControladorTablero();
        controladorTablero.crearTablero();
        controladorTurnos = new ControladorTurnos();
    }

    private void iniciarAdapters() {
        adapterFichaDomino = new AdapterFichaDomino(controladorFichas.obtenerTodasLasFichasDelJuego());
        controladorTablero.setAdapterFichaDomino(adapterFichaDomino);
        adapterJugadorDomino = new AdapterJugadorDomino();
        adapterCasilla = new AdapterCasilla(adapterFichaDomino);
    }

    private void registrarUsuarioComoJugador(UsuarioDTO usuarioDTO) {
        JugadorDominoEntity jugador = adapterJugadorDomino.adaptToEntity(usuarioDTO);
        controladorJugadores.agregarJugador(jugador);
    }

    private void eliminarJugadorPorIdCliente(String idCliente) {
        controladorJugadores.eliminarJugadorPorIdCliente(idCliente);
    }

    private void mostrarPantallaPartidaAJugadores() {
        List<JugadorDominoEntity> listaJugadoresEntity = controladorJugadores.obtenerJugadores();
        List<JugadorDominoDTO> listaJugadoresDTO = new ArrayList<>();
        for (JugadorDominoEntity jugadorEntity : listaJugadoresEntity) {
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
    
    private void mostrarJugadores() {
        // Envia a cada jugador lo que para el son jugadores de otros dispositivos
        // en el orden de los turnos
        List<JugadorDominoEntity> jugadores = controladorJugadores.obtenerJugadores();
        for (int i = 0; i < jugadores.size(); i++) {
            List<JugadorDominoDTO> jugadoresExternos = new ArrayList<>();

            // Añadir jugadores después del jugador actual
            for (int j = i + 1; j < jugadores.size(); j++) {
                JugadorDominoDTO jugadorDominoDTO = adapterJugadorDomino.adaptToDTO(jugadores.get(j));
                jugadoresExternos.add(jugadorDominoDTO);
            }

            // Añadir jugadores antes del jugador actual
            for (int j = 0; j < i; j++) {
                JugadorDominoDTO jugadorDominoDTO = adapterJugadorDomino.adaptToDTO(jugadores.get(j));
                jugadoresExternos.add(jugadorDominoDTO);
            }

            JugadorDominoEntity jugadorLocal = jugadores.get(i);
            String idCliente = jugadorLocal.getIdCliente();
            
            generadorRespuestas.enviarRespuestaMostrarJugadoresPartida(jugadoresExternos, idCliente);
        }

    }

    private void mostrarFichasAJugadores() {
        List<JugadorDominoEntity> jugadores = controladorJugadores.obtenerJugadores();
        for (JugadorDominoEntity jugador : jugadores) {
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

        // Bloquea el pozo inicialmente para el nuevo turno
        generadorRespuestas.enviarRespuestaBloquearPozo(idCliente);

        // Verifica si tiene fichas compatibles
        boolean tieneFichasCompatibles = fichasDTO.stream()
                .anyMatch(FichaDominoDTO::isCompatible);

        // Si no tiene fichas compatibles y hay fichas en el pozo, desbloquea el pozo
        if (!tieneFichasCompatibles) {
            if(controladorFichas.quedanFichasEnPozo()) {
                generadorRespuestas.enviarRespuestaDesbloquearPozo(idCliente);
            } else {
                int turnosOmitidos = controladorTurnos.turnoOmitido();
                int cantidadJugadores = controladorJugadores.obtenerJugadores().size();
                if(turnosOmitidos == cantidadJugadores) {
                    empatarPartida();
                } else {
                    otorgarTurnoASiguienteJugador();
                    return;
                }
            }
        }

        // Envia a los jugadores de quien es el turno actual
        generadorRespuestas.enviarRespuestaOtorgarTurno(jugadorConTurnoDTO);
        // Envia al jugador con el turno las fichas con compatibilidad del tablero asignada
        generadorRespuestas.enviarRespuestaMostrarFichasActualizadasDeJugador(idCliente, fichasDTO);

    }
    
    private void empatarPartida() {
        generadorRespuestas.enviarRespuestaMostrarResultadoPartida(null);
        generadorRespuestas.enviarRespuestaCerrarSaala();
        reiniciarServidor() ;
    }
    
    private void anunciarGanadorPartida(JugadorDominoDTO ganador) {
        generadorRespuestas.enviarRespuestaMostrarResultadoPartida(ganador);
        generadorRespuestas.enviarRespuestaCerrarSaala();
        reiniciarServidor() ;
    }

    private void reiniciarServidor() {
        generadorRespuestas = null ;
        controladorFichas = null ;
        controladorJugadores = null ;
        controladorTablero = null ;
        controladorTurnos = null ;
        adapterFichaDomino = null ;
        adapterJugadorDomino = null ;
        adapterJugadorDomino = null ;
        adapterCasilla = null ;
        configuracionJuegoDTO = null ;
        partidaIniciada = false ;
    }
    
    @Override
    public void procesarSacarFichaPozo(String idCliente) {
        // Obtiene el jugador actual con turno
        JugadorDominoEntity jugadorConTurno = controladorJugadores.obtenerJugadorPorIdCliente(idCliente);

        // Si no hay fichas en el pozo, lo bloquea y termina
        if (!controladorFichas.quedanFichasEnPozo()) {
            generadorRespuestas.enviarRespuestaBloquearPozo(idCliente);
            return;
        }

        // Agrega una ficha al jugador desde el pozo
        controladorFichas.agregarFichaAJugador(jugadorConTurno);

        // Obtiene las fichas actualizadas del jugador
        List<FichaDominoEntity> fichasJugador = jugadorConTurno.getListaFichasJugador();
        List<FichaDominoDTO> fichasJugadorDTO = controladorTablero.asignarCompatibilidadAFichas(fichasJugador);

        // Verifica si tiene fichas compatibles
        boolean tieneFichasCompatibles = fichasJugadorDTO.stream()
                .anyMatch(FichaDominoDTO::isCompatible);

        // Maneja el estado del pozo basado en compatibilidad
        if (tieneFichasCompatibles) {
            generadorRespuestas.enviarRespuestaBloquearPozo(idCliente);
        } else {
            generadorRespuestas.enviarRespuestaDesbloquearPozo(idCliente);
        }

        // Actualiza las fichas del jugador
        generadorRespuestas.enviarRespuestaMostrarFichasActualizadasDeJugador(idCliente, fichasJugadorDTO);
    }

    @Override
    public void procesarMostrarSalaDisponible() {
        if(controladorJugadores.obtenerJugadores().size() < configuracionJuegoDTO.getLimiteJugadores()) {
            SalaDTO sala = new SalaDTO() ;
            List<UsuarioDTO> usuariosDTO = new ArrayList() ;
            controladorJugadores.obtenerJugadores().forEach(jugador -> {
                usuariosDTO.add(adapterJugadorDomino.adaptToUsuarioDTO(jugador)) ;
            });
            sala.setUsuarios(usuariosDTO);
            sala.setSize(configuracionJuegoDTO.getLimiteJugadores());
            generadorRespuestas.enviarRespuestaMostrarSalaDisponible(configuracionJuegoDTO, sala);
        }
    }
}
