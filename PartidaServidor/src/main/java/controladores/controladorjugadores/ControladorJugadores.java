package controladores.controladorjugadores;

import dominio.JugadorDominoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar los jugadores en el juego de dominó.
 * Permite agregar jugadores, obtener la lista de jugadores y buscar un jugador
 * por su identificador de cliente.
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ControladorJugadores implements IControladorJugadores {

    private List<JugadorDominoEntity> listaJugadores;

    public ControladorJugadores() {
        this.listaJugadores = new ArrayList<>();
    }

    
    /**
     * Agrega un jugador a la lista de jugadores.
     *
     * @param jugador el jugador a agregar.
     */
    @Override
    public void agregarJugador(JugadorDominoEntity jugador) {
        listaJugadores.add(jugador);
    }

    
    /**
     * Elimina un jugador de la lista según su identificador de cliente. Si no
     * se encuentra el jugador, no realiza ninguna acción.
     *
     * @param idCliente el identificador del cliente del jugador a eliminar.
     */
    @Override
    public void eliminarJugadorPorIdCliente(String idCliente) {
        JugadorDominoEntity jugador = obtenerJugadorPorIdCliente(idCliente);
        if (jugador != null) {
            listaJugadores.remove(jugador);
        } else {
            // Manejo opcional para el caso en que no se encuentra el jugador
            System.out.println("Jugador con ID: " + idCliente + " no encontrado.");
        }
    }

    /**
     * Obtiene la lista de todos los jugadores.
     *
     * @return Lista de jugadores.
     */
    @Override
    public List<JugadorDominoEntity> obtenerJugadores() {
        return listaJugadores;
    }

    /**
     * Obtiene un jugador a partir de su identificador de cliente.
     *
     * @param idCliente El identificador del cliente del jugador.
     * @return El jugador correspondiente al idCliente, o null si no se
     * encuentra.
     */
    @Override
    public JugadorDominoEntity obtenerJugadorPorIdCliente(String idCliente) {
        for (JugadorDominoEntity jugador : listaJugadores) {
            if (jugador.getIdCliente().equals(idCliente)) {
                return jugador;
            }
        }
        return null;
    }
}
