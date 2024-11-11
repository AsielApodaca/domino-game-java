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
public class SingletonContenedorContenido {
    private static SingletonContenedorContenido instance;
    private FormContenedorController contenedorController;
    private IContenedorEscalaListener contenedorEscalaListener;
    private JPanel contenedorPanel;

    private SingletonContenedorContenido() {
    }

    /**
     * Obtiene instancia estática del Singleton entre Contenedor y Conenido
     * @return instancia estática de si mismo
     */
    public static SingletonContenedorContenido getInstance() {
        if (instance == null) {
            instance = new SingletonContenedorContenido();
        }
        return instance;
    }

    /**
     * Asigna al objeto el controlador del mvc del contenedor de pantallas
     * @param contenedorController FormContenedorController del mvc contenedorpantallas
     */
    public void setContenedorController(FormContenedorController contenedorController) {
        this.contenedorController = contenedorController;
        setContenedorPanel();
    }
    
    /**
     * Asigna al objeto el panel del contenedor
     * Este método es llamado por el método setContenedorController
     */
    private void setContenedorPanel() {
        this.contenedorPanel = contenedorController.getContenedorPanel();
    }
    
    /**
     * Suscribe un oyente de la escala del panel del contenedor.
     * La escala hace referencia a que tan grande es el panel a comparación
     * de su tamaño original.
     * 
     * El oyente que se suscribe es el panel que será el contenido del contenedor,
     * es decir, la pantalla que se mostrará.
     * @param contenedorEscalaListener Pantalla mostrada a suscribir como oyente de la escala.
     */
    public void setContenedorEscalaListener(IContenedorEscalaListener contenedorEscalaListener) {
        this.contenedorEscalaListener = contenedorEscalaListener;
        notificarEscalaAContenedorListener();
    }
    
    /**
     * Notifica la escala del panel contenedor al panel de la pantalla que
     * esté actualmente suscrita como oyente de la escala del contenedor.
     */
    public void notificarEscalaAContenedorListener() {
        if(contenedorController != null && contenedorEscalaListener != null) {
            this.contenedorEscalaListener.actualizarEscala(contenedorController.getEscala());
        }
    }
    
    /**
     * Muestra el view de la pantalla que se extrae del oyente que está suscrito
     * como oyente de la escala del contenedor.
     * 
     * Este método hace visible la pantalla suscrita dentro del contenedor.
     */
    public void mostrarPantalla() {
        if (contenedorPanel != null) {
            contenedorPanel.removeAll();
            contenedorPanel.add(contenedorEscalaListener.obtenerView());
            contenedorPanel.revalidate();
            contenedorPanel.repaint();
        }
    }
    
}
