/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sala;

import domino.manejadores.ManejadorCliente;
import domino.manejadores.ManejadorServidor;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author olive
 */
public class Sala {
    
    private Map<String, ManejadorCliente> clientes ;
    private ManejadorServidor servidor ;
    private String id ;
    private int size ;
    
    public Sala(String id, ManejadorServidor servidor, int size) {
        clientes = new HashMap() ;
        this.servidor = servidor ;
        this.size = size ;
    }
    
    public void asignarServidor(ManejadorServidor servidor) {
        this.servidor = servidor ;
    }
    
    public ManejadorServidor getServidor() {
        return servidor ;
    }
    
    public void agregarCliente(String id, ManejadorCliente cliente) {
        this.clientes.put(id, cliente) ;
    }
    
    public Map<String, ManejadorCliente> obtenerClientes() {
        return clientes ;
    }
    
    public String getId() {
        return this.id ;
    }
    
    public int getSize() {
        return this.size ;
    }
    
}
