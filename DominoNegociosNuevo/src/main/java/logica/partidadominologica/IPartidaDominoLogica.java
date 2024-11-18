package logica.partidadominologica;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import listeners.IContenedorListener;

/**
 * Interfaz que define las operaciones lógicas para iniciar y manejar una partida de dominó.
 * Permite el inicio de la partida, la actualización del estado del juego y la interacción con
 * los jugadores y el tablero.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPartidaDominoLogica {

    /**
     * Inicia una nueva partida de dominó y devuelve un contenedor para la interacción.
     * 
     * @return un objeto que implementa {@link IContenedorListener}, que maneja
     *         la interacción durante la partida.
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
     * Muestra una ficha en el tablero, colocando la ficha en la casilla correspondiente.
     * 
     * @param casilla la casilla en la que se debe mostrar la ficha en el tablero.
     */
    public void mostrarFichaTablero(CasillaDTO casilla);
    
    /**
     * Remueve una ficha del jugador local, en caso de que haya sido jugada o descartada.
     * 
     * @param ficha la ficha que se debe remover del jugador local.
     */
    public void removerFichaJugadorLocal(FichaDominoDTO ficha);
    
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
}
