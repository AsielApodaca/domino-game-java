package controladores.controladorturnos;

import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import java.util.Collections;
import java.util.List;

/**
 * Controlador encargado de gestionar los turnos de los jugadores durante el
 * juego de dominó.
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ControladorTurnos implements IControladorTurnos {

    private List<JugadorDominoEntity> ordenJugadores;
    private int turnoActual;
    private int cantidadDeTurnosOmitidos;

    /**
     * Constructor que inicializa el controlador
     */
    public ControladorTurnos() { 
        this.cantidadDeTurnosOmitidos = 0;
    }

    /**
     * Asigna los turnos a los jugadores. El jugador con la mula más alta
     * comienza. Si no hay mulas, los turnos se asignan de forma aleatoria.
     *
     * Este método organiza la lista de jugadores para que el jugador con la
     * mula más alta (si existe) ocupe la primera posición, garantizando que
     * inicie el turno. Si no hay mulas, simplemente baraja la lista de
     * jugadores aleatoriamente para asignar los turnos de manera equitativa.
     *
     * @param listaJugadores La lista de jugadores que participarán en la
     * partida.
     */
    @Override
    public void asignarTurnosAJugadores(List<JugadorDominoEntity> listaJugadores) {
        this.ordenJugadores = listaJugadores;
        this.turnoActual = listaJugadores.size() - 1;
        JugadorDominoEntity jugadorConMulaMasAlta = encontrarJugadorConMulaMasAlta();
        if (jugadorConMulaMasAlta != null) {
            // Mueve al jugador con la mula más alta al inicio de la lista
            ordenJugadores.remove(jugadorConMulaMasAlta);
            Collections.shuffle(ordenJugadores);
            ordenJugadores.add(0, jugadorConMulaMasAlta);
        } else {
            // Si no hay mulas, baraja la lista de jugadores aleatoriamente
            Collections.shuffle(ordenJugadores);
        }
    }

    /**
     * Obtiene el jugador al que le corresponde el siguiente turno.
     *
     * @return el jugador con el turno actual.
     */
    @Override
    public JugadorDominoEntity obtenerSiguienteTurno() {
        int cantidadJugadores = ordenJugadores.size();
        int nuevoTurno = (turnoActual + cantidadJugadores + 1) % cantidadJugadores;
        turnoActual = nuevoTurno;
        return ordenJugadores.get(nuevoTurno);
    }

    /**
     * Busca al jugador con la mula más alta en su mano.
     *
     * @return el jugador con la mula más alta, o {@code null} si ninguno tiene
     * mulas.
     */
    private JugadorDominoEntity encontrarJugadorConMulaMasAlta() {
        JugadorDominoEntity jugadorConMulaMasAlta = null;
        FichaDominoEntity fichaMulaMasAlta = null;

        // Recorre las fichas de cada jugador
        for (JugadorDominoEntity jugador : ordenJugadores) {
            for (FichaDominoEntity ficha : jugador.getListaFichasJugador()) {
                // Si la ficha es una mula
                if (ficha.getExtremo1().equals(ficha.getExtremo2())) {
                    // Compara el valor de la mula actual con la más alta registrada
                    if (fichaMulaMasAlta == null || ficha.getExtremo1() > fichaMulaMasAlta.getExtremo1()) {
                        fichaMulaMasAlta = ficha;
                        jugadorConMulaMasAlta = jugador;
                    }
                }
            }
        }
        return jugadorConMulaMasAlta;
    }
    
    @Override
    public int turnoOmitido() {
        cantidadDeTurnosOmitidos++;
        return cantidadDeTurnosOmitidos;
    }
    @Override
    public void reiniciarContadorTurnosOmitidos() {
        cantidadDeTurnosOmitidos = 0;
    }
}
