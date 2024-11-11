/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mediadorsingleton;

import javax.swing.JPanel;
import listeners.IContenedorListener;
import mvc.FormContenedorController;

/**
 *
 * @author asielapodaca
 */
public class MediadorSingletonContenedorContenido {
    private static MediadorSingletonContenedorContenido instance;
    private FormContenedorController contenedorController;
    private IContenedorListener contenedorListener;
    private JPanel contenedorPanel;

    private MediadorSingletonContenedorContenido() {
    }

    /**
     * Obtiene instancia estática del Singleton entre Contenedor y Conenido
     * @return instancia estática de si mismo
     */
    public static MediadorSingletonContenedorContenido getInstance() {
        if (instance == null) {
            instance = new MediadorSingletonContenedorContenido();
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
     * Suscribe un oyente del panel del contenedor.
     * La escala hace referencia a que tan grande es el panel a comparación
     * de su tamaño original.
     * 
     * El oyente que se suscribe es el panel que será el contenido del contenedor,
     * es decir, la pantalla que se mostrará.
     * @param contenedorListener Pantalla mostrada a suscribir como oyente del contenedor.
     */
    public void setContenedorListener(IContenedorListener contenedorListener) {
        this.contenedorListener = contenedorListener;
        notificarEscalaAContenedorListener();
    }
    
    /**
     * Notifica la escala del panel contenedor al panel de la pantalla que
     * esté actualmente suscrita como oyente de la escala del contenedor.
     */
    public void notificarEscalaAContenedorListener() {
        if(contenedorController != null && contenedorListener != null) {
            this.contenedorListener.onEscalaChange(contenedorController.getEscala());
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
            contenedorPanel.add(contenedorListener.obtenerView());
            contenedorPanel.revalidate();
            contenedorPanel.repaint();
        }
    }
}
