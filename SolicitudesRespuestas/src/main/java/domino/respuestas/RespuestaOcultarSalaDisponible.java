/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.SalaDTO;

/**
 *
 * @author olive
 */
public class RespuestaOcultarSalaDisponible extends EventoRespuesta {

    public static final boolean ES_PARA_TODOS = true;
    private SalaDTO salaDisponible ;
    
    public RespuestaOcultarSalaDisponible() {
        super(null);
    }

    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS ;
    }

    public SalaDTO getSalaDisponible() {
        return salaDisponible;
    }

    public void setSalaDisponible(SalaDTO salaDisponible) {
        this.salaDisponible = salaDisponible;
    }
    
}
