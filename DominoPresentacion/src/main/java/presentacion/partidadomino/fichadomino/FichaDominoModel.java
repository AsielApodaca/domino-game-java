/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadomino;

import dominodto.FichaDominoDTO;
import dominodto.PosicionDTO;

/**
 *
 * @author asielapodaca
 */
public class FichaDominoModel {
    
    // Mayormente usado para panel de fichas del jugador local
    private FichaDominoDTO fichaDominoDTO; // Ficha que representa el panel
    private int anchoFicha; // ancho del panel de la ficha
    private int alturaFicha; // altura del panel de la ficha
    
    // Mayormente usado para fichas colocadas en el tablero
    private PosicionDTO posicionDTO; // Almacena coordenadas y ficha asignada
    private int rotacion; // Ángulo de rotación en radianes, 0, 90, 180, 270
    private int locacionX; // Locación en eje de las X
    private int locacionY; // Locación en eje de las Y
    
    // Imagenes de los numeros de cada extremo
    private String imgExtremo1; // Fuente de la imagen del extremo 1
    private String imgExtremo2; // Fuente de la imagen del extremo 1
    
    // Numero de extremos por lado, por ejemplo: 1 horizontal, 2 vertical, significa que la ficha está en vertical, 2 y 1 de lo contrario
    private int extremosHorizontal; // 2 o 1
    private int extremosVertical; // 2 o 1

    public FichaDominoModel(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
        this.anchoFicha = 30;
        this.alturaFicha = 60;
        this.extremosHorizontal = 1;
        this.extremosVertical = 2;
    }
    
    
    
}
