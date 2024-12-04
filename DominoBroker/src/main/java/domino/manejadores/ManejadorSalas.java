/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.manejadores;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import deserializador.Deserializador;
import deserializador.Serializador;
import domino.conexiones.ConexionCliente;
import domino.conexiones.ConexionServidor;
import domino.enums.Status;
import domino.respuestas.RespuestaMostrarSalaDisponible;
import domino.respuestas.RespuestaOcultarSalaDisponible;
import domino.sala.Sala;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudAbandonarSala;
import domino.solicitudes.SolicitudObtenerSalasDisponibles;
import dominodto.SalaDTO;
import dominodto.UsuarioDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    public void cerrarSala(ConexionServidor servidor) {
        Sala salaDelServidor = obtenerSalaDeServidor(servidor.getId()) ;
        salas.remove(salaDelServidor.getId()) ;
    }
    
    public void crearSala(ConexionCliente cliente, ConexionServidor servidor, JsonObject solicitudJSON) {
        if (servidor != null) {

            contadorIdSalas += 1L;
            String id = "SAL" + contadorIdSalas;
            
            enviarConfiguracionDePartida(servidor, solicitudJSON);
            servidor.setStatus(Status.OCUPADO);
            
            Sala salaNueva = new Sala(id, servidor, solicitudJSON.get("limiteJugadores").getAsInt());
            salaNueva.agregarCliente(cliente.getId(), cliente);
            salaNueva.setAnfitrion(cliente);
            salaNueva.setStatusPartida(Status.OCUPADO);
            
            salas.put(id, salaNueva);
            System.out.println("Sala Creada con el ID: " + id);
            System.out.println("Se unio el cliente: " + cliente.getId() + " a la Sala: " + id);

        } else {
            System.out.println("No hay servidores libres.");
        }
    }
    
    public void enviarSolicitudObtenerSalasDisponibles(ConexionCliente cliente, JsonObject jsonObject) {
        
        salas.forEach((id, sala) -> {
            if(sala.getStatusPartida() != Status.EN_PARTIDA) {
                SolicitudObtenerSalasDisponibles solicitud = Deserializador.convertirJSONAEvento(jsonObject.toString()) ;
                enviarSolicitudAServidor(cliente, jsonObject);
            }
        });
        
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
        
        System.out.println("Se unio el cliente: " + cliente.getId() + " a la Sala con el ID: " + idSala);
    }
    
    public void eliminarClienteDeSala(ConexionCliente cliente) {
        Sala salaDelCliente = obtenerSalaDeCliente(cliente.getId()) ;
        
        salaDelCliente.eliminarCliente(cliente.getId());
        
        verificarSalaVacia(salaDelCliente);
        
        System.out.println("El Cliente con el ID: " + cliente.getId() + " ha abandonado la Sala con el ID: " + salaDelCliente.getId());
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
    
    public void enviarRespuestaObtenerSalaDisponible(ConexionServidor servidor, JsonObject respuesta, ManejadorClientes manejadorClientes) {
        Sala salaDelServidor = obtenerSalaDeServidor(servidor.getId()) ;
        
        if(salaDelServidor != null) {
            RespuestaMostrarSalaDisponible respuestaSala = Deserializador.convertirJSONAEvento(respuesta.toString()) ;
            SalaDTO salaDisponible = new SalaDTO(salaDelServidor.getId(), salaDelServidor.getSize()) ;
            salaDisponible.setAnfitrion(new UsuarioDTO(salaDelServidor.getAnfitrion().getId()));
            respuestaSala.setSalaDisponible(salaDisponible);
            String respuestaString = Serializador.convertirEventoRespuestaAJSON(respuestaSala) ;
            JsonObject respuestaJSON = JsonParser.parseString(respuestaString).getAsJsonObject() ;
            manejadorClientes.enviarRespuestaATodosLosClientes(respuestaJSON);
        } else {
            System.out.println("El Servidor no esta conectado a una sala");
        }
    }
    
    public void enviarRespuestaOcultarSalaDisponible(ConexionServidor servidor, JsonObject respuesta, ManejadorClientes manejadorClientes) {
        Sala salaDelServidor = obtenerSalaDeServidor(servidor.getId()) ;
        
        if(salaDelServidor != null) {
            RespuestaOcultarSalaDisponible respuestaSala = Deserializador.convertirJSONAEvento(respuesta.toString()) ;
            respuestaSala.getSalaDisponible().setIdSala(salaDelServidor.getId());
            respuestaSala.getSalaDisponible().setSize(salaDelServidor.getSize());
            respuestaSala.getSalaDisponible().setAnfitrion(new UsuarioDTO(salaDelServidor.getAnfitrion().getId()));
            String respuestaJSON = Serializador.convertirEventoRespuestaAJSON(respuestaSala) ;
            manejadorClientes.enviarRespuestaATodosLosClientes(respuesta);
        } else {
            System.out.println("El Servidor no esta conectado a una sala");
        }
    }
    
    public void enviarSolicitudAbandonarSalaClientePorDesconexion(ConexionCliente cliente) {
        Sala salaDelCliente = obtenerSalaDeCliente(cliente.getId()) ;
        UsuarioDTO clienteQueAbandona = new UsuarioDTO() ;
        clienteQueAbandona.setIdCliente(cliente.getId());
        EventoSolicitud eventoSolicitudAbandonarSala = new SolicitudAbandonarSala(clienteQueAbandona) ;
        eventoSolicitudAbandonarSala.setIdCliente(cliente.getId());
        JsonObject solicitudAbandonarSala = JsonParser.parseString(Serializador.convertirEventoSolicitudAJSON(eventoSolicitudAbandonarSala)).getAsJsonObject() ;
        salaDelCliente.getServidor().mandarSolicitudServidor(solicitudAbandonarSala);
        eliminarClienteDeSala(cliente);
        System.out.println("El Cliente con el ID: " + cliente.getId() + " se ha desconectado de la Sala con el ID: " + salaDelCliente.getId());
    }
    
    public void enviarSolicitudIniciarPartida(ConexionCliente cliente, JsonObject solicitud) {
        Sala salaDelCliente = obtenerSalaDeCliente(cliente.getId()) ;
        salaDelCliente.setStatusPartida(Status.EN_PARTIDA);
        enviarSolicitudAServidor(cliente, solicitud);
    }
    
    private void enviarConfiguracionDePartida(ConexionServidor servidor, JsonObject solicitudJSON) {
        servidor.mandarSolicitudServidor(solicitudJSON);
    }
    
    private void verificarSalaVacia(Sala sala) {
        if(sala.getClientes().isEmpty()) {
            sala.getServidor().setStatus(Status.LIBRE);
            salas.remove(sala.getId()) ;
            System.out.println("Se ha eliminado la Sala con el ID: " + sala.getId());
        }
    }
}
