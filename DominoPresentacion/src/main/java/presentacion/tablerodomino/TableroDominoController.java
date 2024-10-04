/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tablerodomino;

import dominio.FichaDomino;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoController {
    
    private TableroDominoModel tableroDominoModel ;
    private TableroDominoView tableroDominoView ;
    
    public TableroDominoController(TableroDominoModel tableroDominoModel, TableroDominoView tableroDominoView) {
        this.tableroDominoModel = tableroDominoModel ;
        this.tableroDominoView = tableroDominoView ;
        this.tableroDominoView.setSeleccionarFichaListener(new SeleccionarFichaListener());
        initializeListeners() ;
    }
    
    private void initializeListeners() {
        for (JButton button : tableroDominoView.getBotonesFichasDominoUsuario()) {
            button.addActionListener(new SeleccionarFichaListener());
        }
        
        tableroDominoView.setSeleccionarFichaListener(new SeleccionarFichaListener());
    }
    
    
    class SeleccionarFichaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton)e.getSource();
            int index = tableroDominoView.getBotonesFichasDominoUsuario().indexOf(sourceButton);
            
            if (index != -1) {
                try {
                    FichaDomino selectedFicha = tableroDominoModel.getListaFichasUsuario().get(index);
                    System.out.println("Ficha Seleccionada: [" + selectedFicha.getExtremo1() + 
                                      " | " + selectedFicha.getExtremo2() + "]");
                    
                    tableroDominoModel.validarExtremoCompatible(selectedFicha);
                    
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Error");
            }
        }
    }
}
