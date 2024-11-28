/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.manejadores;

import com.google.gson.JsonObject;
import domino.conexiones.ConexionCliente;
import domino.conexiones.ConexionServidor;
import domino.enums.Status;
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
            servidor.setStatus(Status.OCUPADO);
            
            Sala salaNueva = new Sala(id, servidor, solicitudJSON.get("limiteJugadores").getAsInt());
            salaNueva.agregarCliente(cliente.getId(), cliente);
            
            salas.put(id, salaNueva);
            System.out.println("Sala Creada con el ID: " + id);
            System.out.println("Se unio el cliente: " + cliente.getId() + " a la Sala: " + id);

        } else {
            System.out.println("No hay servidores libres.");
        }
    }
    
    public Sala obtenerSala(String id) {
        Sala salaBuscada = salas.get(id) ;
        
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
        
        return salaDelCliente ;
    }
    
    public Sala obtenerSalaDeServidor(String id) {
        Sala salaDelServidor = salas.values().stream()
                .filter(sala -> sala.getServidor().getId() == id) 
                .findFirst() 
                .orElse(null); 
        
        return salaDelServidor ;
    }
    
    public void unirClienteASala(ConexionCliente cliente, JsonObject jsonObject) {
        String idSala = jsonObject.get("idSala").getAsString() ;
        Sala salaBuscada = obtenerSala(idSala) ;
        
        salaBuscada.agregarCliente(cliente.getId(), cliente);
        
        System.out.println("Se unio el cliente: " + cliente.getId() + " a la Sala: " + idSala);
    }
    
    public void enviarSolicitudAServidor(ConexionCliente cliente, JsonObject solicitud) {
        Sala salaDelCliente = obtenerSalaDeCliente(cliente.getId()) ;
        
        if (salaDelCliente != null) {
            solicitud.addProperty("idCliente", cliente.getId());
            salaDelCliente.getServidor().mandarSolicitudServidor(solicitud);
        } else {
            System.out.println("El cliente no esta conectado a una sala");
        }
    }
    
    public void enviarRespuestaACliente(ConexionServidor servidor, JsonObject respuesta) {
        Sala salaDelServidor = obtenerSalaDeServidor(servidor.getId()) ;
        
        if(salaDelServidor != null) {
            String idCliente = respuesta.get("idCliente").getAsString() ;
            
            ConexionCliente cliente = salaDelServidor.obtenerCliente(idCliente) ;
            cliente.mandarRespuestaCliente(respuesta);
        } else {
            System.out.println("El Servidor no esta conectado a una sala");
        }
    }
    
    public void enviarRespuestaATodosLosClientes(ConexionServidor servidor, JsonObject respuesta) {
        Sala salaDelServidor = obtenerSalaDeServidor(servidor.getId()) ;
        
        if(salaDelServidor != null) {
            salaDelServidor.obtenerClientes().forEach((id, cliente) -> {
                cliente.mandarRespuestaCliente(respuesta);
            });
        } else {
            System.out.println("El Servidor no esta conectado a una sala");
        }
    }
    
    private void enviarConfiguracionDePartida(ConexionServidor servidor, JsonObject solicitudJSON) {
        servidor.mandarSolicitudServidor(solicitudJSON);
    }
}
