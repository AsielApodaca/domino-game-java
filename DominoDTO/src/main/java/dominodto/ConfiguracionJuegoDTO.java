package dominodto;

/**
 * Clase DTO que contiene la configuración del juego.
 * Incluye el nombre de la sala, el límite de jugadores y la cantidad de fichas distribuidas a cada jugador.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ConfiguracionJuegoDTO {
    // Configuración de la sala
    private String nombreSala; // Nombre de la sala
    private int limiteJugadores; // Tamaño máximo de la sala
    private int fichasPorJugador; // Cantidad de fichas que se distribuye a cada jugador al iniciar la partida

    public ConfiguracionJuegoDTO() {
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
