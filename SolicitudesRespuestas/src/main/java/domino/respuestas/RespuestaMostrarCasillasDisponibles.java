/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.CasillaDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class RespuestaMostrarCasillasDisponibles extends EventoRespuesta{
    public static final boolean ES_PARA_TODOS = false;
    private List<CasillaDTO> casillas;
    
    public RespuestaMostrarCasillasDisponibles(String idCliente, List<CasillaDTO> casillas) {
        super(idCliente);
        this.casillas = casillas;
    }

    public List<CasillaDTO> getCasillas() {
        return casillas;
    }
    
    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS;
    }
    
}
