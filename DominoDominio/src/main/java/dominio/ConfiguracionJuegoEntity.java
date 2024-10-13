/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author hisam
 */
public class ConfiguracionJuegoEntity {
    
    // Configuracion de la sala
    private JugadorDominoAdmin jugadorAdmin; // Creador de la sala
    private String nombreSala; // Nombre de la sala
    private int limiteJugadores; // Tamaño máximo de la sala
    private int fichasPorJugador; // Cantidad de fichas que se distribuye a cada jugador al iniciar la partida

    public ConfiguracionJuegoEntity() {
    }

    public JugadorDominoAdmin getJugadorAdmin() {
        return jugadorAdmin;
    }

    public void setJugadorAdmin(JugadorDominoAdmin jugadorAdmin) {
        this.jugadorAdmin = jugadorAdmin;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public int getLimiteJugadores() {
        return limiteJugadores;
    }

    public void setLimiteJugadores(int limiteJugadores) {
        this.limiteJugadores = limiteJugadores;
    }

    public int getFichasPorJugador() {
        return fichasPorJugador;
    }

    public void setFichasPorJugador(int fichasPorJugador) {
        this.fichasPorJugador = fichasPorJugador;
    }

    
    
    
}
