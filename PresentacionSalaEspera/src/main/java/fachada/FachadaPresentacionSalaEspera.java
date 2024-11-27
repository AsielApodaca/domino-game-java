/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import dominodto.UsuarioDTO;
import listeners.IPresentacionSalaEsperaListener;
import mvc.SalaEsperaController;
import mvc.SalaEsperaModel;
import mvc.SalaEsperaView;
import notificador.PresentacionNotificadorManager;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FachadaPresentacionSalaEspera implements IFachadaPresentacionSalaEspera {

    private SalaEsperaController salaEsperaController;
    private SalaEsperaModel salaEsperaModel;
    private SalaEsperaView salaEsperaView;
    private PresentacionNotificadorManager presentacionNotificadorManager;

    @Override
    public void iniciarPantalla(Boolean esAnfitrion) {
        this.salaEsperaModel = new SalaEsperaModel();
        this.salaEsperaView = new SalaEsperaView(salaEsperaModel);
        this.salaEsperaController = new SalaEsperaController(salaEsperaModel, salaEsperaView);
        this.presentacionNotificadorManager = new PresentacionNotificadorManager();
        salaEsperaController.declararPantallaDeAnfitriones(esAnfitrion);
    }

    @Override
    public void agregarUsuario(UsuarioDTO usuarioDTO) {
        salaEsperaController.agregarUsuario(usuarioDTO);
    }

    @Override
    public void removerUsuario(UsuarioDTO usuarioDTO) {
        salaEsperaController.removerUsuario(usuarioDTO);
    }

    @Override
    public void subscribirPresentacionListener(IPresentacionSalaEsperaListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
