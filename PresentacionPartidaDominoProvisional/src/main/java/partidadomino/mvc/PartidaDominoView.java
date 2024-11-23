/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author castr
 */
public class PartidaDominoView extends JPanel {

    private JLabel labelEspera;
    private final int ANCHO_MINIMO = 600;
    private final int ALTURA_MINIMA = 400;

    public PartidaDominoView() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(ANCHO_MINIMO, ALTURA_MINIMA));
        labelEspera = new JLabel("Sala de Espera", SwingConstants.CENTER);
        labelEspera.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(labelEspera, BorderLayout.CENTER);
    }
}
