/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.partidadominologica;

import domino.fachada.IFachadaClienteProxy;
import dominio.UsuarioEntity;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.solicitudes.SolicitudCrearSala;
import domino.solicitudes.SolicitudFichaSeleccionada;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import dominodto.UsuarioDTO;
import fachada.FachadaPartidaDomino;
import fachada.IFachadaPartidaDomino;
import java.util.logging.Logger;
import listeners.IContenedorListener;
import listeners.IPresentacionPartidaDominoListener;
import mapeodto.MapeadorDTO;
import notificador.eventos.CasillaSeleccionadaEvento;
import notificador.eventos.FichaSeleccionadaEvento;
import setup.Setup;

/**
 *
 * @author asielapodaca
 */
public class PartidaDominoLogica implements IPartidaDominoLogica, IPresentacionPartidaDominoListener {

    private static final Logger LOG = Logger.getLogger(PartidaDominoLogica.class.getName());
    private Setup setup;
    private IFachadaPartidaDomino fachadaPartidaDomino;
    private IFachadaClienteProxy fachadaClienteProxy;
    private IContenedorListener contenedorListener;
    private UsuarioEntity usuarioEntity = new UsuarioEntity("Oliver"); // Usuario local

    public PartidaDominoLogica(Setup setup) {
        this.setup = setup;
        this.fachadaPartidaDomino = new FachadaPartidaDomino();
        this.fachadaClienteProxy = setup.getFachadaClienteProxy();
        fachadaClienteProxy.enviarSolicitud(new SolicitudCrearSala(MapeadorDTO.UsuarioEntityADTO(usuarioEntity)));
    }

    @Override
    public IContenedorListener iniciar() {
        mostrarPresentacionPartida();
        escucharEventosPartidaDomino();

        return contenedorListener;
    }

    private void mostrarPresentacionPartida() {
        this.contenedorListener = fachadaPartidaDomino.iniciarPantalla();
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
     * @param evento El evento de seylección de ficha, que contiene la
     * información de la ficha seleccionada.
     */
    @Override
    public void onFichaSeleccionada(FichaSeleccionadaEvento evento) {
        // Obtiene el DTO de la ficha seleccionada desde el evento
        FichaDominoDTO fichaDominoDTO = evento.getFichaDominoDTO();

        // Mapea el usuario relacionado al DTO correspondiente
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);

        // Crea una nueva solicitud de ficha seleccionada
        EventoSolicitud solicitudFichaSeleccionada = new SolicitudFichaSeleccionada(fichaDominoDTO, usuarioDTO);

        // Envía la solicitud a través de la fachada de DominoClientProxy
        fachadaClienteProxy.enviarSolicitud(solicitudFichaSeleccionada);
    }

    /**
     * Maneja el evento cuando una casilla de del tablero es seleccionada por el
     * usuario. Este método recibe un evento de selección de casilla, mapea la
     * información relevante (casilla y usuario) y crea una solicitud que luego
     * es enviada a través de la fachada cliente.
     *
     * @param evento El evento de selección de casilla, que contiene la
     * información de la casilla seleccionada.
     */
    @Override
    public void onCasillaSeleccionada(CasillaSeleccionadaEvento evento) {
        // Obtiene el DTO de la casilla seleccionada desde el evento
        CasillaDTO casillaDTO = evento.getCasillaDTO();

        // Mapea el usuario relacionado al DTO correspondiente
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);

        // Crea una nueva solicitud de casilla seleccionada
        EventoSolicitud solicitudCasillaSeleccionada = new SolicitudCasillaSeleccionada(casillaDTO, usuarioDTO);

        // Envía la solicitud a través de la fachada cliente
        fachadaClienteProxy.enviarSolicitud(solicitudCasillaSeleccionada);
    }

    @Override
    public void actualizarCantidadFichasDeJugador(JugadorDominoDTO jugador, int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarFichaTablero(CasillaDTO casilla) {
        fachadaPartidaDomino.colocarFichaTablero(casilla);
    }

    @Override
    public void removerFichaJugadorLocal(FichaDominoDTO ficha) {
        fachadaPartidaDomino.quitarFichaJugadorLocal(ficha);
    }

    @Override
    public void agregarFichaJugadorLocal(FichaDominoDTO ficha) {
        fachadaPartidaDomino.agregarFichaJugadorLocal(ficha);
    }

    @Override
    public void cambiarTurno(JugadorDominoDTO jugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
