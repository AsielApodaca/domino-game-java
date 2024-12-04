/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import listeners.IContenedorListener;
import listeners.IPresentacionCrearUsuarioListener;
import mvc.CrearUsuarioController;
import mvc.CrearUsuarioModel;
import mvc.CrearUsuarioView;
import notificador.PresentacionCrearUsuarioNotificador;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FachadaCrearUsuario implements IFachadaCrearUsuario {

    private PresentacionCrearUsuarioNotificador presentacionNotificadorManager;
    private CrearUsuarioModel crearUsuarioModel;
    private CrearUsuarioController crearUsuarioController;
    private CrearUsuarioView crearUsuarioView;

    @Override
    public IContenedorListener iniciarPantalla() {
        this.presentacionNotificadorManager = new PresentacionCrearUsuarioNotificador();
        this.crearUsuarioModel = new CrearUsuarioModel();
        this.crearUsuarioView = new CrearUsuarioView(crearUsuarioModel);
        crearUsuarioModel.setPresentacionNotificadorManager(presentacionNotificadorManager);
        this.crearUsuarioController = new CrearUsuarioController(
                crearUsuarioModel,
                crearUsuarioView,
                presentacionNotificadorManager
        );
        crearUsuarioView.suscribirListener(crearUsuarioController);
        return crearUsuarioController;
    }

    @Override
    public void suscribirPresentacionListener(IPresentacionCrearUsuarioListener listener) {
        presentacionNotificadorManager.suscribirPresentacionListener(listener);
    }

}
