package mvcpartidadomino;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import partidadomino.elementostablero.CasillaPanel;
import partidadomino.elementostablero.FichaDominoTableroPanel;
import partidadomino.elementostablero.PozoPanel;
import partidadomino.fichadominojugadormvc.FichaDominoView;
import partidadomino.jugadores.JugadorPanel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaDominoView extends JPanel {

    private PartidaDominoModel model; // modelo de PartidaDomino

    private BufferedImage fondoPantalla; // Fondo de pantalla de PartidaDominoView

    private JPanel panelContenedorFichasJugadorLocal; // Panel que contendrá las fichas del jugador local
    private JPanel panelTablero; // Panel de tablero donde se colocarán las fichas
    private PozoPanel pozoPanel;


    public PartidaDominoView(PartidaDominoModel model) {
        this.model = model;
        cargarComponentes();
        revalidate();
        repaint();
    }

    private void cargarComponentes() {
        setLayout(null);
        // Cargar la imagen de fondo de pantalla
        try {
            fondoPantalla = ImageIO.read(getClass().getResource(model.getFondoDePantalla()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pozoPanel = new PozoPanel();

        // Pintar contenedor de fichas
        panelContenedorFichasJugadorLocal = new JPanel() { // Panel transparente
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // Establecer una transparencia del 50% (0.5f)
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

                // Pintar el fondo con el color del panel
                g2d.setColor(new Color(0x0C043A));
                // Dibujar un rectángulo con bordes redondeados (radio 25)
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                g2d.dispose();
            }

        };
        panelContenedorFichasJugadorLocal.setOpaque(false); // Hacer que el panel no sea opaco, para permitir la transparencia
        panelContenedorFichasJugadorLocal.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Sin espacios entre componentes

        //Pintar Tablero
        panelTablero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // Establecer una transparencia del 50% (0.5f)
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));

                // Pintar el fondo con el color del panel
                g2d.setColor(new Color(0x0C043A));
                // Dibujar un rectángulo con bordes redondeados (radio 25)
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                g2d.dispose();
            }
        };
        // Hacer que el panel no sea opaco, para permitir la transparencia
        panelTablero.setOpaque(false);
        panelTablero.setLayout(null);

        add(panelContenedorFichasJugadorLocal);
        add(panelTablero);
        add(pozoPanel);

        repintarVista();
    }
    
    public void cargarJugadoresPaneles() {
        Collection<JugadorPanel> jugadores = model.getMapaJugadoresExternos().values();
        for(JugadorPanel jugadorPanel : jugadores) {
            jugadorPanel.reescalar(model.getEscala());
            add(jugadorPanel);
        }
    }

    public void repintarVista() {
        repintarPanel();
        repintarContenedorFichasJugadorLocal();
        repintarFichasJugadorLocal();
        repintarTablero();
        repintarCasillasTablero();
        repintarFichasTablero();
        repintarPozo();
        repintarJugadores();
        revalidate();
        repaint();
    }

    public void repintarJugadores() {
        // Obtiene los jugadores del modelo
        Collection<JugadorPanel> jugadores = model.getMapaJugadoresExternos().values();
        // Reescala los jugadores
        for(JugadorPanel jugadorPanel : jugadores) {
            jugadorPanel.reescalar(model.getEscala());
        }
    }
    
    public void removerJugadorPanel(JugadorPanel jugadorPanel) {
        remove(jugadorPanel);
    }

    private void repintarPanel() {
        int ancho = (int) (model.getAnchoPantalla() * model.getEscala());
        int altura = (int) (model.getAlturaPantalla() * model.getEscala());
        setPreferredSize(new Dimension(ancho, altura));

        revalidate();
        repaint();
    }

    public void repintarContenedorFichasJugadorLocal() {
        int ancho = (int) (model.getAnchoPantalla() * model.getEscala());
        // Redimencionar y posicionar panelContenedorFichasJugadorLocal
        int anchoContenedorFichasJugadorLocal = (int) (model.getAnchoFichaJugadorLocal()
                * (model.getListaFichasJugadorLocal().size())
                * model.getEscala());
        int altoContenedorFichasJugadorLocal = (int) (model.getAlturaMinimaContenedorFichasJugadorLocal()
                * model.getEscala());
        int contenedorFichasJugadorLocalY = (int) (model.getContenedorFichasJugadorLocalLocacionY()
                * model.getEscala());
        // Calcular la posición X para centrar panelContenedorFichasJugadorLocal horizontalmente
        int contenedorFichasJugadorLocalX = (ancho - anchoContenedorFichasJugadorLocal) / 2;

        panelContenedorFichasJugadorLocal.setBounds(
                contenedorFichasJugadorLocalX,
                contenedorFichasJugadorLocalY,
                anchoContenedorFichasJugadorLocal,
                altoContenedorFichasJugadorLocal
        );

        revalidate();
        repaint();
    }

    public void repintarFichasJugadorLocal() {
        panelContenedorFichasJugadorLocal.removeAll();
        for (FichaDominoView fichaPanel : model.getListaPanelesFichasJugadorLocal()) {
            panelContenedorFichasJugadorLocal.add(fichaPanel);
            fichaPanel.repintar();
        }
    }

    private void repintarTablero() {
        // Redimencionar y posicionar panelTablero
        int tableroAncho = (int) (model.getAnchoTablero() * model.getEscala());
        int tableroAltura = (int) (model.getAlturaTablero() * model.getEscala());
        int tableroX = (int) (model.getTableroLocacionX() * model.getEscala());
        int tableroY = (int) (model.getTableroLocacionY() * model.getEscala());
        panelTablero.setBounds(tableroX, tableroY, tableroAncho, tableroAltura);

        revalidate();
        repaint();
    }

    public void repintarPozo() {
        // Redimensionar y posicionar panel del pozo
        int pozoAncho = (int) (model.getAnchoPozo() * model.getEscala());
        int pozoAltura = (int) (model.getAlturaPozo() * model.getEscala());
        int pozoX = (int) (model.getPozoLocacionX() * model.getEscala());
        int pozoY = (int) (model.getPozoLocacionY() * model.getEscala());
        pozoPanel.setBounds(pozoX, pozoY, pozoAncho, pozoAltura);

        revalidate();
        repaint();
    }

    public void colocarFichaTablero(FichaDominoTableroPanel fichaDominoTablero) {
        panelTablero.add(fichaDominoTablero);

    }

    public void repintarFichasTablero() {
        float escala = model.getEscala();
//        int anchoTableroDTO = model.getTableroDominoDTO().getAnchoTablero();
//        int alturaTableroDTO = model.getTableroDominoDTO().getAltoTablero();
        int anchoTableroDTO = 20; // temporal
        int alturaTableroDTO = 9; // temporal
        int anchoTablero = (int) (model.getAnchoTablero() * escala);
        int alturaTablero = (int) (model.getAlturaTablero() * escala);
        int anchoUsableTablero = (int) (anchoTableroDTO * model.getAnchoFichaTablero() * escala);
        int alturaUsableTablero = (int) (alturaTableroDTO * model.getAnchoFichaTablero() * escala);
        int margenAnchoTablero = (int) ((anchoTablero - anchoUsableTablero) / 2);
        int margenAlturaTablero = (int) ((alturaTablero - alturaUsableTablero) / 2);

        for (FichaDominoTableroPanel fichaDominoTablero : model.getListaPanelesFichasSobreTablero()) {
            int locacionX = (int) (fichaDominoTablero.getLocacionX() * model.getAnchoFichaTablero() * escala);
            int locacionY = (int) (fichaDominoTablero.getLocacionY() * model.getAnchoFichaTablero() * escala);
            locacionX += margenAnchoTablero;
            locacionY += margenAlturaTablero;

            int anchoFichaTablero;
            int alturaFichaTablero;

            if (fichaDominoTablero.getEsHorizontal()) {
                anchoFichaTablero = (int) (escala * model.getLargoFichaTablero()); // Horizontal
                alturaFichaTablero = (int) (escala * model.getAnchoFichaTablero());
            } else {
                anchoFichaTablero = (int) (escala * model.getAnchoFichaTablero()); // Vertical
                alturaFichaTablero = (int) (escala * model.getLargoFichaTablero());
            }

            fichaDominoTablero.setBounds(locacionX, locacionY, anchoFichaTablero, alturaFichaTablero);

        }
        revalidate();
        repaint();
    }

    public void repintarCasillasTablero() {
        // Remover casillas en tablero
        for (Component comp : panelTablero.getComponents()) {
            if (CasillaPanel.class.isInstance(comp)) {
                panelTablero.remove(comp);
            }
        }

        float escala = model.getEscala();
//        int anchoTableroDTO = model.getTableroDominoDTO().getAnchoTablero();
//        int alturaTableroDTO = model.getTableroDominoDTO().getAltoTablero();
        int anchoTableroDTO = 20; // temporal
        int alturaTableroDTO = 9; // temporal
        int anchoTablero = (int) (model.getAnchoTablero() * escala);
        int alturaTablero = (int) (model.getAlturaTablero() * escala);
        int anchoUsableTablero = (int) (anchoTableroDTO * model.getAnchoFichaTablero() * escala);
        int alturaUsableTablero = (int) (alturaTableroDTO * model.getAnchoFichaTablero() * escala);
        int margenAnchoTablero = (int) ((anchoTablero - anchoUsableTablero) / 2);
        int margenAlturaTablero = (int) ((alturaTablero - alturaUsableTablero) / 2);

        // Repintar casillas
        for (CasillaPanel casillaPanel : model.getListaPanelesCasillasParaColocarFichas()) {
            panelTablero.add(casillaPanel);
            int locacionX = (int) (casillaPanel.getLocacionX() * model.getAnchoFichaTablero() * escala);
            int locacionY = (int) (casillaPanel.getLocacionY() * model.getAnchoFichaTablero() * escala);
            locacionX += margenAnchoTablero;
            locacionY += margenAlturaTablero;

            int anchoCasillaTablero;
            int alturaCasillaTablero;

            if (casillaPanel.getEsHorizontal()) {
                anchoCasillaTablero = (int) (escala * model.getLargoFichaTablero()); // Horizontal
                alturaCasillaTablero = (int) (escala * model.getAnchoFichaTablero());
            } else {
                anchoCasillaTablero = (int) (escala * model.getAnchoFichaTablero()); // Vertical
                alturaCasillaTablero = (int) (escala * model.getLargoFichaTablero());
            }

            casillaPanel.setBounds(locacionX, locacionY, anchoCasillaTablero, alturaCasillaTablero);

        }
        revalidate();
        repaint();
    }

    //Pintar fondo de pantalla
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondoPantalla != null) {
            g.drawImage(fondoPantalla, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public PozoPanel getPozoPanel() {
        return pozoPanel;
    }

}
