/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadomino;

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
    private int anchoFicha; // ancho del panel de la ficha
    private int alturaFicha; // altura del panel de la ficha    
    private List<FichaDominoView> observers;

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

    public FichaDominoModel() {
    }

    
    public FichaDominoModel(FichaDominoDTO fichaDominoDTO) { // Se instancia cuando la ficha es para la mano del jugador local
        this.fichaDominoDTO = fichaDominoDTO;
        this.observers = new ArrayList<>();
        asignarExtremos(fichaDominoDTO);
        iniciarFichaJugadorLocal();
    }
    

    public FichaDominoModel(CasillaDTO casillaDTO) { // Se instancia cuando la ficha es para colocar en el tablero
        this.casillaDTO = casillaDTO;
        iniciarFichaParaTablero();
    }
    
    public void asignarExtremos(FichaDominoDTO ficha){
        valorExtremo1 = ficha.getValorExtremo1();
        valorExtremo2 = ficha.getValorExtremo2();
    }

    public void addObserver(FichaDominoView view) {
        observers.add(view);
    }
   


    private void iniciarFichaJugadorLocal() {
        this.anchoFicha = 30;
        this.alturaFicha = 60;
        this.rotacion = 0;
        
        this.valorExtremo1 = this.fichaDominoDTO.getValorExtremo1();
        this.valorExtremo2 = this.fichaDominoDTO.getValorExtremo2();
        
        cargarImagenesFicha(this.valorExtremo1, this.valorExtremo2);
    }

    private void iniciarFichaParaTablero() {
        this.fichaDominoDTO = this.casillaDTO.getFichaDominoDTO();
        this.rotacion = this.casillaDTO.getRotacion();
        
        switch(this.rotacion) { // Asigna orientación de la ficha
            case 0: // Horizontal
                this.anchoFicha = 30;
                this.alturaFicha = 15;
            case 90: // Vertical
                this.anchoFicha = 15;
                this.alturaFicha = 30;
                voltearExtremosFicha();
                break;
            case 180: // Horizontal
                this.anchoFicha = 30;
                this.alturaFicha = 15;
                voltearExtremosFicha();
            case 270: // Vertical
                this.anchoFicha = 15;
                this.alturaFicha = 30;
        }
        
        cargarImagenesFicha(valorExtremo1, valorExtremo2);
        
    }
    
    private void voltearExtremosFicha() {
        int valorExtremo1Copia = this.valorExtremo1;
        this.valorExtremo1 = this.valorExtremo2;
        this.valorExtremo2 = valorExtremo1Copia;
    }
    
    private void cargarImagenesFicha(int primerValor, int segundoValor) {
        this.imagenMargenDomino = "/multimedia/DominoFondo.png";
        this.imgExtremo1 = String.format("/multimedia/Domino%d.png", primerValor);
        this.imgExtremo2 = String.format("/multimedia/Domino%d.png", segundoValor);
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

    public void setAnchoFicha(int anchoFicha) {
        this.anchoFicha = anchoFicha;
    }

    public int getAlturaFicha() {
        return alturaFicha;
    }

    public void setAlturaFicha(int alturaFicha) {
        this.alturaFicha = alturaFicha;
    }

    public void setDimensiones(int ancho, int alto) {
        this.anchoFicha = ancho;
        this.alturaFicha = alto;
        notifyObservers();
    }

    public void notifyObservers() {
        for (FichaDominoView view : observers) {
            view.actualizar();
        }
    }

}
