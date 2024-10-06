package presentacion.tablerodomino.mesadomino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MesaDominoPanel extends JPanel {

    public MesaDominoPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(205, 440));
        
        setOpaque(true); 
    }

    public void addFichaComparativaPanel(JPanel fichaComparativaPanel) {
        add(fichaComparativaPanel, BorderLayout.CENTER); 
    }
    
        /**
     * Se encarga de dibujar el contenido visual del panel.
     *
     * @param g Objeto Graphics utilizado para realizar las operaciones de
     * dibujo
     */
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        
        setBackground(new Color(12, 4, 58, 230));
        
    }

}
