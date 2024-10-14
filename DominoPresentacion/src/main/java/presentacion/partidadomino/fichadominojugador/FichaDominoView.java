package presentacion.partidadomino.fichadominojugador;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FichaDominoView extends JPanel {

    private FichaDominoModel model;
    private BufferedImage margen;
    private BufferedImage imgExtremo1;
    private BufferedImage imgExtremo2;

    public FichaDominoView(FichaDominoModel model) throws IOException {
        this.model = model;
        setOpaque(false);
        setLayout(null); // Usar layout null para control total sobre el tamaño y posición
    }

    public void cargarComponentes() throws IOException {
        margen = cargarImagen(model.getFondoFicha());
        imgExtremo1 = cargarImagen(model.getImgExtremo1());
        imgExtremo2 = cargarImagen(model.getImgExtremo2());

        actualizarTamanio();
    }

    public void actualizarFondo() {
        try {
            margen = ImageIO.read(getClass().getResource(model.getFondoFicha()));
            revalidate();
            repaint();
        } catch (IOException ex) {
            Logger.getLogger(FichaDominoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BufferedImage cargarImagen(String ruta) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(ruta);
        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el recurso: " + ruta);
        }
        return ImageIO.read(inputStream);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Dibujar el margen
        if (margen != null) {
            g2d.drawImage(margen, 0, 0, width, height, this);
        }

        // Calcular el tamaño y posición de las imágenes de los extremos
        int extremoWidth = (int) (width * 0.9);
        int extremoHeight = (int) (height * 0.45);
        int extremoX = (width - extremoWidth) / 2;

        // Dibujar el primer extremo en la parte superior
        if (imgExtremo1 != null) {
            g2d.drawImage(imgExtremo1, extremoX, height / 20, extremoWidth, extremoHeight, this);
        }

        // Dibujar el segundo extremo en la parte inferior
        if (imgExtremo2 != null) {
            g2d.drawImage(imgExtremo2, extremoX, height / 2 + height / 20, extremoWidth, extremoHeight, this);
        }

        g2d.dispose();
    }

    public void actualizarTamanio() {
        int ancho = (int) (model.getAnchoFicha() * model.getEscala());
        int altura = (int) (model.getAlturaFicha() * model.getEscala());
        setSize(new Dimension(ancho, altura));
        setPreferredSize(new Dimension(ancho, altura));
        setMinimumSize(new Dimension(ancho, altura));
        setMaximumSize(new Dimension(ancho, altura));
    }

    public void repintar() {
        actualizarTamanio();
        revalidate();
        repaint();
    }
}
