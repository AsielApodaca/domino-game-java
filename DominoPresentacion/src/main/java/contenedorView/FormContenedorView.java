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
        setTitle("Mi Aplicación");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contenedor = new JPanel();
        setContentPane(contenedor);
        
        
    }
    
    public JPanel getContenedor() {
        return contenedor;
    }
}
