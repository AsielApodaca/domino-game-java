package partidadomino.jugadores;

import dominodto.JugadorDominoDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class JugadorIzquierdaPanel extends JugadorPanel {
    private static final int DIAMETRO_CIRCULO = 25;
    private static final int ANCHO_SECCION1 = 45;
    private static final int ALTURA_MINIMA_SECCION1 = DIAMETRO_CIRCULO;

    private static final int ANCHO_SECCION2 = 40;
    private static final int ALTURA_MINIMA_SECCION2 = ALTURA_MINIMA_SECCION1;
    private static final int ANCHO_RECTANGULO = 40;
    private static final int ALTURA_RECTANGULO = 20;

    private static final int ANCHO_SECCION3 = ANCHO_SECCION1 + ANCHO_SECCION2;
    private static final int ALTURA_SECCION3 = 20;

    private static final String RUTA_IMAGEN_RECTANGULO = "/multimedia/FichaVaciaHorizontal.png";

    private BufferedImage imagenJugador;
    private BufferedImage imagenRectangulo;
    private String nombreJugador;

    public JugadorIzquierdaPanel(JugadorDominoDTO jugadorDominoDTO) {
        super(jugadorDominoDTO);
        this.nombreJugador = jugadorDominoDTO.getNombre();

        setOpaque(false);
        try {
            this.imagenJugador = cargarImagen(jugadorDominoDTO.getFuenteIcono());
            this.imagenRectangulo = cargarImagen(RUTA_IMAGEN_RECTANGULO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        reescalarJugadorPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {

        // Pintar Sección 1
        int x1 = 0;
        int y1 = 0;
        int ancho1 = (int) (ANCHO_SECCION1 * escala);
        int altura1 = (int) (Math.max(ALTURA_MINIMA_SECCION1 * escala, getHeight() - ALTURA_SECCION3 * escala));

        // Pintar círculo en Sección 1
        int xCirculo = x1 + (ancho1 - (int) (DIAMETRO_CIRCULO * escala)) / 2;
        int yCirculo = y1 + (altura1 - (int) (DIAMETRO_CIRCULO * escala)) / 2;
        int xPersonaje = x1 + (ancho1 - (int) (DIAMETRO_CIRCULO * escala - 4 * escala)) / 2;
        int yPersonaje = y1 + (altura1 - (int) (DIAMETRO_CIRCULO * escala - 4 * escala)) / 2;
        g.setColor(turnoDeColocar ? Color.GREEN : Color.RED);
        g.drawOval(xCirculo, yCirculo, (int) (DIAMETRO_CIRCULO * escala), (int) (DIAMETRO_CIRCULO * escala));
        g.drawImage(imagenJugador, xPersonaje, yPersonaje, (int) (DIAMETRO_CIRCULO * escala - 4 * escala), (int) (DIAMETRO_CIRCULO * escala - 4 * escala), null);

        // Pintar Sección 2
        int x2 = x1 + ancho1;
        int y2 = 0;
        int ancho2 = (int) (ANCHO_SECCION2 * escala);
        int altura2 = (int) (Math.max(ALTURA_MINIMA_SECCION2 * escala, getHeight() - ALTURA_SECCION3 * escala));

        // Pintar iconos de jugador en Sección 2
        int xIcono = x2 + (ancho2 - (int) (ANCHO_RECTANGULO * escala)) / 2;
        int yIcono = y2 + (altura2 - (cantidadFichas * (int) (ALTURA_RECTANGULO * escala))) / 2;
        for (int i = 0; i < cantidadFichas; i++) {
            g.drawImage(imagenRectangulo, xIcono, yIcono, (int) (ANCHO_RECTANGULO * escala), (int) (ALTURA_RECTANGULO * escala), null);
            yIcono += (int) (ALTURA_RECTANGULO * escala);
        }

        // Pintar Sección 3
        int x3 = 0;
        int y3 = altura1;
        int ancho3 = (int) (ANCHO_SECCION3 * escala);
        int altura3 = (int) (ALTURA_SECCION3 * escala);

        // Pintar nombre de jugador en Sección 3
        g.setColor(Color.WHITE);
        int anchoNombre = g.getFontMetrics().stringWidth(nombreJugador);
        int xNombre = x3 + (ancho3 - anchoNombre) / 2;
        int yNombre = y3 + (altura3 + g.getFontMetrics().getHeight()) / 2;
        g.drawString(nombreJugador, xNombre, yNombre);
    }

    @Override
    protected void repintar() {
        reescalarJugadorPanel();
        repaint();
    }

    @Override
    protected void repintarFichas() {
        repaint();
    }

    @Override
    protected void repintarJugador() {
        repaint();
    }

    @Override
    protected void reescalarJugadorPanel() {
        int ancho = (int) (ANCHO_SECCION3 * escala);
        int altura = (int) ((ALTURA_SECCION3 + ALTURA_RECTANGULO * cantidadFichas) * escala);
        setSize(new Dimension(ancho, altura));
        setPreferredSize(new Dimension(ancho, altura));
        setMinimumSize(new Dimension(ancho, altura));
        setMaximumSize(new Dimension(ancho, altura));
    }

    @Override
    public void actualizarCantidadFichas(int nuevaCantidadFichas) {
        this.cantidadFichas = nuevaCantidadFichas;
        reescalarJugadorPanel();
        repintarFichas();
    }

    @Override
    public void actualizarTurno(boolean turnoDeColocar) {
        this.turnoDeColocar = turnoDeColocar;
        repintarJugador();
    }
}