/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import listeners.IContenedorListener;
import mediadorsingleton.MediadorSingletonContenedorContenido;
import mvc.FormContenedorController;
import mvc.FormContenedorModel;
import mvc.FormContenedorView;

/**
 *
 * @author asielapodaca
 */
public class FachadaContenedorPantallas implements IFachadaContenedorPantallas{
    
    private FormContenedorModel model;
    private FormContenedorView view;
    private FormContenedorController controller;
    private final MediadorSingletonContenedorContenido mdSgContenedorContenido = MediadorSingletonContenedorContenido.getInstance();

    @Override
    public void iniciarContenedorDePantallas() {
        instanciarMVC();
        iniciarMediadorContenedorContenido();
        this.controller.showView();
    }
    
   
    
    private void instanciarMVC() {
        this.model = new FormContenedorModel();
        this.view = new FormContenedorView(model);
        this.controller = new FormContenedorController(model, view);
    }
    
    private void iniciarMediadorContenedorContenido() {
        this.mdSgContenedorContenido.setContenedorController(controller);
    }

    @Override
    public void establecerPantalla(IContenedorListener pantallaController) {
        this.mdSgContenedorContenido.setContenedorListener(pantallaController);
        this.mdSgContenedorContenido.mostrarPantalla();
    }
    
}
