/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.serializador;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudAbandonarSala;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.solicitudes.SolicitudCrearSala;
import domino.solicitudes.SolicitudFichaSeleccionada;
import domino.solicitudes.SolicitudIniciarPartida;
import domino.solicitudes.SolicitudUnirseSala;
import java.util.List;

/**
 *
 * @author castr
 */
public class Deserializador {

    private final Gson gson;
    private final List<Class<? extends EventoSolicitud>> clasesPermitidas = List.of(
            SolicitudAbandonarSala.class,
            SolicitudCasillaSeleccionada.class,
            SolicitudCrearSala.class,
            SolicitudFichaSeleccionada.class,
            SolicitudIniciarPartida.class,
            SolicitudUnirseSala.class
    );
    

    public Deserializador() {
        this.gson = new Gson();
    }

    public EventoSolicitud convertirJSONAEvento(String jsonObject) {
        for (Class<? extends EventoSolicitud> clase : clasesPermitidas) {
            try {
                EventoSolicitud evento = gson.fromJson(jsonObject, clase);
                if (evento != null) {
                    return evento;
                }
            } catch (JsonSyntaxException e) {
                // Continúa con la siguiente clase si no coincide
            }
        }
        System.out.println("El JSON proporcionado no coincide con ninguna clase permitida.");
        return null;
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
