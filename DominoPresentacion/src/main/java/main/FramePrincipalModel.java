package main;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FramePrincipalModel {
    private String tituloJuego;
    private int anchoVentana;
    private int altoVentana;
    private JPanel[] ventanas;

    public FramePrincipalModel() {
        this.tituloJuego = "DOT;MINO";
        this.anchoVentana = 1200;
        this.altoVentana = 800;

        // Arreglo de paneles
        this.ventanas = new JPanel[]{
            createPanel1(),
            createPanel2(),
            createPanel3()
        };
    }

    private JPanel createPanel1() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        panel.setName("Panel 1");
        JButton switchButton = new JButton("Ir al Panel 2");
        panel.add(switchButton);
        return panel;
    }

    private JPanel createPanel2() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setName("Panel 2");
        JButton switchButton = new JButton("Ir al Panel 3");
        panel.add(switchButton);
        return panel;
    }

    private JPanel createPanel3() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setName("Panel 3");
        JButton switchButton = new JButton("Regresar al Panel 1");
        panel.add(switchButton);
        return panel;
    }

    public String getTituloJuego() {
        return tituloJuego;
    }

    public int getAnchoVentana() {
        return anchoVentana;
    }

    public int getAltoVentana() {
        return altoVentana;
    }

    public JPanel[] getVentanas() {
        return ventanas;
    }
}
