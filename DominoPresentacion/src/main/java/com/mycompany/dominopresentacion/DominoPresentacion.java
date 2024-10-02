/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.dominopresentacion;

import dev.itson.dominopresentacion.tablerodomino.TableroDominoView;
import javax.swing.JFrame;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class DominoPresentacion {

    public static void main(String[] args) {
        TableroDominoView tableroDominoView = new TableroDominoView();

        JFrame ventana = new JFrame("Domino");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1200, 800);
        ventana.setResizable(true);
        ventana.add(tableroDominoView);
        ventana.setVisible(true);
    }
}
