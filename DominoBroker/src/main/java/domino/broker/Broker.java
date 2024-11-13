/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.broker;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import domino.enums.Status;
import domino.manejadores.ManejadorCliente;
import domino.manejadores.ManejadorServidor;
import domino.solicitudes.SolicitudColocarFicha;
import domino.solicitudes.SolicitudCrearSala;
import domino.solicitudes.SolicitudUnirseSala;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import sala.Sala;

/**
 *
 * @author olive
 */
public class Broker {

    private final int PORT = 3000 ;
    private final Map<String, ManejadorCliente> clientes ;
    private final Map<String, ManejadorServidor> servers ;
    private final Map<String, Sala> salas ;
    private Long contadorIdClientes ;
    private Long contadorIdServers ;
    private Long contadorIdSalas ;
    private Gson gson ;
    
    public Broker() {
        this.clientes = new HashMap() ;
        this.servers = new HashMap() ;
        this.salas = new HashMap() ;
        this.gson = new Gson() ;
        this.contadorIdClientes = 0L ;
        this.contadorIdServers = 0L ;
        this.contadorIdSalas = 0L ;
        runBroker() ;
    }
    
    public void runBroker() {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor corriendo en puerto: " + PORT);
            
            while(true) {
                Socket newConenctionSocket = serverSocket.accept() ;
                
                new Thread(() -> manejarConexiones(newConenctionSocket)).start();
            }
        } catch (IOException e) {
        }
    }
    
    private void manejarConexiones(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String jsonString = reader.readLine() ;
            
            String type = gson.fromJson(jsonString, String.class) ;
            String id ;
            
            if(type.equals("CLIENT")) {
                contadorIdClientes += 1L ;
                id = "CLI" + contadorIdClientes ;
                
                ManejadorCliente cliente = new ManejadorCliente(id, socket) ;
                clientes.put(id, cliente) ;
                
                System.out.println("Se ha conectado un nuevo Cliente con el id: " + id) ;
                
                new Thread(() -> redirigirSolicitudes(cliente)).start(); ;
            } else if (type.equals("SERVER")) {
                contadorIdServers += 1L ;
                id = "SER" + contadorIdServers ;
                
                ManejadorServidor servidor = new ManejadorServidor(id, socket) ;
                servers.put(id, servidor) ;
                
                System.out.println("Se ha conectado un nuevo Server con el id: " + id) ;
                
                new Thread(() -> redirigirRespuestas(servidor)).start();
            } else {
                System.out.println("Conexion desconocida");
                socket.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al manejar la conexion...");
        }
    }
    
    private void redirigirSolicitudes(ManejadorCliente cliente) {
        try {
            String solicitud ;
            
            while((solicitud = cliente.getReader().readLine()) != null) {
                JsonObject solicitudJSON = JsonParser.parseString(solicitud).getAsJsonObject() ;
                solicitudJSON.addProperty("id_cliente", cliente.getId());
          
                String tipoSolicitud = solicitudJSON.get("tipo").getAsString();

                if (isJsonInstanceOf(solicitud, SolicitudCrearSala.class)) {
                    crearSala(cliente, solicitudJSON);
                } else if (isJsonInstanceOf(solicitud, SolicitudUnirseSala.class)) {
                    unirseSala(cliente, solicitudJSON);
                } else if (isJsonInstanceOf(solicitud, SolicitudColocarFicha.class)) {
                    colocarFicha(cliente, solicitudJSON);
                } else {
                    throw new AssertionError("Tipo de solicitud desconocido: " + tipoSolicitud);
                }
                
            }
        } catch (Exception e) {
        }
    }
    
    private void redirigirRespuestas(ManejadorServidor servidor) {
        
    }

    private void crearSala(ManejadorCliente cliente, JsonObject solicitud) {
        ManejadorServidor servidorLibre = servers.values().stream()
                .filter(servidor -> servidor.getStatus() == Status.LIBRE)
                .findFirst()
                .orElse(null);

        if (servidorLibre != null) {

            contadorIdSalas += 1L;
            String id = "SAL" + contadorIdSalas;
            
            servidorLibre.mandarSolicitudServidor(solicitud);
            
            Sala salaNueva = new Sala(id, servidorLibre, solicitud.get("size").getAsInt());
            salaNueva.agregarCliente(cliente.getId(), cliente);
            
            salas.put(id, salaNueva);
            System.out.println("Sala Creada con el ID: " + id);
            System.out.println("Se unio el cliente: " + cliente.getId() + " a la Sala: " + id);

        } else {
            System.out.println("No hay servidores libres.");
        }
    }
    
    private void unirseSala(ManejadorCliente cliente, JsonObject solicitud) {
        String idSala = solicitud.get("id_sala").getAsString() ;
        Sala salaBuscada = salas.values().stream()
                .filter(sala -> sala.getId().equals(idSala))
                .findFirst()
                .orElse(null);
        
        salaBuscada.agregarCliente(cliente.getId(), cliente);
        
        System.out.println("Se unio el cliente: " + cliente.getId() + " a la Sala: " + idSala);
    }
    
    private void colocarFicha(ManejadorCliente cliente, JsonObject solicitud) {
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

    public <T> boolean isJsonInstanceOf(String json, Class<T> clase) {
        try {
            gson.fromJson(json, clase);
            return true;
        } catch (JsonSyntaxException | NullPointerException e) {
            return false;
        }
    }
    
}
