/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author asielapodaca
 */
public class TableroDominoEntity {
    
    private final int IZQUIERDA = 0;
    private final int DERECHA = 1;
    
    private int direccionExtremo1; // Hacia que dirección se extiende el extremo1
    private int direccionExtremo2; // Hacia que direccion se extiende el extremo2
    
    private PosicionEntity posicionMula; // referencia a la posición que contiene la ficha mula
    private PosicionEntity posicionExtremo1; // referencia a la posición del extremo 1
    private PosicionEntity posicionExtremo2; // referencia a la posición del extremo 2
    
    private final int anchoTablero; // ancho usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)
    private final int altoTablero; // altura usable del tablero para colocar fichas, cada unidad es el ancho de una ficha (1 unidad)
    
    private int valorExtremo1; // Valor del primer extremo
    private int valorExtremo2; // Valor del segundo extremo


    public TableroDominoEntity() {
        this.posicionMula = new PosicionEntity();
        this.posicionMula.setAlturaPosicion(1);
        this.posicionMula.setAnchoPosicion(2);
        this.posicionMula.setPosicionX(9);
        this.posicionMula.setPosicionY(4);
        this.posicionMula.setRotacion(0);
        
        this.valorExtremo1 = -1;
        this.valorExtremo2 = -1;
        
        this.anchoTablero = 20;
        this.altoTablero = 9;
        this.direccionExtremo1 = IZQUIERDA;
        this.direccionExtremo2 = DERECHA;
    }

    public int getAnchoTablero() {
        return anchoTablero;
    }

    public int getAltoTablero() {
        return altoTablero;
    }

    public int getValorExtremo1() {
        return valorExtremo1;
    }

    public PosicionEntity getPosicionExtremo1() {
        return posicionExtremo1;
    }

    public PosicionEntity getPosicionExtremo2() {
        return posicionExtremo2;
    }

    public int getValorExtremo2() {
        return valorExtremo2;
    }
    
    public PosicionEntity obtenerPrimerElemento() {
        if(valorExtremo1 == -1) return null;
        if(posicionExtremo1 == null) return posicionMula;
        return posicionExtremo1;
    }
    
    public void colocarMula(FichaDominoEntity mula) {
        this.posicionMula.setFichaDomino(mula);
        this.valorExtremo1 = mula.getExtremo1();
        this.valorExtremo2 = mula.getExtremo2();
    }
    
    public void colocarFichaExtremo1(FichaDominoEntity ficha) {
        PosicionEntity posicion = new PosicionEntity();
        posicion.setFichaDomino(ficha); // Se le asigna ficha a la posición
        
        // Se asigna la posición al extremo y se conecta a la lista
        if(posicionExtremo1 == null) {
            posicionMula.setAnteriorPosicion(posicion);
            posicion.setSiguientePosicion(posicionMula);
        } else {
            posicionExtremo1.setAnteriorPosicion(posicion);
            posicion.setSiguientePosicion(posicionExtremo1);
        }
        posicionExtremo1 = posicion;
        
        // Ajusta la posición del nuevo extremo
        ajustarPosicionExtremo1();
        
    }
    
    public void colocarFichaExtremo2(FichaDominoEntity ficha) {
        PosicionEntity posicion = new PosicionEntity();
        posicion.setFichaDomino(ficha); // Se le asigna ficha a la posición
        
        // Se asigna la posición al extremo y se conecta a la lista
        if(posicionExtremo2 == null) {
            posicionMula.setSiguientePosicion(posicion);
            posicion.setAnteriorPosicion(posicionMula);
        } else {
            posicionExtremo1.setSiguientePosicion(posicion);
            posicion.setAnteriorPosicion(posicionExtremo1);
        }
        posicionExtremo2 = posicion;
        
        // Ajusta la posición del nuevo extremo
        ajustarPosicionExtremo2();
        
    }
    
