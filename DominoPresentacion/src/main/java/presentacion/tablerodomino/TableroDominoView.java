package presentacion.tablerodomino;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
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
    private final JPanel mesaDominoPanel;
    private JPanel fichaComparativaPanel;
    private BufferedImage fondoPantalla;

    public TableroDominoView(TableroDominoModel tableroDominoModel) {
        this.tableroDominoModel = tableroDominoModel;
        this.fichasDominoUsuario = new ArrayList<>();

        // Configurar layout y tamaño dinámico basado en resolución de pantalla
        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setPreferredSize(new Dimension((int) (screenWidth * 0.8), (int) (screenHeight * 0.8)));

        // Cargar la imagen de fondo
        try {
            fondoPantalla = ImageIO.read(getClass().getResource("/multimedia/FondoPartida.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear el panel MesaDominoPanel
        mesaDominoPanel = new MesaDominoPanel();
        mesaDominoPanel.setOpaque(true);  // Hacer el panel transparente

        fichaComparativaPanel = new JPanel(new GridBagLayout()); // Crear el panel comparativo
        fichaComparativaPanel.setOpaque(false);  // Hacer el panel transparente

        // Crear paneles para las fichas del usuario
        fichaUsuarioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        fichaUsuarioPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        fichaUsuarioPanel.setOpaque(false);  // Hacer el panel transparente
        

        // Agregar el panel MesaDominoPanel al centro
        add(mesaDominoPanel, BorderLayout.CENTER);

        add(fichaUsuarioPanel, BorderLayout.SOUTH);

        tableroDominoModel.addListener(this);
        tableroDominoModel.repartirFichas();

        setOpaque(false);  // Hacer este panel transparente para que se vea el fondo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondoPantalla != null) {
            g.drawImage(fondoPantalla, 0, 0, getWidth(), getHeight(), this);
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
    public void onChangeListaFichasUsuario(List<FichaDominoDTO> listaFichasUsuario) {
        fichasDominoUsuario.clear();
        fichaUsuarioPanel.removeAll();

        for (FichaDominoDTO fichaDomino : listaFichasUsuario) {
            FichaDominoPanel fichaDominoPanel = new FichaDominoPanel(fichaDomino);
            fichasDominoUsuario.add(fichaDominoPanel);
            fichaUsuarioPanel.add(fichaDominoPanel);
            fichaDominoPanel.agregarListenerAlSeleccionar(e -> {
                System.out.println("Ficha seleccionada: " + fichaDomino.getValorExtremo1() + " - " + fichaDomino.getValorExtremo2());
            });
        }

        revalidate();
        repaint();
    }

    @Override
    public void onChangeFichaComparativa(FichaDominoDTO fichaComparativaModel) {
        fichaComparativaPanel.removeAll();

        fichaComparativaPanel = new FichaDominoPanel(fichaComparativaModel);
        fichaComparativaPanel.add(fichaComparativaPanel);

        revalidate();
        repaint();
    }

    @Override
    public void onChangeFichasComparativas(List<FichaDominoDTO> listaFichasComparativas) {

    }

    @Override
    public void onChangeFichaSeleccionada(FichaDominoDTO fichaSeleccionada) {

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
