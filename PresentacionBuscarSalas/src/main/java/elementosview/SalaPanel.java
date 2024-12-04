/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementosview;

import dominodto.SalaDTO;
import dominodto.UsuarioDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author olive
 */
public class SalaPanel extends JPanel {
    private SalaDTO sala;
    private static final int ICON_SIZE = 50;
    private static final int ICON_PADDING = 10;
    private JButton joinButton;

    public SalaPanel(SalaDTO sala) {
        this.sala = sala;
        setBackground(new Color(0, 0, 0, 150)); // Fondo transparente oscuro
        setPreferredSize(new Dimension(600, 100)); // Tamaño de la panel

        // Crear el botón "Unirse"
        joinButton = new JButton("Unirse");
        joinButton.setForeground(Color.WHITE);
        joinButton.setBackground(new Color(0, 128, 0));
        joinButton.setFocusPainted(false);
        joinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                joinButton.setBackground(new Color(0, 160, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                joinButton.setBackground(new Color(0, 128, 0));
            }
        });
        add(joinButton, BorderLayout.SOUTH);
    }

    public JButton getJoinButton() {
        return joinButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el nombre de la sala
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Sala de CompaOli", 10, 20);

        // Dibujar la fracción de jugadores
        g2d.drawString(sala.getUsuarios().size() + "/" + sala.getSize(), 10, 40);

        // Dibujar los iconos de los jugadores
        int x = getWidth() - (ICON_SIZE * sala.getUsuarios().size()) - (ICON_PADDING * (sala.getUsuarios().size() - 1)) - 10 - joinButton.getWidth() - 10;
        int y = 25;
        for (UsuarioDTO usuario : sala.getUsuarios()) {
            drawIcon(g2d, usuario.getFuenteIcono(), x, y);
            g2d.drawString(usuario.getNombre(), x, y + ICON_SIZE + 5);
            x += ICON_SIZE + ICON_PADDING;
        }
    }

    private void drawIcon(Graphics2D g2d, String iconPath, int x, int y) {
        try {
            BufferedImage image = cargarImagen(iconPath);
            Image scaledImage = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            g2d.fillOval(x, y, ICON_SIZE, ICON_SIZE);
            g2d.drawImage(scaledImage, x, y, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage cargarImagen(String ruta) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(ruta);
        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el recurso: " + ruta);
        }
        return ImageIO.read(inputStream);
    }

    public SalaDTO getSala() {
        return sala;
    }
    
    
}