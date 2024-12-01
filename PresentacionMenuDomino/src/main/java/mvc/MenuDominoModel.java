package mvc;

import notificador.IPresentacionNotificadorManager;

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
    private IPresentacionNotificadorManager presentacionNotificadorManager;


 
    public MenuDominoModel() {
        this.fondoDePantalla = "/multimedia/FondoMenu.png";
        this.logo = "/multimedia/DotMino.png";
        this.btnCrearSala = "/multimedia/btnCrearSala.png";
        this.btnUnirseSala = "/multimedia/btnUnirseSala.png";
        this.escala = 1.0f;
        this.anchoPantalla = 600;
        this.alturaPantalla = 400;
    }

    public IPresentacionNotificadorManager getPresentacionNotificadorManager() {
        return presentacionNotificadorManager;
    }

    public void setPresentacionNotificadorManager(IPresentacionNotificadorManager presentacionNotificadorManager) {
        this.presentacionNotificadorManager = presentacionNotificadorManager;
    }

    public void notificarCrearSala(){
        presentacionNotificadorManager.notificarCrearSala();
    }
    
    public void notificarUnirseSala(){
        presentacionNotificadorManager.notificarUnirseSala();
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
