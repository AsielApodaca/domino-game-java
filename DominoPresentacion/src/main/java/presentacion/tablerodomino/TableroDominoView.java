/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tablerodomino;

import dominio.FichaDomino;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import presentacion.tablerodomino.fichadomino.FichaDominoPanel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoView extends JPanel implements ITableroDominoModeloListener {

    private TableroDominoModel tableroDominoModel;
    private List<FichaDominoPanel> fichasDominoUsuario;
    private List<FichaDominoPanel> fichasDominoComparativa;
    private JPanel fichaUsuarioPanel;
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

        // Crear paneles para las fichas del usuario y comparativas
        fichaUsuarioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Añadir un margen inferior de 10 píxeles al panel de fichas del usuario
        fichaUsuarioPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        fichaComparativaPanel = new JPanel(new GridBagLayout());

        add(fichaUsuarioPanel, BorderLayout.SOUTH);
        add(fichaComparativaPanel, BorderLayout.CENTER);

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

        revalidate();
        repaint();
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

    public JPanel getFichaUsuarioPanel() {
        return fichaUsuarioPanel;
    }

    public JPanel getFichaComparativaPanel() {
        return fichaComparativaPanel;
    }

    public List<FichaDominoPanel> getFichasDominoUsuario() {
        return fichasDominoUsuario;
    }

    public List<FichaDominoPanel> getFichasDominoComparativa() {
        return fichasDominoComparativa;
    }

}
