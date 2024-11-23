/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.mvc;

import javax.swing.JPanel;
import listeners.IContenedorListener;

/**
 *
 * @author castr
 */
public class PartidaDominoController implements IContenedorListener {

    private PartidaDominoView view;

    public PartidaDominoController(PartidaDominoView view) {
        this.view = view;
    }

    @Override
    public void onEscalaChange(float escala) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JPanel obtenerView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PartidaDominoView getView() {
        return this.view;
    }
}
