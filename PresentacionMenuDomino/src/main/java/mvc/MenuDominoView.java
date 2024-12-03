package mvc;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import listeners.IMenuDominoViewListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MenuDominoView extends JPanel {

    private MenuDominoModel menuDominoModel;

    private static final Dimension BOTON_DIMENSION = new Dimension(263, 47);
    private static final Dimension LOGO_DIMENSION = new Dimension(500, 86);

    private JPanel panelBtnCrearSala;
    private JPanel panelBtnUnirseSala;
    private JLabel logo;

    private BufferedImage fondoPantalla;
    private BufferedImage fondoBtnUnirseSala;
    private BufferedImage fondoBtnCrearSala;
    private BufferedImage dotMino;

    private IMenuDominoViewListener viewListener;

    public MenuDominoView(MenuDominoModel menuDominoModel) {
        this.menuDominoModel = menuDominoModel;
        cargarComponentes();
        revalidate();
        repaint();
    }

    private void cargarComponentes() {
        setLayout(null);
        setOpaque(false);
        cargarImagenes();

        configurarComponentes();
        configurarLayout();

        repintarPanel();
    }

    private void configurarComponentes() {
        panelBtnUnirseSala = crearPanelConImagen(fondoBtnUnirseSala, BOTON_DIMENSION);
        panelBtnCrearSala = crearPanelConImagen(fondoBtnCrearSala, BOTON_DIMENSION);
        logo = crearLabelConImagen(dotMino, LOGO_DIMENSION);
        addMouseListenerToUnirseSala();
        addMouseListenerToCrearSala();
    }

    private void configurarLayout() {
         // Agregar un listener para ajustar el layout dinámicamente
    addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentResized(java.awt.event.ComponentEvent e) {
            int botonCrearX = (getWidth() - BOTON_DIMENSION.width) / 2;
            int botonCrearY = getHeight() - BOTON_DIMENSION.height;

            // Posicionar el botón "Crear Sala"
            panelBtnCrearSala.setBounds(
                    botonCrearX,
                    botonCrearY - 20,
                    BOTON_DIMENSION.width,
                    BOTON_DIMENSION.height);

            // Posicionar el botón "Unirse Sala"
            panelBtnUnirseSala.setBounds(
                    botonCrearX,
                    botonCrearY - 80,
                    BOTON_DIMENSION.width,
                    BOTON_DIMENSION.height);

            // Posicionar el logo
            int logoX = (getWidth() - LOGO_DIMENSION.width) / 2;
            logo.setBounds(logoX, 20, LOGO_DIMENSION.width, LOGO_DIMENSION.height);

            // Actualizar el layout visualmente
            revalidate();
            repaint();
        }
    });

    // Agregar los componentes al contenedor
    add(panelBtnCrearSala);
    add(panelBtnUnirseSala);
    add(logo);

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (fondoPantalla != null) {
            g.drawImage(fondoPantalla, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void repintarPanel() {
        int ancho = (int) (menuDominoModel.getAnchoPantalla() * menuDominoModel.getEscala());
        int altura = (int) (menuDominoModel.getAlturaPantalla() * menuDominoModel.getEscala());
        setPreferredSize(new Dimension(ancho, altura));

        revalidate();
        repaint();
    }

    private void cargarImagenes() {
        try {
            fondoPantalla = ImageIO.read(getClass().getResource(menuDominoModel.getFondoDePantalla()));
            fondoBtnUnirseSala = ImageIO.read(getClass().getResource(menuDominoModel.getBtnUnirseSala()));
            fondoBtnCrearSala = ImageIO.read(getClass().getResource(menuDominoModel.getBtnCrearSala()));
            dotMino = ImageIO.read(getClass().getResource(menuDominoModel.getLogo()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JPanel crearPanelConImagen(BufferedImage imagen, Dimension dimension) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (imagen != null) {
                    g.drawImage(imagen, 0, 0, dimension.width, dimension.height, this);
                }
            }
        };
        panel.setPreferredSize(dimension);
        return panel;
    }

    private JLabel crearLabelConImagen(BufferedImage imagen, Dimension dimension) {
        JLabel label = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (imagen != null) {
                    g.drawImage(imagen, 0, 0, dimension.width, dimension.height, this);
                }
            }
        };
        label.setPreferredSize(dimension);
        return label;
    }

    public void addMouseListenerToCrearSala() {
        panelBtnCrearSala.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notificarBtnCrearSala();
            }
        });
    }

    public void addMouseListenerToUnirseSala() {
        panelBtnUnirseSala.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notificarBtnUnirseSala();
            }
        });
    }

    public void suscribirListener(IMenuDominoViewListener viewListener) {
        this.viewListener = viewListener;
    }

    private void notificarBtnCrearSala() {
        viewListener.onBtnCrearSala();
    }

    private void notificarBtnUnirseSala() {
        viewListener.onBtnUnirseSala();
    }
}
