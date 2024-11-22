/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class RespuestaMostrarFichasActualizadasDeJugador extends EventoRespuesta{
    
    private List<FichaDominoDTO> fichasDomino;
    
    public RespuestaMostrarFichasActualizadasDeJugador(String idCliente, List<FichaDominoDTO> fichasDomino) {
        super(idCliente);
        this.fichasDomino = fichasDomino;       
    }

    public List<FichaDominoDTO> getFichasDomino() {
        return fichasDomino;
    }
    
}
