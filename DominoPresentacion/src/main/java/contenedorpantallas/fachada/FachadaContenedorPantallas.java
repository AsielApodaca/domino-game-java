/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedorpantallas.fachada;

import contenedorpantallas.SingletonContenedorContenido;
import contenedorpantallas.FormContenedorModel;
import contenedorpantallas.FormContenedorView;
import contenedorpantallas.FormContenedorController;

/**
 *
 * @author asielapodaca
 */
public class FachadaContenedorPantallas implements IFachadaContenedorPantallas{
    
    private FormContenedorModel model;
    private FormContenedorView view;
    private FormContenedorController controller;
    private final SingletonContenedorContenido medContenedorContenido = SingletonContenedorContenido.getInstance();

    public FachadaContenedorPantallas() {
    }
    
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
        this.medContenedorContenido.setContenedorController(controller);
    }
    
}
