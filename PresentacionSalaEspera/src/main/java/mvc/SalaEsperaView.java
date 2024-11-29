package mvc;

import elementosview.UsuarioPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
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
    private IViewListener viewListener;

    public SalaEsperaView(SalaEsperaModel salaEsperaModel) {
        this.salaEsperaModel = salaEsperaModel;

        // Configuración del layout principal
        setLayout(new GridBagLayout());
        setBackground(new Color(0x0C043A));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear y agregar componentes
        crearLblSalaEspera();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelSalaEspera, gbc);

        crearContenedorUsuariosPanel();
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(contenedorUsuarios, gbc);

        crearLblJugadores();
        contenedorUsuarios.add(labelJugadores);

        crearBotonEmpezarPartida();
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(btnEmpezarPartida, gbc);
    }

    private void crearBotonEmpezarPartida() {
        btnEmpezarPartida = new JButton("Empezar Partida");
        btnEmpezarPartida.setBackground(Color.WHITE);
        btnEmpezarPartida.setForeground(new Color(0x4F0149));
        btnEmpezarPartida.setFont(new Font("Cairo", Font.BOLD, 14));
        btnEmpezarPartida.setVisible(true); // Asegurarse de que sea visible
        btnEmpezarPartida.addActionListener(e -> {
            if (viewListener != null) {
                viewListener.onBtnIniciarPartidaPresionado();
            }
        });
    }

    private void crearContenedorUsuariosPanel() {
        contenedorUsuarios = new JPanel();
        contenedorUsuarios.setLayout(new BoxLayout(contenedorUsuarios, BoxLayout.Y_AXIS));
        contenedorUsuarios.setBackground(new Color(0x810077));
        contenedorUsuarios.setOpaque(true); // Asegurarse de que sea opaco
        contenedorUsuarios.setBorder(new RoundedBorder(10, new Color(0x810077)));
    }

    private void crearLblJugadores() {
        labelJugadores = new JLabel("Jugadores:");
        labelJugadores.setForeground(Color.WHITE);
        labelJugadores.setFont(new Font("Cairo", Font.BOLD, 18));
        labelJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelJugadores.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
    }

    private void crearLblSalaEspera() {
        labelSalaEspera = new JLabel("Sala de Espera:");
        labelSalaEspera.setForeground(Color.WHITE);
        labelSalaEspera.setFont(new Font("Cairo", Font.BOLD, 30));
        labelSalaEspera.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSalaEspera.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
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
     * Clase interna para crear bordes redondeados utilizando Graphics2D.
     */
    private static class RoundedBorder extends AbstractBorder {

        private int radius;
        private Color backgroundColor;

        public RoundedBorder(int radius, Color backgroundColor) {
            this.radius = radius;
            this.backgroundColor = backgroundColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(backgroundColor);
            g.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(radius, radius, radius, radius);
            return insets;
        }
    }

    public void suscribirListener(IViewListener viewListener) {
        this.viewListener = viewListener;
    }
}
