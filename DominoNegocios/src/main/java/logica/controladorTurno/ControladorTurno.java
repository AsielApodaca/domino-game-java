/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controladorTurno;

import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ControladorTurno implements IControladorTurno {

    private List<JugadorDominoEntity> jugadores;
    private int jugadorActualIndex;
    private boolean turnosAsignados;
    private FichaDominoEntity fichaMulaMasAlta;

    public ControladorTurno(List<JugadorDominoEntity> jugadores) {
        this.jugadores = jugadores;
        this.jugadorActualIndex = 0;
        this.turnosAsignados = false; 
        this.fichaMulaMasAlta = null;
    }

    /**
     * Metodo para asignar el orden de los turnos entre la lista de jugadores
     *
     * Este proceso se realiza considerando al jugador con la ficha mula mas
     * alta, este pasa a ser el jugadorActualIndex osea el que se encuentra con
     * el indice 0 y de manera aleatoria asignando el resto de los turnos entre
     * los jugadores restantes.
     *
     * El proceso de asignarTurno solo debe ocurrir una sola vez
     */
    @Override
    public void asignarTurno() {
        if (turnosAsignados) {
            throw new IllegalStateException("Los turnos ya han sido asignados");
        }
        JugadorDominoEntity jugadorConMulaMasAlta = encontrarJugadorConMulaMasAlta();
        if (jugadorConMulaMasAlta != null) {
            // Mover el jugador con la mula más alta al inicio de la lista
            jugadores.remove(jugadorConMulaMasAlta);
            Collections.shuffle(jugadores);
            jugadores.add(0, jugadorConMulaMasAlta);
        } else {
            // Si no hay mulas, barajar la lista de jugadores aleatoriamente
            Collections.shuffle(jugadores);
        }
        jugadorActualIndex = 0; //Asegura que el primer jugador en tomar su turno es el que está en la posicion 0
        turnosAsignados = true; //Indica que ya se asignaron los turnos y ya no volverá a ocurrir
    }

    /**
     * Metodo que obtiene el jugador actual
     *
     * Obtiene de la lista de jugadores al cual se encuentre en la primer
     * posicion del index (posicion 0)
     *
     * @return El jugador que actualmente tiene el turno
     */
    @Override
    public JugadorDominoEntity obtenerJugadorActual() {
        if (!turnosAsignados) {
            throw new IllegalStateException("Los turnos aun no han sido asignados");
        }
        return jugadores.get(jugadorActualIndex);
    }

    /**
     * Metodo para avanzar al siguiente turno
     *
     * Este método cambia el índice del jugador actual para pasar al siguiente
     * jugador en la lista.
     */
    @Override
    public void avanzarTurno() {
        if (!turnosAsignados) {
            throw new IllegalStateException("Los turnos aun no han sido asignados");
        }
        jugadorActualIndex = (jugadorActualIndex + 1) % jugadores.size();
    }

    /**
     * Metodo que devuelve la ficha mula mas alta encontrada
     *
     * Regresa la ficha que ha sido previamente identificada como la mula mas
     * alta.
     *
     * @return Ficha Mula mas alta
     */
    @Override
    public FichaDominoEntity obtenerFichaMulaMasAlta() {
        return fichaMulaMasAlta;
    }

    /**
     * Metodo que verifica si una ficha de domino es una mula
     *
     * Regresa un boolean confirmando si la ficha cuenta como mula.
     *
     * @param ficha
     * @return true cuando la ficha tiene el mismo valor en ambos extremos y por
     * lo tanto es mula
     */
    private boolean esMula(FichaDominoEntity ficha) {
        return ficha.getExtremo1().equals(ficha.getExtremo2());
    }

    /**
     * Metodo que encuentra al jugador con la ficha mula mas alta
     *
     * Este metodo recorre la lista de jugadores y recorre sus respectivas
     * fichas para encontrar la mula mas alta. Si se encuentra una mula, se
     * guarda como la ficha mula mas alta y se registra al jugador que la tiene.
     *
     * @return El jugador que tiene la mula con el valor mas alto
     */
    @Override
    public JugadorDominoEntity encontrarJugadorConMulaMasAlta() {
        JugadorDominoEntity jugadorConMulaMasAlta = null;
        fichaMulaMasAlta = null;
        for (JugadorDominoEntity jugador : jugadores) {
            for (FichaDominoEntity ficha : jugador.getListaFichasJugador()) {
                if (esMula(ficha)) {
                    //Al haber ya validado antes que la ficha es mula, podemos solamente comparar uno de los extremos sin problemas
                    if (fichaMulaMasAlta == null || ficha.getExtremo1() > fichaMulaMasAlta.getExtremo1()) {
                        fichaMulaMasAlta = ficha;
                        jugadorConMulaMasAlta = jugador;
                    }
                }
            }
        }
        return jugadorConMulaMasAlta;
    }
}
