/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.itson.dominodominio;

import dev.itson.dominoenums.Posicion;

/**
 *
 * @author olive
 */
public class FichaDomino {
    
    private Integer extremo1 ;
    private Integer extremo2 ;
    private Posicion posicion ;
    
    public FichaDomino(Integer extremo1, Integer extremo2) {
        this.extremo1 = extremo1 ;
        this.extremo2 = extremo2 ;
    }
        
    public FichaDomino(Integer extremo1, Integer extremo2, Posicion posicion) {
        this.extremo1 = extremo1 ;
        this.extremo2 = extremo2 ;
        this.posicion = posicion ;
    }

    public Integer getExtremo1() {
        return extremo1;
    }

    public void setExtremo1(Integer extremo1) {
        this.extremo1 = extremo1;
    }

    public Integer getExtremo2() {
        return extremo2;
    }

    public void setExtremo2(Integer extremo2) {
        this.extremo2 = extremo2;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
    
    
}
