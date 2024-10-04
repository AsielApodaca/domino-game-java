package presentacion.tablerodomino.fichadomino;

import dominio.FichaDomino;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
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
public class FichaDominoPanel extends JPanel {

    private JLabel labelExtremo1;
    private JLabel labelExtremo2;

    /**
     * Constructor con los atributos de la fichaDominoPanel. Se dividio en dos
     * hileras con un grid, la row numero 1 es un extremo y la row numero 2 es
     * otro extremo. Se le dio formato y se agregó al panel.
     *
     * @param ficha
     */
    public FichaDominoPanel(FichaDomino ficha) {
        setPreferredSize(new Dimension(90, 110));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridLayout(2, 1, 2, 2));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        labelExtremo1 = new JLabel(String.valueOf(ficha.getExtremo1()), SwingConstants.CENTER);
        labelExtremo2 = new JLabel(String.valueOf(ficha.getExtremo2()), SwingConstants.CENTER);

        labelExtremo1.setFont(new Font("Arial", Font.BOLD, 22));
        labelExtremo2.setFont(new Font("Arial", Font.BOLD, 22));

        add(labelExtremo1);
        add(labelExtremo2);
    }
    
    /**
     * Se crea un listener para cuando se seleccione esta pieza de dominó
     * @param listener 
     */
    public void agregarListenerAlSeleccionar(ActionListener listener) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                listener.actionPerformed(new java.awt.event.ActionEvent(FichaDominoPanel.this, java.awt.event.ActionEvent.ACTION_PERFORMED, null));
            }
        });
    }
}


