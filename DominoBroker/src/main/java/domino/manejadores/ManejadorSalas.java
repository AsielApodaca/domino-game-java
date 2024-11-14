/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.manejadores;

import com.google.gson.JsonObject;
import domino.conexiones.ConexionCliente;
import domino.conexiones.ConexionServidor;
import domino.sala.Sala;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author olive
 */
public class ManejadorSalas {
    private final Map<String, Sala> salas ;
    private Long contadorIdSalas ;
    
    public ManejadorSalas() {
        this.salas = new HashMap() ;
        this.contadorIdSalas = 0L ;
    }
    
    public Map<String, Sala> getSalas() {
        return this.salas ;
    }
    
    public void crearSala(ConexionCliente cliente, ConexionServidor servidor, JsonObject solicitudJSON) {
        if (servidor != null) {

            contadorIdSalas += 1L;
            String id = "SAL" + contadorIdSalas;
            
            enviarConfiguracionDePartida(servidor, solicitudJSON);
            
            Sala salaNueva = new Sala(id, servidor, solicitudJSON.get("size").getAsInt());
            salaNueva.agregarCliente(cliente.getId(), cliente);
            
            salas.put(id, salaNueva);
            System.out.println("Sala Creada con el ID: " + id);
            System.out.println("Se unio el cliente: " + cliente.getId() + " a la Sala: " + id);

        } else {
            System.out.println("No hay servidores libres.");
        }
    }
    
    public Sala obtenerSala(String id) {
        Sala salaBuscada = salas.values().stream()
                .filter(sala -> sala.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        if(salaBuscada != null) {
            return salaBuscada ;
        } else {
            return null ;
        }
    }
    
    public Sala obtenerSalaDeCliente(String id) {
        Sala salaDelCliente = salas.values().stream()
                .filter(sala -> sala.obtenerClientes().containsKey(id)) 
                .findFirst() 
                .orElse(null); 
        
        if(salaDelCliente != null) {
            return salaDelCliente ;
        } else {
            return null ;
        }
    }
    
    public void unirClienteASala(ConexionCliente cliente, JsonObject jsonObject) {
        Sala salaBuscada = obtenerSala(jsonObject.get("id_sala").getAsString()) ;
        
        salaBuscada.agregarCliente(cliente.getId(), cliente);
        
        System.out.println("Se unio el cliente: " + cliente.getId() + " a la Sala: " + salaBuscada.getId());
    }
    
    public void enviarSolicitudAServidor(ConexionCliente cliente, JsonObject solicitud) {
        Sala salaDelCliente = salas.values().stream()
                .filter(sala -> sala.obtenerClientes().containsKey(cliente.getId())) 
                .findFirst() 
                .orElse(null); 

        if (salaDelCliente != null) {
            salaDelCliente.getServidor().mandarSolicitudServidor(solicitud);
        } else {
            System.out.println("El cliente no esta conectado a una sala");
        }
    }
    
    private void enviarConfiguracionDePartida(ConexionServidor servidor, JsonObject solicitudJSON) {
        servidor.mandarSolicitudServidor(solicitudJSON);
    }
}
