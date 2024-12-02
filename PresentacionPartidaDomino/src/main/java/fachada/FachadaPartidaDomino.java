package fachada;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.util.List;
import listeners.IContenedorListener;
import listeners.IPresentacionPartidaDominoListener;
import mediador.MediadorSingletonPantallaFichasJugador;
import mediadorsingleton.MediadorSingletonContenedorContenido;
import mvcpartidadomino.PartidaDominoController;
import mvcpartidadomino.PartidaDominoModel;
import mvcpartidadomino.PartidaDominoView;
import notificador.PresentacionPartidaDominoNotificador;
import notificador.IPresentacionPartidaDominoNotificador;

/**
 * Fachada para la lógica de presentación de la partida de dominó. 
 * Simplifica la interacción entre las capas de la aplicación, centralizando
 * la gestión de la vista, el modelo y el controlador en la partida.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FachadaPartidaDomino implements IFachadaPartidaDomino {

    private final MediadorSingletonContenedorContenido mdSgContenedorContenido = MediadorSingletonContenedorContenido.getInstance();
    private PartidaDominoController partidaDominoController;
    private PartidaDominoModel partidaDominoModel;
    private PartidaDominoView partidaDominoView;
    private final MediadorSingletonPantallaFichasJugador mediador = MediadorSingletonPantallaFichasJugador.getInstance();

    /**
     * Constructor por defecto de la fachada.
     */
    public FachadaPartidaDomino() {
    }

    /**
     * Inicia la pantalla de la partida de dominó.
     * Configura el modelo, la vista y el controlador necesarios para gestionar la partida.
     * 
     * @return Un listener asociado al contenedor que gestiona la vista.
     */
    @Override
    public IContenedorListener iniciarPantalla() {
        this.partidaDominoModel = new PartidaDominoModel();
        this.partidaDominoView = new PartidaDominoView(partidaDominoModel);
        this.partidaDominoController = new PartidaDominoController(partidaDominoModel, partidaDominoView);
        this.mediador.setPartidaDominoController(partidaDominoController);
        mdSgContenedorContenido.setContenedorListener(partidaDominoController);
        mdSgContenedorContenido.mostrarPantalla();
        this.partidaDominoController.showView();

        return (IContenedorListener) this.partidaDominoController;
    }

    /**
     * Muestra las fichas del jugador local en la interfaz de usuario.
     * 
     * @param fichasJugador Lista de fichas que pertenecen al jugador local.
     */
    @Override
    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichasJugador) {
        partidaDominoController.mostrarFichasJugadorLocal(fichasJugador);
    }

    /**
     * Elimina una ficha específica del jugador local.
     * 
     * @param fichaDominoDTO Objeto que representa la ficha a eliminar.
     */
    @Override
    public void quitarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO) {
        partidaDominoController.removerFichaJugadorLocal(fichaDominoDTO);
    }

    /**
     * Agrega una ficha específica al conjunto de fichas del jugador local.
     * 
     * @param fichaDominoDTO Objeto que representa la ficha a agregar.
     */
    @Override
    public void agregarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO) {
        partidaDominoController.agregarFichaJugadorLocal(fichaDominoDTO);
    }

    /**
     * Coloca una ficha en una casilla específica del tablero.
     * 
     * @param casillaDTO Objeto que representa la casilla donde se colocará la ficha.
     */
    @Override
    public void colocarFichaTablero(CasillaDTO casillaDTO) {
        partidaDominoController.colocarFichaTablero(casillaDTO);
    }

    /**
     * Muestra las casillas disponibles donde el jugador puede colocar fichas.
     * 
     * @param casillasDTO Lista de casillas disponibles.
     */
    @Override
    public void mostrarCasillasParaColocarFicha(List<CasillaDTO> casillasDTO) {
        partidaDominoController.mostrarCasillasParaColocarFicha(casillasDTO);
    }

    /**
     * Oculta las casillas previamente mostradas como disponibles para colocar fichas.
     */
    @Override
    public void ocultarCasillasParaColocarFicha() {
        partidaDominoController.ocultarCasillasParaColocarFicha();
    }

    /**
     * Suscribe un listener para gestionar eventos de la presentación de la partida.
     * 
     * @param listener Listener a suscribir.
     */
    @Override
    public void suscribirPresentacionListener(IPresentacionPartidaDominoListener listener) {
        IPresentacionPartidaDominoNotificador presentacionNotificadorManager
                = new PresentacionPartidaDominoNotificador();
        presentacionNotificadorManager.suscribirPresentacionListener(listener);
        partidaDominoModel.setPresentacionNotificacionesManager(presentacionNotificadorManager);
    }

    /**
     * Muestra el pozo de fichas disponibles en la interfaz de usuario.
     */
    @Override
    public void mostrarPozo() {
        partidaDominoController.mostrarPozoDisponible();
    }

    /**
     * Oculta el pozo de fichas disponibles en la interfaz de usuario.
     */
    @Override
    public void ocultarPozo() {
        partidaDominoController.ocultarPozoDisponible();
    }

    /**
     * Muestra los jugadores en el orden actual en la interfaz de usuario.
     * 
     * @param jugadoresEnOrden Lista de jugadores ordenados.
     */
    @Override
    public void mostrarJugadores(List<JugadorDominoDTO> jugadoresEnOrden) {
        partidaDominoController.mostrarJugadores(jugadoresEnOrden);
    }

    /**
     * Elimina a un jugador de la interfaz de usuario.
     * 
     * @param jugadorDominoDTO Objeto que representa al jugador a eliminar.
     */
    @Override
    public void removerJugador(JugadorDominoDTO jugadorDominoDTO) {
        partidaDominoController.removerJugador(jugadorDominoDTO);
    }

    /**
     * Actualiza la cantidad de fichas de un jugador específico.
     * 
     * @param jugadorDominoDTO Objeto que representa al jugador.
     * @param cantidadFichas Nueva cantidad de fichas del jugador.
     */
    @Override
    public void actualizarCantidadFichasDeJugador(JugadorDominoDTO jugadorDominoDTO, int cantidadFichas) {
        partidaDominoController.actualizarCantidadFichasJugador(jugadorDominoDTO, cantidadFichas);
    }

    /**
     * Otorga el turno a un jugador específico.
     * 
     * @param jugadorDominoDTO Objeto que representa al jugador que recibe el turno.
     */
    @Override
    public void otorgarTurnoAJugador(JugadorDominoDTO jugadorDominoDTO) {
        partidaDominoController.otorgarTurnoAJugador(jugadorDominoDTO);
    }
}
