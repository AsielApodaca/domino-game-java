/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import presentacion.mediador.IMediador;
import presentacion.mediador.Mediador;
import listeners.IPartidaDominoModelListener;
import presentacion.partidadomino.fichadominojugador.FichaDominoView;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaDominoView extends JPanel{

    private PartidaDominoModel model; // modelo de PartidaDomino

    private BufferedImage fondoPantalla; // Fondo de pantalla de PartidaDominoView

    private JPanel panelContenedorFichasJugadorLocal; // Panel que contendrá las fichas del jugador local
    private JPanel panelTablero; // Panel de tablero donde se colocarán las fichas

    public PartidaDominoView(PartidaDominoModel model) {
        this.model = model;
        cargarComponentes();
//        asignarListeners();
    }

    private void cargarComponentes() {
        setLayout(null);
        // Cargar la imagen de fondo de pantalla
        try {
            fondoPantalla = ImageIO.read(getClass().getResource(model.getFondoDePantalla()));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        add(panelContenedorFichasJugadorLocal);
        add(panelTablero);

        repintarVista();
    }
    

    public void repintarVista() {
        repintarPanel();
        repintarContenedorFichasJugadorLocal();
        repintarTablero();

//        model.redimencionarFichasJugadorLocal();
        revalidate();
        repaint();
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
                * (model.getListaFichasJugadorLocal().size() + 1)
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
       for(FichaDominoView fichaPanel : model.getListaPanelesFichasJugadorLocal()) {
           panelContenedorFichasJugadorLocal.add(fichaPanel);
       }
       
       revalidate();
       repaint();
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

    //Pintar fondo de pantalla
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondoPantalla != null) {
            g.drawImage(fondoPantalla, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
//    private void asignarListeners() {
//        this.model.agregarListenerView(this);
//    }


}
