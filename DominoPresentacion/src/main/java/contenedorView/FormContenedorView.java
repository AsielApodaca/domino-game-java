/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package contenedorView;

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

        // Crear el panel principal (contenedor)
        contenedor = new JPanel();
        
        /**
         * Esto es temporal para que la barra del titulo de la ventana
         * no afecte el tamaño el contenido del panel, que no
         * "empuje el contenido".
         * 
         * La idea es después quitar la barra del título para luego
         * poner la opción de cerrar dentro de la presentación del proyecto.
         */
        contenedor.setPreferredSize(new java.awt.Dimension(1200, 800)); // Establece el tamaño preferido

        // Establecer el contenido del JFrame al panel 'contenedor'
        setContentPane(contenedor);

        // Ajustar el tamaño del JFrame basado en el contenido
        pack();
        
    }

    public JPanel getContenedor() {
        return contenedor;
    }
}
