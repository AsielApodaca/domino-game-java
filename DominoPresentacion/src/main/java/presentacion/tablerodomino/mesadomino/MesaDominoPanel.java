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
        setBackground(new Color(12, 4, 58, 230));
        setOpaque(true); 
    }

    public void addFichaComparativaPanel(JPanel fichaComparativaPanel) {
        add(fichaComparativaPanel, BorderLayout.CENTER); 
    }
}
