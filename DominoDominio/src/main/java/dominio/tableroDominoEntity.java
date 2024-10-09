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
public class TableroDominoEntity {

    private final int IZQUIERDA = 0;
    private final int DERECHA = 1;

    private final int anchoTablero = 9; // ancho usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)
    private final int alturaTablero = 20; // altura usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)

    private final int anchoCasillaMula = 2; // Ancho de la casilla donde se colocará la mula
    private final int alturaCasillaMula = 1; // Altura de la casilla donde se colocará la mula
    private final int casillaMulaLocacionX = 9; // Locacion de la casilla de la mula en el eje X
    private final int casillaMulaLocacionY = 4; // Locacion de la casilla de la mula en el eje Y

    // DERECHA o IZQUIERDA
    private int direccionExtremo1; // Hacia que dirección se extiende el extremo1
    private int direccionExtremo2; // Hacia que direccion se extiende el extremo2

    private CasillaEntity casillaMula; // referencia a la posición que contiene la ficha mula
    private CasillaEntity casillaExtremo1; // referencia a la casilla del extremo 1
    private CasillaEntity casillaExtremo2; // referencia a la casilla del extremo 2

    private int valorExtremo1; // Valor del primer extremo
    private int valorExtremo2; // Valor del segundo extremo

    public TableroDominoEntity() {
        iniciarCasillaMula();
        this.valorExtremo1 = -1;
        this.valorExtremo2 = -1;
        this.direccionExtremo1 = IZQUIERDA;
        this.direccionExtremo2 = DERECHA;
    }

    private void iniciarCasillaMula() {
        this.casillaMula = new CasillaEntity();
        this.casillaMula.setAnchoCasilla(anchoCasillaMula);
        this.casillaMula.setAlturaCasilla(alturaCasillaMula);
        this.casillaMula.setLocacionX(casillaMulaLocacionX);
        this.casillaMula.setLocacionY(casillaMulaLocacionY);
    }

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

    public int getValorExtremo2() {
        return valorExtremo2;
    }

    public CasillaEntity obtenerPrimerElemento() {
        if (valorExtremo1 == -1) {
            return null;
        }
        if (casillaExtremo1 == null) {
            return casillaMula;
        }
        return casillaExtremo1;
    }

    public void colocarMula(FichaDominoEntity mula) {
        this.casillaMula.setFichaDomino(mula);
        this.valorExtremo1 = mula.getExtremo1();
        this.valorExtremo2 = mula.getExtremo2();
    }

    public void colocarFichaExtremo1(FichaDominoEntity ficha) {
        CasillaEntity casilla = new CasillaEntity();
        casilla.setFichaDomino(ficha); // Se le asigna ficha a la casilla

        // Se asigna la casilla al extremo y se conecta a la lista
        if (casillaExtremo1 == null) {
            casillaMula.setAnteriorCasilla(casilla);
            casilla.setSiguienteCasilla(casillaMula);
        } else {
            casillaExtremo1.setAnteriorCasilla(casilla);
            casilla.setSiguienteCasilla(casillaExtremo1);
        }
        casillaExtremo1 = casilla;

        // Define posición de la nueva casilla
        posicionarCasillaExtremo1();

    }

    public void colocarFichaExtremo2(FichaDominoEntity ficha) {
        CasillaEntity casilla = new CasillaEntity();
        casilla.setFichaDomino(ficha); // Se le asigna ficha a la casilla

        // Se asigna la casilla al extremo y se conecta a la lista
        if (casillaExtremo2 == null) {
            casillaMula.setSiguienteCasilla(casilla);
            casilla.setAnteriorCasilla(casillaMula);
        } else {
            casillaExtremo1.setSiguienteCasilla(casilla);
            casilla.setAnteriorCasilla(casillaExtremo1);
        }
        casillaExtremo2 = casilla;

        // Define posición de la nueva casilla
        posicionarCasillaExtremo2();

    }

