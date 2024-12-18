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
import domino.respuestas.RespuestaCerrarSala;
import domino.respuestas.RespuestaColocarFichaTablero;
import domino.respuestas.RespuestaMostrarCasillasDisponibles;
import domino.respuestas.RespuestaMostrarFichasActualizadasDeJugador;
import domino.respuestas.RespuestaMostrarPantallaPartida;
import domino.respuestas.RespuestaMostrarSalaDisponible;
import domino.respuestas.RespuestaOcultarCasillasDisponibles;
import domino.respuestas.RespuestaOcultarSalaDisponible;
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
import domino.solicitudes.SolicitudAbandonarSala;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.solicitudes.SolicitudIniciarPartida;
import domino.solicitudes.SolicitudObtenerSalasDisponibles;
import java.util.List;

/**
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Broker {

    private final int PORT = 3000;
    private final ManejadorClientes manejadorClientes;
    private final ManejadorServidores manejadorServidores;
    private final ManejadorSalas manejadorSalas;
    private Gson gson;
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
        this.manejadorClientes = new ManejadorClientes();
        this.manejadorServidores = new ManejadorServidores();
        this.manejadorSalas = new ManejadorSalas();
        this.gson = new Gson();
        runBroker();
    }

    public void runBroker() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor corriendo en puerto: " + PORT);

            while (true) {
                Socket newConenctionSocket = serverSocket.accept();

                new Thread(() -> manejarConexiones(newConenctionSocket)).start();
            }
        } catch (IOException e) {
        }
    }

    private void manejarConexiones(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String jsonString = reader.readLine();

            String type = JsonParser.parseString(jsonString).getAsJsonObject().get("type").getAsString();
            String id;

            if (type.equals("CLIENT")) {
                ConexionCliente cliente = manejadorClientes.agregarCliente(socket);

                System.out.println("Se ha conectado un nuevo Cliente con el id: " + cliente.getId());

                new Thread(() -> redirigirSolicitudes(cliente)).start();;
            } else if (type.equals("SERVER")) {
                ConexionServidor servidor = manejadorServidores.agregarServidor(socket);

                System.out.println("Se ha conectado un nuevo Server con el id: " + servidor.getId());

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
            String solicitud;

            while ((solicitud = cliente.getReader().readLine()) != null) {
                JsonObject solicitudJSON = JsonParser.parseString(solicitud).getAsJsonObject();
                solicitudJSON.addProperty("idCliente", cliente.getId());
                JsonObject usuarioDTO = solicitudJSON.getAsJsonObject("usuarioDTO");
                usuarioDTO.addProperty("idCliente", cliente.getId());

                System.out.println(solicitud);
                System.out.println(solicitudJSON.toString());

                if (Deserializador.esJsonInstanciaDe(solicitud, SolicitudCrearSala.class)) {
                    manejadorSalas.crearSala(cliente, manejadorServidores.buscarServidorLibre(), solicitudJSON);
                } else if (Deserializador.esJsonInstanciaDe(solicitud, SolicitudUnirseSala.class)) {
                    manejadorSalas.unirClienteASala(cliente, solicitudJSON);
                    manejadorSalas.enviarSolicitudAServidor(cliente, solicitudJSON);
                } else if(Deserializador.esJsonInstanciaDe(solicitud, SolicitudAbandonarSala.class)) {
                    manejadorSalas.eliminarClienteDeSala(cliente);
                    manejadorSalas.enviarSolicitudAServidor(cliente, solicitudJSON);
                } else if(Deserializador.esJsonInstanciaDe(solicitud, SolicitudIniciarPartida.class)) {
                    manejadorSalas.enviarSolicitudIniciarPartida(cliente, solicitudJSON);
                } else if(Deserializador.esJsonInstanciaDe(solicitud, SolicitudObtenerSalasDisponibles.class)) {
                    manejadorSalas.enviarSolicitudObtenerSalasDisponibles(cliente, usuarioDTO);
                } else {
                    manejadorSalas.enviarSolicitudAServidor(cliente, solicitudJSON);
                }

            }
        } catch (Exception e) {
            //AQUI OCURRE UNA EXCEPCION CUANDO LOS JUGADORES SE DESCONECTAN DEL BROKER.
            //ES DECIR, CUANDO ABANDONAN LA PARTIDA
            System.out.println(e.getMessage());
            System.out.println("El Cliente con el ID: " + cliente.getId() + " se ha desconectado");
            manejadorSalas.enviarSolicitudAbandonarSalaClientePorDesconexion(cliente);
        }
    }

    private void redirigirRespuestas(ConexionServidor servidor) {
        try {
            String respuesta;

            while ((respuesta = servidor.getReader().readLine()) != null) {
                JsonObject respuestaJSON = JsonParser.parseString(respuesta).getAsJsonObject();

                String tipoRespuesta = respuestaJSON.get("nombreEvento").getAsString();
                System.out.println("Redirigiendo respuesta en broker: " + tipoRespuesta);

                EventoRespuesta eventoRespuesta = Deserializador.convertirJSONAEvento(respuesta);
                if(eventoRespuesta == null) {
                    throw new AssertionError("Tipo de solicitud desconocido: " + tipoRespuesta);
                } else if(Deserializador.esJsonInstanciaDe(respuesta, RespuestaCerrarSala.class)) {
                    manejadorSalas.cerrarSala(servidor);
                } else if(Deserializador.esJsonInstanciaDe(respuesta, RespuestaMostrarSalaDisponible.class)) {
                    manejadorSalas.enviarRespuestaObtenerSalaDisponible(servidor, respuestaJSON, manejadorClientes);
                } else if(Deserializador.esJsonInstanciaDe(respuesta, RespuestaOcultarSalaDisponible.class)) {
                    manejadorSalas.enviarRespuestaOcultarSalaDisponible(servidor, respuestaJSON, manejadorClientes);
                } else if(eventoRespuesta.esParaTodos()){
                    manejadorSalas.enviarRespuestaATodosLosClientes(servidor, respuestaJSON);
                } else {
                    manejadorSalas.enviarRespuestaACliente(servidor, respuestaJSON);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Se ha desconectado el Servidor con el ID: " + servidor.getId());
        }
    }

}
