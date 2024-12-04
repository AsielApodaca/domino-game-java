package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import listeners.ICrearUsuarioViewListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class CrearUsuarioView extends JPanel {

    private static final Dimension BOTON_DIMENSION = new Dimension(30, 30);
    private CrearUsuarioModel crearUsuarioModel;
    private JPanel panelBtnAnterior;
    private JPanel panelBtnSiguiente;
    private JButton btnEmpezar;
    private JLabel tituloJuego;
    private JLabel iconoLabel;
    private JTextField txtNombreUsuario;
    private ICrearUsuarioViewListener viewListener;
    private JPanel panelContenedor;
    private JLabel labelSeleccionarPersonaje;
    private JLabel labelUsuario;

    private BufferedImage tituloImg;
    private BufferedImage iconoActual;
    private BufferedImage btnAntImg;
    private BufferedImage btnSigImg;
    private float escala = 1.0f;

    /**
     * Constructor de la vista CrearUsuarioView. Inicializa los componentes
     * principales y configura el diseño, las imagenes y el manejo de
     * redimensionamiento.
     *
     * @param crearUsuarioModel Modelo que contiene la logica y datos necesarios
     * para esta vista.
     */
    public CrearUsuarioView(CrearUsuarioModel crearUsuarioModel) {
        this.crearUsuarioModel = crearUsuarioModel;
        setLayout(null);
        setBackground(new Color(0x1A0B2E));

        inicializarComponentes();
        cargarImagenes();
        configurarResizing();
        addListenerToBtnEmpezar();

        revalidate();
        repaint();
    }

    /**
     * Inicializa y configura los componentes de la vista. Incluye la creación
     * de etiquetas, campos de texto y paneles interactivos.
     */
    private void inicializarComponentes() {
        // Panel contenedor principal
        panelContenedor = new JPanel();
        panelContenedor.setLayout(null);
        panelContenedor.setBackground(new Color(0x4A0D67));
        add(panelContenedor);

        // Título del juego
        tituloJuego = new JLabel();
        tituloJuego.setHorizontalAlignment(SwingConstants.LEFT);
        add(tituloJuego);

        // Label para seleccionar personaje
        labelSeleccionarPersonaje = new JLabel("Seleccionar personaje");
        labelSeleccionarPersonaje.setForeground(Color.WHITE);
        labelSeleccionarPersonaje.setFont(new Font("Cairo", Font.BOLD, 14));
        labelSeleccionarPersonaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelContenedor.add(labelSeleccionarPersonaje);

        // Label para usuario
        labelUsuario = new JLabel("Usuario:");
        labelUsuario.setForeground(Color.WHITE);
        labelUsuario.setFont(new Font("Cairo", Font.BOLD, 14));
        panelContenedor.add(labelUsuario);

        // Campo de texto para nombre de usuario
        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBackground(new Color(0x2A0D37));
        txtNombreUsuario.setForeground(Color.WHITE);
        txtNombreUsuario.setCaretColor(Color.WHITE);
        txtNombreUsuario.setBorder(BorderFactory.createLineBorder(new Color(0x6A1D87), 1));
        panelContenedor.add(txtNombreUsuario);

        // Icono del jugador
        iconoLabel = new JLabel();
        iconoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconoLabel.setVerticalAlignment(SwingConstants.CENTER);
        iconoLabel.setOpaque(false);
        panelContenedor.add(iconoLabel);

        crearBotonesNavegacion();
        crearBotonEmpezar();
    }

    /**
     * Crea y configura los botones de navegación para seleccionar personajes.
     */
    private void crearBotonesNavegacion() {
        panelBtnAnterior = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (btnAntImg != null) {
                    g.drawImage(btnAntImg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panelBtnAnterior.setOpaque(false);
        panelBtnAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                crearUsuarioModel.anteriorIcono();
                actualizarIcono();
            }
        });
        panelContenedor.add(panelBtnAnterior);

        panelBtnSiguiente = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (btnSigImg != null) {
                    g.drawImage(btnSigImg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panelBtnSiguiente.setOpaque(false);
        panelBtnSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                crearUsuarioModel.siguienteIcono();
                actualizarIcono();
            }
        });
        panelContenedor.add(panelBtnSiguiente);
    }

    /**
     * Carga las imágenes necesarias para la vista, incluyendo íconos y botones.
     */
    private void cargarImagenes() {
        try {
            tituloImg = ImageIO.read(getClass().getResource(crearUsuarioModel.getTituloJuego()));
            iconoActual = ImageIO.read(getClass().getResource(crearUsuarioModel.getIconoActual()));
            btnAntImg = ImageIO.read(getClass().getResource(crearUsuarioModel.getBtnAnterior()));
            btnSigImg = ImageIO.read(getClass().getResource(crearUsuarioModel.getBtnSiguiente()));
            actualizarIcono();
        } catch (IOException e) {
            System.out.println("Error al cargar las imágenes: " + e.getMessage());
        }
    }

    /**
     * Crea y configura el botón "Empezar". Este botón inicia la acción
     * configurada en el listener cuando se proporciona un nombre de usuario.
     */
    private void crearBotonEmpezar() {
        btnEmpezar = new JButton("Empezar");
        btnEmpezar.setBackground(Color.WHITE);  // Fondo blanco
        btnEmpezar.setForeground(new Color(0x4F0149));  // Texto morado
        btnEmpezar.setFont(new Font("Cairo", Font.BOLD, 14));
        btnEmpezar.setOpaque(true);
        btnEmpezar.setContentAreaFilled(true);
        btnEmpezar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));  // Borde blanco
        btnEmpezar.setFocusPainted(false);
        panelContenedor.add(btnEmpezar);
    }

    /**
     * Agrega un ActionListener al botón "Empezar" que valida el nombre de
     * usuario y notifica al viewListener si es válido.
     */
    private void addListenerToBtnEmpezar() {
        btnEmpezar.addActionListener(e -> {
            if (viewListener != null && !txtNombreUsuario.getText().trim().isEmpty()) {
                viewListener.onBtnEmpezarPresionado(txtNombreUsuario.getText());
            }
        });
    }

    /**
     * Configura el manejo del redimensionamiento de la ventana para ajustar los
     * elementos dinámicamente.
     */
    private void configurarResizing() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarLayout();
            }
        });
    }

    /**
     * Actualiza el diseño y las posiciones de los componentes en función del
     * tamaño actual de la ventana.
     */
    private void actualizarLayout() {
        escala = Math.min(
                getWidth() / (float) crearUsuarioModel.getAnchoPantalla(),
                getHeight() / (float) crearUsuarioModel.getAlturaPantalla()
        );

        // Título
        int tituloX = (int) (crearUsuarioModel.getTituloJuegoLocacionX() * escala);
        int tituloY = (int) (crearUsuarioModel.getTituloJuegoLocacionY() * escala);
        int tituloAncho = (int) (crearUsuarioModel.getAnchoTituloJuego() * escala);
        int tituloAlto = (int) (crearUsuarioModel.getAltoTituloJuego() * escala);
        tituloJuego.setBounds(tituloX, tituloY, tituloAncho, tituloAlto);

        // Panel contenedor (más pequeño)
        int panelAncho = (int) (350 * escala); // Reducido de 400 a 350
        int panelAlto = (int) (250 * escala);  // Reducido de 300 a 250
        panelContenedor.setBounds(
                (getWidth() - panelAncho) / 2,
                (getHeight() - panelAlto) / 2,
                panelAncho,
                panelAlto
        );

        actualizarPosicionesIconoYNavegacion(panelAncho, panelAlto);
        actualizarPosicionesLabels(panelAncho, panelAlto);
        actualizarPosicionesBoton(panelAncho, panelAlto);

        repaint();
    }

    /**
     * Actualiza la posición de las etiquetas y el campo de texto dentro del
     * panel contenedor.
     *
     * @param panelAncho Ancho actual del panel.
     * @param panelAlto Alto actual del panel.
     */
    private void actualizarPosicionesLabels(int panelAncho, int panelAlto) {
        // Ajustar el ancho del texto "Seleccionar personaje" para que quepa completo
        int anchoSeleccion = (int) (180 * escala);
        int altoSeleccion = (int) (20 * escala);
        labelSeleccionarPersonaje.setBounds(
                (panelAncho - anchoSeleccion) / 2,
                (int) (panelAlto * 0.45),
                anchoSeleccion,
                altoSeleccion
        );

        // Centrar el conjunto de "Usuario:" y el campo de texto
        int txtWidth = (int) (180 * escala);
        int labelWidth = (int) (70 * escala);
        int labelHeight = (int) (25 * escala);
        int spacing = (int) (15 * escala); // Espacio entre label y campo de texto
        int totalWidth = labelWidth + spacing + txtWidth;
        int startX = (panelAncho - totalWidth) / 2;

        // Posicionar el label
        labelUsuario.setBounds(
                startX,
                (int) (panelAlto * 0.6),
                labelWidth,
                labelHeight
        );

        // Posicionar el campo de texto con el espaciado correcto
        txtNombreUsuario.setBounds(
                startX + labelWidth + spacing,
                (int) (panelAlto * 0.6),
                txtWidth,
                labelHeight
        );
    }

    /**
     * Actualiza la posición del ícono y los botones de navegación.
     *
     * @param panelAncho Ancho actual del panel.
     * @param panelAlto Alto actual del panel.
     */
    private void actualizarPosicionesIconoYNavegacion(int panelAncho, int panelAlto) {
        int iconoAncho = (int) (50 * escala);
        int iconoAlto = (int) (50 * escala);
        int iconoY = (int) (panelAlto * 0.25);
        int iconoX = (panelAncho - iconoAncho) / 2;

        iconoLabel.setBounds(iconoX, iconoY, iconoAncho, iconoAlto);

        if (iconoActual != null) {
            Image scaledIcon = iconoActual.getScaledInstance(iconoAncho, iconoAlto, Image.SCALE_SMOOTH);
            iconoLabel.setIcon(new ImageIcon(scaledIcon));
        }

        int btnSize = (int) (25 * escala);
        panelBtnAnterior.setBounds(
                iconoX - btnSize - 10,
                iconoY + (iconoAlto - btnSize) / 2,
                btnSize,
                btnSize
        );

        panelBtnSiguiente.setBounds(
                iconoX + iconoAncho + 10,
                iconoY + (iconoAlto - btnSize) / 2,
                btnSize,
                btnSize
        );
    }

    /**
     * Actualiza la posición del botón "Empezar".
     *
     * @param panelAncho Ancho actual del panel.
     * @param panelAlto Alto actual del panel.
     */
    private void actualizarPosicionesBoton(int panelAncho, int panelAlto) {
        int btnWidth = (int) (120 * escala);
        int btnHeight = (int) (30 * escala);
        btnEmpezar.setBounds(
                (panelAncho - btnWidth) / 2,
                (int) (panelAlto * 0.8),
                btnWidth,
                btnHeight
        );
    }

    /**
     * Actualiza el ícono mostrado actualmente en la vista.
     */
    private void actualizarIcono() {
        try {
            iconoActual = ImageIO.read(getClass().getResource(crearUsuarioModel.getIconoActual()));
            if (iconoActual != null) {
                int iconoAncho = (int) (50 * escala);
                int iconoAlto = (int) (50 * escala);
                Image scaledIcon = iconoActual.getScaledInstance(iconoAncho, iconoAlto, Image.SCALE_SMOOTH);
                iconoLabel.setIcon(new ImageIcon(scaledIcon));
            }
            iconoLabel.repaint();
        } catch (IOException e) {
            System.out.println("Error al actualizar icono: " + e.getMessage());
        }
    }

    /**
     * Método para actualizar la vista de la pantalla de la sala de espera. Este
     * método repinta tanto el fondo como la lista de jugadores.
     */
    public void suscribirListener(ICrearUsuarioViewListener viewListener) {
        this.viewListener = viewListener;
    }

    /**
     * Sobrescribe el método paintComponent para dibujar elementos
     * personalizados, como el título del juego.
     *
     * @param g Objeto Graphics para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tituloImg != null) {
            g.drawImage(tituloImg,
                    (int) (crearUsuarioModel.getTituloJuegoLocacionX() * escala),
                    (int) (crearUsuarioModel.getTituloJuegoLocacionY() * escala),
                    (int) (crearUsuarioModel.getAnchoTituloJuego() * escala),
                    (int) (crearUsuarioModel.getAltoTituloJuego() * escala),
                    this);
        }
    }
}
