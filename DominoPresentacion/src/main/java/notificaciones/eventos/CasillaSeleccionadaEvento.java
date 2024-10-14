/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificaciones.eventos;

import dominodto.CasillaDTO;

/**
 *
 * @author asielapodaca
 */
public class CasillaSeleccionadaEvento extends Evento{
    
    private CasillaDTO casilla;
    
    public CasillaSeleccionadaEvento() {
        super("CasillaSeleccionadaEvento");
    }

    public CasillaDTO getCasilla() {
        return casilla;
    }

    public void setCasilla(CasillaDTO casilla) {
        this.casilla = casilla;
    }
    
}
