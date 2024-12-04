/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.elementostablero;

import dominodto.JugadorDominoDTO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author castr
 */
public class ResultadoPartidaPanel extends JPanel {

    private final JButton btnSalir;
    private final JLabel lblGanador;
    private final JLabel lblIcono;
    private final int anchoPanel = 600;
    private final int alturaPanel = 400;
    private float escala = 1.0f;

    public ResultadoPartidaPanel(JugadorDominoDTO ganador) {
        setLayout(null);
        setOpaque(false);

        lblGanador = new JLabel("¡" + ganador.getNombre() + " ha ganado!");
        lblGanador.setFont(new Font("Arial", Font.BOLD, 24));
        lblGanador.setForeground(Color.WHITE);
        lblGanador.setHorizontalAlignment(SwingConstants.CENTER);

        lblIcono = new JLabel();
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(ganador.getFuenteIcono()));
            Image scaledImg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            lblIcono.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));

        add(lblGanador);
        add(lblIcono);
        add(btnSalir);
    }

    public void reescalar(float escala) {
        this.escala = escala;
        setBounds(0, 0, (int) (anchoPanel * escala), (int) (alturaPanel * escala));
        repintar();
    }

    private void repintar() {
        int centerX = (int) (anchoPanel * escala / 2);
        int centerY = (int) (alturaPanel * escala / 2);

        lblGanador.setBounds(centerX - 150, centerY - 100, 300, 30);
        lblGanador.setFont(new Font("Arial", Font.BOLD, (int) (24 * escala)));

        lblIcono.setBounds(centerX - 32, centerY - 32, 64, 64);
        btnSalir.setBounds(centerX - 50, centerY + 50, 100, 30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
        g2d.setColor(new Color(12, 4, 58));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        g2d.dispose();
    }
}
