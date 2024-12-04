/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.salaesperalogica;

import dominio.UsuarioEntity;
import domino.fachada.IFachadaClienteProxy;
import domino.solicitudes.SolicitudAbandonarSala;
import domino.solicitudes.SolicitudCrearSala;
import domino.solicitudes.SolicitudIniciarPartida;
import domino.solicitudes.SolicitudUnirseSala;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.UsuarioDTO;
import fachada.FachadaPresentacionSalaEspera;
import fachada.IFachadaPresentacionSalaEspera;
import listeners.IContenedorListener;
import listeners.IPresentacionSalaEsperaListener;
import logica.padrelogica.Logica;
import mapeodto.MapeadorDTO;
import setup.Setup;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SalaEsperaLogica extends Logica implements ISalaEsperaLogica, IPresentacionSalaEsperaListener {

    private IFachadaPresentacionSalaEspera fachadaPresentacionSalaEspera;
    private IFachadaClienteProxy fachadaClienteProxy;

    public SalaEsperaLogica(Setup setup) {
        super(setup);
        this.fachadaClienteProxy = setup.getFachadaClienteProxy();
    }

    @Override
    public IContenedorListener iniciar() {
        this.operando = true;
        this.fachadaPresentacionSalaEspera = new FachadaPresentacionSalaEspera();
        mostrarPresentacionSalaEspera();
        return mvcController;
    }
    
    @Override
    public void cerrar() {
        this.operando = false;
        mvcController = null;
        fachadaPresentacionSalaEspera = null;
    }
    
    @Override
    public boolean estaOperando() {
        return this.operando;
    }
    
    private void mostrarPresentacionSalaEspera() {
        this.mvcController = fachadaPresentacionSalaEspera.iniciarPantalla();
        fachadaPresentacionSalaEspera.subscribirPresentacionListener(this);
    }
    
    // Inicio de métodos temporales para pruebas
    @Override
    public void crearSala() {
        UsuarioEntity usuarioEntity = setup.getGestorUsuario().getUsuario();
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);
        ConfiguracionJuegoDTO configuracionJuegoDTO = new ConfiguracionJuegoDTO();
        configuracionJuegoDTO.setFichasPorJugador(7);
        configuracionJuegoDTO.setLimiteJugadores(4);
        configuracionJuegoDTO.setNombreSala("Pirulin");
        SolicitudCrearSala solicitud = new SolicitudCrearSala(configuracionJuegoDTO, usuarioDTO);
        fachadaClienteProxy.enviarSolicitud(solicitud);
    }
    
    @Override
    public void unirseSala() {
        UsuarioEntity usuarioEntity = setup.getGestorUsuario().getUsuario();
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);
        String idSala = "SAL1"; // Aqui el id de la sala
        SolicitudUnirseSala solicitud = new SolicitudUnirseSala(usuarioDTO, idSala);
        fachadaClienteProxy.enviarSolicitud(solicitud);
    }
    // Fin de métodos temporales para pruebas
    
    @Override
    public void onBtnIniciarPartidaPresionado() {
        UsuarioEntity usuarioEntity = setup.getGestorUsuario().getUsuario();
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);
        SolicitudIniciarPartida solicitud = new SolicitudIniciarPartida(usuarioDTO);
        fachadaClienteProxy.enviarSolicitud(solicitud);
    }

    @Override
    public void onBtnSalirPresionado() {
        UsuarioEntity usuarioEntity = setup.getGestorUsuario().getUsuario();
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);
        SolicitudAbandonarSala solicitud = new SolicitudAbandonarSala(usuarioDTO);
        fachadaClienteProxy.enviarSolicitud(solicitud);
    }

    @Override
    public void otorgarPermisosDeAnfitrion() {
        fachadaPresentacionSalaEspera.otorgarPermisosDeAnfitiron();
    }

    @Override
    public void agregarUsuarioASala(UsuarioDTO usuarioDTO) {
        fachadaPresentacionSalaEspera.agregarUsuario(usuarioDTO);
    }

    @Override
    public void removerUsuarioDeSala(UsuarioDTO usuarioDTO) {
        fachadaPresentacionSalaEspera.removerUsuario(usuarioDTO);
    }

   
    

}
