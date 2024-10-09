package presentacion.fichadomino;

import dominodto.FichaDominoDTO;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
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
public class FichaDominoPanel extends JPanel {

    private FichaDominoDTO fichaDominoDTO;
    private final JLabel labelExtremo1;
    private final JLabel labelExtremo2;
    private boolean isSelected = false;
    private final Image fondoFicha;
    private static FichaDominoPanel selectedFicha = null;

    /**
     * Constructor con los atributos de la fichaDominoPanel. Se dividio en dos
     * hileras con un grid, la row numero 1 es un extremo y la row numero 2 es
     * otro extremo. Se le dio formato y se agregó al panel.
     *
     * @param ficha
     */
    public FichaDominoPanel(FichaDominoDTO ficha) {
        //setPreferredSize(new Dimension(70, 110));
        setLayout(new GridLayout(2, 1, 2, 2));

        this.fichaDominoDTO = ficha;
        fondoFicha = new ImageIcon(getClass().getResource("/multimedia/DominoFondo.png")).getImage();

        labelExtremo1 = new JLabel(cargarImagenPorNumero(ficha.getValorExtremo1()), SwingConstants.CENTER);
        labelExtremo2 = new JLabel(cargarImagenPorNumero(ficha.getValorExtremo2()), SwingConstants.CENTER);

        add(labelExtremo1);
        add(labelExtremo2);
    }

    /**
     * Se encarga de dibujar el contenido visual del panel, incluyendo el fondo
     * de la ficha.
     *
     * @param g Objeto Graphics utilizado para realizar las operaciones de
     * dibujo
     */
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (fondoFicha != null) {
            // Solo dibujar la imagen si hay fondo, pero no pintar el rectángulo
            g.drawImage(fondoFicha, 0, 0, getWidth(), getHeight(), this);
        }
    }

        /**
     * Agrega un listener para manejar la selección de la ficha al hacer clic.
     *
     * @param listener Acción a realizar al seleccionar la ficha
     */
    public void agregarListenerAlSeleccionar(ActionListener listener) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                listener.actionPerformed(new java.awt.event.ActionEvent(FichaDominoPanel.this, java.awt.event.ActionEvent.ACTION_PERFORMED, null));
                selectFicha();
            }
        });
    }

    /**
     * Carga la imagen correspondiente al número del extremo de la ficha.
     *
     * @param numero Número del extremo de la ficha
     * @return Icono de la imagen correspondiente o null si el número es 0
     */
    private Icon cargarImagenPorNumero(int numero) {
        if (numero == 0) {
            return null;
        }
        String nombreImagen = String.format("/multimedia/Domino%d.png", numero);
        return new ImageIcon(getClass().getResource(nombreImagen));
    }

    /**
     * Selecciona la ficha y actualiza el estado de selección.
     */
    private void selectFicha() {
        if (selectedFicha != null && selectedFicha != this) {
            selectedFicha.deselect();
        }
        toggleSelection();
        selectedFicha = this;
    }

    /**
     * Alterna el estado de selección de la ficha.
     */
    private void toggleSelection() {
        isSelected = !isSelected;
        updateBorder();
    }

    /**
     * Deselecciona la ficha y actualiza su estado.
     */
    private void deselect() {
        isSelected = false;
        updateBorder();
    }

    /**
     * Actualiza el borde del panel según el estado de selección.
     */
    private void updateBorder() {
        if (isSelected) {
            setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
        else{
            setBorder(BorderFactory.createEmptyBorder());
        }
        repaint();
    }

    /**
     * Deselecciona todas las fichas.
     */
    public static void deselectAll() {
        if (selectedFicha != null) {
            selectedFicha.deselect();
            selectedFicha = null;
        }
    }
}
