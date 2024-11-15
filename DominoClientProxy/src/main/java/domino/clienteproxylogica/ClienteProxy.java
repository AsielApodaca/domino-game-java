/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.clienteproxylogica;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domino.listeners.IProxyListener;
import domino.respuestas.EventoRespuesta;
import domino.serializador.Deserializador;
import domino.serializador.Serializador;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import dominodto.CasillaDTO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author castr
 */
public class ClienteProxy {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;
    private final Gson gson;
    private final List<IProxyListener> listeners;
    private final Serializador serializador;
    private final Deserializador deserializador;

    public ClienteProxy(String host, int PORT) {
        this.gson = new Gson();
        this.listeners = new ArrayList<>();
        this.serializador = new Serializador();
        this.deserializador = new Deserializador();
        run(host, PORT);
    }

    /**
     * Establece una conexión de socket con el broker en el host y puerto
     * especificados. Identifica la conexión como cliente y configura los flujos
     * de entrada y salida.
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
            clientType.addProperty("type", "CLIENT");
            out.println(gson.toJson(clientType));

            System.out.println("Conexion ClienteProxy exitosa con Broker");
            this.running = true;

//            new Thread(() -> procesarRespuesta())
        } catch (Exception e) {
            System.out.println("Error al conectar con el Broker: " + e.getMessage());
        }
    }

    /**
     * Convierte una solicitud de evento al broker en formato JSON.
     *
     * @param eventoSolicitud el evento a enviar al broker
     */
    private String conversorEventoASolicitud(EventoSolicitud eventoSolicitud) {
        try {
            String jsonSolicitud = serializador.convertirEventoAJSON(eventoSolicitud);
            return jsonSolicitud;
        } catch (Exception e) {
            System.out.println("Error al convertir el EventoSolicitud a JSON: " + e.getMessage());
            return null;
        }
    }

    /**
     * Envia la solicitud serializada al broker
     *
     */
    public void enviarSolicitud(EventoSolicitud solicitud) {
        try {
            String jsonSolicitud = conversorEventoASolicitud(solicitud);
            if (out != null && !socket.isClosed()) {
                // Enviamos el JSON como string al broker
                out.println(jsonSolicitud);
                out.flush();
                System.out.println("Solicitud enviada al broker: " + jsonSolicitud);
            } else {
                System.out.println("No hay conexión con el broker");
            }
        } catch (Exception e) {
            System.out.println("Error al redirigir solicitud: " + e.getMessage());
        }
    }

    public EventoRespuesta conversorSolicitudAEvento(String respuesta) {
        try {
            EventoRespuesta eventoRespuesta = deserializador.convertirJSONAEvento(respuesta);
            return eventoRespuesta;
        } catch (Exception e) {
            System.out.println("Error al convertir el JSON a EventoRespuesta: " + e.getMessage());
            return null;
        }

    }

    public void procesarRespuesta(String jsonRespuesta) {
        EventoRespuesta eventoRespuesta = conversorSolicitudAEvento(jsonRespuesta);
        notificarRespuestaEvento(eventoRespuesta);
    }

    public void notificarRespuestaEvento(EventoRespuesta eventoRespuesta) {
        for (IProxyListener listener : listeners) {
            listener.onRecibirRespuesta(eventoRespuesta);
        }
    }

    public void agregarListener(IProxyListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
}
