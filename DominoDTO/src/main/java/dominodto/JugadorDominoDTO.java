/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author hisam
 */
public class JugadorDominoDTO {

     private List<FichaDominoDTO> listaFichasJugador;
     private String nombre ;
     private Icon icono ;
     
    public JugadorDominoDTO(String nombre, Icon icono) {
        this.listaFichasJugador = new ArrayList<>();
        this.nombre = nombre ;
        this.icono = icono ;
    }
    
    

    public List<FichaDominoDTO> getListaFichasJugador() {
        return listaFichasJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public Icon getIcono() {
        return icono;
    }
    
    
    
}