    private void ajustarPosicionExtremo1() {
        // La nueva posicion es horizontal
        posicionExtremo1.setAnchoPosicion(2); 
        posicionExtremo1.setAlturaPosicion(1);
        
        int fichaExtremo1 = posicionExtremo1.getFichaDomino().getExtremo1(); // Valor del extremo 1 de la ficha
        int fichaExtremo2 = posicionExtremo2.getFichaDomino().getExtremo2(); // Valor del extremo 2 de la ficha
        
        int posicionExtremo1SiguienteLocacionX = posicionExtremo1.getSiguientePosicion().getPosicionX(); // locacion x de la siguiente posicion en la lista
        int posicionExtremo1SiguienteLocacionY = posicionExtremo1.getSiguientePosicion().getPosicionY(); // locacion y de la siguiente posicion en la lista
        boolean posicionExtremo1SiguienteEsVertical = posicionExtremo1.getSiguientePosicion().getAnchoPosicion() == 1; // Si el ancho es 1, está en vertical, 2 de lo contrario
        
        // Asigna coordenadas y medidas del nuevo extremo
        if(direccionExtremo1 == IZQUIERDA) {
            if(posicionExtremo1SiguienteEsVertical) { // Si la posición siguiente de la lista era vertical
                posicionExtremo1.setPosicionX(posicionExtremo1SiguienteLocacionX - 2); // Se coloca 2 a la izquierda
                posicionExtremo1.setPosicionY(posicionExtremo1SiguienteLocacionY - 1); // Se coloca 1 abajo
                // rotacion
                if(fichaExtremo2 == valorExtremo1) {
                    posicionExtremo1.setRotacion(0);
                    valorExtremo1 = fichaExtremo1;
                } else {
                    posicionExtremo1.setRotacion(180);
                    valorExtremo1 = fichaExtremo2;
                }
            } else if(posicionExtremo1SiguienteLocacionX - 2 < 0) { // Si la coordenada X para la nueva posicion es menor a 0, no cabe y cambia de direccion
                posicionExtremo1.setPosicionX(posicionExtremo1SiguienteLocacionX); // Se coloca 0 a la izquierda
                posicionExtremo1.setPosicionY(posicionExtremo1SiguienteLocacionY - 1); // Se coloca 1 a abajo
                // La nueva cambia a vertical
                posicionExtremo1.setAnchoPosicion(1);
                posicionExtremo1.setAlturaPosicion(2);
                // Cambia de dirección para las siguientes posicionExtremo1es
                direccionExtremo1 = DERECHA;
                // rotacion
                if(fichaExtremo2 == valorExtremo1) {
                    posicionExtremo1.setRotacion(90);
                    valorExtremo1 = fichaExtremo1;
                } else {
                    posicionExtremo1.setRotacion(270);
                    valorExtremo1 = fichaExtremo2;
                }
            } else { // La nueva posición si cabe en el tablero y no proviene de una posicion vertical
                posicionExtremo1.setPosicionX(posicionExtremo1SiguienteLocacionX - 2); // Se coloca 2 a la izquierda
                posicionExtremo1.setPosicionY(posicionExtremo1SiguienteLocacionY); // Se coloca 0 a abajo
                // rotacion
                if(fichaExtremo2 == valorExtremo1) {
                    posicionExtremo1.setRotacion(0);
                    valorExtremo1 = fichaExtremo1;
                } else {
                    posicionExtremo1.setRotacion(180);
                    valorExtremo1 = fichaExtremo2;
                }
            }
            
        } else if(direccionExtremo1 == DERECHA) {
            if(posicionExtremo1SiguienteEsVertical) { // Si la posición siguiente de la lista era vertical
                posicionExtremo1.setPosicionX(posicionExtremo1SiguienteLocacionX + 1); // Se coloca 1 a la derecha
                posicionExtremo1.setPosicionY(posicionExtremo1SiguienteLocacionY - 1); // Se coloca 1 abajo
                // rotacion
                if(fichaExtremo1 == valorExtremo1) {
                    posicionExtremo1.setRotacion(0);
                    valorExtremo1 = fichaExtremo2;
                } else {
                    posicionExtremo1.setRotacion(180);
                    valorExtremo1 = fichaExtremo1;
                }
            } else if(posicionExtremo1SiguienteLocacionX + 4 > anchoTablero) { // Si la coordenada X para la nueva posicion es mayor que 20, no cabe y cambia de direccion
                posicionExtremo1.setPosicionX(posicionExtremo1SiguienteLocacionX + 1); // Se coloca 1 a la derecha
                posicionExtremo1.setPosicionY(posicionExtremo1SiguienteLocacionY - 1); // Se coloca 1 a abajo
                // La nueva posicon es vertical
                posicionExtremo1.setAnchoPosicion(1);
                posicionExtremo1.setAlturaPosicion(2);
                // Cambia de dirección para las siguientes posicionExtremo1es
                direccionExtremo1 = IZQUIERDA;
                // rotacion
                if(fichaExtremo1 == valorExtremo1) {
                    posicionExtremo1.setRotacion(270);
                    valorExtremo1 = fichaExtremo2;
                } else {
                    posicionExtremo1.setRotacion(90);
                    valorExtremo1 = fichaExtremo1;
                }
            } else { // La nueva posición si cabe en el tablero y no proviene de una posicion vertical
                posicionExtremo1.setPosicionX(posicionExtremo1SiguienteLocacionX + 2); // Se coloca 2 a la izquierda
                posicionExtremo1.setPosicionY(posicionExtremo1SiguienteLocacionY); // Se coloca 0 a abajo
                // rotacion
                if(fichaExtremo1 == valorExtremo1) {
                    posicionExtremo1.setRotacion(0);
                    valorExtremo1 = fichaExtremo2;
                } else {
                    posicionExtremo1.setRotacion(180);
                    valorExtremo1 = fichaExtremo1;
                }
            }
        }
    }
    
    
    private void ajustarPosicionExtremo2() {
        // La nueva posicion es horizontal
        posicionExtremo2.setAnchoPosicion(2); 
        posicionExtremo2.setAlturaPosicion(1);
        
        int fichaExtremo1 = posicionExtremo2.getFichaDomino().getExtremo1(); // Valor del extremo 1 de la ficha
        int fichaExtremo2 = posicionExtremo2.getFichaDomino().getExtremo2(); // Valor del extremo 2 de la ficha
        
        int posicionExtremo2AnteriorLocacionX = posicionExtremo2.getAnteriorPosicion().getPosicionX(); // locacion x de la anterior posicion en la lista
        int posicionExtremo2AnteriorLocacionY = posicionExtremo2.getAnteriorPosicion().getPosicionY(); // locacion y de la anterior posicion en la lista
        boolean posicionExtremo2AnteriorEsVertical = posicionExtremo2.getAnteriorPosicion().getAnchoPosicion() == 1; // Si el ancho es 1, está en vertical, 2 de lo contrario
        
        // Asigna coordenadas y medidas del nuevo extremo
        if(direccionExtremo2 == IZQUIERDA) {
            if(posicionExtremo2AnteriorEsVertical) { // Si la posición anterior de la lista era vertical
                posicionExtremo2.setPosicionX(posicionExtremo2AnteriorLocacionX - 2); // Se coloca 2 a la izquierda
                posicionExtremo2.setPosicionY(posicionExtremo2AnteriorLocacionY - 1); // Se coloca 1 abajo
                // rotacion
                if(fichaExtremo2 == valorExtremo2) {
                    posicionExtremo2.setRotacion(0);
                    valorExtremo2 = fichaExtremo1;
                } else {
                    posicionExtremo2.setRotacion(180);
                    valorExtremo2 = fichaExtremo2;
                }
            } else if(posicionExtremo2AnteriorLocacionX - 2 < 0) { // Si la coordenada X para la nueva posicion es menor a 0, no cabe y cambia de direccion
                posicionExtremo2.setPosicionX(posicionExtremo2AnteriorLocacionX); // Se coloca 0 a la izquierda
                posicionExtremo2.setPosicionY(posicionExtremo2AnteriorLocacionY - 1); // Se coloca 1 a abajo
                // La nueva cambia a vertical
                posicionExtremo2.setAnchoPosicion(1);
                posicionExtremo2.setAlturaPosicion(2);
                // Cambia de dirección para las siguientes posiciones
                direccionExtremo2 = DERECHA;
                // rotacion
                if(fichaExtremo2 == valorExtremo2) {
                    posicionExtremo2.setRotacion(270);
                    valorExtremo2 = fichaExtremo1;
                } else {
                    posicionExtremo2.setRotacion(90);
                    valorExtremo2 = fichaExtremo2;
                }
            } else { // La nueva posición si cabe en el tablero y no proviene de una posicion vertical
                posicionExtremo2.setPosicionX(posicionExtremo2AnteriorLocacionX - 2); // Se coloca 2 a la izquierda
                posicionExtremo2.setPosicionY(posicionExtremo2AnteriorLocacionY); // Se coloca 0 a abajo
                // rotacion
                if(fichaExtremo2 == valorExtremo2) {
                    posicionExtremo2.setRotacion(0);
                    valorExtremo2 = fichaExtremo1;
                } else {
                    posicionExtremo2.setRotacion(180);
                    valorExtremo2 = fichaExtremo2;
                }
            }
            
        } else if(direccionExtremo2 == DERECHA) {
            if(posicionExtremo2AnteriorEsVertical) { // Si la posición anterior de la lista era vertical
                posicionExtremo2.setPosicionX(posicionExtremo2AnteriorLocacionX + 1); // Se coloca 1 a la derecha
                posicionExtremo2.setPosicionY(posicionExtremo2AnteriorLocacionY - 1); // Se coloca 1 abajo
                // rotacion
                if(fichaExtremo1 == valorExtremo2) {
                    posicionExtremo2.setRotacion(0);
                    valorExtremo2 = fichaExtremo2;
                } else {
                    posicionExtremo2.setRotacion(180);
                    valorExtremo2 = fichaExtremo1;
                }
            } else if(posicionExtremo2AnteriorLocacionX + 4 > anchoTablero) { // Si la coordenada X para la nueva posicion es mayor que 20, no cabe y cambia de direccion
                posicionExtremo2.setPosicionX(posicionExtremo2AnteriorLocacionX + 1); // Se coloca 1 a la derecha
                posicionExtremo2.setPosicionY(posicionExtremo2AnteriorLocacionY - 1); // Se coloca 1 a abajo
                // La nueva posicon es vertical
                posicionExtremo2.setAnchoPosicion(1);
                posicionExtremo2.setAlturaPosicion(2);
                // Cambia de dirección para las siguientes posiciones
                direccionExtremo2 = IZQUIERDA;
                // rotacion
                if(fichaExtremo1 == valorExtremo2) {
                    posicionExtremo2.setRotacion(90);
                    valorExtremo2 = fichaExtremo2;
                } else {
                    posicionExtremo2.setRotacion(270);
                    valorExtremo2 = fichaExtremo1;
                }
            } else { // La nueva posición si cabe en el tablero y no proviene de una posicion vertical
                posicionExtremo2.setPosicionX(posicionExtremo2AnteriorLocacionX + 2); // Se coloca 2 a la izquierda
                posicionExtremo2.setPosicionY(posicionExtremo2AnteriorLocacionY); // Se coloca 0 a abajo
                // rotacion
                if(fichaExtremo1 == valorExtremo2) {
                    posicionExtremo2.setRotacion(0);
                    valorExtremo2 = fichaExtremo2;
                } else {
                    posicionExtremo2.setRotacion(180);
                    valorExtremo2 = fichaExtremo1;
                }
            }
        }
    }
    
