package presentacion.partidadomino;

import dominodto.FichaDominoDTO;
import dominodto.TableroDominoDTO;
import java.util.ArrayList;
import java.util.List;
import presentacion.partidadomino.fichadominojugador.FichaDominoView;
import listeners.ITableroDominoLogicaListener;
import listeners.IPartidaDominoModelListener;
import notificaciones.PresentacionNotificacionesManager;
import notificaciones.eventos.Evento;
import notificaciones.eventos.FichaSeleccionadaEvento;
import presentacion.partidadomino.tablero.CasillaPanel;
import presentacion.partidadomino.tablero.FichaDominoTablero;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaDominoModel{

    private float escala; // Escala de view, afecta el tamaño de todos los componentes para adaptarse al Frame
    // media
    private String fondoDePantalla;

    // Medidas originales
    private int anchoPantalla; // ancho del panel de la pantalla
    private int alturaPantalla; // altura del panel de la pantalla

    private int anchoFichaJugadorLocal; // ancho de las fichas del jugador del dispositivo
    private int alturaFichaJugadorLocal; // ancho de las fichas del jugador del dispositivo

    private int anchoFichaJugadorExterno; // ancho de las fichas de los jugadores externos.
    private int alturaFichaJugadorExterno; // altura de las fichas de los jugadores externos.

    private int alturaMinimaContenedorFichasJugadorLocal; // altura del panel que contiene las fichas del jugador local
    private int anchoMinimoContenedorFichasJugadorLocal; // ancho del panel que contiene las fichas del jugador local
    private int contenedorFichasJugadorLocalLocacionY; // posicion del contenedor de fichas locales en eje de las Y

    private int anchoTablero; // ancho del panel del tablero donde iran las fichas colocadas.
    private int alturaTablero; // altura del panel del tablero donde iran las fichas colocadas.
    private int tableroLocacionX; // locación del tablero en el eje de las X
    private int tableroLocacionY; // locación del tablero en el eje de las Y

    private int anchoFichaTablero; // ancho de las fichas dentro del tablero
    private int largoFichaTablero; // alto de las fichas dentro del tablero

    // Configuración de la partida
    private int numeroDeJugadores; // Número de jugadores dentro de la partida

    // Partida
    private TableroDominoDTO tableroDominoDTO; // tablero con posiciones de las fichas
    private List<FichaDominoDTO> listaFichasJugadorLocal; // Lista de fichas del jugador del disposivo
    private List<FichaDominoView> listaPanelesFichasJugadorLocal; // Lista de panales de fichas del jugador del dispositivo
    private List<FichaDominoTablero> listaPanelesFichasSobreTablero; // Lista de paneles de fichas sobre el tablero
    private List<CasillaPanel> listaPanelesCasillasParaColocarFichas; // Lista de casillas para colocar fichas sobre el tablero;
    //private List<FichaDominoDTO>[] listasFichasJugadoresExternos; // Listas de fichas de los jugadores externos (Temporal, posiblemente se cambie por lista jugadores externos)

    private List<IPartidaDominoModelListener> listeners ;
    private PresentacionNotificacionesManager presentacionNotificacionesManager ;
    private FichaDominoDTO fichaSeleccionada ;
    
    public PartidaDominoModel() {
        this.fondoDePantalla = "/multimedia/FondoPartida.jpg";
        this.escala = 1.0f;
        this.anchoPantalla = 600;
        this.alturaPantalla = 400;
        this.anchoFichaJugadorLocal = 30;
        this.alturaFichaJugadorLocal = 60;
        this.anchoFichaJugadorExterno = 20;
        this.alturaFichaJugadorExterno = 40;
        this.alturaMinimaContenedorFichasJugadorLocal = 60;
        this.anchoMinimoContenedorFichasJugadorLocal = 30;
        this.contenedorFichasJugadorLocalLocacionY = 330;
        this.anchoTablero = 400;
        this.alturaTablero = 200;
        this.tableroLocacionX = 100;
        this.tableroLocacionY = 100;
        this.anchoFichaTablero = 15;
        this.largoFichaTablero = 30;
        this.numeroDeJugadores = 1; // temporal
        this.listaFichasJugadorLocal = new ArrayList<>();
        this.listaFichasJugadorLocal = new ArrayList<>();
        this.listaPanelesFichasSobreTablero = new ArrayList<>();
        listeners = new ArrayList() ;
    }

    public void setPresentacionNotificacionesManager(PresentacionNotificacionesManager presentacionNotificacionesManager) {
        this.presentacionNotificacionesManager = presentacionNotificacionesManager;
    }

    public PresentacionNotificacionesManager getPresentacionNotificacionesManager() {
        return presentacionNotificacionesManager;
    }
    
    public void notificarFichasJugadorChange (){
        listeners.forEach(listener -> {
            listener.onListaFichasDominoUsuarioChange();
        });
    }
    
    public void notificarFichaSeleccionadaChange(FichaDominoDTO fichaSeleccionada) {
        FichaSeleccionadaEvento evento = new FichaSeleccionadaEvento();
        evento.setFichaSeleccionada(fichaSeleccionada);
        presentacionNotificacionesManager.notificarCambioAPresentacionListener(evento);
    }

    public List<CasillaPanel> getListaPanelesCasillasParaColocarFichas() {
        return listaPanelesCasillasParaColocarFichas;
    }

    public void setListaPanelesCasillasParaColocarFichas(List<CasillaPanel> listaPanelesCasillasParaColocarFichas) {
        this.listaPanelesCasillasParaColocarFichas = listaPanelesCasillasParaColocarFichas;
    }

    public List<FichaDominoTablero> getListaPanelesFichasSobreTablero() {
        return listaPanelesFichasSobreTablero;
    }

    public void agregarPanelFichaSobreTablero(FichaDominoTablero fichaDominoTablero) {
        listaPanelesFichasSobreTablero.add(fichaDominoTablero);
    }
    
    public List<FichaDominoView> getListaPanelesFichasJugadorLocal() {
        return listaPanelesFichasJugadorLocal;
    }

    public void setListaPanelesFichasJugadorLocal(List<FichaDominoView> listaPanelesFichasJugadorLocal) {
        this.listaPanelesFichasJugadorLocal = listaPanelesFichasJugadorLocal;
    }

    public void setListaFichasJugadorLocal(List<FichaDominoDTO> listaFichasJugadorLocal) {
        this.listaFichasJugadorLocal = listaFichasJugadorLocal;
        notificarFichasJugadorChange();
    }

    public int getAlturaMinimaContenedorFichasJugadorLocal() {
        return alturaMinimaContenedorFichasJugadorLocal;
    }

    public int getAnchoMinimoContenedorFichasJugadorLocal() {
        return anchoMinimoContenedorFichasJugadorLocal;
    }

    public int getContenedorFichasJugadorLocalLocacionY() {
        return contenedorFichasJugadorLocalLocacionY;
    }

    public String getFondoDePantalla() {
        return fondoDePantalla;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public int getAlturaPantalla() {
        return alturaPantalla;
    }

    public int getAnchoFichaJugadorLocal() {
        return anchoFichaJugadorLocal;
    }

    public int getAlturaFichaJugadorLocal() {
        return alturaFichaJugadorLocal;
    }

    public int getAnchoFichaJugadorExterno() {
        return anchoFichaJugadorExterno;
    }

    public int getAlturaFichaJugadorExterno() {
        return alturaFichaJugadorExterno;
    }

    public int getAnchoTablero() {
        return anchoTablero;
    }

    public int getAlturaTablero() {
        return alturaTablero;
    }

    public int getTableroLocacionX() {
        return tableroLocacionX;
    }

    public int getTableroLocacionY() {
        return tableroLocacionY;
    }

    public int getAnchoFichaTablero() {
        return anchoFichaTablero;
    }

    public int getLargoFichaTablero() {
        return largoFichaTablero;
    }

    public TableroDominoDTO getTableroDominoDTO() {
        return tableroDominoDTO;
    }

    public List<FichaDominoDTO> getListaFichasJugadorLocal() {
        return listaFichasJugadorLocal;
    }

    public List<IPartidaDominoModelListener> getListenersView() {
        return listeners;
    }

    public void setListenersView(List<IPartidaDominoModelListener> listenersFichasUsuario) {
        this.listeners= listenersFichasUsuario;
    }
    
    public void agregarListener(IPartidaDominoModelListener listener) {
        this.listeners.add(listener) ;
    }

    public FichaDominoDTO getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(FichaDominoDTO fichaSeleccionada) {
        notificarFichaSeleccionadaChange(fichaSeleccionada);
        this.fichaSeleccionada = fichaSeleccionada;
    }

    
}
