package main;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FramePrincipalView extends JFrame {
    private static FramePrincipalView instance; // Campo estático para la instancia
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private String tituloJuego;
    private int anchoVentana;
    private int altoVentana;
    private JPanel[] ventanas;

    private FramePrincipalView(FramePrincipalModel model, FramePrincipalController controller) {
        this.tituloJuego = model.getTituloJuego();
        this.anchoVentana = model.getAnchoVentana();
        this.altoVentana = model.getAltoVentana();
        this.ventanas = model.getVentanas();

        inicializarComponentes(controller);
    }

    public static FramePrincipalView getInstance(FramePrincipalModel model, FramePrincipalController controller) {
        if (instance == null) {
            instance = new FramePrincipalView(model, controller);
        }
        return instance;
    }

    private void inicializarComponentes(FramePrincipalController controller) {
        setTitle(tituloJuego);
        setSize(anchoVentana, altoVentana);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Agregar los paneles al Form
        for (JPanel ventana : ventanas) {
            cardPanel.add(ventana, ventana.getName());
        }

        add(cardPanel);

        // Configurar los botones para que utilicen el controlador
        ((JButton) ventanas[0].getComponent(0)).addActionListener(e -> controller.mostrarPanel(1)); // Ir a Panel 2
        ((JButton) ventanas[1].getComponent(0)).addActionListener(e -> controller.mostrarPanel(2)); // Ir a Panel 3
        ((JButton) ventanas[2].getComponent(0)).addActionListener(e -> controller.mostrarPanel(0)); // Regresar a Panel 1

        setVisible(true);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public static void main(String[] args) {
        FramePrincipalModel model = new FramePrincipalModel();
        FramePrincipalController controller = new FramePrincipalController(model);
        getInstance(model, controller); // Obtener la instancia de la vista
    }
}
