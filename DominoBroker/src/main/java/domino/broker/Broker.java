/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.broker;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import deserializador.Deserializador;
import domino.enums.Status;
import domino.conexiones.ConexionCliente;
import domino.conexiones.ConexionServidor;
import domino.manejadores.ManejadorClientes;
import domino.manejadores.ManejadorSalas;
import domino.manejadores.ManejadorServidores;
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
import domino.sala.Sala;

/**
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Broker {

    private final int PORT = 3000 ;
    private final ManejadorClientes manejadorClientes ;
    private final ManejadorServidores manejadorServidores ;
    private final ManejadorSalas manejadorSalas ;
    private Gson gson ;
    
    public Broker() {
        this.manejadorClientes = new ManejadorClientes() ;
        this.manejadorServidores = new ManejadorServidores() ;
        this.manejadorSalas = new ManejadorSalas() ;
        this.gson = new Gson() ;
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
                ConexionCliente cliente = manejadorClientes.agregarCliente(socket) ;
                
                System.out.println("Se ha conectado un nuevo Cliente con el id: " + cliente.getId()) ;
                
                new Thread(() -> redirigirSolicitudes(cliente)).start(); ;
            } else if (type.equals("SERVER")) {
                ConexionServidor servidor = manejadorServidores.agregarServidor(socket) ;
                
                System.out.println("Se ha conectado un nuevo Server con el id: " + servidor.getId()) ;
                
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
    
    private void redirigirSolicitudes(ConexionCliente cliente) {
        try {
            String solicitud ;
            
            while((solicitud = cliente.getReader().readLine()) != null) {
                JsonObject solicitudJSON = JsonParser.parseString(solicitud).getAsJsonObject() ;
                solicitudJSON.addProperty("id_cliente", cliente.getId());
          
                String tipoSolicitud = solicitudJSON.get("tipo").getAsString();

                if (Deserializador.esJsonInstanciaDe(solicitud, SolicitudCrearSala.class)) {
                    manejadorSalas.crearSala(cliente, manejadorServidores.buscarServidorLibre(), solicitudJSON);
                } else if (Deserializador.esJsonInstanciaDe(solicitud, SolicitudUnirseSala.class)) {
                    manejadorSalas.unirClienteASala(cliente, solicitudJSON);
                } else if (Deserializador.esJsonInstanciaDe(solicitud, SolicitudColocarFicha.class)) {
                    manejadorSalas.enviarSolicitudAServidor(cliente, solicitudJSON);
                } else {
                    throw new AssertionError("Tipo de solicitud desconocido: " + tipoSolicitud);
                }
                
            }
        } catch (Exception e) {
        }
    }
    
    private void redirigirRespuestas(ConexionServidor servidor) {
        
    }
    
}
