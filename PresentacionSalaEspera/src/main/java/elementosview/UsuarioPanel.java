/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementosview;

import dominodto.UsuarioDTO;
import java.awt.Color;
import java.awt.Component;
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

    public UsuarioPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Coloca los elementos en fila vertical
        setBackground(Color.WHITE); // Fondo blanco por defecto
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Agrega un borde para separar los elementos

        // Alineación y diseño
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el avatar redondo
        if (icon != null) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image img = imageIcon.getImage();
            int width = 50; // Ancho del círculo
            int height = 50; // Alto del círculo

            // Dibuja el avatar redondo
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setClip(new Ellipse2D.Float(0, 0, width, height)); // Establece el clip a un círculo
            g2d.drawImage(img, 0, 0, width, height, null); // Dibuja la imagen dentro del círculo
        }
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * Método para organizar los elementos del panel.
     */
    public void organizarComponentes() {
        // Nombre del usuario
        JLabel nombreLabel = new JLabel(nombre);
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        nombreLabel.setFont(new Font("Cairo", Font.BOLD, 12));
        nombreLabel.setForeground(Color.BLACK); 

        // Añadir los componentes al panel
        add(Box.createVerticalStrut(10)); 
        add(nombreLabel);
    }
}
