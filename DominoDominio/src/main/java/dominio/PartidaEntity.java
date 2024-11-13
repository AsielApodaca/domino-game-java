/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaEntity {
    
    private JugadorDominoEntity ganador; // Ganador de la partida
    private JugadorDominoEntity turnoDeJugador; // Jugador con el turno de colocar ficha

    public PartidaEntity(JugadorDominoEntity ganador, JugadorDominoEntity turnoDeJugador) {
        this.ganador = ganador;
        this.turnoDeJugador = turnoDeJugador;
    }

    public JugadorDominoEntity getGanador() {
        return ganador;
    }

    public void setGanador(JugadorDominoEntity ganador) {
        this.ganador = ganador;
    }

    public JugadorDominoEntity getTurnoDeJugador() {
        return turnoDeJugador;
    }

    public void setTurnoDeJugador(JugadorDominoEntity turnoDeJugador) {
        this.turnoDeJugador = turnoDeJugador;
    }
    
}
