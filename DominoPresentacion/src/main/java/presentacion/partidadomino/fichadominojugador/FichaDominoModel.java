/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadominojugador;

import dominodto.FichaDominoDTO;
import dominodto.CasillaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FichaDominoModel {

    private String imagenMargenDomino;
    // Mayormente usado para panel de fichas del jugador local
    private FichaDominoDTO fichaDominoDTO; // Ficha que representa el panel
    private final int anchoFicha = 30; // ancho del panel de la ficha
    private final int alturaFicha = 60; // altura del panel de la ficha    

    // Mayormente usado para fichas colocadas en el tablero
    private CasillaDTO casillaDTO; // Almacena coordenadas y ficha asignada
    private int rotacion; // Ángulo de rotación en radianes, 0, 90, 180, 270
    private int locacionX; // Locación en eje de las X
    private int locacionY; // Locación en eje de las Y

    // Valor de los extremos
    private int valorExtremo1;
    private int valorExtremo2;

    // Imagenes de los numeros de cada extremo
    private String imgExtremo1; // Fuente de la imagen del extremo 1
    private String imgExtremo2; // Fuente de la imagen del extremo 1

    private boolean compatible; // Si la ficha es compatible con uno de los 2 extremos
    private boolean seleccionada; // Si la ficha está seleccionada
    private boolean colocada; // Si la ficha ha sido colocada en el tablero;

    private final String fondoFichaNormal = "/multimedia/DominoFondo.png";
    private String fondoFicha;
    private final String fondoSeleccionado = "/multimedia/DominoSeleccionadoFondo.png";
    private final String fondoCompatible = "/multimedia/DominoCompatibleFondo.png";

    private float escala; // Escala

    public FichaDominoModel(FichaDominoDTO fichaDominoDTO) { // Se instancia cuando la ficha es para la mano del jugador local
        this.fichaDominoDTO = fichaDominoDTO;
        this.compatible = true;
        this.fondoFicha = "/multimedia/DominoFondo.png";
    }

    public void setImagenMargenDomino(String imagenMargenDomino) {
        this.imagenMargenDomino = imagenMargenDomino;
    }

    public String getFondoFicha() {
        return fondoFicha;
    }

    public void setFondoFicha(String fondoFicha) {
        this.fondoFicha = fondoFicha;
    }

    public String getFondoFichaNormal() {
        return fondoFichaNormal;
    }

    public String getFondoSeleccionado() {
        return fondoSeleccionado;
    }

    public String getFondoCompatible() {
        return fondoCompatible;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }

//    private void iniciarFichaJugadorLocal() {
//        this.anchoFicha = 30;
//        this.alturaFicha = 60;
//        this.rotacion = 0;
//        
//        this.valorExtremo1 = this.fichaDominoDTO.getValorExtremo1();
//        this.valorExtremo2 = this.fichaDominoDTO.getValorExtremo2();
//            }
//
//    private void iniciarFichaParaTablero() {
//        this.fichaDominoDTO = this.casillaDTO.getFichaDominoDTO();
//        this.rotacion = this.casillaDTO.getRotacion();
//        
//        switch(this.rotacion) { // Asigna orientación de la ficha
//            case 0: // Horizontal
//                this.anchoFicha = 30;
//                this.alturaFicha = 15;
//            case 90: // Vertical
//                this.anchoFicha = 15;
//                this.alturaFicha = 30;
//                voltearExtremosFicha();
//                break;
//            case 180: // Horizontal
//                this.anchoFicha = 30;
//                this.alturaFicha = 15;
//                voltearExtremosFicha();
//            case 270: // Vertical
//                this.anchoFicha = 15;
//                this.alturaFicha = 30;
//        }
//                
//    }
    private void voltearExtremosFicha() {
        int valorExtremo1Copia = this.valorExtremo1;
        this.valorExtremo1 = this.valorExtremo2;
        this.valorExtremo2 = valorExtremo1Copia;
    }

    public String getImagenMargenDomino() {
        return imagenMargenDomino;
    }

    public String getImgExtremo1() {
        return imgExtremo1;
    }

    public String getImgExtremo2() {
        return imgExtremo2;
    }

    public int getAnchoFicha() {
        return anchoFicha;
    }

    public int getAlturaFicha() {
        return alturaFicha;
    }

    public int getValorExtremo1() {
        return fichaDominoDTO.getValorExtremo1();
    }

    public int getValorExtremo2() {
        return fichaDominoDTO.getValorExtremo2();
    }

    public void setValorExtremo1(int valorExtremo1) {
        this.valorExtremo1 = valorExtremo1;
    }

    public void setValorExtremo2(int valorExtremo2) {
        this.valorExtremo2 = valorExtremo2;
    }

    public void setImgExtremo1(String imgExtremo1) {
        this.imgExtremo1 = imgExtremo1;
    }

    public void setImgExtremo2(String imgExtremo2) {
        this.imgExtremo2 = imgExtremo2;
    }

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }

    public void setFichaDominoDTO(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
    }

}
