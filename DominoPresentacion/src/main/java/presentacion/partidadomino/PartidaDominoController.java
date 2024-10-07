/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino;

import contenedorView.EscalaObserver;
import contenedorView.FormContenedorModel;

/**
 *
 * @author asielapodaca
 */
public class PartidaDominoController implements EscalaObserver{

    private PartidaDominoModel model;
    private PartidaDominoView view;
    private FormContenedorModel proveedorDeEscala;

    public PartidaDominoController(PartidaDominoModel model, PartidaDominoView view, FormContenedorModel proveedorDeEscala) {
        this.proveedorDeEscala = proveedorDeEscala;
        this.proveedorDeEscala.addScaleObserver(this); // Controller ahora escucha cuando la escala cambia
        this.model = model;
        this.view = view;
        
        view.repintarComponentes();
        view.actualizarListaFichasJugadorLocal();
    }
    
    
    @Override
    public void onScaleChanged(float newScale) {
        model.setEscala(newScale);
        view.repintarComponentes();
    }
    
}
