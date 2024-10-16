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


    private ConfiguracionJuegoEntity configuracionPartida; // Configuración de la partida
    private List<JugadorDominoEntity> listaJugadores; // Lista de jugadores en la sala
    private JugadorDominoEntity jugadorLocal; // Jugador de este dispositivo
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
        jugadorLocal = jugador1;
    }

    public ConfiguracionJuegoEntity getConfiguracionPartida() {
        return configuracionPartida;
    }

    public void setConfiguracionPartida(ConfiguracionJuegoEntity configuracionPartida) {
        this.configuracionPartida = configuracionPartida;
    }

    public List<JugadorDominoEntity> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<JugadorDominoEntity> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public JugadorDominoEntity getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(JugadorDominoEntity jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    public PartidaEntity getPartida() {
        return partida;
    }

    public void setPartida(PartidaEntity partida) {
        this.partida = partida;
    }
    
    

}
