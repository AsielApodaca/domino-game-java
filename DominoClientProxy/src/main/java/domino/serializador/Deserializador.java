/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.serializador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import domino.respuestas.EventoRespuesta;

/**
 *
 * @author castr
 */
public class Deserializador {

    private final Gson gson;

    public Deserializador() {
        this.gson = new Gson();
    }

    public EventoRespuesta convertirJSONAEvento(String jsonObject) {
        if (isJsonInstanceOf(jsonObject, RespuestaQuitarFichaJugador.class)) {
            return gson.fromJson(jsonObject, RespuestaQuitarFichaJugador.class);
        }
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