    private void posicionarCasillaExtremo1() {
        // La nueva casilla es horizontal
        casillaExtremo1.setAnchoCasilla(2);
        casillaExtremo1.setAlturaCasilla(1);

        int fichaExtremo1 = casillaExtremo1.getFichaDomino().getExtremo1(); // Valor del extremo 1 de la ficha
        int fichaExtremo2 = casillaExtremo2.getFichaDomino().getExtremo2(); // Valor del extremo 2 de la ficha

        int casillaExtremo1SiguienteLocacionX = casillaExtremo1.getSiguienteCasilla().getLocacionX(); // locacion x de la siguiente casilla en la d-linked list
        int casillaExtremo1SiguienteLocacionY = casillaExtremo1.getSiguienteCasilla().getLocacionY(); // locacion y de la siguiente casilla en la d-linked list
        boolean casillaExtremo1SiguienteEsVertical = casillaExtremo1.getSiguienteCasilla().getAnchoCasilla() == 1; // Si el ancho es 1, está en vertical, 2 de lo contrario

        // Asigna coordenadas y medidas del nuevo extremo
        if (direccionExtremo1 == IZQUIERDA) {
            if (casillaExtremo1SiguienteEsVertical) { // Si la casilla siguiente de la d-linked list era vertical
                casillaExtremo1.setLocacionX(casillaExtremo1SiguienteLocacionX - 2); // Se coloca 2 a la izquierda
                casillaExtremo1.setLocacionY(casillaExtremo1SiguienteLocacionY - 1); // Se coloca 1 abajo
                // rotacion
                if (fichaExtremo2 == valorExtremo1) {
                    casillaExtremo1.setRotacion(0);
                    valorExtremo1 = fichaExtremo1;
                } else {
                    casillaExtremo1.setRotacion(180);
                    valorExtremo1 = fichaExtremo2;
                }
            } else if (casillaExtremo1SiguienteLocacionX - 2 < 0) { // Si la coordenada X para la nueva casilla es menor a 0, no cabe y cambia de direccion
                casillaExtremo1.setLocacionX(casillaExtremo1SiguienteLocacionX); // Se coloca 0 a la izquierda
                casillaExtremo1.setLocacionY(casillaExtremo1SiguienteLocacionY - 1); // Se coloca 1 a abajo
                // La nueva casilla cambia a vertical
                casillaExtremo1.setAnchoCasilla(1);
                casillaExtremo1.setAlturaCasilla(2);
                // Cambia de dirección para las siguientes casillas
                direccionExtremo1 = DERECHA;
                // rotacion
                if (fichaExtremo2 == valorExtremo1) {
                    casillaExtremo1.setRotacion(90);
                    valorExtremo1 = fichaExtremo1;
                } else {
                    casillaExtremo1.setRotacion(270);
                    valorExtremo1 = fichaExtremo2;
                }
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                casillaExtremo1.setLocacionX(casillaExtremo1SiguienteLocacionX - 2); // Se coloca 2 a la izquierda
                casillaExtremo1.setLocacionY(casillaExtremo1SiguienteLocacionY); // Se coloca 0 a abajo
                // rotacion
                if (fichaExtremo2 == valorExtremo1) {
                    casillaExtremo1.setRotacion(0);
                    valorExtremo1 = fichaExtremo1;
                } else {
                    casillaExtremo1.setRotacion(180);
                    valorExtremo1 = fichaExtremo2;
                }
            }

        } else if (direccionExtremo1 == DERECHA) {
            if (casillaExtremo1SiguienteEsVertical) { // Si la casilla siguiente de la lista era vertical
                casillaExtremo1.setLocacionX(casillaExtremo1SiguienteLocacionX + 1); // Se coloca 1 a la derecha
                casillaExtremo1.setLocacionY(casillaExtremo1SiguienteLocacionY - 1); // Se coloca 1 abajo
                // rotacion
                if (fichaExtremo1 == valorExtremo1) {
                    casillaExtremo1.setRotacion(0);
                    valorExtremo1 = fichaExtremo2;
                } else {
                    casillaExtremo1.setRotacion(180);
                    valorExtremo1 = fichaExtremo1;
                }
            } else if (casillaExtremo1SiguienteLocacionX + 4 > anchoTablero) { // Si la coordenada X para la nueva casilla es mayor que 20, no cabe y cambia de direccion
                casillaExtremo1.setLocacionX(casillaExtremo1SiguienteLocacionX + 1); // Se coloca 1 a la derecha
                casillaExtremo1.setLocacionY(casillaExtremo1SiguienteLocacionY - 1); // Se coloca 1 a abajo
                // La nueva casilla es vertical
                casillaExtremo1.setAnchoCasilla(1);
                casillaExtremo1.setAlturaCasilla(2);
                // Cambia de dirección para las siguientes casillas
                direccionExtremo1 = IZQUIERDA;
                // rotacion
                if (fichaExtremo1 == valorExtremo1) {
                    casillaExtremo1.setRotacion(270);
                    valorExtremo1 = fichaExtremo2;
                } else {
                    casillaExtremo1.setRotacion(90);
                    valorExtremo1 = fichaExtremo1;
                }
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                casillaExtremo1.setLocacionX(casillaExtremo1SiguienteLocacionX + 2); // Se coloca 2 a la izquierda
                casillaExtremo1.setLocacionY(casillaExtremo1SiguienteLocacionY); // Se coloca 0 a abajo
                // rotacion
                if (fichaExtremo1 == valorExtremo1) {
                    casillaExtremo1.setRotacion(0);
                    valorExtremo1 = fichaExtremo2;
                } else {
                    casillaExtremo1.setRotacion(180);
                    valorExtremo1 = fichaExtremo1;
                }
            }
        }
    }

