/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.awt.image.BufferedImage;


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
    private BufferedImage icono;

    public UsuarioEntity(String idCliente, String nombre, BufferedImage icono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.icono = icono;
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

    public BufferedImage getIcono() {
        return icono;
    }

    public void setIcono(BufferedImage icono) {
        this.icono = icono;
    }

    

}
