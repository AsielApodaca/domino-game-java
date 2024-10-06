/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tablerodomino;

import dominio.FichaDomino;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

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
        this.tableroDominoModel = tableroDominoModel;
        this.tableroDominoView = tableroDominoView;
        inicializarListener();
    }
    
    private void inicializarListener() {
        tableroDominoView.setSeleccionarFichaListener(new SeleccionarFichaListener());
    }

    class SeleccionarFichaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel fichaDominoPanel = (JPanel) e.getSource();
            int index = tableroDominoView.getFichasDominoUsuario().indexOf(fichaDominoPanel);

            if (index != -1) {
                try {
                    FichaDomino selectedFicha = tableroDominoModel.getListaFichasUsuario().get(index);
                    System.out.println("Ficha Seleccionada: [" + selectedFicha.getExtremo1()
                            + " | " + selectedFicha.getExtremo2() + "]");
                    tableroDominoModel.setFichaSeleccionada(selectedFicha);
                    tableroDominoModel.validarExtremoCompatible(selectedFicha);

                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Error");
            }
        }
    }
     public void startNewGame() {
        tableroDominoModel.repartirFichas();
    }
    
    public void endTurn() {
        
    }
}
