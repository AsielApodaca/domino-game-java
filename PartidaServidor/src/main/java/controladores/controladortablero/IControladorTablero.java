package controladores.controladortablero;

import adapterentidades.IAdapterFichaDomino;
import dominio.CasillaEntity;
import dominio.FichaDominoEntity;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.List;

/**
 * Interfaz que define las operaciones para la gestión del tablero de dominó.
 * Incluye métodos para la creación del tablero, la colocación de fichas, la
 * obtención de casillas compatibles y la asignación de compatibilidad entre
 * fichas.
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IControladorTablero {

    /**
     * Crea un nuevo tablero de dominó. Este método debe inicializar un tablero
     * vacío listo para colocar las fichas.
     */
    public void crearTablero();

    /**
     * Obtiene una lista de las casillas que son compatibles con una ficha de
     * dominó. Las casillas compatibles son aquellas donde se puede colocar una
     * ficha según las reglas del juego.
     *
     * @param fichaDomino La ficha de dominó para la cual se buscan casillas
     * compatibles.
     * @return Lista de casillas compatibles.
     */
    public List<CasillaEntity> obtenerCasillasCompatibles(FichaDominoEntity fichaDomino);

    /**
     * Coloca una ficha en una casilla determinada. Utiliza la información de un
     * objeto `CasillaDTO` para determinar la ubicación y coloca una ficha de
     * dominó en dicha casilla.
     *
     * @param casillaDTO Objeto que contiene la información de la casilla donde
     * se colocará la ficha.
     * @param fichaDomino La ficha que se desea colocar.
     * @return La casilla actualizada con la ficha colocada.
     */
    public CasillaEntity colocarFichaEnCasilla(CasillaDTO casillaDTO, FichaDominoEntity fichaDomino);

    /**
     * Asigna la compatibilidad entre una lista de fichas de dominó. Este método
     * determina qué fichas pueden ser colocadas en cada casilla según las
     * reglas del juego y genera una lista de objetos `FichaDominoDTO` que
     * reflejan esta compatibilidad.
     *
     * @param listaFichas Lista de fichas de dominó para las cuales se
     * determinará la compatibilidad.
     * @return Lista de objetos `FichaDominoDTO` que representan las fichas con
     * su compatibilidad asignada.
     */
    public List<FichaDominoDTO> asignarCompatibilidadAFichas(List<FichaDominoEntity> listaFichas);

    /**
     * Establece el adaptador de ficha de dominó a usar en la lógica de juego.
     * Este método permite que la clase utilice un adaptador específico que
     * implemente la interfaz {@link IAdapterFichaDomino} para manejar las
     * operaciones relacionadas con las fichas de dominó.
     *
     * @param adapterFichaDomino el adaptador de ficha de dominó a establecer.
     */
    public void setAdapterFichaDomino(IAdapterFichaDomino adapterFichaDomino);

}
