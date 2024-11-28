package dominio;

/**
 * Clase que representa el tablero de dominó. Gestiona el estado y las
 * operaciones relacionadas con el tablero, incluyendo la colocación de fichas y
 * la gestión de los extremos de la cadena de fichas.
 *
 * El tablero tiene dos extremos (izquierda y derecha) hacia los cuales se
 * pueden colocar las fichas. Inicialmente, el tablero está vacío, y la casilla
 * de la mula ocupa ambas posiciones de los extremos.
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoEntity {

    public static final int IZQUIERDA = 0; // El extremo de la cadena de fichas se extiende actualmente hacia la izquierda
    public static final int DERECHA = 1; // El extremo de la cadena de fichas se extiende actualmente hacia la derecha

    private final int anchoTablero = 20; // ancho usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)
    private final int alturaTablero = 9; // altura usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)

    private final int casillaMulaLocacionX = 9; // Locación de la casilla de la mula en el eje X
    private final int casillaMulaLocacionY = 4; // Locación de la casilla de la mula en el eje Y

    private int direccionExtremo1; // Dirección en la que se extiende el extremo 1
    private int direccionExtremo2; // Dirección en la que se extiende el extremo 2

    private CasillaEntity casillaMula; // Referencia a la posición que contiene la ficha mula
    private CasillaEntity casillaExtremo1; // Referencia a la casilla del extremo 1
    private CasillaEntity casillaExtremo2; // Referencia a la casilla del extremo 2

    private int valorExtremo1; // Valor del primer extremo
    private int valorExtremo2; // Valor del segundo extremo

    private boolean estaVacia; // Estado que indica si el tablero está vacío

    /**
     * Constructor de la clase que inicializa el tablero y la casilla de la
     * mula.
     */
    public TableroDominoEntity() {
        this.estaVacia = true;
        iniciarCasillaMula();
        iniciarCasillasExtremos();
    }

    /**
     * Inicializa la casilla de la mula en el tablero.
     */
    private void iniciarCasillaMula() {
        this.casillaMula = new CasillaEntity();
        this.casillaMula.setLocacionX(casillaMulaLocacionX);
        this.casillaMula.setLocacionY(casillaMulaLocacionY);
    }

    /**
     * Inicializa las casillas de los extremos del tablero.
     */
    private void iniciarCasillasExtremos() {
        this.valorExtremo1 = -1; // Sin ficha asignada no hay valor
        this.valorExtremo2 = -1; // Sin ficha asignada no hay valor
        this.casillaExtremo1 = casillaMula;
        this.casillaExtremo2 = casillaMula;
        this.direccionExtremo1 = IZQUIERDA;
        this.direccionExtremo2 = DERECHA;
    }

    /**
     * Obtiene la casilla del primer extremo del tablero, si está disponible.
     *
     * @return La casilla del primer extremo o null si no está asignada.
     */
    public CasillaEntity obtenerPrimerElemento() {
        if (valorExtremo1 == -1) {
            return null;
        }
        return casillaExtremo1;
    }

    /**
     * Coloca una ficha mula en la casilla de la mula y actualiza los valores de
     * los extremos.
     *
     * @param mula La ficha mula que se va a colocar.
     */
    public void colocarMula(FichaDominoEntity mula) {
        this.estaVacia = false;
        this.casillaMula.setFichaDomino(mula);
        this.valorExtremo1 = mula.getExtremo1();
        this.valorExtremo2 = mula.getExtremo2();
    }

    /**
     * Coloca una ficha en el extremo correspondiente de la cadena de fichas.
     *
     * @param ficha La casilla que representa la ficha a colocar.
     * @param extremoCadena El extremo de la cadena donde se coloca la ficha.
     */
    public void colocarFicha(CasillaEntity ficha, int extremoCadena) {
        if (extremoCadena == CasillaEntity.EXTREMO1) {
            if (casillaExtremo1 == casillaMula) {
                // Conectar ficha con casillaMula si extremo1 está vacío
                casillaMula.setAnteriorCasilla(ficha);
                ficha.setSiguienteCasilla(casillaMula);
            } else {
                // Conectar ficha al extremo1 existente
                casillaExtremo1.setAnteriorCasilla(ficha);
                ficha.setSiguienteCasilla(casillaExtremo1);
            }
            // Actualizar el extremo1
            casillaExtremo1 = ficha;
        } else if (extremoCadena == CasillaEntity.EXTREMO2) {
            if (casillaExtremo2 == casillaMula) {
                // Conectar ficha con casillaMula si extremo2 está vacío
                casillaMula.setSiguienteCasilla(ficha);
                ficha.setAnteriorCasilla(casillaMula);
            } else {
                // Conectar ficha al extremo2 existente
                casillaExtremo2.setSiguienteCasilla(ficha);
                ficha.setAnteriorCasilla(casillaExtremo2);
            }
            // Actualizar el extremo2
            casillaExtremo2 = ficha;
        } else {
            throw new IllegalArgumentException("Extremo inválido: " + extremoCadena);
        }
    }

    // Métodos getter y setter para las propiedades de la clase.
    public int getAnchoTablero() {
        return anchoTablero;
    }

    public int getAltoTablero() {
        return alturaTablero;
    }

    public int getValorExtremo1() {
        return valorExtremo1;
    }

    public CasillaEntity getCasillaExtremo1() {
        return casillaExtremo1;
    }

    public CasillaEntity getCasillaExtremo2() {
        return casillaExtremo2;
    }

    public CasillaEntity getCasillaMula() {
        return casillaMula;
    }

    public int getDireccionExtremo1() {
        return direccionExtremo1;
    }

    public void setDireccionExtremo1(int direccionExtremo1) {
        this.direccionExtremo1 = direccionExtremo1;
    }

    public int getDireccionExtremo2() {
        return direccionExtremo2;
    }

    public void setDireccionExtremo2(int direccionExtremo2) {
        this.direccionExtremo2 = direccionExtremo2;
    }

    public boolean estaVacia() {
        return estaVacia;
    }

    public int getValorExtremo2() {
        return valorExtremo2;
    }

    public void setValorExtremo1(int valorExtremo1) {
        this.valorExtremo1 = valorExtremo1;
    }

    public void setValorExtremo2(int valorExtremo2) {
        this.valorExtremo2 = valorExtremo2;
    }
}
