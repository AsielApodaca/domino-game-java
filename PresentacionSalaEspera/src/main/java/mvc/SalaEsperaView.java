package mvc;

import elementosview.UsuarioPanel;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.border.AbstractBorder;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SalaEsperaView {

    private SalaEsperaModel salaEsperaModel;
    private JPanel panelSalaEspera;
    private JPanel contenedorUsuarios;
    private JLabel labelJugadores;
    private JButton btnEmpezarPartida; 

    public SalaEsperaView(SalaEsperaModel salaEsperaModel) {
        this.salaEsperaModel = salaEsperaModel;
        this.panelSalaEspera = new JPanel(new GridBagLayout());
        this.panelSalaEspera.setBackground(new Color(0x0C043A)); // Fondo del panel principal        
        crearContenedorUsuariosPanel();
        crearLblSalaEspera();
        crearLblJugadores();
        crearBotonEmpezarPartida();
        contenedorUsuarios.add(labelJugadores);
        panelSalaEspera.add(contenedorUsuarios, new GridBagConstraints());
    }

    private void crearBotonEmpezarPartida() {
        btnEmpezarPartida = new JButton("Empezar Partida");
        btnEmpezarPartida.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnEmpezarPartida.setBackground(Color.WHITE);
        btnEmpezarPartida.setForeground(new Color(0x4F0149)); 
        btnEmpezarPartida.setFont(new Font("Cairo", Font.BOLD, 14)); 
        btnEmpezarPartida.setVisible(false);
    }

    private void crearContenedorUsuariosPanel() {
        this.contenedorUsuarios = new JPanel();
        this.contenedorUsuarios.setLayout(new BoxLayout(contenedorUsuarios, BoxLayout.Y_AXIS));
        this.contenedorUsuarios.setBackground(new Color(0x810077));
        this.contenedorUsuarios.setOpaque(false);
        this.contenedorUsuarios.setBorder(new RoundedBorder(10, new Color(0x810077)));
    }

    private void crearLblJugadores() {
        this.labelJugadores = new JLabel("Jugadores:");
        this.labelJugadores.setForeground(Color.WHITE);
        this.labelJugadores.setFont(new Font("Cairo", Font.BOLD, 18));
        this.labelJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.labelJugadores.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
    }

    private void crearLblSalaEspera() {
        this.labelJugadores = new JLabel("Sala de Espera:");
        this.labelJugadores.setForeground(Color.WHITE);
        this.labelJugadores.setFont(new Font("Cairo", Font.BOLD, 30));
        this.labelJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.labelJugadores.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }

    /**
     * Método para actualizar la vista de la pantalla de la sala de espera.
     * Incluye la actualización del fondo y la lista de usuarios.
     */
    public void repintarPantalla() {
        repintarFondo();
        repintarJugadores();
        panelSalaEspera.revalidate();
        panelSalaEspera.repaint();
    }

    /**
     * Configura el color de fondo del panel de la sala de espera.
     */
    private void repintarFondo() {
        panelSalaEspera.setBackground(new Color(0x0C043A));
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
            Graphics2D g2 = (Graphics2D) g;

            // Habilitar el suavizado para los bordes
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);

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
}
