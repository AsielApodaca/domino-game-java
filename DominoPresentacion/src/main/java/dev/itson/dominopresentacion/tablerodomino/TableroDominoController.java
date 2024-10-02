/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.itson.dominopresentacion.tablerodomino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author olive
 */
public class TableroDominoController {
    
    private TableroDominoModel tableroDominoModel ;
    private TableroDominoView tableroDominoView ;
    
    public TableroDominoController(TableroDominoModel tableroDominoModel, TableroDominoView tableroDominoView) {
        this.tableroDominoModel = tableroDominoModel ;
        this.tableroDominoView = tableroDominoView ;
    }
    
    
    class SeleccionarFichaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
}
