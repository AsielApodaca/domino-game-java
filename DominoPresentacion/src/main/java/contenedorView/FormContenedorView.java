/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedorView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author asielapodaca
 */
public class FormContenedorView extends JFrame{
    
    private JPanel containerPanel; // Panel que contendrá las pantallas del juego
    
    private FormContenedorModel model; // Modelo FormContenedorModel

    public FormContenedorView(FormContenedorModel model) {
        this.model = model;
        // Configuración del JFrame
        setTitle("DOT;MINO");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(model.getFrameBrackgroundColor()); // Color del Frame
        setLayout(null); // Usar null layout para posicionar manualmente el JPanel
        
        // Crear el JPanel contenedor
        containerPanel = model.getContenedorPanel();
        containerPanel.setBackground(model.getContenedorBrackgroundColor()); // Color del contenedor
        containerPanel.setLayout(new BorderLayout()); // Usar BorderLayout en el JPanel
        add(containerPanel); // Añadir el JPanel al JFrame
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizado por defecto
    }
    
    public void updateContainerSize() {
        
        // Ajustar el tamaño y la posición del JPanel
        containerPanel.setSize(model.getAnchoContenedor(), model.getAlturaContenedor()); // Establecer el tamaño del JPanel

        // Centrando el JPanel en el JFrame
        int x = model.getLocacionContenedorX();
        int y = model.getLocacionContenedorY();
        containerPanel.setLocation(x, y); // Establecer la ubicación del JPanel

        // Llamar a revalidate y repaint para reflejar los cambios
        containerPanel.revalidate();
        containerPanel.repaint();
    }
    
    public void updateFrameMinimumSize() {
        int anchoMinimo = model.getAnchoMinimoFrame();
        int alturaMinima = model.getAlturaMinimaFrameAjustado();
        setMinimumSize(new Dimension(anchoMinimo, alturaMinima));
    }
    
}
