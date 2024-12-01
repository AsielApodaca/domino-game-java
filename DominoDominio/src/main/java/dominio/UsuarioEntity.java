/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;



/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class UsuarioEntity {

    private String idCliente;
    private String nombre;
    private String fuenteIcono;

    public UsuarioEntity(String idCliente, String nombre, String fuenteIcono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.fuenteIcono = fuenteIcono;
    }

    public UsuarioEntity(String idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
    }
    
    public UsuarioEntity(String nombre) {
        this.nombre = nombre;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFuenteIcono() {
        return fuenteIcono;
    }

    public void setFuenteIcono(String fuenteIcono) {
        this.fuenteIcono = fuenteIcono;
    }

    

}
