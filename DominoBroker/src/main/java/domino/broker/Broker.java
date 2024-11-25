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
import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaActualizarCantidadFichas;
import domino.respuestas.RespuestaAgregarFichaJugador;
import domino.respuestas.RespuestaColocarFichaTablero;
import domino.respuestas.RespuestaMostrarCasillasDisponibles;
import domino.respuestas.RespuestaMostrarFichasActualizadasDeJugador;
import domino.respuestas.RespuestaMostrarPantallaPartida;
import domino.respuestas.RespuestaOcultarCasillasDisponibles;
import domino.respuestas.RespuestaOtorgarTurno;
import domino.solicitudes.SolicitudFichaSeleccionada;
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
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.solicitudes.SolicitudIniciarPartida;
import java.util.List;

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
    private final List<Class<? extends EventoRespuesta>> respuestasParaUno = List.of(
            RespuestaAgregarFichaJugador.class,
            RespuestaMostrarCasillasDisponibles.class,
            RespuestaMostrarFichasActualizadasDeJugador.class,
            RespuestaOcultarCasillasDisponibles.class
    );
    private final List<Class<? extends EventoRespuesta>> respuestasParaTodos = List.of(
            RespuestaActualizarCantidadFichas.class,
            RespuestaColocarFichaTablero.class,
            RespuestaMostrarPantallaPartida.class,
            RespuestaOtorgarTurno.class
    );
    
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
            
            String type = JsonParser.parseString(jsonString).getAsJsonObject().get("type").getAsString() ;
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
                solicitudJSON.addProperty("idCliente", cliente.getId());

                System.out.println(solicitud);
                System.out.println(solicitudJSON.toString());
                
                if (Deserializador.esJsonInstanciaDe(solicitud, SolicitudCrearSala.class)) {
                    manejadorSalas.crearSala(cliente, manejadorServidores.buscarServidorLibre(), solicitudJSON);
                } else if (Deserializador.esJsonInstanciaDe(solicitud, SolicitudUnirseSala.class)) {
                    manejadorSalas.unirClienteASala(cliente, solicitudJSON);
                } else if (Deserializador.esJsonInstanciaDe(solicitud, SolicitudFichaSeleccionada.class) || Deserializador.esJsonInstanciaDe(solicitud, SolicitudCasillaSeleccionada.class)) {
                    manejadorSalas.enviarSolicitudAServidor(cliente, solicitudJSON);
                } else {
                    throw new AssertionError("Tipo de solicitud desconocido");
                }
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void redirigirRespuestas(ConexionServidor servidor) {
        try {
            String respuesta ;
            
            while((respuesta = servidor.getReader().readLine()) != null) {
                JsonObject respuestaJSON = JsonParser.parseString(respuesta).getAsJsonObject() ;
          
                String tipoRespuesta = respuestaJSON.get("tipo").getAsString();
                
                for (Class<? extends EventoRespuesta> claseRespuestaParaUno : respuestasParaUno) {
                    if (Deserializador.esJsonInstanciaDe(respuesta, claseRespuestaParaUno)) {
                        manejadorSalas.enviarRespuestaACliente(servidor, respuestaJSON);
                        return;
                    }
                }
                for (Class<? extends EventoRespuesta> claseRespuestaParaTodos : respuestasParaTodos) {
                    if (Deserializador.esJsonInstanciaDe(respuesta, claseRespuestaParaTodos)) {
                        manejadorSalas.enviarRespuestaATodosLosClientes(servidor, respuestaJSON);
                        return;
                    }
                }
                throw new AssertionError("Tipo de solicitud desconocido: " + tipoRespuesta);
                
            }
        } catch (Exception e) {
        }
    }
    
}
