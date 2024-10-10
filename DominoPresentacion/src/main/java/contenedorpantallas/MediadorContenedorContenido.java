/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedorpantallas;

import javax.swing.JPanel;

/**
 *
 * @author asielapodaca
 */
public class MediadorContenedorContenido {
    private static MediadorContenedorContenido instance;
    private FormContenedorController contenedorController;
    private IContenidoController contenidoController;
    private JPanel contenedorPanel;

    private MediadorContenedorContenido() {
    }

    public static MediadorContenedorContenido getInstance() {
        if (instance == null) {
            instance = new MediadorContenedorContenido();
        }
        return instance;
    }

    public void setContenedorController(FormContenedorController contenedorController) {
        this.contenedorController = contenedorController;
        setContenedorPanel();
    }
    
    private void setContenedorPanel() {
        this.contenedorPanel = contenedorController.getContenedorPanel();
    }
    
    public void setContenidoController(IContenidoController contenidoController) {
        this.contenidoController = contenidoController;
        notificarEscalaAContenido();
    }
    
    public void notificarEscalaAContenido() {
        if(contenedorController != null && contenidoController != null) {
            this.contenidoController.actualizarEscala(contenedorController.getEscala());
        }
    }
    
    public void mostrarPantalla() {
        if (contenedorPanel != null) {
            contenedorPanel.removeAll();
            contenedorPanel.add(contenidoController.obtenerView());
            contenedorPanel.revalidate();
            contenedorPanel.repaint();
        }
    }
    
}