    private void posicionarCasillaExtremo2() {
        // La nueva casilla es horizontal
        casillaExtremo2.setAnchoCasilla(2);
        casillaExtremo2.setAlturaCasilla(1);

        int fichaExtremo1 = casillaExtremo2.getFichaDomino().getExtremo1(); // Valor del extremo 1 de la ficha
        int fichaExtremo2 = casillaExtremo2.getFichaDomino().getExtremo2(); // Valor del extremo 2 de la ficha

        int casillaExtremo2AnteriorLocacionX = casillaExtremo2.getAnteriorCasilla().getLocacionX(); // locacion x de la anterior casilla en la d-linked list
        int casillaExtremo2AnteriorLocacionY = casillaExtremo2.getAnteriorCasilla().getLocacionY(); // locacion y de la anterior casilla en la d-linked list
        boolean casillaExtremo2AnteriorEsVertical = casillaExtremo2.getAnteriorCasilla().getAnchoCasilla() == 1; // Si el ancho es 1, está en vertical, 2 de lo contrario

        // Asigna coordenadas y medidas del nuevo extremo
        if (direccionExtremo2 == IZQUIERDA) {
            if (casillaExtremo2AnteriorEsVertical) { // Si la casilla anterior de la d-linked list era vertical
                casillaExtremo2.setLocacionX(casillaExtremo2AnteriorLocacionX - 2); // Se coloca 2 a la izquierda
                casillaExtremo2.setLocacionY(casillaExtremo2AnteriorLocacionY - 1); // Se coloca 1 abajo
                // rotacion
                if (fichaExtremo2 == valorExtremo2) {
                    casillaExtremo2.setRotacion(0);
                    valorExtremo2 = fichaExtremo1;
                } else {
                    casillaExtremo2.setRotacion(180);
                    valorExtremo2 = fichaExtremo2;
                }
            } else if (casillaExtremo2AnteriorLocacionX - 2 < 0) { // Si la coordenada X para la nueva casilla es menor a 0, no cabe y cambia de direccion
                casillaExtremo2.setLocacionX(casillaExtremo2AnteriorLocacionX); // Se coloca 0 a la izquierda
                casillaExtremo2.setLocacionY(casillaExtremo2AnteriorLocacionY - 1); // Se coloca 1 a abajo
                // La nueva casilla cambia a vertical
                casillaExtremo2.setAnchoCasilla(1);
                casillaExtremo2.setAlturaCasilla(2);
                // Cambia de dirección para las siguientes casilla
                direccionExtremo2 = DERECHA;
                // rotacion
                if (fichaExtremo2 == valorExtremo2) {
                    casillaExtremo2.setRotacion(270);
                    valorExtremo2 = fichaExtremo1;
                } else {
                    casillaExtremo2.setRotacion(90);
                    valorExtremo2 = fichaExtremo2;
                }
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                casillaExtremo2.setLocacionX(casillaExtremo2AnteriorLocacionX - 2); // Se coloca 2 a la izquierda
                casillaExtremo2.setLocacionY(casillaExtremo2AnteriorLocacionY); // Se coloca 0 a abajo
                // rotacion
                if (fichaExtremo2 == valorExtremo2) {
                    casillaExtremo2.setRotacion(0);
                    valorExtremo2 = fichaExtremo1;
                } else {
                    casillaExtremo2.setRotacion(180);
                    valorExtremo2 = fichaExtremo2;
                }
            }

        } else if (direccionExtremo2 == DERECHA) {
            if (casillaExtremo2AnteriorEsVertical) { // Si la casilla anterior de la d-linked list era vertical
                casillaExtremo2.setLocacionX(casillaExtremo2AnteriorLocacionX + 1); // Se coloca 1 a la derecha
                casillaExtremo2.setLocacionY(casillaExtremo2AnteriorLocacionY - 1); // Se coloca 1 abajo
                // rotacion
                if (fichaExtremo1 == valorExtremo2) {
                    casillaExtremo2.setRotacion(0);
                    valorExtremo2 = fichaExtremo2;
                } else {
                    casillaExtremo2.setRotacion(180);
                    valorExtremo2 = fichaExtremo1;
                }
            } else if (casillaExtremo2AnteriorLocacionX + 4 > anchoTablero) { // Si la coordenada X para la nueva casilla es mayor que 20, no cabe y cambia de direccion
                casillaExtremo2.setLocacionX(casillaExtremo2AnteriorLocacionX + 1); // Se coloca 1 a la derecha
                casillaExtremo2.setLocacionY(casillaExtremo2AnteriorLocacionY - 1); // Se coloca 1 a abajo
                // La nueva posicon es vertical
                casillaExtremo2.setAnchoCasilla(1);
                casillaExtremo2.setAlturaCasilla(2);
                // Cambia de dirección para las siguientes casilla
                direccionExtremo2 = IZQUIERDA;
                // rotacion
                if (fichaExtremo1 == valorExtremo2) {
                    casillaExtremo2.setRotacion(90);
                    valorExtremo2 = fichaExtremo2;
                } else {
                    casillaExtremo2.setRotacion(270);
                    valorExtremo2 = fichaExtremo1;
                }
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                casillaExtremo2.setLocacionX(casillaExtremo2AnteriorLocacionX + 2); // Se coloca 2 a la izquierda
                casillaExtremo2.setLocacionY(casillaExtremo2AnteriorLocacionY); // Se coloca 0 a abajo
                // rotacion
                if (fichaExtremo1 == valorExtremo2) {
                    casillaExtremo2.setRotacion(0);
                    valorExtremo2 = fichaExtremo2;
                } else {
                    casillaExtremo2.setRotacion(180);
                    valorExtremo2 = fichaExtremo1;
                }
            }
        }
    }

