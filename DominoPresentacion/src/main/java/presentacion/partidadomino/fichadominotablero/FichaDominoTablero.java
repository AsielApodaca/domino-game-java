package presentacion.partidadomino.fichadominotablero;

import dominodto.FichaDominoDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FichaDominoTablero extends JPanel {
    private final String imagenMargenDomino = "/multimedia/DominoTableroFondo.png";
    private BufferedImage margen;
    private String imgExtremo1;
    private String imgExtremo2;
    private JLabel labelExtremo1;
    private JLabel labelExtremo2;
    private FichaDominoDTO fichaDominoDTO;
    private int anchoSinEscala;
    private int alturaSinEscala;
    private int rotacion;
    private boolean isHorizontal;

    public FichaDominoTablero() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void cargarFondo() {
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

    public void asignarExtremos() {
        removeAll();
        
        labelExtremo1 = createScaledLabel(imgExtremo1);
        labelExtremo2 = createScaledLabel(imgExtremo2);

        if (isHorizontal) {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        } else {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        if (rotacion == 0 || rotacion == 270) {
            add(labelExtremo1);
            add(labelExtremo2);
        } else {
            add(labelExtremo2);
            add(labelExtremo1);
        }

        revalidate();
        repaint();
    }

    private JLabel createScaledLabel(String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(getWidth() / 2, getHeight() / 2, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImg));
    }

    @Override
    public Dimension getPreferredSize() {
        int width = isHorizontal ? 60 : 30;
        int height = isHorizontal ? 30 : 60;
        return new Dimension(width, height);
    }

    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
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

    public int getAnchoSinEscala() {
        return anchoSinEscala;
    }

    public void setAnchoSinEscala(int anchoSinEscala) {
        this.anchoSinEscala = anchoSinEscala;
    }

    public int getAlturaSinEscala() {
        return alturaSinEscala;
    }

    public void setAlturaSinEscala(int alturaSinEscala) {
        this.alturaSinEscala = alturaSinEscala;
    }

    
    
   
}
