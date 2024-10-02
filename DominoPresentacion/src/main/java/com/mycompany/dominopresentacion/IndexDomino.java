/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominopresentacion;

import dev.itson.dominopresentacion.tablerodomino.TableroDominoPanel;
import javax.swing.JFrame;

/**
 *
 * @author hisam
 */
public class IndexDomino extends JFrame{

    public IndexDomino() { 
        setTitle("Index");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TableroDominoPanel tableroDominoPanel = new TableroDominoPanel(); // Crear instancia del panel
        add(tableroDominoPanel); // Añadir el panel al JFrame
        setVisible(true);
    }
    
      public static void main(String[] args) {
        new IndexDomino();
    }
    
    
}
