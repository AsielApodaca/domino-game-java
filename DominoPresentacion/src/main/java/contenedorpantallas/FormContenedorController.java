/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedorpantallas;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

/**
 *
 * @author asielapodaca
 */
public class FormContenedorController {
    
    private final MediadorContenedorContenido mediadorEscala = MediadorContenedorContenido.getInstance();
    private FormContenedorModel model; // Modelo FormContenedorModel
    private FormContenedorView view; // JFrame FormContenedorView

    public FormContenedorController(FormContenedorModel model, FormContenedorView view) {
        this.model = model;
        this.view = view;
        
        // Inicializar el tamaño del label
        updateModelAndView(); // Llamar a este método para inicializar

        // Agregar los listeners
        view.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateModelAndView();
            }
        });
    }
    
    public void showView() {
        view.setVisible(true);
    }
    
    public JPanel getContenedorPanel() {
        return view.getContainerPanel();
    }
    
    public float getEscala() {
        return model.getEcale();
    }
    
    private void updateModelAndView() {
        // Actualizar el modelo con el nuevo tamaño
        model.setAnchoFrame(view.getWidth());
        model.setAlturaFrame(view.getHeight());
        model.setAlturaBarraTituloVentana(view.getInsets().top);
        updateFrameContainerValues();

        
        
        // Asigna la locación centrada para el contenedor
        updateContainerLocation();
        
        // Actualizar el tamaño del contenedor
        view.updateContainerSize();
        
        // Ajustar el tamaño minimo del Frame
        view.updateFrameMinimumSize();
        
        // Asigna la escala de tamaño del contenedor
        updateScale();
        
        // Notifica el cambio de escala al contenido del contenedor
        mediadorEscala.notificarEscalaAContenido();
    }
    
    private void updateFrameContainerValues() {
        // No hace nada si aun no hay un Frame
        if (model.getAnchoFrame() == 0 || model.getAlturaFrame() == 0) return; 
        // Calcular el nuevo tamaño del JPanel
        int anchoContenedor = model.getAnchoFrame();
        int alturaContenedor = (int) (anchoContenedor * model.getRelacionAlturaAncho());
        
        // Calcula altura útil del frame
        int alturaUtilFrame = model.getAlturaFrame() - model.getAlturaBarraTituloVentana();
        model.setAlturaUtilFrame(alturaUtilFrame);
        
        // Verificar si el nuevo alto supera la altura útil del JFrame
        if (alturaContenedor > alturaUtilFrame) {
            alturaContenedor = alturaUtilFrame; // Limitar el alto al tamaño útil del JFrame
            float relacionAnchoAltura = 1 / model.getRelacionAlturaAncho();
            anchoContenedor = (int) (alturaContenedor * (relacionAnchoAltura)); // Ajustar el ancho para mantener la proporción
        }
        
        // Guarda las nuevas medidas del contenedor en modelo
        model.setAnchoContenedor(anchoContenedor);
        model.setAlturaContenedor(alturaContenedor);
        
        // Ajusta la altura minima del Frame para que tenga la mejor
        // proporción para el contenedor
        int alturaMinimaFrameAjustado = model.getAlturaMinimaFrame() + model.getAlturaBarraTituloVentana();
        model.setAlturaMinimaFrameAjustado(alturaMinimaFrameAjustado);

    }
    
    private void updateScale() {
        int anchoBase = model.getAnchoMinimoFrame();
        int anchoContenedor = model.getAnchoContenedor();
        float escala = (float) anchoContenedor / anchoBase;
        model.setEscala(escala);
    }
    
    private void updateContainerLocation() {
        // Establece locación centrada para el contenedor en el Frame
        int x = (model.getAnchoFrame() - model.getAnchoContenedor()) / 2;
        int y = (model.getAlturaUtilFrame() - model.getAlturaContenedor()) / 2;
        model.setLocacionContenedorX(x);
        model.setLocacionContenedorY(y);
    }
    
    
    
}
