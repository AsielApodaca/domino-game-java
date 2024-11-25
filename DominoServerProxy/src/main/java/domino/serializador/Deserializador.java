/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.serializador;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.solicitudes.SolicitudFichaSeleccionada;
import domino.solicitudes.SolicitudIniciarPartida;
import java.util.List;

/**
 *
 * @author castr
 */
public class Deserializador {

    private final Gson gson;
    private final List<Class<? extends EventoSolicitud>> clasesPermitidas = List.of(
            SolicitudCasillaSeleccionada.class,
            SolicitudFichaSeleccionada.class,
            SolicitudIniciarPartida.class
    );
    

    public Deserializador() {
        this.gson = new Gson();
    }

    public EventoSolicitud convertirJSONAEvento(String jsonObject) {
        for(Class<? extends EventoSolicitud> clase: clasesPermitidas) {
            if(isJsonInstanceOf(jsonObject, clase)) {
                return gson.fromJson(jsonObject, clase);
            }
        }
        return null; // Si no coincide con ninguna clase tipo EventoSolicitud
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
