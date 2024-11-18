package fachada;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.List;
import listeners.IContenedorListener;
import listeners.IPresentacionPartidaDominoListener;

/**
 * Interfaz para la fachada de la partida de dominó, que actúa como punto central 
 * de comunicación entre la lógica del juego y la presentación. 
 * Permite gestionar la interacción con la interfaz gráfica y los eventos del juego.
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
     * @return un objeto que implementa {@link IContenedorListener}, que gestiona la pantalla.
     */
    public IContenedorListener iniciarPantalla();

    /**
     * Muestra las fichas del jugador local en la interfaz gráfica.
     * 
     * @param fichasJugador la lista de fichas del jugador local.
     */
    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichasJugador);

    /**
     * Elimina una ficha específica del jugador local de la interfaz gráfica.
     * 
     * @param fichaDominoDTO la ficha a quitar del jugador local.
     */
    public void quitarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO);

    /**
     * Agrega una ficha específica al conjunto de fichas del jugador local en la interfaz gráfica.
     * 
     * @param fichaDominoDTO la ficha a agregar al jugador local.
     */
    public void agregarFichaJugadorLocal(FichaDominoDTO fichaDominoDTO);

    /**
     * Coloca una ficha en el tablero en la casilla especificada.
     * 
     * @param casillaDTO la casilla donde se colocará la ficha.
     */
    public void colocarFichaTablero(CasillaDTO casillaDTO);

    /**
     * Resalta las casillas disponibles en el tablero donde se puede colocar una ficha.
     * 
     * @param casillasDTO la lista de casillas disponibles para colocar fichas.
     */
    public void mostrarCasillasParaColocarFicha(List<CasillaDTO> casillasDTO);

    /**
     * Oculta las casillas resaltadas previamente que indican lugares disponibles
     * para colocar fichas.
     */
    public void ocultarCasillasParaColocarFicha();

    /**
     * Suscribe un listener para manejar eventos de la presentación de la partida de dominó.
     * 
     * @param listener el listener que será notificado de los eventos de la presentación.
     */
    public void suscribirPresentacionListener(IPresentacionPartidaDominoListener listener);
}
