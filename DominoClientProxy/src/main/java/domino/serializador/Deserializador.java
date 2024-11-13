/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.serializador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

    public EventoRespuesta convertirJSONAEvento(JsonObject jsonObject) {

        return null;

    }

}
