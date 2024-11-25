package controladores.controladorfichas;

import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import java.util.List;

/**
 * Interfaz que define los métodos para gestionar las fichas en el juego de dominó.
 * Permite crear el pozo, repartir las fichas, agregar o quitar fichas a los jugadores,
 * seleccionar y deseleccionar fichas, y verificar si quedan fichas en el pozo.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IControladorFichas {
    /**
     * Crea el pozo de fichas para el juego.
     */
    public void crearPozo();

    /**
     * Obtiene todas las fichas del juego.
     * 
     * @return Lista de todas las fichas del juego.
     */
    public List<FichaDominoEntity> obtenerTodasLasFichasDelJuego();

    /**
     * Reparte un número determinado de fichas a los jugadores.
     * 
     * @param jugadores Lista de jugadores a quienes se les repartirán las fichas.
     * @param cantidadFichas Cantidad de fichas a repartir por jugador.
     */
    public void repartirFichasAJugadores(List<JugadorDominoEntity> jugadores, int cantidadFichas);

    /**
     * Agrega una ficha al jugador especificado.
     * 
     * @param jugador El jugador al que se le agrega la ficha.
     */
    public void agregarFichaAJugador(JugadorDominoEntity jugador);

    /**
     * Quita una ficha al jugador especificado.
     * 
     * @param jugador El jugador del que se quita la ficha.
     * @param ficha La ficha a quitar.
     */
    public void quitarFichaAJugador(JugadorDominoEntity jugador, FichaDominoEntity ficha);

    /**
     * Selecciona una ficha para que el jugador la juegue.
     * 
     * @param fichaDomino La ficha a seleccionar.
     */
    public void seleccionarFicha(FichaDominoEntity fichaDomino);

    /**
     * Deselecciona la ficha seleccionada.
     */
    public void desseleccionarFicha();

    /**
     * Obtiene la ficha que está actualmente seleccionada.
     * 
     * @return La ficha seleccionada.
     */
    public FichaDominoEntity obtenerFichaSeleccionada();

    /**
     * Verifica si quedan fichas en el pozo.
     * 
     * @return true si quedan fichas, false si el pozo está vacío.
     */
    public boolean quedanFichasEnPozo();
}
