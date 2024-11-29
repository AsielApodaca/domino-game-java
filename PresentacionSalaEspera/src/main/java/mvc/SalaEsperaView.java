package mvc;

import elementosview.UsuarioPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
    private GridBagConstraints gbc;
    private GridBagConstraints gbcContenedorUsuarios;
    private IViewListener viewListener;

    public SalaEsperaView(SalaEsperaModel salaEsperaModel) {
        this.salaEsperaModel = salaEsperaModel;

        // Configuración del layout principal
        setLayout(new GridBagLayout());
        setBackground(new Color(0x0C043A));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        crearLblSalaEspera();
        crearContenedorUsuariosPanel();
        crearLblJugadores();
        crearBotonEmpezarPartida();
        add(contenedorUsuarios, gbc);
        

    }

    /**
     * Crea el botón "Empezar Partida" en la interfaz.
     * Este botón está centrado en el layout y permite iniciar la partida cuando se presiona.
     */
    private void crearBotonEmpezarPartida() {
        btnEmpezarPartida = new JButton("Empezar Partida");

        btnEmpezarPartida.setBackground(Color.WHITE);
        btnEmpezarPartida.setForeground(new Color(0x4F0149));
        btnEmpezarPartida.setFont(new Font("Cairo", Font.BOLD, 14));
        btnEmpezarPartida.setOpaque(true);
        btnEmpezarPartida.setContentAreaFilled(true);

        btnEmpezarPartida.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 7),
                BorderFactory.createEmptyBorder(0, 0, 3, 0)
        ));

        btnEmpezarPartida.setVisible(true);
        btnEmpezarPartida.addActionListener(e -> {
            if (viewListener != null) {
                viewListener.onBtnIniciarPartidaPresionado();
            }
        });

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnEmpezarPartida, gbc);
    }

    /**
     * Crea el panel para mostrar la lista de usuarios (jugadores) en la sala de espera.
     * El panel tiene un fondo con bordes redondeados y se ajusta dinámicamente cuando el tamaño de la ventana cambia.
     */
    private void crearContenedorUsuariosPanel() {
        contenedorUsuarios = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            }
        };
        contenedorUsuarios.setLayout(new BoxLayout(contenedorUsuarios, BoxLayout.Y_AXIS));
        contenedorUsuarios.setBackground(new Color(129, 0, 119, 95));
        contenedorUsuarios.setOpaque(false);

        gbcContenedorUsuarios = new GridBagConstraints();
        gbcContenedorUsuarios.gridx = 0;
        gbcContenedorUsuarios.gridy = 1;
        gbcContenedorUsuarios.weightx = 1.0;
        gbcContenedorUsuarios.weighty = 1.0;
        gbcContenedorUsuarios.fill = GridBagConstraints.BOTH;
        gbcContenedorUsuarios.insets = new Insets(80, 110, 60, 110);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calcular márgenes dinámicos
                int topInset = (int) (0.1 * panelHeight);
                int leftInset = (int) (0.1 * panelWidth);
                int bottomInset = (int) (0.05 * panelHeight);
                int rightInset = (int) (0.1 * panelWidth);
                gbcContenedorUsuarios.insets = new Insets(topInset, leftInset, bottomInset, rightInset);

                remove(contenedorUsuarios); // Remover para actualizar márgenes
                add(contenedorUsuarios, gbcContenedorUsuarios);
                revalidate();
                repaint();
            }
        });
    }

    /**
     * Crea el JLabel para mostrar el texto "Jugadores:" en la interfaz.
     * El texto es centrado y tiene un estilo específico.
     */
    private void crearLblJugadores() {
        labelJugadores = new JLabel("Jugadores:");
        labelJugadores.setForeground(Color.WHITE);
        labelJugadores.setFont(new Font("Cairo", Font.PLAIN, 18));
        labelJugadores.setHorizontalAlignment(SwingConstants.CENTER);  
        labelJugadores.setBorder(BorderFactory.createEmptyBorder(100, 0, 1, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor =  GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        add(labelJugadores, gbc);
    }

    /**
     * Crea el JLabel para mostrar el título "Sala de Espera".
     * El texto es centrado y tiene un estilo destacado.
     */
    private void crearLblSalaEspera() {
        labelSalaEspera = new JLabel("Sala de Espera");
        labelSalaEspera.setForeground(Color.WHITE);
        labelSalaEspera.setFont(new Font("Cairo", Font.BOLD, 30));
        labelSalaEspera.setHorizontalAlignment(SwingConstants.CENTER);
        labelSalaEspera.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

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
     * Método para actualizar la vista de la pantalla de la sala de espera.
     * Este método repinta tanto el fondo como la lista de jugadores.
     */
    public void suscribirListener(IViewListener viewListener) {
        this.viewListener = viewListener;
    }
}
