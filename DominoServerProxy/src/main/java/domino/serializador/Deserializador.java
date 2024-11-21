/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.serializador;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaQuitarFichaJugador;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.solicitudes.SolicitudCrearSala;
import domino.solicitudes.SolicitudFichaSeleccionada;
import domino.solicitudes.SolicitudUnirseSala;

/**
 *
 * @author castr
 */
public class Deserializador {

    private final Gson gson;

    public Deserializador() {
        this.gson = new Gson();
    }

    public EventoSolicitud convertirJSONAEvento(String jsonObject) {
        if (isJsonInstanceOf(jsonObject, SolicitudFichaSeleccionada.class)) {
            return gson.fromJson(jsonObject, SolicitudFichaSeleccionada.class);
        } else if (isJsonInstanceOf(jsonObject, SolicitudCasillaSeleccionada.class)) {
            return gson.fromJson(jsonObject, SolicitudCasillaSeleccionada.class);
        } else if (isJsonInstanceOf(jsonObject, SolicitudCrearSala.class)) {
            return gson.fromJson(jsonObject, SolicitudCrearSala.class);
        } else if (isJsonInstanceOf(jsonObject, SolicitudUnirseSala.class)) {
            return gson.fromJson(jsonObject, SolicitudUnirseSala.class);
        } else {
            return null;
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
