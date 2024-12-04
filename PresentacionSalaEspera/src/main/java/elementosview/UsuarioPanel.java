/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementosview;

import dominodto.UsuarioDTO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class UsuarioPanel extends JPanel {

    private UsuarioDTO usuarioDTO;
    private String nombre;
    private Icon icon;
    private static final int AVATAR_SIZE = 80;

    public UsuarioPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 0));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setPreferredSize(new Dimension(120, 150));
        setMaximumSize(new Dimension(120, 150));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (icon != null) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image img = imageIcon.getImage();

            // Center the avatar
            int x = (getWidth() - AVATAR_SIZE) / 2;
            int y = 0;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Create circular clip
            Ellipse2D.Float circle = new Ellipse2D.Float(x, y, AVATAR_SIZE, AVATAR_SIZE);
            g2d.setClip(circle);
            g2d.drawImage(img, x, y, AVATAR_SIZE, AVATAR_SIZE, null);

            // Draw circle border
            g2d.setClip(null);
            g2d.setColor(new Color(255, 255, 255, 100));
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(circle);
        }
    }

    public void organizarComponentes() {
        removeAll();

        add(Box.createVerticalStrut(AVATAR_SIZE + 10));

        // Username label
        JLabel nombreLabel = new JLabel(nombre);
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreLabel.setFont(new Font("Cairo", Font.BOLD, 14));
        nombreLabel.setForeground(Color.WHITE);
        add(nombreLabel);

        revalidate();
        repaint();
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    
    
}
