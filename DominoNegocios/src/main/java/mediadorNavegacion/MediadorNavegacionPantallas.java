/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mediadorNavegacion;

import javax.swing.JPanel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MediadorNavegacionPantallas {

    private static MediadorNavegacionPantallas instance;
    private JPanel contenedor;

    private MediadorNavegacionPantallas() {
    }

    public static MediadorNavegacionPantallas getInstance() {
        if (instance == null) {
            instance = new MediadorNavegacionPantallas();
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
