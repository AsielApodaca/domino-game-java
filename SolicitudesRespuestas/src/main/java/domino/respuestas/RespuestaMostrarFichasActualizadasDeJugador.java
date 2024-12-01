/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class RespuestaMostrarFichasActualizadasDeJugador extends EventoRespuesta{
    public static final boolean ES_PARA_TODOS = false;
    private List<FichaDominoDTO> fichasDomino;
    
    public RespuestaMostrarFichasActualizadasDeJugador(String idCliente, List<FichaDominoDTO> fichasDomino) {
        super(idCliente);
        this.fichasDomino = fichasDomino;       
    }

    public List<FichaDominoDTO> getFichasDomino() {
        return fichasDomino;
    }
    
    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS;
    }
    
}
