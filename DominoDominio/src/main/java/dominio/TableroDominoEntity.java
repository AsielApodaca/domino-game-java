package dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoEntity {

    private final int IZQUIERDA = 0; // El extremo de la cadena de fichas se extiende actualmente hacia la izquierda
    private final int DERECHA = 1; // El extremo de la cadena de fichas se extiende actualmente hacia la derecha

    private final int anchoTablero = 20; // ancho usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)
    private final int alturaTablero = 9; // altura usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)

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

    private FichaDominoEntity fichaSeleccionada;

    public TableroDominoEntity() {
        iniciarCasillaMula();
        iniciarCasillasExtremos();
    } // como definir cuanto tiempo dura una iteración

    private void iniciarCasillaMula() {
        this.casillaMula = new CasillaEntity();
        // La locación de la casilla de la mula siempre será la misma
        this.casillaMula.setLocacionX(casillaMulaLocacionX);
        this.casillaMula.setLocacionY(casillaMulaLocacionY);
        this.casillaExtremo1 = casillaMula;
        this.casillaExtremo2 = casillaMula;

    }

    private void iniciarCasillasExtremos() {
        this.valorExtremo1 = -1; // Sin ficha asignada no hay valor
        this.valorExtremo2 = -1; // Sin ficha asignada no hay valor
        // inicialmente la casilla de la mula involucra ambos extremos.
        this.casillaExtremo1 = casillaMula;
        this.casillaExtremo2 = casillaMula;
        this.direccionExtremo1 = IZQUIERDA; // Orientación inicial de las casillas a colocar hacia la izquierda
        this.direccionExtremo2 = DERECHA; // Orientación inicial de las casillas a colocar hacia la izquierda
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

    public CasillaEntity getCasillaMula() {
        return casillaMula;
    }

    public int getValorExtremo2() {
        return valorExtremo2;
    }

    public FichaDominoEntity getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(FichaDominoEntity fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
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
            casillaExtremo2.setSiguienteCasilla(casilla);
            casilla.setAnteriorCasilla(casillaExtremo2);
        }
        casillaExtremo2 = casilla;

        // Define posición de la nueva casilla
        posicionarCasillaExtremo2();

    }

    private void posicionarCasillaExtremo1() {
        System.out.println("Direcion extremo 1 " + direccionExtremo1);

        int fichaValorExtremo1 = casillaExtremo1.getFichaDomino().getExtremo1(); // Valor del extremo 1 de la ficha
        int fichaValorExtremo2 = casillaExtremo1.getFichaDomino().getExtremo2(); // Valor del extremo 2 de la ficha

        CasillaEntity extremo1Anterior = casillaExtremo1.getSiguienteCasilla();
        int extremo1AnteriorLocacionX = extremo1Anterior.getLocacionX(); // locacion x del antiguo extremo 1
        int extremo1AnteriorLocacionY = extremo1Anterior.getLocacionY(); // locacion y del antiguo extremo 1
        int extremo1AnteriorRotacion = extremo1Anterior.getRotacion();
        boolean extremo1AnteriorEsVertical; // Si la ficha es vertical o horizontal

        switch (extremo1AnteriorRotacion) { // verifica la orientación de la ficha
            case 0:
                extremo1AnteriorEsVertical = false;
                break;
            case 90:
                extremo1AnteriorEsVertical = true;
                break;
            case 180:
                extremo1AnteriorEsVertical = false;
                break;
            case 270:
                extremo1AnteriorEsVertical = true;
                break;
            default:
                extremo1AnteriorEsVertical = false;
        }

        // Asigna coordenadas y orientación del nuevo extremo
        if (direccionExtremo1 == IZQUIERDA) {
            if (extremo1AnteriorEsVertical) { // Si el anterior extremo era vertical
                casillaExtremo1.setLocacionX(extremo1AnteriorLocacionX - 2); // Se coloca 2 a la izquierda
                casillaExtremo1.setLocacionY(extremo1AnteriorLocacionY + 1); // Se coloca 1 abajo
                // rotacion
                if (fichaValorExtremo2 == valorExtremo1) {
                    casillaExtremo1.setRotacion(0);
                    valorExtremo1 = fichaValorExtremo1;
                } else {
                    casillaExtremo1.setRotacion(180);
                    valorExtremo1 = fichaValorExtremo2;
                }
            } else if (extremo1AnteriorLocacionX - 2 < 0) { // Si la coordenada X para la nueva casilla es menor a 0, no cabe y cambia de direccion
                casillaExtremo1.setLocacionX(extremo1AnteriorLocacionX); // Se coloca 0 a la izquierda
                casillaExtremo1.setLocacionY(extremo1AnteriorLocacionY + 1); // Se coloca 1 a abajo
                // Cambia de dirección para las siguientes casillas
                direccionExtremo1 = DERECHA;
                // rotacion
                if (fichaValorExtremo2 == valorExtremo1) {
                    casillaExtremo1.setRotacion(90);
                    valorExtremo1 = fichaValorExtremo1;
                } else {
                    casillaExtremo1.setRotacion(270);
                    valorExtremo1 = fichaValorExtremo2;
                }
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                casillaExtremo1.setLocacionX(extremo1AnteriorLocacionX - 2); // Se coloca 2 a la izquierda
                casillaExtremo1.setLocacionY(extremo1AnteriorLocacionY); // Se coloca 0 a abajo
                // rotacion
                if (fichaValorExtremo2 == valorExtremo1) {
                    casillaExtremo1.setRotacion(0);
                    valorExtremo1 = fichaValorExtremo1;
                } else {
                    casillaExtremo1.setRotacion(180);
                    valorExtremo1 = fichaValorExtremo2;
                }
            }

        } else if (direccionExtremo1 == DERECHA) {
            if (extremo1AnteriorEsVertical) { // Si el anterior extremo era vertical
                casillaExtremo1.setLocacionX(extremo1AnteriorLocacionX + 1); // Se coloca 1 a la derecha
                casillaExtremo1.setLocacionY(extremo1AnteriorLocacionY + 1); // Se coloca 1 abajo
                // rotacion
                if (fichaValorExtremo1 == valorExtremo1) {
                    casillaExtremo1.setRotacion(0);
                    valorExtremo1 = fichaValorExtremo2;
                } else {
                    casillaExtremo1.setRotacion(180);
                    valorExtremo1 = fichaValorExtremo1;
                }
            } else if (extremo1AnteriorLocacionX + 4 > anchoTablero) { // Si la coordenada X para la nueva casilla es mayor que 20, no cabe y cambia de direccion
                casillaExtremo1.setLocacionX(extremo1AnteriorLocacionX + 1); // Se coloca 1 a la derecha
                casillaExtremo1.setLocacionY(extremo1AnteriorLocacionY + 1); // Se coloca 1 a abajo
                // Cambia de dirección para las siguientes casillas
                direccionExtremo1 = IZQUIERDA;
                // rotacion
                if (fichaValorExtremo1 == valorExtremo1) {
                    casillaExtremo1.setRotacion(270);
                    valorExtremo1 = fichaValorExtremo2;
                } else {
                    casillaExtremo1.setRotacion(90);
                    valorExtremo1 = fichaValorExtremo1;
                }
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                casillaExtremo1.setLocacionX(extremo1AnteriorLocacionX + 2); // Se coloca 2 a la izquierda
                casillaExtremo1.setLocacionY(extremo1AnteriorLocacionY); // Se coloca 0 a abajo
                // rotacion
                if (fichaValorExtremo1 == valorExtremo1) {
                    casillaExtremo1.setRotacion(0);
                    valorExtremo1 = fichaValorExtremo2;
                } else {
                    casillaExtremo1.setRotacion(180);
                    valorExtremo1 = fichaValorExtremo1;
                }
            }
        }
    }

    private void posicionarCasillaExtremo2() {
        System.out.println("Direcion extremo 2 " + direccionExtremo2);
        int fichaValorExtremo1 = casillaExtremo2.getFichaDomino().getExtremo1(); // Valor del extremo 1 de la ficha
        int fichaValorExtremo2 = casillaExtremo2.getFichaDomino().getExtremo2(); // Valor del extremo 2 de la ficha

        CasillaEntity extremo2Anterior = casillaExtremo2.getAnteriorCasilla();
        int extremo2AnteriorLocacionX = extremo2Anterior.getLocacionX(); // locacion x del antiguo extremo 1
        int extremo2AnteriorLocacionY = extremo2Anterior.getLocacionY(); // locacion y del antiguo extremo 1
        int extremo2AnteriorRotacion = extremo2Anterior.getRotacion();
        boolean extremo2AnteriorEsVertical; // Si la ficha es vertical o horizontal

        switch (extremo2AnteriorRotacion) { // verifica la orientación de la ficha
            case 0:
                extremo2AnteriorEsVertical = false;
                break;
            case 90:
                extremo2AnteriorEsVertical = true;
                break;
            case 180:
                extremo2AnteriorEsVertical = false;
                break;
            case 270:
                extremo2AnteriorEsVertical = true;
                break;
            default:
                extremo2AnteriorEsVertical = false;
        }

        // Asigna coordenadas y orientación del nuevo extremo
        if (direccionExtremo2 == IZQUIERDA) {
            if (extremo2AnteriorEsVertical) { // Si la casilla anterior al extremo actual es vertical
                casillaExtremo2.setLocacionX(extremo2AnteriorLocacionX - 2); // Se coloca 2 a la izquierda
                casillaExtremo2.setLocacionY(extremo2AnteriorLocacionY); // Se mantiene igual
                // rotacion
                if (fichaValorExtremo2 == valorExtremo2) {
                    casillaExtremo2.setRotacion(0);
                    valorExtremo2 = fichaValorExtremo1;
                } else {
                    casillaExtremo2.setRotacion(180);
                    valorExtremo2 = fichaValorExtremo2;
                }
            } else if (extremo2AnteriorLocacionX - 2 < 0) { // Si la coordenada X para la nueva casilla es menor a 0, no cabe y cambia de direccion
                casillaExtremo2.setLocacionX(extremo2AnteriorLocacionX); // Se coloca 0 a la izquierda
                casillaExtremo2.setLocacionY(extremo2AnteriorLocacionY - 1); // Se coloca 1 a abajo
                // Cambia de dirección para las siguientes casilla
                direccionExtremo2 = DERECHA;
                // rotacion
                if (fichaValorExtremo2 == valorExtremo2) {
                    casillaExtremo2.setRotacion(270);
                    valorExtremo2 = fichaValorExtremo1;
                } else {
                    casillaExtremo2.setRotacion(90);
                    valorExtremo2 = fichaValorExtremo2;
                }
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                casillaExtremo2.setLocacionX(extremo2AnteriorLocacionX - 2); // Se coloca 2 a la izquierda
                casillaExtremo2.setLocacionY(extremo2AnteriorLocacionY); // Se coloca 0 a abajo
                // rotacion
                if (fichaValorExtremo2 == valorExtremo2) {
                    casillaExtremo2.setRotacion(0);
                    valorExtremo2 = fichaValorExtremo1;
                } else {
                    casillaExtremo2.setRotacion(180);
                    valorExtremo2 = fichaValorExtremo2;
                }
            }

        } else if (direccionExtremo2 == DERECHA) {
            if (extremo2AnteriorEsVertical) { // Si la casilla anterior al extremo actual es vertical
                casillaExtremo2.setLocacionX(extremo2AnteriorLocacionX + 1); // Se coloca 1 a la derecha
                casillaExtremo2.setLocacionY(extremo2AnteriorLocacionY); // Se mantiene igual
                // rotacion
                if (fichaValorExtremo1 == valorExtremo2) {
                    casillaExtremo2.setRotacion(0);
                    valorExtremo2 = fichaValorExtremo2;
                } else {
                    casillaExtremo2.setRotacion(180);
                    valorExtremo2 = fichaValorExtremo1;
                }
            } else if (extremo2AnteriorLocacionX + 4 > anchoTablero) { // Si la coordenada X para la nueva casilla es mayor que 20, no cabe y cambia de direccion
                casillaExtremo2.setLocacionX(extremo2AnteriorLocacionX + 1); // Se coloca 1 a la derecha
                casillaExtremo2.setLocacionY(extremo2AnteriorLocacionY - 2); // Se coloca 2 a arriba
                // Cambia de dirección para las siguientes casilla
                direccionExtremo2 = IZQUIERDA;
                // rotacion
                if (fichaValorExtremo1 == valorExtremo2) {
                    casillaExtremo2.setRotacion(90);
                    valorExtremo2 = fichaValorExtremo2;
                } else {
                    casillaExtremo2.setRotacion(270);
                    valorExtremo2 = fichaValorExtremo1;
                }
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                casillaExtremo2.setLocacionX(extremo2AnteriorLocacionX + 2); // Se coloca 2 a la izquierda
                casillaExtremo2.setLocacionY(extremo2AnteriorLocacionY); // Se coloca 0 a abajo
                // rotacion
                if (fichaValorExtremo1 == valorExtremo2) {
                    casillaExtremo2.setRotacion(0);
                    valorExtremo2 = fichaValorExtremo2;
                } else {
                    casillaExtremo2.setRotacion(180);
                    valorExtremo2 = fichaValorExtremo1;
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

        int extremo1LocacionX = casillaExtremo1.getLocacionX(); // locacion x del antiguo extremo 1
        int extremo1LocacionY = casillaExtremo1.getLocacionY(); // locacion y del antiguo extremo 1
        int extremo1Rotacion = casillaExtremo1.getRotacion();
        boolean extremo1EsVertical; // Si la ficha es vertical o horizontal

        switch (extremo1Rotacion) { // verifica la orientación de la ficha
            case 0:
                extremo1EsVertical = false;
                break;
            case 90:
                extremo1EsVertical = true;
                break;
            case 180:
                extremo1EsVertical = false;
                break;
            case 270:
                extremo1EsVertical = true;
                break;
            default:
                extremo1EsVertical = false;
        }

        // Asigna coordenadas y medidas de la posible casilla
        if (direccionExtremo1 == IZQUIERDA) {
            if (extremo1EsVertical) { // Si la casilla del extremo 1 es vertical
                posibleCasilla.setLocacionX(extremo1LocacionX - 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(extremo1LocacionY + 1); // Se coloca 1 abajo
                posibleCasilla.setRotacion(0); // Horizontal
            } else if (extremo1LocacionX - 2 < 0) { // Si la coordenada X para la posible casilla es menor a 0, no cabe y cambia de direccion
                posibleCasilla.setLocacionX(extremo1LocacionX); // Se coloca 0 a la izquierda
                posibleCasilla.setLocacionY(extremo1LocacionY + 1); // Se coloca 1 a abajo
                posibleCasilla.setRotacion(90); // Vertical
            } else { // La posible casilla si cabe en el tablero y extremo1 no es una casilla vertical
                posibleCasilla.setLocacionX(extremo1LocacionX - 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(extremo1LocacionY); // Se coloca 0 a abajo
                posibleCasilla.setRotacion(0); // Horizontal
            }

        } else if (direccionExtremo1 == DERECHA) {
            if (extremo1EsVertical) { // Si la casilla extremo 1 es vertical
                posibleCasilla.setLocacionX(extremo1LocacionX + 1); // Se coloca 1 a la derecha
                posibleCasilla.setLocacionY(extremo1LocacionY + 1); // Se coloca 1 abajo
                posibleCasilla.setRotacion(0); // Horizontal
            } else if (extremo1LocacionX + 4 > anchoTablero) { // Si la coordenada X para la posible casilla es mayor que 20, no cabe y cambia de direccion
                posibleCasilla.setLocacionX(extremo1LocacionX + 1); // Se coloca 1 a la derecha
                posibleCasilla.setLocacionY(extremo1LocacionY + 1); // Se coloca 1 a abajo
                posibleCasilla.setRotacion(90); // Vertical
            } else { // La nueva casilla si cabe en el tablero y no proviene de una casilla vertical
                posibleCasilla.setLocacionX(extremo1LocacionX + 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(extremo1LocacionY); // Se coloca 0 a abajo
                posibleCasilla.setRotacion(0); // Horizontal
            }
        }

        return posibleCasilla;
    }

    public CasillaEntity obtenerPosiblePosicionExtremo2() {
        CasillaEntity posibleCasilla = new CasillaEntity();

        int extremo2LocacionX = casillaExtremo2.getLocacionX(); // locacion x del antiguo extremo 1
        int extremo2LocacionY = casillaExtremo2.getLocacionY(); // locacion y del antiguo extremo 1
        int extremo2Rotacion = casillaExtremo2.getRotacion();
        boolean extremo2EsVertical; // Si la ficha es vertical o horizontal

        switch (extremo2Rotacion) { // verifica la orientación de la ficha del extremo
            case 0:
                extremo2EsVertical = false;
                break;
            case 90:
                extremo2EsVertical = true;
                break;
            case 180:
                extremo2EsVertical = false;
                break;
            case 270:
                extremo2EsVertical = true;
                break;
            default:
                extremo2EsVertical = false;
        }

        // Asigna coordenadas y medidas a la posible casilla
        if (direccionExtremo2 == IZQUIERDA) {
            if (extremo2EsVertical) { // Si el extremo 2 es vertical
                posibleCasilla.setLocacionX(extremo2LocacionX - 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(extremo2LocacionY); // Se mantiene igual
                posibleCasilla.setRotacion(0); // Horizontal
            } else if (extremo2LocacionX - 2 < 0) { // Si la coordenada X para la posible casilla es menor a 0, no cabe y cambia de direccion
                posibleCasilla.setLocacionX(extremo2LocacionX); // Se coloca 0 a la izquierda
                posibleCasilla.setLocacionY(extremo2LocacionY - 2); // Se coloca 2 a arriba
                posibleCasilla.setRotacion(90); // Vertical
            } else { // La posible casilla si cabe en el tablero y extremo 2 no es vertical
                posibleCasilla.setLocacionX(extremo2LocacionX - 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(extremo2LocacionY); // Se coloca 0 a abajo
                posibleCasilla.setRotacion(0); // Horizontal
            }

        } else if (direccionExtremo2 == DERECHA) {
            if (extremo2EsVertical) { // Si el extremo 2 es vertical
                posibleCasilla.setLocacionX(extremo2LocacionX + 1); // Se coloca 1 a la derecha
                posibleCasilla.setLocacionY(extremo2LocacionY); // Se coloca 1 arriba
                posibleCasilla.setRotacion(0); // Horizontal
            } else if (extremo2LocacionX + 4 > anchoTablero) { // Si la coordenada X para la posible casilla es mayor que 20, no cabe y cambia de direccion
                posibleCasilla.setLocacionX(extremo2LocacionX + 1); // Se coloca 1 a la derecha
                posibleCasilla.setLocacionY(extremo2LocacionY - 2); // Se coloca 2 a arriba
                posibleCasilla.setRotacion(90); // Vertical
            } else { // La posible casilla si cabe en el tablero y extremo 2 no es vertical
                posibleCasilla.setLocacionX(extremo2LocacionX + 2); // Se coloca 2 a la izquierda
                posibleCasilla.setLocacionY(extremo2LocacionY); // Se coloca 0 a abajo
                posibleCasilla.setRotacion(0); // Horizontal
            }
        }

        return posibleCasilla;
    }

}
