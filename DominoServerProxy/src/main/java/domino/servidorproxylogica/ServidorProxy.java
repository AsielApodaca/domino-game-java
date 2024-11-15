/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.servidorproxylogica;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domino.listeners.IProxyListener;
import domino.respuestas.EventoRespuesta;
import domino.serializador.Deserializador;
import domino.serializador.Serializador;
import domino.solicitudes.EventoSolicitud;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

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
    private List<IProxyListener> listeners;

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

//            new Thread(()-> procesarSolicitud());

        } catch (Exception e) {
            System.out.println("Error al conectar con el Broker: " + e.getMessage());
        }
    }

    /**
     * Procesa una solicitud en formato JSON, convirtiéndola en un objeto de
     * tipo EventoSolicitud y notificando a los listeners sobre la recepción de
     * la solicitud.
     *
     * @param jsonSolicitud El contenido de la solicitud en formato JSON.
     */
    public void procesarSolicitud(String jsonSolicitud) {
        try {
            EventoSolicitud eventoSolicitud = deserializador.convertirJSONAEvento(jsonSolicitud);
            notificarSolicitudEvento(eventoSolicitud);
        } catch (Exception e) {
        }
    }

    /**
     * Envia una respuesta en formato JSON al broker si hay una conexión activa.
     *
     * @param eventoRespuesta El objeto EventoRespuesta que se convertirá a JSON
     * y se enviará al broker.
     */
    public void enviarRespuestas(EventoRespuesta eventoRespuesta) {
//        try {
//            String jsonRespuesta = conversorEventoARespuesta(eventoRespuesta);
//           
//        } catch (Exception e) {
//            System.out.println("Error al redirigir respuesta: " + e.getMessage());
//        }
    }

    /**
     * Notifica a todos los listeners registrados sobre la recepción de un
     * EventoSolicitud.
     *
     * @param eventoSolicitud El objeto EventoSolicitud que se notificará a los
     * listeners.
     */
    public void notificarSolicitudEvento(EventoSolicitud eventoSolicitud) {
        for (IProxyListener listener : listeners) {
            listener.onRecibirSolicitud(eventoSolicitud);
        }
    }

    /**
     * Convierte un objeto EventoRespuesta en su representación JSON.
     *
     * @param eventoRespuesta El objeto EventoRespuesta a convertir a JSON.
     * @return El JSON generado a partir del EventoRespuesta o null si ocurrió
     * un error.
     */
    private String conversorEventoARespuesta(EventoRespuesta eventoRespuesta) {
        try {
            String jsonRespuesta = serializador.convertirEventoAJSON(eventoRespuesta);
            return jsonRespuesta;
        } catch (Exception e) {
            System.out.println("Error al convertir el EventoRespuesta a JSON: " + e.getMessage());
            return null;
        }
    }
}
