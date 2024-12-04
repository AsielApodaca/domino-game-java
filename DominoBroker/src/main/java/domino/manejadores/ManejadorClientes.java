/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.manejadores;

import com.google.gson.JsonObject;
import domino.conexiones.ConexionCliente;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author olive
 */
public class ManejadorClientes {
    
    private final Map<String, ConexionCliente> clientes ;
    private Long contadorIdClientes ;
    
    public ManejadorClientes() {
        this.clientes = new HashMap() ;
        this.contadorIdClientes = 0L ;
    }
    
    public Map<String, ConexionCliente> getClientes() {
        return this.clientes ;
    }
    
    public ConexionCliente agregarCliente(Socket socket) {
        contadorIdClientes += 1L ;
        String id = "CLI" + contadorIdClientes ;
        ConexionCliente cliente = new ConexionCliente(id, socket) ;
        this.clientes.put(id, cliente) ;
        return cliente ;
    }
    
    public ConexionCliente obtenerClientePorId(String id) {
        return clientes.get(id) ;
    }
    
    public void enviarRespuestaATodosLosClientes(JsonObject respuestaJSON) {
        clientes.forEach((id, cliente) ->{
            cliente.mandarRespuestaCliente(respuestaJSON);
        });
    }
}
