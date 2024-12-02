
package dominodto;

import java.util.Objects;


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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JugadorDominoDTO other = (JugadorDominoDTO) obj;
        return Objects.equals(this.idCliente, other.idCliente);
    }
    
    
    
}
