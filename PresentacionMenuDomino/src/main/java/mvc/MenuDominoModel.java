package mvc;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MenuDominoModel {
    private float escala; // Escala de view, afecta el tamaño de todos los componentes para adaptarse al Frame
    // media
    private String fondoDePantalla;

    // Medidas originales
    private int anchoPantalla; // ancho del panel de la pantalla
    private int alturaPantalla; // altura del panel de la pantalla

 
    public MenuDominoModel() {
        this.fondoDePantalla = "/multimedia/FondoPartida.jpg";
        this.escala = 1.0f;
        this.anchoPantalla = 600;
        this.alturaPantalla = 400;
    }

    public float getEscala() {
        return escala;
    }

    public String getFondoDePantalla() {
        return fondoDePantalla;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public int getAlturaPantalla() {
        return alturaPantalla;
    }

    
    

}
