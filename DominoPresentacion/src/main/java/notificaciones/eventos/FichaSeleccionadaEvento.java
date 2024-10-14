/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificaciones.eventos;

import dominodto.FichaDominoDTO;

/**
 *
 * @author asielapodaca
 */
public class FichaSeleccionadaEvento extends Evento{
    
    private FichaDominoDTO fichaSeleccionada;
    
    public FichaSeleccionadaEvento() {
        super("FichaSeleccionadaEvento");
    }

    @Override
    public String getNombreEvento() {
        return super.getNombreEvento();
    }

    public FichaDominoDTO getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(FichaDominoDTO fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }
}
