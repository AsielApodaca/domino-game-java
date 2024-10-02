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
 * @author olive
 */
public class TableroDominoView extends JPanel {

   private JButton[] fichas;
    private JButton fichaComparativa;

    public TableroDominoView() {
        setLayout(null); // Usar diseño nulo para posicionamiento manual
        setPreferredSize(new Dimension(1200, 800)); // Establecer tamaño preferido del panel
        crearFichasVisuales();
    }

    private void crearFichasVisuales() {
        // Inicializar el array de fichas
        fichas = new JButton[5]; // Cambia el tamaño según la cantidad de fichas que desees

        // Crear y agregar fichas de dominó al panel
        for (int i = 0; i < fichas.length; i++) {
            fichas[i] = new JButton("[" + i + " | " + (i + 1) + "]");
            fichas[i].setPreferredSize(new Dimension(70, 110)); // Tamaño de la ficha
            fichas[i].setFocusable(false); // Para evitar que el botón esté en foco
            fichas[i].setBackground(Color.LIGHT_GRAY); // Color de fondo de la ficha
            fichas[i].setFont(new Font("Arial", Font.BOLD, 16)); // Fuente del texto
            
            // Establecer posición de las fichas
            int xPosition = 377 + (i * 75); // Ajusta el valor de 75 según el espaciado que desees
            fichas[i].setBounds(xPosition, 678, 70, 110);
            add(fichas[i]);
        }

        // Crear y posicionar la ficha comparativa en el centro
        fichaComparativa = new JButton("[3 | 4]");
        fichaComparativa.setPreferredSize(new Dimension(70, 110));
        fichaComparativa.setFocusable(false);
        fichaComparativa.setBackground(Color.YELLOW); // Color de fondo para distinguirla
        fichaComparativa.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente del texto
        
        // Posicionar la ficha comparativa en el centro
        fichaComparativa.setBounds(550, 350, 70, 110);
        add(fichaComparativa);
    }

    public void setSeleccionarFichaListener(ActionListener listener) {
        // Asignar el ActionListener a cada ficha
        for (JButton ficha : fichas) {
            ficha.addActionListener(listener);
        }
    }
}
