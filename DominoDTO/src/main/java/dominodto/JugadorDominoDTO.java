/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hisam
 */
public class JugadorDominoDTO {

     private List<FichaDominoDTO> listaFichasJugador;
     
    public JugadorDominoDTO() {
        this.listaFichasJugador = new ArrayList<>();
    }
    
    

    public List<FichaDominoDTO> getListaFichasJugador() {
        return listaFichasJugador;
    }
    
    
    
}
