/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class SalaEntity {

    // Configuracion de la sala
    private JugadorDominoAdmin jugadorAdmin; // Creador de la sala
    private String nombreSala; // Nombre de la sala
    private int limiteJugadores; // Tamaño máximo de la sala
    private int fichasPorJugador; // Cantidad de fichas que se distribuye a cada jugador al iniciar la partida

    // Juego
    private List<JugadorDominoEntity> listaJugadores; // Lista de jugadores en la sala
    private PartidaEntity partida; // Partida en juego

    public SalaEntity() {
        this.listaJugadores = new ArrayList<>();
        agregarJugadoresProvicional();
    }

    private void agregarJugadoresProvicional() {
        JugadorDominoEntity jugador1 = new JugadorDominoEntity("Juan");
        JugadorDominoEntity jugador2 = new JugadorDominoEntity("Francis");
        JugadorDominoEntity jugador3 = new JugadorDominoEntity("Minerva");
        JugadorDominoEntity jugador4 = new JugadorDominoEntity("Hermione");
        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);
        listaJugadores.add(jugador3);
        listaJugadores.add(jugador4);
    }

}
