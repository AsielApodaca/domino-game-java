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
     * Agrega una lista de jugadores al controlador.
     * 
     * @param listaJugadores Lista de jugadores a agregar.
     */
    public void agregarJugadores(List<JugadorDominoEntity> listaJugadores);

    /**
     * Obtiene la lista de todos los jugadores.
     * 
     * @return Lista de jugadores.
     */
    public List<JugadorDominoEntity> obtenerJugadores();

    /**
     * Obtiene un jugador a partir de su identificador de cliente.
     * 
     * @param idCliente El identificador del cliente del jugador.
     * @return El jugador correspondiente al idCliente, o null si no se encuentra.
     */
    public JugadorDominoEntity obtenerJugadorPorIdCliente(String idCliente);
}
