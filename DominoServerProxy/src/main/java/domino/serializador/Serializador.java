/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.serializador;

import com.google.gson.Gson;
import domino.respuestas.EventoRespuesta;

/**
 *
 * @author castr
 */
public class Serializador {

    private final Gson gson;

    public Serializador() {
        this.gson = new Gson();
    }

    public String convertirEventoAJSON(EventoRespuesta eventoRespuesta) {
        return gson.toJson(eventoRespuesta);
    }
}
