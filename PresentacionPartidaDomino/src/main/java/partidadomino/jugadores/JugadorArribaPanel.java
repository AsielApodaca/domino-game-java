package partidadomino.jugadores;

import dominodto.JugadorDominoDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;

public class JugadorArribaPanel extends JugadorPanel {
    private static final int DIAMETRO_CIRCULO = 25;
    private static final int ALTURA_SECCION1 = 45;
    private static final int ALTURA_SECCION2 = 40;
    private static final int ANCHO_RECTANGULO = 20;
    private static final int ALTURA_RECTANGULO = 40;
    private static final int ANCHO_SECCION3 = 40;
    
    private static final int LOCACION_Y_DESDE_ARRIBA = 10;
    private static final int ESPACIO_ENTRE_SECCIONES = 5;

    private static final String RUTA_IMAGEN_RECTANGULO = "/multimedia/FichaVaciaVertical.png";

    private BufferedImage imagenJugador;
    private BufferedImage imagenRectangulo;
    private String nombreJugador;

    public JugadorArribaPanel(JugadorDominoDTO jugadorDominoDTO) {
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
        // Pintar Sección 1 (círculo) - Parte superior izquierda
        int x1 = 0;
        int y1 = 0;
        int ancho1 = (int) (ANCHO_RECTANGULO * cantidadFichas * escala);
        int altura1 = (int) (ALTURA_SECCION1 * escala);

        // Pintar círculo en Sección 1, completamente centrado
        int xCirculo = (ancho1 - (int) (DIAMETRO_CIRCULO * escala)) / 2;
        int yCirculo = (altura1 - (int) (DIAMETRO_CIRCULO * escala)) / 2;
        int xPersonaje = (ancho1 - (int) (DIAMETRO_CIRCULO * escala - 4 * escala)) / 2;
        int yPersonaje = (altura1 - (int) (DIAMETRO_CIRCULO * escala - 4 * escala)) / 2;
        g.setColor(turnoDeColocar ? Color.GREEN : Color.RED);
        g.drawOval(xCirculo, yCirculo, (int) (DIAMETRO_CIRCULO * escala), (int) (DIAMETRO_CIRCULO * escala));
        g.drawImage(imagenJugador, xPersonaje, yPersonaje, 
            (int) (DIAMETRO_CIRCULO * escala - 4 * escala), 
            (int) (DIAMETRO_CIRCULO * escala - 4 * escala), null);

        // Pintar Sección 2 (rectangulos) - Parte inferior izquierda
        int x2 = 0;
        int y2 = altura1 + ESPACIO_ENTRE_SECCIONES;
        int alturaRectangulos = (int) (ALTURA_RECTANGULO * escala);
        int anchoRectangulos = (int) (ANCHO_RECTANGULO * escala);

        // Pintar iconos de jugador en Sección 2
        int xIcono = x2;
        int yIcono = y2;
        for (int i = 0; i < cantidadFichas; i++) {
            g.drawImage(imagenRectangulo, xIcono, yIcono, anchoRectangulos, alturaRectangulos, null);
            xIcono += anchoRectangulos;
        }

        // Pintar Sección 3 (nombre) - Parte derecha desde arriba hasta abajo
        int x3 = ancho1 + ESPACIO_ENTRE_SECCIONES;
        int y3 = 0;
        int anchoSeccion3 = (int) (ANCHO_SECCION3 * escala);
        int alturaSeccion3 = getHeight();

        // Pintar nombre de jugador en Sección 3
        g.setColor(Color.WHITE);
        int letterSize = (int) (tamanioLetra * escala);
        g.setFont(new Font("Tahoma", Font.PLAIN, letterSize));
        int anchoNombre = g.getFontMetrics().stringWidth(nombreJugador);
        int xNombre = x3 + (anchoSeccion3 - anchoNombre) / 2;
        int yNombre = alturaSeccion3 / 2 + g.getFontMetrics().getHeight() / 2;
        g.drawString(nombreJugador, xNombre, yNombre);
    }

    @Override
    protected void reescalarJugadorPanel() {
        // Calcular ancho basado en rectangulos
        int anchoRectangulos = (int) (ANCHO_RECTANGULO * cantidadFichas * escala);
        int anchoSeccion3 = (int) (ANCHO_SECCION3 * escala);
        
        int ancho = anchoRectangulos + anchoSeccion3 + ESPACIO_ENTRE_SECCIONES;
        int altura = (int) ((ALTURA_SECCION1 + ALTURA_SECCION2 + ESPACIO_ENTRE_SECCIONES) * escala);
        
        setSize(new Dimension(ancho, altura));
        setPreferredSize(new Dimension(ancho, altura));
        setMinimumSize(new Dimension(ancho, altura));
        setMaximumSize(new Dimension(ancho, altura));
        
        JPanel contenedorPadre = (JPanel) this.getParent();
        if(contenedorPadre != null) {
            int anchoPantalla = (int) (anchoContenedorPantalla * escala);
            
            // Calcular posición basándose en el ancho de la sección 1 (izquierda)
            int anchoSeccion1 = (int) (ANCHO_RECTANGULO * cantidadFichas * escala);
            int locacionX = (int) ((anchoPantalla - anchoSeccion1) / 2);
            int locacionY = (int) (LOCACION_Y_DESDE_ARRIBA * escala);
            
            setBounds(locacionX, locacionY, ancho, altura);
        }
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