    public PosicionEntity obtenerPosiblePosicionMula() {
        // Se supone este método sea llamado unicamente cuando aun no se ha
        // colocado la mula, por lo que se puede regresar esta posicion que
        // al momento de llamar este método, aún no se le ha asignado una ficha
        // a esta posición.
        return posicionMula;
    }
    
    public PosicionEntity obtenerPosiblePosicionExtremo1() {
        PosicionEntity posiblePosicion = new PosicionEntity();
        // La posible posición es horizontal
        posiblePosicion.setAnchoPosicion(2); 
        posiblePosicion.setAlturaPosicion(1);
        
        int posicionExtremo1X = posicionExtremo1.getPosicionX(); // locacion x de la posicion del extremo 1
        int posicionExtremo1Y = posicionExtremo1.getPosicionY(); // locacion y de la posicion del extremo 1
        boolean Extremo1EsVertical = posicionExtremo1.getAnchoPosicion() == 1; // Si el ancho es 1, está en vertical, 2 de lo contrario
        
        // Asigna coordenadas y medidas de la posible posición
        if(direccionExtremo1 == IZQUIERDA) {
            if(Extremo1EsVertical) { // Si la posición extremo 1 es vertical
                posiblePosicion.setPosicionX(posicionExtremo1X - 2); // Se coloca 2 a la izquierda
                posiblePosicion.setPosicionY(posicionExtremo1Y - 1); // Se coloca 1 abajo
            } else if(posicionExtremo1X - 2 < 0) { // Si la coordenada X para la posible posicion es menor a 0, no cabe y cambia de direccion
                posiblePosicion.setPosicionX(posicionExtremo1X); // Se coloca 0 a la izquierda
                posiblePosicion.setPosicionY(posicionExtremo1Y - 1); // Se coloca 1 a abajo
                // La posible posicion cambia a vertical
                posiblePosicion.setAnchoPosicion(1);
                posiblePosicion.setAlturaPosicion(2);
            } else { // La posible posición si cabe en el tablero y extremo1 no es una posicion vertical
                posiblePosicion.setPosicionX(posicionExtremo1X - 2); // Se coloca 2 a la izquierda
                posiblePosicion.setPosicionY(posicionExtremo1Y); // Se coloca 0 a abajo
            }
            
        } else if(direccionExtremo1 == DERECHA) {
            if(Extremo1EsVertical) { // Si la posición extremo 1 es vertical
                posiblePosicion.setPosicionX(posicionExtremo1X + 1); // Se coloca 1 a la derecha
                posiblePosicion.setPosicionY(posicionExtremo1Y - 1); // Se coloca 1 abajo
            } else if(posicionExtremo1X + 4 > anchoTablero) { // Si la coordenada X para la posible posicion es mayor que 20, no cabe y cambia de direccion
                posiblePosicion.setPosicionX(posicionExtremo1X + 1); // Se coloca 1 a la derecha
                posiblePosicion.setPosicionY(posicionExtremo1Y - 1); // Se coloca 1 a abajo
                // La nueva posicon es vertical
                posiblePosicion.setAnchoPosicion(1);
                posiblePosicion.setAlturaPosicion(2);
            } else { // La nueva posición si cabe en el tablero y no proviene de una posicion vertical
                posiblePosicion.setPosicionX(posicionExtremo1X + 2); // Se coloca 2 a la izquierda
                posiblePosicion.setPosicionY(posicionExtremo1Y); // Se coloca 0 a abajo
            }
        }
        
        return posiblePosicion;
    }
    
    
    public PosicionEntity obtenerPosiblePosicionExtremo2() {
        PosicionEntity posiblePosicion = new PosicionEntity();
        // La posible posición es horizontal
        posiblePosicion.setAnchoPosicion(2); 
        posiblePosicion.setAlturaPosicion(1);
        
        int posicionExtremo2X = posicionExtremo2.getPosicionX(); // locacion x de la posicion del extremo 2
        int posicionExtremo2Y = posicionExtremo2.getPosicionY(); // locacion y de la posicion del extremo 2
        boolean Extremo2EsVertical = posicionExtremo2.getAnchoPosicion() == 1; // Si el ancho es 1, está en vertical, 2 de lo contrario
        
        // Asigna coordenadas y medidas a la posible posición
        if(direccionExtremo2 == IZQUIERDA) {
            if(Extremo2EsVertical) { // Si el extremo 2 es vertical
                posiblePosicion.setPosicionX(posicionExtremo2X - 2); // Se coloca 2 a la izquierda
                posiblePosicion.setPosicionY(posicionExtremo2Y - 1); // Se coloca 1 abajo
            } else if(posicionExtremo2X - 2 < 0) { // Si la coordenada X para la posible posición es menor a 0, no cabe y cambia de direccion
                posiblePosicion.setPosicionX(posicionExtremo2X); // Se coloca 0 a la izquierda
                posiblePosicion.setPosicionY(posicionExtremo2Y - 1); // Se coloca 1 a abajo
                // La posible posición cambia a vertical
                posiblePosicion.setAnchoPosicion(1);
                posiblePosicion.setAlturaPosicion(2);
            } else { // La posible posición si cabe en el tablero y extremo 2 no es vertical
                posiblePosicion.setPosicionX(posicionExtremo2X - 2); // Se coloca 2 a la izquierda
                posiblePosicion.setPosicionY(posicionExtremo2Y); // Se coloca 0 a abajo
            }
            
        } else if(direccionExtremo2 == DERECHA) {
            if(Extremo2EsVertical) { // Si el extremo 2 es vertical
                posiblePosicion.setPosicionX(posicionExtremo2X + 1); // Se coloca 1 a la derecha
                posiblePosicion.setPosicionY(posicionExtremo2Y - 1); // Se coloca 1 abajo
            } else if(posicionExtremo2X + 4 > anchoTablero) { // Si la coordenada X para la posible posicion es mayor que 20, no cabe y cambia de direccion
                posiblePosicion.setPosicionX(posicionExtremo2X + 1); // Se coloca 1 a la derecha
                posiblePosicion.setPosicionY(posicionExtremo2Y - 1); // Se coloca 1 a abajo
                // La posible posicon es vertical
                posiblePosicion.setAnchoPosicion(1);
                posiblePosicion.setAlturaPosicion(2);
            } else { // La posible posición si cabe en el tablero y extremo 2 no es vertical
                posiblePosicion.setPosicionX(posicionExtremo2X + 2); // Se coloca 2 a la izquierda
                posiblePosicion.setPosicionY(posicionExtremo2Y); // Se coloca 0 a abajo
            }
        }
        
        return posiblePosicion;
    }
}
