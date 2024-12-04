package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

    /**
     * Carga los componentes de la vista, incluyendo las imágenes y la configuración de los botones.
     */
    private void cargarComponentes() {
        setLayout(null);
        setOpaque(true);
        cargarImagenes();

        configurarComponentes();
        configurarLayout();

        repintarPanel();
    }

    /**
     * Configura los componentes visuales, como los botones y el logo.
     */
    private void configurarComponentes() {
        panelBtnUnirseSala = crearPanelConImagen(fondoBtnUnirseSala, BOTON_DIMENSION);
        panelBtnCrearSala = crearPanelConImagen(fondoBtnCrearSala, BOTON_DIMENSION);
        logo = crearLabelConImagen(dotMino, LOGO_DIMENSION);
        addMouseListenerToUnirseSala();
        addMouseListenerToCrearSala();
    }

    /**
     * Configura el layout de la vista, ajustando dinámicamente la posición de los botones y el logo.
     */
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
                        botonCrearY - 45,
                        BOTON_DIMENSION.width,
                        BOTON_DIMENSION.height);

                // Posicionar el botón "Unirse Sala"
                panelBtnUnirseSala.setBounds(
                        botonCrearX,
                        botonCrearY - 105,
                        BOTON_DIMENSION.width,
                        BOTON_DIMENSION.height);

                // Posicionar el logo
                int logoX = (getWidth() - LOGO_DIMENSION.width) / 2;
                logo.setBounds(logoX, 90, LOGO_DIMENSION.width, LOGO_DIMENSION.height);

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

    /**
     * Sobrescribe el método de pintura para dibujar el fondo y los elementos visuales.
     * 
     * @param g El objeto Graphics utilizado para pintar la vista.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if (fondoPantalla != null) {
            g2d.setBackground(new Color(12, 4, 58));
            g2d.fillRect(0, 0, getWidth(), getHeight()); // Asegurarte de rellenar el fondo antes de dibujar la imagen
            g2d.drawImage(fondoPantalla, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Repinta el panel con el tamaño adecuado según la escala definida en el modelo.
     */
    private void repintarPanel() {
        int ancho = (int) (menuDominoModel.getAnchoPantalla() * menuDominoModel.getEscala());
        int altura = (int) (menuDominoModel.getAlturaPantalla() * menuDominoModel.getEscala());
        setPreferredSize(new Dimension(ancho, altura));

        revalidate();
        repaint();
    }

     /**
     * Carga las imágenes utilizadas en la interfaz.
     */
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

    /**
     * Crea un panel con una imagen de fondo.
     * 
     * @param imagen La imagen que se dibujará en el panel.
     * @param dimension Las dimensiones del panel.
     * @return El panel con la imagen de fondo.
     */
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

     /**
     * Crea una etiqueta con una imagen de fondo.
     * 
     * @param imagen La imagen que se dibujará en la etiqueta.
     * @param dimension Las dimensiones de la etiqueta.
     * @return La etiqueta con la imagen de fondo.
     */
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

    /**
     * Agrega un listener al botón "Crear Sala" para manejar el evento de clic.
     */
    public void addMouseListenerToCrearSala() {
        panelBtnCrearSala.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notificarBtnCrearSala();
            }
        });
    }

    /**
     * Agrega un listener al botón "Unirse Sala" para manejar el evento de clic.
     */
    public void addMouseListenerToUnirseSala() {
        panelBtnUnirseSala.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notificarBtnUnirseSala();
            }
        });
    }

    /**
     * Suscribe un listener a los eventos de la vista.
     * 
     * @param viewListener El listener a suscribir.
     */
    public void suscribirListener(IMenuDominoViewListener viewListener) {
        this.viewListener = viewListener;
    }

    /**
     * Notifica al listener que se hizo clic en el botón "Crear Sala".
     */
    private void notificarBtnCrearSala() {
        viewListener.onBtnCrearSala();
    }

    /**
     * Notifica al listener que se hizo clic en el botón "Unirse Sala".
     */
    private void notificarBtnUnirseSala() {
        viewListener.onBtnUnirseSala();
    }
}
