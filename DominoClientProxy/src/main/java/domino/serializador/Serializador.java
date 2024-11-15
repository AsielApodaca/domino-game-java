/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.serializador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import dominodto.CasillaDTO;

/**
 *
 * @author castr
 */
public class Serializador {

    private final Gson gson;

    public Serializador() {
        this.gson = new Gson();
    }

    public String convertirEventoAJSON(EventoSolicitud solicitud) {
        return gson.toJson(solicitud);
    }
}
