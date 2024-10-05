package presentacion.tablerodomino;

import dominio.FichaDomino;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import presentacion.tablerodomino.fichadomino.FichaDominoPanel;
import presentacion.tablerodomino.mesadomino.MesaDominoPanel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoView extends JPanel implements ITableroDominoModeloListener {

    private final TableroDominoModel tableroDominoModel;
    private final List<FichaDominoPanel> fichasDominoUsuario;
    private final JPanel fichaUsuarioPanel;
    private final MesaDominoPanel mesaDominoPanel;
    private JPanel fichaComparativaPanel;

    public TableroDominoView(TableroDominoModel tableroDominoModel) {
        this.tableroDominoModel = tableroDominoModel;
        this.fichasDominoUsuario = new ArrayList<>();

        // Configurar layout y tamaño dinámico basado en resolución de pantalla
        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setPreferredSize(new Dimension((int) (screenWidth * 0.8), (int) (screenHeight * 0.8)));

        // Crear el panel MesaDominoPanel
        mesaDominoPanel = new MesaDominoPanel();
        fichaComparativaPanel = new JPanel(new GridBagLayout()); // Crear el panel comparativo

        // Crear paneles para las fichas del usuario
        fichaUsuarioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        fichaUsuarioPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Agregar el panel MesaDominoPanel al centro
        add(mesaDominoPanel, BorderLayout.CENTER);

        // Agregar el fichaComparativaPanel al mesaDominoPanel
        mesaDominoPanel.addFichaComparativaPanel(fichaComparativaPanel);

        add(fichaUsuarioPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();

        tableroDominoModel.addListener(this);
        tableroDominoModel.repartirFichas();
        
    }

    private void crearFichasVisuales() {
        fichaUsuarioPanel.removeAll();

        for (FichaDomino ficha : tableroDominoModel.getListaFichasUsuario()) {
            FichaDominoPanel fichaDominoPanel = new FichaDominoPanel(ficha);
            fichasDominoUsuario.add(fichaDominoPanel);
            fichaUsuarioPanel.add(fichaDominoPanel);
            fichaDominoPanel.agregarListenerAlSeleccionar(e -> {
                System.out.println("Ficha seleccionada: " + ficha.getExtremo1() + " - " + ficha.getExtremo2());
            });
        }

      
    }

    /**
     *
     *
     * @param listener
     */
    public void setSeleccionarFichaListener(ActionListener listener) {
        for (FichaDominoPanel fichaDominoPanel : fichasDominoUsuario) {
            fichaDominoPanel.agregarListenerAlSeleccionar(listener);
        }
    }

    @Override
    public void onChangeListaFichasUsuario(List<FichaDomino> listaFichasUsuario) {
        SwingUtilities.invokeLater(() -> {
            fichasDominoUsuario.clear();
            fichaUsuarioPanel.removeAll();

            for (FichaDomino fichaDomino : listaFichasUsuario) {
                FichaDominoPanel fichaDominoPanel = new FichaDominoPanel(fichaDomino);
                fichasDominoUsuario.add(fichaDominoPanel);
                fichaUsuarioPanel.add(fichaDominoPanel);
                fichaDominoPanel.agregarListenerAlSeleccionar(e -> {
                    System.out.println("Ficha seleccionada: " + fichaDomino.getExtremo1() + " - " + fichaDomino.getExtremo2());
                });
            }

            revalidate();
            repaint();
        });
    }

    @Override
    public void onChangeFichaComparativa(FichaDomino fichaComparativaModel) {
        SwingUtilities.invokeLater(() -> {
            fichaComparativaPanel.removeAll();

            fichaComparativaPanel = new FichaDominoPanel(fichaComparativaModel);
            fichaComparativaPanel.add(fichaComparativaPanel);

            revalidate();
            repaint();
        });
    }

    @Override
    public void onChangeFichasComparativas(List<FichaDomino> listaFichasComparativas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onChangeFichaSeleccionada(FichaDomino fichaSeleccionada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public JPanel getFichaUsuarioPanel() {
        return fichaUsuarioPanel;
    }

    public JPanel getFichaComparativaPanel() {
        return fichaComparativaPanel;
    }

    public List<FichaDominoPanel> getFichasDominoUsuario() {
        return fichasDominoUsuario;
    }

}
