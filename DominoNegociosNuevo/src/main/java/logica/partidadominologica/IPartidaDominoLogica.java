package logica.partidadominologica;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.util.List;
import listeners.IContenedorListener;

/**
 * Interfaz que define las operaciones lógicas para iniciar y manejar una
 * partida de dominó. Permite el inicio de la partida, la actualización del
 * estado del juego y la interacción con los jugadores y el tablero.
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPartidaDominoLogica {

    /**
     * Inicia una nueva partida de dominó y devuelve un contenedor para la
     * interacción.
     *
     * @return un objeto que implementa {@link IContenedorListener}, que maneja
     * la interacción durante la partida.
     */
    public IContenedorListener iniciar();

    /**
     * Actualiza la cantidad de fichas de un jugador durante la partida.
     *
     * @param jugador el jugador cuyo número de fichas se actualizará.
     * @param cantidad la nueva cantidad de fichas del jugador.
     */
    public void actualizarCantidadFichasDeJugador(JugadorDominoDTO jugador, int cantidad);

    /**
     * Muestra una ficha en el tablero, colocando la ficha en la casilla
     * correspondiente.
     *
     * @param casilla la casilla en la que se debe mostrar la ficha en el
     * tablero.
     */
    public void mostrarFichaTablero(CasillaDTO casilla);

    /**
     * Muestra las fichas de un jugador local.
     *
     * Este método es responsable de mostrar las fichas disponibles de un
     * jugador local en la interfaz. La implementación concreta deberá
     * encargarse de mostrar las fichas de acuerdo a la lógica de presentación
     * del juego.
     *
     * @param fichas La lista de fichas del jugador local a mostrar.
     */
    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichas);

    /**
     * Agrega una ficha al conjunto de fichas del jugador local en la interfaz
     * gráfica.
     *
     * @param ficha la ficha que se debe agregar al jugador local.
     */
    public void agregarFichaJugadorLocal(FichaDominoDTO ficha);

    /**
     * Cambia el turno de la partida, pasando el turno al siguiente jugador.
     *
     * @param jugador el jugador al que se le asignará el siguiente turno.
     */
    public void cambiarTurno(JugadorDominoDTO jugador);

    /**
     * Muestra las casillas disponibles en el tablero donde se pueden colocar
     * fichas.
     *
     * Este método es responsable de mostrar en la interfaz las casillas donde
     * un jugador puede colocar fichas en el tablero. La implementación concreta
     * deberá reflejar las casillas disponibles según la lógica del juego.
     *
     * @param casillasDisponibles La lista de casillas disponibles donde se
     * pueden colocar fichas.
     */
    public void mostrarCasillasDisponibles(List<CasillaDTO> casillasDisponibles);

    /**
     * Oculta las casillas disponibles en el tablero.
     *
     * Este método es responsable de ocultar cualquier casilla que se haya
     * mostrado previamente como disponible para colocar una ficha. Es útil para
     * restablecer el estado del tablero después de realizar una jugada o al
     * cambiar de turno.
     */
    public void ocultarCasillasDisponibles();

}
