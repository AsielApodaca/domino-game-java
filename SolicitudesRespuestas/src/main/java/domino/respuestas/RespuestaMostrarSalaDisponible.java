/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.ConfiguracionJuegoDTO;
import dominodto.SalaDTO;
import java.util.List;

/**
 *
 * @author olive
 */
public class RespuestaMostrarSalaDisponible extends EventoRespuesta {

    public static final boolean ES_PARA_TODOS = true;
    private SalaDTO salaDisponible ;
    private ConfiguracionJuegoDTO configuracionJuego;
    
    public RespuestaMostrarSalaDisponible(ConfiguracionJuegoDTO configuracionJuego) {
        super(null);
        this.configuracionJuego = configuracionJuego ;
    }
    public RespuestaMostrarSalaDisponible(ConfiguracionJuegoDTO configuracionJuego, SalaDTO sala) {
        super(null);
        this.configuracionJuego = configuracionJuego ;
        this.salaDisponible = sala ;
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

    public ConfiguracionJuegoDTO getConfiguracionJuego() {
        return configuracionJuego;
    }

    public void setConfiguracionJuego(ConfiguracionJuegoDTO configuracionJuego) {
        this.configuracionJuego = configuracionJuego;
    }
    
}
