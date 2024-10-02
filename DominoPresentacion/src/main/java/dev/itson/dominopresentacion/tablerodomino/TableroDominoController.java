/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.itson.dominopresentacion.tablerodomino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Hisamy Cinco Cota
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
    }
    
    
    class SeleccionarFichaListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
}
