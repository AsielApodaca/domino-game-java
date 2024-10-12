/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

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
    private JugadorDominoEntity listaJugadores; // Lista de jugadores en la sala
    private PartidaEntity partida; // Partida en juego
     
    
    
    
}
