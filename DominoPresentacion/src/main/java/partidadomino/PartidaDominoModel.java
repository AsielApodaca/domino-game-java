/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino;

import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class PartidaDominoModel {
    
    // Medidas originales
    private int anchoPantalla; // ancho del panel de la pantalla
    private int alturaPantalla; // altura del panel de la pantalla
    
    private int anchoFichaJugadorLocal; // ancho de las fichas del jugador del dispositivo
    private int alturaFichaJugadorLocal; // ancho de las fichas del jugador del dispositivo
    
    private int anchoFichaJugadorExterno; // ancho de las fichas de los jugadores externos.
    private int alturaFichaJugadorExterno; // altura de las fichas de los jugadores externos.
    
    private int anchoTablero; // ancho del panel del tablero donde iran las fichas colocadas.
    private int alturaTablero; // altura del panel del tablero donde iran las fichas colocadas.
    private int tableroLocacionX; // locación del tablero en el eje de las X
    private int tableroLocacionY; // locación del tablero en el eje de las Y
    
    // Configuración de la partida
    private int numeroDeJugadores; // Número de jugadores dentro de la partida
    
    // Partida
    private List<FichaDominoDTO> listaFichasJugadorLocal; // Lista de fichas del jugador del disposivo
    //private List<FichaDominoDTO>[] listasFichasJugadoresExternos; // Listas de fichas de los jugadores externos (Temporal, posiblemente se cambie por lista jugadores externos)
    
    
}
