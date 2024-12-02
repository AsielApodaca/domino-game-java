package mvc;

import java.util.ArrayList;
import java.util.List;
import notificador.IPresentacionCrearUsuarioNotificador;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class CrearUsuarioModel {

    private String nombreUsuario;
    private int indiceIconoActual;
    private List<String> listaIconos;

    // Media
    private String tituloJuego;
    private String btnAnterior;
    private String btnSiguiente;
    private String btnEmpezar;

    //Iconos
    private String icono1;
    private String icono2;
    private String icono3;
    private String icono4;
    private String icono5;
    private String icono6;
    private String icono7;
    private String icono8;

    // Medidas y locaciones
    private int anchoPantalla;
    private int alturaPantalla;
    private int anchoTituloJuego;
    private int altoTituloJuego;
    private int tituloJuegoLocacionX;
    private int tituloJuegoLocacionY;
    private int anchoIconoJugador;
    private int altoIconoJugador;
    private int iconoJugadorLocacionX;
    private int iconoJugadorLocacionY;
    private int textoSeleccionAncho;
    private int textoSeleccionAlto;
    private int textoUsuarioAncho;
    private int textoUsuarioAlto;
    private int textoSeleccionLocacionX;
    private int textoSeleccionLocacionY;
    private int textoUsuarioLocacionX;
    private int textoUsuarioLocacionY;

    private IPresentacionCrearUsuarioNotificador presentacionNotificadorManager;

    public CrearUsuarioModel() {
        // Inicialización de variables básicas
        this.nombreUsuario = "";
        this.indiceIconoActual = 0;
        this.listaIconos = new ArrayList<>();

        // Rutas de recursos multimedia
        this.tituloJuego = "/multimedia/TituloJuego.png";
        this.btnAnterior = "/multimedia/btnAnterior.png";
        this.btnSiguiente = "/multimedia/btnSiguiente.png";
        this.btnEmpezar = "/multimedia/btnEmpezar.png";

        // Rutas iconos
        this.icono1 = "/multimedia/Icono1.png";
        this.icono2 = "/multimedia/Icono2.png";
        this.icono3 = "/multimedia/Icono3.png";
        this.icono4 = "/multimedia/Icono4.png";
        this.icono5 = "/multimedia/Icono5.png";
        this.icono6 = "/multimedia/Icono6.png";
        this.icono7 = "/multimedia/Icono7.png";
        this.icono8 = "/multimedia/Icono8.png";

        // Medidas iniciales
        this.anchoPantalla = 600;
        this.alturaPantalla = 400;
        this.altoTituloJuego = 18;
        this.anchoTituloJuego = 100;
        this.tituloJuegoLocacionX = 22;
        this.tituloJuegoLocacionY = 22;
        this.anchoIconoJugador = 50;
        this.altoIconoJugador = 50;
        this.iconoJugadorLocacionX = anchoPantalla / 2 - anchoIconoJugador / 2;
        this.iconoJugadorLocacionY = alturaPantalla / 2 - altoIconoJugador / 2;
        this.textoSeleccionAncho = 148;
        this.textoSeleccionAlto = 16;
        this.textoSeleccionLocacionX = 225;
        this.textoSeleccionLocacionY = 197;
        this.textoUsuarioAncho = 95;
        this.textoUsuarioAlto = 16;
        this.textoUsuarioLocacionX = 145;
        this.textoUsuarioLocacionY = 240;

        // Inicializar lista de iconos
        cargarIconosDisponibles();
    }

    private void cargarIconosDisponibles() {
        // Aquí se cargan las rutas de los iconos disponibles
        listaIconos.add(icono1);
        listaIconos.add(icono2);
        listaIconos.add(icono3);
        listaIconos.add(icono4);
        listaIconos.add(icono5);
        listaIconos.add(icono6);
        listaIconos.add(icono7);
        listaIconos.add(icono8);
    }

    public IPresentacionCrearUsuarioNotificador getPresentacionNotificadorManager() {
        return presentacionNotificadorManager;
    }

    public void setPresentacionNotificadorManager(IPresentacionCrearUsuarioNotificador presentacionNotificadorManager) {
        this.presentacionNotificadorManager = presentacionNotificadorManager;
    }

    public void siguienteIcono() {
        indiceIconoActual = (indiceIconoActual + 1) % listaIconos.size();
    }

    public void anteriorIcono() {
        indiceIconoActual = (indiceIconoActual - 1 + listaIconos.size()) % listaIconos.size();
    }

    public String getIconoActual() {

        return listaIconos.get(indiceIconoActual);

    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTituloJuego() {
        return tituloJuego;
    }

    public String getBtnAnterior() {
        return btnAnterior;
    }

    public String getBtnSiguiente() {
        return btnSiguiente;
    }

    public String getBtnEmpezar() {
        return btnEmpezar;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public int getAlturaPantalla() {
        return alturaPantalla;
    }

    public int getAnchoTituloJuego() {
        return anchoTituloJuego;
    }

    public int getAltoTituloJuego() {
        return altoTituloJuego;
    }

    public int getTituloJuegoLocacionX() {
        return tituloJuegoLocacionX;
    }

    public int getTituloJuegoLocacionY() {
        return tituloJuegoLocacionY;
    }

    public int getIconoJugadorLocacionX() {
        return iconoJugadorLocacionX;
    }

    public int getIconoJugadorLocacionY() {
        return iconoJugadorLocacionY;
    }

    public int getAnchoIconoJugador() {
        return anchoIconoJugador;
    }

    public int getAltoIconoJugador() {
        return altoIconoJugador;
    }

    public int getTextoSeleccionAncho() {
        return textoSeleccionAncho;
    }

    public int getTextoSeleccionAlto() {
        return textoSeleccionAlto;
    }

    public int getTextoUsuarioAncho() {
        return textoUsuarioAncho;
    }

    public int getTextoUsuarioAlto() {
        return textoUsuarioAlto;
    }

    public int getTextoSeleccionLocacionX() {
        return textoSeleccionLocacionX;
    }

    public int getTextoSeleccionLocacionY() {
        return textoSeleccionLocacionY;
    }

    public int getTextoUsuarioLocacionX() {
        return textoUsuarioLocacionX;
    }

    public int getTextoUsuarioLocacionY() {
        return textoUsuarioLocacionY;
    }

}
