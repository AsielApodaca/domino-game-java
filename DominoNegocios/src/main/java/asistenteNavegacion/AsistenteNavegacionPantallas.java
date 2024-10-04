/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package asistenteNavegacion;

import javax.swing.JPanel;
/**
 *
 * @author Asiel Apodaca Monge
 */
public class AsistenteNavegacionPantallas {
    private static AsistenteNavegacionPantallas instance;
    private JPanel contenedor;
    
    private AsistenteNavegacionPantallas() {}
    
    public static AsistenteNavegacionPantallas getInstance() {
        if (instance == null) {
            instance = new AsistenteNavegacionPantallas();
        }
        return instance;
    }
    
    public void setContenedor(JPanel contenedor) {
        this.contenedor = contenedor;
    }
    
    public void navegarA(JPanel nuevaPantalla) {
        if (contenedor != null) {
            contenedor.removeAll();
            contenedor.add(nuevaPantalla);
            contenedor.revalidate();
            contenedor.repaint();
        }
    }
}
