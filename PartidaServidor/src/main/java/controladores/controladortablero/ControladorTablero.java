
package controladores.controladortablero;

import adapterEntidades.IAdapterFichaDomino;
import dominio.CasillaEntity;
import dominio.FichaDominoEntity;
import dominio.TableroDominoEntity;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ControladorTablero que implementa la interfaz IControladorTablero.
 * Esta clase se encarga de gestionar las operaciones relacionadas con el tablero de dominó,
 * incluyendo la creación de un nuevo tablero y la manipulación de las casillas y fichas en el tablero.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ControladorTablero implements IControladorTablero {


    private TableroDominoEntity tableroDominoEntity;
    private IAdapterFichaDomino adapterFichaDomino;

    /**
     * Constructor de la clase ControladorTablero.
     *
     * @param adapterFichaDomino El adaptador que se utilizará para interactuar
     * con las fichas de dominó.
     */
    public ControladorTablero(IAdapterFichaDomino adapterFichaDomino) {
        this.adapterFichaDomino = adapterFichaDomino;
    }

    /**
     * Crea una nueva instancia de TableroDominoEntity, inicializando un nuevo
     * tablero de dominó. Este método se llama para configurar un tablero vacío
     * antes de comenzar una partida de dominó.
     */
    @Override
    public void crearTablero() {
        this.tableroDominoEntity = new TableroDominoEntity();
    }

    /**
     * Obtiene las casillas compatibles con la ficha de dominó proporcionada. Si
     * el tablero está vacío, retorna la casilla de la mula. Si el tablero no
     * está vacío, verifica los extremos de la ficha para determinar las
     * casillas compatibles y las agrega a la lista de casillas compatibles.
     *
     * @param fichaDomino La ficha de dominó a evaluar para obtener las casillas
     * compatibles.
     * @return Una lista de casillas compatibles con la ficha de dominó
     * proporcionada.
     */
    @Override
    public List<CasillaEntity> obtenerCasillasCompatibles(FichaDominoEntity fichaDomino) {
        List<CasillaEntity> casillasCompatibles = new ArrayList<>();

        if (tableroDominoEntity.estaVacia()) { // Si la tabla está vacía, retorna la casilla de la mula
            casillasCompatibles.add(obtenerCasillaDisponible(CasillaDTO.MULA));
            return casillasCompatibles;
        }

        boolean[] extremosCompatibles = obtenerExtremosCompatibles(fichaDomino);
        if (extremosCompatibles[0]) { // Si la ficha es compatible con el extremo 1
            casillasCompatibles.add(obtenerCasillaDisponible(CasillaDTO.EXTREMO1));
        } else if (extremosCompatibles[1]) { // Si la ficha es compatible con el extremo 2
            casillasCompatibles.add(obtenerCasillaDisponible(CasillaDTO.EXTREMO2));
        }

        return casillasCompatibles;
    }

    /**
     * Coloca una ficha de dominó en una nueva casilla en un extremo de la
     * cadena de fichas del tablero. Si el tablero está vacío, coloca la ficha
     * en la casilla de la mula. Si el tablero ya tiene fichas, coloca la ficha
     * en el extremo adecuado y ajusta la dirección y la rotación de la casilla
     * y los extremos del tablero según corresponda. Además, actualiza el valor
     * de los extremos de la cadena de fichas.
     *
     * @param casillaDTO Contiene a que extremo de la cadena de fichas pertenece
     * la casilla
     * @param fichaDomino La ficha de dominó a colocar en el tablero.
     * @return La casilla en la que se colocó la ficha.
     */
    @Override
    public CasillaEntity colocarFichaEnCasilla(CasillaDTO casillaDTO, FichaDominoEntity fichaDomino) {
        if (tableroDominoEntity.estaVacia()) { // Si el tablero está vacío
            // Coloca la ficha en la casilla de la mula y retorna la casilla
            tableroDominoEntity.colocarMula(fichaDomino);
            return tableroDominoEntity.getCasillaMula();
        }

        // Obtiene el extremo de la cadena donde se colocará la ficha, EXTREMO1 o EXTREMO2
        int extremoCadena = casillaDTO.getExtremo();
        // Obtiene una casilla vacía disponible del extremo seleccionado
        CasillaEntity casillaEntity = obtenerCasillaDisponible(extremoCadena);
        // Le asigna una ficha a la casilla
        casillaEntity.setFichaDomino(fichaDomino);

        int rotacionCasilla = casillaEntity.getRotacion();
        boolean casillaEsVertical = rotacionCasilla == 90; // Al obtener una casilla disponible, puede tener una rotación de 0 o 90 grados

        int cadenaValorExtremo = extremoCadena == CasillaEntity.EXTREMO1
                ? // Obtiene el valor actual del extremo seleccionado de la cadena de fichas
                tableroDominoEntity.getValorExtremo1() : tableroDominoEntity.getValorExtremo2();

        // Obtiene el valor de cada extremo de la ficha
        int fichaValorExtremo1 = fichaDomino.getExtremo1();
        int fichaValorExtremo2 = fichaDomino.getExtremo2();

        if (casillaEsVertical) { // Si la ficha es Vertical
            int direccionActual;
            int nuevaDireccion;
            // Cambia la dirección para las siguientes casillas
            // Y asigna una nueva rotación a la casilla para que la dirección de la ficha sea correcta
            if (extremoCadena == CasillaEntity.EXTREMO1) {
                direccionActual = tableroDominoEntity.getDireccionExtremo1(); // Obtiene la dirección actual del extremo 1
                nuevaDireccion = direccionActual == TableroDominoEntity.IZQUIERDA
                        ? // Obtiene la nueva dirección para las siguientes casillas del extremo 1
                        TableroDominoEntity.DERECHA : TableroDominoEntity.IZQUIERDA;
                tableroDominoEntity.setDireccionExtremo1(nuevaDireccion);  // Actualiza la dirección del extremo 1 con la nueva dirección

                // Gira la ficha si está al revés y actualiza el valor del extremo 1 de la cadena
                if (fichaValorExtremo2 != cadenaValorExtremo) {
                    trasladarYRotarCasilla(casillaEntity, 0, 0, 270);
                    tableroDominoEntity.setValorExtremo1(fichaValorExtremo2);
                } else { // Si la casilla es vertical, por defecto ya tiene 90 grados
                    tableroDominoEntity.setValorExtremo1(fichaValorExtremo1);
                }
            } else { // EXTREMO2
                direccionActual = tableroDominoEntity.getDireccionExtremo2(); // Obtiene la dirección actual del extremo 2
                nuevaDireccion = direccionActual == TableroDominoEntity.IZQUIERDA
                        ? // Obtiene la nueva dirección para las siguientes casillas del extremo 2
                        TableroDominoEntity.DERECHA : TableroDominoEntity.IZQUIERDA;
                tableroDominoEntity.setDireccionExtremo2(nuevaDireccion); // Actualiza la dirección del extremo 2 con la nueva dirección

                // Gira la ficha si está al revés y actualiza el valor del extremo 2 de la cadena
                if (fichaValorExtremo1 != cadenaValorExtremo) {
                    trasladarYRotarCasilla(casillaEntity, 0, 0, 270);
                    tableroDominoEntity.setValorExtremo2(fichaValorExtremo1);
                } else { // Si la casilla es horizontal, por defecto ya tiene 0 grados
                    tableroDominoEntity.setValorExtremo1(fichaValorExtremo2);
                }
            }
        } else { // Si la ficha es Horizontal
            int direccionExtremo = extremoCadena == CasillaEntity.EXTREMO1
                    ? // Obtiene la dirección del extremo seleccionado
                    tableroDominoEntity.getDireccionExtremo1()
                    : tableroDominoEntity.getDireccionExtremo2();

            if (direccionExtremo == TableroDominoEntity.IZQUIERDA) {
                if (fichaValorExtremo2 != cadenaValorExtremo) {
                    trasladarYRotarCasilla(casillaEntity, 0, 0, 180);
                    if (extremoCadena == CasillaEntity.EXTREMO1) {
                        tableroDominoEntity.setValorExtremo1(fichaValorExtremo2);
                    } else { // EXTREMO2
                        tableroDominoEntity.setValorExtremo2(fichaValorExtremo2);
                    }
                }
            } else { // DERECHA
                if (fichaValorExtremo1 != cadenaValorExtremo) {
                    trasladarYRotarCasilla(casillaEntity, 0, 0, 180);
                    if (extremoCadena == CasillaEntity.EXTREMO1) {
                        tableroDominoEntity.setValorExtremo1(fichaValorExtremo1);
                    } else { // EXTREMO2
                        tableroDominoEntity.setValorExtremo2(fichaValorExtremo1);
                    }
                }
            }

        }

        // Coloca la casilla con la ficha en el tablero
        tableroDominoEntity.colocarFicha(casillaEntity, extremoCadena);
        return casillaEntity;
    }

    /**
     * Determina la compatibilidad de las fichas de dominó con la cadena actual
     * de fichas y asigna dicha información a una lista de objetos DTO.
     *
     * @param listaFichas la lista de fichas de dominó en formato entidad.
     * @return una lista de objetos FichaDominoDTO, donde cada ficha tiene
     * asignado si es compatible con la cadena actual de fichas.
     */
    @Override
    public List<FichaDominoDTO> asignarCompatibilidadAFichas(List<FichaDominoEntity> listaFichas) {
        List<FichaDominoDTO> listaFichasDTO = adapterFichaDomino.adaptListToDTO(listaFichas); // Convierte la lista a DTO
        for (int i = 0; i < listaFichas.size(); i++) { // Recorre todas las fichas entidad
            boolean[] extremosCompatibles = obtenerExtremosCompatibles(listaFichas.get(i));
            if (extremosCompatibles[0] || extremosCompatibles[1]) { // Verifica compatibilidad
                listaFichasDTO.get(i).setCompatible(true);
            }
        }
        return listaFichasDTO;
    }

    /**
     * Método que retorna arreglo booleano de extremos compatibles
     *
     * La posición 0 representa el extremo 1 del tablero La posición 1
     * representa el extremo 2 del tablero
     *
     * El valor de cada posición es true si cualquiera de los valores de la
     * ficha es compatible con el extremo que representa la posición.
     *
     * @param fichaDominoEntity Ficha a comparar con extremos del tablero.
     * @return arreglo booleano de extremos compatibles
     */
    private boolean[] obtenerExtremosCompatibles(FichaDominoEntity fichaDominoEntity) {
        boolean[] extremosCompatibles = new boolean[2];

        int fichaExtremo1 = fichaDominoEntity.getExtremo1();
        int fichaExtremo2 = fichaDominoEntity.getExtremo2();

        int tableroExtremo1 = tableroDominoEntity.getValorExtremo1();
        int tableroExtremo2 = tableroDominoEntity.getValorExtremo2();

        if (tableroExtremo1 == -1) {
            extremosCompatibles[0] = true;
            extremosCompatibles[1] = true;
        } else {
            extremosCompatibles[0] = tableroExtremo1 == fichaExtremo1 || tableroExtremo1 == fichaExtremo2;
            extremosCompatibles[1] = tableroExtremo2 == fichaExtremo1 || tableroExtremo2 == fichaExtremo2;
        }

        return extremosCompatibles;
    }

    /**
     * Obtiene una casilla disponible para colocar una ficha en el tablero.
     * Dependiendo del extremo de la cadena (EXTREMO1 o EXTREMO2), se determina
     * la ubicación y dirección en el tablero. La casilla se ajusta a las
     * restricciones de espacio y rotación necesarias para colocar la ficha
     * correctamente. Si el extremo de la cadena está en una dirección
     * horizontal o vertical, se traslada y rota la casilla según las reglas del
     * juego. Si se exceden los límites del tablero, se ajusta la dirección o
     * rotación de la casilla.
     *
     * @param extremoCadena El extremo de la cadena donde se colocará la ficha,
     * puede ser 0 para la mula, 1 para EXTREMO1 o 2 para EXTREMO2.
     * @return La casilla disponible ajustada a las restricciones de la cadena y
     * el tablero.
     */
    private CasillaEntity obtenerCasillaDisponible(int extremoCadena) {
        if (extremoCadena == 0) // Retorna la casilla para la mula
        {
            return tableroDominoEntity.getCasillaMula();
        }

        CasillaEntity posibleCasilla = new CasillaEntity();

        CasillaEntity casillaExtremo = extremoCadena == 1
                ? // obtiene la casilla del extremo seleccionado de la cadena de fichas
                tableroDominoEntity.getCasillaExtremo1()
                : tableroDominoEntity.getCasillaExtremo2();

        int direccionExtremo = extremoCadena == 1
                ? // Obtiene la dirección del extremo seleccionado
                tableroDominoEntity.getDireccionExtremo1()
                : tableroDominoEntity.getDireccionExtremo2();

        int extremoLocacionX = casillaExtremo.getLocacionX(); // locacion x de la casilla del extremo seleccionado
        int extremoLocacionY = casillaExtremo.getLocacionY(); // locacion y de la casilla del extremo seleccionado
        int extremoRotacion = casillaExtremo.getRotacion(); // Rotacion de la casilla del extremo seleccionado
        final int IZQUIERDA = TableroDominoEntity.IZQUIERDA; // Hace referencia a la dirección a la que se extiende uno de los extremos de la cadena.
        boolean nuevaCasillaExcedeLimite;
        if (direccionExtremo == IZQUIERDA) { // Verifica si una nueva casilla excede el límite del tablero si continua horizontalmente
            nuevaCasillaExcedeLimite = extremoLocacionX - 2 < 0;
        } else { // direccionExtremo == DERECHA
            nuevaCasillaExcedeLimite = extremoLocacionX + 4 > tableroDominoEntity.getAnchoTablero();
        }

        boolean extremoEsVertical; // Si la ficha de la casilla del extremo seleccionado es vertical o horizontal.
        // Con base a la rotación de la ficha de la casilla del extremo seleccionado,
        // verifica si es vertical o no.
        extremoEsVertical = (extremoRotacion + 90) % 180 == 0;

        // Asigna la locación base a la posible casilla
        posibleCasilla.setLocacionX(extremoLocacionX);
        posibleCasilla.setLocacionY(extremoLocacionY);
        // Gira y traslada la casilla a su nueva locación correspondiente

        if (extremoEsVertical) { // Si la casilla del extremo es vertical
            trasladarYRotarCasilla(posibleCasilla,
                    direccionExtremo == IZQUIERDA ? -2 : 1, // Traslación eje X
                    extremoCadena == 1 ? 1 : 0, // Traslación eje Y
                    0);                                     // Rotación
        } else if (nuevaCasillaExcedeLimite) { // Si la coordenada X para la posible casilla es menor a 0, no cabe y cambia de direccion
            trasladarYRotarCasilla(posibleCasilla,
                    direccionExtremo == IZQUIERDA ? 0 : 1, // Traslación eje X
                    extremoCadena == 1 ? 1 : -2, // Traslación eje Y
                    90);                                    // Rotación
        } else { // La posible casilla si cabe en el tablero y extremo1 no es una casilla vertical
            trasladarYRotarCasilla(posibleCasilla,
                    direccionExtremo == IZQUIERDA ? -2 : 2, // Traslación eje X
                    0, // Traslación eje Y
                    0);                                     // Rotación
        }

        return posibleCasilla;
    }

    /**
     * Traslada y rota una casilla en el tablero de acuerdo a los parámetros de
     * entrada. La casilla se mueve en el eje X y Y según los valores
     * proporcionados y se rota a la orientación especificada. Esto permite
     * ajustar la ubicación y la orientación de una casilla en el tablero de
     * acuerdo con las reglas del juego.
     *
     * @param casilla La casilla que se va a trasladar y rotar.
     * @param x El valor de traslación en el eje X. Un valor positivo mueve la
     * casilla hacia la derecha y un valor negativo hacia la izquierda.
     * @param y El valor de traslación en el eje Y. Un valor positivo mueve la
     * casilla hacia abajo y un valor negativo hacia arriba.
     * @param rotacion El valor de rotación en grados, que puede ser 0, 90, 180
     * o 270 grados. Este valor determina la orientación de la casilla después
     * de la rotación.
     */
    private void trasladarYRotarCasilla(CasillaEntity casilla, int x, int y, int rotacion) {
        int nuevaLocacionX = casilla.getLocacionX() + x;
        int nuevaLocacionY = casilla.getLocacionY() + y;
        casilla.setLocacionX(nuevaLocacionX);
        casilla.setLocacionY(nuevaLocacionY);
        casilla.setRotacion(rotacion);
    }

}
