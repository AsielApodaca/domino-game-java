/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto;

import java.awt.image.BufferedImage;


/**
 *
 * @author asielapodaca
 */
public class UsuarioDTO {
    
    private String idCliente;
    private String nombre;
    private String fuenteIcono;

    public UsuarioDTO() {
    }
    

    //provisional
    public UsuarioDTO(String nombre) {
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
