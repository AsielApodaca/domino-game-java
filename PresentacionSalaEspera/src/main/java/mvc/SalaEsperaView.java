/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc;

import javax.swing.JPanel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SalaEsperaView {

    private SalaEsperaModel salaEsperaModel;
    private JPanel panelSalaEspera;

    public SalaEsperaView(SalaEsperaModel salaEsperaModel) {
        this.salaEsperaModel = salaEsperaModel;

    }

    public void repintarPantalla() {
        repintarFondo();
        repintarJugadores();
        panelSalaEspera.revalidate();
        panelSalaEspera.repaint();
    }

    private void repintarFondo() {
    }

    public void repintarJugadores() {
    }

}
