/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadomino;

import dominodto.FichaDominoDTO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import presentacion.mediador.IMediador;
import presentacion.mediador.Mediador;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FichaDominoView extends JPanel {

    private FichaDominoModel model;
    private IMediador mediador;
    private BufferedImage margen;
    private JLabel labelExtremo1;
    private JLabel labelExtremo2;

    public FichaDominoView(FichaDominoModel model) throws IOException {
        mediador = new Mediador();
        cargarComponentes();
        asignarExtremos(model);
    }

    private void cargarComponentes() throws IOException {
        margen = ImageIO.read(getClass().getResource(model.getImagenMargenDomino()));
    }

    /**
     * A cada extremo se le asigna una imágen previamente cargada en el modelo.
     *
     * @param model
     * @throws IOException Si ocurre un error al intentar leer el archivo.
     */
    private void asignarExtremos(FichaDominoModel model) throws IOException {
        labelExtremo1 = new JLabel(new ImageIcon(model.getImgExtremo1()), SwingConstants.CENTER);
        labelExtremo2 = new JLabel(new ImageIcon(model.getImgExtremo2()), SwingConstants.CENTER);

        add(labelExtremo1);
        add(labelExtremo2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (margen != null) {
            g.drawImage(margen, 0, 0, getWidth(), getHeight(), this);
        }
    }
    private void redimencionarFichasJugadorLocal() {
        // Redimencionar fichas
        
    }

}
