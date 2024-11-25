package controladores.controladorfichas;

import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import dominio.PozoEntity;
import java.util.List;

/**
 * Controlador que gestiona la lógica relacionada con las fichas del juego de dominó.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ControladorFichas implements IControladorFichas {

    private PozoEntity pozo;
    private List<FichaDominoEntity> listaFichasDelJuego;
    private FichaDominoEntity fichaSeleccionada;

    public ControladorFichas() {
    }
    
    /**
     * Crea un pozo de fichas y lo inicializa con todas las fichas del juego.
     */
    @Override
    public void crearPozo() {
        this.pozo = new PozoEntity();
        this.listaFichasDelJuego = pozo.getListaFichas();
    }

    /**
     * Obtiene la lista completa de fichas del juego.
     * 
     * @return una lista con todas las fichas disponibles en el juego.
     */
    @Override
    public List<FichaDominoEntity> obtenerTodasLasFichasDelJuego() {
        return listaFichasDelJuego;
    }

    /**
     * Reparte una cantidad específica de fichas a cada jugador.
     * 
     * @param jugadores lista de jugadores que recibirán fichas.
     * @param cantidadFichas número de fichas a repartir por jugador.
     */
    @Override
    public void repartirFichasAJugadores(List<JugadorDominoEntity> jugadores, int cantidadFichas) {
        for (JugadorDominoEntity jugador : jugadores) {
            jugador.agregarFichas(pozo.sacarFichas(cantidadFichas));
        }
    }

    /**
     * Agrega una ficha del pozo al jugador especificado.
     * 
     * @param jugador jugador al que se le agregará una ficha.
     */
    @Override
    public void agregarFichaAJugador(JugadorDominoEntity jugador) {
        jugador.agregarFichas(pozo.sacarFichas(1));
    }

    /**
     * Remueve una ficha específica de la lista de fichas de un jugador.
     * 
     * @param jugador jugador al que se le quitará la ficha.
     * @param ficha ficha a ser removida del jugador.
     */
    @Override
    public void quitarFichaAJugador(JugadorDominoEntity jugador, FichaDominoEntity ficha) {
        jugador.quitarFicha(ficha);
    }

    /**
     * Marca una ficha como seleccionada en el juego.
     * 
     * @param fichaDomino ficha que se seleccionará.
     */
    @Override
    public void seleccionarFicha(FichaDominoEntity fichaDomino) {
        this.fichaSeleccionada = fichaDomino;
    }

    /**
     * Deselecciona cualquier ficha previamente marcada como seleccionada.
     */
    @Override
    public void desseleccionarFicha() {
        this.fichaSeleccionada = null;
    }

    /**
     * Obtiene la ficha actualmente seleccionada.
     * 
     * @return la ficha seleccionada o {@code null} si no hay ninguna ficha seleccionada.
     */
    @Override
    public FichaDominoEntity obtenerFichaSeleccionada() {
        return fichaSeleccionada;
    }

    /**
     * Verifica si aún quedan fichas disponibles en el pozo.
     * 
     * @return {@code true} si el pozo no está vacío; {@code false} en caso contrario.
     */
    @Override
    public boolean quedanFichasEnPozo() {
        return !pozo.estaVacio();
    }
}
