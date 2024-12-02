package mvc;

import notificador.IPresentacionMenuDominoNotificador;

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
    private String logo;
    private String btnUnirseSala;
    private String btnCrearSala;

    // Medidas originales
    private int anchoPantalla; // ancho del panel de la pantalla
    private int alturaPantalla; // altura del panel de la pantalla
    private IPresentacionMenuDominoNotificador presentacionNotificadorManager;


 
    public MenuDominoModel() {
        this.fondoDePantalla = "/multimedia/FondoMenu.png";
        this.logo = "/multimedia/DotMino.png";
        this.btnCrearSala = "/multimedia/btnCrearSala.png";
        this.btnUnirseSala = "/multimedia/btnUnirseSala.png";
        this.escala = 1.0f;
        this.anchoPantalla = 600;
        this.alturaPantalla = 400;
    }

    public IPresentacionMenuDominoNotificador getPresentacionNotificadorManager() {
        return presentacionNotificadorManager;
    }

    public void setPresentacionNotificadorManager(IPresentacionMenuDominoNotificador presentacionNotificadorManager) {
        this.presentacionNotificadorManager = presentacionNotificadorManager;
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

    public String getLogo() {
        return logo;
    }

    public String getBtnUnirseSala() {
        return btnUnirseSala;
    }

    public String getBtnCrearSala() {
        return btnCrearSala;
    }

    
    

}
