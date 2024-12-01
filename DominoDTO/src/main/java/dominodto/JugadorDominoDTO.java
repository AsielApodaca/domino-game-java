
package dominodto;


/**
 *
 * @author hisam
 */
public class JugadorDominoDTO {

     private String idCliente;
     private String nombre ;
     private String fuenteIcono ;
     
    public JugadorDominoDTO(String idCliente, String nombre, String fuenteIcono) {
        this.idCliente = idCliente;
        this.nombre = nombre ;
        this.fuenteIcono = fuenteIcono ;
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

    public String getFuenteIcono() {
        return fuenteIcono;
    }
    
    
    
}
