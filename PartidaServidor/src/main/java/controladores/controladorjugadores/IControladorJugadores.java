package controladores.controladorjugadores;

import dominio.JugadorDominoEntity;
import java.util.List;

/**
 * Interfaz que define los métodos para gestionar los jugadores en el juego de dominó.
 * Permite agregar jugadores, obtener la lista de jugadores y buscar un jugador
 * por su identificador de cliente.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IControladorJugadores {

    /**
     * Agrega un jugador al controlador.
     * 
     * @param jugador El jugador a agregar.
     */
    void agregarJugador(JugadorDominoEntity jugador);

    /**
     * Elimina un jugador del controlador usando su identificador de cliente.
     * 
     * @param idCliente El identificador del cliente asociado al jugador.
     */
    void eliminarJugadorPorIdCliente(String idCliente);

    /**
     * Obtiene la lista de todos los jugadores.
     * 
     * @return Lista de jugadores.
     */
    List<JugadorDominoEntity> obtenerJugadores();

    /**
     * Obtiene un jugador a partir de su identificador de cliente.
     * 
     * @param idCliente El identificador del cliente del jugador.
     * @return El jugador correspondiente al idCliente, o null si no se encuentra.
     */
    JugadorDominoEntity obtenerJugadorPorIdCliente(String idCliente);
}