    public CasillaEntity obtenerPosibleCasillaMula() {
        // Se supone este método sea llamado unicamente cuando aun no se ha
        // colocado la mula, por lo que se puede regresar esta casilla que
        // al momento de llamar este método, aún no se le ha asignado una ficha
        // a esta casilla.
        return casillaMula;
    }

    public CasillaEntity obtenerPosibleCasillaExtremo1() {
        CasillaEntity posibleCasilla = new CasillaEntity();
        // La posible casilla es horizontal
        posibleCasilla.setAnchoCasilla(2);
        posibleCasilla.setAlturaCasilla(1);

        int casillaExtremo1X = casillaExtremo1.getLocacionX(); // locacion x de la casilla del extremo 1
        int casillaExtremo1Y = casillaExtremo1.getLocacionY(); // locacion y de la casilla del extremo 1
        boolean Extremo1EsVertical = casillaExtremo1.getAnchoCasilla() == 1; // Si el ancho es 1, está en vertical, 2 de lo contrario

        // Asigna coordenadas y medidas de la posible casilla
        if (direccionExtremo1 == IZQUIERDA) {
            if (Extremo1EsVertical) { // Si la casilla del extremo 1 es vertical
                posibleCasilla.setLocacionX(casillaExtremo1X - 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(casillaExtremo1Y - 1); // Se coloca 1 abajo
            } else if (casillaExtremo1X - 2 < 0) { // Si la coordenada X para la posible casilla es menor a 0, no cabe y cambia de direccion
                posibleCasilla.setLocacionX(casillaExtremo1X); // Se coloca 0 a la izquierda
                posibleCasilla.setLocacionY(casillaExtremo1Y - 1); // Se coloca 1 a abajo
                // La posible casilla cambia a vertical
                posibleCasilla.setAnchoCasilla(1);
                posibleCasilla.setAlturaCasilla(2);
            } else { // La posible casilla si cabe en el tablero y extremo1 no es una casilla vertical
                posibleCasilla.setLocacionX(casillaExtremo1X - 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(casillaExtremo1Y); // Se coloca 0 a abajo
            }

        } else if (direccionExtremo1 == DERECHA) {
            if (Extremo1EsVertical) { // Si la casilla extremo 1 es vertical
                posibleCasilla.setLocacionX(casillaExtremo1X + 1); // Se coloca 1 a la derecha
                posibleCasilla.setLocacionY(casillaExtremo1Y - 1); // Se coloca 1 abajo
            } else if (casillaExtremo1X + 4 > anchoTablero) { // Si la coordenada X para la posible casilla es mayor que 20, no cabe y cambia de direccion
                posibleCasilla.setLocacionX(casillaExtremo1X + 1); // Se coloca 1 a la derecha
                posibleCasilla.setLocacionY(casillaExtremo1Y - 1); // Se coloca 1 a abajo
                // La nueva casilla es vertical
                posibleCasilla.setAnchoCasilla(1);
                posibleCasilla.setAlturaCasilla(2);
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                posibleCasilla.setLocacionX(casillaExtremo1X + 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(casillaExtremo1Y); // Se coloca 0 a abajo
            }
        }

        return posibleCasilla;
    }

    public CasillaEntity obtenerPosiblePosicionExtremo2() {
        CasillaEntity posibleCasilla = new CasillaEntity();
        // La posible casilla es horizontal
        posibleCasilla.setAnchoCasilla(2);
        posibleCasilla.setAlturaCasilla(1);

        int casillaExtremo2X = casillaExtremo2.getLocacionX(); // locacion x de la casilla del extremo 2
        int casillaExtremo2Y = casillaExtremo2.getLocacionY(); // locacion y de la casilla del extremo 2
        boolean Extremo2EsVertical = casillaExtremo2.getAnchoCasilla() == 1; // Si el ancho es 1, está en vertical, 2 de lo contrario

        // Asigna coordenadas y medidas a la posible casilla
        if (direccionExtremo2 == IZQUIERDA) {
            if (Extremo2EsVertical) { // Si el extremo 2 es vertical
                posibleCasilla.setLocacionX(casillaExtremo2X - 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(casillaExtremo2Y - 1); // Se coloca 1 abajo
            } else if (casillaExtremo2X - 2 < 0) { // Si la coordenada X para la posible casilla es menor a 0, no cabe y cambia de direccion
                posibleCasilla.setLocacionX(casillaExtremo2X); // Se coloca 0 a la izquierda
                posibleCasilla.setLocacionY(casillaExtremo2Y - 1); // Se coloca 1 a abajo
                // La posible casilla cambia a vertical
                posibleCasilla.setAnchoCasilla(1);
                posibleCasilla.setAlturaCasilla(2);
            } else { // La posible casilla si cabe en el tablero y extremo 2 no es vertical
                posibleCasilla.setLocacionX(casillaExtremo2X - 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(casillaExtremo2Y); // Se coloca 0 a abajo
            }

        } else if (direccionExtremo2 == DERECHA) {
            if (Extremo2EsVertical) { // Si el extremo 2 es vertical
                posibleCasilla.setLocacionX(casillaExtremo2X + 1); // Se coloca 1 a la derecha
                posibleCasilla.setLocacionY(casillaExtremo2Y - 1); // Se coloca 1 abajo
            } else if (casillaExtremo2X + 4 > anchoTablero) { // Si la coordenada X para la posible casilla es mayor que 20, no cabe y cambia de direccion
                posibleCasilla.setLocacionX(casillaExtremo2X + 1); // Se coloca 1 a la derecha
                posibleCasilla.setLocacionY(casillaExtremo2Y - 1); // Se coloca 1 a abajo
                // La posible casilla es vertical
                posibleCasilla.setAnchoCasilla(1);
                posibleCasilla.setAlturaCasilla(2);
            } else { // La posible casilla si cabe en el tablero y extremo 2 no es vertical
                posibleCasilla.setLocacionX(casillaExtremo2X + 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(casillaExtremo2Y); // Se coloca 0 a abajo
            }
        }

        return posibleCasilla;
    }
}
