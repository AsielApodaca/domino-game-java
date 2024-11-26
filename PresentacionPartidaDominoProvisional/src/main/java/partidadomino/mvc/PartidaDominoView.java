/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import listeners.mvcview.IViewListener;

/**
 *
 * @author castr
 */
public class PartidaDominoView extends JPanel {

    private JLabel labelEspera;
    private JButton botonIniciar;
    private IViewListener viewListener;
    private final int ANCHO_MINIMO = 600;
    private final int ALTURA_MINIMA = 400;

    public PartidaDominoView() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(ANCHO_MINIMO, ALTURA_MINIMA));
        botonIniciar = new JButton("Iniciar Partida");
        botonIniciar.setBounds(50, 50, 200, 30);
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onBtnEmpezarPartidaPrecionado();
            }
        });
        labelEspera = new JLabel("Sala de Espera", SwingConstants.CENTER);
        labelEspera.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(labelEspera, BorderLayout.CENTER);
        this.add(botonIniciar, BorderLayout.CENTER);
    }
    
    
    
    public void suscribirListener(IViewListener viewListener) {
        this.viewListener = viewListener;
    }
}
