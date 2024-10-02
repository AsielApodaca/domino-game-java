/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.itson.dominopresentacion.tablerodomino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoView extends JPanel {

   private JButton[] fichas;
    private JButton fichaComparativa;

    public TableroDominoView() {
        setLayout(null); 
        setPreferredSize(new Dimension(1200, 800)); 
        crearFichasVisuales();
    }

    private void crearFichasVisuales() { 
        fichas = new JButton[5];

      
        for (int i = 0; i < fichas.length; i++) {
            fichas[i] = new JButton("[" + i + " | " + (i + 1) + "]");
            fichas[i].setPreferredSize(new Dimension(110, 70)); 
            fichas[i].setFocusable(false); 
            fichas[i].setBackground(Color.LIGHT_GRAY); 
            fichas[i].setFont(new Font("Arial", Font.BOLD, 16));
            
            int xPosition = 377 + (i * 75);
            fichas[i].setBounds(xPosition, 678, 70, 110);
            add(fichas[i]);
        }

       
        fichaComparativa = new JButton("[3 | 4]");
        fichaComparativa.setPreferredSize(new Dimension(110, 70));
        fichaComparativa.setFocusable(false);
        fichaComparativa.setBackground(Color.YELLOW); 
        fichaComparativa.setFont(new Font("Arial", Font.BOLD, 16));
        fichaComparativa.setBounds(550, 350, 70, 110);
        add(fichaComparativa);
    }

    public void setSeleccionarFichaListener(ActionListener listener) {
        for (JButton ficha : fichas) {
            ficha.addActionListener(listener);
        }
    }
}
