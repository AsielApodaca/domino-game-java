
package dominodto;

import javax.swing.Icon;

/**
 *
 * @author hisam
 */
public class JugadorDominoDTO {

     private String idCliente;
     private String nombre ;
     private Icon icono ;
     
    public JugadorDominoDTO(String idCliente, String nombre, Icon icono) {
        this.idCliente = idCliente;
        this.nombre = nombre ;
        this.icono = icono ;
    }
    
    public JugadorDominoDTO(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public Icon getIcono() {
        return icono;
    }
    
    
    
}
