package fachada;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.util.List;
import listeners.IContenedorListener;
import listeners.IPresentacionPartidaDominoListener;

/**
 * Interfaz para la fachada de la partida de dominó, que actúa como punto
 * central de comunicación entre la lógica del juego y la presentación. Permite
 * gestionar la interacción con la interfaz gráfica y los eventos del juego.
 * 
 * Define métodos para manipular elementos visuales, como fichas, jugadores y
 * casillas, además de gestionar la interacción entre la partida y su 
 * representación visual.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IFachadaPartidaDomino {

    /**
     * Inicia la pantalla principal de la partida de dominó.
     *
     * @return un objeto que implementa {@link IContenedorListener}, el cual
     * gestiona la pantalla.
     */
    IContenedorListener iniciarPantalla();

    /**
     * Muestra las fichas del jugador local en la interfaz gráfica.
     *
     * @param fichasJugador la lista de fichas del jugador local.
     */
    void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichasJugador);

    /**
     * Elimina una ficha específica del jugador local de la interfaz gráfica.
     *
     * @param fichaDominoDTO la ficha a quitar del jugador local.
     */
    void quitarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO);

    /**
     * Agrega una ficha específica al conjunto de fichas del jugador local en la
     * interfaz gráfica.
     *
     * @param fichaDominoDTO la ficha a agregar al jugador local.
     */
    void agregarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO);

    /**
     * Coloca una ficha en el tablero en la casilla especificada.
     *
     * @param casillaDTO la casilla donde se colocará la ficha.
     */
    void colocarFichaTablero(CasillaDTO casillaDTO);

    /**
     * Resalta las casillas disponibles en el tablero donde se puede colocar una
     * ficha.
     *
     * @param casillasDTO la lista de casillas disponibles para colocar fichas.
     */
    void mostrarCasillasParaColocarFicha(List<CasillaDTO> casillasDTO);

    /**
     * Oculta las casillas resaltadas previamente que indican lugares
     * disponibles para colocar fichas.
     */
    void ocultarCasillasParaColocarFicha();

    /**
     * Muestra el pozo de fichas disponibles en la interfaz gráfica.
     */
    void mostrarPozo();

    /**
     * Oculta el pozo de fichas disponibles de la interfaz gráfica.
     */
    void ocultarPozo();

    /**
     * Suscribe un listener para manejar eventos de la presentación de la
     * partida de dominó.
     *
     * @param listener el listener que será notificado de los eventos de la
     * presentación.
     */
    void suscribirPresentacionListener(IPresentacionPartidaDominoListener listener);

    /**
     * Muestra los jugadores en el orden especificado en la interfaz gráfica.
     *
     * @param jugadoresEnOrden lista de jugadores ordenados.
     */
    void mostrarJugadores(List<JugadorDominoDTO> jugadoresEnOrden);

    /**
     * Remueve a un jugador de la interfaz gráfica.
     *
     * @param jugadorDominoDTO el jugador a remover.
     */
    void removerJugador(JugadorDominoDTO jugadorDominoDTO);

    /**
     * Actualiza la cantidad de fichas de un jugador específico en la interfaz gráfica.
     *
     * @param jugadorDominoDTO el jugador cuya cantidad de fichas será actualizada.
     * @param cantidadFichas la nueva cantidad de fichas del jugador.
     */
    void actualizarCantidadFichasDeJugador(JugadorDominoDTO jugadorDominoDTO, int cantidadFichas);

    /**
     * Asigna el turno a un jugador específico en la interfaz gráfica.
     *
     * @param jugadorDominoDTO el jugador que recibe el turno.
     */
    void otorgarTurnoAJugador(JugadorDominoDTO jugadorDominoDTO);
    
    void mostrarResultadoPartida();
}
