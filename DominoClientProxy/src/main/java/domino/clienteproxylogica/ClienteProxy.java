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

    public ClienteProxy(String host, int PORT) {
        this.gson = new Gson();
        this.listeners = new ArrayList<>();
        run(host, PORT);
    }

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

        } catch (Exception e) {
            System.out.println("Error al conectar con el Broker: " + e.getMessage());
        }
    }

    /**
     * Metodo para mandar una solicitud al broker en formato JSON, en este caso,
     * al ser una aplicacion de mensajes sencilla la request es el mismo mensaje
     * que se envia, y para antes de mandarlo, lo convierte a formato JSON.
     *
     * @param message
     */
    public void enviarSolicitud(EventoSolicitud solicitudEvento) {
        try {
            JsonObject jsonRequest = new JsonObject();

            if (solicitudEvento instanceof SolicitudCasillaSeleccionada) {
                SolicitudCasillaSeleccionada solicitud = (SolicitudCasillaSeleccionada) solicitudEvento;
                CasillaDTO casilla = solicitud.getCasillaDTO();

            }

        } catch (Exception e) {
        }
    }

    /**
     * Metodo que funciona conjunto a un Hilo creado en el constructor para
     * estar atento a las respuesta que vayan llegando del Broker. Este le
     * comunica al cliente cuando le llegan los mensajes para que el cliente lo
     * maneje. Metodo de redireccionamiento.
     */
    private void forwardResponses() {

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
