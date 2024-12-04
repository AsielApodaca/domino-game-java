/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.sala;

import domino.conexiones.ConexionCliente;
import domino.conexiones.ConexionServidor;
import domino.enums.Status;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Sala {
    
    private Map<String, ConexionCliente> clientes ;
    private ConexionServidor servidor ;
    private String id ;
    private int size ;
    private ConexionCliente anfitrion ;
    private Status statusPartida ;
    
    public Sala(String id, ConexionServidor servidor, int size) {
        this.id = id ;
        this.clientes = new HashMap() ;
        this.servidor = servidor ;
        this.size = size ;
    }
    
    public void asignarServidor(ConexionServidor servidor) {
        this.servidor = servidor ;
    }
    
    public ConexionServidor getServidor() {
        return servidor ;
    }
    
    public void agregarCliente(String id, ConexionCliente cliente) {
        this.clientes.put(id, cliente) ;
    }
    
    public Map<String, ConexionCliente> obtenerClientes() {
        return clientes ;
    }
    
    public ConexionCliente obtenerCliente(String id) {
        return clientes.get(id) ;
    }
    
    public void eliminarCliente(String id) {
        this.clientes.remove(id) ;
    }
    
    public String getId() {
        return this.id ;
    }
    
    public int getSize() {
        return this.size ;
    }

    public Map<String, ConexionCliente> getClientes() {
        return clientes;
    }

    public void setClientes(Map<String, ConexionCliente> clientes) {
        this.clientes = clientes;
    }

    public ConexionCliente getAnfitrion() {
        return anfitrion;
    }

    public void setAnfitrion(ConexionCliente anfitrion) {
        this.anfitrion = anfitrion;
    }

    public Status getStatusPartida() {
        return statusPartida;
    }

    public void setStatusPartida(Status statusPartida) {
        this.statusPartida = statusPartida;
    }
    
}
