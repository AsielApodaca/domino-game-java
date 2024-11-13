/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.servidorproxylogica;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domino.respuestas.EventoRespuesta;
import domino.serializador.Deserializador;
import domino.serializador.Serializador;
import domino.solicitudes.EventoSolicitud;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author castr
 */
public class ServidorProxy {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;
    private final Gson gson;
    private final Serializador serializador;
    private final Deserializador deserializador;

    public ServidorProxy(String host, int PORT) {
        this.gson = new Gson();
        this.serializador = new Serializador();
        this.deserializador = new Deserializador();
        run(host, PORT);
    }

    /**
     * Establece una conexión de socket con el broker en el host y puerto
     * especificados. Identifica la conexión como servidor y configura los
     * flujos de entrada y salida.
     *
     * @param host la dirección del broker
     * @param PORT el puerto del broker
     */
    public void run(String host, int PORT) {
        try {
            this.socket = new Socket(host, PORT);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Identificarse como cliente para el broker
            JsonObject clientType = new JsonObject();
            clientType.addProperty("type", "SERVER");
            out.println(gson.toJson(clientType));

            System.out.println("Conexion ServidorProxy exitosa con Broker");
            this.running = true;

        } catch (Exception e) {
            System.out.println("Error al conectar con el Broker: " + e.getMessage());
        }
    }

    public void procesarSolicitud(JsonObject jsonSolicitud) {
        try {
//            EventoSolicitud eventoSolicitud = deserializador.convertirJSONAEvento(jsonSolicitud);
        } catch (Exception e) {
        }
    }

    public void procesarRespuesta() {

    }

    public void notificarSolicitudEvento(EventoRespuesta eventoRespuesta) {

    }
}
