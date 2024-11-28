/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.salaesperalogica;

import dominio.UsuarioEntity;
import domino.fachada.IFachadaClienteProxy;
import domino.solicitudes.SolicitudCrearSala;
import domino.solicitudes.SolicitudIniciarPartida;
import domino.solicitudes.SolicitudUnirseSala;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.UsuarioDTO;
import listeners.IContenedorListener;
import listeners.SalaEspera.IPresentacionSalaEsperaListener;
import mapeodto.MapeadorDTO;
import partidadomino.fachada.FachadaPartidaDominoProvisional;
import partidadomino.fachada.IFachadaPartidaDominoProvisional;
import setup.Setup;

/**
 *
 * @author castr
 */
public class SalaEsperaLogica implements ISalaEsperaLogica, IPresentacionSalaEsperaListener {

    private IContenedorListener contenedorListener;
    private Setup setup;
    private IFachadaPartidaDominoProvisional fachadaPartidaDominoProvisional;
    private IFachadaClienteProxy fachadaClienteProxy;

    public SalaEsperaLogica(Setup setup) {
        this.setup = setup;
        this.fachadaClienteProxy = setup.getFachadaClienteProxy();
        this.fachadaPartidaDominoProvisional = new FachadaPartidaDominoProvisional();
    }

    @Override
    public IContenedorListener iniciar() {
        mostrarPresentacionSalaEspera();
        crearSala();
        //unirseSala();
        return contenedorListener;
    }
    
    private void mostrarPresentacionSalaEspera() {
        this.contenedorListener = fachadaPartidaDominoProvisional.iniciar(this);
    }
    
    private void crearSala() {
        UsuarioEntity usuarioEntity = setup.getUsuarioLocal();
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);
        ConfiguracionJuegoDTO configuracionJuegoDTO = new ConfiguracionJuegoDTO();
        configuracionJuegoDTO.setFichasPorJugador(20);
        configuracionJuegoDTO.setLimiteJugadores(4);
        configuracionJuegoDTO.setNombreSala("Pirulin");
        SolicitudCrearSala solicitud = new SolicitudCrearSala(configuracionJuegoDTO, usuarioDTO);
        fachadaClienteProxy.enviarSolicitud(solicitud);
    }
    
    private void unirseSala() {
        UsuarioEntity usuarioEntity = setup.getUsuarioLocal();
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);
        String idSala = "SAL1"; // Aqui el id de la sala
        SolicitudUnirseSala solicitud = new SolicitudUnirseSala(usuarioDTO, idSala);
        fachadaClienteProxy.enviarSolicitud(solicitud);
    }

    @Override
    public void onBtnIniciarPartidaPrecionado() {
        UsuarioEntity usuarioEntity = setup.getUsuarioLocal();
        UsuarioDTO usuarioDTO = MapeadorDTO.UsuarioEntityADTO(usuarioEntity);
        SolicitudIniciarPartida solicitud = new SolicitudIniciarPartida(usuarioDTO);
        fachadaClienteProxy.enviarSolicitud(solicitud);
    }
    
    

}
