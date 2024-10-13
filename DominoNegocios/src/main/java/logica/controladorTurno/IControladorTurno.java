/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.controladorTurno;

import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IControladorTurno {

    /**
     * Metodo para asignar los turnos a los jugadores
     *
     * Otorga el orden basandose en asignar al jugador con mula mas alta en
     * primer lugar y barajando el orden del resto de jugadores.
     */
    void asignarTurnos();

    /**
     * Metodo para encontrar al jugador con la ficha mula mas alta
     *
     * Recorre todos los jugadores y sus fichas, buscando la ficha mula con el
     * valor mas alto. Devuelve el jugador que tiene esa ficha.
     *
     * @return El jugador con la ficha mula mas alta
     */
    JugadorDominoEntity encontrarJugadorConMulaMasAlta();

    /**
     * Metodo para obtener el jugador actual que tiene el turno en este momento
     *
     * Este metodo devuelve el jugador que tiene el turno actualmente, en
     * función del indice de turno actual.
     *
     * @return El jugador actual
     */
    JugadorDominoEntity obtenerJugadorActual();

    /**
     * Metodo para avanzar al siguiente turno
     *
     * Cambia el turno al siguiente jugador en la lista.
     */
    void avanzarTurno();

    /**
     * Metodo que devuelve la ficha mula mas alta encontrada
     *
     * @return La ficha mula más alta
     */
    FichaDominoEntity obtenerFichaMulaMasAlta();
    
    void setJugadores(List<JugadorDominoEntity> jugadores);

}
