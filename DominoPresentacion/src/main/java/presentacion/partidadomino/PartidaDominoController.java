/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino;

import contenedorpantallas.IContenidoController;
import javax.swing.JPanel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaDominoController implements IContenidoController{

    private PartidaDominoModel model;
    private PartidaDominoView view;

    public PartidaDominoController(PartidaDominoModel model, PartidaDominoView view) {
        this.model = model;
        this.view = view;
        
        view.repintarComponentes();
//        view.actualizarListaFichasJugadorLocal();
    }
    
    public void showView() {
        view.setVisible(true);
    }

    @Override
    public void actualizarEscala(float escala) {
        model.setEscala(escala);
        view.repintarComponentes();
    }

    @Override
    public JPanel obtenerView() {
        return this.view;
    }
    
}
