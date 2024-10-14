/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.tablero;

import dominodto.FichaDominoDTO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author asielapodaca
 */
public class CasillaPanel extends JPanel{
    
    private final int MULA = 0;
    private final int EXTREMO1 = 1;
    private final int EXTREMO2 = 2;
    private int extremo;
    
    private static final Logger LOGGER = Logger.getLogger(FichaDominoTablero.class.getName());
    private final String fuenteImagenCasilla = "/multimedia/casilla.png";
    private BufferedImage margen;
    private boolean esHorizontal;
    private int locacionX;
    private int locacionY;

    public CasillaPanel() {
        setLayout(null);
        setOpaque(false);
        cargarFondo();
    }

    public void cargarFondo() {
        try {
            margen = ImageIO.read(getClass().getResource(fuenteImagenCasilla));
        } catch (IOException | IllegalArgumentException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo cargar la imagen de fondo: " + fuenteImagenCasilla, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Dibujar el fondo rotado
        if (margen != null) {
            if (esHorizontal) {
                AffineTransform old = g2d.getTransform();
                g2d.translate(width / 2, height / 2);
                g2d.rotate(Math.toRadians(90));
                g2d.translate(-height / 2, -width / 2);
                g2d.drawImage(margen, 0, 0, height, width, null);
                g2d.setTransform(old);
            } else {
                g2d.drawImage(margen, 0, 0, width, height, null);
            }
        }

        g2d.dispose();
    }
    
    public boolean getEsHorizontal() {
        return esHorizontal;
    }

    public void setEsHorizontal(boolean esHorizontal) {
        this.esHorizontal = esHorizontal;
        revalidate();
        repaint();
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

    public int getExtremo() {
        return extremo;
    }

    public void setExtremo(int extremo) {
        this.extremo = extremo;
    }

    public int getEXTREMO1() {
        return EXTREMO1;
    }

    public int getEXTREMO2() {
        return EXTREMO2;
    }
    
    
}
