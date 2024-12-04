package mvc;

import elementosview.UsuarioPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import listeners.IViewListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SalaEsperaView extends JPanel {

    private SalaEsperaModel salaEsperaModel;
    private JPanel contenedorUsuarios;
    private JLabel labelSalaEspera;
    private JLabel labelJugadores;
    private JButton btnEmpezarPartida;
    private JButton btnAbandonarSala;
    private IViewListener viewListener;
    private GridBagConstraints gbc;

    public SalaEsperaView(SalaEsperaModel salaEsperaModel) {
        this.salaEsperaModel = salaEsperaModel;
        setLayout(new GridBagLayout());
        setBackground(new Color(0x0C043A));
        gbc = new GridBagConstraints();
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(0x0C043A));
        crearLblSalaEspera();
        crearContenedorUsuariosPanel();
        crearLblJugadores();
        crearBotonesPanel();

    }

    private void crearBotonesPanel() {
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botonesPanel.setBackground(new Color(0, 0, 0, 0));

        btnEmpezarPartida = crearBotonPersonalizado("Empezar Partida", new Color(255, 255, 255), 222, 45);
        btnAbandonarSala = crearBotonPersonalizado("Salir", new Color(253, 58, 105), 160, 45);

        addListenerBtnEmpezarPartida();
        addListenerBtnAbandonarPartida();

        botonesPanel.add(btnEmpezarPartida);
        botonesPanel.add(btnAbandonarSala);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(botonesPanel, gbc);
    }

    private JButton crearBotonPersonalizado(String texto, Color color, int ancho, int alto) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        boton.setPreferredSize(new Dimension(ancho, alto));
        boton.setBackground(color);
        if (texto.equalsIgnoreCase("Empezar Partida")) {
            boton.setForeground(new Color(79, 1, 73));
        } else {
            boton.setForeground(Color.WHITE);
        }

        boton.setFont(new Font("Cairo", Font.BOLD, 14));
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        return boton;
    }

    private void addListenerBtnAbandonarPartida() {
        btnAbandonarSala.addActionListener(e -> {
            if (viewListener != null) {
                viewListener.onBtnSalirPresionado();
            }
        });
    }

    private void addListenerBtnEmpezarPartida() {
        btnEmpezarPartida.addActionListener(e -> {
            if (viewListener != null) {
                viewListener.onBtnIniciarPartidaPresionado();
            }
        });
    }

    /**
     * Crea el panel para mostrar la lista de usuarios (jugadores) en la sala de
     * espera. El panel tiene un fondo con bordes redondeados y se ajusta
     * dinámicamente cuando el tamaño de la ventana cambia.
     */
    private void crearContenedorUsuariosPanel() {
        contenedorUsuarios = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(129, 0, 119, 95));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };

        contenedorUsuarios.setLayout(new GridLayout(1, 4, 20, 0));
        contenedorUsuarios.setBackground(new Color(0, 0, 0, 0));
        contenedorUsuarios.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0;
        add(contenedorUsuarios, gbc);
    }

    /**
     * Crea el JLabel para mostrar el texto "Jugadores:" en la interfaz. El
     * texto es centrado y tiene un estilo específico.
     */
    private void crearLblJugadores() {
        labelJugadores = new JLabel("Jugadores:");
        labelJugadores.setForeground(Color.WHITE);
        labelJugadores.setFont(new Font("Cairo", Font.PLAIN, 18));
        labelJugadores.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        add(labelJugadores, gbc);
    }

    /**
     * Crea el JLabel para mostrar el título "Sala de Espera". El texto es
     * centrado y tiene un estilo destacado.
     */
    private void crearLblSalaEspera() {
        labelSalaEspera = new JLabel("Sala de Espera");
        labelSalaEspera.setForeground(Color.WHITE);
        labelSalaEspera.setFont(new Font("Cairo", Font.BOLD, 30));
        labelSalaEspera.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 1, 0);
        gbc.fill = GridBagConstraints.NONE;
        add(labelSalaEspera, gbc);
    }

    /**
     * Método para actualizar la vista de la pantalla de la sala de espera.
     * Incluye la actualización del fondo y la lista de usuarios.
     */
    public void repintarPantalla() {
        repintarFondo();
        repintarJugadores();
        revalidate();
        repaint();
    }

    /**
     * Configura el color de fondo del panel de la sala de espera.
     */
    private void repintarFondo() {
        setBackground(new Color(0x0C043A));
    }

    /**
     * Redibuja los paneles de usuario dentro del contenedor de usuarios.
     */
    public void repintarJugadores() {
        contenedorUsuarios.removeAll();

        List<UsuarioPanel> usuariosPaneles = salaEsperaModel.getListaUsuariosPaneles();
        for (UsuarioPanel usuarioPanel : usuariosPaneles) {
            contenedorUsuarios.add(usuarioPanel);
        }

        contenedorUsuarios.revalidate();
        contenedorUsuarios.repaint();
    }

    /**
     * Método para actualizar la vista de la pantalla de la sala de espera. Este
     * método repinta tanto el fondo como la lista de jugadores.
     */
    public void suscribirListener(IViewListener viewListener) {
        this.viewListener = viewListener;
    }
}
