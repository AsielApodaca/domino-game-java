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
import java.util.List;
import domino.listeners.IServidorProxyListener;

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
    private List<IServidorProxyListener> listeners;

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

            new Thread(() -> procesarSolicitud()).start();

        } catch (Exception e) {
            System.out.println("Error al conectar con el Broker: " + e.getMessage());
        }
    }

    /**
     * Envia una respuesta en formato JSON al broker si hay una conexión activa.
     *
     * @param eventoRespuesta El objeto EventoRespuesta que se convertirá a JSON
     * y se enviará al broker.
     */
    public void enviarRespuestas(EventoRespuesta eventoRespuesta) {
        try {
            String jsonRespuesta = serializador.convertirEventoAJSON(eventoRespuesta);
            if (out != null && !socket.isClosed()) {
                // Enviamos el JSON como string al broker
                out.println(jsonRespuesta);
                out.flush();
                System.out.println("Solicitud enviada al broker: " + jsonRespuesta);
            } else {
                System.out.println("No hay conexión con el broker");
            }
        } catch (Exception e) {
            System.out.println("Error al redirigir respuesta: " + e.getMessage());
        }
    }

    /**
     * Procesa una solicitud en formato JSON, convirtiéndola en un objeto de
     * tipo EventoSolicitud y notificando a los listeners sobre la recepción de
     * la solicitud.
     *
     * @param jsonSolicitud El contenido de la solicitud en formato JSON.
     */
    private void procesarSolicitud() {
        try {
            String jsonString;

            while ((jsonString = in.readLine()) != null) {
                EventoSolicitud eventoSolicitud = deserializador.convertirJSONAEvento(jsonString);
                notificarSolicitudEvento(eventoSolicitud);
            }
        } catch (Exception e) {
            System.out.println("Error al procesar la solicitud" + e.getMessage());
        }
    }

    /**
     * Notifica a todos los listeners registrados sobre la recepción de un
     * EventoSolicitud.
     *
     * @param eventoSolicitud El objeto EventoSolicitud que se notificará a los
     * listeners.
     */
    private void notificarSolicitudEvento(EventoSolicitud eventoSolicitud) {
        for (IServidorProxyListener listener : listeners) {
            listener.onRecibirSolicitud(eventoSolicitud);
        }
    }

    /**
     * Agrega un listener a la lista de listeners si no está ya registrado.
     *
     * @param listener El listener a agregar.
     */
    public void agregarListener(IServidorProxyListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
}
