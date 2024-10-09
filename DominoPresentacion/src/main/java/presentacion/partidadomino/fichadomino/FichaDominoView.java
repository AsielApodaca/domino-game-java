/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadomino;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FichaDominoView extends JPanel {

    private FichaDominoModel model;
    private JLabel labelExtremo1;
    private JLabel labelExtremo2;

    public FichaDominoView() throws IOException {
        this.model = new FichaDominoModel();
        cargarComponentes();
        asignarExtremos(model);
    }

    private void cargarComponentes() throws IOException {
        ImageIO.read(getClass().getResource(model.getImagenMargenDomino()));
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

}
