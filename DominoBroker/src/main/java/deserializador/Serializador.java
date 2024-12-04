/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domino.respuestas.EventoRespuesta;
import domino.solicitudes.EventoSolicitud;

/**
 *
 * @author castr
 */
public class Serializador {

    private static final Gson gson = new Gson();

    public Serializador() {
        
    }

    public static String convertirEventoRespuestaAJSON(EventoRespuesta eventoRespuesta) {
        return gson.toJson(eventoRespuesta);
    }
    
    public static String convertirEventoSolicitudAJSON(EventoSolicitud eventoRespuesta) {
        return gson.toJson(eventoRespuesta);
    }
}
