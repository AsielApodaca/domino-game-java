/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.partidadominologica;

import domino.fachada.IFachadaClienteProxy;
import dominio.UsuarioEntity;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.solicitudes.SolicitudFichaSeleccionada;
import domino.solicitudes.SolicitudSacarFichaPozo;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import dominodto.UsuarioDTO;
import fachada.FachadaPartidaDomino;
import fachada.IFachadaPartidaDomino;
import java.util.List;
import java.util.logging.Logger;
import listeners.IContenedorListener;
import listeners.IPresentacionPartidaDominoListener;
import logica.padrelogica.Logica;
import mapeodto.MapeadorDTO;
import setup.Setup;

/**
 *
 * @author asielapodaca
 */
public class PartidaDominoLogica extends Logica implements IPartidaDominoLogica, IPresentacionPartidaDominoListener {

    private static final Logger LOG = Logger.getLogger(PartidaDominoLogica.class.getName());
    private IFachadaPartidaDomino fachadaPartidaDomino;
    private IFachadaClienteProxy fachadaClienteProxy;

    public PartidaDominoLogica(Setup setup) {
        super(setup);
        this.fachadaClienteProxy = setup.getFachadaClienteProxy();

    }

    @Override
    public IContenedorListener iniciar() {
        this.fachadaPartidaDomino = new FachadaPartidaDomino();
        this.operando = true;
        mostrarPresentacionPartida();
        escucharEventosPartidaDomino();

        return mvcController;
    }

    public void cerrar() {
        fachadaPartidaDomino = null;
        mvcController = null;
        this.operando = false;
    }
    
    @Override
    public boolean estaOperando() {
        return this.operando;
    }
    
    private void mostrarPresentacionPartida() {
        this.mvcController = fachadaPartidaDomino.iniciarPantalla();
    }

    private void escucharEventosPartidaDomino() {
        fachadaPartidaDomino.suscribirPresentacionListener((IPresentacionPartidaDominoListener) this);
    }

    /**
     * Maneja el evento cuando una ficha de dominó es seleccionada por el
     * usuario. Este método recibe un evento de selección de ficha, mapea la
     * información relevante (ficha y usuario) y crea una solicitud que luego es
     * enviada a través de la fachada cliente.
     *
     * @param fichaSeleccionada la ficha seleccionada.
     */
    @Override
    public void onFichaSeleccionada(FichaDominoDTO fichaSeleccionada) {
        UsuarioEntity usuarioEntity = setup.getGestorUsuario().getUsuario();
        // Mapea el usuario relacionado al DTO correspondiente
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);

        // Crea una nueva solicitud de ficha seleccionada
        EventoSolicitud solicitudFichaSeleccionada = new SolicitudFichaSeleccionada(fichaSeleccionada, usuarioDTO);

        // Envía la solicitud a través de la fachada de DominoClientProxy
        fachadaClienteProxy.enviarSolicitud(solicitudFichaSeleccionada);
    }

    /**
     * Maneja el evento cuando una casilla de del tablero es seleccionada por el
     * usuario. Este método recibe un evento de selección de casilla, mapea la
     * información relevante (casilla y usuario) y crea una solicitud que luego
     * es enviada a través de la fachada cliente.
     *
     * @param casillaSeleccionada la casilla seleccionada.
     */
    @Override
    public void onCasillaSeleccionada(CasillaDTO casillaSeleccionada) {
        UsuarioEntity usuarioEntity = setup.getGestorUsuario().getUsuario();
        // Mapea el usuario relacionado al DTO correspondiente
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);

        // Crea una nueva solicitud de casilla seleccionada
        EventoSolicitud solicitudCasillaSeleccionada = new SolicitudCasillaSeleccionada(casillaSeleccionada, usuarioDTO);

        // Envía la solicitud a través de la fachada cliente
        fachadaClienteProxy.enviarSolicitud(solicitudCasillaSeleccionada);
    }

    @Override
    public void actualizarCantidadFichasDeJugador(JugadorDominoDTO jugador, int cantidad) {
        fachadaPartidaDomino.actualizarCantidadFichasDeJugador(jugador, cantidad);
    }

    @Override
    public void mostrarFichaTablero(CasillaDTO casilla) {
        fachadaPartidaDomino.colocarFichaTablero(casilla);
    }

    @Override
    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichas) {
        fachadaPartidaDomino.mostrarFichasJugadorLocal(fichas);
    }

    @Override
    public void agregarFichaJugadorLocal(FichaDominoDTO ficha) {
        fachadaPartidaDomino.agregarFichaJugadorLocal(ficha);
    }

    @Override
    public void otorgarTurnoAJugador(JugadorDominoDTO jugador) {
        fachadaPartidaDomino.otorgarTurnoAJugador(jugador);
    }

    @Override
    public void mostrarCasillasDisponibles(List<CasillaDTO> casillasDisponibles) {
        fachadaPartidaDomino.mostrarCasillasParaColocarFicha(casillasDisponibles);
    }

    @Override
    public void ocultarCasillasDisponibles() {
        fachadaPartidaDomino.ocultarCasillasParaColocarFicha();
    }

    @Override
    public void onPozoSeleccionado() {
        UsuarioEntity usuarioEntity = setup.getGestorUsuario().getUsuario();
        // Mapea el usuario relacionado al DTO correspondiente
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);

        // Crear una nueva solicitud de pozo seleccionado
        EventoSolicitud solicitudPozoSeleccionado = new SolicitudSacarFichaPozo(usuarioDTO);

        // Envia la solicitud a través de la fachada cliente
        fachadaClienteProxy.enviarSolicitud(solicitudPozoSeleccionado);

    }

    @Override
    public void mostrarPozoDisponible() {
        fachadaPartidaDomino.mostrarPozo();
    }

    @Override
    public void ocultarPozoDisponible() {
        fachadaPartidaDomino.ocultarPozo();
    }

    @Override
    public void mostrarJugadoresDePartida(List<JugadorDominoDTO> jugadoresEnOrden) {
        fachadaPartidaDomino.mostrarJugadores(jugadoresEnOrden);
    }

    @Override
    public void removerJugadorDePartida(JugadorDominoDTO jugadorDominoDTO) {
        fachadaPartidaDomino.removerJugador(jugadorDominoDTO);
    }

    @Override
    public void actualizarCantidadFichasJugador(JugadorDominoDTO jugadorDominoDTO, int cantidadFichas) {
        fachadaPartidaDomino.actualizarCantidadFichasDeJugador(jugadorDominoDTO, cantidadFichas);
    }

    @Override
    public void mostrarResultadoPartida() {
        fachadaPartidaDomino.mostrarResultadoPartida();
    }

}
