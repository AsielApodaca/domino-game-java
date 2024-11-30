/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.elementostablero;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author castr
 */
public class PozoPanel extends JPanel {

    private static final Logger LOG = Logger.getLogger(PozoPanel.class.getName());
    private BufferedImage imagenPozo;

    private final String fuenteImagenPozoBloqueado = "/multimedia/PozoBloqueado.png";
    private final String fuenteImagenPozoDesbloqueado = "/multimedia/PozoBloqueado.png";
    private int locacionX;
    private int locacionY;
    private boolean Bloqueado;

    public PozoPanel() {
        setLayout(null);
        setOpaque(false);
        cargarFondo();
    }

    public void cargarFondo() {
        try {
            // Cargar la imagen correspondiente segun el estado de Bloqueado
            if (Bloqueado) {
                imagenPozo = ImageIO.read(getClass().getResource(fuenteImagenPozoBloqueado));
            } else {
                imagenPozo = ImageIO.read(getClass().getResource(fuenteImagenPozoDesbloqueado));
            }
        } catch (IOException | IllegalArgumentException ex) {
            LOG.log(Level.SEVERE, "No se pudo cargar la imagen de fondo.", ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Dibujar el fondo (sin rotación, siempre en vertical)
        if (imagenPozo != null) {
            g2d.drawImage(imagenPozo, 0, 0, width, height, null);
        }

        g2d.dispose();
    }

    public int getLocacionX() {
        return locacionX;
    }

    public void setLocacionX(int locacionX) {
        this.locacionX = locacionX;
    }

    public int getLocacionY() {
        return locacionY;
    }

    public void setLocacionY(int locacionY) {
        this.locacionY = locacionY;
    }

    public boolean isBloqueado() {
        return Bloqueado;
    }

    public void setBloqueado(boolean Bloqueado) {
        this.Bloqueado = Bloqueado;
        cargarFondo(); // Recargar la imagen cuando cambie el estado
        repaint(); // Volver a dibujar el panel con la nueva imagen
    }

}
