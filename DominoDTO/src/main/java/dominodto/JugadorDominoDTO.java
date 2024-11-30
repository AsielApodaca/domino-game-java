
package dominodto;

import java.awt.image.BufferedImage;


/**
 *
 * @author hisam
 */
public class JugadorDominoDTO {

     private String idCliente;
     private String nombre ;
     private BufferedImage icono ;
     
    public JugadorDominoDTO(String idCliente, String nombre, BufferedImage icono) {
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

    public BufferedImage getIcono() {
        return icono;
    }
    
    
    
}
