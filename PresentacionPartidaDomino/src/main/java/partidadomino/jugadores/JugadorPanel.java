/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.jugadores;

import dominodto.JugadorDominoDTO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author asielapodaca
 */
public abstract class JugadorPanel extends JPanel{
    protected JugadorDominoDTO jugadorDominoDTO;
    protected int cantidadFichas;
    protected boolean turnoDeColocar;
    
    // Medidas originales
    protected float escala = 1;
    protected int tamanioLetra = 10;
    protected final int anchoContenedorPantalla = 600;
    protected final int alturaContenedorPantalla = 400;

    public JugadorPanel(JugadorDominoDTO jugadorDominoDTO) {
        this.jugadorDominoDTO = jugadorDominoDTO;
        this.cantidadFichas = 0;
        this.turnoDeColocar = false;
    }
    
    public void actualizarCantidadFichas(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }
    
    public void actualizarTurno(boolean turnoDeColocar) {
        this.turnoDeColocar = turnoDeColocar;
    }

    public JugadorDominoDTO getJugadorDominoDTO() {
        return jugadorDominoDTO;
    }

    public void reescalar(float escala) {
        this.escala = escala;
        repintar();
    }
    
    protected abstract void repintar();
    
    protected abstract void repintarFichas();
    
    protected abstract void repintarJugador();
    
    protected abstract void reescalarJugadorPanel();
    
    protected BufferedImage cargarImagen(String ruta) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(ruta);
        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el recurso: " + ruta);
        }
        return ImageIO.read(inputStream);
    }
}
