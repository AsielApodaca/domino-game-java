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
     * Agrega una lista de jugadores al controlador.
     * 
     * @param listaJugadores Lista de jugadores a agregar.
     */
    @Override
    public void agregarJugadores(List<JugadorDominoEntity> listaJugadores) {
        this.listaJugadores = listaJugadores;
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
     * @return El jugador correspondiente al idCliente, o null si no se encuentra.
     */
    @Override
    public JugadorDominoEntity obtenerJugadorPorIdCliente(String idCliente) {
        for (JugadorDominoEntity jugador : listaJugadores) {
            if (jugador.getIdCliente().equals(idCliente))
                return jugador;
        }
        return null;
    }
}
