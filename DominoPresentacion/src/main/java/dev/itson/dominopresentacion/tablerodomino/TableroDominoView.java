/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.itson.dominopresentacion.tablerodomino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;

/**
 *
 * @author olive
 */
public class TableroDominoView extends JPanel {
    
    private JLabel piezaDomino;

    public TableroDominoView() {
        setPreferredSize(new Dimension(1200, 800)); // Definir tamaño preferido
        setBackground(Color.CYAN); // Color de fondo del panel

        piezaDomino = new JLabel();
        piezaDomino.setSize(200, 100); // Establecer tamaño de la pieza
        piezaDomino.setBackground(Color.BLACK); // Pintar de negro
        piezaDomino.setOpaque(true); // Hacer opaco para que el color de fondo sea visible
        piezaDomino.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el JLabel

        this.add(piezaDomino); // Añadir la pieza al panel
    }
    public void setSeleccionarFichaListener(ActionListener listener) {
        
    }
       
}
