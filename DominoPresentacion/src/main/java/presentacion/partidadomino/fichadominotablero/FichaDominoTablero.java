/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadominotablero;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import presentacion.partidadomino.fichadominojugador.FichaDominoModel;

/**
 *
 * @author hisam
 */
public class FichaDominoTablero extends JPanel{
    
    private final String imagenMargenDomino = "/multimedia/DominoTableroFondo.png";
    private int valorExtremo1;
    private int valorExtremo2;
    private String imgExtremo1; 
    private String imgExtremo2;
    private BufferedImage margen;
    private JLabel labelExtremo1;
    private JLabel labelExtremo2;
    private FichaDominoDTO fichaDominoDTO;
    

    public FichaDominoTablero() {
    }
    
    public void cargarFondo(){
        try {
            margen = ImageIO.read(getClass().getResource(imagenMargenDomino));
        } catch (IOException ex) {
            Logger.getLogger(FichaDominoTablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (margen != null) {
            g.drawImage(margen, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    /**
     * A cada extremo se le asigna una imágen previamente cargada en el modelo.
     *
     * @param model
     * @throws IOException Si ocurre un error al intentar leer el archivo.
     */
    private void asignarExtremos() throws IOException {
        labelExtremo2 = new JLabel(new ImageIcon(imgExtremo2), SwingConstants.CENTER);
        labelExtremo2 = new JLabel(new ImageIcon(imgExtremo1), SwingConstants.CENTER);

        add(labelExtremo1);
        add(labelExtremo2);
    }

    public JLabel getLabelExtremo1() {
        return labelExtremo1;
    }

    public void setLabelExtremo1(JLabel labelExtremo1) {
        this.labelExtremo1 = labelExtremo1;
    }

    public JLabel getLabelExtremo2() {
        return labelExtremo2;
    }

    public void setLabelExtremo2(JLabel labelExtremo2) {
        this.labelExtremo2 = labelExtremo2;
    }

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }

    public void setFichaDominoDTO(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
    }

    
    public int getValorExtremo1() {
        return valorExtremo1;
    }

    public void setValorExtremo1(int valorExtremo1) {
        this.valorExtremo1 = valorExtremo1;
    }

    public int getValorExtremo2() {
        return valorExtremo2;
    }

    public void setValorExtremo2(int valorExtremo2) {
        this.valorExtremo2 = valorExtremo2;
    }

    public String getImgExtremo1() {
        return imgExtremo1;
    }

    public void setImgExtremo1(String imgExtremo1) {
        this.imgExtremo1 = imgExtremo1;
    }

    public String getImgExtremo2() {
        return imgExtremo2;
    }

    public void setImgExtremo2(String imgExtremo2) {
        this.imgExtremo2 = imgExtremo2;
    }

    public String getImagenMargenDomino() {
        return imagenMargenDomino;
    }

    
    
   
}
