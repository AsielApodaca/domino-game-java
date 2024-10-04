/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package contenedorView;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Asiel Apodaca Monge
 */
public class FormContenedorView extends JFrame {
    private JPanel contenedor;

    public FormContenedorView() {
        setTitle("DOT;MINO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Obtener la resolución de pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Calcular tamaño de la ventana en función de la resolución (80% del tamaño de la pantalla)
        int windowWidth = (int) (screenWidth * 0.8);
        int windowHeight = (int) (screenHeight * 0.8);
        setSize(windowWidth, windowHeight);

        // Crear el panel principal (contenedor)
        contenedor = new JPanel();
        contenedor.setPreferredSize(new Dimension(windowWidth, windowHeight)); // Ajustar dinámicamente

        // Establecer el contenido del JFrame al panel 'contenedor'
        setContentPane(contenedor);

        // Ajustar el tamaño del JFrame basado en el contenido
        pack();
        
    }

    public JPanel getContenedor() {
        return contenedor;
    }
}
