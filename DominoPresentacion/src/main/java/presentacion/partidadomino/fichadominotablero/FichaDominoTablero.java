package presentacion.partidadomino.fichadominotablero;

import dominodto.FichaDominoDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FichaDominoTablero extends JPanel {

    private static final Logger LOGGER = Logger.getLogger(FichaDominoTablero.class.getName());
    private final String imagenMargenDomino = "/multimedia/DominoTableroFondo.png";
    private BufferedImage margen;
    private BufferedImage imageExtremo1;
    private BufferedImage imageExtremo2;
    private FichaDominoDTO fichaDominoDTO;
    private boolean esHorizontal;
    private int locacionX;
    private int locacionY;

    public FichaDominoTablero() {
        setLayout(null);
        setOpaque(false);
        cargarFondo();
    }

    public void cargarFondo() {
        try {
            margen = ImageIO.read(getClass().getResource(imagenMargenDomino));
        } catch (IOException | IllegalArgumentException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo cargar la imagen de fondo: " + imagenMargenDomino, ex);
        }
    }

    private BufferedImage cargarImagen(String rutaImagen) {
        if (rutaImagen == null || rutaImagen.isEmpty()) {
            LOGGER.warning("Ruta de imagen no especificada");
            return null;
        }
        try {
            return ImageIO.read(getClass().getResource(rutaImagen));
        } catch (IOException | IllegalArgumentException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo cargar la imagen: " + rutaImagen, ex);
            return null;
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
        // Dibujar los extremos
        if (imageExtremo1 != null && imageExtremo2 != null) {

            if (esHorizontal) {
                // Ficha horizontal
                int extremoWidth = height;
                int extremoHeight = height;

                // Dibujar el primer extremo (izquierda)
                AffineTransform old = g2d.getTransform();
                g2d.translate(height / 2, height / 2);
                g2d.rotate(Math.toRadians(90));
                g2d.translate(-height / 2, -height / 2);
                g2d.drawImage(imageExtremo1, 0, 0, extremoHeight, extremoWidth, null);
                g2d.setTransform(old);

                // Dibujar el segundo extremo (derecha)
                //g2d.rotate(Math.toRadians(-90));
                old = g2d.getTransform();
                g2d.rotate(Math.toRadians(90));
                g2d.translate(0, -width);
                g2d.drawImage(imageExtremo2, 0, 0, extremoHeight, extremoWidth, null);
                g2d.setTransform(old);
            } else {
                int extremoWidth = width;
                int extremoHeight = height / 2;

                // Dibujar el segundo extremo (arriba)
                g2d.drawImage(imageExtremo2, 0, 0, extremoWidth, extremoHeight, null);

                // Dibujar el primer extremo (abajo)
                g2d.drawImage(imageExtremo1, 0, extremoHeight, extremoWidth, extremoHeight, null);
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

    public void setImgExtremo1(String imgExtremo1) {
        this.imageExtremo1 = cargarImagen(imgExtremo1);
        if (this.imageExtremo1 == null) {
            LOGGER.warning("No se pudo cargar la imagen del extremo 1: " + imgExtremo1);
        } else {
            LOGGER.info("Imagen del extremo 1 cargada correctamente: " + imgExtremo1);
        }
    }

    public void setImgExtremo2(String imgExtremo2) {
        this.imageExtremo2 = cargarImagen(imgExtremo2);
        if (this.imageExtremo2 == null) {
            LOGGER.warning("No se pudo cargar la imagen del extremo 2: " + imgExtremo2);
        } else {
            LOGGER.info("Imagen del extremo 2 cargada correctamente: " + imgExtremo2);
        }
    }

    public void setFichaDominoDTO(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
    }

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }
}
